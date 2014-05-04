import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class MiniVector<E> extends AbstractList<E> implements List<E>,
		RandomAccess, Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = -6535583477063192586L;
	private transient E[] list;
	private int size;
	private int capacity;

	public MiniVector() {
		this(10);
	}

	@SuppressWarnings("unchecked")
	public MiniVector(int size) {

		this.size = 0;
		capacity = size;
		list = (E[]) new Object[capacity];
		list = (E[]) Arrays.copyOf(list, capacity, Object[].class);
	}
    
	public MiniVector(MiniVector<E> vect){
    	
    	this.size = vect.size;
    	this.capacity = vect.capacity;
    	copy(list, vect.list, 0, size/2);
    	copy(list, vect.list, size/2, size);
    
    }
	// Setters
	@Override
	public boolean add(E element) {

		if (size < capacity) {
			list[size++] = element;
		} else {
			list = grow();
			list[size++] = element;
		}
		return true;
	}

	@Override
	public void add(int index, E element) throws ArrayIndexOutOfBoundsException {

		throwException(index);
		for (int i = size; i > index; --i)
			list[i] = list[i - 1];
		list[index] = element;
		size++;

	}
    
	public void setElementAt(E obj, int index)
			throws ArrayIndexOutOfBoundsException {

		throwException(index);

		list[index] = obj;
	}

	public void trimToSize() {
		capacity = size;
	}

	// Getters
	@Override
	public E get(int index) throws IndexOutOfBoundsException {

		throwException(index);

		final E temp = (E) list[index];
		return temp;
	}

	@Override
	public int lastIndexOf(Object obj) {

		int position = -1;
		for (int i = 0; i < size; ++i)
			if (list[i].equals(obj))
				position = i;
		return position;
	}

	public int lastIndexOf(Object obj, int index) {

		int position = -1;
		for (int i = index; i > 0; --i)
			if (list[i].equals(obj))
				position = i;
		return position;
	}

	@Override
	public int size() {

		return size;
	}

	public int capacity() {
		return capacity;
	}

	public E firstElement() throws NoSuchElementException {

		if (size == 0)
			throw new NoSuchElementException();

		final E first = (E) list[0];
		return first;
	}

	public E lastElement() throws NoSuchElementException {

		if (size == 0)
			throw new NoSuchElementException();

		final E last = (E) list[size - 1];
		return last;
	}

	// Deleter(s)
	public E remove(int index) throws ArrayIndexOutOfBoundsException {

		throwException(index);

		final E temp = (E) list[index];

		for (int i = index; i < size; ++i)
			list[i] = list[i + 1];

		return temp;
	}

	@Override
	public boolean remove(Object obj) {

		int position = 0;

		while (position < size && !list[position++].equals(obj) )	
			
		if (position < size) {
			System.err.println(position);
			for (int i = position; i < size; ++i)
				list[i] = list[i + 1];
			--size;
			return true;
		}
		return false;
	}

	public void removeElementAt(int index)
			throws ArrayIndexOutOfBoundsException {

		throwException(index);
		for (int i = index; i < size; ++i)
			list[i] = list[i + 1];
		--size;
	}

	// Helpers
	public void ensureCapacity(int minCapacity) {

		if (minCapacity > capacity)
			list = grow();
		
	}
	@Override
    public List<E> subList(int fromIndex, int toIndex){
    	
		throwException(fromIndex);
		throwException(toIndex);
		if(fromIndex > toIndex)
				 new IllegalArgumentException("fromIndex is larger than toIndex ");
		
		List<E> link = new MiniVector<>();
    	for(int i = fromIndex; i < toIndex; ++i){
    		 link.add(list[i]);
    	}
    	
    return link;	
    }
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder("[");
		builder.append(list[0]);
		for (int i = 1; i < size; i++) {
			builder.append(", ");
			builder.append(list[i]);
		}

		return String.valueOf(builder.append("]"));
	}
	//Java's clone is broken so this is just an method that
	//calls and returns the copy constructor.  
	//@Override
    public MiniVector<E> clone(){
    	return new MiniVector<E>(this);
    }
	private void throwException(int index) {

		if (index < 0 || index >= size)
			throw new ArrayIndexOutOfBoundsException(index);
	}

	private E[] grow() {

		MiniVector<E> temp = new MiniVector<>(capacity * 2);
		copy(temp.list, list, 0, size / 2);
		copy(temp.list, list, size / 2, size);
		this.capacity = temp.capacity;

		return (E[]) temp.list;
	}

	private void copy(Object[] target, Object[] original, int position, int size) {

		if (position != size) {
			target[position] = original[position];
			copy(target, original, ++position, size);
		}
	}
}
