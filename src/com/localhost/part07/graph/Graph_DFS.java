package com.localhost.part07.graph;

import java.util.Stack;

public class Graph_DFS<AnyType> {

	// 顶点最大个数
	private final int MAXVERTICES = 20;
	
	// 顶点访问表
	private Vertex<AnyType> vertexList[];
	
	// 邻接矩阵
	private int adjMatrix[][];
	
	// 顶点个数
	private int vertexCount;
	
	private Stack<Integer> theStack;

	/**
	 * 构造方法
	 * @param vertexCount
	 */
	@SuppressWarnings("unchecked")
	public Graph_DFS() {
		this.vertexList = new Vertex[MAXVERTICES];
		this.adjMatrix = new int[MAXVERTICES][MAXVERTICES];
		this.vertexCount = 0;
		for (int i = 0; i < MAXVERTICES; i++) {
			for (int j = 0; j < MAXVERTICES; j++) {
				this.adjMatrix[i][j] = 0;
			}
		}
		this.theStack = new Stack<>();
	}
	
	/**
	 * 添加顶点
	 * @param label 顶点元素
	 */
	public void addVertex(AnyType label) {
		vertexList[vertexCount++] = new Vertex<AnyType>(label);
	}
	
	/**
	 * 添加边
	 * @param start 源点
	 * @param end 终点
	 */
	public void addEdge(int start, int end) {
		adjMatrix[start][end] = 1;
		adjMatrix[end][start] = 1;
	}
	
	/**
	 * 打印顶点
	 * @param v 顶点
	 */
	public void displayVertex(int v) {
		System.out.print(vertexList[v].getLabel());
	}
	
	/**
	 * 深度优先搜索
	 */
	public void dfs() {
		// 默认第一个顶点首先被访问
		vertexList[0].setVisited(true);
		// 打印第一个顶点的值
		displayVertex(0);
		// 记录被访问的顶点下标
		theStack.push(0);
		
		while (!theStack.isEmpty()) {
			// 获取未被访问的顶点
			int v = getAdjUnvisitedVertex(theStack.peek());
			if (v == -1) {
				theStack.pop();
			} else {
				vertexList[v].setVisited(true);
				displayVertex(v);
				theStack.push(v);
			}
		}
		
		// 初始化标记
		for (int i = 0; i < vertexCount; i++) {
			vertexList[i].setVisited(false);
		}
	}
	
	/**
	 * 根据已被访问顶点的下标获取未被访问的顶点下标
	 * @param v 被访问过的顶点下标
	 * @return 未被访问顶点的下标
	 */
	public int getAdjUnvisitedVertex(int v) {
		for (int i = 0; i < vertexCount; i++) {
			if (adjMatrix[v][i] == 1 && vertexList[i].isVisited() == false) {
				return i;
			}
		}
		return -1;
	}
}

class Vertex<AnyType> {
	public AnyType label;
	public boolean visited;
	public Vertex(AnyType label) {
		super();
		this.label = label;
		this.visited = false;
	}
	/**
	 * @return the label
	 */
	public AnyType getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(AnyType label) {
		this.label = label;
	}
	/**
	 * @return the visited
	 */
	public boolean isVisited() {
		return visited;
	}
	/**
	 * @param visited the visited to set
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
}