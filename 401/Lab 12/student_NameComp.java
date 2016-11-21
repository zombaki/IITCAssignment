package CS401;

public class student_NameComp implements Comparable<student_NameComp>{
	 String fname;
	  String lname;
	  String mname;
	  long studentId;
	  //constructor
	  public student_NameComp (String lname,String fname,String mname,long studentId)
	  {
	    this.fname = fname;
	    this.lname = lname;
	    this.mname = mname;
	    this.studentId = studentId;
	  } 
	  public int compareTo (student_NameComp student)
	  {
	    
		  String fullName = lname+fname+mname;
		  String OtherFullName = student.lname+student.fname+student.mname;
	    
			if (fullName.compareTo(OtherFullName) < 0 )
				return -1;
			else if (fullName.compareTo(OtherFullName) > 0 )
				return 1;
			else 
			{
				if (studentId < student.studentId )
				      return -1;
				    else if (studentId > student.studentId )
				      return 1;
				    else 
						return 0;
			}
		
	    } // method compareTo
}
