package CS401;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CS401SortingMainLab12 {
	public static void main(String[] args) throws FileNotFoundException{
		int arrayLength = 64000;
		File f = new File("C:/Users/piyus/workspace/CS401Assignment/students-64000.dat");
		String readLine;
		String[] readLineSplited;
		student_NameComp DataHeapSort[]= new student_NameComp[arrayLength];
		student_NameComp DataMergeSort[]= new student_NameComp[arrayLength];
		student_NameComp DataQuickSort[]= new student_NameComp[arrayLength];
		student_IdComp DataHeapSortWRTID[]= new student_IdComp[arrayLength];
		student_IdComp DataMergeSortWRTID[]= new student_IdComp[arrayLength];
		student_IdComp DataQuickSortWRTID[]= new student_IdComp[arrayLength];
		Scanner scan = new Scanner(f);
		//Getting data for all the sorting algo
		int i=0;
		while(scan.hasNextLine()){	
			readLine = scan.nextLine();
			readLineSplited= readLine.split(" ");
			student_NameComp student1 = new student_NameComp(readLineSplited[0] , readLineSplited[1], readLineSplited[2], Long.parseLong(readLineSplited[3]));
			student_IdComp student2 = new student_IdComp(readLineSplited[0] , readLineSplited[1], readLineSplited[2], Long.parseLong(readLineSplited[3]));
			DataHeapSort[i]= student1;
			DataMergeSort[i]= student1;
			DataQuickSort[i]= student1;
			DataHeapSortWRTID[i]= student2;
			DataMergeSortWRTID[i]= student2;
			DataQuickSortWRTID[i]= student2;
			i++;
		}
		scan.close();
		long startTime = System.nanoTime();
		System.out.println("Comparison-Function-A");
		//QuickSort
		QuickSort.quickSort(DataQuickSort);
		long elepsedTime = System.nanoTime() - startTime;
		double seconds = (double)elepsedTime / 1000000.0;
		System.out.println("Quick Sort of data file takes "+seconds+" miliseconds");

		//Merge Sort
		startTime = System.nanoTime();
		MergeSort.mergeSort(DataMergeSort);
		elepsedTime = System.nanoTime() - startTime;
		seconds = (double)elepsedTime / 1000000.0;
		System.out.println("Merge Sort of data file takes "+seconds+" miliseconds");

		//Heap Sort
		startTime = System.nanoTime();
		HeapSort.heapSort(DataHeapSort);
		elepsedTime = System.nanoTime() - startTime;
		seconds = (double)elepsedTime / 1000000.0;
		System.out.println("Heap Sort of data file takes "+seconds+" miliseconds");
		System.out.println("\nComparison-Function-B");
		//QuickSort WRTID
		startTime = System.nanoTime();
		QuickSort.quickSort(DataQuickSortWRTID);
		elepsedTime = System.nanoTime() - startTime;
		seconds = (double)elepsedTime / 1000000.0;
		System.out.println("Quick Sort of data file takes "+seconds+" miliseconds");

		//Merge Sort WRTID
		startTime = System.nanoTime();
		MergeSort.mergeSort(DataMergeSortWRTID);
		elepsedTime = System.nanoTime() - startTime;
		seconds = (double)elepsedTime / 1000000.0;
		System.out.println("Merge Sort of data file takes "+seconds+" miliseconds");

		//Heap Sort WRTID
		startTime = System.nanoTime();
		HeapSort.heapSort(DataHeapSortWRTID);
		elepsedTime = System.nanoTime() - startTime;
		seconds = (double)elepsedTime / 1000000.0;
		System.out.println("Heap Sort of data file takes "+seconds+" miliseconds");

		
		}
}
