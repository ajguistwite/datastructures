//Andrew Guistwite 
//SortedList

import java.lang.reflect.Array;

public class SortedList<T extends Comparable>{

	private Node<Integer> head = null;
	private int length = 0;

	// empty Sorted list
	public SortedList() {
	}

	// checks if the list is empty
	public boolean isEmpty() {
		return (head == null);
	}

	// size of the linked list
	public int size() {
		return (length);
	}

	// clears list
	public void clear() {
		head = null;
		length = 0;
	}

	// returns data from node in linked list
	public Integer get(int pos) {
		pos -= 1;

		if (pos > length || pos < 0) {
			return null;
		} else {
			Node<Integer> p = head;
			for (int i = 0; i < pos; i++) {
				if (p == null) {
					return null;
				}
				p = p.getNext();
			}
			if (p == null) {
				return null;
			}
			return p.getData();
		}
	}

	// Adds item to list 
	public boolean add(Integer item) {
		if (head == null) {
			head = new Node<Integer>(item,head);
			length++;
			return true;
			}
		else if (head.getData().compareTo(item) >= 0){
			head = new Node<Integer>(item, head);
			length++;
			return true;
			}
		else {
			Node<Integer> p = head;
			p.setNext(new Node(item, p.getNext()));
			length++;
			return true;
		}
	}

	//Find method...Need to return [] and item
	public int find(int[]Integer, int item) { 
		for(int i=0;i<Integer.length;i++){
            if(Integer[i]==item){
                break;
            }
        }
    return (item);
	    
	}

	// removes item from list
	public boolean remove(Integer item) {
		Node<Integer> p = head;
		Node<Integer> previous = null;
		while (p != null) {
			if (p.getData().equals(item)) {
				if (previous == null) {
					head = p.getNext();
				} else {
					previous.setNext(p.getNext());
				}
				length = length - 1;
				return true;
			}
			previous = p;
			p = p.getNext();
		}
		return false;
	}

	// true if list contains item T
	public boolean contains(T item) {
		Node<Integer> p = head;
		while (p != null) {
			if (p.getData().equals(item)) {
				return true;
			}
			p = p.getNext();
		}
		return false;
	}

	// prints out items string list
	public String toString() {
		Node<Integer> p = head;
		String ret = "";
		while (p != null) {
			ret = ret + p.toString();
			if (p.getNext() != null) {
				ret = ret + ", ";
			}
			p = p.getNext();
		}
		return ("[" + ret + "]");
	}

}