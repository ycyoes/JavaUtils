package com.ycyoes.demos.geetime.algorithm.chapter17;


import java.util.Random;

/**
 * 跳表的一种实现方法。
 * 跳表中存储的是正整数，并且存储的是不重复的。
 *
 * @author ycyoes
 * @date 2021-09-07
 */
public class SkipList2 {
    private static final int MAX_LEVEL = 16;
    private int levelCount = 1;

    //带头链表
    private Node head = new Node(MAX_LEVEL);
    private Random r = new Random();

    public Node find(int value) {
        Node p = head;
        //从最大层开始查找，找到前一节点，通过--i,移动到下层再开始查找
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                //找到前一节点
                p = p.forwards[i];
            }
        }

        //
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    public void insert(int value) {
        int level = head.forwards[0] == null ? 1 : randomLevel();
        //每次只增加一层，如果条件满足
        if (level > levelCount) {
            level = ++levelCount;
        }

        Node newNode = new Node(level);
        newNode.data = value;
        Node update[] = new Node[level];
        for (int i = 0; i < level; i++) {
            update[i] = head;
        }

        Node p = head;
        //从最大层开始查找，找到前一节点，通过--i,移动到下层再开始查找
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                //找到前一节点
                p = p.forwards[i];
            }

            //levelCount会 > level,所以加上判断
            if (level > i) {
                update[i] = p;
            }
        }

        for (int i = 0; i < level; i++) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }

    }

    public void insert2(int value) {
        int level = head.forwards[0] == null ? 1 : randomLevel();
        if (level > levelCount) level = ++levelCount;

        Node newNode = new Node(level);
        newNode.data = value;
        Node p = head;

        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }

            if (level > i) {
                if (p.forwards[i] == null) {
                    p.forwards[i] = newNode;
                } else {
                    Node next = p.forwards[i];
                    p.forwards[i] = newNode;
                    newNode.forwards[i] = next;
                }
            }
        }
    }

    public void delete(int value) {
        Node[] update = new Node[levelCount];
        Node p = head;
        //逐层查找前一节点
        for (int i = levelCount - 1; i >= 0; --i) {

            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }

            update[i] = p;
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; --i) {
                if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }
    }

    /**
     * 随机level次，如果是奇数层数+1，防止伪随机
     * @return
     */
    private int randomLevel() {
        int level = 1;
        for (int i = 0; i < MAX_LEVEL; i++) {
            if (r.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    /**
     * 打印所有数据
     */
    public void printAll_beautiful() {
        Node p = head;
        Node[] c = p.forwards;
        Node[] d = c;
        int maxLevel = c.length;
        for (int i = maxLevel - 1; i >= 0; i--) {
            do {
                System.out.print((d[i] != null ? d[i].data : null) + ":" + i + "-------");
            } while (d[i] != null && (d = d[i].forwards)[i] != null);
            System.out.println();
            d = c;
        }
    }


    //跳表的节点，每个节点记录了当前节点数据和所在层数数据
    public class Node {
        private int data = -1;
        /**
         * 表示当前节点位置的下一个节点所有层的数据，从上层切换到下层，就是数组下标-1，
         * forwards[3]表示当前节点在第三层的下一个节点。
         */
        private Node forwards[];

        //这个值其实可以不用
        private int maxLevel = 0;

        public Node(int level) {
            forwards = new Node[level];
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");

            return builder.toString();
        }
    }
}
