package com.localhost.part05.tree;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTest {

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
	
	/*
	 * 测试：向二叉树插入一个元素
	 * 结果：
	 * 插入元素8后的二叉树层序遍历：
	 * 1 2 3 4 5 6 7 8 
	 */
	@Test
	public void insertTest() {
		int data = 8;
		root = BinaryTree.insert(root, data);
		// 使用层序遍历
		BinaryTreeTraversal<Integer> btt = new BinaryTreeTraversal<Integer>();
		System.out.println("插入元素" + data + "后的二叉树层序遍历：");
		btt.levelOrder(root);
	}
	
	/*
	 * 测试：获取二叉树节点个数
	 * 结果：
	 * 二叉树节点个数：7
	 * 二叉树节点个数(非递归)：7
	 */
	@Test
	public void sizeTest() {
		int size1 = BinaryTree.size(root);
		int size2 = BinaryTree.sizeByLevelOrder(root);
		System.out.println("二叉树节点个数：" + size1);
		System.out.println("二叉树节点个数(非递归)：" + size2);
	}
	
	/*
	 * 测试：逆向逐层输出树中的元素
	 * 结果：
	 * 逐层逆向输出：4 5 6 7 2 3 1 
	 */
	@Test
	public void printLevelInReverseTest() {
		BinaryTree.printLevelInReverse(root);
	}
	
	/*
	 * 测试：求已知二叉树高度/深度
	 * 结果：
	 * 二叉树的高度/深度：3
	 * 二叉树的高度/深度(非递归)：3
	 */
	@Test
	public void getHeightTest() {
		int height1 = BinaryTree.getHeight(root);
		int height2 = BinaryTree.getHeightByLevelOrder(root);
		System.out.println("二叉树的高度/深度：" + height1);
		System.out.println("二叉树的高度/深度(非递归)：" + height2);
	}
	
	/*
	 * 测试：获取二叉树中最深节点
	 * 结果：
	 * 二叉树的高度/深度：3
	 * 二叉树的高度/深度(非递归)：3
	 */
	@Test
	public void getDeepestNodesTest() {
		BinaryTree.getDeepestNodes(root);
	}
	
	/*
	 * 测试：删除二叉树中某个节点
	 * 结果：
	 * 删除元素1后的二叉树层序遍历：7 2 3 4 5 6 
	 */
	@Test
	public void deleteBinaryTreeNodeTest() {
		int data = 1;
		root = BinaryTree.deleteBinaryTreeNode(root, data);
		BinaryTreeTraversal<Integer> btt = new BinaryTreeTraversal<Integer>();
		System.out.print("删除元素" + data + "后的二叉树层序遍历：");
		btt.levelOrder(root);
	}
	
	/*
	 * 测试：用非递归算法获取二叉树叶子节点个数
	 * 结果：
	 * 二叉树叶子节点的个数：4
	 * 测试：用非递归算法获取二叉树满节点个数
	 * 结果：
	 * 二叉树满节点的个数：3
	 * 测试：用非递归算法获取二叉树半节点个数
	 * 结果：
	 * 二叉树半节点的个数：0
	 */
	@Test
	public void getNodeNumTest() {
		int count1 = BinaryTree.getLeafNodeNum(root);
		System.out.println("二叉树叶子节点的个数：" + count1);
		
		int count2 = BinaryTree.getFullNodeNum(root);
		System.out.println("二叉树满节点的个数：" + count2);
		
		int count3 = BinaryTree.getHalfNodeNum(root);
		System.out.println("二叉树半节点的个数：" + count3);
	}
	
	/*
	 * 测试：判断给定的两棵树结构是否相同
	 * 结果：
	 * 两棵树是否相同：true
	 */
	@Test
	public void AreStructullySameTreesTest() {
		BinaryTreeNode<Integer> root2 = root;
		System.out.println("两棵树是否相同：" + BinaryTree.AreStructullySameTrees(root, root2));
	}
	
	/*
	 * 测试：二叉树的直径
	 * 结果：
	 * 二叉树的直径：4
	 */
	@Test
	public void diameterOfBinaryTreeTest() {
		// 计算二叉树直径
		BinaryTree.diameterOfBinaryTree(root);
		System.out.println("二叉树的直径：" + BinaryTree.diameter);
	}
	
	/*
	 * 测试：输出所有从根节点到子节点的路径
	 * 结果：
	 * 1 2 4 0 0 0 0 0 0 0 
	 * 1 2 5 0 0 0 0 0 0 0 
	 * 1 3 6 0 0 0 0 0 0 0 
	 * 1 3 7 0 0 0 0 0 0 0 
	 */
	@Test
	public void printPathsTest() {
		int[] path = new int[10];
		BinaryTree.printPaths(root, path, 0);
	}
	
	/*
	 * 测试：判断是否存在路径的数据和等于给定的值
	 * 结果：
	 * 二叉树路径的数据和等于18:false
	 */
	@Test
	public void hasPathSumTest() {
		int sum = 18;
		System.out.println("二叉树路径的数据和等于" + sum + ":" + BinaryTree.hasPathSum(root, sum));
	}
	
	/*
	 * 测试：计算二叉树所有节点之和
	 * 结果：
	 * 二叉树节点累加和：28
	 * 二叉树节点累加和(非递归)：28
	 */
	@Test
	public void getSumTest() {
		System.out.println("二叉树节点累加和：" + BinaryTree.getSum(root));
		System.out.println("二叉树节点累加和(非递归)：" + BinaryTree.getSumByLevelOrder(root));
	}
	
	/*
	 * 测试：将一棵树转换成其镜像树。
	 * 结果：
	 * 原二叉树层序遍历：1 2 3 4 5 6 7
	 * 原二叉树镜像后层序遍历：1 3 2 7 6 5 4 
	 */
	@Test
	public void mirrorOfBinaryTreeTest() {
		BinaryTreeTraversal<Integer> btt = new BinaryTreeTraversal<Integer>();
		System.out.print("原二叉树层序遍历：");
		btt.levelOrder(root);
		System.out.println();
		root = BinaryTree.mirrorOfBinaryTree(root);
		System.out.print("原二叉树镜像后层序遍历：");
		btt.levelOrder(root);
	}
	
	/*
	 * 测试：判断两棵树是否互为镜像
	 * 结果：
	 * 两棵二叉树是否为镜像：false
	 */
	@Test
	public void areMirrorsTest() {
		BinaryTreeNode<Integer> root1 = root;
		BinaryTreeNode<Integer> root2 = root;
		System.out.println("两棵二叉树是否为镜像：" + BinaryTree.areMirrors(root1, root2));
	}
	
	/*
	 * 测试：根据中序遍历序列和前序遍历序列构建二叉树
	 * 结果：
	 * 两棵二叉树是否为镜像：false
	 */
	@Test
	public void buildBinaryTreeTest() {
		String[] inOrder = {"D", "B", "E", "A", "F", "C"};
		String[] preOrder = {"A", "B", "D", "E", "C", "F"};
		BinaryTreeNode<String> root = BinaryTree.buildBinaryTree(inOrder, preOrder, 0, 5);
		BinaryTreeTraversal<String> btt = new BinaryTreeTraversal<String>();
		System.out.print("二叉树前序遍历：");
		btt.preOrder(root);
		System.out.println();
		System.out.print("二叉树中序遍历：");
		btt.inOrder(root);
	}
	
	/*
	 * 测试：打印二叉树中某节点的所有祖先节点
	 * 结果：
	 * 节点7的祖先节点：3 1 
	 */
	@Test
	public void printAllAncestorsTest() {
		BinaryTreeNode<Integer> node = btn7;
		System.out.print("节点" + node.getData() + "的祖先节点：");
		BinaryTree.printAllAncestors(root, node);
	}
	
	/*
	 * 测试：查找二叉树中两个节点的最近公共祖先(LCA,Lowest Common Ancestor)
	 * 结果：
	 * 节点2节点6的最近公共祖先节点：1
	 */
	@Test
	public void LCATest() {
		BinaryTreeNode<Integer> n = btn2;
		BinaryTreeNode<Integer> m = btn6;
		System.out.print("节点" + n.getData() + "节点" + m.getData() + "的最近公共祖先节点：" + BinaryTree.LCA(root, n, m).getData());
	}
	
	/*
	 * 测试：Zigzag树遍历
	 * 结果：
	 * ZigZag遍历：1324567
	 */
	@Test
	public void zigZagTraversalTest() {
		System.out.print("ZigZag遍历：");
		BinaryTree.zigZagTraversal(root);
	}
	
	/*
	 * 测试：Zigzag树遍历
	 * 结果：
	 * 表达式二叉树层序遍历：/ + D A * B C 
	 */
	@Test
	public void BuildExprTreeTest() {
		char[] postExpr = {'A', 'B', 'C', '*', '+', 'D', '/'};
		BinaryTreeNode<Character> root = BinaryTree.BuildExprTree(postExpr);
		
		BinaryTreeTraversal<Character> btt = new BinaryTreeTraversal<Character>();
		System.out.print("表达式二叉树层序遍历：");
		btt.levelOrder(root);
	}
	
	
}








