/**
 * 
 */
package CS401;

import java.util.Comparator;

/**
 * @author piyush
 * We are using Fair Pririoty queue to maintain the address of the elements in form of Fair Element insted of E,Here fair element acts as a carrier
 * which will have the element as well the count for that element. 
 */
public class FairPQ<E> extends CS401PriorityQueue<E>{

	public static final int DEFAULT_INITIAL_CAPACITY = 11;
	protected long count;
	public FairPQ() 
	   {                     
	        super (DEFAULT_INITIAL_CAPACITY, new Natural<E>());
	        count = 0;
	   } 
	public FairPQ (int size, Comparator<E> comp) 
	   {         
	      super (size, new Unnatural<E> (comp));  
	      count = 0;
	   } 
	   public boolean add (E element) 
	   {    
	      return super.add ((E)new FairElement<E> (element, count++));    
	   } // method add
	   public E remove() 
	   {
	       FairElement<E> fairElement = (FairElement<E>)super.remove();
	    
	       return (E)fairElement.element;    
	   } // method remove 
	   
}

/*******
 * 
 * @author piyush
 * We are keeping both natural comparator process and unnatural comparator process to keep a track if we want to maintain based on age,
 * we keep another tag of count,so that it can be used to judge the time its been there
 * @param <E>
 */
class Natural<E> implements Comparator<E>
{
    public int compare (E e1, E e2) 
    {
        int result = ((Comparable)(((FairElement)e1).element)).compareTo(((FairElement)e2).element);
       
        if (result != 0)
            return result;
        return (int)(((FairElement)e1).count - ((FairElement)e2).count);//This will only occur in case there is a equal operation
    } // method compare

}
class Unnatural<E> implements Comparator<E>
{
    protected Comparator<E> comp;
    
    public Unnatural (Comparator<E> comp)
    {
        this.comp = comp;
    } // constructor
    
    public int compare (E e1, E e2) 
    {  			
        int result = comp.compare( (E)(((FairElement)e1).element), (E)(((FairElement)e2).element));
        if (result != 0)
            return result;
        return (int)(((FairElement)e1).count - ((FairElement)e2).count);       //This will only occur in case there is a equal operation  
    } // method compare
  
} 

