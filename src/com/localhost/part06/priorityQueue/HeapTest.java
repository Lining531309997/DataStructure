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

