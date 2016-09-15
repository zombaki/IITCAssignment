import java.util.Scanner;

public class FiboRecursion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		//DECLARATION OF VARIABLES
        final String INPUT_PROMPT = "\n\nPlease enter the positive integer whose Fibonacci number you want : ";
        final String FIBONACCI_MESSAGE = "\nIts Fibonacci number is ";
        final String MESSAGE_1 = "\n\nThe elapsed time was ";
        
        final double NANO_FACTOR = 1000000000.0;  // nanoseconds per second
        final String MESSAGE_2 = " seconds.";

	      long startTime,
	           finishTime,
	           elapsedTime;
	      Scanner sc = new Scanner (System.in);
	      System.out.print (INPUT_PROMPT); //GUIDE USER TO KEY IN VALUE 
        
        int n = sc.nextInt(); //ASSIGNE VALUE TO n 
		// TODO Auto-generated method stub
        


	      startTime = System.nanoTime();//STORE START TIME
	      System.out.print (FIBONACCI_MESSAGE + fib(n));//INVOKE OUR METHOD
	      finishTime = System.nanoTime();//STORE END TIME
	      elapsedTime = finishTime - startTime;
	      System.out.println (MESSAGE_1 + (elapsedTime / NANO_FACTOR) + MESSAGE_2);
	}
	/**
	 *  Returns the nth Fibonacci number.
	 *
	 *  @param n - the integer whose Fibonacci number is sought.
	 *
	 *  @throws IllegalArgumentException - if n <= 0 or > 92 (note
	 *          that fib (93) is larger than Long.MAX_VALUE).
	 *  Objective is to fetch fibonacci number for value of n using recursive way
	 */

	public static long fib (int n)  
	{
		final int MAX_N = 92;

	        final String ERROR_MESSAGE = "\nThe number entered must be " +
	            "greater than 0 and at most " + MAX_N + ".";

	        if (n <= 0 || n > MAX_N)
	            throw new IllegalArgumentException (ERROR_MESSAGE);
	  	if (n <= 2 )
	    	    return 1;
	    	return fib (n - 1) + fib (n - 2); 
	} // method fib
}
