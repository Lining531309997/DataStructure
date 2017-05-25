package com.localhost.part10.Hash;

public class HashTable {
	
	// Hash表的表长
	private int length;
	
	// Hash表的元素个数
	private int count;
	
	// 使用数组实现HashTable
	private HashTableNode[] table;

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the table
	 */
	public HashTableNode[] getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(HashTableNode[] table) {
		this.table = table;
	}

	
	
}
