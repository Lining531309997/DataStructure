package com.localhost.part01.recursive;

import java.util.Scanner;

/**
 * 题目：生成所有n位的二进制串
 * 例如：输入1
 * 	         输出 0、1
 * 	         输入2
 * 	         输出00、01、10、11
 */
public class Back_Track {
	
	// 大小为n的数组，存储二进制串
	public static int[] A;
	
	// 要生成的二进制串的位数
	public static int x;
	
	public static void main(String[] args) {
		System.out.println("功能：生成所有n位的二进制串");
		
		// 获取输入的位数
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入一个整数n:");
		int n = scanner.nextInt();
		
		// 初始化成员变量
		x = n;
		A = new int[n];
		
		// 生成二进制串
		System.out.println("结果如下所示：");
		binary(n);
	}

	/**
	 * 生成所有n位的二进制串
	 * @param n 二进制串的位数
	 */
	public static void binary(int n) {
		if (n < 1) {
			print();
		} else {
			A[n-1] = 0;
			binary(n -1);
			A[n-1] = 1;
			binary(n -1);
		}
	}
	
	/**
	 * 打印数组内容
	 */
	public static void print() {
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i]);
			// 格式化输出，方便查看
			if ((i+1) % x == 0) {
				System.out.println();
			}
		}
	}
}

