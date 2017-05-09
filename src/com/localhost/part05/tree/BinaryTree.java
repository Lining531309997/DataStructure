package com.localhost.part05.tree;

import com.localhost.part03.stack.LinkedListStack;
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
	
	/**
	 * 题目：向二叉树插入一个元素
	 * 思路：因为给定的是二叉树，所以能在任意位置插入元素。为了插入元素，可以使用层序遍历找到一个左孩子或右孩子为空的节点，然后插入该元素。
	 * @param root 二叉树的根节点
	 * @param data 需要插入的元素
	 * @return 插入新元素后的根节点
	 */
	public static BinaryTreeNode<Integer> insert(BinaryTreeNode<Integer> root, int data) {
		// 创建新节点
		BinaryTreeNode<Integer> newNode = new BinaryTreeNode<Integer>(data);
		// 二叉树为空则设置根节点并返回
		if (root == null) {
			root = newNode;
			return root;
		}
		
		// 出队元素
		BinaryTreeNode<Integer> temp = new BinaryTreeNode<Integer>();
		// 创建存储二叉树节点的队列
		LinkListQueue<BinaryTreeNode<Integer>> queue = new LinkListQueue<BinaryTreeNode<Integer>>();
		// 根节点入队
		queue.enQueue(root);
		// 队列不为空则遍历
		while (!queue.isEmpty()) {
			// 获取出队元素
			temp = queue.deQueue();
			
			// 检查当前节点左孩子是否为空
			if (temp.getLeft() == null) {
				temp.setLeft(newNode);
				return root;
			} else {
				queue.enQueue(temp.getLeft());
			}
			
			// 检查当前节点右孩子是否为空
			if (temp.getRight() == null) {
				temp.setRight(newNode);
				return root;
			} else {
				queue.enQueue(temp.getRight());
			}
		}
		return null;
	}
	
	/**
	 * 题目：获取二叉树节点个数
	 * 思路：递归计算左子树和右子树的大小，再加一
	 * @param root 二叉树根节点
	 * @return 二叉树节点数
	 */
	public static int size(BinaryTreeNode<Integer> root) {
		if (root == null) {
			return 0;
		} else {
			return (size(root.getLeft()) + 1 + size(root.getRight()));
		}
	}
	
	/**
	 * 题目：非递归获取二叉树节点个数
	 * 思路：利用层序遍历，有元素出队时节点个数加一
	 * @param root 二叉树根节点
	 * @return 二叉树节点数
	 */
	public static int sizeByLevelOrder(BinaryTreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		// 出队元素
		BinaryTreeNode<Integer> temp = new BinaryTreeNode<Integer>();
		// 创建存储二叉树节点的队列
		LinkListQueue<BinaryTreeNode<Integer>> queue = new LinkListQueue<BinaryTreeNode<Integer>>();
		// 根节点入队
		queue.enQueue(root);
		// 计数
		int count = 0;
		// 队列不为空则遍历
		while (!queue.isEmpty()) {
			// 获取出队元素
			temp = queue.deQueue();
			// 计数加一
			count++;
			// 当前节点左子树不为空则入队
			if (temp.getLeft() != null) {
				queue.enQueue(temp.getLeft());
			}
			// 当前节点右子树不为空则入队
			if (temp.getRight() != null) {
				queue.enQueue(temp.getRight());
			}
		}
		return count;
	}
	
	/**
	 * 题目：逆向逐层输出树中的元素
	 * 思路：采用层序遍历，将数据先存入队列，然后出队存入栈中，最后再出栈即可
	 * 例如下图所示的二叉树逆向逐层输出顺序为：4 5 6 7 2 3 1
	 *							          二叉树
	 * 								 1
	 * 								/ \
	 * 							   2   3
	 * 							  / \ / \
	 * 							 4  5 6  7
	 * @param root 二叉树根节点
	 */
	public static void printLevelInReverse(BinaryTreeNode<Integer> root) {
		// 如果根节点为空，即二叉树本来就不存在
		if (root == null) {
			return;
		}
		
		// 存储出队元素
		BinaryTreeNode<Integer> temp = new BinaryTreeNode<Integer>();
		// 创建存储二叉树节点的队列
		LinkListQueue<BinaryTreeNode<Integer>> queue = new LinkListQueue<BinaryTreeNode<Integer>>();
		// 创建存储出队元素的栈
		LinkedListStack<BinaryTreeNode<Integer>> stack = new LinkedListStack<BinaryTreeNode<Integer>>();
		
		// 根节点最先入队
		queue.enQueue(root);
		
		// 队列不为空则遍历
		while (!queue.isEmpty()) {
			// 获取出队元素
			temp = queue.deQueue();
			// 将出队元素入栈
			stack.push(temp);
			// 右子树先入队
			if (temp.getRight() != null) {
				queue.enQueue(temp.getRight());
			}
			// 左子树后入队
			if (temp.getLeft() != null) {
				queue.enQueue(temp.getLeft());
			}
		}
		
		// 输出结果
		System.out.print("逐层逆向输出：");
		while (!stack.isEmpty()) {
			System.out.print(stack.pop().getData() + " ");
		}
	}
	
	/**
	 * 题目：求已知二叉树高度/深度
	 * 思路：递归计算左子树和右子树的高度，然后找出两棵树中高度的大值，再加一，就是树的高度。
	 * @param root 二叉树的根节点
	 * @return 二叉树的高度/深度
	 */
	public static int getHeight(BinaryTreeNode<Integer> root) {
		return 0;
	}
}





















