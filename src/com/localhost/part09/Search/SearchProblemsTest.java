package com.localhost.part09.Search;

import org.junit.Test;

public class SearchProblemsTest {

	@Test
	public void checkDuplicatesTest() {
		int[] array = {3, 2, 1, 2, 2, 3};
		SearchProblems.checkDuplicatesBruteForce(array);
		SearchProblems.checkDuplicatesSorting(array);
		SearchProblems.checkDuplicates(array);
	}
	
	@Test
	public void missingNumberTest() {
		int[] array = {1, 2, 4, 6, 3, 7, 8};
		int a = SearchProblems.findMissingNumber1(array);
		int b = SearchProblems.findMissingNumber2(array);
		int c = SearchProblems.findMissingNumber3(array);
		int d = SearchProblems.findMissingNumber4(array);
		System.out.println("A:" + a);
		System.out.println("B:" + b);
		System.out.println("C:" + c);
		System.out.println("D:" + d);
	}
	
	@Test
	public void searchTest() {
		int k = 10;
		int[] array = {1, 2, 4, 6, 3, 7, 8};
		SearchProblems.search(array, k);
	}
	
	@Test
	public void twoElementsWithMinSumTest() {
		int[] array = {1, 60, -10,  70, -80, 85};
		SearchProblems.twoElementsWithMinSum(array);
	}
	
	@Test
	public void dutchNationalFlagTest() {
		int[] array = {12, 34, 45, 9, 8, 90, 3};
		SearchProblems.dutchNationalFlag(array);
		for (int i : array) {
			System.out.print(i + " ");
		}
	}
	
	@Test
	public void dutchFlagTest() {
		int[] array = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
		SearchProblems.dutchFlag(array);
		for (int i : array) {
			System.out.print(i + " ");
		}
	}
}
