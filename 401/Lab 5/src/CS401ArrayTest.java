
public class CS401ArrayTest {

 public static void main(String[] args) {
 CS401ArrayImpl<Integer> numbers = new CS401ArrayImpl<Integer>();
 numbers.add(100); numbers.add(200);
 numbers.add(300); numbers.add(400);
 numbers.add(500); numbers.add(600);
 numbers.add(700); numbers.add(700);
 print_list(numbers);
 CS401ArrayImpl<Integer> sub = numbers.subList(1,3);
 if (sub != null)
	 print_list(sub);
 /*sub.add(100);
 sub.add(200);
 sub.add(300);
 sub.add(400);
 sub.add(500);sub.add(600);
 sub.add(600);sub.add(600);
 sub.add(700);sub.add(600);
 sub.add(900);
 print_list(sub);*/
 }
 private static void print_list(CS401ArrayImpl<Integer> numbers) {
 for (int i = 0; i < numbers.size(); i++)
 {
 System.out.print(numbers.get(i) + " ");
 }
 System.out.println();
 }
}
