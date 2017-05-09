package com.localhost.part03.stack;

import com.localhost.part02.linkedList.ListNode;

/**
 * 链表实现栈
 */
public class LinkedListStack<AnyType> {
	
	/**
	 * 栈顶
	 */
	private ListNode<AnyType> headNode;
	
	/**
	 * 无参构造方法
	 */
	public LinkedListStack() {
		headNode = null;
	}
	
	/**
	 * 有参构造方法
	 */
	public LinkedListStack(AnyType data) {
		this.headNode = new ListNode<AnyType>(data); 
	}
	
	/**
	 * 判断栈是否为空
	 * @return true 是空栈	false 不是空栈
	 */
	public boolean isEmpty() {
		return (headNode == null);
	}
	
	/**
	 * 获取栈中元素个数
	 * @return 栈中元素个数
	 */
	public int size() {
		int size = 0;
		ListNode<AnyType> currentNode = headNode;
		while (currentNode != null) {
			size++;
			currentNode = currentNode.getNext();
		}
		return size;
	}
	
	/**
	 * 入栈
	 * @param data 需要入栈的数据
	 */
	public void push(AnyType data) {
		if (isEmpty()) {
			headNode = new ListNode<AnyType>(data);
		} else if (headNode.getData() == null){
			headNode.setData(data);
		} else {
			ListNode<AnyType> newNode = new ListNode<AnyType>(data);
			newNode.setNext(headNode);
			// 更新栈顶
			headNode = newNode;
		}
	}
	
	/**
	 * 输出栈顶元素
	 * @return 栈顶元素
	 */
	public AnyType top() {
		if (isEmpty()) {
			return null;
		} else {
			return headNode.getData();
		}
	}
	
	/**
	 * 出栈
	 * @return 出栈数据
	 */
	public AnyType pop() {
		if (isEmpty()) {
			System.out.println("栈为空！");
			return null;	
		} else {
			AnyType data = headNode.getData();
			headNode = headNode.getNext();
			return data;
		}
	}
	
	/**
	 * 删除栈
	 */
	public void deleteStack() {
		headNode = null;
	}
}
