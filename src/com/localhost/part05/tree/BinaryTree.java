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
	
	/*----------------------- 2017.05.12 -----------------------*/	
	
	/**
	 * 题目：判断给定的两棵树结构是否相同
	 * 思路：
	 * 两棵树都为空树，则返回true
	 * 两棵树有任意一棵树不为空时返回false
	 * 两棵树都不为空，则比较数据并递归地判断左子树和右子树是否相同
	 * @param root1 二叉树的根节点
	 * @param root2 二叉树的根节点
	 * @return true 结构相同 false 结构不相同
	 */
	public static boolean AreStructullySameTrees(BinaryTreeNode<Integer> root1, BinaryTreeNode<Integer> root2) {
		/* 如果两棵树都为空则返回true */
		if ((root1 == null) && (root2 == null)) {
			return true;
		}
		
		/* 如果两棵树有任意一棵树不为空时返回false */
		if ((root1 == null) || (root2 == null)) {
			return false;
		}
		
		/* 如果两棵树都不为空时，则比较左右子树是否相同 */
		return (AreStructullySameTrees(root1.getLeft(), root2.getLeft()) && AreStructullySameTrees(root1.getRight(), root2.getRight()));
	}
	
	// 二叉树的直径距离
	public static int diameter = 0;
	
	/**
	 * 题目：二叉树的直径
	 * 思路：
	 * 树的直径(也称作树的宽度)就是树中的两个叶子节点之间的最长路径中的节点数。
	 * 递归计算左右子树的直径，找出两者的最大值，再加一返回。
	 * @param root 二叉树的根节点
	 * @return 二叉树的直径
	 */
	public static int diameterOfBinaryTree(BinaryTreeNode<Integer> root) {
		/* 如果二叉树为空则返回0 */
		if (root == null) {
			return 0;
		}
		
		/* 递归计算左右子树的直径 */
		int left = diameterOfBinaryTree(root.getLeft());
		int right = diameterOfBinaryTree(root.getRight());
		
		/* 修改树的直径 */
		if ((left + right) > diameter) {
			diameter = left + right;
		}
		return Math.max(left, right) + 1;
	}
	
	/**
	 * 题目：输出所有从根节点到子节点的路径
	 * 思路：先保存当前节点的数据到数组，后采用递归遍历左右子树，遇到叶子节点则输出数组的值。这个方法存在资源浪费或数组溢出问题
	 * @param root 二叉树根节点
	 * @param path 路径数组
	 * @param pathLen 路径长度
	 */
	public static void printPaths(BinaryTreeNode<Integer> root, int[] path, int pathLen) {
		/* 如果节点为空则返回 */
		if (root == null) {
			return;
		}
		
		/* 将当前节点的数据存入路径数组，并修改数组的长度 */
		path[pathLen] = root.getData();
		pathLen++;
		
		/* 如果是叶子节点则输出从根节点到此叶子节点的路径 */
		if ((root.getLeft() == null) && (root.getRight() == null)) {
			// 遍历数组输出
			for (int i : path) {
				System.out.print(i + " ");
			}
			// 换行
			System.out.println();
			return;
		}
		
		/* 如果不是叶子节点，继续遍历左右子树 */
		System.out.println(pathLen);
		printPaths(root.getLeft(), path, pathLen);
		printPaths(root.getRight(), path, pathLen);
	}
	
	/**
	 * 题目：判断是否存在路径的数据和等于给定的值；也就是说，判断是否存在一条从根节点到任意叶子节点的路径，该路径上的节点数据和等于给定的值
	 * 思路：在调用孩子节点之前，先把sum值减去该节点的值。最后检查sum的值是否等于0
	 * @param root
	 * @param sum
	 * @return
	 */
	public static boolean hasPathSum(BinaryTreeNode<Integer> root, int sum) {
		/* 如果节点为空，判断此时sum是否为0 */
		if (root == null) {
			return (sum == 0);
		}
		/* 节点不为空时递归遍历左右子树 */
		// 在递归之前先减去该节点的数据
		int subSum = sum - root.getData();
		return (hasPathSum(root.getLeft(), subSum) || hasPathSum(root.getRight(), subSum));
	}
	
	/**
	 * 题目：计算二叉树所有节点之和
	 * 思路：递归计算左右子树的和，然后加上当前节点的数据值
	 * @param root 二叉树根节点
	 * @return 所有节点数据之和
	 */
	public static int getSum(BinaryTreeNode<Integer> root) {
		/* 节点为空时则返回0 */
		if (root == null) {
			return 0;
		}
		/* 左子树节点值得和 + 当前节点数据值 + 右子树节点值得和 */
		return (getSum(root.getLeft()) + root.getData() + getSum(root.getRight()));
	}
	
	
	/**
	 * 题目：计算二叉树所有节点之和
	 * 思路：使用非递归的层序遍历，每次元素出队时就进行累加
	 * @param root 二叉树根节点
	 * @return 所有节点数据之和
	 */
	public static int getSumByLevelOrder(BinaryTreeNode<Integer> root) {
		/* 节点为空时则返回0 */
		if (root == null) {
			return 0;
		}
		
		// 声明并初始化累加和
		int sum = 0;
		// 声明并初始化出队元素变量
		BinaryTreeNode<Integer> temp = new BinaryTreeNode<Integer>();
		// 声明并初始化存储节点的队列
		LinkListQueue<BinaryTreeNode<Integer>> queue = new LinkListQueue<BinaryTreeNode<Integer>>();
		// 根节点先入队
		queue.enQueue(root);
		
		/* 计算每层的节点数据和 */
		while (!queue.isEmpty()) {
			// 获取出队元素
			temp = queue.deQueue();
			// 计算累加和
			sum += temp.getData();
			// 如果左子树不为空则入队
			if (temp.getLeft() != null) {
				queue.enQueue(temp.getLeft());
			}
			// 如果右子树不为空则入队
			if (temp.getRight() != null) {
				queue.enQueue(temp.getRight());
			}
		}
		return sum;
	}
	
	/*----------------------- 2017.05.14 -----------------------*/	
	
	/**
	 * 题目：将一棵树转换成其镜像树。
	 * 思路：树的镜像指的是互相交换非叶子节点的左子树、右子树而得到的一棵树。如下两棵树：
	 * 			1					1
	 * 		   / \	               / \
	 *        2   3               3   2
	 *       / \					 / \
	 *      4   5     				5   4
	 * @param root 二叉树根节点
	 * @return 镜像树的根节点
	 */
	public static BinaryTreeNode<Integer> mirrorOfBinaryTree(BinaryTreeNode<Integer> root) {
		if (root != null) {
			/* 交换节点的左右子树 */
			BinaryTreeNode<Integer> temp = new BinaryTreeNode<Integer>();
			temp = root.getLeft();
			root.setLeft(root.getRight());
			root.setRight(temp);
			/* 镜像左右子树 */
			mirrorOfBinaryTree(root.getLeft());
			mirrorOfBinaryTree(root.getRight());
		}
		/* 返回镜像后的二叉树根节点 */
		return root;
	}
	
	/**
	 * 题目：判断两棵树是否互为镜像
	 * 思路：
	 * 两棵树都为空则是镜像
	 * 两棵树有一树不为空则不是镜像
	 * 两棵树都不为空，先比较当前节点值、其次比较roo1的左子树与root2的右子树、最后比较roo1的右子树与root2的左子树
	 * @param root 二叉树根节点
	 * @return true 是镜像 false 不是镜像
	 */
	public static boolean areMirrors(BinaryTreeNode<Integer> root1, BinaryTreeNode<Integer> root2) {
		/* 如果两棵树都为空则是镜像 */
		if ((root1 == null) && (root2 == null)) {
			return true;
		}
		/* 如果两棵树有一树不为空则不是镜像 */
		if ((root1 == null) || (root2 == null)) {
			return false;
		}
		/* 两棵树都不为空 */
		// 先比较当前节点的数据值
		if (root1.getData() != root2.getData()) {
			return false;
		} 
		// 递归比较节点的左右子树
		return (areMirrors(root1.getLeft(), root2.getRight()) && areMirrors(root1.getRight(), root2.getLeft()));
	}
	
	// 前序索引
	private static int preIndex = 0;
	
	/**
	 * 题目：根据中序遍历序列和前序遍历序列构建二叉树
	 * 思路：考虑一下遍历序列
	 * 中序遍历序列：D B E A F C
	 * 前序遍历序列：A B D E C F
	 * 在前序遍历序列中，最左边的元素是树的根节点。所以A是给定序列的根。通过中序找到A，能够找到A左边的所有元素，即A的左子树；也能够找到A右边所有的元素，即A的右子树。
	 * 						A
	 * 					   / \
	 * 					 DBE FC
	 * 继续递归可得
	 * 						A
	 * 					   / \
	 * 					  B   C
	 * 					 / \ /
	 * 					D  E F
	 * @param inOrder 中序遍历序列
	 * @param preOrder 前序遍历序列
	 * @param inStart 中序遍历的起始索引
	 * @param inEnd 中序遍历的结束索引
	 * @return 二叉树的根节点
	 */
	public static BinaryTreeNode<String> buildBinaryTree(String[] inOrder, String[] preOrder, int inStart, int inEnd) {
		/* 中序的起始序号大于结束序号说明序列遍历结束 */
		if (inStart > inEnd) {
			return null;
		}
		
		/* 创建新节点并赋值 */
		BinaryTreeNode<String> newNode = new BinaryTreeNode<String>();
		newNode.setData(preOrder[preIndex]);
		// 前序索引自增
		preIndex++;
		
		/* 中序的起始序号等于结束序号说明该节点无孩子节点则返回该节点 */
		if (inStart == inEnd) {
			return newNode;
		}
		
		/* 根据当前节点查找该节点值在中序序列中的索引 */
		int inIndex = 0;
		for (int i = inStart; i <= inEnd; i++) {
			if (inOrder[i] == newNode.getData()) {
				inIndex = i;
				break;
			}
		}
		
		/* 利用该节点在中序中的索引分别建立左子树和右子树 */
		newNode.setLeft(buildBinaryTree(inOrder, preOrder, inStart, inIndex - 1));
		newNode.setRight(buildBinaryTree(inOrder, preOrder, inIndex + 1, inEnd));
		
		return newNode;
	}
	
	/**
	 * 题目：打印二叉树中某节点的所有祖先节点
	 * 思路：
	 * 先检查当前节点的左孩子或右孩子是否是目标节点
	 * 再检查当前节点的左子树或右子树是否包含目标节点
	 * @param root 二叉树根节点
	 * @param node 要查找的目标节点
	 * @return true 包含目标节点 false 不包含目标节点
	 */
	public static boolean printAllAncestors(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> node) {
		/* 如果当前节点为空则返回 */
		if (root == null) {
			return false;
		}
		/* 如果当前节点的左孩子或右孩子为目标节点则打印当前节点 */
		if ((root.getLeft() == node) || (root.getRight() == node)) {
			System.out.print(root.getData() + " ");
			return true;
		}
		/* 如果当前节点的左子树中或右右子树中包含目标节点则打印当前节点 */
		if ((printAllAncestors(root.getLeft(), node)) || (printAllAncestors(root.getRight(), node))) {
			System.out.print(root.getData() + " ");
			return true;
		}
		return false;
	}
	
	/**
	 * 题目：查找二叉树中两个节点的最近公共祖先(LCA,Lowest Common Ancestor)
	 * 思路：
	 * 检查root是否与n或m节点相同，相同则返回根节点
	 * 检查左子树、右子树中是否存在n和m的最近公共祖先，若存在则返回；若左右子树都存在则返回根节点
	 * @param root 二叉树根节点
	 * @param n 二叉树节点
	 * @param m 二叉树节点
	 * @return 最近公共祖先节点
	 */
	public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> n, BinaryTreeNode<Integer> m) {
		/* 如果当前节点为空则返回 */
		if (root == null) {
			return root;
		}
		/* 如果n或m有一个为当前节点，则返回当前节点 */
		if ((root == n) || (root == m)) {
			return root;
		}
		/* 分别获取n和m在左子树、右子树中LCA */
		BinaryTreeNode<Integer> left = LCA(root.getLeft(), n, m);
		BinaryTreeNode<Integer> right = LCA(root.getRight(), n, m);
		
		if ((left != null) && (right != null)) {
			return root;
		} else {
			return (left != null) ? left :right;
		}
	}
	
	/*----------------------- 2017.05.15 -----------------------*/	
	
	/**
	 * 题目：Zigzag树遍历
	 * 思路：
	 * 例如下面二叉树的Zigzag遍历输出为：A CB DEF
	 * 						A
	 * 					   / \
	 * 					  B   C
	 * 					 / \ /
	 * 					D  E F
	 * 使用两个栈很容易解决。假设两个栈：currentLevel和nextLevel，以及一个变量来记录当前层的输出顺序(从左至右或从右至左)
	 * 节点从currentLevel栈出栈并输出，判断当前层的输出顺序：
	 * 如果当前层输出顺序为从左至右，按照先左孩子后右孩子的顺序压入nextLevel栈中；
	 * 如果当前层输出顺序为从右至左，按照先右孩子后左孩子的顺序压入nextLevel栈中；
	 * 因为栈是后进先出结构，所以节点从nextLevel出栈时按照相反顺序。
	 * 最后判断当前层是否为空，为空时交换两个栈。
	 * @param root 二叉树根节点
	 */
	public static void zigZagTraversal(BinaryTreeNode<Integer> root) {
		/* 根节点为空则返回 */
		if (root == null) {
			return;
		}
		
		/* 变量声明初始化 */
		// 打印方向标志
		boolean leftToRight = true;
		// 声明并初始化出栈元素变量
		BinaryTreeNode<Integer> temp = new BinaryTreeNode<Integer>();
		// 声明并初始化当前层栈
		LinkedListStack<BinaryTreeNode<Integer>> currentLevel = new LinkedListStack<BinaryTreeNode<Integer>>();
		// 声明并初始化下一层栈
		LinkedListStack<BinaryTreeNode<Integer>> nextLevel = new LinkedListStack<BinaryTreeNode<Integer>>();
		
		// 根节点先入栈
		currentLevel.push(root);
		
		/* 遍历并打印ZigZag */
		while (!currentLevel.isEmpty()) {
			// 获取当前层栈出栈元素
			temp = currentLevel.pop();
			// 输出当前层数据
			System.out.print(temp.getData());
			
			/* 确定下一层入栈数据 */
			if (leftToRight) {
				// 当前层是从左至右输出，则下一层从右至左输出，栈是先入后出
				if (temp.getLeft() != null) {
					nextLevel.push(temp.getLeft());
				}
				if (temp.getRight() != null) {
					nextLevel.push(temp.getRight());
				}
			} else {
				// 当前层是从右至左输出，则下一层从左至右输出，栈是先入后出
				if (temp.getRight() != null) {
					nextLevel.push(temp.getRight());
				}
				if (temp.getLeft() != null) {
					nextLevel.push(temp.getLeft());
				}
			}
			
			/* 如果当前层输出结束，则与下一层交换 */
			if (currentLevel.isEmpty()) {
				// 输出方向切换
				leftToRight = !leftToRight;
				// 交换当前层与下一层
				LinkedListStack<BinaryTreeNode<Integer>> tempLevel = currentLevel;
				currentLevel = nextLevel;
				nextLevel = tempLevel;
			}
		}
	}
	
	/**
	 * 题目：n个节点可以组合成多少棵不同的二叉树？
	 * 思路：
	 * 例如，考虑3个节点的树，最多有5(2^3 - 3)种不同的组合形式。如下：
	 * 	
	 * 			0				0			0			0				0
	 * 		   / \			   /		   /  			 \				 \
	 * 		  0   0           0			  0				  0				  0
	 * 						 /			   \			   \			 /
	 * 						0				0				0			0
	 * 
	 * 一般来讲，如果有n个节点，则存在(2^n - n)棵不同的二叉树
	 */
	
	
	/**
	 * 基于后缀表达式构建表达式树
	 * @param postExpr 后缀表达式
	 * @return 表达式树的根节点
	 */
	public static BinaryTreeNode<Character> BuildExprTree(char[] postExpr) {
		// 声明变量用来表示操作数或操作符
		BinaryTreeNode<Character> oper;
		// 声明并初始化栈用来存储操作数和操作符
		LinkedListStack<BinaryTreeNode<Character>> stack = new LinkedListStack<BinaryTreeNode<Character>>();
		
		/* 遍历后缀表达式 */
		for (int i = 0; i < postExpr.length; i++) {
			switch (postExpr[i]) {
			case '+':
			case '-':
			case '*':
			case '/':
				/* 如果是操作符，创建节点存储该操作符，并弹出两个操作数作为左右孩子，最后入栈*/
				oper = new BinaryTreeNode<Character>();
				oper.setData(postExpr[i]);
				oper.setRight(stack.pop());
				oper.setLeft(stack.pop());
				// 入栈
				stack.push(oper);
				break;
			default:
				/* 如果是操作数，创建节点存储数据并入栈 */
				oper = new BinaryTreeNode<Character>();
				oper.setData(postExpr[i]);
				oper.setRight(null);
				oper.setLeft(null);
				// 入栈
				stack.push(oper);
				break;
			}
		}
		return stack.pop();
	}
}





















