package com.localhost.part04.queue;

import com.localhost.part02.linkedList.ListNode;

/**
 * 基于链表实现的队列
 * 通过在链表末端插入元素的方法实现入队
 * 通过删除链表表头元素实现出队
 */
public class LinkListQueue<AnyType> {

	// 队首节点
	private ListNode<AnyType> frontNode;
	
	// 对尾节点
	private ListNode<AnyType> rearNode;
	
	/**
	 * 构造方法
	 */
	public LinkListQueue() {
		this.frontNode = null;
		this.rearNode = null;
	}
	
	/**
	 * 创建队列
	 * @return 新建的队列
	 */
	public LinkListQueue<AnyType> createQueue() {
		return new LinkListQueue<AnyType>();
	}
	
	/**
	 * 检查队列是否为空
	 * @return true 空队列 false 非空队列
	 */
	public boolean isEmpty() {
		return (frontNode == null);
	}
	
	/**
	 * 获取队列中元素的个数
	 * @return
	 */
	public int getQueueSize() {
		int size = 0;
		ListNode<AnyType> currentNode = frontNode;
		while (currentNode != null) {
			size++;
			currentNode = currentNode.getNext();
		}
		return size;
	}
	
	/**
	 * 入队
	 * @param data 入队的元素值
	 */
	public void enQueue(AnyType data) {
		// 创建新节点
		ListNode<AnyType> newNode = new ListNode<AnyType>(data);
		// 入队
		if (rearNode != null) {
			rearNode.setNext(newNode);
		}
		// 使尾节点指向新节点
		rearNode = newNode;
		// 如果是第一个入队元素，修改队首值
		if (frontNode == null) {
			frontNode = rearNode;
		}
	}
	
	/**
	 * 出队
	 * @return 出队元素的值
	 * @throws Exception 
	 */
	public AnyType deQueue() {
		AnyType data = null;
		// 检查队列是否为空
		if (!isEmpty()) {
			// 如果队列不为空，获取出队元素值
			data = frontNode.getData();
			
			// 修改队首的值
			if (frontNode == rearNode) {
				//  如果是队列中唯一一个元素
				frontNode = rearNode = null;
			} else {
				// 不是队列中的唯一元素
				frontNode = frontNode.getNext();
			}
		}
		return data;
	}
}











