package com.localhost.test;

import java.util.Scanner;

/*
 * 小明同学要参加一场考试，考试一共有n道题目，小明必须做对至少60%的题目才能通过考试。考试结束后，小明估算出每题做对的概率，p1,p2,...,pn。你能帮他算出他通过考试的概率吗？
 */
public class Test {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// 输入题目数量
		System.out.println("请输入题目总数量，取值为1-100");
		int n = scanner.nextInt();
		
		// 需要作对的题目数
		int m = (int) Math.ceil(n * 0.6);
		
		// 计算概率
		int sum = 0;
		int a = getFactorialSum(n);
		for (int i = n; i >= m; i--) {
			int b = getFactorialSum(i) * getFactorialSum(n - i);
			sum += a / b;
		}
		
		double p = sum * Math.pow(0.5, 4);
		
		// 输出结果
		System.out.println(String.format("%.5f", p));
	}

	//通过递归实现阶乘  
	private static int getFactorialSum (int num) {   
	    if(num == 1 || num == 0){     
	      return 1;    
	    }else{     
	      return getFactorialSum(num-1) * num;   
	    }  
	}  
}
