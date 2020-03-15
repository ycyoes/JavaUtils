package com.ycyoes.demos.algorithm.list;

public class ListTest {
	
	/**
	 * 判断链表是否有环 - 使用快慢指针，慢指针一次一个，快指针一次两个，如果有环一定会重合
	 * @param head
	 * @return boolean
	 */
	private static boolean cycle(Node head) {
		if(head == null){
	        return false;
	    }
		Node aNode = head;
		Node bNode = head;
	    while(bNode.next != null && bNode.next.next != null){
	        if(bNode.next.next == aNode.next){
	            return true;
	        }
	        bNode = bNode.next.next;
	        aNode = aNode.next;
	    }
	    return false;
	}
	
	private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

}
