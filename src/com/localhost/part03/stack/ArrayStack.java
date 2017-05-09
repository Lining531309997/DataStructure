package com.localhost.part03.stack;

/**
 * 简单数组实现栈
 */
public class ArrayStack {
	
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
	public ArrayStack() {
		this.capacity = 1;
		this.array = new int[this.capacity];
		this.top = -1;
	}
	
	/**
	 * 有参构造方法
	 * @param capacity 数组的大小
	 */
	public ArrayStack(int capacity) {
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
			System.out.println("栈溢出！");
		} else {
			top++;
			array[top] = data;
		}
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
