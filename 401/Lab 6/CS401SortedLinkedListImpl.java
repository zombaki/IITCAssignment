

public class CS401SortedLinkedListImpl<E> extends CS401LinkedListImpl<E>
{
   public CS401SortedLinkedListImpl()
   {
      super();
   }

   /** 
    * Adds element e in sorted order in the collection class (linked 
    * list).
    * Returns true if e was added successfully, false otherwise.
    */
   public boolean add(E e)
   {
      /**
       * Add code here. */
	   LinkEntry<E> ne = new LinkEntry<E>();
	      ne.element = e;
	      if (head == null && tail == null)
	      {
	          head = tail = ne;
	          num_elements++;
	          return true;
	      }
	      else{
	    	  CS401LinkedListIterator it = new CS401LinkedListIterator();
	    	  E x;
	    	  boolean isLastItem=true;
	    	  LinkEntry<E> prev=null;
		   	   while(it.hasNext()){
		   		  x= it.next();
		   		System.out.println(x);
		   		   if (((Comparable)e).compareTo(x)<0){
		   			isLastItem=false;
		   			   break;
		   			
		   		   }
			   			
		   		   prev=it.previous;
		   			   			   
		   	   }
		   	   if (prev == null && isLastItem==false){
		   		   ne.next=head;
		   		   head=ne;
		   	   }
		   	   else if(prev==null&& isLastItem){
		   		   tail.next = ne;
		   		   tail=ne;
		   	   }
		   	   else{
		   		   ne.next=prev.next;
	    		  prev.next=ne;
		   	   }
		   	num_elements++;
		   	System.out.println("end loop"+ num_elements);
		   	return true;
	      }
   }

   /**
    * Print the sorted linked list in reverse order using recursion.
    */
   public void reverse_print()  {
      /**
       * Add code here 
       */
	   if(head==null)
	   {
		   System.out.println("There is no element to print");
	   }
	   else
	   {
		   LinkEntry<E> temp=head;
		   Print(temp);
	   }
   }
   public void Print (LinkEntry<E> temp)
   {
	   String result="";
	   if(temp==null)
		   return;
	   
	   Print(temp.next);
	   result+=temp.element;
	  
	   System.out.print(result);
	   
	  
	   
   }

} /* CS401SortedLinkedListImpl<E> */

