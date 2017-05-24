package com.localhost.part09.Search;

import org.junit.Test;

public class SearchTest {

	@Test
	public void unSortedLinearSearchTest() {
		int data = 8;
		int[] array = {1,2,4,6,3,7,8};
		int index = Search.unSortedLinearSearch(array, data);
		System.out.println(data + "在数组中的下标为：" + index);
	}
	
	@Test
	public void sortedLinearSearchTest() {
		int data = 5;
		int[] array = {1,2,3,4,6,7,8};
		int index = Search.sortedLinearSearch(array, data);
		System.out.println(data + "在数组中的下标为：" + index);
	}
	
	@Test
	public void binarySearchIterativeTest() {
		int data = 3;
		int[] array = {1,2,3,4,6,7,8};
		int index = Search.unSortedLinearSearch(array, data);
		System.out.println(data + "在数组中的下标为：" + index);
	}
	
	@Test
	public void binarySearchRecursiveTest() {
		int data = 5;
		int[] array = {1,2,3,4,6,7,8};
		int index = Search.sortedLinearSearch(array, data);
		System.out.println(data + "在数组中的下标为：" + index);
	}
	
	@Test
	public void test() {
		int[] array = {1,2,4,6,3,7,8};
		int n = array.length + 1;
		int X = 0;
		int Y = 0;
		for (int i = 0; i < array.length; i++) {
			X ^= array[i];
		}
		for (int i = 1; i <= n; i++) {
			Y ^= i;
		}
		System.out.println("数组中缺失的元素：" + (X^Y));
	}
}
