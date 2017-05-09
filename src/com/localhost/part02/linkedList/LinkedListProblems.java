package com.localhost.part02.linkedList;


public class LinkedListProblems {

	public static void main(String[] args) {

		// 创建测试结点
		ListNode<Integer> headNode1 = null;
		ListNode<Integer> headNode2 = null;
		ListNode<Integer> node1 = new ListNode<Integer>(1);
		ListNode<Integer> node2 = new ListNode<Integer>(3);
		ListNode<Integer> node3 = new ListNode<Integer>(5);
		ListNode<Integer> node4 = new ListNode<Integer>(7);
		ListNode<Integer> node5 = new ListNode<Integer>(9);
		
		ListNode<Integer> node6 = new ListNode<Integer>(2);
		ListNode<Integer> node7 = new ListNode<Integer>(4);
		ListNode<Integer> node8 = new ListNode<Integer>(6);
		ListNode<Integer> node9 = new ListNode<Integer>(8);
		
		// 生成单链表
		headNode1 = node1;
		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node4);
		node4.setNext(node5);
		headNode2 = node6;
		node6.setNext(node7);
		node7.setNext(node8);
		node8.setNext(node9);
		
		ListNode<Integer> newListNode = mergeList2(headNode1, headNode2);
		LinkedList.traverseLinkedList(newListNode);
	}

	
	/*
	 * 思路：
	 * 使用两个具有不同移动速度的指针。如果链表有环，两个指针就会在环中相遇；如果链表没有环，移动快的指针遇到null就结束。
	 * 形象一点，可以设想乌龟和兔子在一个环形轨道上赛跑，那么跑得快的兔子就会追赶上乌龟。
	 * 设定：指针slowPtr每次移动一个结点，指针fastPtr每次移动两个结点
	 */
	
	/**
	 * 判断给定的链表是否包含环
	 * @param headNode 链表的头结点
	 * @return true：表示链表包含环；false：表示链表不包含环
	 */
	public static boolean doesLinkedListContainsLoop(ListNode<Integer> headNode){
		// 首先判断链表是否存在
		if (headNode == null) {
			return false;
		}
		// 初始化slowPtr和fastPtr指针，使其都指向头结点
		ListNode<Integer> slowPtr = headNode;
		ListNode<Integer> fastPtr = headNode;
		// slowPtr指针每次移动一个结点;fastPtr每次移动两个结点
		while(slowPtr.getNext() != null && fastPtr.getNext().getNext() != null){
			slowPtr = slowPtr.getNext();
			fastPtr = fastPtr.getNext().getNext();
			if (slowPtr == fastPtr) {
				return true;
			}
		}
		return false;
	}
	
	// 如果链表包含环，找到环的开始结点即入环口。
	
	/*
	 * http://www.cnblogs.com/ccdev/archive/2012/09/06/2673618.html
	 * 算法描述：
	 * 当fastPtr在环内第一次最赶上slowPtr时，slowPtr肯定没有走完链表，而fast已经在环内循环了n圈(1<=n)。
	 * 假设slowPtr走了s步，则fastPtr走了2s步（fastPtr的步数还等于s加上在环上多转的n圈），设环长为r，则：
	 * 2s = s + nr
	 * s = nr
	 * 设整个链表长L，入口环与相遇点距离为x，起点到环入口点的距离为a。
	 * 因为 s = a + x 所以有 a + x = nr
	 * a + x = (n–1)r + r = (n-1)r + (L-a)
	 * a = (n-1)r + (L–a–x)
	 * (L–a–x)为相遇点到环入口点的距离，由此可知，从链表头到环入口点距离 = (n-1)圈的环长 + 相遇点到环入口点距离
	 * 于是我们在链表头和相遇点分别设一个指针，两个指针每次各走一步，两个指针必定相遇，且相遇第一点为环入口点。
	 * 
	 * 也就是说，假设使fastPtr指向链表头结点，使slowPtr指向相遇点，两个指针同时移动，每次移动一个结点，
	 * 当fastPtr移动到环开始的结点时，移动的距离就为a;此时slowPtr就移动了(n-1)圈的环长+相遇点到环入口点的距离
	 * 两个指针相遇的结点就是环的入口结点。
	 */
	
	/**
	 * 找到链表中环的开始结点即入环口。
	 * @param headNode 链表的头结点
	 * @return 环入口结点
	 */
	public static ListNode<Integer> findBeginOfLoop(ListNode<Integer> headNode) {
		// 先判断链表中是否有环
		boolean loopExists = false;
		// 初始化slowPtr和fastPtr指针，使其都指向头结点
		ListNode<Integer> slowPtr = headNode;
		ListNode<Integer> fastPtr = headNode;
		while(slowPtr.getNext() != null && fastPtr.getNext().getNext() != null){
			slowPtr = slowPtr.getNext();
			fastPtr = fastPtr.getNext().getNext();
			if (slowPtr == fastPtr) {
				System.out.println("找到环的入口");
				// 链表中环存在
				loopExists = true;
				// 跳出循环，一定要写，不然就是死循环
				break;
			}
		}
		// 如果环存在
		if (loopExists) {
			fastPtr = headNode;
			// slowPtr指针和fastPtr指针每次移动一个结点
			while(slowPtr != fastPtr){
				slowPtr = slowPtr.getNext();
				fastPtr = fastPtr.getNext();
			}
			return slowPtr;
		}
		// 如果不存在
		return null;
	}
	
	
	// 如果链表中存在环，求环的长度
	
	/*
	 * 首先找到slowPtr指针和fastPtr指针两个指针的相遇点，然后保持fastPtr指针不动，使slowPtr指针继续移动，每次移动一个结点。
	 */
	/**
	 * 如果链表中存在环，求环的长度
	 * @param headNode 链表的头结点
	 * @return 环的长度
	 */
	public static int getLengthOfLoop(ListNode<Integer> headNode){
		int length = 0;
		// 先判断链表中是否有环
		boolean loopExists = false;
		// 初始化slowPtr和fastPtr指针，使其都指向头结点
		ListNode<Integer> slowPtr = headNode;
		ListNode<Integer> fastPtr = headNode;
		while(slowPtr.getNext() != null && fastPtr.getNext().getNext() != null){
			slowPtr = slowPtr.getNext();
			fastPtr = fastPtr.getNext().getNext();
			if (slowPtr == fastPtr) {
				System.out.println("找到环的入口");
				// 链表中环存在
				loopExists = true;
				// 跳出循环，一定要写，不然就是死循环
				break;
			}
		}
		// 如果环存在
		if (loopExists) {
			do {
				slowPtr = slowPtr.getNext();
				length++;
			} while (slowPtr != fastPtr);
		}
		// 返回环的长度
		return length;
	}
	
	// 向有序链表中插入一个结点
	/*
	 * 遍历链表，找到存放元素的正确结点位置后，插入结点
	 */
	/**
	 * 向有序链表中插入一个结点
	 * @param headNode 有序链表的头结点
	 * @param newNode 需要插入的结点
	 * @return 插入结点后的有序链表
	 */
	public static ListNode<Integer> insertIntoSortedList(ListNode<Integer> headNode, ListNode<Integer> newNode) {
		// 判断链表是否为空
		if (headNode == null) {
			return newNode;
		}
		// 寻找正确位置，以及该位置的前驱结点
		ListNode<Integer> previousNode = null;
		ListNode<Integer> currentNode = headNode;
		while ((currentNode != null) && (currentNode.getData() < newNode.getData())) {
			previousNode = currentNode;
			currentNode = currentNode.getNext();
		}
		// 插入结点
		previousNode.setNext(newNode);
		newNode.setNext(currentNode);
		// 返回有序链表的头结点
		return headNode;
	}
	
	// 逆置单向链表
	/*
	 * 思路：利用栈实现
	 * 1.先获取单链表的长度，并创建相应长度的栈；
	 * 2.将单链表的数据一个一个存到栈中，然后利用栈先入后出的特性
	 * 3.从栈中取出数据生成新的单链表，即原单链表的逆置链表
	 */
	/*
	 * 思路：使用迭代
	 */
	
	/**
	 * 逆置单向链表
	 * @param headNode 链表的头结点
	 * @return 逆置后的链表
	 */
	public static ListNode<Integer> reverseList(ListNode<Integer> headNode) {
		// 已经被逆置的结点(准确说是原链表中当前结点的前驱结点)
		ListNode<Integer> tempNode = null;
		// 即将被逆置的结点的下一个结点(准确说是原链表中当前结点的后继结点)
		ListNode<Integer> nextNode = null;
		
		while (headNode != null) {
			// 先找到将被逆置的结点的下一个结点(也就是原链表中当前结点的后继结点)
			nextNode = headNode.getNext();
			// 使当前结点的next指针指向上一个被逆置过的结点(也就是指向原链表中当前结点的前驱结点)
			headNode.setNext(tempNode);
			// 逆置结束，将当前结点标记为已经被逆置的结点
			tempNode = headNode;
			// 寻找下一个即将被逆置的结点
			headNode = nextNode;
		}
		// 返回被逆置的链表头结点
		return tempNode;
	}
	
	// 如何从单链表的表尾开始输出该链表
	/*
	 * 思路：先将链表逆置，然后输出
	 */
	/*
	 * 思路：使用递归实现
	 */
	
	/**
	 * 从单链表的表尾开始输出该链表
	 * @param headNode 链表的头结点
	 */
	public static void printListFromEnd1(ListNode<Integer> headNode) {
		if (headNode != null) {
			printListFromEnd1(headNode.getNext());
			System.out.print(headNode.getData() + " ");
		}
	}
	
	
	/**
	 * 从单链表的表尾开始输出该链表
	 * @param headNode 链表的头结点
	 */
	public static void printListFromEnd2(ListNode<Integer> headNode) {
		if (headNode != null) {
			ListNode<Integer> currentNode = reverseList(headNode);
			while (currentNode != null) {
				System.out.print(currentNode.getData() + " ");
				currentNode = currentNode.getNext();
			}
			System.out.println();
		}
	}
	
	// 假设两个单向链表在某结点相交后，成为一个单向链表。两个链表的头结点是已知的，但是相交的结点未知。也就是说，它们相交之前的结点个数是未知的，并且两个链表的结点数也可能不同。假设链表List1和链表List2在相交之前
	// 的结点个数分别为n和m，那么m可能等于或小于n,也可能大于n。找出两个链表的合并点。
	
	/*
	 * 思路：
	 * 1.先获取两个链表的长度（假设List1的长度为m,List2的长度为n，m > n）
	 * 2.计算两个链表的长度差 d = m - n
	 * 3.从较长的链表（List1）的表头开始，移动d个结点
	 * 4.两个链表同时移动，直至出现两个后继指针的值相等的情况
	 */
	/**
	 * 找出两个链表的合并点
	 * @param list1  链表1
	 * @param list2  链表2
	 * @return 合并点
	 */
	public static ListNode<Integer> findIntersectingNode(ListNode<Integer> list1, ListNode<Integer> list2) {
		// 先获取两个链表的长度
		int m = LinkedList.getLength(list1);
		int n = LinkedList.getLength(list2);
		// 计算长度的差值,并确定长链表和短链表
		int diff = 0;
		ListNode<Integer> headOfLongList = null;
		ListNode<Integer> headOfShortList = null;
		if (m < n) {
			diff = n - m;
			headOfLongList = list2;
			headOfShortList = list1;
		} else {
			diff = m - n;
			headOfLongList = list1;
			headOfShortList = list2;
		}
		// 长链表移动diff个结点
		for (int i = 0; i < diff; i++) {
			headOfLongList = headOfLongList.getNext();
		}
		// 两个链表一起移动，寻找合并点
		while (headOfLongList != null && headOfShortList != null) {
			headOfLongList = headOfLongList.getNext();
			headOfShortList = headOfShortList.getNext();
			// 如果找到直接返回
			if (headOfLongList == headOfShortList) {
				return headOfLongList;
			}
		}
		// 两个链表没有合并点
		return null;
	}
	
	/*
	 * 题目：如何找到链表的中间结点
	 * 思路1：
	 * 遍历链表，得到链表的长度；
	 * 定位到第n/2个结点，即中间结点。
	 * 思路2：
	 * 使用两个指针fastPtr和slowPtr，两个指针同时从头结点出发，并使fastPtr移动速度是slowPtr移动速度的两倍
	 * 当fastPtr移动到链表的尾结点时，slowPtr指向的结点就是中间结点
	 */
	/**
	 * 如何找到链表的中间结点
	 * @param headNode 链表的头结点
	 * @return 链表的中间结点
	 */
	public static ListNode<Integer> findMiddleNode1(ListNode<Integer> headNode) {
		// 获取链表的长度
		int length = LinkedList.getLength(headNode);
		// 定位到第n/2个结点，即中间结点
		ListNode<Integer> middleNode = LinkedList.getNodeByPosition(headNode, length/2);
		return middleNode;
	}
	
	/**
	 * 如何找到链表的中间结点
	 * @param headNode 链表的头结点
	 * @return 链表的中间结点
	 */
	public static ListNode<Integer> findMiddleNode2(ListNode<Integer> headNode) {
		// 定义两个指针
		ListNode<Integer> fastPtr = headNode;
		ListNode<Integer> slowPtr = headNode;
		// 同时移动，并使fastPtr移动速度是slowPtr移动速度的两倍，需要判断尾结点
		int i = 0;
		while (fastPtr.getNext() != null) {
			if (i == 0) {
				// fastPtr指针先移动一个结点
				fastPtr = fastPtr.getNext();
				i = 1;
			} else if (i == 1) {
				// 两个指针同时移动一个结点
				fastPtr = fastPtr.getNext();
				slowPtr = slowPtr.getNext();
				i = 0;
			} 
		}
		// 返回中间结点
		return slowPtr;
	}
	
	/*
	 * 题目:检查链表的长度是奇数还是偶数
	 * 思路1：
	 * 先获取链表的长度，对链表的长度进行判断
	 * 思路2：
	 * 使指针指向链表的头结点，指针每次移动两个结点。如果最后指针指向null，说明链表长度是偶数；否则就是指向尾结点，长度为奇数。
	 */
	/**
	 * 检查链表的长度是奇数还是偶数
	 * @param headNode 链表的头结点
	 * @return true 是偶数	false 是奇数
	 */
	public static boolean isEven1(ListNode<Integer> headNode) {
		// 先获取链表的长度
		int length = LinkedList.getLength(headNode);
		// 对长度进行奇偶判断
		if (length % 2 == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 检查链表的长度是奇数还是偶数
	 * @param headNode 链表的头结点
	 * @return true 是偶数	false 是奇数
	 */
	public static boolean isEven2(ListNode<Integer> headNode) {
		while (headNode != null && headNode.getNext() != null) {
			headNode = headNode.getNext().getNext();
		}
		if (headNode == null) {
			return true;
		}
		return false;
	}
	
	/*
	 * 题目：将两个有序链表合并为一个新的有序链表
	 * 思路1：
	 * 先获取两个链表的长度，保持长的链表不动，对短的链表遍历，一个一个的插入的新的链表
	 * 思路2：
	 * 利用递归实现
	 */
	/**
	 * 将两个有序链表合并为一个新的有序链表
	 * @param list1  有序链表
	 * @param list2  有序链表
	 * @return 合并后新的链表
	 */
	public static ListNode<Integer> mergeList1(ListNode<Integer> list1, ListNode<Integer> list2) {
		// 判断链表是否存在
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}
		// 先获取两个链表的长度
		int m = LinkedList.getLength(list1);
		int n = LinkedList.getLength(list2);
		// 对比链表长度,确定长链表和短链表
		ListNode<Integer> headOfLongList = null;
		ListNode<Integer> headOfShortList = null;
		if (m < n) {
			headOfLongList = list2;
			headOfShortList = list1;
		} else {
			headOfLongList = list1;
			headOfShortList = list2;
		}
		// 遍历短的链表并插入长的链表中
		ListNode<Integer> newHeadNode = headOfLongList;
		ListNode<Integer> previousNode = null;
		ListNode<Integer> currentNode = null;
		while (headOfLongList != null && headOfShortList != null) {
			if (headOfLongList.getData() <= headOfShortList.getData()) {
				previousNode = headOfLongList;
				headOfLongList = headOfLongList.getNext();
				continue;
			}
			// 找到需要插入的结点，并将短链表移动一个结点
			currentNode = headOfShortList;
			headOfShortList = currentNode.getNext();
			// 向长链表插入短链表的结点
			previousNode.setNext(currentNode);
			currentNode.setNext(headOfLongList);
		}
		return newHeadNode;
	}
	
	/**
	 * 将两个有序链表合并为一个新的有序链表
	 * @param list1  有序链表
	 * @param list2  有序链表
	 * @return 合并后新的链表
	 */
	public static ListNode<Integer> mergeList2(ListNode<Integer> list1, ListNode<Integer> list2) {
		ListNode<Integer> result = null;
		// 判断链表是否存在
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}
		if (list1.getData() <= list2.getData()) {
			result = list1;
			result.setNext(mergeList2(list1.getNext(), list2));
		} else {
			result = list2;
			result.setNext(mergeList2(list1, list2.getNext()));
		}
		return result;
	}
}




















