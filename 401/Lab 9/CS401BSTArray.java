public class CS401BSTArray<E extends Comparable <E>> {
   protected TreeNode<E> [] tree;
   protected int root, size, indx;
	
   public CS401BSTArray() {
       /**
        * Do not initialize tree as: tree=(TreeNode<E> []) new Object[50];
        * While this will compile, it will throw a ClassCastException
        * when run.  Instead, use the initialization below.  Even the one
        * shown below is not the best, since Java does not allow a clean
        * way to allocate memory to hold an array of generic class objects.
        * For more information, see
        * https://courses.cs.washington.edu/courses/cse332/10su/notes/
        * genericArrays.html 
        * (Mind the line break in the above URL.  When you go to the above
        * URL, see Workaround #2.) */
      tree =  (TreeNode<E>[]) new TreeNode[50];
      root = 0;
      size = 0;
      indx = 0;
      /**
       *  Initializes this Binary Search Tree (array) object to be empty
       */ 


   }
   /**
    *  Returns the size of this BinarySearchTree object.
    *
    * @return the size of this BinarySearchTree object.
    *
    */
   public int size( )
   {
       return size;
   }
   
   protected static class TreeNode<E> {
      E element;
      int parent, left, right;
		
      public TreeNode (E element, int parent, int left, int right) {
         this.element = element;
         this.parent = parent;
         this.left = left;
         this.right = right;
      }
   }

   public boolean add(E element)  {

      boolean r = false;
     int i=0;//To keep a track of parent node
      /**
       * Example code to demonstrate creating a simple tree.  You will
       * replace this code to create a more complex tree */
      if (size == 0)  {
         tree[root] = new TreeNode(element, -1, -1, -1);
         r = true;
         size++;
      }
      else{
    	  TreeNode<E> temp = tree[i];
          int comp;
          indx++;
          tree[indx] =new TreeNode(element, -1, -1, -1);//element=element;
          while (true) 
          {
              comp =  element.compareTo (temp.element);
              
              if (comp < 0){
                  if (temp.left != -1){
                      i=temp.left;
                      temp = tree[temp.left];
                  }
                  else 
                  {
                      temp.left = indx;
                      tree[indx].parent=i;
                      size++; 
                      r= true;
                      break;
                  } // temp.left == null
              }
              else if (temp.right != -1){
            	  i=temp.right;
                  temp = tree[temp.right];
              }
              else 
              {
            	  temp.right = indx;
                  tree[indx].parent=i;
                  size++; 
                  r= true;
                  break;
              } // temp.right == null
          } // while
      } // root not null
      return r;
   }

   /**
    * print - Prints the array based tree as a table with 4 columns.
    */
   void print()  {
      int indx;

      for (indx = 0; indx < size; indx++)  {
         System.out.println(tree[indx].element + ", " +
                            tree[indx].parent  + ", " +
                            tree[indx].left    + ", " +
                            tree[indx].right);
      }
   }
   public String inorder()  {
	   return inorder_p(tree[root]);
    }
   
   private String inorder_p(TreeNode<E> root)  {
       String s = "";
       if (root != null)  {
    	   if (root.left!=-1)
    		   s +=" "+ inorder_p(tree[root.left]); 
           s += " "+ root.element.toString();
           if (root.right!=-1)
        	   s += " " +  inorder_p(tree[root.right]);
       }
       return s;
    }
   
   public boolean remove (E obj)
   {
       TreeNode<E> e = getTreeNode (obj);
       if (e == null)
           return false;
       deleteTreeNode (e);       
       return true;
   } // method remove
   
   protected TreeNode<E> getTreeNode (E obj) 
   {
       int comp;

       if (obj == null)
          throw new NullPointerException();
       TreeNode<E> e = tree[root];
       while (e != null) 
       {
           comp = obj.compareTo (e.element);
           if (comp == 0)
               return e;
           else if (comp < 0){
        	   if (e.left!=-1)
        		   e = tree[e.left];
        	   else
        		   e=null;
           }
           else{
        	   if (e.right!=-1)
        		   e = tree[e.right];
        	   else
        		   e=null;
           }
               
       } // while
       return null;
   } // method getTreeNode
   protected TreeNode<E> deleteTreeNode (TreeNode<E> p) 
   {
       size--;
       indx--;
       // If p has two children, replace p's element with p's successor's
       // element, then make p reference that successor.
       if (p.left != -1 && p.right != -1) 
       {
           TreeNode<E> s = successor (p);
           p.element = s.element;
           p = s;
       } // p had two children


       // At this point, p has either no children or one child.

       TreeNode<E> replacement=null;
        
       if (p.left != -1)
           replacement = tree[p.left];
       else if(p.right !=-1)
           replacement = tree[p.right];

       // If p has at least one child, link replacement to p.parent.
       if (replacement != null) 
       {
           replacement.parent = p.parent;
           if (p.parent == -1)
               tree[root] = replacement;
           else if (p == tree[tree[p.parent].left])
               tree[tree[p.parent].left]  = replacement;
           else
        	   tree[tree[p.parent].right]  = replacement;
       } // p has at least one child  
       else if (p.parent == -1)
           tree[root].element = null;
       else 
       {
           if ((tree[p.parent].left!= -1) && p == tree[tree[p.parent].left])
               tree[p.parent].left = -1;
           else
        	   tree[p.parent].right = -1;        
       } // p has a parent but no children
       return p;
   } // method deleteTreeNode


   /**
    *  Finds the successor of a specified TreeNode object in this 
    *  BinarySearchTree. The worstTime(n) is O(n) and averageTime(n) is 
    *  constant.
    *
    *  @param e - the TreeNode object whose successor is to be found.
    *
    *  @return the successor of e, if e has a successor; otherwise, return null
    *
    */
   protected TreeNode<E> successor (TreeNode<E> e) 
   {
       if (e == null)
           return null;
    // successor is leftmost TreeNode in right subtree of e
           TreeNode<E> p = tree[e.right];
           while (p.left != -1)
               p = tree[p.left];
         if(p.right!=-1)
        	 tree[p.parent].left = p.right;
      
           return p;
   } // method successor
  /* public static void main(String[] args)  {
      CS401BSTArray<Integer> myGenClassArray = new CS401BSTArray<Integer>();

      myGenClassArray.add(33);
      myGenClassArray.add(20);
      myGenClassArray.add(40);

      myGenClassArray.print();
   }*/
}
