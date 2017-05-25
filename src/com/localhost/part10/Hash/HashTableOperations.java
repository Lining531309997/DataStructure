package com.localhost.part10.Hash;

public class HashTableOperations {

	public final static int LOADFACTOR = 20;
	
	/**
	 * 创建散列表
	 * @param size 散列表的元素个数
	 * @return
	 */
	public static HashTable createHashTable(int size) {
		// 初始化散列表
		HashTable table = new HashTable();
		table.setLength(size / LOADFACTOR);
		table.setCount(0);
		for (int i = 0; i < table.getLength(); i++) {
			table.getTable()[i].setStartNode(null);
		}
		return table;
	}
	
	/**
	 * 散列表查找
	 * @param table 散列表
	 * @param data 目标数据
	 * @return 
	 */
	public static boolean hashSearch(HashTable table, int data) {
		// 获取该元素在散列表中对应位置上的链表头节点
		ListNode temp = table.getTable()[hash(data, table)].getStartNode();
		// 遍历链表查找元素
		while (temp != null) {
			if (temp.getData() == data) {
				return true;
			} else {
				temp = temp.getNext();
			}
		}
		return false;
	}
	
	/**
	 * 散列插入
	 * @param table
	 * @param data
	 */
	public static void hashInsert(HashTable table, int data) {
		// 先判断元素是否存在
		if (hashSearch(table, data)) {
			return;
		}
		// 获取元素对应的散列码
		int index = hash(data, table);
		// 获取散列码位置上的链表
		ListNode temp = table.getTable()[index].getStartNode();
		// 初始化新节点
		ListNode newNode = new ListNode();
		newNode.setKey(index);
		newNode.setData(data);
		newNode.setNext(temp);
		// 更新散列码位置上的链表头节点以及链表节点的个数
		table.getTable()[index].setStartNode(newNode);
		table.getTable()[index].setBlockCount(table.getTable()[index].getBlockCount() + 1);
		// 更新散列表的元素个数
		table.setCount(table.getCount() + 1);
		// 解决冲突
		if ((table.getCount() / table.getLength()) > LOADFACTOR) {
			rehash(table);
		}
	}
	
	/**
	 * 解决散列冲突
	 * @param table
	 */
	public static void rehash(HashTable table) {
		// 散列表长度
		int oldLength = table.getLength();
		// 散列表
		HashTableNode[] oldTable = table.getTable();
		// 初始化新的散列表
		table = new HashTable();
		// 更新散列表的长度
		table.setLength(oldLength * 2);
		// 初始化临时变量
		ListNode temp = null;
		ListNode temp2 = null;
		// 散列码
		int index = 0;
		for (int i = 0; i < oldLength; i++) {
			for (temp = oldTable[i].getStartNode(); temp != null;) {
				// 获取散列码
				index = hash(temp.getData(), table);
				temp2 = temp;
				temp = temp.getNext();
				// ？？？？
				temp2.setNext(table.getTable()[i].getStartNode());
				// 更新散列码位置上的链表头节点
				table.getTable()[index].setStartNode(temp2);
			}
		}
	}
	
	/**
	 * 散列表删除元素
	 * @param table
	 * @param data
	 * @return
	 */
	public static boolean hashDelete(HashTable table, int data) {
		// 获取散列码
		int index = hash(data, table);
		// 初始化目标节点的前驱节点
		ListNode prev = null;
		// 初始化临时变量
		ListNode temp = null;
		for (temp = table.getTable()[index].getStartNode(); temp != null;) {
			if (temp.getData() == data) {
				if (prev != null) {
					prev.setNext(temp.getNext());
				}
				temp = null;
				table.getTable()[index].setBlockCount(table.getTable()[index].getBlockCount() - 1);
				table.setCount(table.getCount() - 1);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取散列码，大小不超过表的大小
	 * @param data 
	 * @param table
	 * @return
	 */
    public static int hash(int data, HashTable table){  
        return data % table.getLength();  
    } 
}
