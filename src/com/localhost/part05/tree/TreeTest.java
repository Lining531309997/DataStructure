package com.localhost.part05.tree;

import org.junit.Before;
import org.junit.Test;

public class TreeTest {

	/**
	 * 
	 * 							          二叉树
	 * 
	 * 								 1
	 * 								/ \
	 * 							   2   3
	 * 							  / \ / \
	 * 							 4  5 6  7
	 */
	BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>();
	BinaryTreeNode<Integer> btn1 = new BinaryTreeNode<Integer>(1);
	BinaryTreeNode<Integer> btn2 = new BinaryTreeNode<Integer>(2);
	BinaryTreeNode<Integer> btn3 = new BinaryTreeNode<Integer>(3);
	BinaryTreeNode<Integer> btn4 = new BinaryTreeNode<Integer>(4);
	BinaryTreeNode<Integer> btn5 = new BinaryTreeNode<Integer>(5);
	BinaryTreeNode<Integer> btn6 = new BinaryTreeNode<Integer>(6);
	BinaryTreeNode<Integer> btn7 = new BinaryTreeNode<Integer>(7);
	
	@Before
	public void init() {
		// 组成二叉树
		btn1.setLeft(btn2);
		btn1.setRight(btn3);
		btn2.setLeft(btn4);
		btn2.setRight(btn5);
		btn3.setLeft(btn6);
		btn3.setRight(btn7);
		// 设置二叉树根节点
		root = btn1;
	}
	
	/*
	 *  测试：查找二叉树中最大元素
	 *  结果：
	 *  二叉树中最大元素值：7
	 */
	@Test
	public void findMaxTest() {
		int max = BinaryTree.findMax(root);
		System.out.println("二叉树中最大元素值：" + max);
	}

	/*
	 *  测试：用非递归的方法查找二叉树中最大元素
	 *  结果：
	 *  二叉树中最大元素值(非递归)：7
	 */
	@Test
	public void findMaxUsingLevelOrderTest() {
		int max = BinaryTree.findMaxUsingLevelOrder(root);
		System.out.println("二叉树中最大元素值(非递归)：" + max);
	}
	
	/*
	 * 测试：搜索二叉树中某个元素
	 * 结果：
	 * 二叉树中是否包含元素11:false
	 */
	@Test
	public void hasDataTest() {
		int data = 11;
		boolean isExist = BinaryTree.hasData(root, data);
		System.out.println("二叉树中是否包含元素" + data + ":" + isExist);
	}
	
	/*
	 * 测试：用非递归方法搜索二叉树中某个元素
	 * 结果：
	 * 二叉树中是否包含元素5:true
	 */
	@Test
	public void hasDataByLevelOrderTest() {
		int data = 5;
		boolean isExist = BinaryTree.hasDataByLevelOrder(root, data);
		System.out.println("二叉树中是否包含元素" + data + ":" + isExist);
	}
}
