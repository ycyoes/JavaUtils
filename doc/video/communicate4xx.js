define([
    "dojo/on", "dojo/_base/lang", "dojo/_base/declare"
], function (
    ON, LANG, DECLARE
) {
    return DECLARE(null, {

        // 构造函数
        constructor: function () {
            this.app = APP;
            var that = this;
            dojo.connect(APP, "mapInitComplated", function (wrapmap) {
                that.setCommunicate4xx();
            });
        },
        setCommunicate4xx: function () {
            try {
                this.init({
                    map: this.app.map
                });
                this.start();
            } catch (e) {
                this.app.showError(e);
            }
        },
        // 初始化
        init: function (options) {
            this.initOptions(options);
            this.addEvent();
            this.initCommunicate();
        },
        // 初始化选项
        initOptions: function (options) {
            this.options = options;
            this.map = options.map;
            this.agsLayer = options.map.agsLayer;
            // 配置用户名、ID
            this.userId = CONFIG.userId;
            this.userName = CONFIG.userName;

            // 定位使用
            var queryStringDe = CONFIG.getRequestDe();
            this.startX = queryStringDe["x"];
            this.startY = queryStringDe["y"];
        },
        // 添加事件
        addEvent: function () {
        },

        // 启动
        start: function () {
            if (this.startX && this.startY) {
                var x = parseFloat(this.startX);
                var y = parseFloat(this.startY);
                if (!this.map.common.isOutOfChina(x, y)) {
                    var level = this.map.MainMap.getLevel();
                    level += this.map.MinLevel;
                    this.map.ZoomToLevel(x, y, level);
                    // 展开指定新城
                    APP.footprint.showCountyPeopleByCountyCoords(x, y);
                }
            }
        },

        //地图弹窗呼叫
        callPeopleLive: function (user) {
            var userId = user.imUserId || user.userId;
            if (this.curRole == 0 || this.curRole == 1) {
                //新用户
                if (!this.userLists[userId]) {
                    if (this.methods.getObjLength(this.userLists) >= this.maxNum) {
                        layer.msg('同时视频通讯人数不能超过' + (this.maxNum + 1));
                    } else {
                        this.callUsers([user]);
                    }
                }
            }
        },


        liveAct: null,                //互动直播对象
        userName: '',                 //互动直播登录用户名(当前pc端登录中文名)
        userId: '',                   //当前pc端登录ID
        imUserId: '',                 //互动直播登录用户id
        curRole: "0",                 //当前角色 0无角色 1发起者 2接收者
        maxNum: 20,                   //最多呼叫人数
        keyword: "",                  //当前模糊搜索 关键字
        searchTreeIsloading: false,   //搜索用户树是否正在加载
        willCallUsers: {},            //选中的准备呼叫的用户列表
        userLists: {},                //已在呼叫列表的用户
        speed: 30000,                 //SDK心跳包、检查在线用户频率（毫秒）
        dept: { name: '西咸新区', depId: '0' },  //当前搜索辖区类型 

        //初始化即时通讯
        initCommunicate: function () {
            var _this = this;
            //判断是否在其他设备登录
            this.isOtherLogin(function () {
                _this.getDepIdOptions(function () {

                    //初始化SDK
                    _this.createLiveAct(function () {
                        _this.imUserId = _this.liveAct.id;

                        //sdk心跳
                        _this.sdkHeading();
                        _this.checkOnline();

                        //初始化登录之后监听异常和房间事件
                        _this.liveAct.listenerObjs(function (obj) {
                            _this.processListener(obj);
                        });

                        //开始监听C2C消息
                        _this.msgListenerAction();

                        //监听搜索事件
                        _this.createSearchEvents();

                    })


                })
            });
        },

        //判断是否在别的设备登录SDK
        isOtherLogin: function (cb) {
            var _this = this;
            $.ajax({
                url: "//" + CONFIG.domain + "/zhzlbackend/webIm/webIm/getUserOnline",
                type: 'get',
                cache: false,
                data: {
                    userId: _this.userId
                },
                success: function (res) {
                    if (res.success) {
                        var data = res.data && res.data[0]
                        if (data && data.isOnline && data.isOnline == 1 && data.dataSource == 2) {
                            layer.alert("你的即时通讯账号在其他设备登录了")
                        } else {
                            //登录SDK
                            cb && typeof cb === "function" && cb();
                        }
                    } else {
                        layer.alert(res.errMsg)
                    }
                },
                error: function (err) {
                    //console.log(err)
                }
            })
        },
        //获取辖区下拉选择项
        getDepIdOptions: function (cb) {
            var _this = this;
            $.ajax({
                url: '//' + CONFIG.domain + '/zhzlbackend/system/queryDomains',
                data: {
                    domainNames: 'imArea'
                },
                success: function (res) {
                    if (res.success) {
                        var options = res.data && res.data.imArea;
                        if (options && cb && typeof cb === 'function') {
                            var str = ''
                            options.map(function (item, index) {
                                var obj = {
                                    name: item.text,
                                    depId: item.value
                                },
                                    objStr = encodeURIComponent(JSON.stringify(obj));
                                if (index === 0) {
                                    _this.dept = obj;
                                    $(".search_wrap .depid_div .con span:eq(0)").html(item.text);
                                    $(".select_wrap .left_con .title:eq(0)").html(item.text);
                                    str += '<li data-dept="' + objStr + '" class="on">' + item.text + '</li>'
                                } else {
                                    str += '<li data-dept="' + objStr + '">' + item.text + '</li>'
                                }
                            })
                            $(".search_wrap .depid_div .options ul").empty().append(str);
                            cb();
                        }
                    } else {
                        console.log("获取辖区选择domains出错：" + res);
                    }
                },
                error: function (err) {
                    console.log("获取辖区选择domains出错：" + err);
                }
            })
        },
        //生成视频互动对象
        createLiveAct: function (cb) {
            var param = {
                getSigUrl: "//" + CONFIG.domain + "/zhzlbackend/webIm/webIm/getUserSig",
                creatRoomUrl: "//" + CONFIG.domain + "/zhzlbackend/webIm/webIm/getRoomId",
                liveGuestMax: 21,
                initedCallback: function () {
                    cb && typeof cb === 'function' && cb();
                }
            }
            this.liveAct = new liveAct(param);
        },
        //SDK心跳包
        sdkHeading: function () {
            var _this = this,
                now = new Date();
            setInterval(function () {
                $.ajax({
                    url: "//" + CONFIG.domain + "/zhzlbackend/webIm/webIm/userOnline",
                    type: 'get',
                    cache: false,
                    success: function (res) {
                        if (res.success) {
                            console.log(now + "sdk心跳成功");
                        } else {
                            console.log(now + "sdk心跳失败");
                        }
                    },
                    error: function (err) {
                        //console.log(err)
                        console.log(now + "sdk心跳失败");
                    }
                });
            }, this.speed)
        },
        //定时检查在线用户
        checkOnline: function () {
            var _this = this,
                now = new Date();
            setInterval(function () {
                var userIdArr = [];
                $(".select_wrap .tree ul li").each(function () {
                    var userId = $(this).attr('userId');
                    userId && userIdArr.push(userId)
                });
                userIdArr.length > 0 && $.ajax({
                    url: "//" + CONFIG.domain + "/zhzlbackend/webIm/webImServer/getUserOnline",
                    type: 'get',
                    cache: false,
                    data: {
                        userId: userIdArr.join(','),
                        depId: _this.dept.depId
                    },
                    success: function (res) {
                        if (res.success) {
                            var data = res.data;
                            data.map(function (item) {
                                var liuserid = item.userId,
                                    $el = $(".select_wrap .tree ul li[userId='" + liuserid + "']"),
                                    userInfo = $el ? JSON.parse(decodeURIComponent($el.attr("data-item"))) : null;
                                if (item.isOnline == 1) {
                                    $el.addClass("online");
                                    userInfo && $el.attr("data-item", encodeURIComponent(JSON.stringify($.extend({}, userInfo, item))));
                                } else {
                                    $el.removeClass("online");
                                }
                            });
                        } else {
                            //console.log(res);
                        }
                    },
                    error: function (err) {
                        //console.log(err);
                    }
                });
            }, this.speed)
        },
        //搜索即时通讯人员界面交互事件
        createSearchEvents: function () {
            var _this = this;

            //辖区下拉选择
            $(".search_wrap .options ul li").unbind('click').on('click', function (e) {
                e.stopPropagation();
                var dept = JSON.parse(decodeURIComponent($(this).attr("data-dept"))),
                    index = $(this).index(),
                    str = $(this).html();
                _this.dept = dept;
                $(".search_wrap .depid_div .con span:eq(0)").html(str);
                $(".search_wrap .options ul li").removeClass("on").eq(index).addClass("on");
                $(".select_wrap .left_con .title:eq(0)").html(str);
                _this.keyword = $(".search_wrap input[name='keyword']").val();
                _this.dosearch();
            })

            //搜索框输入
            $(".search_wrap input[name='keyword']").unbind('keyup').on('keyup', function (e) {
                e.stopPropagation();
                var value = $(this).val(),
                    keyCode = e.keyCode;
                _this.keyword = value;
                keyCode == 13 && _this.dosearch();
            })

            //搜索框删除按钮
            $(".search_wrap .input_del").unbind('click').on('click', function (e) {
                e.stopPropagation();
                $(".search_wrap input[name='keyword']").val('');
                _this.keyword = '';
                $(".select_wrap").is(":visible") && _this.dosearch();
            })

            //搜索按钮点击
            $(".search_wrap .search_btn").unbind('click').on('click', function (e) {
                e.stopPropagation();
                var value = $(".search_wrap input[name='keyword']").val();
                _this.keyword = value;
                _this.dosearch();
            })



            //_this.dosearch();
        },
        //即时通讯人员搜索
        dosearch: function (node, isSignin) {
            $(".select_wrap").show();
            if (this.searchTreeIsloading) return;
            this.searchTreeIsloading = true;

            var _this = this,
                haskeyword = _this.keyword || (_this.methods.getType(_this.keyword) == "Number" && _this.keyword == 0) ? true : false,
                todata = { depId: _this.dept.depId },
                initHtml = node ? '<div class="con">\
                                    <ul>\
                                        <li class="loading">\
                                            <div class="title">\
                                                <div class="icon"></div>\
                                                <div class="text">正在加载……</div>\
                                            </div>\
                                        </li>\
                                    </ul>\
                                </div>':
                                '<li class="loading">\
                                        <div class="title">\
                                            <div class="icon"></div>\
                                            <div class="text">正在加载……</div>\
                                        </div>\
                                    </li>';

            if (node) {
                node.$el.removeClass("toclose").addClass("open"),
                node.$el.find(".con").remove();
                node.$el.append(initHtml);
            } else {
                $(".select_wrap .left_con .tree ul").html(initHtml);
                $(".select_wrap .left_con .tree").mCustomScrollbar({
                    theme: "minimal-dark"
                });
                $(".select_wrap .right_con .con").mCustomScrollbar({
                    theme: "minimal-dark"
                });

            }
            haskeyword && (todata['keyWord'] = _this.keyword);
            node && node.id && (todata['pid'] = node.id);
            isSignin && (todata['isSignin'] = isSignin);
            $.ajax({
                url: haskeyword ? "//" + CONFIG.domain + "/zhzlbackend/webIm/webImServer/queryLike" :
                    "//" + CONFIG.domain + "/zhzlbackend/webIm/webImServer/getDepUserIm",
                type: 'get',
                data: todata,
                success: function (res) {
                    var data = res.data
                    _this.renderTree({ data: data, haskeyword: haskeyword, node: node });
                    _this.searchTreeIsloading = false;
                },
                error: function (err) {
                    //console.log(err)
                    _this.searchTreeIsloading = false;
                }
            })
        },
        //即时通讯人员搜索结果树渲染
        renderTree: function (obj) {
            var _this = this,
                data = obj.data,
                haskeyword = obj.haskeyword,
                node = obj.node,
                nodataStr = '<li class="nodata">\
                                <div class="title">\
                                    <div class="icon"></div>\
                                    <div class="text">没有数据</div>\
                                </div>\
                            </li>';
            if (data) {
                var c_str = '';
                c_str += data.dep ? getli(data.dep, 'dep') : '';
                c_str += data.signin ? getli(data.signin, 'signin') : '';
                c_str += data.user ? getli(data.user, 'user') : '';
                c_str = c_str ? c_str : nodataStr;
                if (node) {
                    node.$el.find(".con ul").html(c_str);
                } else {
                    if (haskeyword) {
                        $(".select_wrap .left_con .tree ul").html(c_str);
                    } else {
                        var s_str = '<li class="open root">\
                                    <div class="title">\
                                        <div class="icon"></div>\
                                        <div class="text">'+ this.dept.name + '</div>\
                                    </div>\
                                    <div class="con">\
                                        <ul>',
                            e_str = '</ul></div></li>';
                        $(".select_wrap .left_con .tree ul").html(s_str + c_str + e_str);
                    }
                }
            } else {
                if (node) {
                    node.$el.find(".con .ul").html(nodataStr);
                } else {
                    $(".select_wrap .left_con .tree ul").html(nodataStr);
                }
            }

            function getli(arr, type) {
                var str = ''
                arr.map(function (item) {
                    if (type == "user") {
                        var itemStr = encodeURIComponent(JSON.stringify(item));
                        if (_this.dept.depId === '0') {
                            str += '<li class="person" data-item="' + itemStr + '" userId="' + item.userId + '" imUserId="' + item.imUserId + '">\
                                        <div class="title">\
                                            <div class="icon"></div>\
                                            <div class="text">'+ item.userName + '</div>\
                                        </div>\
                                    </li>'
                        } else {
                            var iconStr = item.dataSource == 1 ?
                                    '<div class="icon_pc"></div>' :
                                    '<div class="icon_moblie"></div>'
                            str += '<li class="person" data-item="' + itemStr + '" userId="' + item.userId + '" imUserId="' + item.imUserId + '">\
                                        <div class="title">\
                                            <div class="icon"></div>\
                                            <div class="text">'+ item.userName + '</div>\
                                            '+ iconStr + '\
                                            <div class="icon_location"></div>\
                                        </div>\
                                    </li>'
                        }
                    } else {
                        item['isSignin'] = type == "signin";
                        var itemStr = encodeURIComponent(JSON.stringify(item));
                        str += '<li class="toclose" data-item="' + itemStr + '" groupId="' + item.id + '">\
                                    <div class="title">\
                                        <div class="icon"></div>\
                                        <div class="text">'+ item.name + '</div>\
                                    </div>\
                                </li>'
                    }
                })
                return str;
            }

            //搜索树点击
            $(".select_wrap .left_con .tree ul li").unbind("click").on("click", function (e) {
                e.stopPropagation();
                var $el = $(this);
                if ($el.hasClass("root")) return;
                if ($el.length > 0) {
                    var isUser = $el.hasClass("person"),
                        isOnlineUser = $el.hasClass("online"),
                        userId = $el.attr("imUserId"),
                        item = JSON.parse(decodeURIComponent($el.attr("data-item")));
                    if (isUser) {
                        if (isOnlineUser) {
                            var target = e.target;
                            if ($el.find(".icon_pc").length > 0) {
                                layer.msg('电脑端不能呼叫');
                                return;
                            }
                            if ($(target).hasClass("icon_location")) {
                                UI.showPeopleLocationFromPC1($.extend({ depId: _this.dept.depId }, item)); //地图定位
                                return;
                            }
                            if ($el.hasClass("checked")) {
                                $el.removeClass("checked")
                                _this.updateWillCallUsers('del', { imUserId: userId });
                            } else {
                                if (_this.methods.getObjLength(_this.willCallUsers) + _this.methods.getObjLength(_this.userLists) > _this.maxNum) {
                                    layer.msg('同时视频通讯人数不能超过' + (_this.maxNum + 1));
                                } else {
                                    $el.addClass("checked")
                                    _this.updateWillCallUsers('add', item);
                                }
                            }
                        }
                    } else {
                        if ($el.hasClass("open")) {
                            $el.removeClass("open").addClass("toclose");
                        } else if ($el.hasClass("toclose")) {
                            var node = item,
                                isSignin = _this.methods.getType(item.isSignin) === "Boolean" ? String(+item.isSignin) : String(item.isSignin);
                            node['$el'] = $el;
                            _this.dosearch(node, isSignin);
                        }
                    }
                }
            })



            //取消呼叫按钮
            $(".select_wrap .btns .btn_grey").unbind('click').on("click", function (e) {
                e.stopPropagation();
                $(".select_wrap").hide();
                _this.updateWillCallUsers('clear');
            })

            //确定呼叫按钮
            $(".select_wrap .btns .btn_blue").unbind('click').on("click", function (e) {
                e.stopPropagation();
                if (_this.methods.isNullObj(_this.willCallUsers)) {
                    layer.msg('没有选中要呼叫的人');
                } else {
                    var newUserArr = _this.methods.objToArray(_this.methods.objReduce(_this.userLists, _this.willCallUsers));
                    newUserArr.length > 0 && _this.callUsers(newUserArr);
                    _this.updateWillCallUsers('clear');
                }
            })
        },
        //更新将呼叫人员列表
        updateWillCallUsers: function (type, user) {
            var _this = this;

            switch (type) {
                case 'del':
                    var imUserId = user.imUserId;
                    $(".select_wrap .right_con .con ul li[imUserId='" + imUserId + "']").remove();
                    _this.willCallUsers[imUserId] && (delete _this.willCallUsers[imUserId])
                    break;

                case 'add':
                    var imUserId = user.imUserId;
                    var str = '<li imUserId="' + imUserId + '">\
                        <span class="text">'+ user.userName + '</span>\
                        <span class="icon_del"></span>\
                    </li>'
                    $(".select_wrap .right_con .con ul").append(str);
                    _this.willCallUsers[imUserId] = user;
                    //删除选中用户
                    $(".select_wrap .right_con .con ul li .icon_del").unbind("click").on("click", function (e) {
                        e.stopPropagation();
                        var user = {
                            imUserId: $(this).parent("li").attr("imUserId")
                        }
                        _this.updateWillCallUsers('del', user);
                    })
                    break;

                case 'clear':
                    $(".select_wrap .right_con .con ul").empty();
                    _this.willCallUsers = {};
                    break;

            }

        },
        //视频画面显示
        viewShow: function () {
            $(".picture_wrap").show();
            this.viewCreatEvent();
        },
        //视频画面操作事件
        viewCreatEvent: function () {
            var _this = this;

            //开关自己的扬声器
            $(".picture_wrap .main_view .operate i:eq(0)").unbind("click").on("click", function () {
                if (_this.curRole == 1 || _this.curRole == 2) {
                    if ($(this).hasClass("disable")) {
                        _this.liveAct.openPlayer();
                        $(this).removeClass("disable");
                    } else {
                        _this.liveAct.closePlayer();
                        $(this).addClass("disable");
                    }
                }
            });

            //开关自己的麦克风
            $(".picture_wrap .main_view .operate i:eq(1)").unbind("click").on("click", function () {
                if (_this.curRole == 1 || _this.curRole == 2) {
                    if ($(this).hasClass("disable")) {
                        _this.liveAct.openMic();
                        $(this).removeClass("disable");
                    } else {
                        _this.liveAct.closeMic();
                        $(this).addClass("disable");
                    }
                }
            });

            //开关自己的摄像头
            $(".picture_wrap .main_view .operate i:eq(2)").unbind("click").on("click", function () {
                if (_this.curRole == 1 || _this.curRole == 2) {
                    if ($(this).hasClass("disable")) {
                        _this.liveAct.openCamera();
                        $(this).removeClass("disable");
                        _this.liveAct.localRender.setIdentifer(_this.imUserId);
                    } else {
                        _this.liveAct.closeCamera();
                        $(this).addClass("disable");
                    }
                }
            });

            //关闭通讯
            $(".picture_wrap .main_view .operate i:eq(3)").unbind("click").on("click", function () {
                if (_this.curRole == 1) {
                    layer.confirm('你确定要关闭视频通讯吗？', {
                        btn: ['确定', '取消']
                    }, function (index) {
                        layer.close(index);
                        _this.closeVideo('exitLive');
                    }, function () {
                        console.log("你点击了取消！");
                    });

                }
                if (_this.curRole == 2) {
                    layer.confirm('你确定要退出视频通讯吗？', {
                        btn: ['确定', '取消']
                    }, function (index) {
                        layer.close(index);
                        _this.closeVideo("quitRoom", { userid: $(".picture_wrap .other_view ul li:eq(0)").attr("userId") });
                    }, function () {
                        console.log("你点击了取消！");
                    });
                }
            });

            //全部静音
            $(".picture_wrap .top_btns .all_mute_btn").unbind("click").on("click", function () {
                if (_this.curRole == 1) {
                    if ($(this).hasClass("disable")) {
                        var $el = $(this),
                            msg = {
                                'type': 'openMic',
                                'roomid': _this.liveAct.roomnum,
                                'name': _this.userName,
                                'userid': _this.imUserId
                            }
                        _this.liveAct.sendGroupMessage(msg, function () {
                            _this.liveAct.openMic();
                            $(".picture_wrap .main_view .operate i:eq(1)").removeClass("disable");
                            $el.removeClass("disable");
                        });
                    } else {
                        var $el = $(this),
                            msg = {
                                'type': 'closeMic',
                                'roomid': _this.liveAct.roomnum,
                                'name': _this.userName,
                                'userid': _this.imUserId
                            }
                        _this.liveAct.sendGroupMessage(msg, function () {
                            _this.liveAct.closeMic();
                            $(".picture_wrap .main_view .operate i:eq(1)").addClass("disable");
                            $el.addClass("disable");
                        });
                    }
                }
            });

            //其他渲染器静音开关操作
            $(".picture_wrap .other_view ul li .mic").unbind("click").on("click", function (e) {
                e.stopPropagation();
                var index = $(this).parents("li").index(),
                    userId = $(this).parents("li").attr("userId"),
                    $el = $(this);
                if (_this.curRole == 1) {
                    if ($(this).hasClass("disable")) {
                        var msg = {
                            'type': 'openMic',
                            'roomid': _this.liveAct.roomnum,
                            'name': _this.userName,
                            'userid': _this.imUserId
                        }
                        _this.liveAct.sendC2CMessage(msg, userId, function (res) {
                            $el.removeClass("disable");
                        }, function (err) {
                        });
                    } else {
                        var msg = {
                            'type': 'closeMic',
                            'roomid': _this.liveAct.roomnum,
                            'name': _this.userName,
                            'userid': _this.imUserId
                        }
                        _this.liveAct.sendC2CMessage(msg, userId, function (res) {
                            $el.addClass("disable");
                        }, function (err) {
                        });
                    }
                }
            });

            //其他渲染器摄像头开关操作
            $(".picture_wrap .other_view ul li .camera").unbind("click").on("click", function (e) {
                e.stopPropagation();
                var index = $(this).parents("li").index(),
                    userId = $(this).parents("li").attr("userId"),
                    $el = $(this);
                if (_this.curRole == 1) {
                    if ($(this).hasClass("disable")) {
                        var msg = {
                            'type': 'openCamera',
                            'roomid': _this.liveAct.roomnum,
                            'name': _this.userName,
                            'userid': _this.imUserId
                        }
                        _this.liveAct.sendC2CMessage(msg, userId, function (res) {
                            $el.removeClass("disable");
                        }, function (err) {
                        });
                    } else {
                        var msg = {
                            'type': 'closeCamera',
                            'roomid': _this.liveAct.roomnum,
                            'name': _this.userName,
                            'userid': _this.imUserId
                        }
                        _this.liveAct.sendC2CMessage(msg, userId, function (res) {
                            $el.addClass("disable");
                        }, function (err) {
                        });
                    }
                }
            });

            //其他渲染器退出房间
            $(".picture_wrap .other_view ul li .toclose").unbind("click").on("click", function (e) {
                e.stopPropagation();
                var userId = $(this).parent("li").attr("userId");
                if (_this.curRole == 1) {
                    var msg = {
                        'type': 'quitRoom',
                        'roomid': _this.liveAct.roomnum,
                        'name': _this.userName,
                        'userid': _this.imUserId
                    }
                    _this.liveAct.sendC2CMessage(msg, userId);
                }
            });
        },
        //呼叫用户
        callUsers: function (userArr) {
            $(".select_wrap").hide();
            this.viewShow();
            $(".picture_wrap .other_view").mCustomScrollbar({
                theme: "minimal-dark"
            });
            var _this = this,
                len = userArr.length;
            if (len > 0) {
                var i = 0,
                    cb = function (index, userArr) {
                        index++;
                        if (userArr[index]) {
                            _this.inviteJoinRoom(userArr[index], function () {
                                cb(index, userArr);
                            });
                        }
                    };
                _this.inviteJoinRoom(userArr[i], function () {
                    cb(i, userArr);
                });
            }
        },
        //邀请加入房间
        inviteJoinRoom: function (user, cb) {
            var _this = this,
                fromOpt = {
                    roomnum: null,
                    userName: this.userName,
                    userId: this.imUserId
                },
                action = function (user, fromOpt) {
                    var userId = user.imUserId || user.userId,
                        userName = user.userName;
                    this.liveAct.inviteAppJoinRoom(userId, fromOpt, function () {
                    }.bind(this), function () {
                        layer.alert('呼叫"' + userName + '"失败', { icon: 5 });
                    });
                };
            user.messageId && (fromOpt['messageId'] = user.messageId);

            if (!this.liveAct.roomnum) {
                this.liveAct.createRoom(function (roomNum) {

                    _this.curRole = 1;
                    _this.setMainView({ "userId": _this.imUserId, "userName": _this.userName })//把自己渲染到主画面
                    var initmsg = {
                        'type': 'exitLive',
                        'roomid': roomNum,
                        'name': _this.userName,
                        'userid': _this.imUserId
                    };
                    _this.liveAct.sendGroupMessage(initmsg, function () {
                        _this.joinRoomUser(roomNum, { "userId": _this.imUserId, "userName": _this.userName });
                        fromOpt['roomnum'] = roomNum;
                        action.apply(_this, [user, fromOpt]);
                        cb && typeof cb === "function" && cb();
                    });
                }.bind(this), function (e) {
                    layer.alert(e, { icon: 5 });
                });
            } else {
                fromOpt['roomnum'] = this.liveAct.roomnum;
                action.apply(this, [user, fromOpt]);
                cb && typeof cb === "function" && cb();
            }
        },
        //渲染主画面
        setMainView: function (user) {
            this.liveAct.localRender.setIdentifer(user.imUserId || user.userId);
            if (this.curRole == 1) {
                var msg = {
                    'type': 'mainrender',
                    'name': user.userName,
                    'userid': user.imUserId || user.userId,
                    'roomid': this.liveAct.roomnum
                };
                this.liveAct.sendGroupMessage(msg);
            }
        },
        //渲染其他画面
        setOtherView: function (user) {
            var _this = this,
                userId = user.imUserId || user.userId,
                hasRender = false;
            $(".picture_wrap .other_view ul li").each(function () {
                var viewUserId = $(this).attr("userId");
                if (userId == viewUserId && _this.userLists[userId] && _this.userLists[userId].renderIndex) {
                    $(this).show();
                    _this.liveAct.renders[_this.userLists[userId].renderIndex].setIdentifer(userId);
                    hasRender = true;
                }
            });
            if (!hasRender) {
                //console.log("渲染画面用户：" + JSON.stringify(user));
                var len = this.liveAct.renders.length,
                    index = null;
                for (var i = 0; i < len; i++) {
                    if (this.liveAct.renders[i].isFreeRender()) {
                        index = i;
                        break;
                    }
                }
                if (index || index === 0) {
                    this.liveAct.renders[index].setIdentifer(userId);
                    if (!this.userLists[userId]) this.userLists[userId] = user;
                    this.userLists[userId]['renderIndex'] = index;
                    $(".picture_wrap .other_view ul li").eq(index).attr("userId", userId);
                    $(".picture_wrap .other_view ul li").eq(index).find(".name").html(user.userName);
                    $(".picture_wrap .other_view ul li").eq(index).show();
                }
            }
        },
        //房间人员加入
        joinRoomUser: function (roomid, user, cb) {
            user.userId = user.imUserId || user.userId
            var todata = $.extend({}, { roomId: roomid }, user);
            $.ajax({
                url: "//" + CONFIG.domain + "/zhzlbackend/webIm/webIm/joinRoomUser",
                type: "get",
                data: todata,
                success: function (res) {
                    if (res.success) {
                        var data = res.data;
                        cb && typeof cb === "function" && cb(data);
                    } else {
                        //console.log(res);
                    }
                },
                error: function (err) {
                    //console.log(err);
                }
            })
        },
        //房间人员删除
        outRoomUser: function (roomid, user, cb) {
            $.ajax({
                url: "//" + CONFIG.domain + "/zhzlbackend/webIm/webIm/outRoomUser",
                type: "get",
                data: {
                    roomId: roomid,
                    userId: user.imUserId || user.userId
                },
                success: function (res) {
                    if (res.success) {
                        var data = res.data;
                        cb && typeof cb === "function" && cb(data);
                    } else {
                        //console.log(res);
                    }
                },
                error: function (err) {
                    //console.log(err);
                }
            })
        },
        //获取房间里的人员信息
        getRoomUsers: function (roomid, cb) {
            $.ajax({
                url: "//" + CONFIG.domain + "/zhzlbackend/webIm/webIm/getRoomUser",
                type: "get",
                data: {
                    roomid: roomid
                },
                success: function (res) {
                    if (res.success) {
                        var data = res.data;
                        cb && typeof cb === "function" && cb(data);
                    } else {
                        //console.log(res);
                    }
                },
                error: function (err) {
                    //console.log(err);
                }
            })
        },
        //更新已呼列表中的用户
        updateUserLists: function (user, type) {
            var userId = user.userId;
            if (type === 'del') {
                this.userLists[userId] && delete this.userLists[userId];
                $(".picture_wrap .other_view ul li[userId='" + userId + "']").hide();
                $(".picture_wrap .other_view ul li[userId='" + userId + "'] .name").empty();
                $(".picture_wrap .top_btns span").html('共' + this.methods.getObjLength(this.userLists) + '人参会');
                if (this.curRole == 1) {
                    var msg = {
                        'type': 'removeuser',
                        'name': user.userName,
                        'userid': userId,
                        'roomid': this.liveAct.roomnum
                    };
                    this.liveAct.sendGroupMessage(msg);
                }
            }
            if (type === 'add') {
                this.userLists[userId] = user;
                $(".picture_wrap .top_btns span").html('共' + this.methods.getObjLength(this.userLists) + '人参会');
                if (this.curRole == 1) {
                    var msg = {
                        'type': 'adduser',
                        'name': user.userName,
                        'userid': userId,
                        'roomid': this.liveAct.roomnum
                    };
                    this.liveAct.sendGroupMessage(msg);
                }
            }
        },
        //监听消息处理
        msgListenerAction: function () {
            this.liveAct.setC2CListener(function (res) {
                var res = JSON.parse(res);
                //console.log(res)
                if (res.type) {
                    var type = res.type,
                        _this = this;

                    /* 作为角色为0(无角色)时的监听 start*/
                    if (this.curRole == 0) {

                        //来自APP呼叫(让其加入)
                        if (type == "call" && res.messageId) {
                            res['userId'] = res.userid;
                            res['userName'] = res.name;
                            layer.confirm(res.name + '邀请你进入视频通讯，是否加入？', {
                                btn: ['加入', '不加入']
                            }, function (index) {
                                layer.close(index);
                                var userArr = [res];
                                _this.callUsers(userArr);
                            }, function () {
                                var msg = {
                                    'type': 'res',
                                    'sta': '0',
                                    'name': _this.userName,
                                    'userid': _this.imUserId,
                                    'messageId': res.messageId
                                }
                                _this.liveAct.sendC2CMessage(msg, res.userid);
                                console.log("你拒绝了" + res.nam + "的视频通讯邀请！");
                            });

                        }

                        //来自PC呼叫(加入)
                        if (type == "call" && !res.messageId) {

                            layer.confirm(res.name + '邀请你进入视频通讯，是否加入？', {
                                btn: ['加入', '不加入']
                            }, function (index) {
                                layer.close(index);
                                _this.liveAct.joinRoom(res.roomid, function () {
                                    _this.curRole = 2;
                                    $(".select_wrap").hide();
                                    _this.viewShow();
                                    $(".picture_wrap").addClass('disable');
                                    $(".picture_wrap .all_mute_btn").addClass('disable');
                                    $(".search_wrap").addClass('disable');
                                    $(".picture_wrap .other_view").mCustomScrollbar({
                                        theme: "minimal-dark"
                                    });
                                    var msg = {
                                        'type': 'res',
                                        'sta': '1',
                                        'name': _this.userName,
                                        'userid': _this.imUserId
                                    }
                                    _this.liveAct.sendC2CMessage(msg, res.userid);
                                }, function (err) {
                                    //console.log(err)
                                })
                            }, function () {
                                var msg = {
                                    'type': 'res',
                                    'sta': '0',
                                    'name': _this.userName,
                                    'userid': _this.imUserId
                                }
                                _this.liveAct.sendC2CMessage(msg, res.userid);
                                console.log("你拒绝了" + res.nam + "的视频通讯邀请！");
                            });
                        }

                    }
                    /* 作为角色为0(无角色)时的监听 end*/

                    /* 作为发起者的监听 start*/
                    if (this.curRole == 1) {
                        //邀请加入房间监听
                        if (type == "res" && res.sta == 1) {
                            var fromOpt = {
                                roomnum: this.liveAct.roomnum,
                                userName: this.userName,
                                userId: this.imUserId
                            }
                            this.liveAct.interact(res.userid, fromOpt, function () {
                            }, function () {
                                layer.alert('呼叫"' + userName + '"失败', { icon: 5 });
                            });
                        }

                        //与APP || PC通话上麦监听
                        if (type == "interact" && res.sta == 1) {
                            res['userId'] = res.userid;
                            res['userName'] = res.name;
                            this.joinRoomUser(this.liveAct.roomnum, res);
                            this.updateUserLists(res, 'add');
                            this.setOtherView(res);
                        }

                        // APP || PC退出房间(含被踢下线)
                        if ((type == "exitLive" && res.roomid == this.liveAct.roomnum) || type == "quitRoom") {
                            res['userId'] = res.userid;
                            res['userName'] = res.name;
                            this.outRoomUser(res.roomid, res);
                            this.updateUserLists(res, 'del')
                        }

                        //被app呼叫
                        if (type == "call" && res.messageId) {
                            if (this.methods.getObjLength(this.userLists) < this.maxNum) {  //人员未满可以加
                                res['userId'] = res.userid;
                                res['userName'] = res.name;
                                var userArr = [res];
                                this.callUsers(userArr);

                            } else {
                                var msg = {
                                    'type': 'res',
                                    'sta': '0',
                                    'name': _this.userName,
                                    'userid': _this.imUserId,
                                    'messageId': res.messageId
                                }
                                _this.liveAct.sendC2CMessage(msg, res.userid);
                                console.log("因人数已满你拒绝了" + res.nam + "的视频通讯邀请！");
                            }
                        }

                        //被pc呼叫，因在视频中直接拒绝
                        if (type == "call" && !res.messageId) {
                            var msg = {
                                'type': 'res',
                                'sta': '0',
                                'name': _this.userName,
                                'userid': _this.imUserId
                            }
                            _this.liveAct.sendC2CMessage(msg, res.userid);
                            console.log("因在视频中，你拒绝了PC端" + res.nam + "的视频通讯邀请！");
                        }

                    }
                    /* 作为发起者的监听 end*/


                    /* 作为接受者角色为2的监听 start*/
                    if (this.curRole == 2) {

                        // 与PC通话上麦监听
                        if (type == "invite" && !res.sta && res.roomid == this.liveAct.roomnum) {
                            var msg = {
                                'type': 'interact',
                                'sta': '1',
                                'name': _this.userName,
                                'userid': _this.imUserId,
                                'FM': 'PC'
                            }
                            _this.liveAct.sendC2CMessage(msg, res.userid, function () {
                                res['userId'] = res.userid;
                                res['userName'] = res.name;
                                _this.updateUserLists(res, 'add');
                                _this.setOtherView(res); //渲染主叫人
                                _this.setMainView({ userId: _this.imUserId }); //渲染自己

                                _this.getRoomUsers(res.roomid, function (users) {
                                    users.map(function (user) {
                                        _this.updateUserLists(user, 'add');
                                        _this.setOtherView(user);

                                    })
                                });
                            });
                        }

                        // 退出房间(含被踢下线)
                        if ((type == "exitLive" || type == "quitRoom" || type == "stopInteract") && res.roomid == this.liveAct.roomnum) {
                            res['userId'] = res.userid;
                            res['userName'] = res.name;
                            var content = type == "exitLive" ? res.name + "解散了即时通讯" : "你被" + res.name + "中断了即时通讯";
                            layer.open({
                                title: '提示'
                                , content: content
                                , closeBtn: 0
                                , yes: function (index) {
                                    layer.close(index)
                                    _this.closeVideo("quitRoom", res);
                                }
                                , success: function (layerDom, index) {
                                    setTimeout(function () {
                                        layer.close(index)
                                        _this.closeVideo("quitRoom", res);
                                    }, 3000)
                                }
                            });
                        }

                        // PC广播房间新增上麦用户 || 下麦或退出房间用户
                        if (type == 'adduser' || type == 'removeuser') {
                            this.getRoomUsers(res.roomid, function (users) {
                                var willadd = [],
                                    willdelete = [],
                                    usersObj = this.methods.arrayToObj(users, 'userId');
                                users.map(function (user) {
                                    !_this.userLists[user.userId] && willadd.push(user)
                                });
                                willdelete = this.methods.objToArray(this.methods.objReduce(users, this.userLists));

                                willdelete.map(function (user) {
                                    _this.updateUserLists(user, 'del')
                                });

                                willadd.map(function (user) {
                                    _this.updateUserLists(user, 'add');
                                    _this.setOtherView(user);
                                })
                            });
                        }

                        // 麦克风/摄像头设备操作
                        if (type == 'closeMic' || type == 'openMic' || type == 'closeCamera' || type == 'openCamera') {
                            this.deviceAction(type, this.userInfo);
                        }

                    }
                    /* 作为接受者角色为2的监听 end*/

                }
            }.bind(this));
        },
        //监听（异常，房间等事件）
        processListener: function (obj) {
            var type = obj.type;

            //其它地方登录或被迫下线
            if (type == "offline" || type == "sdkLoginoutRoom") {
                layer.alert(obj.msg + "（你需要刷新或重开浏览器）", { icon: 5 });
                var msg = {
                    'type': 'exitLive',
                    'roomid': this.liveAct.roomnum,
                    'name': this.userName,
                    'userid': this.imUserId
                };
                this.liveAct.sendGroupMessage(msg);
            }

            //房间(设备)事件
            if (type == "roomevent" && this.curRole == 1) {
                var eventid = obj.msg.eventid,
                    userId = obj.msg.identifier,
                    type = null;
                type = eventid == E_iLiveRoomEventType.HAS_CAMERA_VIDEO ? "openCamera" : type;
                type = eventid == E_iLiveRoomEventType.NO_CAMERA_VIDEO ? "closeCamera" : type;
                type = eventid == E_iLiveOperType.Open_Mic ? "openMic" : type;
                type = eventid == E_iLiveOperType.Close_Mic ? "closeMic" : type;
                type = eventid == E_iLiveOperType.Open_Player ? "openPlayer" : type;
                type = eventid == E_iLiveOperType.Close_Player ? "closePlayer" : type;
                type && this.deviceAction(type, { userId: userId });
            }
        },
        //设备操作
        deviceAction: function (type, user) {
            var userId = user.imUserId || user.userId;
            _this = this;
            if (userId == this.imUserId) {
                this.liveAct[type]();
            }
            switch (type) {
                case 'closeMic':
                    userId == this.imUserId && $(".picture_wrap .main_view .operate i:eq(1)").addClass("disable");
                    $(".picture_wrap .other_view ul li").each(function () {
                        var viewUserId = $(this).attr("userId");
                        if (userId == viewUserId) {
                            $(this).find(".mic").addClass("disable");
                        }
                    });
                    break;

                case 'openMic':
                    userId == this.imUserId && $(".picture_wrap .main_view .operate i:eq(1)").removeClass("disable");
                    $(".picture_wrap .other_view ul li").each(function () {
                        var viewUserId = $(this).attr("userId");
                        if (userId == viewUserId) {
                            $(this).find(".mic").removeClass("disable");
                        }
                    });
                    break;

                case 'closeCamera':
                    userId == this.imUserId && $(".picture_wrap .main_view .operate i:eq(2)").addClass("disable");
                    $(".picture_wrap .other_view ul li").each(function () {
                        var viewUserId = $(this).attr("userId");
                        if (userId == viewUserId) {
                            $(this).find(".camera").addClass("disable");
                        }
                    });
                    break;

                case 'openCamera':
                    if (userId == this.imUserId) {
                        $(".picture_wrap .main_view .operate i:eq(2)").removeClass("disable");
                        _this.liveAct.localRender.setIdentifer(userId);
                    }
                    $(".picture_wrap .other_view ul li").each(function () {
                        var viewUserId = $(this).attr("userId"),
                            index = $(this).index();
                        if (userId == viewUserId) {
                            $(this).find(".camera").removeClass("disable");
                            if (!_this.userLists[userId]) _this.userLists[userId] = user;
                            if (_this.userLists[userId].renderIndex) {
                                _this.liveAct.renders[_this.userLists[userId].renderIndex].setIdentifer(userId);
                            } else if (_this.userLists[userId]) {
                                _this.liveAct.renders[index].setIdentifer(userId);
                                _this.userLists[userId]['renderIndex'] = index;
                            }
                        }
                    });
                    break;

                case 'closePlayer':
                    userId == this.imUserId && $(".picture_wrap .main_view .operate i:eq(0)").addClass("disable");
                    break;

                case 'openPlayer':
                    userId == this.imUserId && $(".picture_wrap .main_view .operate i:eq(0)").removeClass("disable");
                    break;
            }
        },
        //解散或退出房间
        closeVideo: function (type, user) {
            if (type == "quitRoom") {
                this.liveAct.quitRoom(function () {
                    var msg = {
                        'type': 'quitRoom',
                        'sta': '1',
                        'name': this.userName,
                        'userid': this.imUserId
                    };
                    this.liveAct.sendC2CMessage(msg, user.userid)
                    this.recoverInit();
                }.bind(this), function (err) {
                    //console.log(err)
                })
            }
            if (type == "exitLive") {
                var fromOpt = {
                    roomnum: this.liveAct.roomnum,
                    userName: this.userName,
                    userId: this.imUserId
                };
                this.liveAct.exitLive(fromOpt, function () {
                    this.recoverInit();
                }.bind(this), function (err) {
                    //console.log(err)
                });
            }
        },
        //curRole角色、HTML等还原到初始化状态
        recoverInit: function () {
            this.curRole = 0;
            this.userLists = {};
            $(".picture_wrap").hide();
            $(".picture_wrap").removeClass('disable');
            $(".picture_wrap .all_mute_btn").removeClass("disable");
            $(".picture_wrap .top_btns span").html("共xx人参会");
            $(".picture_wrap .other_view ul li").hide();
            $(".search_wrap").removeClass("disable");
        },
        //公用方法集合
        methods: {

            //获取Obj类型
            getType: function (obj) {
                return Object.prototype.toString.call(obj).split(" ")[1].slice(0, -1);
            },

            //判断对象是否为空
            isNullObj: function (obj) {
                for (var i in obj) {
                    if (obj.hasOwnProperty(i)) {
                        return false;
                    }
                }
                return true;
            },

            //获得对象的长度
            getObjLength: function (obj) {
                var len = 0;
                for (var i in obj) {
                    if (obj.hasOwnProperty(i)) {
                        len++;
                    }
                }
                return len;
            },

            //深度复制
            deepClone: function (obj) {
                return this.isNullObj(obj) ? obj : JSON.parse(JSON.stringify(obj));
            },

            //对象转成数组
            objToArray: function (obj) {
                var arr = [];
                for (var i in obj) {
                    if (obj.hasOwnProperty(i)) {
                        arr.push(obj[i]);
                    }
                }
                return arr;
            },

            //数组转成对象
            arrayToObj: function (arr, key) {
                var obj = {};
                arr.map(function (item) {
                    obj[key] = item
                })
                return obj;
            },

            //对象的扩展(合集)
            objExtend: function (target, obj) {
                return $.extend({}, target, obj);
            },

            //对象的扩展(交集)
            objRepeated: function (target, obj) {
                var newObj = {}
                for (var i in obj) {
                    if (obj.hasOwnProperty(i) && target.hasOwnProperty(i)) {
                        newObj[i] = obj[i];
                    }
                }
                return newObj;
            },

            //对象的扩展(差集)
            objReduce: function (target, obj) {
                var newObj = {}
                for (var i in obj) {
                    if (obj.hasOwnProperty(i) && !target.hasOwnProperty(i)) {
                        newObj[i] = obj[i];
                    }
                }
                return newObj;
            },

            //处理departmentName(所属辖区字段)
            getDepartmentName: function (departmentName) {
                return !!~departmentName.indexOf("/") ? departmentName.split("/")[departmentName.split("/").length - 1] : departmentName
            }
        },



    });
});