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
}
