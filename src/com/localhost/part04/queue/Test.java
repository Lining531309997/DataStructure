package com.localhost.part04.queue;

import com.localhost.part03.stack.LinkedListStack;

public class Test {

	public static void main(String[] args) {
		reverseQueueFirstKElementsTest();
		stackWithTwoQueuesTest();
	}
	
	/**
	 * 逆置队列中前k个元素测试类
	 * 结果：
	 * 逆置前队列元素：10 20 30 40 50 60 70 80 90 
	 * 逆置后队列元素：40 30 20 10 50 60 70 80 90 
	 */
	public static void reverseQueueFirstKElementsTest() {
		LinkListQueue<Integer> queue = new LinkListQueue<Integer>();
		queue.enQueue(10);
		queue.enQueue(20);
		queue.enQueue(30);
		queue.enQueue(40);
		queue.enQueue(50);
		queue.enQueue(60);
		queue.enQueue(70);
		queue.enQueue(80);
		queue.enQueue(90);
		
		int size = queue.getQueueSize();
		int count = 0;
		System.out.print("逆置前队列元素：");
		while (count < size) {
			int temp = queue.deQueue();
			queue.enQueue(temp);
			System.out.print(temp + " ");
			count++;
		}
		
		System.out.println();
		
		try {
			LinkListQueue<Integer> result = reverseQueueFirstKElements(4, queue);
			System.out.print("逆置后队列元素：");
			while (!result.isEmpty()) {
				System.out.print(result.deQueue() + " ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 题目：给定一个整数k和一个整数队列，如何把队列中前k个元素逆置，其余元素保持不变？
	 * 例如：如果k为4，队列元素为[10,20,30,40,50,60,70,80,90],则输出[40,30,20,10,50,60,70,80,90]
	 * 
	 * 逆置队列中前k个元素
	 * @param k 逆置元素的个数
	 * @param queue 整数队列
	 * @throws Exception 
	 */
	public static LinkListQueue<Integer> reverseQueueFirstKElements(int k, LinkListQueue<Integer> queue) throws Exception {
		if (queue == null || k > queue.getQueueSize()) {
			throw new Exception("队列为空或K值太大！");
		} else {
			LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
			// 将队列前k个元素压入栈中
			for (int i = 0; i < k; i++) {
				stack.push(queue.deQueue());
			}
			// 将栈中k个元素出栈，入队到队列
			while (!stack.isEmpty()) {
				queue.enQueue(stack.pop());
			}
			// 将队列中剩余元素逆置到队尾
			for (int i = 0; i < queue.getQueueSize() - k; i++) {
				queue.enQueue(queue.deQueue());
			}
			return queue;
		}
		 
	}
	
	/**
	 * 测试类
	 */
	public static void checkStackPairwiseOrderTest() {
		LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
		stack.push(4);
		stack.push(5);
		stack.push(-2);
		stack.push(-3);
		stack.push(11);
		stack.push(10);
		stack.push(5);
		stack.push(6);
		stack.push(2);
		System.out.println(checkStackPairwiseOrder(stack)); // true
	}
	
	/**
	 * 题目：给定一个整数栈，如何检查栈中每对相邻数字是否是连续的。每对数字的值可以是递增或递减的。如果栈中的元素个数是奇数，那么组队时忽略栈顶元素。
	 * 例如，假设栈中元素为[4,5,-2,-3,11,10,5,6,20]，那么该算法应该忽略栈顶元素20，并输出true
	 * 因为每对(4,5)、(-2,-3)、(11,10)、(5,6)都是连续数字
	 * 
	 * 给定一个整数栈，如何检查栈中每对相邻数字是否是连续的
	 * 注意：要保证栈中元素顺序和个数不变
	 * @param stack 给定的整数栈
	 * @return true 是连续的 false 不是连续的
	 */
	public static boolean checkStackPairwiseOrder(LinkedListStack<Integer> stack) {
		LinkListQueue<Integer> queue = new LinkListQueue<Integer>();
		boolean pairwiseOrdered = true;
		// 出栈 --> 入队  --> 队列元素(队首-->队尾):[20,6,5,10,11,-3,-2,5,4]
		while (!stack.isEmpty()) {
			queue.enQueue(stack.pop());
		}
		// 出队 --> 入栈  --> 栈中元素(栈底-->栈顶):[20,6,5,10,11,-3,-2,5,4]
		while (!queue.isEmpty()) {
			stack.push(queue.deQueue());
		}
		// 出栈 --> 入队  --> 队列元素(队首-->队尾):[4,5,-2,-3,11,10,5,6,20]
		while (!stack.isEmpty()) {
			int m = stack.pop();
			queue.enQueue(m);
			if (!stack.isEmpty()) {
				int n = stack.pop();
				queue.enQueue(n);
				if (Math.abs(m - n) != 1) {
					pairwiseOrdered = false;
				}
			}
		}
		// 出队 --> 入栈  --> 栈中元素(栈底-->栈顶):[4,5,-2,-3,11,10,5,6,20]
		while (!queue.isEmpty()) {
			stack.push(queue.deQueue());
		}
		return pairwiseOrdered;
	}
	
	/**
	 * 队列逆置测试方法
	 */
	public static void reverseQueueTest() {
		// 创建原始队列
		LinkListQueue<Integer> queue = new LinkListQueue<Integer>();
		queue.enQueue(1);
		queue.enQueue(9);
		queue.enQueue(3);
		queue.enQueue(7);
		
		// 创建逆置队列实例
		QueueReversal<Integer> qr = new QueueReversal<Integer>();
		// 创建结果队列
		LinkListQueue<Integer> result = new LinkListQueue<Integer>();
		// 逆置队列
		result = qr.reverseQueue(queue);
		
		// 输出结果
		while (!result.isEmpty()) {
			System.out.print(result.deQueue() + " ");
		}
	}
	
	/**
	 * 使用两个栈实现队列测试方法
	 */
	public static void queueWithTwoStacksTest() {
		QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks<Integer>();
		
		// 结果为true
		System.out.println(queue.isEmpty());
		
		queue.enQueue(9);
		queue.enQueue(8);
		queue.enQueue(7);
		queue.deQueue();
		queue.enQueue(6);
		queue.enQueue(5);
		
		// 结果为false
		System.out.println(queue.isEmpty());
		
		// 结果为 8 7 6 5 
		while (!queue.isEmpty()) {
			System.out.print(queue.deQueue() + " ");
		}
	}
	
	/**
	 * 使用两个队列来实现一个栈的测试类
	 */
	public static void stackWithTwoQueuesTest() {
		StackWithTwoQueues<Integer> stack = new StackWithTwoQueues<Integer>();
		
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}
} 

/**
 * 题目：如何使用两个队列来实现一个栈
 * 思路：假设用来实现栈的两个队列分别为queue1和queue2，确保总有一个队列是空的
 * 入栈算法：
 * 如果queue1为空，则对queue2执行入队操作
 * 否则就对queue1执行如对操作
 * 出栈算法：
 * 从有元素的队列移动n-1个元素到另一个队列，删除当前队列的最后一个元素，即可完成出栈
 * 如果queue1非空，那么从queue1移动n-1个元素到queue2中，然后对queue1的最后一个元素执行出队操作；
 * 如果queue2非空，那么从queue2移动n-1个元素到queue1中，然后对queue2的最后一个元素执行出队操作；
 *
 * 使用两个队列来实现一个栈
 * @param <AnyType>
 */
class StackWithTwoQueues<AnyType> {
	
	LinkListQueue<AnyType> queue1;
	LinkListQueue<AnyType> queue2;
	
	/**
	 * 构造方法
	 */
	public StackWithTwoQueues() {
		queue1 = new LinkListQueue<AnyType>();
		queue2 = new LinkListQueue<AnyType>();
	}
	
	/**
	 * 判断栈是否为空
	 * @return true 是空栈	false 不是空栈
	 */
	public boolean isEmpty() {
		return (queue1.isEmpty() && queue2.isEmpty());
	}
	
	/**
	 * 入栈
	 * 向非空的队列添加数据
	 * @param data 入栈元素
	 */
	public void push(AnyType data) {
		if (queue1.isEmpty()) {
			queue2.enQueue(data);
		} else {
			queue1.enQueue(data);
		}
	}
	
	/**
	 * 出栈
	 * @return 出栈元素
	 */
	public AnyType pop() {
		int size;
		int count;
		if (queue1.isEmpty()) {
			count = 0;
			size = queue2.getQueueSize();
			while (count < size - 1) {
				queue1.enQueue(queue2.deQueue());
				count++;
			}
			return queue2.deQueue();
		} else {
			count = 0;
			size = queue1.getQueueSize();
			while (count < size - 1) {
				queue2.enQueue(queue1.deQueue());
				count++;
			}
			return queue1.deQueue();
		}
	}
}

/**
 * 题目：如何使用两个栈实现队列
 * 思路：假设用来实现队列的两个栈分别为stack1和stack2，保证两个栈总有一个为空
 * 入队算法：
 * 如果stack2不为空，先将stack2中的元素弹出并压入stack1；
 * 将元素压入stack1
 * 出队算法：
 * 如果stack2不为空，那么栈顶元素就是队首元素，直接弹出栈顶元素即可；
 * 如果stack2为空，那么先将stack1中的元素弹出并压入stack2，弹出stack2的栈顶元素；
 * 
 * 使用两个栈实现队列
 * @param <AnyType> 元素类型
 */
class QueueWithTwoStacks<AnyType> {
	
	LinkedListStack<AnyType> stack1;
	LinkedListStack<AnyType> stack2;
	
	/**
	 * 构造方法
	 */
	public QueueWithTwoStacks() {
		stack1 = new LinkedListStack<AnyType>();
		stack2 = new LinkedListStack<AnyType>();
	}
	
	/**
	 * 判断是否为空
	 * @return true 为空 false 不为空
	 */
	public boolean isEmpty() {
		if (stack1.isEmpty() && stack2.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 入队
	 * @param data 入队元素值
	 */
	public void enQueue(AnyType data) {
		// 如果stack2中有值，先将值弹出压入stack1
		while (!stack2.isEmpty()) {
			stack1.push(stack2.pop());
		}
		// 将元素值压入stack1
		stack1.push(data);
	}
	
	/**
	 * 出队
	 * @return 出队元素
	 */
	public AnyType deQueue() {
		// 如果stack2不为空，说明元素都在stack2中，此时stack2的栈顶元素就是队首元素
		if (!stack2.isEmpty()) {
			return stack2.pop();
		} else {
			// 如果stack2为空，说明元素都在stack1中，所以先将元素从stack1弹出，并压入stack2中
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
			// 此时stack2的栈顶元素就是队首元素
			return stack2.pop();
		}
	}
}

/**
 * 题目：设计一个逆置队列元素的算法。
 * 队列逆置类
 * @param <AnyType> 逆置的类型
 */
class QueueReversal<AnyType> {
	
	/**
	 * 构造方法
	 */
	public QueueReversal() {}
	
	/**
	 * 逆置队列元素
	 * @param queue 原始队列
	 * @return 逆置后的队列
	 */
	public LinkListQueue<AnyType> reverseQueue(LinkListQueue<AnyType> queue) {
		LinkedListStack<AnyType> stack = new LinkedListStack<AnyType>();
		// 出队-->入栈
		while (!queue.isEmpty()) {
			stack.push(queue.deQueue());
		}
		// 出栈-->入队
		while (!stack.isEmpty()) {
			queue.enQueue(stack.pop());
		}
		return queue;
	}
	
} 