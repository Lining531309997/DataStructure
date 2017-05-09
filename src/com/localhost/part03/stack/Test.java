package com.localhost.part03.stack;

import java.util.StringTokenizer;

public class Test {

	public static void main(String[] args) {

//		String string = "asdffdsa";
//		if (isPalindrome(string)) {
//			System.out.println("是回文");
//		} else {
//			System.out.println("不是回文");
//		}
		
//		System.out.println("--------------------");
//		
//		LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
//		stack.push(4);
//		stack.push(3);
//		stack.push(2);
//		
//		StackReversal<Integer> sr = new StackReversal<Integer>();
//		sr.reverseStack(stack);
//		
//		int size = stack.size();
//		for (int i = 0; i < size; i++) {
//			System.out.println("第" + i + "个元素：" + stack.pop());
//		}
		
//		System.out.println("--------------------");
//		LinkedListStack<Integer> sourceStack = new LinkedListStack<Integer>();
//		sourceStack.push(4);
//		sourceStack.push(6);
//		sourceStack.push(2);
//		LinkedListStack<Integer> resultStack= sort(sourceStack);
//		
//		int size1 = resultStack.size();
//		for (int i = 0; i < size1; i++) {
//			System.out.println("第" + i + "个元素：" + resultStack.pop());
//		}
		
//		System.out.println("--------------------");
//		String string = "((A+B)+(C-D)";
//		if (isMatch(string)) {
//			System.out.println("匹配");
//		} else {
//			System.out.println("不匹配");
//		}
		
//		System.out.println("--------------------");
//		String string = "A*B-(C+D)+E";
//		String result = infixToPostfix(string);
//		System.out.println(result);
		
		System.out.println("--------------------");
		String string = "( 1 + 2 ) * ( 5 - 3 )";
		int result = resultOfInfix(string);
		System.out.println(result);
	}
	
	/**
	 * 计算中缀表达式结果
	 * 思路：
	 * 1.创建一个空的操作数栈
	 * 2.创建一个空的操作符栈
	 * 3.遍历每一个字符，并根据情况处理
	 *   如果是数字则直接压入操作数栈
	 *   如果是操作符则判断优先级，并进行处理
	 *   如果是"("直接压入操作符栈
	 *   如果是")"，则从操作符栈中弹出运算符，直到"("出栈；但"("不输出
	 * 4.要保证操作数栈和操作符栈为空
	 * @param infix 中缀表达式
	 * @return 运算结果
	 */
	public static int resultOfInfix(String infix) {
		// 1.获取字符串集合
    	StringTokenizer st = new StringTokenizer(infix);
    	
    	// 2.1 创建栈来存储操作数
    	LinkedListStack<Integer> datas = new LinkedListStack<Integer>();
    	
    	// 2.2 创建栈来存储操作符
    	LinkedListStack<String> opers = new LinkedListStack<String>();
		
    	// 标记运算符优先级
    	boolean priority = false;
    	
    	// 3.遍历字符串集合
    	while (st.hasMoreTokens()) {
    		// 3.1 获取下一个字符串
			String str = st.nextToken();
			boolean flag = false;
			// 3.2 检查字符串
			switch (str) {
			case "(":
			case "+":
			case "-":
			case "*":
			case "/":
				break;
			// 遇到")"时，从栈中弹出运算符，直到"("出栈；但"("不输出
			case ")":
				while (!opers.top().equals("(")) {
					calculate(datas, opers);
				}
				// 将"("弹出
				opers.pop();
				flag = true;
				break;
			default:
				// 如果是操作数则压入数据栈
				datas.push(Integer.valueOf(str));
				flag = true;
				break;
			}
			
			// 3.3 如果对")"和操作数做过处理，则结束当前循环，继续读取下一个字符串
			if (flag) {
				continue;
			}
			
			// 3.4 操作符栈不为空，则判断运算符优先级
			if (!opers.isEmpty()) {
				priority = compare(opers.top().toCharArray()[0], str.toCharArray()[0]);
				// 栈内运算符优先级高
				if (priority) {
					calculate(datas, opers);
				}
			}
			
			// 3.5 运算符入栈
			opers.push(str);
		}
    	
    	// 4.操作符栈不为空，说明存在数据未处理
		while (!opers.isEmpty()) {
			calculate(datas, opers);
		}
		
		// 5.返回运算结果
		return datas.pop();
	}
	
	/**
	 * 运算
	 * @param datas 数据栈
	 * @param opers 操作符栈
	 */
	public static void calculate(LinkedListStack<Integer> datas, LinkedListStack<String> opers) {
		int oper1 = datas.pop();
		int oper2 = datas.pop();
		switch (opers.pop()) {
		case "*":
			datas.push(oper2 * oper1);
			break;
		case "/":
			datas.push(oper2 / oper1);
			break;
		case "+":
			datas.push(oper2 + oper1);
			break;
		case "-":
			datas.push(oper2 - oper1);
			break;
		default:
			break;
		}
	}
	
	/**
	 * 中缀表达式转换成后缀表达式
	 * 思路：
	 * 1.栈中只存储"("和运算符
	 * 2.在遇到")"时，从栈中弹出运算符，直到"("出栈；但"("不输出
	 * 3.在比较运算符优先级时，只要栈顶运算符优先级不低于读取的运算符，就出栈
	 * 4.要保证栈最后为空
	 * @param infix 中缀表达式
	 * @return 后缀表达式
	 */
	public static String infixToPostfix(String infix) {
		
		// 1.将表达式转换成字符数组
		char[] chars = infix.toCharArray();
		// 2.后缀表达式
		StringBuffer postfix = new StringBuffer();
		// 3.创建一个字符栈，栈里面只存储"("和运算符
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		// 4.遍历字符数组
		for (int i = 0; i < chars.length; i++) {
			switch (chars[i]) {
			case '(':
			case '+':
			case '-':
			case '*':
			case '/':
				if (!stack.isEmpty() && compare(stack.top(), chars[i])) {
					postfix.append(stack.pop());
				}
				stack.push(chars[i]);
				break;
			case ')':
				while (stack.top() != '(') {
					postfix.append(stack.pop());
				}
				// 将"("弹出
				stack.pop();
				break;
			default:
				postfix.append(chars[i]);
				break;
			}
		}
		// 5.字符数组遍历完，如果栈不为空，说明栈内还有运算符
		while (!stack.isEmpty()) {
			postfix.append(stack.pop());
		}
		return postfix.toString();
	}
	
	/**
	 * 运算符比较优先级
	 * @param stackSymbol 栈中运算符
	 * @param symbol 读取的运算符
	 * @return true 栈中优先级高 false 读取的运算符优先级高
	 */
	public static boolean compare(char stackSymbol, char symbol) {
		int priorityOfStack = OperatorEnum.priorityOf(stackSymbol);
		int priorityOfSymbol = OperatorEnum.priorityOf(symbol);
		
		if ((priorityOfStack - priorityOfSymbol) >= 0) {
			// 如果读取的字符为"("，直接压入栈
			if (priorityOfSymbol == 0) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	
	/**
	 * 判定字符串中开闭分隔符是否匹配
	 * 思路：
	 * 1.分解字符串成字符数组，并创建一个栈
	 * 2.如果读取的字符不是开闭分隔符，直接忽略
	 *   如果读取的字符是开分隔符则压栈
	 *   如果读取的字符是闭分隔符，且栈不为空，栈顶元素出栈，否则提示匹配错误
	 * 3.如果出栈的开分隔符与读取的闭分隔符不匹配，提示匹配错误
	 * 4.字符串处理完后，如果栈不为空，则提示匹配错误
	 * @param string 需要判断的字符串
	 * @return true 分隔符匹配 false 分隔符不匹配
	 */
	public static boolean isMatch(String string) {
		// 1.将字符串转换成字符数组
		char[] chars = string.toCharArray();
		// 2.创建一个字符栈
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		// 3.遍历字符数组
		for (int i = 0; i < chars.length; i++) {
			// 3.1 如果为开分隔符则压栈
			if ('(' == chars[i] || '{' == chars[i] || '[' == chars[i]) {
				stack.push(chars[i]);
			}
			// 3.2 如果为闭分隔符且栈不为空则出栈，否则提示匹配错误
			if (')' == chars[i] || '}' == chars[i] || ']' == chars[i]) {
				if (stack.isEmpty()) {
					System.out.println("开闭分隔符不匹配！缺少与" + chars[i] + "对应的分隔符");
					return false;
				} else {
					// 栈不为空时，判断出栈的字符是不是匹配的开分隔符
					char temp = stack.pop();
					switch (chars[i]) {
					case ']':
						if ('[' != temp) {
							System.out.println("开闭分隔符不匹配！缺少与" + chars[i] + "对应的分隔符");
							return false;
						}
						break;
					case '}':
						if ('{' != temp) {
							System.out.println("开闭分隔符不匹配！缺少与" + chars[i] + "对应的分隔符");
							return false;
						}
						break;
					case ')':
						if ('(' != temp) {
							System.out.println("开闭分隔符不匹配！缺少与" + chars[i] + "对应的分隔符");
							return false;
						}
						break;
					default:
						break;
					}
				}
			}
		}
		// 4.字符数组处理完，如果栈不为空，说明栈内有多余的开分隔符，提示匹配出错
		if (!stack.isEmpty()) {
			System.out.println("开闭分隔符不匹配！缺少与" + stack.top() + "对应的分隔符");
			return false;
		}
		return true;
	} 
	
	/**
	 * 栈测试
	 */
	public static void test() {
		LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
		stack.push(4);
		stack.push(3);
		stack.push(2);
		
		int size = stack.size();
		System.out.println("-----------");
		for (int i = 0; i < size; i++) {
			System.out.println("第" + i + "个元素：" + stack.pop());
			System.out.println("-----------");
		}
	}
	
	/**
	 * 字符串回文判断
	 * @param str 需要判断的字符串
	 * @return true 是回文  false 不是回文
	 */
	public static boolean isPalindrome(String str) {
		
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		
		// 将字符串转换成字符数组
		char[] inputChar = str.toCharArray();
		
		// 字符数组的中间序号
		int mid = inputChar.length / 2;
		
		// 将字符数组的前一半字符压入栈中
		for (int i = 0; i < mid; i++) {
			stack.push(inputChar[i]);
		}

		// 如果字符数组大小为奇数
		if (inputChar.length % 2 != 0) {
			mid += 1;
		}
		
		// 对比字符数组后半部分字符和栈中字符
		for (int i = mid; i < inputChar.length; i++) {
			System.out.println(inputChar[i] + " == " + stack.top());
			if (inputChar[i] != stack.pop()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 设计一个可以把栈中元素按照升序排列的排序算法
	 * @param sourceStack 需要排序的原栈
	 * @return 排序后的栈
	 */
	public static LinkedListStack<Integer> sort(LinkedListStack<Integer> sourceStack) {
		LinkedListStack<Integer> resultStack = new LinkedListStack<Integer>();
		while (!sourceStack.isEmpty()) {
			Integer temp = sourceStack.pop();
			while (!resultStack.isEmpty() && resultStack.top() > temp) {
				sourceStack.push(resultStack.pop());
			}
			resultStack.push(temp);
		}
		return resultStack;
	}
}

/**
 * 给定一个栈，如何只使用栈操作（push和pop）逆置栈中的内容
 * @author Administrator
 *
 * @param <AnyType>
 */
class StackReversal<AnyType> {
	
	public void reverseStack(LinkedListStack<AnyType> stack) {
		if (stack.isEmpty()) {
			return;
		}
		AnyType temp = stack.pop();
		reverseStack(stack);
		insertAtBottom(stack, temp);
	}
	
	public void insertAtBottom(LinkedListStack<AnyType> stack, AnyType data) {
		if (stack.isEmpty()) {
			stack.push(data);
			return;
		}
		AnyType temp = stack.pop();
		insertAtBottom(stack, data);
		stack.push(temp);
	}
}

