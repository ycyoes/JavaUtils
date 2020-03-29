package com.ycyoes.demos.geetime.algorithm.chapter6;

/**
 * 回文串判定
 * 1）单链表的插入、删除、查找操作；
 * 2）链表中存储的是int类型的数据；
 *
 * @author ycyoes
 * @create 2020-03-28 18:52
 *
 */
public class SinglyLinkedList {

    private Node head = null;

    /**
     * 找到与给定值相等的节点
     * @param value
     * @return
     */
    public Node findByValue(int value) {
        Node p = head;
        while (p != null && p.data != value) {
            p = p.next;
        }
        return p;
    }

    /**
     * 找到index对应的节点
     * @param index
     * @return
     */
    public Node findByIndex(int index) {
        Node p = head;
        int pos = 0;
        while (p != null && pos != index) {
            p = p.next;
            ++pos;
        }
        return p;
    }

    /**
     * 无头节点
     * 表头部插入
     * 这种操作将与输入的顺序相反，逆序
     * @param value
     */
    public void insertToHead(int value) {
        Node newNode = new Node(value, null);
        insertToHead(newNode);
    }

    public void insertToHead(Node newNode) {
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    /**
     * 顺序插入
     * 链表尾部插入
     * @param value
     */
    public void insertTail(int value) {
        Node newNode = new Node(value, null);
        //空链表，可以插入新节点作为head，也可以不操作
        if (head == null) {
            head = newNode;
        } else {
            Node q = head;
            //找到倒数第二个节点
            while (q.next != null) {
                q = q.next;
            }
            //将新节点的next设为空，即倒数第二个节点的next
            newNode.next = q.next;
            //将倒数第二个节点的next设为新节点
            q.next = newNode;
        }
    }

    public void insertAfter(Node p, int val) {
        Node newNode = new Node(val, null);
        insertAfter(p, newNode);
    }

    public void insertAfter(Node p, Node newNode) {
        if (p == null) return;
        newNode.next = p.next;
        p.next = newNode;
    }

    public void insertBefore(Node p, int val) {
        Node newNode = new Node(val, null);
        insertBefore(p, newNode);
    }

    /**
     * 在指定节点前插入新的节点
     * @param p
     * @param newNode
     */
    public void insertBefore(Node p, Node newNode) {
        if (p == null) return;
        if (head == p) {
            insertToHead(newNode);
            return;
        }

        Node q = head;
        //找到p的前一个节点
        while (q != null && q.next != p) {
            q = q.next;
        }

        if (q == null) {
            return;
        }

        //将新节点插入p节点前
        newNode.next = p;
        q.next = newNode;
    }

    public void deleteByNode(Node p) {
        if (p == null || head == null) return;

        if (p == head) {
            head = head.next;
            return;
        }

        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }

        q.next = p.next;
    }

    public void deleteByValue(int val) {
        if (head == null) return;

        Node p = head;
        Node q = null;
        //找到指定值的前一个节点
        while (p != null && p.data != val) {
            q = p;
            p = p.next;
        }

        //未找到与指定值相等的节点
        if (p == null) return;

        //首节点值与指定值相等，原首节点的next设为新的首节点
        if (q == null) {
            head = head.next;
        //删除指定节点
        } else {
            q.next = q.next.next;
        }
    }

    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    //判断true or false
    public boolean TFResult(Node left, Node right) {
        Node l = left;
        Node r = right;

        boolean flag = true;
        System.out.println("left_:" + l.data);
        System.out.println("right_:" + r.data);
        while (l != null && r != null) {
            if (l.data == r.data) {
                l = l.next;
                r = r.next;
                continue;
            } else {
                flag = false;
                break;
            }

        }
        System.out.println("什么结果");
        return flag;
    }

    //判断是否为回文
    public boolean palindrome() {
        if (head == null) {
            return false;
        } else {
            System.out.println("开始执行找到中间节点");
            Node p = head;
            Node q = head;
            if (p.next == null) {
                System.out.println("只有一个元素");
                return true;
            }

            //使用快慢指针找到中间节点
            while (q.next != null && q.next.next != null) {
                p = p.next;
                q = q.next.next;
            }
            System.out.println("中间节点: " + p.data);
            System.out.println("开始执行奇数节点的回文判断");
            Node leftLink = null;
            Node rightLink = null;
            if (q.next == null) {
                //p一定为整个链表的中点，且节点数目为奇数
                rightLink = p.next;
                leftLink = inverseLinkList(p).next;
                System.out.println("左边第一个节点: " + leftLink.data);
                System.out.println("右边第一个节点: " + rightLink.data);
            } else {
                //p q均为中点
                rightLink = p.next;
                leftLink = inverseLinkList(p);
            }
            return TFResult(leftLink, rightLink);
        }
    }

    //带头节点的链表反转
    public Node inverseLinkList_head(Node p) {
        //Head为新建的一个头节点
        Node Head = new Node(9999, null);
        //p为原来整个链表的头节点，现在Head指向整个链表
        Head.next = p;
        /**
         * 带头节点带链表翻转等价于从第二个元素开始重新头插法建立链表
         */
        Node cur = p.next;
        p.next = null;
        Node next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = Head.next;
            Head.next = cur;
            System.out.println("first: " + Head.data);
            cur = next;
        }

        //返回左半部分的中点之前的那个节点，从此处开始同步向两边比较
        return Head;
    }

    //无头节点的链表反转
    public Node inverseLinkList(Node p) {
        Node pre = null;
        Node r = head;
        System.out.println("z: " + r.data);
        Node next = null;
        while (r != p) {
            next = r.next;
            r.next = pre;   //链表指针反向
            pre = r;
            r = next;
        }
        r.next = pre;
        //返回左半部分的中点之前的那个节点
        //从此处开始同步向两边比较
        return r;
    }

    public static Node createNode(int val) {
        return new Node(val, null);
    }

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList link = new SinglyLinkedList();
        System.out.println("hello");
        int data[] = {1,2,5,2,1};
        for(int i = 0; i < data.length; i++) {
            link.insertTail(data[i]);
        }
        System.out.println("打印原始: ");
        link.printAll();
        if (link.palindrome()) {
            System.out.println("回文");
        } else {
            System.out.println("不是回文");
        }
    }
}
