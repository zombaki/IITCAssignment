package CS401;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import CS401.CS401BST.TreeNode;
/**********************************
 * CS401Assignment10a : Code changes to observe the time consumed by different methods ,Linked list and BST
 * @author piyush
 *
 */
public class CS401Assignment10a  {
	public static void main (String[] args) throws IOException{
		CS401LinkedListImpl<Student> student_LinkList = new CS401LinkedListImpl<Student>();
		CS401BST<Student> student_BST = new CS401BST<Student>(); 
		
		File f_stu = new File("C:/Users/piyus/workspace/CS401Assignment/students-64000.dat");
		
		String ReadLine;
		String[] ReadLineSplit;
		
		//FETCHING DATA FROM DAT FILE AND MOVING TO OUR STUDENT CLASS(WITH LINKED LIST)
		Scanner inputData = new Scanner(f_stu);
		long StartTime = System.nanoTime();
		while(inputData.hasNextLine()){	
			ReadLine = inputData.nextLine();
			ReadLineSplit= ReadLine.split(" ");
			Student searchData = new Student(ReadLineSplit[0] , ReadLineSplit[1], ReadLineSplit[2], Long.parseLong(ReadLineSplit[3])); 
			student_LinkList.add(searchData);
		}
		long ElapsedTime = System.nanoTime() - StartTime; //Elapsed Time for Linked List
		double time = (double)ElapsedTime / 1000000000.0; //Converting to Seconds
		System.out.println("Time taken to load the records in a linked list: "+ (time) +" sec");
		inputData.close();
		//FETCHING DATA FROM DAT FILE AND MOVE TO BINARY TREE
		Scanner Scan2 = new Scanner(f_stu);
		StartTime = System.nanoTime();
		while(Scan2.hasNextLine()){	
			ReadLine = Scan2.nextLine();
			ReadLineSplit= ReadLine.split(" ");
			Student studentsdata1 = new Student(ReadLineSplit[0] , ReadLineSplit[1], ReadLineSplit[2], Long.parseLong(ReadLineSplit[3])); 
			student_BST.add(studentsdata1);
		}
		ElapsedTime = System.nanoTime() - StartTime; //ELAPSED TIME FOR BINARY TREE DATA INSURTION
		time = (double)ElapsedTime / 1000000000.0;
		System.out.println("Time taken to load the records in a binary search tree : "+ (time) +" sec");
		Scan2.close();	
		
		
		
		// SEARCHING STUDENT ID 483293267
		System.out.println("\nSearching for student ID 483293267");
		searchOperation(483293267,student_LinkList,student_BST);
		
		
		// SEARCHING STUDENT ID 1902997270
		System.out.println("\nSearching for student ID 1902997270");
		searchOperation(1902997270,student_LinkList,student_BST);
		
		
		// SEARCHING STUDENT ID 856408684
		System.out.println("\nSearching for student ID 856408684");
		searchOperation(856408684,student_LinkList,student_BST);
		// SEARCHING STUDENT ID 143507366
		System.out.println("\nSearching for student ID 143507366");
		searchOperation(143507366,student_LinkList,student_BST);
		// SEARCHING STUDENT ID 307954472
		System.out.println("\nSearching for student ID 307954472");
		searchOperation(307954472,student_LinkList,student_BST);
		
		// SEARCHING STUDENT ID 876561221
		System.out.println("\nSearching for student ID 876561221");
		searchOperation(876561221,student_LinkList,student_BST);
	}
	public static void searchOperation(int studentID,CS401LinkedListImpl<Student> student_LinkList,CS401BST<Student> student_BST){
		Student searchData = new Student(null, null, null, studentID);
		Student temp = null;
		long StartTime = System.nanoTime();
		for(Student s : student_LinkList){
			if(s.studentId == searchData.studentId)
				temp =s;
		}
		if(temp==null){
			System.out.println("\t No record with this key found");
		}else
			System.out.println("\t Success (record found): " + temp.lastName+ " " + temp.firstName + " " + temp.middleName + " " + temp.studentId);
		long ElapsedTime = System.nanoTime() - StartTime;
		double time = (double)ElapsedTime / 1000000000.0;
		System.out.println("\t Searching time in linked list : "+ (time) +" seconds");
		
		StartTime = System.nanoTime();
		TreeNode<Student> sResult = student_BST.getTreeNode(searchData);
		
		ElapsedTime = System.nanoTime() - StartTime;
		time = (double)ElapsedTime / 1000000000.0;
		System.out.println("\t Searching time in binary search tree : "+ (time) +" seconds");

	}
}
