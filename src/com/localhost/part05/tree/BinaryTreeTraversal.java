package com.localhost.part05.tree;

import com.localhost.part03.stack.LinkedListStack;
import com.localhost.part04.queue.LinkListQueue;

/**
 * 访问树中所有节点的过程叫作树遍历。
 * 
 * 遍历分类
 * 遍历分类可以根据当前节点被访问的顺序来划分
 * 当前节点用D表示
 * 该节点的左子树用L表示
 * 该节点的右子树用R表示
 * DLR：前序遍历(preOrder traversal)
 * LDR：中序遍历(inOrder traversal)
 * LRD：后序遍历(postOrder traversal)
 * 还有一种遍历方法，叫作层序遍历(level order traversal)，在该遍历中，所有深度为d的节点要在深度d+1的节点之前进行处理。
 *
 * 							          二叉树
 * 
 * 								 1
 * 								/ \
 * 							   2   3
 * 							  / \ / \
 * 							 4  5 6  7
 */
public class BinaryTreeTraversal<AnyType> {
	
	public static void main(String[] args) {
		// 创建二叉树节点
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>();
		BinaryTreeNode<Integer> btn1 = new BinaryTreeNode<Integer>(1);
		BinaryTreeNode<Integer> btn2 = new BinaryTreeNode<Integer>(2);
		BinaryTreeNode<Integer> btn3 = new BinaryTreeNode<Integer>(3);
		BinaryTreeNode<Integer> btn4 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> btn5 = new BinaryTreeNode<Integer>(5);
		BinaryTreeNode<Integer> btn6 = new BinaryTreeNode<Integer>(6);
		BinaryTreeNode<Integer> btn7 = new BinaryTreeNode<Integer>(7);
		
		// 组成二叉树
		btn1.setLeft(btn2);
		btn1.setRight(btn3);
		btn2.setLeft(btn4);
		btn2.setRight(btn5);
		btn3.setLeft(btn6);
		btn3.setRight(btn7);
		
		// 设置二叉树根节点
		root = btn1;
		
		// 测试递归遍历
		testTraversal(root);
		
		// 测试非递归遍历
		testNoTraversal(root);

	}
	
	/**
	 * 测试非递归遍历
	 * @param root 二叉树根节点
	 */
	public static void testNoTraversal(BinaryTreeNode<Integer> root) {
		BinaryTreeTraversal<Integer> btt = new BinaryTreeTraversal<Integer>();
		System.out.print("非递归前序遍历:");
		btt.preOrderNonRecursive(root);
		System.out.println();
		System.out.print("非递归中序遍历:");
		btt.inOrderNonRecursive(root);
		System.out.println();
		System.out.print("非递归后序遍历:");
		btt.postOrderNonRecursive(root);
		System.out.println();
	}
	/*	
 	非递归前序遍历:1 2 4 5 3 6 7 
	非递归中序遍历:4 2 5 1 6 3 7 
	非递归后序遍历:4 5 2 6 7 3 1 
	*/
	
	/**
	 * 测试递归遍历
	 * @param root 二叉树根节点
	 */
	public static void testTraversal(BinaryTreeNode<Integer> root) {
		BinaryTreeTraversal<Integer> btt = new BinaryTreeTraversal<Integer>();
		System.out.print("前序遍历:");
		btt.preOrder(root);
		System.out.println();
		System.out.print("中序遍历:");
		btt.inOrder(root);
		System.out.println();
		System.out.print("后序遍历:");
		btt.postOrder(root);
		System.out.println();
		System.out.print("层序遍历:");
		btt.levelOrder(root);
		System.out.println();
	}
	/*
	前序遍历:1 2 4 5 3 6 7 
	中序遍历:4 2 5 1 6 3 7 
	后序遍历:4 5 2 6 7 3 1 
	层序遍历:1 2 3 4 5 6 7 
	*/
	
	/**
	 * 前序遍历
	 * 1.访问根结点
	 * 2.按前序遍历方式遍历左子树
	 * 3.按前序遍历方式遍历右子树
	 * @param root 二叉树根节点
	 */
	public void preOrder(BinaryTreeNode<AnyType> root) {
		if (root != null) {
			System.out.print(root.getData() + " ");
			preOrder(root.getLeft());
			preOrder(root.getRight());
		}
	}
	
	/**
	 * 中序遍历
	 * 1.按前序遍历方式遍历左子树
	 * 2.访问根结点
	 * 3.按前序遍历方式遍历右子树
	 * @param root 二叉树根节点
	 */
	public void inOrder(BinaryTreeNode<AnyType> root) {
		if (root != null) {
			inOrder(root.getLeft());
			System.out.print(root.getData() + " ");
			inOrder(root.getRight());
		}
	}
	
	/**
	 * 后序遍历
	 * 1.按前序遍历方式遍历左子树
	 * 2.按前序遍历方式遍历右子树
	 * 3.访问根结点
	 * @param root 二叉树根节点
	 */
	public void postOrder(BinaryTreeNode<AnyType> root) {
		if (root != null) {
			postOrder(root.getLeft());
			postOrder(root.getRight());
			System.out.print(root.getData() + " ");
		}
	}
	
	/**
	 * 层序遍历
	 * 1.访问根节点
	 * 2.在访问第l层时，将l+1层的节点按顺序保存在队列中
	 * 3.进入下一层并访问该层的所有节点
	 * 4.重复上述操作直至所有层都访问完
	 * @param root 根节点
	 */
	public void levelOrder(BinaryTreeNode<AnyType> root) {
		if (root == null) {
			return;
		}
		BinaryTreeNode<AnyType> temp;
		LinkListQueue<BinaryTreeNode<AnyType>> queue = new LinkListQueue<BinaryTreeNode<AnyType>>();
		queue.enQueue(root);
		while (!queue.isEmpty()) {
			temp = queue.deQueue();
			// 处理当前节点
			System.out.print(temp.getData() + " ");
			if (temp.getLeft() != null) {
				queue.enQueue(temp.getLeft());
			}
			if (temp.getRight() != null) {
				queue.enQueue(temp.getRight());
			}
		}
	}
	
	/**
	 * 非递归前序遍历
	 * 首先处理当前节点，在遍历左子树之前，把当前节点保留到栈中，当遍历完左子树后，将该元素出栈，然后找到其右子树进行遍历。持续该过程直到栈为空。
	 * @param root 二叉树根节点
	 */
	public void preOrderNonRecursive(BinaryTreeNode<AnyType> root) {
		// 根节点为null，不作处理，直接结束
		if (root == null) {
			return;
		}
		// 创建栈存储节点
		LinkedListStack<BinaryTreeNode<AnyType>> stack = new LinkedListStack<BinaryTreeNode<AnyType>>();
		// 遍历
		while (true) {
			while (root != null) {
				System.out.print(root.getData() + " ");
				stack.push(root);
				root = root.getLeft();
			}
			if (stack.isEmpty()) {
				break;
			}
			root = stack.pop();
			root = root.getRight();
		}
	}
	
	/**
	 * 非递归中序遍历
	 * 首先移动到节点的左子树，完成遍历左子树之后，再将该元素出栈，然后找到其右子树进行遍历。持续该过程直到栈为空。
	 * @param root 二叉树根节点
	 */
	public void inOrderNonRecursive(BinaryTreeNode<AnyType> root) {
		// 根节点为null，不作处理，直接结束
		if (root == null) {
			return;
		}
		// 创建栈存储节点
		LinkedListStack<BinaryTreeNode<AnyType>> stack = new LinkedListStack<BinaryTreeNode<AnyType>>();
		// 遍历
		while (true) {
			while (root != null) {
				stack.push(root);
				root = root.getLeft();
			}
			if (stack.isEmpty()) {
				break;
			}
			root = stack.pop();
			System.out.print(root.getData() + " ");
			root = root.getRight();
		}
	}
	
	/**
	 * 非递归后序遍历
	 * 完成遍历左子树之后，需要访问当前节点，之后遍历完成右子树还需要访问该当前节点。只有在第二次访问时才处理当前节点。
	 * 如何区分两次访问？
	 * 方法：当从栈中出栈一个元素时，检查这个元素与栈顶元素的右子节点是否相同。如果相同，则说明已经完成左、右子树遍历。此时只需要将栈顶元素出栈输出。
	 * @param root 二叉树根节点
	 */
	public void postOrderNonRecursive(BinaryTreeNode<AnyType> root) {
		// 根节点为null，不作处理，直接结束
		if (root == null) {
			return;
		}
		// 创建栈存储节点
		LinkedListStack<BinaryTreeNode<AnyType>> stack = new LinkedListStack<BinaryTreeNode<AnyType>>();
		
		// 遍历
		while (true) {
			if (root != null) {
				stack.push(root);
				root = root.getLeft();
			} else {
				// 栈为空，结束程序
				if (stack.isEmpty()) {
					stack.deleteStack();
					return;
				} else {
					// 如果右子树为空，说明栈顶元素是叶子节点
					if (stack.top().getRight() == null) {
						root = stack.pop();
						System.out.print(root.getData() + " ");
						// 判断左右子树是否遍历完
						if (root == stack.top().getRight()) {
							System.out.print(stack.top().getData() + " ");
							root = stack.pop();
						}
					}
				}
				
				if (!stack.isEmpty()) {
					// 判断根节点左右子树是否遍历完
					if (root == stack.top().getRight()) {
						System.out.print(stack.top().getData() + " ");
						stack.pop();
						root = null;
					} else {
						root = stack.top().getRight();
					}
				} else {
					root = null;
				}
			}
		}
	}
}
