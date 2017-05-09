package com.localhost.part01.recursive;

/**
 * 题目：汉诺塔
 * 汉诺塔（又称河内塔）问题是源于印度一个古老传说的益智玩具。
 * 大梵天创造世界的时候做了三根金刚石柱子，在一根柱子上从下往上按照大小顺序摞着64片黄金圆盘。
 * 大梵天命令婆罗门把圆盘从下面开始按大小顺序重新摆放在另一根柱子上。
 * 并且规定，在小圆盘上不能放大圆盘，在三根柱子之间一次只能移动一个圆盘。
 * 
 * 算法：
 * 将源柱最上面的n-1个盘子移动到辅助的柱子上
 * 将源柱上第n个盘子移动到目标柱
 * 将辅助柱的n-1个盘子移动到目标柱
 * 
 * 假设A为源柱（此时A上有n个盘子），B为辅助柱，C为目标柱
 * 1.先将A上面的n-1个盘子移动到B上面（此时B上面有n-1个盘子）
 * 2.再将A上的第n个个盘子移动到C上面（此时A上面已经没有盘子）
 * 3.最后将B上的n-1个盘子借助A移动到C上面（此时可以把B看做源柱，A看做辅助柱，C仍然是目标柱）
 */
public class TowersOfHanio {

	public static void main(String[] args) {
		Hanio(3, 'A', 'B', 'C');
	}

	/**
	 * 汉诺塔
	 * @param n 盘子个数
	 * @param from 源柱
	 * @param assist 辅助柱
	 * @param to 目标柱
	 */
	public static void Hanio(int n, char from, char assist, char to) {
		if (n == 1) {
			// 仅有一个盘子，直接从源柱(A)移动到目标柱(C)
			System.out.println("Move disk :" + from + " ----> " + to);
		} else {
			// 先将源柱(A)上n-1盘子移动到辅助柱(B)上
			Hanio(n-1, from, to, assist);
			// 再将源柱(A)上第n个盘子移动到目标柱(C)上
			System.out.println("Move disk :" + from + " ----> " + to);
			// 最后将辅助柱(B)上的n-1个盘子移动到目标柱(C)
			Hanio(n-1, assist, from, to);
		}
	}
}
