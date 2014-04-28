import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;

public class MiniVector<E> extends AbstractList <E> 
implements List<E> , RandomAccess , Serializable {	
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
    public MiniVector(int size){
    	//super();
    	this.size = 0;
        capacity = size;     	
		list = (E[])new Object[capacity];
		list = (E[]) Arrays.copyOf(list, capacity, Object[].class);
    }
   
	@Override
	public E get(int index) throws IndexOutOfBoundsException{
		
		if(index < 0 || index > size )
			throw new IndexOutOfBoundsException("Index : " + index);
		int i;
		for (i = 0; i < index ; ++i)
		  ;
		@SuppressWarnings("unchecked")
	    final E temp = (E)list[i];  
		
		return temp;
	}
    
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
    
	@Override
	public boolean add(E element){
 		
    	if(size < capacity){
    	   list[size++] = element;
    	}
    	else{
    	list = grow();
    	list[size++] = element;
    	}
    return true;
	}
	
	@Override
	public void add( int index, E element){
		
	}
	
	public void ensureCapacity(int minCapacity){
		
		if(minCapacity > capacity)
		    list = grow();
	}
	
    public int capacity(){
    	return capacity;
    }
	@SuppressWarnings("unchecked")
    private E[] grow(){
		
    	MiniVector<E> temp = new MiniVector<>(capacity*2);
        copy(temp.list, list, 0 , size/2); 
      	copy(temp.list, list, size/2, size);
      	this.capacity = temp.capacity;
      	
    return (E[])temp.list;
	}
    
    private void copy(Object[] target, Object[] original, int position, int size){
    	
    	if(position != size){
    		target[position] = original[position];
    		copy(target, original, ++position, size);		
    	}
    }
    
    public static void main(String[] args) {
		MiniVector<Integer> array = new MiniVector<>(5);
		
		array.add(5);
		array.add(5);
		array.add(5);
		array.add(5);
		array.add(5);
		array.add(5);
		array.add(7);
		for(int i =0 ; i < array.size; ++i)
			System.out.println(array.get(i));
	
		System.out.println(array.size);
    }
    
}
