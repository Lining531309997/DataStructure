package com.localhost.part02.linkedList;

public class ListNode<AnyType> {

	/**
	 * 结点数据域
	 */
	private AnyType data;	
	
	/**
	 * 结点的next指针
	 */
	private ListNode<AnyType> next;	
	
	/**
	 * 无参构造函数
	 */
	public ListNode() {}
	
	/**
	 * 有参构造函数
	 * @param data 节点数据
	 */
	public ListNode(AnyType data) {
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public AnyType getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(AnyType data) {
		this.data = data;
	}

	/**
	 * @return the next
	 */
	public ListNode<AnyType> getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(ListNode<AnyType> next) {
		this.next = next;
	}
}
