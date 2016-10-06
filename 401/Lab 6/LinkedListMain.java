public class LinkedListMain
{
   public static void main(String[] args)
   {
      CS401LinkedListImpl<Chores> 
                    chores_list = new CS401LinkedListImpl<Chores>();

      Chores a = new Chores("Make bed", 10);
      Chores b = new Chores("Do laundry", 5);
      Chores c = new Chores("Take out garbage", 20);
      Chores d = new Chores("Clean car", 18);
      Chores e = new Chores("Excercise", 70);
      Chores f = new Chores("Sleep", 50);

      chores_list.add(a); 
      chores_list.add(b); 
      chores_list.add(Where.FRONT, c);
      chores_list.add(Where.MIDDLE, 1, d);
      chores_list.add(e);
      chores_list.add(Where.MIDDLE, 4, f);
   
      System.out.println("Size of collection: " + chores_list.size());
      System.out.println("Collection contains:");
      System.out.println(chores_list);

      System.out.print("Searching for " + a + "...");
      if (chores_list.contains(a))  {
          System.out.println("FOUND");
      }
      else  {
          System.out.println("NOT FOUND");
      }

      Chores g = chores_list.remove(1);
      System.out.println("Removed " + g);
      System.out.print("Searching for " + g + "...");
      if (chores_list.contains(g))  {
          System.out.println("FOUND");
      }
      else  {
          System.out.println("NOT FOUND");
      }

      g = chores_list.remove(2);
      System.out.println("Removed " + g);

      System.out.println("Size of collection: " + chores_list.size());
      System.out.println("Collection contains:");
      System.out.println(chores_list);
      return;
   }

}
