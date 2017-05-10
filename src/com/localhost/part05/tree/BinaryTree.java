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
	
	/*----------------------- 2017.05.10 -----------------------*/	
	
	/**
	 * 题目：求已知二叉树高度/深度
	 * 思路：递归计算左子树和右子树的高度，然后找出两棵树中高度的大值，再加一，就是树的高度。
	 * @param root 二叉树的根节点
	 * @return 二叉树的高度/深度
	 */
	public static int getHeight(BinaryTreeNode<Integer> root) {
		/* 基本情况：根节点为空时返回0 */
		if (root == null) {
			return 0;
		}
		/* 计算子树的高度/深度 */
		int leftHeight = getHeight(root.getLeft());
		int rightHeight = getHeight(root.getRight());
		/* 返回子树的高度 */
		if (leftHeight > rightHeight) {
			return (leftHeight + 1);
		} else {
			return (rightHeight + 1);
		}
	}
	
	/**
	 * 题目：求已知二叉树高度/深度
	 * 思路：使用层序遍历，使用null作为每一层的结束标志。
	 * @param root 二叉树的根节点
	 * @return 二叉树的高度/深度
	 */
	public static int getHeightByLevelOrder(BinaryTreeNode<Integer> root) {
		
		/* 根节点为空则返回0 */
		if (root == null) {
			return 0;
		}
		
		/* 计算二叉树高度 */
		// 声明并初始化树的层数
		int level = 0;
		// 声明并初始化出队元素
		BinaryTreeNode<Integer> temp = new BinaryTreeNode<Integer>();
		// 声明并初始化存储二叉树节点的队列
		LinkListQueue<BinaryTreeNode<Integer>> queue = new LinkListQueue<BinaryTreeNode<Integer>>();
		
		// 根节点先入队
		queue.enQueue(root);
		// 标记第一层结束
		queue.enQueue(null);
		// 遍历队列直到为空
		while (!queue.isEmpty()) {
			// 获取出队元素
			temp = queue.deQueue();
			// 检查当前层是否结束
			if (temp == null) {
				// 层数自增
				level++;
				// 为下一层添加结束标志
				if (!queue.isEmpty()) {
					queue.enQueue(null);
				}
			} else {
				// 如果当前节点的左孩子存在则入队
				if (temp.getLeft() != null) {
					queue.enQueue(temp.getLeft());
				}
				// 如果当前节点的右孩子存在则入队
				if (temp.getRight() != null) {
					queue.enQueue(temp.getRight());
				}
			}
		}
		return level;
	}
	
	/**
	 * 题目：获取二叉树中最深节点
	 * 思路：使用层序遍历依次入队，并以null作为每层的结束标志；将出队元素全部存入栈中，最后出栈，遇到null结束即可；
	 * @param root 二叉树根节点
	 */
	public static void getDeepestNodes(BinaryTreeNode<Integer> root) {
		
		/* 根节点为空则返回空 */
		if (root == null) {
			return;
		}
		
		/* 实现二叉树出队入栈 */
		// 声明并初始化出队元素
		BinaryTreeNode<Integer> temp = new BinaryTreeNode<Integer>();
		// 声明并初始化结束标志
		BinaryTreeNode<Integer> end = new BinaryTreeNode<Integer>((int) 'z');
		// 声明并初始化存储二叉树节点的队列
		LinkListQueue<BinaryTreeNode<Integer>> queue = new LinkListQueue<BinaryTreeNode<Integer>>();
		// 声明并初始化存储二叉树节点的栈
		LinkedListStack<BinaryTreeNode<Integer>> stack = new LinkedListStack<BinaryTreeNode<Integer>>();
		
		// 根节点先入队
		queue.enQueue(root);
		// 标记第一层结束
		queue.enQueue(end);
		
		// 队列不为空则遍历
		while (!queue.isEmpty()) {
			// 获取出队元素
			temp = queue.deQueue();
			// 检查当前层是否结束
			if (temp == end) {
				// 为下一层添加结束标志
				if (!queue.isEmpty()) {
					queue.enQueue(end);
				}
			} else {
				// 如果当前节点的右孩子存在则入队
				if (temp.getRight() != null) {
					queue.enQueue(temp.getRight());
				}
				// 如果当前节点的左孩子存在则入队
				if (temp.getLeft() != null) {
					queue.enQueue(temp.getLeft());
				}
			}
			// 将出队元素入栈
			stack.push(temp);
		}
		
		/* 输出结果 */
		// 将最后一层的结束标志去掉
		stack.pop();
		System.out.print("二叉树最深节点：");
		while (stack.top() != end) {
			System.out.print(stack.pop().getData() + " ");
		}
	}
	
	/**
	 * 题目：删除二叉树中某个节点
	 * 思路：
	 * 1.从根节点开始层序遍历，找到要删除的节点
	 * 2.找到最深节点，如果有多个最深节点，则选择最后一个最深节点即二叉树最底层最右边的节点
	 * 3.用最深节点替换要删除的节点
	 * 4.删除最深节点
	 * @param root 二叉树的根节点
	 * @param data 要删除节点的数据值
	 * @return 删除结点后的二叉树根节点
	 */
	public static BinaryTreeNode<Integer> deleteBinaryTreeNode(BinaryTreeNode<Integer> root, int data) {
		/* 根节点为空则返回空 */
		if (root == null) {
			return null;
		}
		
		/* 实现二叉树出队入栈 */
		// 声明要删除的节点
		BinaryTreeNode<Integer> deleteNode = null;
		// 声明并初始化出队元素
		BinaryTreeNode<Integer> temp = new BinaryTreeNode<Integer>();
		// 声明并初始化结束标志
		BinaryTreeNode<Integer> end = new BinaryTreeNode<Integer>((int) 'z');
		// 声明并初始化存储二叉树节点的队列
		LinkListQueue<BinaryTreeNode<Integer>> queue = new LinkListQueue<BinaryTreeNode<Integer>>();
		// 声明并初始化存储二叉树节点的栈
		LinkedListStack<BinaryTreeNode<Integer>> stack = new LinkedListStack<BinaryTreeNode<Integer>>();
		
		// 根节点先入队
		queue.enQueue(root);
		// 标记第一层结束
		queue.enQueue(end);
		
		// 队列不为空则遍历
		while (!queue.isEmpty()) {
			// 获取出队元素
			temp = queue.deQueue();
			// 检查是否是要删除的节点
			if (temp.getData() == data) {
				deleteNode = temp;
			}
			// 检查当前层是否结束
			if (temp == end) {
				// 为下一层添加结束标志
				if (!queue.isEmpty()) {
					queue.enQueue(end);
				}
			} else {
				// 如果当前节点的左孩子存在则入队
				if (temp.getLeft() != null) {
					queue.enQueue(temp.getLeft());
				}
				// 如果当前节点的右孩子存在则入队
				if (temp.getRight() != null) {
					queue.enQueue(temp.getRight());
				}
			}
			// 将出队元素入栈
			stack.push(temp);
		}
		
		/* 获取最深节点 */
		// 将最后一层的结束标志去掉
		stack.pop();
		// 声明并初始化最深节点
		BinaryTreeNode<Integer> deepestNode = stack.pop();
		
		/* 重置删除节点的值即可 */
		deleteNode.setData(deepestNode.getData());
		
		/* 删除最深节点 */
		while (!stack.isEmpty()) {
			temp = stack.pop();
			if (temp.getLeft() == deepestNode) {
				temp.setLeft(null);
			}
			if (temp.getRight() == deepestNode) {
				temp.setRight(null);
			}
		}
		return root;
	}
	
	/**
	 * 题目：用非递归算法获取二叉树叶子节点个数
	 * 思路：左右孩子都为空的节点为叶子节点
	 * @param root 二叉树根节点
	 * @return 叶子节点的个数
	 */
	public static int getLeafNodeNum(BinaryTreeNode<Integer> root) {
		/* 根节点为空则返回0 */
		if (root == null) {
			return 0;
		}
		
		/* 计算二叉树叶子节点个数 */
		// 声明并初始化叶子节点个数
		int count = 0;
		// 声明并初始化出队元素
		BinaryTreeNode<Integer> temp = new BinaryTreeNode<Integer>();
		// 声明并初始化存储二叉树节点的队列
		LinkListQueue<BinaryTreeNode<Integer>> queue = new LinkListQueue<BinaryTreeNode<Integer>>();
		
		// 根节点先入队
		queue.enQueue(root);
		// 遍历队列直到为空
		while (!queue.isEmpty()) {
			// 获取出队元素
			temp = queue.deQueue();
			// 检查当前节点是否是叶子节点
			if ((temp.getLeft() == null) && (temp.getRight() == null)) {
				// 叶子节点个数自增
				count++;
			} else {
				// 如果当前节点的左孩子存在则入队
				if (temp.getLeft() != null) {
					queue.enQueue(temp.getLeft());
				}
				// 如果当前节点的右孩子存在则入队
				if (temp.getRight() != null) {
					queue.enQueue(temp.getRight());
				}
			}
		}
		return count;
	}
	
	/**
	 * 题目：用非递归算法获取二叉树满节点个数
	 * 思路：左右孩子都存在的节点为满节点
	 * @param root 二叉树根节点
	 * @return 满节点的个数
	 */
	public static int getFullNodeNum(BinaryTreeNode<Integer> root) {
		/* 根节点为空则返回0 */
		if (root == null) {
			return 0;
		}
		
		/* 计算二叉树满节点个数 */
		// 声明并初始化满节点个数
		int count = 0;
		// 声明并初始化出队元素
		BinaryTreeNode<Integer> temp = new BinaryTreeNode<Integer>();
		// 声明并初始化存储二叉树节点的队列
		LinkListQueue<BinaryTreeNode<Integer>> queue = new LinkListQueue<BinaryTreeNode<Integer>>();
		
		// 根节点先入队
		queue.enQueue(root);
		// 遍历队列直到为空
		while (!queue.isEmpty()) {
			// 获取出队元素
			temp = queue.deQueue();
			// 检查当前节点是否是满节点
			if ((temp.getLeft() != null) && (temp.getRight() != null)) {
				// 叶子节点个数自增
				count++;
			} 
		
			// 如果当前节点的左孩子存在则入队
			if (temp.getLeft() != null) {
				queue.enQueue(temp.getLeft());
			}
			// 如果当前节点的右孩子存在则入队
			if (temp.getRight() != null) {
				queue.enQueue(temp.getRight());
			}
		}
		return count;
	}
	
	/**
	 * 题目：用非递归算法获取二叉树半节点个数
	 * 思路：仅有一个孩子存在的节点为满节点
	 * @param root 二叉树根节点
	 * @return 半节点的个数
	 */
	public static int getHalfNodeNum(BinaryTreeNode<Integer> root) {
		/* 根节点为空则返回0 */
		if (root == null) {
			return 0;
		}
		
		/* 计算二叉树半节点个数 */
		// 声明并初始化半节点个数
		int count = 0;
		// 声明并初始化出队元素
		BinaryTreeNode<Integer> temp = new BinaryTreeNode<Integer>();
		// 声明并初始化存储二叉树节点的队列
		LinkListQueue<BinaryTreeNode<Integer>> queue = new LinkListQueue<BinaryTreeNode<Integer>>();
		
		// 根节点先入队
		queue.enQueue(root);
		// 遍历队列直到为空
		while (!queue.isEmpty()) {
			// 获取出队元素
			temp = queue.deQueue();
			// 检查当前节点是否是半节点
			if (((temp.getLeft() != null) && (temp.getRight() == null)) || ((temp.getRight() != null) && (temp.getLeft() == null))) {
				// 半节点个数自增
				count++;
			} 
			
			// 如果当前节点的左孩子存在则入队
			if (temp.getLeft() != null) {
				queue.enQueue(temp.getLeft());
			}
			// 如果当前节点的右孩子存在则入队
			if (temp.getRight() != null) {
				queue.enQueue(temp.getRight());
			}
		}
		return count;
	}
	
	
	
}





















