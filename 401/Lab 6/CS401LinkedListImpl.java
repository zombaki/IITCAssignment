import java.util.Iterator;

public class CS401LinkedListImpl<E> implements CS401CollectionInterface<E>,Iterable<E>
{
   protected LinkEntry<E> head;
   protected LinkEntry<E> tail;
   protected int num_elements = 0;

   public CS401LinkedListImpl()
   {
      head = tail = null;
   }

   /**
    * Methods inherited from CS401CollectionInterface
    */
   public boolean is_empty()
   {
      if (head == null) 
          return true;

      return false;
   }

   public boolean is_full() { return false; }

   public int size() { return num_elements; }

   /* 
    * Adds element e at the end of the linked list. */
   public boolean add(E e)
   {
      add(Where.BACK, e);
      
      return true;
   }

   /**
    * Remove element indicated by i.
    * If the element exists in the collection, return that element back 
    * to the user.  If index is out of bounds, return null.
    */
   public E remove(int i)
   {
      /**
       * Add code here. */
	   CS401LinkedListIterator it=new CS401LinkedListIterator();
	   E e=null;LinkEntry<E> temp;
	   if (i<0 || i>num_elements)
	   {
		   return null;
	   }
	   int count=1;
	   if(i==1)
	   {
		   temp=head;
		   e=temp.element;
		   head=head.next;
		}
	   	else
	   	{
	   		while(count<i)
	   		{
	   			it.next();
		    	e=it.current.element;
		    	count++;
	   		}
	   		it.remove();
	     
	      }
	      num_elements--;

           return e;
      
      
   }

   /**
    * Determines if e is in the collection. 
    * Hint: Remember lecture on comparison.
    * Returns true if e is in the collection, false otherwise. */
   public boolean contains(E e)
   {
      /**
       * Add code here. */
	   CS401LinkedListIterator it = new CS401LinkedListIterator();
	   while(it.hasNext()){
		   if (it.next()==e)
			   return true;
		   					   
	   }
      return false;
   }

   /**
    * Add e to either front of the linked list or the end of the linked
    * list, depending on the value of the parameter where.
    * If where == MIDDLE, return false.
    * Returns true if element added to back or front, or false if asked
    * to add in the middle. */
   public boolean add(Where where, E e)  {

      if (where == Where.MIDDLE) 
          return false;

      LinkEntry<E> ne = new LinkEntry<E>();
      ne.element = e;

      if (head == null && tail == null)
      {
          head = tail = ne;
          num_elements++;
          return true;
      }

      if (where == Where.BACK) {
         tail.next = ne;
         tail = ne;
      }
      else if (where == Where.FRONT)  {
          /**
           * Add code here. */
    	  ne.next=head;
    	  head=ne;
      }
      num_elements++;
      return true;
   }

   /**
    * Add e to the middle of a linked list.  More specifically, add e
    * after index in the linked list.  
    * First element of the linked list is 1, second is 2, and so on.
    *
    * Returns true if element added, false if where != MIDDLE. */
   public boolean add(Where where, int index, E e)  {
	   LinkEntry<E> ne = new LinkEntry<E>();
	   LinkEntry<E> temp = head;
	   ne.element = e;
	   if (where == Where.MIDDLE) {
	         for (int i = 1 ;i<index;i++){
	        	 if (temp.next == null)
	        		 return false;
	        	 else
	        		 temp=temp.next;
	         }
	         num_elements++;
	         ne.next=temp.next;
	         temp.next=ne;
	         return true;
	      }
	   else
	   {
		   return add(where,e);
	   }
      /**
       * Add code here. */
	   
      //return true;
   }

   /**
    * Print the collection class (linked list) by iterating over all of
    * the elements in the collection class and serializing them. 
    * Returns a Java String object that represents the serialization of
    * the collection class. */
   public String toString()  {

      StringBuilder result = new StringBuilder();
 
      /**
       * Add code here. 
       * Traverse the linked list and serialize each object.  Add the
       * serialized form to result. */
      CS401LinkedListIterator it=new CS401LinkedListIterator();
      while(it.hasNext())
      {
    	  result.append(it.next());
      }
      return result.toString();
   }
   

   /* ------------------------------------------------------------------- */
   /* Inner classes                                                      */
   protected class LinkEntry<E>
   {
      protected E element;
      protected LinkEntry<E> next;
   }
   /* ------------------------------------------------------------------- */


	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new CS401LinkedListIterator();
	}
	protected class CS401LinkedListIterator implements Iterator<E>
	 {
		 protected LinkEntry<E> current;
		 protected LinkEntry<E> previous;
		 protected CS401LinkedListIterator() { current = head; previous=null;}
		 public E next()
		 {
		 /* To be filled in by students … */
			 E e = current.element;
			 previous=current;
	        if (current.next != null)  {
	            current = current.next;
	        }
	        else {
	        	 current = null;
	        }
	        return e;
		 }
		 public boolean hasNext() { return current != null; }
		 public void remove() /* Remove the element under the iterator. */
		 {
		 /*
		 * To be filled in by students ... */
			 if(current==head){
				 if (current.next ==null)
					 head=current=previous=tail=null;//When we are removing first and thats the only element remaining
				 else
					 head=current.next;
			 }
			 else if (current.next!=null){
				 current=current.next;
			 	previous.next=current;
			 }
			 else{//When removing element is the last element of linked list
				 previous.next=null;
				 current=previous;
				 tail=current;
				 
			 }
			 return;
		 }
	 } /* class CS401LinkedListIterator() */

} /* CS401LinkedListImpl<E> */

