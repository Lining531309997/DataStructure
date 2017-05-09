package com.localhost.part03.stack;

/**
 * 动态数组实现栈
 */
public class DynArrayStack {
	
	/**
	 * 栈顶
	 */
	private int top;
	
	/**
	 * 栈的容量
	 */
	private int capacity;
	
	/**
	 * 实现栈的数组
	 */
	private int[] array;
	
	/**
	 * 无参构造方法
	 */
	public DynArrayStack() {
		this.capacity = 1;
		this.array = new int[this.capacity];
		this.top = -1;
	}
	
	/**
	 * 有参构造方法
	 * @param capacity 数组的大小
	 */
	public DynArrayStack(int capacity) {
		this.capacity = capacity;
		this.array = new int[this.capacity];
		this.top = -1;
	}
	
	/**
	 * 判断栈是否为空
	 * @return true 是空栈	false 不是空栈
	 */
	public boolean isEmpty() {
		return (top == -1);
	}
	
	/**
	 * 判断栈是否已满
	 * @return true 是满栈	false 不是满栈
	 */
	public boolean isFull() {
		return (top == (capacity - 1));
	}
	
	/**
	 * 获取栈中元素个数
	 * @return 栈中元素个数
	 */
	public int size() {
		return (top + 1);
	}
	
	/**
	 * 入栈
	 * @param data 需要入栈的数据
	 */
	public void push(int data) {
		if (isFull()) {
			// 如果栈满了就倍增扩容
			doubleStack();
		} 
		top++;
		array[top] = data;
	}
	
	/**
	 * 数组动态扩容，采用重复倍增
	 */
	private void doubleStack() {
		// 创建新的数组，大小为原来数组的两倍
		int[] newArray = new int[2 * capacity];
		// 复制原数组到新数组
		System.arraycopy(array, 0, newArray, 0, capacity);
		// 重置数组容量
		capacity = capacity * 2;
		// 复制给原数组，也就是使array指向新生成内存
		array = newArray;
	}
	
	/**
	 * 出栈
	 * @return 出栈数据
	 */
	public int pop() {
		if (isEmpty()) {
			System.out.println("栈为空！");
			return -999;	// 栈为空时一般约定返回一个不常用的数
		} else {
			return array[top--];
		}
	}
	
	/**
	 * 删除栈
	 */
	public void deleteStack() {
		top = -1;
	}
}
