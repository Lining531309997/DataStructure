package com.localhost.part05.tree;

/**
 * 二叉树节点
 * 节点包含数据域、左子树域和右子树域
 * @param <AnyType> 节点的数据类型
 */
public class BinaryTreeNode<AnyType> {

	// 节点的数据域
	private AnyType data;
	
	// 节点的左子树
	private BinaryTreeNode<AnyType> left;
	
	// 节点的右子树
	private BinaryTreeNode<AnyType> right;
	
	/**
	 * 构造方法
	 */
	public BinaryTreeNode() {}

	/**
	 * 构造方法
	 * @param data 数据域
	 */
	public BinaryTreeNode(AnyType data) {
		this(data, null, null);
	}

	/**
	 * 构造方法
	 * @param data 数据域
	 * @param left 左子树
	 * @param right 右子树
	 */
	private BinaryTreeNode(AnyType data, BinaryTreeNode<AnyType> left, BinaryTreeNode<AnyType> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	/**
	 * @return the data
	 */
	public AnyType getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(AnyType data) {
		this.data = data;
	}

	/**
	 * @return the left
	 */
	public BinaryTreeNode<AnyType> getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(BinaryTreeNode<AnyType> left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public BinaryTreeNode<AnyType> getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(BinaryTreeNode<AnyType> right) {
		this.right = right;
	}
}
