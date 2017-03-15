// Public class representing a parameterized Node
public class Node<T extends Comparable>{

	// Private data (Type: T)
	private T data;
	// Next pointer (Node<T>) for linked structure
	private Node<T> next = null;

	// Constructor to build Node<T> with no next
	public Node(T data) {
		this.data = data;
	}

	// Constructor to build Node<T> given next pointer
	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}

	// Accessor to get data
	public T getData() {
		return data;
	}

	// Accessor to get next node
	public Node<T> getNext() {
		return next;
	}
	
	public void setNext(Node<T> newNext){
		next = newNext;
	}

	// Public toString method to print out the Node<T> in simple form
	public String toString() {
		return data.toString();
	}

}