package com.localhost.part09.Search;

import com.localhost.part08.Sort.Sort;

public class SearchProblems {

	/**
	 * 题目：给定一个长度为n的数组，给出一个算法，判定该数组中是否存在重复元素
	 */
	
	/**
	 * 蛮力法
	 * 穷尽搜索整个数组，检查是否存在重复元素。
	 * 对每一个输入元素，都要检查数组中是否存在与其具有相同值得元素。
	 * @param array 给定的数组
	 */
	public static void checkDuplicatesBruteForce(int[] array) {
		int length = array.length;
		int j = 0;
		for (int i = 0; i < length; i++) {
			for (j = i + 1; j < length; j++) {
				if (array[i] == array[j]) {
					System.out.println("存在重复元素" + array[i]);
					return;
				}
			}
		}
		System.out.println("不存在重复元素");
	}
	
	/**
	 * 方法二
	 * 相对给定的数组排序，排序后，值相等的元素相邻。检查相邻元素是否相同即可
	 * @param array
	 */
	public static void checkDuplicatesSorting(int[] array) {
		// 先对数组排序
		Sort.bubbleSortImproved(array);
		int length = array.length;
		for (int i = 0; i < length; i++) {
			if (array[i] == array[i + 1]) {
				System.out.println("存在重复元素" + array[i]);
				return;
			}
		}
		System.out.println("不存在重复元素");
	}
	
	/**
	 * 方法三
	 * 假设数组中的元素都是取值范围在0~n-1的正数。对于每个元素A[i]，寻找索引为A[i]的数组元素。
	 * 选择A[A[i]]且将其标记为-A[A[i]](求A[A[i]]的负数)。重复该过程，直到遇到的元素值已经为负数，说明存在重复元素。
	 * 
	 * 已数组A={3,2,1,2,2,3}为例
	 * 初始时
	 * 		3	2	1	2	2	3
	 * 		---------------------
	 * 		0	1	2	3	4	5
	 * 在步骤1中，对A[abs(A[0])]求负，
	 *		3	2	1	-2	2	3
	 * 		---------------------
	 * 		0	1	2	3	4	5
	 * 在步骤2中，对A[abs(A[1])]求负，
	 *		3	2	-1	-2	2	3
	 * 		---------------------
	 * 		0	1	2	3	4	5
	 * 在步骤3中，对A[abs(A[2])]求负，
	 *		3	-2	-1	-2	2	3
	 * 		---------------------
	 * 		0	1	2	3	4	5
	 * 在步骤4中，对A[abs(A[3])]求负，
	 *		3	-2	-1	-2	2	3
	 * 		---------------------
	 * 		0	1	2	3	4	5
	 * 此时可以发现A[abs(A[3])]已经为负数。这表明该元素已经被访问两次。
	 * 
	 * 注意：
	 * 该方法不适用于只读数组
	 * 该方法仅适用于所有元素为正数的数组
	 * 如果数组中的元素取值范围不在0~n-1，则可能出现异常
	 * @param array 给定的数组
	 */
	public static void checkDuplicates(int[] array) {
		int length = array.length;
		for (int i = 0; i < length; i++) {
			if (array[Math.abs(array[i])] < 0) {
				System.out.println("存在重复元素" + Math.abs(array[i]));
				return;
			} else {
				array[Math.abs(array[i])] = -array[Math.abs(array[i])];
			}
		}
		System.out.println("不存在重复元素");
	}
	
	/**
	 * 题目：发现缺失的数：已知一个由n-1个取值范围在1~n的整数组成的列表。假设列表中没有重复的出现的整数，则列表的中缺失某个整数。
	 * 例子：数组 A={1,2,4,6,3,7,8}，由7个整数，范围在1~8，没有重复元素，缺失整数5
	 */
	
	/**
	 * 方法一
	 * 对于每一个在1~n的正数，检查该数是否在给定的数组中
	 * @param array 给定的数组
	 * @return 缺失的整数
	 */
	public static int findMissingNumber1(int[] array) {
		int n = array.length + 1;
		boolean found;
		for (int i = 1; i <= n; i++) {
			found = true;
			for (int j = 0; j < array.length; j++) {
				if (array[j] == i) {
					found = false;
				}
			}
			if (found) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 方法二
	 * 对数组排序，再通过一次扫描即可找到缺失整数
	 * @param array 给定的数组
	 * @return 缺失的整数
	 */
	public static int findMissingNumber2(int[] array) {
		Sort.bubbleSortImproved(array);
		int i = 1;
		while (i == array[i - 1]) {
			i++;
		}
		if (i != array[i - 1]) {
			return i;
		}
		return -1;
	}
	
	/**
	 * 方法三
	 * 使用求和公式
	 * 1.对于n个数求和，sum=n*(n+1)/2
	 * 2.用sum减去输入序列的所有元素，结果就是缺失的数据
	 * @param array 给定的数组
	 * @return 缺失的整数
	 */
	public static int findMissingNumber3(int[] array) {
		int n = array.length + 1;
		int sum = n * (n + 1) / 2;
		for (int i = 0; i < array.length; i++) {
			sum -= array[i];
		}
		if (sum != 0) {
			return sum;
		} else {
			return -1;
		}
	}
	
	/**
	 * 方法四
	 * 如果数字的总和超出所允许的最大整数范围，则可能导致整数溢出而不能获得正确答案。
	 * 1.对数组中的所有元素执行异或(XOR)操作，设异或后的结果为X
	 * 2.对1~n的所有元素执行异或(XOR)操作，设异或后的结果为Y
	 * 3.对X与Y异或可得到缺失的整数
	 * @param array 给定的数组
	 * @return 缺失的整数
	 */
	public static int findMissingNumber4(int[] array) {
		int n = array.length + 1;
		int X = 0;
		int Y = 0;
		for (int i = 0; i < array.length; i++) {
			X ^= array[i];
		}
		for (int i = 1; i <= n; i++) {
			Y ^= i;
		}
		return X^Y;
	}
	
	/**
	 * 题目：给定一个含n个元素的数组，在数组中查找两个元素，这两个元素的和等于给定的元素K
	 * 思路：
	 * 1.先将数组排序
	 * 2.设置索引low=0，high=n-1，并计算sum=A[low]+A[high]
	 * 3.如果sum==k,则直接输出当前元素对的索引
	 * 4.如果sum>k,则high减一
	 * 5.如果sum<k,则low加一
	 */
	public static void search(int[] array, int k) {
		// 先对数组进行排序
		Sort.bubbleSortImproved(array);
		int sum;
		int low = 0;
		int high = array.length - 1;
		while (low < high) {
			sum = array[low] + array[high];
			if (sum == k) {
				System.out.println("Items Found, low:" + low + " high:" + high);
				return;
			} else if (sum < k) {
				low++;
			} else {
				high--;
			}
		}
		System.out.println("Items No Found: No Such Elements !");
	}
	
	/**
	 * 题目：查找和最接近0的两个元素：给定一个包含正数和负数的数组，查找两个元素，使得它们的和最接近0.
	 * 例如：对于数组A = {1， 60， -10， 70， -80， 85}，算法找到的两个元素为-80 和 85
	 * 思路：
	 * 1.对给定的数组排序，positiveClosest表示最小正数和，negativeClosest表示最小负数和
	 * 2.设置索引low=0，high=n-1，并计算sum=A[low]+A[high]
	 * 3.如果sum大于0且小于positiveClosest,则更新positiveClosest
	 * 4.如果sum小于0且大于negativeClosest,则更新negativeClosest
	 * 5.如果sum等于0则直接输出当前元素对
	 */
	public static void twoElementsWithMinSum(int[] array) {
		// 先对数组进行排序
		Sort.bubbleSortImproved(array);
		// 初始化最小正数和以及最小负数和
		int positiveClosest = Integer.MAX_VALUE;
		int negativeClosest = Integer.MIN_VALUE;
		// 初始化最小正数和的元素对索引
		int positive_low = 0;
		int positive_high = array.length - 1;
		// 初始化最小负数和的元素对索引
		int negative_low = 0;
		int negative_high = array.length - 1;
		// 数组元素的和
		int sum;
		// 数组的索引
		int low = 0;
		int high = array.length - 1;
		while (low < high) {
			sum = array[low] + array[high];
			if (sum > 0) {
				if (sum < positiveClosest) {
					positiveClosest = sum;
					positive_low = low;
					positive_high = high;
				}
				high--;
			} else if (sum < 0) {
				if (sum > negativeClosest) {
					negativeClosest = sum;
					negative_low = low;
					negative_high = high;
				}
				low++;
			} else {
				// 数组元素和为0
				System.out.println("Elements:" + array[low] + " And " + array[high]);
				return;
			}
		}
		// 判断元素对
		if (Math.abs(positiveClosest) > Math.abs(negativeClosest)) {
			System.out.println("Elements:" + array[negative_low] + " And " + array[negative_high]);
		} else {
			System.out.println("Elements:" + array[positive_low] + " And " + array[positive_high]);
		}
	}
	
	
}
