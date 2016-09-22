import java.util.Scanner;


public class PascalsRecursive {
	 /**
     *  Returns a Integer as previous value which need to be added to get the newer value 
     *  Here we uses PascalFunction(row-1,column-1) + PascalFunction(row-1,column) to get the summation of previous two element
     *  Base case in our program is when element reached to the first row==1 or it reached first column or scenario where row is equal to column.
     *
     *  @return a integer value for which system asked for value.
     *
     */ 
public static int PascalFunction(int row, int column){
	if (row==1 || column == 1 || row == column)//First Row, First column and when we are asking for element when row ==column ie last element of row,that will come as 1
		return 1;
	else
		return (PascalFunction(row-1,column-1) + PascalFunction(row-1,column));
}
public static void main(String[] args) {
	String[] InputData;
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter row, element: ");
    String row = scanner.nextLine();//Take input from user as string of values
    InputData=row.split(","); //Deliminate value with respect to "," as required
   // System.out.println(row);
    int Value = PascalFunction(Integer.parseInt(InputData[0]),Integer.parseInt(InputData[1]));//Calling the recursive function
    System.out.println(Value);
}
}
