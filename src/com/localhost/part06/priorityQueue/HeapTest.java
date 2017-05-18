package com.localhost.part06.priorityQueue;

import org.junit.Test;

public class HeapTest {

	@Test
	public void test() {
		// 创建数组和堆
		int[] array = {17, 1, 4, 2, 6, 5, 13};
		Heap heap = new Heap(array.length,"big");
		
		// 数组建堆
		heap.buildHeap(heap, array);
		
		/*
		 * 测试：堆中的元素
		 * 结果：
		 * 堆中元素：17 6 13 2 1 5 4 
		 */
		print(heap);
		
		/*
		 * 测试：根据位置获取双亲结点位置
		 * 结果：
		 * 第5个位置的双亲结点位置：2
		 */
		int index = 5;
		System.out.println("第" + index + "个位置的双亲结点位置：" + heap.getParentByIndex(index));
		
		/*
		 * 测试：插入新元素
		 * 结果：
		 * 堆中元素：17 11 13 6 1 5 4 2 0 0 0 0 0 0 
		 */
		int data = 11;
		heap.insert(data);
		print(heap);
		
		/*
		 * 测试：堆排序
		 * 结果：
		 * 堆排序：17 13 6 5 4 2 1 
		 */
		array = heap.heapSort(array);
		System.out.print("堆排序：");
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();

	}
	
	/**
	 * 题目：请说明有N个元素的堆的高度为logN
	 * 解答：堆是完全二叉树。除了底层外，其他所有层都是满的。
	 * 因此堆至少有2^h个元素，最多有2^(h+1)-1个元素，即2^h <= N <= 2^(h+1)-1
	 * 这表明h <= logN <= (h+1)；由于h为整数，所以h=logN
	 */
	
	/*
	 * 测试：给定一个最大堆，查找最小元素
	 * 结果：
	 * 最大堆中的最小元素：1
	 */
	@Test
	public void findMinInMaxHeapTest() {
		// 创建数组和堆
		int[] array = {17, 1, 4, 2, 6, 5, 13};
		Heap heap = new Heap(array.length,"big");
		
		// 数组建堆
		heap.buildHeap(heap, array);
		
		int min = findMinInMaxHeap(heap);
		System.out.println("最大堆中的最小元素：" + min);
	}
	
	/**
	 * 题目：给定一个最大堆，查找最小元素
	 * 思路：
	 * 在最大堆中，最小元素只可能是叶子节点。
	 * 因为最后一个节点(x)的双亲节点((x-1)/2)的下一个节点为第一个叶子节点((x-1)/2+1)
	 * 其中x = size - 1
	 * @param heap 最大堆
	 * @return 堆中最小元素
	 */
	public int findMinInMaxHeap(Heap heap) {
		// 从左至右，第一个叶子节点
		int index = heap.getSize() / 2;
		// 假设第一个叶子节点为最小值
		int min = heap.getArray()[index];
		// 遍历叶子节点
		for (int i = index + 1; i < heap.getSize(); i++) {
			if (heap.getArray()[i] < min) {
				min = heap.getArray()[i];
			}
		}
		return min;
	}
	
	/**
	 * 打印堆中元素
	 * @param heap
	 */
	public void print(Heap heap) {
		System.out.print("堆中元素：");
		for (int i : heap.getArray()) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}

