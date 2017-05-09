/**
 * @author Administrator
 *
 */
package com.localhost.part04.queue;

/*
 * 定义：
 * 队列是一种只能在一端插入(队尾)，在另一端删除(队首)的有序线性表。
 * 队列的第一个插入的元素也是第一个被删除的元素。所以，队列是一种先进先出(FIFO,First In First Out)或后进后出(LILO,Last In Last Out)线性表。
 * 
 * 队列操作的专有名称
 * 向队列中插入一个元素，称为入队(EnQueue)
 * 从队列中删除一个元素，称为出队(DnQueue)
 * 
 * 试图对一个空队列执行出队操作称为下溢(underflow)
 * 试图对一个满队列执行入队操作称为溢出(overflow)
 * 通常认为下溢和下溢是异常
 * 
 * 
 */