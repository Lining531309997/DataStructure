package com.localhost.part07.graph;

/**
 * 邻接矩阵
 * 图的表示需要顶点数、边数以及他们之间的连接关系。
 * 邻接矩阵采用一个大小为V*V的矩阵Adj，其中矩阵的值为布尔值。如果存在一条从顶点u到顶点v的边，则设置Adj[u,v]为true,否则false
 * 在矩阵中，无向图的每一条边可以用两个二进制数表示。即一条从u到v的边用Adj[u,v]=true和Adj[v,u]=true表示。为了节约时间，只需要处理该对称矩阵的上三角或下三角元素。
 * 对于所有顶点Adj[u,u]=true。
 * 
 * 以无向图为例
 */
public class Graph_Matrix {

	// 邻接矩阵
	private boolean adjMatrix[][];
	
	// 顶点个数
	private int vertexCount;

	/**
	 * 构造方法
	 * @param vertexCount
	 */
	public Graph_Matrix(int vertexCount) {
		super();
		this.vertexCount = vertexCount;
		this.adjMatrix = new boolean[vertexCount][vertexCount];
	}
	
	public void addEdge(int i, int j) {
		if ((i >= 0 && i < vertexCount) && (j > 0 && j < vertexCount)) {
			adjMatrix[i][j] = true;
			adjMatrix[j][i] = true;
		}
	}
	
	public void removeEdge(int i, int j) {
		if ((i >= 0 && i < vertexCount) && (j > 0 && j < vertexCount)) {
			adjMatrix[i][j] = false;
			adjMatrix[j][i] = false;
		}
	}
	
	public boolean isEdge(int i, int j) {
		if ((i >= 0 && i < vertexCount) && (j > 0 && j < vertexCount)) {
			return adjMatrix[i][j];
		} else {
			 return false;
		}
	}

	/**
	 * @return the adjMatrix
	 */
	public boolean[][] getAdjMatrix() {
		return adjMatrix;
	}

	/**
	 * @param adjMatrix the adjMatrix to set
	 */
	public void setAdjMatrix(boolean[][] adjMatrix) {
		this.adjMatrix = adjMatrix;
	}

	/**
	 * @return the vertexCount
	 */
	public int getVertexCount() {
		return vertexCount;
	}

	/**
	 * @param vertexCount the vertexCount to set
	 */
	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}
	
}
