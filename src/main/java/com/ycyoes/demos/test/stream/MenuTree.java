package com.ycyoes.demos.test.stream;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MenuTree {
    public Integer id;
    public String name; //名称
    public Integer parentId;    //父id,根节点为0
    public List<MenuTree> childList;

    public MenuTree(Integer id, String name, Integer parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public MenuTree(Integer id, String name, Integer parentId, List<MenuTree> childList) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.childList = childList;
    }
}
