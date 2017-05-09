package com.localhost.part02.linkedList;

/**
 * 链表的基本操作
 * 1. 遍历链表
 * 2. 向链表中插入一个元素
 * 3. 从链表中删除一个元素
 */
public class LinkedList1 {
	
	public static void main(String[] args) {

		LinkedList1 list = new LinkedList1();
		ListNode node1 = new ListNode(5);
		ListNode node2 = new ListNode(6);
		ListNode node3 = new ListNode(7);
		ListNode node4 = new ListNode(8);
		ListNode node5 = new ListNode(10);
		
		ListNode headNode = node1;
		headNode = list.insertLinkedListNode(headNode, node5, 1);
		headNode = list.insertLinkedListNode(headNode, node2, 2);
		headNode = list.insertLinkedListNode(headNode, node3, 3);
		headNode = list.insertLinkedListNode(headNode, node4, 4);
		
		// 获取单链表的长度
		int length = list.getLength(headNode);
		System.out.println("单链表的长度为：" + length);
		
		// 遍历单链表
		list.traverseLinkedList(headNode);
		
		// 插入新结点
		ListNode newNode = new ListNode(9);
		headNode = list.insertLinkedListNode(headNode, newNode, 10);
		list.traverseLinkedList(headNode);
		
		// 删除结点
		headNode = list.deleteLinkedListNode(headNode, 2);
		list.traverseLinkedList(headNode);
		
		// 清除单链表
		list.clearLinkedList(headNode);
		int size = list.getLength(headNode);
		System.out.println("单链表的长度为：" + size);
		
		list.traverseLinkedList(headNode);
	}
	
	/**
	 * 获取链表的长度
	 * @param headNode 头结点
	 * @return 链表的长度
	 */ 
	public int getLength(ListNode headNode) {
		int length = 0;
		ListNode currentNode = headNode;
		while (currentNode != null) {
			length++;
			currentNode = currentNode.getNext();
		}
		return length;
	}
	
	/**
	 * 遍历链表
	 * @param headNode 头结点
	 */
	public void traverseLinkedList(ListNode headNode) {
		ListNode currentNode = headNode;
		while (currentNode != null) {
			System.out.print(currentNode.getData() + " ");
			currentNode = currentNode.getNext();
		}
		System.out.println();
	}
	
	/**
	 * 插入新结点
	 * @param headNode 头结点
	 * @param nodeToInsert 要插入的新结点
	 * @param position 要插入的位置
	 */
	public ListNode insertLinkedListNode(ListNode headNode, ListNode nodeToInsert, int position) {
		
		/*
		 * 判断链表是否存在
		 * 当链表不存在直接返回nodeToInsert作为链表的头结点
		 */
		if (headNode == null) {
			return nodeToInsert;
		}
		
		/*
		 * 检查需要插入的位置是否合法
		 */
		int length = getLength(headNode);
		if (position > length + 1 || position < 1) {
			System.out.println("插入的位置不合法，有效的位置为：1 ~ " + (length + 1));
			return headNode;
		} 
		
		/*
		 * 插入新结点
		 * 1.向链表的开头插入新的结点
		 * 	  更新新结点的next指针，使其指向当前链表的表头
		 * 	  更新当前链表的表头指针的值，使其指向新结点
		 * 2.向单链表的尾部插入新结点
		 * 	  使新结点的next指针指向null
		 * 	  使当前链表的最后结点的next指针指向新结点
		 * 3.向单链表的中间插入新结点
		 * 	   先遍历找到位置结点的前驱结点
		 *   将前驱结点next指针指向的结点赋值给新结点的next指针
		 *   使前驱结点的next指针指向新结点
		 */
		if (position == 1) {
			// 在单链表开头插入结点
			nodeToInsert.setNext(headNode);
			return nodeToInsert;
		} else {
			// 在链表中间或者尾部插入结点
			int count = 1;
			ListNode previousNode = headNode;
			while(count < position - 1) {
				previousNode = previousNode.getNext();
				count++;
			}
			ListNode currentNode = previousNode.getNext();
			nodeToInsert.setNext(currentNode);
			previousNode.setNext(nodeToInsert);
		}
		return headNode;
	}
	
	
	public ListNode deleteLinkedListNode(ListNode headNode, int position) {
		
		/*
		 * 检查删除的位置是否合法
		 */
		int length = getLength(headNode); 
		if (position > length || position < 1) {
			System.out.println("删除的位置不合法，有效的位置为：1 ~ " + (length + 1));
			return headNode;
		}
		
		/*
		 * 单链表删除结点
		 * 1.删除单链表的头结点
		 * 	   创建一个临时结点指向表头指针指向的头节点
		 *   修改表头指针，使其指向下一个结点，并移除临时结点
		 * 2.删除单链表的最后一个结点
		 * 	  先遍历找到尾结点的前驱结点
		 * 	  使尾结点的next指针指向null
		 *   移除单链表的尾结点
		 * 3.删除单链表的中间结点
		 * 	  先遍历找到删除结点的前驱结点
		 * 	  使前驱结点的next指针指向删除结点next指针指向的值
		 *   移除单链表的删除结点
		 */
		if (position == 1) {
			// 删除单链表的头结点
			ListNode newHaedNode = headNode.getNext(); 
			headNode = null;
			return newHaedNode;
		} else {
			// 删除单链表的中间结点或者尾结点
			int count = 1;
			ListNode previousNode = headNode;
			while(count < position - 1) {
				previousNode = previousNode.getNext();
				count++;
			}
			ListNode nodeToDelete = previousNode.getNext();
			previousNode.setNext(nodeToDelete.getNext());
			nodeToDelete = null;
		}
		return headNode;
	}
	
	/**
	 * 清除单链表
	 * @param headNode 头指针
	 */
	public void clearLinkedList(ListNode headNode) {
//		ListNode currentNode = headNode;
		ListNode nextNode = headNode;
		while (headNode != null) {
			nextNode = headNode.getNext();
			headNode.setNext(null);
			headNode = null;
			headNode = nextNode;
		}
//		return headNode;
	}
}
