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
	private Object[] list;
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

	@Override
	public E get(int index) throws IndexOutOfBoundsException {

		throwException(index);
		@SuppressWarnings("unchecked")
		final E temp = (E) list[index];

		return temp;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

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

	public E firstElement() throws NoSuchElementException {

		if (size == 0)
			throw new NoSuchElementException();
		@SuppressWarnings("unchecked")
		final E first = (E) list[0];
		return first;
	}

	public E lastElement() throws NoSuchElementException {

		if (size == 0)
			throw new NoSuchElementException();
		@SuppressWarnings("unchecked")
		final E last = (E) list[size-1];
		return last;
	}

	@Override
	public void add(int index, E element) throws ArrayIndexOutOfBoundsException {

		throwException(index);
		for(int i = size; i > index; --i)
			list[i] = list[i-1];
		list[index] = element;
		
	}
	
	public E remove(int index) throws ArrayIndexOutOfBoundsException{
		
		throwException(index);
        @SuppressWarnings("unchecked")
		final E temp = (E)list[index];
		
        for(int i = index; i < size; ++i)
    		list[i] = list[i+1];
			
		return temp;	
	}
    public void removeElementAt(int index) throws ArrayIndexOutOfBoundsException{
    	
    	throwException(index);
    	for(int i = index; i < size; ++i)
    		list[i] = list[i+1];
    	--size;
    }
	public void ensureCapacity(int minCapacity) {

		if (minCapacity > capacity)
			list = grow();
	}

	public int capacity() {
		return capacity;
	}
	
    public void setElementAt(E obj, int index)throws ArrayIndexOutOfBoundsException{
    	
    	throwException(index);
    	
    	list[index] = obj;  	
    }
    
    private void throwException(int index){
    	
    	if( index < 0 || index >= size)
    		throw new ArrayIndexOutOfBoundsException(index);
    }
    
	@SuppressWarnings("unchecked")
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

	public static void main(String[] args) {
		MiniVector<Integer> array = new MiniVector<>(5);
        
		array.add(1);
		array.add(2);
		array.add(3);
		array.add(4);
		array.add(5);
		array.add(6);
		array.add(7);
		array.add(0, 16);
        array.removeElementAt(14);
		for (int i = 0; i < array.size; ++i)
			System.out.println(array.get(i));
		
		//System.out.println(array.lastElement());
	}

}
