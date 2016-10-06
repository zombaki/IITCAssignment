public class DblLinkedListMain
{
   public static void main(String[] args)
   {
      CS401DblLinkedListImpl<Chores> 
                    chores_list = new CS401DblLinkedListImpl<Chores>();

      Chores a = new Chores("Make bed", 10);
      Chores b = new Chores("Do laundry", 5);
      Chores c = new Chores("Take out garbage", 20);
      Chores d = new Chores("Clean car", 18);
      Chores e = new Chores("Excercise", 70);
      Chores f = new Chores("Sleep", 50);

      chores_list.add(a); 
      chores_list.add(b); 
      chores_list.add(Where.FRONT, c);
      chores_list.add(d);
      chores_list.add(Where.MIDDLE, 2, e);
      chores_list.add(f);
   
      System.out.println("Size of collection: " + chores_list.size());
      System.out.println("Iterating from the front, collection contains:");
      chores_list.print_from_beginning();
      System.out.println("Iterating from the back, collection contains:");
      chores_list.print_from_end();

      chores_list.remove(1);                     // Remove first element
      //chores_list.print_from_beginning();
      chores_list.remove(chores_list.size());    // Remove last element
      chores_list.remove(2);                     // Remove second element

      System.out.println("Size of collection: " + chores_list.size());
      System.out.println("Iterating from the front, collection contains:");
      chores_list.print_from_beginning();
      System.out.println("Iterating from the back, collection contains:");
      chores_list.print_from_end();
      
      Chores g = chores_list.remove(chores_list.size()+10);
      if (g != null)  {
          System.out.println(g + " removed");
      }
      else  {
          System.out.println("Index out of bound");
      }

      return;
   }

}
