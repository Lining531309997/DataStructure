package com.localhost.part04.queue;

/**
 * 基于简单循环数组实现队列
 * 局限性：用于实现队列的数组最大空间必须预先声明且不能改变
 */
public class ArrayQueue {

	// 队首在数组中的下标
	private int front;
	
	// 队尾在数组中的下标
	private int rear;
	
	// 队列容量，队列可以存储的元素个数
	private int	capacity;
	
	// 数组
	private int[] array;
	
	/**
	 * 构造方法
	 * 初始化时，front和rear都置为-1，表示队列为空
	 * @param size 队列大小
	 */
	private ArrayQueue(int size) {
		this.capacity = size;
		this.front = -1;
		this.rear = -1;
		this.array = new int[size];
	}
	
	/**
	 * 创建队列
	 * @param size 队列大小
	 * @return 新的队列
	 */
	public static ArrayQueue createQueue(int size) {
		return new ArrayQueue(size);
	}
	
	/**
	 * 检查队列是否为空
	 * @return true 表示队列为空 false 队列不为空
	 */
	public boolean isEmpty() {
		return (front == -1);
	}
	
	/**
	 * 检查队列是否为满队列
	 * @return true 满队列 false 不是满队列
	 */
	public boolean isFull() {
		return ((rear + 1) % capacity == front);
	}
	
	/**
	 * 获取队列中元素的个数
	 * @return
	 */
	public int getQueueSize() {
		return ((capacity - front + rear + 1) % capacity);
	}
	
	/**
	 * 入队列
	 * @param data 入队列的元素
	 * @throws Exception 
	 */
	public void enQueue(int data) throws Exception {
		// 1.检查队列是否已满，如果满则抛出异常
		if (isFull()) {
			throw new Exception("Queue Overflow");
		} else {
			// 2.1 栈不为空，先计算新增元素在数组中的下标
			rear = (rear + 1) % capacity;
			// 2.2 将元素添加到数组
			array[rear] = data;
			// 2.3 如果是第一个入队元素，修改队首值
			if (front == -1) {
				front = rear;
			}
		}
	} 
	
	/**
	 * 出队
	 * @return 出队的元素值
	 * @throws Exception
	 */
	public int deQueue() throws Exception {
		int data = -999;
		// 1.检查队列是否为空，如果为空则抛出异常
		if (isEmpty()) {
			throw new Exception("Queue Underflow");
		} else {
			// 2.1 如果队列不为空，获取出队元素值
			data = array[front];
			
			// 修改队首的值
			if (front == rear) {
				// 2.2 如果是队列中唯一一个元素
				front = rear -1;
			} else {
				// 2.3 不是队列中的唯一元素
				front = (front + 1) % capacity;
			}
		}
		return data;
	}
}
