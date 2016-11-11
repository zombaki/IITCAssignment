package CS401;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CS401Sorting {
	public static void main(String[] args) throws FileNotFoundException{
			
			int arrayLength = 64000;
			File f = new File("C:/Users/piyus/workspace/CS401Assignment/students-64000.dat");
			
			String readLine;
			String[] readLineSplited;
			Student DataInsertSort[]= new Student[arrayLength];
			Student DataSelectionSort[]= new Student[arrayLength];
			Student DataBubleSort[]= new Student[arrayLength];
			Scanner scan = new Scanner(f);
			
			
			//Fetching all the data form the file to student object
			int i=0;
			while(scan.hasNextLine()){	
				readLine = scan.nextLine();
				readLineSplited= readLine.split(" ");
				Student Student1 = new Student(readLineSplited[0] , readLineSplited[1], readLineSplited[2], Long.parseLong(readLineSplited[3])); 
				DataInsertSort[i]= Student1;
				DataSelectionSort[i]= Student1;
				DataBubleSort[i]= Student1;
				i++;
			}
			
			long startTime = System.nanoTime();
			//INSERTION SORT
			CS401ComparableSort.insertionSort(DataInsertSort);
			long elepsedTime = System.nanoTime() - startTime;
			double seconds = (double)elepsedTime / 1000000.0;
			System.out.println("Insertion sort of data file takes "+seconds+" miliseconds");
			scan.close();
			
			
			//SLECTION SORT BEGINSs
			startTime = System.nanoTime();
			CS401ComparableSort.selectionSort(DataSelectionSort);
			elepsedTime = System.nanoTime() - startTime;
			seconds = (double)elepsedTime / 1000000.0;
			System.out.println("Selection sort of data file takes "+seconds+" miliseconds");
			
			//BUBLE SORT BEGINS
			
			startTime = System.nanoTime();
			CS401ComparableSort.bubbleSort(DataBubleSort);
			elepsedTime = System.nanoTime() - startTime;
			seconds = (double)elepsedTime / 1000000.0;
			System.out.println("Bubble sort of data file takes "+seconds+" miliseconds");
	
	}
}
