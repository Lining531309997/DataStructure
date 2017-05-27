package com.localhost.part12.GreedyAlgorithm;

import com.localhost.part05.tree.BinaryTreeNode;
import com.localhost.part06.priorityQueue.Heap;

public class GreedyAlgorithm {

	public static BinaryTreeNode<Integer> huffmanCodingAlgorithm(int[] array) {
		// 创建包含n个元素的优先队列
		Heap heap = new Heap(array.length,"small");
		// 数组建堆
		heap.buildHeap(heap, array);
		// 二叉树节点
		BinaryTreeNode<Integer> temp;
		for (int i = 0; i < array.length; i++) {
			temp = new BinaryTreeNode<Integer>();
			temp.setLeft(heap.deleteMin());
			temp.setRight(heap.deleteMin());
			temp.setData(temp.getLeft().getData() + temp.getRight().getData());
			heap.insert(temp.getData());
		}
		return temp;
	}
}
