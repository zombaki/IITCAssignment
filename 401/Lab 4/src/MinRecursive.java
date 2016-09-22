
public class MinRecursive {


	static int min;
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
	public static void main(String args[]){
		int A[]={10,-20,2,0,5,100,-23,2,1,30};
		int s= minimum(A,A.length);
		System.out.println("final ouput "+s);
	}
}