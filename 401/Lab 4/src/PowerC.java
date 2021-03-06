import java.util.Scanner;
/** Code change for first part of the code 2c With improved Recursive*/
public class PowerC {
static int intcount = 0;
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
		 PowerC call = new PowerC();
		 Scanner scanner = new Scanner(System.in);
		 System.out.print("Enter number : ");
		 long x = scanner.nextLong();
		 System.out.print("Enter power : ");
		 long y = scanner.nextLong();
		 startTime = System.nanoTime(); //START TIMER
			 System.out.println("\n" +x +"^" +y +" = "+call.Power(x,y));
			 finishTime = System.nanoTime();//STORE END TIME
		      elapsedTime = finishTime - startTime;
		      System.out.println (MESSAGE_1 + (elapsedTime / NANO_FACTOR) + MESSAGE_2);
			// System.out.println(intcount);
	 }
	 /**
	 * Calculates the value of a given integer raised to the power of a second integer.
	 * The worstTime(n) is O(n), where n is the second integer.
	 *
	 * @param i � the base integer (to be raised to a power).
	 * @param n � the exponent (the power i is to be raised to).
	 *
	 * @return the value of i to the nth power.
	 *
	 * @throws IllegalArgumentException � if n is a negative integer or if i raised to
	 * to the n is greater than Long.MAX_VALUE.
	 *
	 */
	 long Power(long x, long y)
	 {
		 intcount++;
		 if(y==0)
			 return 1;
		 else if(y==1)
			 return x;
		 else if (y<0 || y>Long.MAX_VALUE )
			 throw new IllegalArgumentException("Illigal Power Argument.");
		 else
		 {
			 long TempValue = Power(x,y/2);
			 //System.out.println(TempValue);
			 if (y%2==0){
				 return TempValue*TempValue; 
			 }
			 else{

				 return TempValue*TempValue*x; 
			 }
		 } 
	 }
	}