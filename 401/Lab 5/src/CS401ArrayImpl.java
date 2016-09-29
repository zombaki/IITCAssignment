public class CS401ArrayImpl<E> implements CS401CollectionInterface<E>
{
   private E[] elements;
   private int num_elements;
   private int capacity;

   @SuppressWarnings("unchecked")
   public CS401ArrayImpl(int size)
   {
      elements = (E[]) new Object[size];
      num_elements = 0;
      capacity = size;
   }

   @SuppressWarnings("unchecked")
   public CS401ArrayImpl()
   {
      /**
       * Call the c'tor that takes the 'size' parameter.  **/
      this(5);
   }

   /**
    * Methods inherited from CS401CollectionInterface
    */
   public boolean is_full()
   {
      if (num_elements == capacity)
          return true;
      return false;
   }

   public boolean is_empty()
   {
      if (num_elements == 0) 
          return true;
      return false;
   }

   public int size() { return num_elements; }

   public boolean add(E e)  {
      add(Where.BACK, e);  // Add at the end
      return true;
   }

   /*
    * Remove element at index i.  If the element exists in the collection, 
    * return that element back to the user.  If the index is out of bounds,
    * return null.
    */
   public E remove(int i) {
      /*
       * Add code here.
       * Remember to compact the array so there are no spaces in between
       * if an element from the middle is removed or an element at the
       * beginning of the array is removed.
       */
	   E obj;
	   obj = (E) elements[i];
       for (int currentIndex =i+1  ;currentIndex<num_elements; currentIndex++){
    	   elements[currentIndex-1]=elements[currentIndex];
       }	 
       num_elements--;
       
      return obj;
   }

   /*
    * Return true if e is in the collection class, false otherwise.
    */
   public boolean contains(E e) {
       /*
        * Add code here
        */
	   Chores objChore;
       for (int i =0  ;i<num_elements; i++){
    	   objChore= (Chores)elements[i];
     	  if (objChore.compareTo((Chores)e) == 0)
     		  return true;
       }	
       return false;
   }

   /**
    * ---- Methods defined by this class 
    * ----------------------------------------------------------
    * Methods that are added by this class and not in the 
    * CS401CollectionInterface
    */

   /**
    * Add element in middle.
    * Preconditions: where == MIDDLE
    *                index <= num_elements - 1
    */
   public boolean add(Where where, int index, E e) { 
  
      /* 
       * If there is no space to add the new element, grow the array. */
      if (is_full())  {
          grow();
      }
      if (where == Where.MIDDLE)  {
    	  int currentIndex=0;
    	     E[] newelements;
             newelements =  (E[]) new Object[capacity];
             for (currentIndex =0  ;currentIndex<index; currentIndex++){
           	  newelements[currentIndex]=elements[currentIndex];
             }	  
             newelements[index] = e;
             for (currentIndex =currentIndex+1  ;currentIndex<=num_elements; currentIndex++){
              	  newelements[currentIndex]=elements[currentIndex-1];
             }	 
             num_elements++;
             elements=newelements;
      }
      /**
       * Add code here 
       */
      return true; 
   }

   /**
    * Add element in front or at the end, as dictated by where.
    * Preconditions: where != MIDDLE
    */
   public boolean add(Where where, E e) { 
   
      /* 
       * If there is no space to add the new element, grow the array. */
      if (is_full())  {
          grow();
         // System.out.println("need to grow array");
      }

      if (where == Where.BACK)  {
          System.out.println("Inserting element at index " + num_elements);
          elements[num_elements] = e;
          num_elements++;
      } 
      else if (where == Where.FRONT)  {
          //System.out.println("Inserting element at index 0");
          //System.out.println("Compacting storage");
          /*
           * Add code here.
           * You will add the new element at index 0, and shift all the
           * elements down by one. */
          E[] newelements;
          newelements =  (E[]) new Object[capacity];
          newelements[0] = e;
          for (int i =1  ;i<=num_elements; i++){
        	  newelements[i]=elements[i-1];
          }
          elements = newelements;
          num_elements++;
      }

      return true; 
   }

   /*
    * Gets the element at index i (0 <= i <= num_elements-1)
    */
   public E get(int i)  {

      if (i < 0 && i > num_elements)
          return null;

      return(elements[i]);
   }

   /**
    * ----------- Private methods
    */
   /*
    * Grows elements array to hold more elements.  Copies old (existing)
    * elements in the new array.
    * 
    * Postcondition: (a) elements must contain the contents of the old array
    *                (b) elements must now have twice as much capacity as
    *                    before
    */
   @SuppressWarnings("unchecked")
   private boolean grow()  {

      /* 
       * Add code here 
       * Expand capacity (double it) and copy old array contents to the
       * new one. 
       */
	   
      System.out.println("Capacity reached.  Increasing storage...");
      
      E[] newelements;
      newelements = (E[]) new Object[capacity*2];
      for (int i =0  ;i<capacity; i++){
    	  newelements[i]=elements[i];
      }
      elements=newelements;
      capacity*=2;
      System.out.println("New capacity is " + capacity + " elements");

      return true;
   }
   /**
    * subList(int from, int to): Returns the view of a portion of the list between the specified index from,
    * inclusive, and index to, exclusive. That is returns a sublist [from, to).
    *
    *
    * The method returns a new CS401ArrayImpl<E> object.
    */
    public CS401ArrayImpl<E> subList(int from, int to){
    	//Sanitation check for from and to values
    	if (from <0)
    		System.out.println("From index is less then 0");
    	else if (to >num_elements)
    		System.out.println("To index is more then maximum element");
    	else{
    		E[] newSublist;
    		newSublist =  (E[]) new Object[this.capacity];
       
            for (int i =from  ;i<to; i++){
            	newSublist[i-from]=elements[i];
            }
            CS401ArrayImpl<E> obj = new CS401ArrayImpl<E>(this.capacity);
            obj.elements=newSublist;
           obj.num_elements = to-from;
           //System.out.println(this.capacity);
           //System.out.println(to-1);
            return obj;
    	}
    	return null;
    }
}
