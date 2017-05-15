package com.localhost.part05.tree;

/**
 * 二叉搜索树
 */
public class BinarySearchTree {

	/**
	 * 查找元素
	 * @param root 二叉搜索树的根节点
	 * @param data 需要查找的目标元素
	 * @return 目标元素的所在的节点
	 */
	public static BinarySearchTreeNode<Integer> find(BinarySearchTreeNode<Integer> root, int data) {
		/* 当前节点为空直接返回空 */
		if (root == null) {
			return null;
		}
		
		/* 递归查找 */
		if (data > root.getData()) {
			return find(root.getRight(), data);
		} else if (data < root.getData()) {
			return find(root.getLeft(), data);
		}
		
		return root;
	}
	
	/**
	 * 查找元素(非递归)
	 * @param root 二叉搜索树的根节点
	 * @param data 需要查找的目标元素
	 * @return 目标元素的所在的节点
	 */
	public static BinarySearchTreeNode<Integer> findNode(BinarySearchTreeNode<Integer> root, int data) {
		/* 当前节点为空直接返回空 */
		if (root == null) {
			return null;
		}
		/* 遍历搜索 */
		while (root != null) {
			if (root.getData() == data) {
				return root;
			} else if (root.getData() > data) {
				root = root.getLeft();
			} else {
				root = root.getRight();
			}
		}
		return null;
	}
	
	/**
	 * 查找二叉搜索树中的最小元素
	 * @param root 二叉搜索树的根节点
	 * @return 最小元素节点
	 */
	public static BinarySearchTreeNode<Integer> findMin(BinarySearchTreeNode<Integer> root) {
		/* 当前节点为空直接返回空 */
		if (root == null) {
			return null;
		}
		/* 递归搜索 */
		if (root.getLeft() == null) {
			return root;
		} else {
			return findMin(root.getLeft());
		}
	}
	
	/**
	 * 查找二叉搜索树中的最小元素(非递归)
	 * @param root 二叉搜索树的根节点
	 * @return 最小元素节点
	 */
	public static BinarySearchTreeNode<Integer> findMinNode(BinarySearchTreeNode<Integer> root) {
		/* 当前节点为空直接返回空 */
		if (root == null) {
			return null;
		}
		/* 遍历搜索 */
		while (root.getLeft() != null) {
			root = root.getLeft();
		}
		return root;
	}
	
	/**
	 * 查找二叉搜索树中的最大元素
	 * @param root 二叉搜索树的根节点
	 * @return 最大元素节点
	 */
	public static BinarySearchTreeNode<Integer> findMax(BinarySearchTreeNode<Integer> root) {
		/* 当前节点为空直接返回空 */
		if (root == null) {
			return null;
		}
		/* 递归搜索 */
		if (root.getRight() == null) {
			return root;
		} else {
			return findMax(root.getRight());
		}
	}
	
	/**
	 * 查找二叉搜索树中的最大元素(非递归)
	 * @param root 二叉搜索树的根节点
	 * @return 最大元素节点
	 */
	public static BinarySearchTreeNode<Integer> findMaxNode(BinarySearchTreeNode<Integer> root) {
		/* 当前节点为空直接返回空 */
		if (root == null) {
			return null;
		}
		/* 遍历搜索 */
		while (root.getRight() != null) {
			root = root.getRight();
		}
		return root;
	}
	
    /**
     * 内部方法：向二叉查找树中添加元素
     * @param x 添加的元素值
     * @param t 二叉查找树
     * @return 添加新元素后的二叉查找树根节点
     */
    public static BinarySearchTreeNode<Integer> insert(int data, BinarySearchTreeNode<Integer> root) {
        // 如果该根节点为空，创建新的节点
        if (root == null) {
            return new BinarySearchTreeNode<>(data);
        }
        
        if (data < root.getData()) {
            root.setLeft(insert(data, root.getLeft())); 
        } else if (data > root.getData()) {
        	 root.setRight(insert(data, root.getRight())); 
        } 
        return root;
    }

    /**
     * 内部方法：按顺序打印二叉查找树的结点
     * @param t 二叉查找树的根节点
     */
    public static void printTree(BinarySearchTreeNode<Integer> root) {
        if (root != null) {
            printTree(root.getLeft());
            System.out.println(root.getData());
            printTree(root.getRight());
        }
    }

    /**
     * 内部方法：从子树中删除元素
     * @param x 需要删除的目标元素
     * @param t 子树的根节点
     * @return 删除元素后的子树根节点
     */
    public static BinarySearchTreeNode<Integer> remove(int data, BinarySearchTreeNode<Integer> root) {
        // 如果子树的根节点为空，直接返回null
        if (root == null) {
            return null;
        }
        
        if (data < root.getData()) {
            root.setLeft(remove(data, root.getLeft()));
        } else if (data > root.getData()) {
        	root.setRight(remove(data, root.getRight()));
        } else if (root.getLeft() != null && root.getRight() != null) {
            // 左右子树都存在，则查找右子树中最小元素
        	// 也可以使用左子树中最大元素代替
            root.setData(findMin(root.getRight()).getData());
            // 返回右子树根节点
            root.setRight(remove(data, root.getRight()));
        } else {
            // 返回存在的子树
            root = (root.getLeft() != null) ? root.getLeft() : root.getRight();
        }
        return root;
    }
}
