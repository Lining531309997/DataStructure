package com.localhost.part11.String;

public class StringMatch {

	/**
	 * 蛮力法
	 * 思路：
	 * 检查text中每一个可能位置，检查pattern是否匹配。由于text的长度为n，所以有n-m+1个可选的位置来比较。
	 * 因为pattern的长度为m，所以TXT中最后的m-1个位置无需检查。
	 * @param text 文本
	 * @param pattern 需要匹配的字符串
	 * @return 匹配字符串在TXT中起始索引
	 */
	public static int bruteForceStringMatch(String text, String pattern) {
		// 获取文本以及字符串的长度
		int n = text.length();
		int m = pattern.length();
		// 字符串转换成字符数组
		char[] t = text.toCharArray();
		char[] p = pattern .toCharArray();
		// 精确匹配
		// 只检查TXT的前n-m+1个可选的位置
		for (int i = 0; i < (n - m + 1); i++) {
			int j = 0;
			while ((j < m) && (p[j] == t[j + i])) {
				j++;
			}
			// 如果匹配则返回TXT中匹配的起始索引
			if (j == m) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Robin-Karp算法
	 * 思路：
	 * 使用散列技术代替文本text中每个可能的位置进行检查的方法，即仅在pattern的散列值和text中m个字符的散列值相等时才检查具体字符是否相同。
	 * 举例：
	 * 假设字符串的字符都是整数，即text中的所有字符都属于{0,1,2,3,4,5,6,7,8,9}。由于所有字符都是整数，可以把m个连续的字符串看做十进制数。
	 * 例如字符串"61815"对应的十进制数是61815。
	 * 按照上面的假设，pattern可以看做一个十进制，假设pattern对应的十进制数为p
	 * p = pattern[m-1] + 10*(pattern[m-2] + 10*(pattern[m-3] + ... + 10*(pattern[1] + 10*pattern[0])...))
	 * 代码实现，如下：
	 * value = 0;
	 * for(int i = 0; i < m; i++) {
	 * 		value = value * 10;
	 * 		value = value + pattern[i];
	 * }
	 * text中m个字符对应的十进制为t(i)(i=0,1,2...n-m+1)
	 * t(i+1) = 10 * (t(i) - 10^(m-1)*text[i+1]) + text(i+m+1)
	 * 例如，如果text="123456",m=3,t(0)=123,t(1)=10*(123-100*1)+4=234
	 * 逐步解释如下：
	 * 第一步：移除第一个数字，123-100*1=23
	 * 第二步：乘以10来移动第一步的结果，23*10=230
	 * 第三步：加上最后一个数字，230+4=234
	 * 然后算法对t(i)与p进行比较。当t(i)=p时，表示在text中找到子串pattern
	 * 
	 * 参考：http://www.ituring.com.cn/article/1759
	 * 	   http://www.geeksforgeeks.org/searching-for-patterns-set-3-rabin-karp-algorithm/
	 */
	private static int d = 256;				
	private static int hashLength = 101;	// hash表的长度，一般是个素数
	public static int robinKarp(String text, String pattern) {
		// 获取文本以及字符串的长度
		int n = text.length();
		int m = pattern.length();
		// 字符串转换成字符数组
		char[] txt = text.toCharArray();
		char[] pat = pattern.toCharArray();
		
		// 获取文本以及字符串的hash值
		int pHash = 0;
		int tHash = 0;
		// 计算pattern的hash值和text的前m个元素的hash值
		for (int i = 0; i < m; i++) {
			pHash = (d * pHash + pat[i]) % hashLength;
			tHash = (d * tHash + txt[i]) % hashLength;
		}
	 
		int h = 1;
	    // The value of h would be "pow(d, m-1) % hashLength"
	    for (int i = 0; i < m - 1; i++) {
	    	h = (h * d) % hashLength;
	    }
		
		// 精确匹配
		// 只检查TXT的前n-m+1个可选的位置
		int j = 0;
		for (int i = 0; i <= (n - m); i++) {
			// 先检查hash值是否相等
			if (pHash == tHash) {
				// hash值相等进而判断字符是否相等
				for (j = 0; j < m; j++) {
					if (pat[j] != txt[i + j]) {
						break;
					}
				}
				// 如果匹配则返回text中匹配的起始索引
				if (j == m) {
					return i;
				}
			}
			if (i < (n - m)) {
				tHash = (d * (tHash - txt[i] * h) + txt[i + m]) % hashLength;
				// We might get negative value of t, converting it to positive
				if (tHash < 0) {
					tHash += hashLength;
				}
			}
		}
		return -1;
	}
	
	
}































