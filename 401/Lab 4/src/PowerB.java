import java.util.Scanner;

/** Code change for firs part of the code 2b With itteration*/
public class PowerB {

	 public static void main(String args[]) 
	 {
		 //****************Start for time system *********************
	     final String MESSAGE_1 = "\n\nThe elapsed time was ";
	     final double NANO_FACTOR = 1000000000.0;  // nanoseconds per second
	     final String MESSAGE_2 = " seconds.";
	     long startTime,
	     finishTime,
	     elapsedTime;
	     //**************************end of time system declaration*********
		 PowerB call = new PowerB();
		 Scanner scanner = new Scanner(System.in);
		 System.out.print("Enter number : ");
		 long x = scanner.nextLong();
		 System.out.print("Enter power : ");
		 long y = scanner.nextLong();
		 startTime = System.nanoTime(); //START TIMER
			 System.out.println("\n" +x +"^" +y +" = "+call.toPower(x,y));
			 finishTime = System.nanoTime();//STORE END TIME
		      elapsedTime = finishTime - startTime;
		      System.out.println (MESSAGE_1 + (elapsedTime / NANO_FACTOR) + MESSAGE_2);
	 }
	 /**
	 * Calculates the value of a given integer raised to the power of a second integer.
	 * The worstTime(n) is O(n), where n is the second integer.
	 *
	 * @param i – the base integer (to be raised to a power).
	 * @param n – the exponent (the power i is to be raised to).
	 *
	 * @return the value of i to the nth power.
	 *
	 * @throws IllegalArgumentException – if n is a negative integer or if i raised to
	 * to the n is greater than Long.MAX_VALUE.
	 *
	 */
	 long toPower(long x, long y)
	 {
		 long PowerValue=1;
		 if (y<0 || y>Long.MAX_VALUE)
			 throw new IllegalArgumentException("Illigal Power Argument.");
		 else if (x==0)
			 return 0;
		 else
		{
			 for (long i =1;i<=y;i++){
				 PowerValue*=x;
			 }
			 return PowerValue;
		 }
	 }
}
