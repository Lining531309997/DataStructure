package com.localhost.part06.priorityQueue;

/**
 * 最大堆
 * @author Administrator
 *
 */
public class Heap {

	// 堆元素数组
	private int[] array;		
	
	// 堆中元素的个数
	private int size;
	
	// 堆的大小
	private int capacity;
	
	// 堆的类型 
	private String heap_type;

	/**
	 * 构造方法：根据大小和类型创建堆
	 * 
	 * @param capacity 堆的大小
	 * @param heap_type 堆的类型
	 */
	public Heap(int capacity, String heap_type) {
		this.size = 0;
		this.capacity = capacity;
		this.heap_type = heap_type;
		this.array = new int[capacity];
	}

	/**
	 * 根据节点的位置获取双亲结点的的位置
	 * 
	 * 对于第i个位置上的节点，则其双亲节点在(i-1)/2位置上
	 * @param index 节点的位置
	 * @return 双亲结点的位置
	 */
	public int getParentByIndex(int index) {
		if ((index < 0) || (index >= this.size)) {
			return -1;
		}
		return (index - 1)/2;
	}
	
	/**
	 * 根据节点位置获取左孩子节点的位置
	 * 
	 * 对于第i个位置上的节点，则其左孩子节点在2*i+1位置上
	 * @param index 节点位置
	 * @return 左孩子节点位置
	 */
	public int leftChild(int index) {
		int left = 2 * index + 1;
		if (left >= this.size) {
			return -1;
		}
		return left;
	}
	
	/**
	 * 根据节点位置获取右孩子节点的位置
	 * 
	 * 对于第i个位置上的节点，则其右孩子节点在2*i+2位置上
	 * @param index 节点位置
	 * @return 右孩子节点位置
	 */
	public int rightChild(int index) {
		int right = 2 * index + 2;
		if (right >= this.size) {
			return -1;
		}
		return right;
	}
	
	/**
	 * 获取最大元素
	 * 
	 * 最大堆中的最大元素就是根节点，所以其存储在数组0号位置
	 * @return 最大元素
	 */
	public int getMax() {
		if (this.size == 0) {
			return -1;
		}
		return this.array[0];
	}
	
	/**
	 * 堆化当前元素
	 * 
	 * 当插入或删除一个堆元素时，它可能不满足堆的性质。在这种情况下就需要调整堆中的元素使之重新变成堆。这一过程叫作堆化(heapifying)
	 * 在最大堆中，要堆化一个元素，需要找到其孩子节点中的最大值，然后交换元素，重复该过程直到每个节点都满足堆的性质。这一过程是自顶向下，所以也叫作向下渗透。
	 * @param index 当前元素的位置
	 */
	public void percolateDown(int index) {
		int max = 0;
		/* 先获取当前节点的孩子节点 */
		int left = leftChild(index);
		int right = rightChild(index);
		/* 比较当前节点与其孩子节点的值 */
		if ((left != -1) && (this.array[left] > this.array[index])) {
			max = left;
		} else {
			max = index;
		}
		if ((right != -1) && (this.array[right] > this.array[max])) {
			max = right;
		} 
		/* 如果当前节点不是最大值则交换元素 */
		if (max != index) {
			int temp = this.array[index];
			this.array[index] = this.array[max];
			this.array[max] = temp;
			/* 递归堆化 */
			percolateDown(max);
		} 
	}
	
	/**
	 * 删除堆元素
	 * 
	 * 删除堆元素，只需要从根节点删除元素。
	 * 删除根节点后，将堆的最后一个元素复制到根节点位置，然后删除最后一个元素。
	 * 当用最后一个元素替换根节点后，可能导致二叉树不满足堆的性质，所以需要堆化根节点元素。
	 * @return 删除的元素
	 */ 
	public int deleteMax() {
		/* 先判断堆是否存在 */
		if (this.size == 0) {
			return -1;
		}
		// 获取根节点元素
		int data = this.array[0];
		// 将最后一个元素复制到第一个元素位置
		this.array[0] = this.array[this.size - 1];
		// 堆的大小减小
		this.size--;
		/* 堆化根节点元素，即数组第一个位置的元素 */
		percolateDown(0);
		// 返回删除的元素
		return data;
	}
	
	/**
	 * 插入元素
	 * 
	 * 堆的大小增加一
	 * 将新元素放到堆的尾部
	 * 从下至上的堆化该元素，也称作向上渗透
	 * @param data 插入的新元素
	 */
	public void insert(int data) {
		/* 如果数组容量不够则扩容 */
		if (this.size == this.capacity) {
			resizeHeap();
		}
		// 数组元素个数加一
		this.size++;
		// 新元素的位置
		int index = this.size - 1;
		/* 堆化元素，向上渗透 */
		while ((index >= 0) && (data > this.array[(index - 1) / 2])) {
			// 当新元素大于其双亲结点，复制双亲结点到新元素位置
			this.array[index] = this.array[(index - 1) / 2];
			// 更新新元素位置
			index = (index - 1) / 2;
		}
		this.array[index] = data;
	}
	
	/**
	 * 清空堆
	 */
	public void destroyHeap() {
		this.size = 0;
		this.array = null;
	}
	
	/**
	 * 数组建堆
	 * 
	 * 先将数组元素插入空堆中，不考虑堆的性质。
	 * 因为叶子节点总是满足堆的性质，无需关注它们。
	 * 叶子节点元素总是在最后面，因此要对给定的数组建堆，只需要堆化叶子节点即可。
	 * 
	 * 堆的最后一个元素所处的位置为size-1,通过最后一个元素的双亲节点就能找到第一个非叶子节点((size-1)-1)/2。
	 * @param heap 堆
	 * @param array 数组
	 */
	public void buildHeap(Heap heap, int[] array) {
		// 获取数组的大小
		int n = array.length;
		/* 如果堆不存在则返回 */
		if (heap == null) {
			return;
		}
		/* 如果堆的容量不够则扩容 */
		while (n > this.capacity) {
			heap.resizeHeap();
		}
		/* 先将数组元素存入堆中 */
		for (int i = 0; i < n; i++) {
			this.array[i] = array[i];
		}
		// 设置堆的大小
		this.size = n;
		// 堆化非叶子节点元素
		for (int i = (n-2)/2; i >= 0; i--) {
			heap.percolateDown(i);
		}
	}
	
	/**
	 * 堆排序
	 * 
	 * 堆排序算法首先将所有元素插入堆中，然后从堆中依次删除根节点元素，直到堆为空
	 * @param array 无序数组
	 * @return 排序后的数组
	 */
	public int[] heapSort(int[] array) {
		// 获取数组大小
		int n = array.length;
		// 创建堆
		Heap heap = new Heap(n, "big");
		// 数组建堆
		heap.buildHeap(heap, array);
		int temp = 0;
		for (int i = 0; i < n; i++) { 
			// 获取根节点元素
			temp = heap.array[0];
			// 将最后一个元素复制到第一个元素位置
			heap.array[0] = heap.array[heap.size - 1];
			// 堆的大小减小
			heap.size--;
			/* 堆化根节点元素，即数组第一个位置的元素 */
			heap.percolateDown(0);
			// 存储删除的元素
			array[i] = temp;
		}
		return array;
	}
	
	/**
	 * 内部方法：堆扩容
	 */
	private void resizeHeap() {
		// 创建新的数组，大小为原来数组的两倍
		int[] newArray = new int[2 * capacity];
		// 复制原数组到新数组
		System.arraycopy(this.array, 0, newArray, 0, this.size);
		// 重置数组容量
		capacity = capacity * 2;
		// 复制给原数组，也就是使array指向新生成内存
		array = newArray;
	}
	
	/**
	 * @return the array
	 */
	public int[] getArray() {
		return array;
	}

	/**
	 * @param array the array to set
	 */
	public void setArray(int[] array) {
		this.array = array;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the heap_type
	 */
	public String getHeap_type() {
		return heap_type;
	}

	/**
	 * @param heap_type the heap_type to set
	 */
	public void setHeap_type(String heap_type) {
		this.heap_type = heap_type;
	}

}
