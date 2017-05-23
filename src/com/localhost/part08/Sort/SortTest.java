package com.localhost.part08.Sort;

import org.junit.Test;


public class SortTest {

	private int[] array = {7, 5, 3, 11, 8, 2, 10, 9};
	
	@Test
	public void BubbleSortTest() {
		
		Sort.bubbleSort(array);
		for (int i : array) {
			System.out.print(i + " ");
		}
		
		Sort.bubbleSortImproved(array);
		for (int i : array) {
			System.out.print(i + " ");
		}
	}
	
	@Test
	public void SelectionortTest() {
		
		Sort.selectionSort(array);
		for (int i : array) {
			System.out.print(i + " ");
		}
	}
	
	@Test
	public void InsertionSortTest() {
		
		Sort.insertionSort(array);
		for (int i : array) {
			System.out.print(i + " ");
		}
	}
	
	@Test
	public void ShellSortTest() {
		
		Sort.shellSort(array);
		for (int i : array) {
			System.out.print(i + " ");
		}
	}
	
	@Test
	public void MergeSortTest() {
		
		int left = 0;
		int right = array.length - 1;
		Sort.mergeSort(array, left, right);
		for (int i : array) {
			System.out.print(i + " ");
		}
	}
	
	
	@Test
	public void QuickSortTest() {
		
		int low = 0;
		int high = array.length - 1;
		Sort.quickSort(array, low, high);
		for (int i : array) {
			System.out.print(i + " ");
		}
	}
	
	
	
	
}
