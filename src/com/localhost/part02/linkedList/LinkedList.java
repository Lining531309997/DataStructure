package com.localhost.part02.linkedList;


/**
 * 链表的基本操作
 * 1. 遍历链表
 * 2. 向链表中插入一个元素
 * 3. 从链表中删除一个元素
 */
public class LinkedList {
	
	public static void main(String[] args) {
		
		// 创建测试结点
		ListNode<Integer> headNode = null;
		ListNode<Integer> node1 = new ListNode<Integer>(5);
		ListNode<Integer> node2 = new ListNode<Integer>(6);
		ListNode<Integer> node3 = new ListNode<Integer>(7);
		ListNode<Integer> node4 = new ListNode<Integer>(8);
		
		// 生成单链表
		headNode = node1;
		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node4);
		
		traverseLinkedList(headNode);
		ListNode<Integer> tempNode = getReciprocalNode3(headNode, 3);
		if (tempNode != null) {
			System.out.println(tempNode.getData());
		}
//		// 获取单链表的长度
//		int length = getLength(headNode);
//		System.out.println("单链表的长度为：" + length);
//		
//		// 遍历单链表
//		traverseLinkedList(headNode);
//		
//		// 插入新结点
//		ListNode newNode = new ListNode(9);
//		headNode = insertLinkedListNode(headNode, newNode, 3);
//		traverseLinkedList(headNode);
//		
//		// 删除结点
//		headNode = deleteLinkedListNode(headNode, 2);
//		traverseLinkedList(headNode);
//		
//		// 清除单链表
//		clearLinkedList(headNode);
//		int size = getLength(headNode);
//		System.out.println("单链表的长度为：" + size);
//		
//		traverseLinkedList(headNode);
	}
	
	/**
	 * 获取链表的长度
	 * @param headNode 头结点
	 * @return 链表的长度
	 */ 
	public static int getLength(ListNode<Integer> headNode) {
		int length = 0;
		ListNode<Integer> currentNode = headNode;
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
	public static void traverseLinkedList(ListNode<Integer> headNode) {
		ListNode<Integer> currentNode = headNode;
		while (currentNode != null) {
			System.out.print(currentNode.getData() + " ");
			currentNode = currentNode.getNext();
		}
		System.out.println();
	}
	
	/**
	 * 根据位置获取结点
	 * @param headNode 链表的头结点
	 * @param position 位置
	 * @return 位置结点
	 */
	public static ListNode<Integer> getNodeByPosition(ListNode<Integer> headNode, int position) {

		// 检查需要查询的位置是否合法
		int length = getLength(headNode);
		if (position > length + 1 || position < 1) {
			System.out.println("查询的位置不合法，有效的位置为：1 ~ " + (length + 1));
			return null;
		} 
		
		int count = 1;
		ListNode<Integer> currentNode = headNode;
		while ((currentNode != null) && (count != position)) {
			count++;
			currentNode = currentNode.getNext();
		}
		return currentNode;
	}
	
	/**
	 * 插入新结点
	 * @param headNode 头结点
	 * @param nodeToInsert 要插入的新结点
	 * @param position 要插入的位置
	 */
	public static ListNode<Integer> insertLinkedListNode(ListNode<Integer> headNode, ListNode<Integer> nodeToInsert, int position) {
		
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
			ListNode<Integer> previousNode = headNode;
			while(count < position - 1) {
				previousNode = previousNode.getNext();
				count++;
			}
			ListNode<Integer> currentNode = previousNode.getNext();
			nodeToInsert.setNext(currentNode);
			previousNode.setNext(nodeToInsert);
		}
		return headNode;
	}
	
	
	/**
	 * 根据位置删除结点
	 * @param headNode 头结点
	 * @param position 结点位置
	 * @return 删除位置结点后的链表
	 */
	public static ListNode<Integer> deleteLinkedListNode(ListNode<Integer> headNode, int position) {
		
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
			ListNode<Integer> newHaedNode = headNode.getNext(); 
			headNode = null;
			return newHaedNode;
		} else {
			// 删除单链表的中间结点或者尾结点
			int count = 1;
			ListNode<Integer> previousNode = headNode;
			while(count < position - 1) {
				previousNode = previousNode.getNext();
				count++;
			}
			ListNode<Integer> nodeToDelete = previousNode.getNext();
			previousNode.setNext(nodeToDelete.getNext());
			nodeToDelete = null;
		}
		return headNode;
	}
	
	/**
	 * 清除单链表
	 * @param headNode 头指针
	 */
	public static void clearLinkedList(ListNode<Integer> headNode) {
		ListNode<Integer> nextNode = headNode;
		while (headNode != null) {
			nextNode = headNode.getNext();
			headNode.setData(0);
			headNode.setNext(null);
			headNode = null;
			headNode = nextNode;
		}
	}
	
	/**
	 * 找到链表中倒数第n个结点
	 * @param n 倒数结点位置
	 * @return 倒数第n个结点
	 */
	public static ListNode<Integer> getReciprocalNode1(ListNode<Integer> headNode, int n) {
		// 先获取链表长度，判断n是否小于等于0
		int length = getLength(headNode);
		if (n < 1) {
			System.out.println("结点的个数不能为小于1");
			return null;
		}
		// 判断链表个数是否够n个
		if (n > length) {
			System.out.println("链表中结点的个数不足" + n + "个");
			return null;
		}
		
		/*
		 * 方法一：根据位置获取倒数第n个结点
		 * 总共length个结点，倒数第n个就等于正着数的（length-n+1）个结点
		 */
		ListNode<Integer> currentNode = getNodeByPosition(headNode, length - n + 1);
		return currentNode;
	}
	
	/**
	 * 找到链表中倒数第n个结点
	 * @param n 倒数结点位置
	 * @return 倒数第n个结点
	 */
	public static ListNode<Integer> getReciprocalNode2(ListNode<Integer> headNode, int n) {
		// 先获取链表长度，判断n是否小于等于0
		int length = getLength(headNode);
		if (n < 1) {
			System.out.println("结点的个数不能为小于1");
			return null;
		}
		// 判断链表个数是否够n个
		if (n > length) {
			System.out.println("链表中结点的个数不足" + n + "个");
			return null;
		}
		
		/*	
		 * 方法二：每次都从当前结点扫描链表中剩余的结点个数	
		 * 这里采用一个笨的方法，从第一个开始一个一个判断，每次都从当前结点扫描链表中剩余的结点个数
		 */
		ListNode<Integer> currentNode = headNode;
		while(length > n) {
			currentNode = currentNode.getNext();
			length--;
		}
		return currentNode;
	}
	
	/**
	 * 找到链表中倒数第n个结点
	 * @param n 倒数结点位置
	 * @return 倒数第n个结点
	 */
	public static ListNode<Integer> getReciprocalNode3(ListNode<Integer> headNode, int n) {
		// 先获取链表长度，判断n是否小于等于0
		int length = getLength(headNode);
		if (n < 1) {
			System.out.println("结点的个数不能为小于1");
			return null;
		}
		// 判断链表个数是否够n个
		if (n > length) {
			System.out.println("链表中结点的个数不足" + n + "个");
			return null;
		}

		/*
		 * 方法三：
		 * 使用两个指针pTemp和pNth。首先，两个指针都指向链表的头结点。
		 * 先使pTemp移动n次后，pNth才开始移动。此时两个指针一起移动，
		 * 当pTemp指针移动到链表的尾部，也就是pTemp指向链表的最后一个结点。
		 * 此时pNth所指向的结点就是所要求的倒数第n个结点。
		 */
		ListNode<Integer> pTemp = headNode;
		ListNode<Integer> pNth = headNode;
		// 使pTemp移动n次
		for (int i = 0; i < n; i++) {
			if (pTemp != null) {
				pTemp = pTemp.getNext();
			}
		}
		// pTemp和pNth两个指针一起移动
		while (pTemp != null) {
			pTemp = pTemp.getNext();
			pNth = pNth.getNext();
		}
		// 
		return pNth;
	}
}
