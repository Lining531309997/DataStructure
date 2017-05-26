package com.localhost.part11.String;

import org.junit.Test;

public class StringProblemsTest {

	@Test
	public void reversingStringTest() {
		String string = "Internet";
		System.out.println(StringProblems.reversingString1(string));
		System.out.println(StringProblems.reversingString2(string));
		System.out.println(StringProblems.reversingString3(string));
	}
	
	@Test
	public void reversingSentenceTest() {
		String string = "This is a Boy";
		System.out.println(StringProblems.reversingSentence(string));
	}
	
	@Test
	public void removeAdjacentPairsTest() {
		String string = "ABCCBCBA";
		StringProblems.removeAdjacentPairs(string);
	}
}
