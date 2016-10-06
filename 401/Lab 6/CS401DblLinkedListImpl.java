import java.util.Iterator;


public class CS401DblLinkedListImpl<E> extends CS401LinkedListImpl<E> 
{
   private LinkEntry<E> head;
   private LinkEntry<E> tail;
   private int num_elements = 0;

   public CS401DblLinkedListImpl()
   {
      head = tail = null;
   }

   public boolean is_empty()
   {
      if (head == null) 
          return true;

      return false;
   }

   public boolean is_full() { return false; }

   public int size() { return num_elements; }

   /*
    * Add e to the end of the doubly linked list.
    * Returns true - if e was successfully added, false otherwise.
    */
   public boolean add(E e)
   {
      /** Add code here **/ 
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
	   DbLinkedListIterator it= new DbLinkedListIterator("Front");
	   E e=null;LinkEntry<E> temp;
	   
	   if (i<0 || i>num_elements)
	   {
		   return null;
	   }
	   if(i==1)
	   {
		   temp=head;
		   e=temp.element;
		   head=head.next;
		   head.previous=null;
		}
	   else  if(i==num_elements)
	   {
		   temp =tail; 
		   if(head.next==null)
			   head=tail=null;
		   else{
			   tail.previous.next=null;
		   tail=tail.previous;
		   }
		   
	   }
	   else
	   {
		   int count=1;
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
	   DbLinkedListIterator it= new DbLinkedListIterator("Front");
	   boolean status=false;
	   while(it.hasNext())
	   {
		   if(it.next()==e)
			   status=true;;
	   }
	   return status;
      
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

      /**
       * Add code here.
       * Hint: follow the same logic as 
       * CS401LinkedListImpl::add(Where where, E e) except account for 
       * the previous and next references in the doubly linked list. */
      if (head == null && tail == null)
      {
          head = tail = ne;
          num_elements++;
          return true;
      }
      else if (where == Where.BACK) {
          tail.next = ne;
          ne.previous=tail;
          tail = ne;
       }
       else if (where == Where.FRONT)  {
    	   ne.next=head;
    	   head.previous=ne;
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

      /**
       * Add code here. 
       * Hint: follow the same logic as 
       * CS401LinkedListImpl::add(Where where, int index, E e) except 
       * account for the previous and next references in the doubly 
       * linked list. */
	   LinkEntry<E> ne = new LinkEntry<E>();
	      ne.element = e;


	      DbLinkedListIterator it= new DbLinkedListIterator("Front");
	      for (int i = 1 ;i<=index;i++){
	        	it.next();
	         }
	      LinkEntry<E> temp=it.current;
	      ne.previous=it.previous;
	      it.previous.next=ne;
	      ne.next=temp;
	      temp.previous=ne;
	      num_elements++;
	      return true;
   }

   /**
    * Print the doubly linked list starting at the beginning.
    */
   public void print_from_beginning()
   {
      /** Add code here **/
	   DbLinkedListIterator it= new DbLinkedListIterator("Front");
	   while(it.hasNext())
	   {
		   
		   System.out.print(it.next());
		   
	   }
	   System.out.print("\n");
      return;
   }

   /**
    * Print the doubly linked list starting the end.
    */
   public void print_from_end()
   {
      /** Add code here **/
	   DbLinkedListIterator it= new DbLinkedListIterator("Back");
	   while(it.hasPrevious())
	   {
		   
		   System.out.print(it.previous());
		   
	   }
	   System.out.print("\n");
      return;
      
   }

   /* ------------------------------------------------------------------- */
   /* Inner classes                                                      */
   protected class LinkEntry<E>
   {
      protected E element;
      protected LinkEntry<E> next;
      protected LinkEntry<E> previous;

      protected LinkEntry() { element = null; next = previous = null; }
   }
   protected class DbLinkedListIterator implements Iterator<E>
   {
	protected LinkEntry<E> current;  
	protected LinkEntry<E> previous;
	protected DbLinkedListIterator(String str)
	{
		if(str=="Front")
		{
			current=head;
		}
		else
		{
			current=tail;
		}
		
		}
	
	public E next()
	{
		E e=current.element;
		previous=current;
		current=current.next;
		return e;
	}
	
	public boolean hasNext()
	{
		return current!=null;
	}
	public boolean hasPrevious()
	{
		return current!=null;
	}
	public E previous()
	{
		E e=current.element;
		current=current.previous;
		return e;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		   current=current.next;
		 	previous.next=current;
		 	current.previous = previous;
	}
   }
} /* CS401LinkedListImpl<E> */
