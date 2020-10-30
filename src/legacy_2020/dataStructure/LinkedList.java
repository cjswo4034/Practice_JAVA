package dataStructure;

public class LinkedList<T> {
	private Node<T> head, tail;
	private int size;
	
	public LinkedList(){
		head = tail = new Node<>();
		size = 0;
	}
	
	public void add(T t){
		Node<T> node = new Node<>();
		node.data = t;
		tail.next = node;
		tail = node;
		size++;
	}

	public T get(int idx) {
		if (size <= idx || 0 > idx) throw new IndexOutOfBoundsException();

		Node<T> curr = head.next;
		int i = 0;
		
		while(curr.next != null) {
			if (i++ == idx) break;
			curr = curr.next;
		}
		
		return curr.data;
	}

	public boolean contain(T t) {
		Node<T> curr = head;
		while(curr.next != null) {
			if (curr.next.data.equals(t)) return true;
			curr = curr.next;
		}
		return false;
	}

	public boolean remove(T t) {
		Node<T> curr = head;
		while(curr.next != null) {
			if(curr.next.data.equals(t)) {
				curr.next = curr.next.next;
				return true;
			}
			curr = curr.next;
		}
		
		return false;
	}

	public boolean remove(int idx) {
		if (size <= idx || 0 > idx) throw new IndexOutOfBoundsException();
		
		Node<T> curr = head.next;
		int i = 0;
		
		while(curr.next != null) {
			if (i++ == idx) {
				curr.next = curr.next.next;
				return true;
			}
			curr = curr.next;
		}
		
		return false;
	}
	
	public int getSize() {
		return this.size;
	}
	
	class Node<T>{
		Node<T> next;
		T data;
	}
}
