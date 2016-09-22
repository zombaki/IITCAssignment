Illinois Institute of Technology CS 401 Laboratory assignment #4

1(a) : Done in the Java File : PascalsIterative.Java
1(b) : Done in the JAva File : PascalsRecursive.Java
1(c) : Complexit for Itterative version of Pascals Triangle Would be O(n^2)

If we see out code : 
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

In this program , we can see that ,first code will run for some constatnt steps to set elements for first elements , 
we can also observe :
for value of intRow 2 34 ......n
inner loop runs 1 2 3 .......n
So total work done by inner loop is 1+2+3+4+..+n == n*(n+1)/2 
which is O(n^2)
If you observe we have another inner loop which we are using to print the output, that is not going to make much change in complexity as its not in itteration,will end up giving the same value.

I we observe in time taken by the program

for value of n ---------- 	3	5	10	15	20	30	35	40	45
Time taken   -----------		.0007	0.0015	0.00444	.0082	0.01189	0.0217	.0272	.031	.037

as  you can observe the value is getting increased more then linear as we move from 10 to 15 and 15 to 20.
2(a) First part to complete all the three form, its attached to the files ,these files are :-
 a) Power.Java      ,Simple Recursion
 b) PowerB.Java   , Itteration
 c) PowerC.Java   , Improved Recursion using devide and conquor method.

Observeation with respect to time :- Here base value is kept as constant ,that is 2, power value is kept as n
For Value of n	-->	0	1	5	10	20
Recursion	(sec)	-->	.0004	.0005	.0006	.0007	.0007
Itterative	(sec)	-->	.0004	.0005	.0006	.0006	.0008
ImprovedRecursion(s)-->	.0003	.0004	.0004	.0004	.0004

if we observe that value of n is increasing slowly for n value in case of Recursive way and Itterative way, but for improved Recursive its giving a constant value of .0004. If we draw  this graphically, for other 2 it comes out to be a n slant where as for improved recursion it became kind of constant,whcih happens when complexity is Log(n).

Please find Attachement for the Graphical representation "Comparison Between Different Method in Graph.xlsx"

Complexity for these functions are:-

recursive = T(n) = T(n-1) +c which is equal to O(n) as proved in 3rd Question.
Itteration = O(n) as its n itteration to multiple and find the power value
Imoproved Recursion = T(n) = T(n/2) + c
which is quals to O(logn) using masters method ,where a=1 and b=2 .

3(a) I have attached the code in the sorce with file name MinRecursive.java
(b)	recurence relation for my code minimum.

public static int minimum(int A[],int size){
		//System.out.println(size);
		if (size ==1)
			return A[0];
		else{
		int temp = minimum(A, size - 1);
			if (temp > A[size-1]){
				//System.out.println(A[size-1]);
				return A[size-1];
				
			}
			else
				return temp;
				
		
		}
	}

Here we can write this program as T(n)= T(n-1)+c 
Reason to have T(n-1) because we are invoking minimum function with one constant value as Array A and second size is reduced by 1.
c are those constant values which is there in the functions whcih are taking constand time and can be combined to form 1.

Forward substitution:
given T(n)=T(n-1)+c
base case here is when the size is 1 return First element. as in our function variable which is kept on changing is size and array is constant. so we can write .
T(0) = a[0];
T(0) = a
T(1) 	=	 T(1-1)+c
	 =	T(0) +c
	=	a+c
T(2)	=	T(1)+c
	=	2c + a
T(3)	=	T(2)+c
	=	2c+a+c
	=	3c+a
.
.
.
.
T(n)	=	T(n-1)+c	=T(0) + nc   Can be infered from above equations
hence T(n) = a+nc (closed form)
hence Lim n--> infinity O(n) for the given equation.

