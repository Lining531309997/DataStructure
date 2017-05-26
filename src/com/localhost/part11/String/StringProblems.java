package com.localhost.part11.String;

import java.util.StringTokenizer;

public class StringProblems {

	/**
	 * 题目：反转字符串
	 */
	
	/**
	 * 通过字符数组反转
	 * @param string
	 * @return
	 */
	public static String reversingString1(String string) {
		// 字符串开始索引
		int start = 0;
		// 字符串结束索引
		int end = string.length() - 1;
		// 将字符串转换成字符数组
		char[] str = string.toCharArray();
		// 声明临时变量
		char temp;
		// 字符交换
		for (start = 0; start <= end / 2; start++) {
			temp = str[start];
			str[start] = str[end - start];
			str[end - start] = temp;
		}
		// 生成字符创
		return new String(str);
	}
	
	/**
	 * 字符串反转
	 * @param string
	 * @return
	 */
	public static String reversingString2(String string) {
		// 获取字符串长度
		int length = string.length() - 1;
		StringBuilder sb = new StringBuilder();
		for (int i = length; i >= 0; i--) {
			sb.append(string.charAt(i));
		}
		return sb.toString();
	}
	
	/**
	 * 使用XOR逻辑运算实现字符串反转
	 * @param string
	 * @return
	 */
	public static String reversingString3(String string) {
		// 字符串开始索引
		int start = 0;
		// 字符串结束索引
		int end = string.length() - 1;
		// 将字符串转换成字符数组
		char[] str = string.toCharArray();
		while (start < end) {
			str[start] ^= str[end]; 
			str[end] ^= str[start]; 
			str[start] ^= str[end]; 
			start++;
			end--;
					
		}
		return new String(str);
	}
	
	/**
	 * 题目：反转给定句子中单词
	 * 例如 输入："This is a Boy", 输出："Boy a is This"
	 * @param string
	 * @return
	 */
	public static String reversingSentence(String string) {
		StringTokenizer st = new StringTokenizer(string);
		String result = "";
		while (st.hasMoreTokens()) {
			result = st.nextToken() + " " + result;
		}
		return result;
	}
	
	/**
	 * 题目：删除给定字符串中相同的相邻字符
	 * 例如：ABCCBCBA --> ABBCBA --> ACBA
	 * 思路：
	 * 检查字符串中是否存在相同的相邻字符对。如果存在，则删除字符对。然后检查下一个和前一个字符。
	 * 持续该过程直到字符串串首或串尾或找不到相同的相邻字符对为止。
	 * @param string
	 */
	public static void removeAdjacentPairs(String string) {
		// 字符串长度
		int length = string.length();
		// 将字符串转换成字符数组
		char[] str = string.toCharArray();
		// 初始化索引变量
		int j = 0;
		for (int i = 1; i < length; i++) {
			// 删除相同的相邻字符对
			while ((str[i] == str[j]) && (j >= 0)) {
				i++;
				j--;
			}
			str[++j] = str[i];
		}
		// 输出结果
		for (int i = 0; i <= j; i++) {
			System.out.print(str[i] + " ");
		}
	}
}
