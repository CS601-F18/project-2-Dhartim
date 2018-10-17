package cs601.project2;

import java.util.concurrent.TimeUnit;
/**
 *
 * @author dhartimadeka
 * BlockingQueue - Data structure that has take, put, size and poll method to manipulate data in blocking queue
 * @param <T>
 */
public class CS601BlockingQueue<T> {

	private T[] items;
	private int start;
	private int end;
	private int size;

	@SuppressWarnings("unchecked")
	public CS601BlockingQueue(int size) {
		this.items = (T[]) new Object[size];
		this.start = 0;
		this.end = -1;
		this.size = 0;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Put - puts item into blocking queue
	 * @param item
	 */
	public synchronized void put(T item) {

		while (size == items.length) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int next = (end + 1) % items.length;
		items[next] = item;
		end = next;
		size++;
		if (size == 1) {
			this.notifyAll();
		}
	}
	/**
	 * Take - returns an item from blocking queue
	 * @return 
	 */
	public synchronized T take() {

		while (size == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		T item = items[start];
		start = (start + 1) % items.length;
		size--;
		if (size == items.length - 1) {
			this.notifyAll();
		}
		return item;
	}
	/**
	 * isEmpty - checks size of blocking queue
	 * @return a boolean value
	 */
	public synchronized boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Poll - to remove data from blocking queue, if not present then return null.
	 * 
	 * @param time - time to wait to poll out data from queue
	 * @param timeUnit - unit of time 
	 * @return
	 */
	public synchronized T poll(long time, TimeUnit timeUnit) {
		while (size == 0) {
			try {
				this.wait(time);
				if(size <= 0) {
					return null;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		T item = items[start];
		start = (start + 1) % items.length;
		size--;
		if (size == items.length - 1) {
			this.notifyAll();
		}
		return item;
	}
}
