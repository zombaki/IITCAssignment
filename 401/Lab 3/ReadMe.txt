Lab 7:

FIBONACCI NUMBERS:

There are multiple ways in which we can achieve Fibonacci series , here objective is to analysize the difference between Itterative ,recursive and write a improved version of recursive function.

Firstly we created a program : FiboItterative.java (iterative action)
This program uses for loop add intteratively ,to get the n'th element of fibonacci series.
when we calculate asymtotic equation,it comes out to be linear,which we can observe when we execute our code.
Entered Value		Time elapsed 
--------------------                            ----------------------------
5			.0003 sec
10			.0003 sec
20			.0003 sec
30			.0003 sec
40			.0003 sec
45			.0003 sec

It hardly gets changed with the increase of value of n .

Secondly we created a program : FiboRecursion.java (Recursive)
This program has Traditional way of recursive function.
When we executed we observed a elapsed time increases exponentially.
Like :
Entered Value		Time elapsed 
--------------------                            ----------------------------
5			.0003 sec
10			.0003 sec
20			.0024 sec
30			.0191 sec
40			.8489 sec
45			9.17 sec

If we observe that time elapse by our method increase exponentially when we shifted from 40 to 45. the reason is,when ever we are doing recursion in there is repeatition of fetching series values mutliple time.
Reason: when we were comparing with less n value, the number of comparision to find the fibonaci series was less, but as we kept on increasing the n number, the comparison increased exponentially.  

We can infer that, the fibonacci series program using recursive function is taking too much time to complete as compared to itterative version, because of same function call is happening multiple times, so using recursive function to do this is not a good idea. though we can improve the functionality and do some changes to avoid calling method again and again.

so we came up with improved version of recursive function.

Program Name: FibImproveRecursion.java
In this we have used the concept which is used in ittereative method and combine it with recursive functionality to achive our objective of better recursion to get fibonacci series.
Rather the passing only n now we are passing three parameter, n(nth term for whcih we need to get the element) current ,previous element. so that in fcuntion we just do the additional and when the program counter reached to the base state,that is when n'th element is 1 ,we can return the summed value.

Entered Value		Time elapsed 
--------------------                            ----------------------------
5			.0003 sec
10			.0003 sec
20			.0003 sec
30			.0003 sec
40			.0003 sec
45			.0003 sec

now its similar to linear expression and much improved recursive function.

I have attached all three java files (Fiborecursion.java, FiboItterative.java and FibImprovedRecursion.java)

