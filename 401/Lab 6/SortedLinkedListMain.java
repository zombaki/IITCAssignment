public class SortedLinkedListMain
{
   public static void main(String[] args)
   {
      CS401SortedLinkedListImpl<Chores> 
                    chores_list = new CS401SortedLinkedListImpl<Chores>();

      Chores a = new Chores("Make bed", 10);
      Chores b = new Chores("Do laundry", 5);
      Chores c = new Chores("Take out garbage", 20);
      Chores d = new Chores("Clean car", 18);
      Chores e = new Chores("Excercise", 70);
      Chores f = new Chores("Sleep", 50);

      chores_list.add(a); 
      chores_list.add(b); 
      chores_list.add(c);
      chores_list.add(d);
      chores_list.add(e);
      chores_list.add(f);
   
      System.out.println("Size of collection: " + chores_list.size());
      System.out.println("Collection contains:");
      System.out.println(chores_list);

      System.out.println("Printing collection in reverse order: ");
      chores_list.reverse_print();

      return;
   }

}
