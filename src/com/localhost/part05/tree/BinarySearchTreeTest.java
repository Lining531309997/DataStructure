package com.localhost.part05.tree;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {

	/**
	 * 
	 * 							      二叉搜索树
	 * 
	 * 								10
	 * 							    / \
	 * 							   6   16
	 * 							  / \ / 
	 * 							 4  9 13  
	 * 							   /
	 * 							  7
	 */
	BinarySearchTreeNode<Integer> root = new BinarySearchTreeNode<Integer>();
	BinarySearchTreeNode<Integer> btn1 = new BinarySearchTreeNode<Integer>(10);
	BinarySearchTreeNode<Integer> btn2 = new BinarySearchTreeNode<Integer>(6);
	BinarySearchTreeNode<Integer> btn3 = new BinarySearchTreeNode<Integer>(16);
	BinarySearchTreeNode<Integer> btn4 = new BinarySearchTreeNode<Integer>(4);
	BinarySearchTreeNode<Integer> btn5 = new BinarySearchTreeNode<Integer>(9);
	BinarySearchTreeNode<Integer> btn6 = new BinarySearchTreeNode<Integer>(13);
	BinarySearchTreeNode<Integer> btn7 = new BinarySearchTreeNode<Integer>(7);
	
	@Before
	public void init() {
		// 组成二叉树
		btn1.setLeft(btn2);
		btn1.setRight(btn3);
		btn2.setLeft(btn4);
		btn2.setRight(btn5);
		btn3.setLeft(btn6);
		btn5.setLeft(btn7);
		// 设置二叉树根节点
		root = btn1;
	}

	/**
	 * 测试：查找元素
	 * 结果：
	 * 递归方式：9
	 * 非递归方式：9
	 */
	@Test
	public void findTest() {
		int data = 9;
		BinarySearchTreeNode<Integer> node = BinarySearchTree.find(root, data);
		System.out.println("递归方式：" + node.getData());
		node = BinarySearchTree.findNode(root, data);
		System.out.println("非递归方式：" + node.getData());
	}
	
	/**
	 * 测试：查找最小元素
	 * 结果：
	 * 递归方式：4
	 * 非递归方式：4
	 */
	@Test
	public void findMinTest() {
		BinarySearchTreeNode<Integer> node = BinarySearchTree.findMin(root);
		System.out.println("递归方式：" + node.getData());
		node = BinarySearchTree.findMinNode(root);
		System.out.println("非递归方式：" + node.getData());
	}
	
	/**
	 * 测试：查找最大元素
	 * 结果：
	 * 递归方式：16
	 * 非递归方式：16
	 */
	@Test
	public void findMaxTest() {
		BinarySearchTreeNode<Integer> node = BinarySearchTree.findMax(root);
		System.out.println("递归方式：" + node.getData());
		node = BinarySearchTree.findMaxNode(root);
		System.out.println("非递归方式：" + node.getData());
	}
	
}








