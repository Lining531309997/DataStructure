package com.localhost.test;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.localhost.part03.stack.LinkedListStack;
public class AlibabaTest {

/*
 一个对于一个单行的逆波兰表达式，由如下元素构成：
数字：十进制数字字符构成的正整数，比如 223
运算符：支持三种运算符^+和*，分别代表自增，加法和乘法
分隔符：一个或者多个空格
例如下面的字符串就是个逆波兰表达式
2 3  4 * ^ 5 +
逆波兰表达式在一个基于栈的虚拟机中求解，虚拟机的栈能保存16个整数，虚拟机从左向右扫描表达式，遇到整数就压栈，遇到表达式则从栈顶弹出若干个整数进行计算，计算结果重新压回栈中。
其中运算符^从栈顶弹出一个整数，增加1之后压栈；运算符+和*从栈顶弹出两个整数，分别做相加和相乘运算后压栈。如果遇到运算符的时候，栈内没有足够的整数，称为下溢出，返回-1；
把整数压栈的时候，如果栈没有足够的空间，称为上溢出，返回-2；如果整个计算过程中没有发生溢出，在整个表达式求解完成后，返回栈顶的整数。
 */
	
    public static void main(String[] args) {
    	
        ArrayList<Integer> inputs = new ArrayList<Integer>();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        if(line != null && !line.isEmpty()) {
            int res = resolve(line.trim());
            System.out.println(String.valueOf(res));
        }
    }

    // write your code here
    public static int resolve(String expr) {
    	
    	// 获取字符串集合
    	StringTokenizer st = new StringTokenizer(expr);
    	
    	// 创建栈来存储操作数
    	LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
    	
    	// 遍历字符串集合
    	while (st.hasMoreTokens()) {
    		// 获取一个字符串
    		String str = st.nextToken();
    		
    		switch (str) {
			case "*":
				// 双目操作需要保证数据栈至少有两个操作数
				if (stack.size() >= 2) {
					int oper1 = stack.pop();
					int oper2 = stack.pop();
					stack.push(oper1 * oper2);
				} else {
					// 栈内没有足够的整数，称为下溢出，返回-1
					return -1;
				}
				break;
			case "+":
				// 双目操作需要保证数据栈至少有两个操作数
				if (stack.size() >= 2) {
					int oper1 = stack.pop();
					int oper2 = stack.pop();
					stack.push(oper1 + oper2);
				} else {
					// 栈内没有足够的整数，称为下溢出，返回-1
					return -1;
				}
				break;
			case "^":
				if (!stack.isEmpty()) {
					int oper = stack.pop();
					oper += 1;
					stack.push(oper);
				} else {
					// 栈内没有足够的整数，称为下溢出，返回-1
					return -1;
				}
				break;
			default:
				// 如果是操作数，直接入栈
				stack.push(Integer.valueOf(str));
				break;
			}
		}
    	// 返回栈顶元素
		return stack.pop();
       
    }
    
    /**
     * 阿里的消息中间件，负责淘宝天猫支付宝等各个系统的消息中转，削峰填谷及架构的解耦。在每年的双11中承载了数万亿的消息。消息中间件中有Pub/Sub两个角色，Pub方发送消息到消息中间件，消息中间件再根据订阅关系投递给订阅方。例如用户成功购买了一个物品，交易平台（Pub）会发送一条交易完成（trade-done）的消息，购物车平台（Sub）订阅到这个消息后，会将用户的购物车物品删除掉。这里涉及一个问题，交易平台会发送各种类型的消息，消息中间件是如何准确的将相应的消息投递给购物车平台的？所使用的就是消息中间件的过滤功能，过滤的方式有很多种，表达式判断，正则匹配等。假设让你来实现一个过滤功能，来匹配订阅关系是否符合，给定如下条件：
‘?’ 匹配一个字符
‘*’ 匹配任意连串的字符
如上面的例子中，购物车平台订阅方式是pattern=*trade-done，那么
filter(100-trade-done, pattern) = 1,
filter(200-trade-done, pattern) = 1,
filter(200-paid-done, pattern) = 0
 
如果pattern=200-?*-done :
filter(100-trade-done, pattern) = 0, 
filter(200-trade-done, pattern) = 1,
filter(200-paid-done, pattern) = 1
 
如果pattern=1*trade*done :
filter(100-trade-done, pattern) = 1,
filter(200-trade-done, pattern) = 0,
filter(200-paid-done, pattern) = 0
     */
}
