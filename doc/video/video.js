var axios = require('axios');
var sha = require('sha1');

const ip="http://111.19.144.40";
const prot=":8089";
const server = ip+prot;


// ceshi01 账号 ceshi001 密码 Nucleus!
const zhenYouUserName = "504shq";
const zhenYouPassword = "Nucleus!";
const imei = zhenYouUserName;

let loginParam = {
	"userName": zhenYouUserName,
	"password": zhenYouPassword,
	"imei": imei
};
console.log('登录请求参数：', loginParam);
console.log('sha: ', sha('test'))
console.log(random(10, {letters:false}));
console.log(new Date().getTime());

//axios.post({url: server + "/nuas/api/v1/sessions", loginParam},
//	function(error, response, body) {
//    console.log(error,response,body)
//})

let map = formHeaderParams();
console.log(typeof map);
console.log(strMapToJson(map));

/**
 * 组装请求头参数
 *
 * @param mediaType MediaType
 * @returns {Map<any, any>}
 */
function formHeaderParams(mediaType) {
	const formMediaType = mediaType !== undefined ? (mediaType.replace(/\s*/g,"") !== '' ? mediaType : "application/json;charset=UTF-8") : "application/json;charset=UTF-8";
	const apiKey = "gns11529c136998cb6";
	let headerMap = new Map();
	// 生成随机数
    const nonce = random(10, {letters:false});
	//获取时间戳
	const timestamp = new Date().getTime();
	//数组排序
	const arr = [apiKey, timestamp, nonce].sort();
	console.log('arr after: ', arr);
	let codeSort = '';
	for(let v of arr) {
		codeSort += v;
	}
	console.log('mediaType: ', mediaType);
	// SHA-1加密生成signature
    const signature = sha(codeSort);
	console.log('signature: ', signature);
	//请求头参数
	headerMap.set('X-Signature', signature)
		.set('X-Timestamp', timestamp)
		.set('X-Nonce', nonce)
		.set('Accept', formMediaType);
	console.log('header params: ', headerMap);
	return headerMap;
}

//以下为封装的工具方法
function strMapToObj(strMap) {
  let obj = Object.create(null);
  for (let [k,v] of strMap) {
    obj[k] = v;
  }
  return obj;
}

function strMapToJson(strMap) {
  return JSON.stringify(strMapToObj(strMap));
}

/**
 * Generate random string
 * @param {Number} length
 * @param {Object} options
 */
function random(length, options) {
  var numbers = '0123456789';
  var letters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
  var specials = '~!@#$%^*()_+-=[]{}|;:,./<>?';
  length || (length = 8);
  options || (options = {});

  var chars = '';
  var result = '';
  if (options === true) {
    chars = numbers + letters + specials;
  } else if (typeof options == 'string') {
    chars = options;
  } else {
    if (options.numbers !== false) {
      chars += (typeof options.numbers == 'string') ? options.numbers : numbers;
    }

    if (options.letters !== false) {
      chars += (typeof options.letters == 'string') ? options.letters : letters;
    }

    if (options.specials) {
      chars += (typeof options.specials == 'string') ? options.specials : specials;
    }
  }
  while (length > 0) {
    length--;
    result += chars[Math.floor(Math.random() * chars.length)];
  }
  return result;
}
