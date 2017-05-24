package com.localhost.part09.Search;

public class Search {

	/**
	 * 无序线性查找(UnSorted Linear Search)
	 * 假设给定的一个数组，其元素的排列顺序是未知的，即数组中的元素是无序的。
	 * @param array 给定的无序数组
	 * @param data 需要查找的元素
	 * @return 该元素在数组中的下标
	 */
	public static int unSortedLinearSearch(int[] array, int data) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == data) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 无序线性查找(Sorted Linear Search)
	 * 假设给定的一个数组，其元素的排列顺序是已知的，即数组中的元素是有序的。
	 * @param array 给定的有序数组
	 * @param data 需要查找的元素
	 * @return 该元素在数组中的下标
	 */
	public static int sortedLinearSearch(int[] array, int data) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == data) {
				return i;
			} else if(array[i] > data) {
				return -1;
			}
		}
		return -1;
	}
	
	/**
	 * 非递归二分查找算法
	 * @param array 给定的有序数组
	 * @param data 需要查找的元素
	 * @return 该元素在数组中的下标
	 */
	public static int binarySearchIterative(int[] array, int data) {
		int mid = 0;
		int low = 0;
		int high = array.length - 1;
		while (low < high) {
			// 避免溢出
			mid = low + (high - low) / 2;
			if (array[mid] == data) {
				return mid;
			} else if (array[mid] > data) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}
	
	/**
	 * 二分查找算法(递归)
	 * @param array 给定的有序数组
	 * @param low 数组的起始位置
	 * @param high 数组的结束位置
	 * @param data 需要查找的元素
	 * @return 该元素在数组中的下标
	 */
	public static int binarySearchRecursive(int[] array, int low, int high, int data) {
		// 避免溢出
		int mid = low + (high - low) / 2;
		if (array[mid] == data) {
			return mid;
		} else if (array[mid] > data) {
			binarySearchRecursive(array, low, mid - 1, data);
		} else {
			binarySearchRecursive(array, mid + 1, high, data);
		}
		return -1;
	}
}
