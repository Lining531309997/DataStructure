package com.localhost.part11.String;

import org.junit.Test;

public class StringMatchTest {

	/**
	 * 所有方法只是简单匹配了第一个符合条件的字符串，并没有查找出TXT中所有的符合条件的字符串
	 */
	
	private String txt = "Internet";
	private String part = "net";
	
	@Test
	public void bruteForceStringMatchTest() {
		int index = StringMatch.bruteForceStringMatch(txt, part);
		System.out.println(txt.substring(index, index + part.length()));
	}
	
	@Test
	public void robinKarpTest() {
		int index = StringMatch.robinKarp(txt, part);
		System.out.println(txt.substring(index, index + part.length()));
	}
}
