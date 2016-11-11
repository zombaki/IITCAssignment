package CS401;

public class CS401ComparableSort {
	/**********
	 * Insertion SORT
	 * @param x
	 */
	 public static void insertionSort (Object[] x) 
	   {
	      for (int i = 1; i < x.length; i++)
	         for (int k = i; k > 0 && ((Comparable)x [k -1]).compareTo (x [k]) > 0; k--)
	         swap (x, k, k -1);
	   } 
	  
	  /**********
	   * Selection sort
	   * @param x
	   */
	   
	   public static void selectionSort (Object [] x) 
	   {
	      
	      for (int i = 0; i < (x.length - 1); i++) 
	      {
	         int pos = i;
	         for (int k = i + 1; k < x.length; k++)
	           if (((Comparable)x [k]).compareTo (x [pos]) < 0) 
	            pos = k;
	        swap (x, i, pos);
	      } 
	   } 
	  
	  /*************8
	   * bubble sort
	   * @param x
	   */
	   
	   public static void bubbleSort (Object[] x) 
	   {
	      int finalSwapPos = x.length - 1,
	            swapPos;              
	    
	      while (finalSwapPos > 0)
	      {
	         swapPos = 0;
	         for (int i = 0; i < finalSwapPos; i++)
	           if (((Comparable)x [i]).compareTo (x [i + 1]) > 0)
	          {
	            swap (x, i, i + 1);
	            swapPos = i;
	          } 
	         finalSwapPos = swapPos;             
	      }      
	   } 
	  
	   /***********
	    * swap method which is consumed by other methods
	    * @param x
	    * @param a
	    * @param b
	    */
	   public static void swap (Object [] x, int a, int b) 
	   {
	      Object t = x[a];
	      x[a] = x[b];
	      x[b] = t;
	   } 
}
