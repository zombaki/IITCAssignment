import java.util.Scanner;


public class PascalsIterative {
	public static void PascalFunction(int intRows) {
	       int[] previousRow ={1};//If not initialized, System gives compilation error 
	       int[] currentRow;
	       System.out.print(1);//PRinting the first elelement 
	       System.out.println();
	      // previousRow = currentRow;
	       //System.out.println(intRows);
	       for (int i = 2; i <= intRows; i++) {
	           currentRow = new int[i];
	           currentRow[0] = 1; // Setting Value for first element
	           currentRow[i - 1] = 1; // Setting value for last element
	           for (int j = 0; j < i - 2; j++) {//i - 2 removing 2 elements as we are already setting those two values as 1
	        	   //System.out.println("inside loop");
	               currentRow[j + 1] = previousRow[j] + previousRow[j + 1]; // Logic to set middle as sum of two elements from previous row
	           }
	           //System.out.println(currentRow.length);
	           for (int local = 0; local < currentRow.length; local++) {
		           System.out.print(currentRow[local] + " ");
		       }
		       System.out.println();
	           previousRow = currentRow;
	       }
	   }

	  

	public static void main(String[] args) {
	       Scanner scanner = new Scanner(System.in);
	       System.out.print("Enter the rows in the Pascal's triangle: ");
	       int row = scanner.nextInt();
	      // System.out.println(row);
	       final double NANO_FACTOR = 1000000000.0;  // nanoseconds per second
	        final String MESSAGE_2 = " seconds.";
	        final String MESSAGE_1 = "\n\nThe elapsed time was ";
		      long startTime,
		           finishTime,
		           elapsedTime;

		      startTime = System.nanoTime();//STORE START TIME
	       PascalFunction(row);
	       finishTime = System.nanoTime();//STORE END TIME
		      elapsedTime = finishTime - startTime;
		      System.out.println (MESSAGE_1 + (elapsedTime / NANO_FACTOR) + MESSAGE_2);
	   }
	}
