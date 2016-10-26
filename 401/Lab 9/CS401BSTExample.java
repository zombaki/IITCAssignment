public class CS401BSTExample  {
  public static void main (String[] args)
  {
    new CS401BSTExample().run();
  } // method main
  
  public void run()  {
     run_example_1();
     run_example_2();
   }

   public void run_example_1()  {

      CS401BST<Integer> t = new CS401BST<Integer>();
      int nums[] = {100, 59, 13, 82, 58, 63, 65, 46, 80, 62, 28, 71, 
                    92, 47, 93, 8};

      for (int i = 0; i < nums.length; i++)
          t.addRecursive(nums[i]);
     
      System.out.println("Tree:\n Length: " + t.size());
      System.out.println(" Elements: " + t.inorder());
      System.out.println();

      Integer r = 200;
      System.out.println("Removing element " + r);
      if (t.remove(r) == true)  {
          System.out.println("Element " + r + " removed.");
          System.out.println("Tree:\n Length: " + t.size());
          System.out.println(" Elements: " + t.inorder());
          System.out.println();
      }
      else  {
          System.out.println("Element " + r + " does not exist in the tree.");
          System.out.println();
      }

      r = 59;
      System.out.println("Removing element " + r);
      if (t.remove(r) == true)  {
          System.out.println("Element " + r + " removed.");
          System.out.println("Tree:\n Length: " + t.size());
          System.out.println(" Elements: " + t.inorder());
          System.out.println();
      }
      else  {
          System.out.println("Element " + r + " does not exist in the tree.");
          System.out.println();
      }

      return;
   }

    public void run_example_2()  {

       CS401BST<Student> t = new CS401BST<Student>();
 
       Student a[] = new Student[6];
 
       a[0] = new Student("Foghorn Leghorn", 4.0);
       a[1] = new Student("Yosemite Sam", 4.0);
       a[2] = new Student("Elmer Fudd", 2.5);
       a[3] = new Student("Bugs Bunny", 1.5);
       a[4] = new Student("Elmer Fudd", 3.0);
       a[5] = new Student("Elmer Fudd", 2.1);

       for (int i = 0; i < a.length; i++)
            t.addRecursive(a[i]);

       System.out.println("Tree:\n Length: " + t.size());
       System.out.println(" Elements: " + t.inorder());
       System.out.println();

       Student r = new Student("Henery Hawk", 2.1);
       System.out.println("Removing element " + r);
       if (t.remove(r) == true)  {
           System.out.println("Element " + r + " removed.");
           System.out.println("Tree:\n Length: " + t.size());
           System.out.println(" Elements: " + t.inorder());
           System.out.println();
       }
       else  {
           System.out.println("Element " + r + " does not exist in the tree.");
           System.out.println();
       }
 
       r = null;
       r = a[4];
       System.out.println("Removing element " + r);
       if (t.remove(r) == true)  {
           System.out.println("Element " + r + " removed.");
           System.out.println("Tree:\n Length: " + t.size());
           System.out.println(" Elements: " + t.inorder());
           System.out.println();
       }
       else  {
           System.out.println("Element " + r + " does not exist in the tree.");
           System.out.println();
       }


       return;
  }
  
} // class BinarySearchTreeExample
