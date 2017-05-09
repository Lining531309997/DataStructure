package com.localhost.test;

import java.util.Iterator;
import java.util.Scanner;
/*
 * 异或运算是常见的二进制运算，给出两个n位二进制数a，b。a异或b的运算依次考虑二进制的每一位，若这一位相同，那么这一位的异或结果就是0，不同就是1。
	例如a=1100, b=0100。执行a异或b的运算，a的最高位是1，b的最高位是0，两个数字不同所以最高位异或结果是1；a和b次高位都是1，所以次高位异或为0；
	最后两位它们都是0，所以异或结果也都是0。那么a异或b的答案就是1000。
	现在输入两个n位二进制数，输出它们异或结果的十进制答案。上述样例中异或的二进制结果为1000，转化成十进制就是8。
 */

public class MyTest {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		// 获取输入二进制的位数
		System.out.println("请输入二进制的位数!");
		int size = scanner.nextInt();
		
		// 获取输入a并检查位数
		System.out.println("请输入第一个二进制!");
		String a = "";
		byte[] aByte = new byte[size];
		while (scanner.hasNext()) {
			a = scanner.nextLine();
			if (a.length() != size ) {
				System.out.println("对不起！输入的二进制位数不对！请重新输入！");
				continue;
			} else {
				aByte = a.getBytes();
				break;
			}
		}
		
		// 获取输入b并检查位数
		System.out.println("请输入第二个二进制!");
		String b = "";
		byte[] bByte = new byte[size];
		while (scanner.hasNext()) {
			b = scanner.nextLine();
			if (b.length() != size) {
				System.out.println("对不起！输入的二进制位数不对！请重新输入！");
				continue;
			} else {
				bByte = b.getBytes();
				break;
			}
		}
		
		// 异或
		StringBuffer sb = new StringBuffer();
		if (aByte.length > 0 && bByte.length > 0) {
			for (int i = 0; i < size; i++) {
				if (aByte[i] == bByte[i]) {
					sb.append("0");
				} else {
					sb.append("1");
				}
			}
		}
		
		// 输出最终结果
		System.out.println(Integer.valueOf(sb.toString(), 2));
	}
}
