package CS401;

public class student_IdComp  implements Comparable<student_IdComp>{
	 String fname;
	  String lname;
	  String mname;
	  long studentId;
	  //constructor
	  public student_IdComp (String lname,String fname,String mname,long studentId)
	  {
	    this.fname = fname;
	    this.lname = lname;
	    this.mname = mname;
	    this.studentId = studentId;
	  } 
	  public int compareTo (student_IdComp student)
	  {
				if (studentId < student.studentId )
				      return -1;
				    else if (studentId > student.studentId )
				      return 1;
				    else 
						return 0;
	  }
		
}
