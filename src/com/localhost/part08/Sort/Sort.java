package com.localhost.part08.Sort;

public class Sort {

	/**
	 * 冒泡排序(Bubble Sort)
	 * 是一种最简单的排序算法。
	 * 基本思想：迭代地对输入序列中的第一个元素到最后一个元素进行两两比较，当需要时交换两个元素位置。
	 */
	public static void bubbleSort(int[] array) {
		// 获取数组元素个数
		int n = array.length;
		// 这种for循环是为了避免比较已经排好序的数据
		for (int pass = n - 1; pass >= 0; pass--) {
			// 只比较未排序部分
			for (int i = 0; i < pass; i++) {
				// 最大值向后移动
				if (array[i] > array[i + 1]) {
					int temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
				}
			}
		}
	}
	
	/**
	 * 冒泡排序(Bubble Sort)加强版
	 */
	public static void bubbleSortImproved(int[] array) {
		// 获取数组元素个数
		int n = array.length;
		// 标记每一趟排序是否出现数据交换
		boolean swapped = true;
		// 这种for循环是为了避免比较已经排好序的数据
		for (int pass = n - 1; pass >= 0 && swapped; pass--) {
			swapped = false;
			// 只比较未排序部分
			for (int i = 0; i < pass; i++) {
				// 最大值向后移动
				if (array[i] > array[i + 1]) {
					int temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
					// 出现数据交换
					swapped = true;
				}
			}
		}
	}
	
	/**
	 * 选择排序(Selection Sort)
	 * 是一种原地排序(即不需要额外的存储空间)算法，适用于小文件。
	 * 思路：
	 * 寻找序列中最小值
	 * 用当前位置的交换最小值
	 * 对所有元素重复上述过程，直到序列排序完成
	 */
	public static void selectionSort(int[] array) {
		// 获取数组元素个数
		int n = array.length;
		// 最小值的下标
		int min ;
		// 临时变量
		int temp;
		// 寻找最小值位置并与当前位置交换
		for (int i = 0; i < n; i++) {
			min = i;
			// 寻找最小值位置
			for (int j = i + 1; j < n; j++) {
				if (array[j] < array[min]) {
					min = j;
				}
			}
			// 交换元素
			temp = array[i];
			array[i] = array[min];
			array[min] = temp;
		}
	}
	
	/**
	 * 插入排序(Selection Sort)
	 * 是一种简单且高效的比较排序算法。典型的原地排序
	 * 思路：每次从输入数据中移除一个元素并将其插入已排序序列的正确位置，直到所有输入输入元素都插入有序序列中。
	 * @param array
	 */
	public static void insertionSort(int[] array) {
		// 获取数组元素个数
		int n = array.length;
		// 临时变量
		int temp;
		// 有序序列的循环变量
		int j;
		for (int i = 1; i < n; i++) {
			// 获取输入数据
			temp = array[i];
			j = i;
			// 排序
			while (j >= 1 && (array[j - 1] > temp)) {
				array[j] = array[j - 1];
				j--;
			}
			// 将数据放入有序序列
			array[j] = temp; 
		}
	}
	
	
	
}

















