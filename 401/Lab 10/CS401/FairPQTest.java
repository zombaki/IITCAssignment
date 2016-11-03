package CS401;
/*****
 * Description : This code is using comparator operator with a inbetween class called FairPQ, which keeps a track of age as well,using 
 * count in it.
 * Objective of this program is to show min heap and maintain the order of name
 * @author piyush
 *
 */
public class FairPQTest {
	
	public static void main(String[ ] args){
		FairPQ<Student> pq1 = new FairPQ<Student>(11, new ByName()); //Calling our Fair Priority Que with size as 11,and order is by name
		//FairPQ<Student> pq1 = new FairPQ<Student>();
	            //pq2 = new FairPQ<Student> (11, new ByName());
		//Adding elements
		Student obj1 =  new Student("nath",2);
		
		Student obj2 =  new Student("Kumar",6);
		Student obj3 =  new Student("Sahani",3);
		Student obj4 =  new Student("Xin",9);
		Student obj5 =  new Student("London",1);
		Student obj6 =  new Student("Camroon",4);
		Student obj7 =  new Student("Vienna",5);
		Student obj8 =  new Student("Rakesh",7);
		Student obj9 =  new Student("Ditto",8);
		//Adding element to the priority que
		pq1.add(obj1);pq1.add(obj2);pq1.add(obj3);pq1.add(obj4);pq1.add(obj5);pq1.add(obj6);pq1.add(obj7);pq1.add(obj9);
		pq1.add(obj8);pq1.add(obj9);
		 System.out.println("Removing elements from heap:");
	      System.out.println(" Removed: " + pq1.remove());

	      System.out.println(" Removed: " + pq1.remove());
	      System.out.println(" Removed: " + pq1.remove());
	      System.out.println(" Removed: " + pq1.remove());
	      System.out.println(" Removed: " + pq1.remove());

	}
}
