package com.localhost.part05.tree;

import com.localhost.part04.queue.LinkListQueue;

public class BinaryTree {
	
	/**
	 * 题目：查找二叉树中最大元素
	 * 思路：利用递归思想，分别查找到左子树中最大元素和右子树中最大元素，然后将它们与根节点的值进行比较。
	 * @param root 二叉树根节点
	 * @return 二叉树中最大元素
	 */
	public static int findMax(BinaryTreeNode<Integer> root) {
		int max = Integer.MIN_VALUE;
		int root_val = 0;
		int left_val = 0;
		int right_val = 0;
		if (root != null) {
			root_val = root.getData();
			left_val = findMax(root.getLeft());
			right_val = findMax(root.getRight());
			// 3个值进行比较
			if (left_val > right_val) {
				max = left_val;
			} else {
				max = right_val;
			}
			if (root_val > max) {
				max = root_val;
			}
		}
		return max;
	}
	
	/**
	 * 题目：用非递归的方法查找二叉树中最大元素
	 * 思路：利用层序遍历，在节点出队时观察其数据值是否为最大值
	 * @param root 二叉树根节点
	 * @return 二叉树中最大元素
	 */
	public static int findMaxUsingLevelOrder(BinaryTreeNode<Integer> root) {
		int max = Integer.MIN_VALUE;
		BinaryTreeNode<Integer> temp;
		LinkListQueue<BinaryTreeNode<Integer>> queue = new LinkListQueue<BinaryTreeNode<Integer>>();
		
		// 根节点先入队
		queue.enQueue(root);
		// 队列不为空则遍历
		while (!queue.isEmpty()) {
			// 获取出队元素
			temp = queue.deQueue();
			// 判断最大值
			if (temp.getData() > max) {
				max = temp.getData();
			}
			// 出队节点的左子树不为空，则左子树入队
			if (temp.getLeft() != null) {
				queue.enQueue(temp.getLeft());
			}
			// 出队节点的右子树不为空，则右子树入队
			if (temp.getRight() != null) {
				queue.enQueue(temp.getRight());
			}
		}
		return max;
	}
	
	/**
	 * 题目：搜索二叉树中某个元素
	 * 思路：对于给定的二叉树，如果发现树中某个节点的数据值与搜索的元素值相同，返回true。递归的从树根节点向下，比较左子树与右子树各个节点的值。
	 * @param root 二叉树根节点
	 * @param data 目标数据值
	 * @return true 存在该元素 false 不存在该元素
	 */
	public static boolean hasData(BinaryTreeNode<Integer> root, int data) {
		boolean temp = false;
		// 二叉树为空，说明数据未找到，返回false
		if (root == null) {
			return false;
		} else {
			// 判断当前节点的值是否等于目标数据值
			if (root.getData() == data) {
				return true;
			} else {
				// 判断左子树是否包含该元素
				temp = hasData(root.getLeft(), data);
				// 如果左子树不包含该元素，查找右子树
				if (!temp) {
					temp = hasData(root.getRight(), data);
				}
			}
		}
		return temp;
	}
	
	/**
	 * 题目：用非递归方法搜索二叉树中某个元素
	 * 思路：层序遍历
	 * @param root 二叉树根节点
	 * @param data 目标数据值
	 * @return true 存在该元素 false 不存在该元素
	 */
	public static boolean hasDataByLevelOrder(BinaryTreeNode<Integer> root, int data) {
		BinaryTreeNode<Integer> temp = new BinaryTreeNode<Integer>();
		LinkListQueue<BinaryTreeNode<Integer>> queue = new LinkListQueue<BinaryTreeNode<Integer>>();
		// 二叉树为空，说明数据未找到，返回false
		if (root == null) {
			return false;
		} 
		// 根节点入队
		queue.enQueue(root);
		// 当队列不为空则遍历
		while (!queue.isEmpty()) {
			// 获取出队元素
			temp = queue.deQueue();
			// 判断当前节点是否是目标元素
			if (temp.getData() == data) {
				return true;
			}
			// 左子树不为空时入队列
			if (temp.getLeft() != null) {
				queue.enQueue(temp.getLeft());
			}
			// 左子树不为空时入队列
			if (temp.getRight() != null) {
				queue.enQueue(temp.getRight());
			}
		}
		return false;
	}
	
	
}





















