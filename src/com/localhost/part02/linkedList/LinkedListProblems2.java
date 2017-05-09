package com.localhost.part02.linkedList;



public class LinkedListProblems2 {

	public static void main(String[] args) {

		// 创建测试结点
		ListNode headNode1 = null;
		ListNode headNode2 = null;
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		ListNode node7 = new ListNode(7);
		
		// 生成单链表
		headNode1 = node1;
		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node4);
		node4.setNext(node5);
		node5.setNext(node6);
		node6.setNext(node7);
		node7.setNext(node1);
		
		ListNode node = getJosephusPosition(headNode1,7, 3);
		System.out.println(node.getData());
	}

	
	/*
	 * 题目：如何逐对的逆置链表
	 * 假设初始链表为1->2->3->4->X，逐对逆置后2->1->4->3->X
	 * 思路：递归、迭代
	 */
	/**
	 * 逐对的逆置链表
	 * @param headNode 需要被逐对逆置的链表头结点
	 * @return 逐对逆置后新的链表
	 */
	public static ListNode reversePairRecursive(ListNode headNode) {
		ListNode tempNode = null;
		// 递归的基本情形为当前链表为空或者只有一个元素
		if (headNode == null || headNode.getNext() == null) {
			return headNode;
		} else {
			// 先逆置一对
			tempNode = headNode.getNext();
			headNode.setNext(tempNode.getNext());
			tempNode.setNext(headNode);
			// 更新链表头结点
			headNode = tempNode;
			// 链表剩余部分继续递归逆置
			headNode.getNext().setNext(reversePairRecursive(headNode.getNext().getNext()));
			return headNode;
		}
	} 
	
	/**
	 * 逐对的逆置链表
	 * @param headNode 需要被逐对逆置的链表头结点
	 * @return 逐对逆置后新的链表
	 */
	public static ListNode reversePairIterative(ListNode headNode) {
		ListNode tempNode = null;
		ListNode newHeadNode = null;
		while (headNode != null && headNode.getNext() != null) {
			// 如果成立，说明不是第一对结点
			if (tempNode != null) {
				tempNode.getNext().setNext(headNode.getNext());
			}
			// 逐对逆置结点
			tempNode = headNode.getNext();
			headNode.setNext(headNode.getNext().getNext());
			tempNode.setNext(headNode);
			// 如果是第一对结点，则更新表头
			if (newHeadNode == null) {
				newHeadNode = tempNode;
			}
			// 指向下一对结点
			headNode = headNode.getNext();
		}
		return newHeadNode;
	}
	
	/*
	 * 题目：把一个循环链表分割成两个长度相等的循环链表；如果链表的结点数为奇数，那么让第一个链表的结点数比第二个多一个。
	 * 思路：使用两个指针slowPtr和fastPtr从headNode同时移动,slowPtr一次移动一个结点，fastPtr一次移动两个结点；
	 * 如果循环链表的结点数为奇数，则fastPtr.getNext()将指向headNode
	 * 如果循环链表的结点数为偶数，则fastPtr.getNext().getNext()将指向headNode
	 */
	/**
	 * 把一个循环链表分割成两个长度相等的循环链表；如果链表的结点数为奇数，那么让第一个链表的结点数比第二个多一个。
	 * @param headNode 循环链表的头结点
	 * @return 分割后的第二个链表头结点
	 */
	public static ListNode splitList(ListNode headNode) {
		ListNode slowPtr = headNode;
		ListNode fastPtr = headNode;
		// 检查链表是否为空
		if (headNode == null) {
			return headNode;
		}
		// 循环查找分割点
		while (fastPtr.getNext() != headNode && fastPtr.getNext().getNext() != headNode) {
			fastPtr = fastPtr.getNext().getNext();
			slowPtr = slowPtr.getNext();
		}
		// 如果循环链表的结点数为偶数，那么fastPtr再向后移动一个结点，此时就是循环链表的最后一个结点（此结点的下一个结点就是headNode）
		if (fastPtr.getNext().getNext() == headNode) {
			fastPtr = fastPtr.getNext();
		}
		// 设置前半部分的头结点,实际上headNode就是前部分循环链表的头结点
//		ListNode headNode1 = headNode;
		// 设置后半部分的头结点
		ListNode headNode2 = slowPtr.getNext();
		// 把后半部分变成环
		fastPtr.setNext(slowPtr.getNext());
		// 把前半部分变成环
		slowPtr.setNext(headNode);
		// 只需要返回后半部分循环链表的头结点
		return headNode2;
	}
	
	/*
	 * 题目：约瑟夫环，N个人想选出一个领头人，他们排成一个环，沿着环每数到第M个人就从环中排除该人，并从下一个人开始重新数。请找到最后留下的那个人
	 */
	/**
	 * 约瑟夫环，N个人想选出一个领头人，他们排成一个环，沿着环每数到第M个人就从环中排除该人，并从下一个人开始重新数。请找到最后留下的那个人
	 * @param headNode 循环链表的头结点
	 * @param length 循环链表的长度
	 * @param m 淘汰的数字
	 * @return 最后留下来的结点
	 */
	public static ListNode getJosephusPosition(ListNode headNode, int length, int m) {
		// 如果环内选手数大于1，就删除第m个选手
		for (int i = length; i > 1; i--) {
			// 开始数数,寻找被删除结点的前驱结点
			for (int j = 1; j < m - 1; j++) {
				headNode = headNode.getNext();
			}
			// 删除第m个结点
			headNode.setNext(headNode.getNext().getNext());
			// 指向下一个结点重新数数
			headNode = headNode.getNext();
		}
		return headNode;
	}
}




















