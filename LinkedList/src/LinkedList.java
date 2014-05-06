import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class LinkedList<E> extends AbstractSequentialList<E> implements
		List<E>, Serializable, Cloneable, Queue<E> {

	/**
	 * Computed serial ID
	 */
	private static final long serialVersionUID = 7532946786315141316L;
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

		if (head == null) {
			head = new Node<E>(e, null, head);
			last = head;
		} else {
			Node<E> temp = new Node<E>(e, last, null);
			last.next = temp;
			last = temp;
		}
		size++;
		return true;
	}

	@Override
	public void add(int index, E e) {

		if (index == 0 || head == null) {
			addFirst(e);
		}
		// Just add it to the end
		else if (index == size - 1)
			addLast(e);
		else {
			Node<E> runner = head;
			for (int i = 0; i < index; ++i)
				runner = runner.next;
			Node<E> temp = new Node<E>(e, runner.previous, runner);
			runner.previous.next = temp;
			runner.previous = temp;
		}
	}

	public void addFirst(E e) {

		if (head == null)
			add(e);
		else
			head = new Node<E>(e, null, head);
		size++;
	}

	public void addLast(E e) {
		add(e);
	}

	public E pop() throws NoSuchElementException {

		if (head == null)
			throw new NoSuchElementException();
		
		final E temp = head.type;
		head = head.next;
		return temp;
	}

	public E get() {
		return last.type;
	}

	@Override
	public boolean offer(E e) {

		return false;
	}

	@Override
	public E remove() {

		return null;
	}

	@Override
	public E poll() {

		return null;
	}

	@Override
	public E element() {
		return null;
	}

	@Override
	public E peek() {

		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {

		return null;
	}

	@Override
	public int size() {

		return size;
	}

	@SuppressWarnings("hiding")
	private class Node<E> {

		private E type;
		private Node<E> next;
		private Node<E> previous;

		private Node(E data, Node<E> past, Node<E> nextLink) {

			this.type = data;
			this.previous = past;
			this.next = nextLink;
		}
	}

	public static void main(String[] args) {

		LinkedList<Integer> list = new LinkedList<>();
		list.add(0,1);
		list.add(1,2);
		list.add(1,3);
		System.out.println(list.pop());
		System.out.println(list.pop());
		System.out.println(list.pop());

	}
}
