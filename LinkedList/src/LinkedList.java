import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

public class LinkedList<E> extends AbstractSequentialList<E> implements
		List<E>, Serializable, Cloneable, Queue<E> {

	private Node<E> head;
	private Node<E> last;
	private int size;

	public LinkedList() {

		head = null;
		last = null;
		size = 0;
	}

	@Override
	public boolean add(E e) {

		 if(head == null){
			head = new Node<E>(e, null ,head);
		    last = head;
		 }
		 else{
			 Node<E> temp = new Node<E>(e,last, null);
			 last.next = temp;
			 last = temp; 
		 }
			return true;
	}
	@Override
    public void add(int index, E e ){

		if(index == 0 || head == null){
			
		}
    }
	
	public void addFirst(E e){
		
		if(head == null)
			add(e);
		else
			head = new Node<E>(e, null, head);
	}
	public E pop() {
		 E temp = null;
	     if(head != null){
	         temp = head.type;
	         head = head.next;
	     }
		return temp;
	}

	public E get() {
		return last.type;
	}

	@Override
	public boolean offer(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("hiding")
	private class Node<E> {

		private E type;
		private Node<E> next;
		private Node<E> previous;

		private Node(E data, Node<E> past ,Node<E> nextLink) {

			this.type = data;
			this.previous = past;
			this.next = nextLink;
		}
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		list.addFirst(10);
		list.addFirst(11);
		list.addFirst(12);
		System.out.println(list.pop());
		System.out.println(list.pop());
		System.out.println(list.pop());
		

	}
}
