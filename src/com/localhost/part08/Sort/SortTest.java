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
	public void InsertionortTest() {
		
		Sort.insertionSort(array);
		for (int i : array) {
			System.out.print(i + " ");
		}
	}
	
	
	
	
}
