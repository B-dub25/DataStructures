import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.Collection;
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

		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		if (index == 0 || head == null) {
			addFirst(e);
		}
		// Just add it to the end
		else if (index == size)
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

	@Override
	public boolean addAll(Collection<? extends E> c) {

		if (c == null)
			return false;

		Iterator<? extends E> iter = (Iterator<? extends E>) c.iterator();
		while (iter.hasNext())
			addLast(iter.next());
		return true;

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

	@Override
	public boolean offer(E e) {
		addLast(e);
		return true;
	}

	public boolean offerFirst(E e) {

		addFirst(e);
		return true;
	}

	public boolean offerLast(E e) {

		addLast(e);
		return true;
	}

	@Override
	public E set(int index, E e) {

		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index " + index + " and Size "
					+ size);
		Node<E> runner = head;
		if (index > size / 2) {
			runner = last;
			for (int i = size; i > index; ++i)
				runner = runner.previous;
		} else
			for (int i = 1; i < index; ++i)
				runner = runner.next;
		final E element = runner.type;
		runner.type = e;
		return element;
	}

	public E pop() throws NoSuchElementException {

		if (head == null)
			throw new NoSuchElementException();

		final E temp = head.type;
		head = head.next;
		return temp;
	}

	public E get(int index) {

		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();

		Node<E> runner = head;
		for (int i = 1; i < index; ++i)
			runner = runner.next;

		return runner.type;
	}

	public E getFirst() {

		if (head == null)
			throw new NoSuchElementException("Empty list");
		final E element = head.type;
		return element;
	}

	public E getLast() {

		if (head == null)
			throw new NoSuchElementException("empty list");
		final E element = last.type;
		return element;
	}

	@Override
	public E remove() {

		if (head == null)
			throw new NoSuchElementException("empty list");
		final E temp = head.type;
		head = head.next;
		return temp;
	}

	public E removeFirst() throws NoSuchElementException {

		return pop();
	}

	public E removeLast() {

		if (head == null)
			throw new NoSuchElementException("empty list");
		final E element = last.type;
		last = last.previous;
		last.next = null;
		return element;
	}

	public boolean removeFirstOccurenceOf(Object o) {

		Node<E> runner = head;
		if (runner != null && runner.type.equals(o)) {
			remove();
		} else {
			while (runner != null)
				if (runner.type.equals(o)) {
					runner.previous.next = runner.next;
					runner = runner.next;
					break;
				} else
					runner = runner.next;
			// wasn't in the list
		}
		return runner != null;
	}

	public boolean removeLastOccurenceOf(Object o) {

		if (head == null)
			return false;
		else {
			Node<E> firstRunner = head;
			Node<E> secondRunner = null;

			while (firstRunner != null)
				if (firstRunner.type.equals(o)) {
					secondRunner = firstRunner;
					firstRunner = firstRunner.next;
				} else
					firstRunner = firstRunner.next;

			// was no in the list
			if (secondRunner == null)
				return false;
			if (secondRunner == head) {
				pop();
			} else {
				secondRunner.previous.next = secondRunner.next;
				secondRunner = secondRunner.next;
			}
		}

		return true;
	}

	@Override
	public E poll() {
		// protect if the list is a list of objects
		final E temp;

		if (head == null)
			temp = null;
		else {
			temp = head.type;
			head = head.next;
		}
		return temp;

	}

	public E pollFirst() {

		return poll();
	}

	public E pollLast() {

		final E temp;

		if (head == null)
			temp = null;
		else {
			temp = last.type;
			last = last.previous;
		}
		return temp;
	}

	@Override
	public E element() {

		if (head == null)
			throw new NoSuchElementException("empty list");
		final E temp = head.type;
		return temp;
	}

	@Override
	public E peek() {

		return (head == null) ? null : head.type;
	}

	public E peekFirst() {
		return peek();
	}

	public E peekLast() {

		return (last == null) ? null : last.type;
	}

	@Override
	public boolean contains(Object o) {

		Node<E> frontRunner = head;
		Node<E> backRunner = last;

		while (frontRunner != null)
			if (frontRunner.type.equals(o) || backRunner.type.equals(o))
				return true;
			else {
				// should bring worst case to be O(n/2) I'll look into a better
				// way to do this
				if (frontRunner == backRunner)
					return false;
				frontRunner = frontRunner.next;
				backRunner = backRunner.previous;
			}

		return false;
	}

	public int indexOf(Object o) {

		Node<E> runner = head;
		for (int i = 0; i < size; ++i)
			if (runner.type.equals(o))
				return i;
			else
				runner = runner.next;
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public void clear() {

		head = null;
		last = null;
		System.gc();
	}

	@Override
	public ListIterator<E> listIterator(int index) {

		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index " + index + " Size "
					+ size);
		Iterator<E> iter = new Iterator<>();
		if (index >= size / 2) {
			iter.iterator = last;
			for (int i = size; i > index; --i)
				iter.iterator = iter.iterator.previous;
		}
		return (ListIterator<E>) iter;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String toString() {

		Node<E> runner = head;
		StringBuilder builder = new StringBuilder("[");
		while (runner != last) {
			builder.append(runner.type + ", ");
			runner = runner.next;
		}
		return builder.append(runner.type + "]").toString();

	}

	 @SuppressWarnings("unchecked")
	public boolean equals(Object o) {

		if (o == null)
			return false;
		else if (getClass() != o.getClass())
			return false;

		else if (this.size != ((LinkedList<E>) o).size)
			return false;
		else {
			Node<E> runner = head;
			Node<E> otherRunner = (Node<E>) o;
			
			while (runner != null && otherRunner != null)
				if (!runner.type.equals(otherRunner.type))
					return false;
				else {
					runner = runner.next;
					otherRunner = otherRunner.next;
				}
		}

		return true;
	}

	// TODO add last object to match lists.
	@SuppressWarnings("hiding")
	public class Iterator<E> implements ListIterator<E> {

		private Node<E> iterator;
		private int index = 0;

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Iterator() {
			index = 0;
			iterator = new Node(head);
		}

		@Override
		public boolean hasNext() {
			return iterator != null;
		}

		@Override
		public E next() {

			if (iterator == null)
				throw new NoSuchElementException();

			final E element = iterator.type;
			iterator = iterator.next;
			index++;
			return element;
		}

		@Override
		public boolean hasPrevious() {
			return index != 0;
		}

		@Override
		public E previous() {
			if (index == 0)
				throw new NoSuchElementException();
			final E element = iterator.previous.type;
			iterator = iterator.previous;
			--index;

			return element;
		}

		@Override
		public int nextIndex() {
			if (iterator == last)
				return size;

			return index + 1;
		}

		@Override
		public int previousIndex() {

			if (iterator == head)
				return -1;

			return index - 1;
		}

		@Override
		public void remove() {
			if (iterator == head)
				iterator = iterator.next;
			else {
				iterator.previous.next = iterator.next;
				iterator = iterator.next;
				--size;
			}

		}

		@Override
		public void set(E e) {
			iterator.type = e;
		}

		@Override
		public void add(E e) {
			iterator = new Node<E>(e, null, iterator);
			++size;
		}

	}

	@SuppressWarnings("hiding")
	private final class Node<E> {

		private E type;
		private Node<E> next;
		private Node<E> previous;

		private Node(E data, Node<E> past, Node<E> nextLink) {

			this.type = data;
			this.previous = past;
			this.next = nextLink;
		}

		private Node(Node<E> n) {
			next = n.next;
			previous = n.previous;
			type = n.type;
		}
	}

}
