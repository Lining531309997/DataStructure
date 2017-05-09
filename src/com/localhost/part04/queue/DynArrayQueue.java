package com.localhost.part04.queue;

/**
 * 基于动态循环数组实现队列
 */
public class DynArrayQueue {

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
	 */
	private DynArrayQueue() {
		this.capacity = 1;
		this.front = -1;
		this.rear = -1;
		this.array = new int[1];
	}
	
	/**
	 * 创建队列
	 * @return 新的队列
	 */
	public static DynArrayQueue createQueue(int size) {
		return new DynArrayQueue();
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
		
		if (front == -1) {
			return 0;
		}
		
		int size = (capacity - front + rear + 1) % capacity;
		// 如果size为0，说明队列为满队列
		if (size == 0) {
			return capacity;
		} else {
			return size;
		}
	}
	
	/**
	 * 入队列
	 * @param data 入队列的元素
	 * @throws Exception 
	 */
	public void enQueue(int data) throws Exception {
		// 1.检查队列是否已满，如果满则抛出异常
		if (isFull()) {
			resizeQueue();
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
	 * 对队列进行扩容
	 */
	private void resizeQueue() {
		int initCapacity = capacity;
		// 扩容为原来的2倍
		capacity *= 2;
		// 记录数组的元素
		int[] oldArray = array;
		// 创建扩容后新的数组
		array = new int[capacity];
		// 将原来数组的元素复制到新数组中
		for (int i = 0; i < oldArray.length; i++) {
			array[i] = oldArray[i];
		}
		// 将元素按入队顺序排列好
		if (rear < front) {
			for (int i = 0; i < front; i++) {
				array[i + initCapacity] = array[i];
				array[i] = 0; // 清空原来的元素值
			}
			rear = rear + initCapacity;
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
