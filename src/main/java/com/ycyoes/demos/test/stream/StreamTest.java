package com.ycyoes.demos.test.stream;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class StreamTest {

    @Test
    public void testTree() {
        List<MenuTree> menus = Arrays.asList(
                new MenuTree(1, "根节点", 0),
                new MenuTree(2, "子节点1", 1),
                new MenuTree(3, "子节点1.1", 2),
                new MenuTree(4, "子节点1.2", 2),
                new MenuTree(5, "子节点1.3", 2),
                new MenuTree(6, "根节点2", 1),
                new MenuTree(7, "根节点2.1", 6),
                new MenuTree(8, "根节点2.2", 6),
                new MenuTree(9, "根节点2.2.1", 7),
                new MenuTree(10, "根节点2.2.2", 7),
                new MenuTree(11, "根节点3", 1),
                new MenuTree(12, "子节点3.1", 11)
        );

        //获取父节点
        List<MenuTree> collect = menus.stream().filter(m -> m.getParentId() == 0)
                .map(
                        (m) -> {
                            m.setChildList(getChildrens(m, menus));
                            return m;
                        }
                ).collect(Collectors.toList());

        System.out.println("-------------convert to json-----------");
        System.out.println(JSON.toJSON(collect));
    }

    private List<MenuTree> getChildrens(MenuTree root, List<MenuTree> all) {
        List<MenuTree> children = all.stream().filter(
                m -> {
                    return Objects.equals(m.getParentId(), root.getId());
                }
        ).map(
                m -> {
                    m.setChildList(getChildrens(m, all));
                    return m;
                }
        ).collect(Collectors.toList());
        return children;
    }


}
