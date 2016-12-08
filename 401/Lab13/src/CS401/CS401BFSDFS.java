package CS401;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**************
 * CS401BFSDFS : Class where implementation for BFS and DFS has been done.
 * @author piyush
 *
 */

public class CS401BFSDFS {
	int max_row, max_col;
	int inf=-1;
              /*        A    B    C    D    E    F    G    H    I */
	int adj[][] = 
		   { /* A */ { inf, 8,   inf, 10,  inf, inf, 12,  inf, inf },
	         /* B */ { 8,   inf, inf, inf, 12,  18,  inf, inf, inf },
	         /* C */ { inf, inf, inf, inf, inf, 2,   inf, 10,  inf },
	         /* D */ { 10,  inf, inf, inf, inf, 8,   inf, inf, inf },
	         /* E */ { inf, 12,  inf, inf, inf, inf, 24,  inf, inf },
	         /* F */ { inf, 18,  2,   8,   inf, inf, inf, inf, inf },
	         /* G */ { 12,  inf, inf, inf, 24,  inf, inf, inf, inf },
	         /* H */ { inf, inf, 10,  inf, inf, inf, inf, inf, inf },
	         /* I */ { inf, inf, inf, inf, inf, inf, inf, 3,   inf }
	};
	static CS401Graph localGraph;
	static String str_arr[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
	final static int no_of_ver = 9;
	public static void main(String[] args)
	{
		new CS401BFSDFS().run();
	}
	
	void run()
	{
		
		String inputVer;
		//LOGIC TO GET THE Input VERTIEX from user
		do{
			System.out.print("Enter starting Vertex: ");
			Scanner sc = new Scanner (System.in);
			inputVer = sc.nextLine();
			if (Arrays.asList(str_arr).contains(inputVer))
				break;
			else
				System.out.println("Please enter Vertex any of the following character : "+Arrays.toString(str_arr));
		}while(true);
		//LOGIC FOR BFS
		localGraph = new CS401Graph();
			System.out.print("For starting vertex " + inputVer + ", the breadth-first traversal produces: " );
			String op1 = BFSTree(inputVer);
			System.out.println(op1+"\n");
			localGraph = new CS401Graph();
			System.out.print("For starting vertex " + inputVer + ", the depth-first traversal produces: " );
			String op2 = DFSTree(inputVer);
			System.out.println(op2+"\n");
	}
	/************
	 * BFS breath first search logical implementation
	 * @param input_ver
	 * @return string with traverse value
	 */
	String BFSTree(String input_ver)
	{
		String op1 = new String();
		int trav_flag[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		Queue<String> queue = new LinkedList<String>();
		queue.add(input_ver);
		op1 += input_ver + ", ";//Initialize  o/p value with input vertex
		set_vertex(trav_flag, input_ver);
		String node = input_ver;
		while(queue.isEmpty() == false)
		{
			localGraph.set_n_ver(node);
			while(true)
			{
				String next_ver = localGraph.get_next_ver(node);//GET Next vertex or node
				if(next_ver != null)
				{
					if(check_vertex(trav_flag, next_ver) == false)//Check if that vertex is already explored
					{
						queue.add(next_ver);//if not the add the element to queue
						op1 += next_ver + ", ";
						set_vertex(trav_flag, next_ver);//Set vertex as explored vertex (or node)
					}
					
				}
				else
				{
					break;
				}
			}
			queue.remove();
			node = queue.peek();
		}
		return op1;
	}
	String DFSTree(String input_ver)
	{
		String op2 = new String();
		int trav_flag[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0};
		Stack<String> stack = new Stack<>();
		stack.push(input_ver);
		op2 += input_ver + ", ";//Initialize  o/p value with input vertex
		set_vertex(trav_flag, input_ver);
		
		String node = input_ver;
		while(stack.isEmpty() == false)
		{
			
			localGraph.set_n_ver(node);
			String next_ver = localGraph.get_next_ver(node);//GET Next vertex
			//System.out.println(next_ver);
			if(next_ver != null)
			{
				if(check_vertex(trav_flag, next_ver) == false)//Check if that vertex is already explored
				{
					stack.push(next_ver);//if not then we stack the new node and add to as explored node
					op2 += next_ver + ", ";
					set_vertex(trav_flag, next_ver);//sets the node as explored node
					node = next_ver;
				}
				
			}
			else
			{
				node = stack.pop();
				if(node != null)
				{
					localGraph.set_n_ver(node);
					if(localGraph.get_ver_count(node) > 0)
					{
						stack.push(node);
					}
				}
			}
		}
		return op2;
	}
	/*******
	 * check_vertex does check if the vertex is explored or not
	 * @param flags
	 * @param node
	 * @return true or false
	 */
	boolean check_vertex(int flags[], String node)
	{
		for(int i=0; i<no_of_ver; i++)
		{
			if(node.compareToIgnoreCase(str_arr[i]) == 0)
			{
				if(flags[i] == 1)
					return true;
				else
					return false;
			}
		}
		return false;
	}
	/*********
	 * set vertex : it takes care for setting node as explore.
	 * @param flags
	 * @param node
	 */
	void set_vertex(int flags[], String node)
	{
		for(int i=0; i<no_of_ver; i++)
		{
			if(str_arr[i].compareToIgnoreCase(node) == 0)
			{
				flags[i] = 1;
				return;
			}
		}
	}
	/************
	 * CS401Graph This has the basic graph on which we are working
	 * @author piyush
	 *
	 */
	class CS401Graph
	{
		private Vertex v[] = new Vertex[no_of_ver];
		int ver_index = -1;
		String ver_label = "";
		
		public CS401Graph()
		{
			for(int i=0; i< no_of_ver; i++)
			{
				v[i] = new Vertex(str_arr[i], i);
				for(int j=0; j < no_of_ver; j++)
				{
					if(adj[i][j] != -1)
					{
						Edge n = new Edge(str_arr[i],str_arr[j], adj[i][j]);
						v[i].add_edge(n);
					}
				}
			}
		}
		
		public int get_ver_count(String node)
		{
			if((node.compareToIgnoreCase(ver_label) != 0) && (ver_index != -1) )
			{
				set_n_ver(node);
			}
			return v[ver_index].get_edge_count();
		}
		
		public void set_n_ver(String node)
		{
			for(int i=0; i<no_of_ver; i++)
			{
				if(node.compareToIgnoreCase(v[i].get_label()) == 0)
				{
					ver_index = i;
					ver_label = v[i].get_label();
					break;
				}
			}
		}
		/*************
		 * This method will return then next vertex which is associated and will remove the edge associated.
		 * @param node
		 * @return next node associated
		 */
		public String get_next_ver(String node)
		{
			if((node.compareToIgnoreCase(ver_label) != 0) && (ver_index != -1))
			{
				set_n_ver(node);
			}
			return v[ver_index].get_closer_edge();
		}
	}
}
/***************
 * Vertex Class to store details for an vertex
 * @author piyush
 *
 */
class Vertex
{
	private String name;
	private int id;
	private int state;
	private int pred;
	private PriorityQueue<Edge> edgelist;
	
	public Vertex(String l, int i)
	{
		name = l;
		id = i;
		edgelist = null;
		pred = -1;
		state = 0;
	}
	
	public String toString()
	{
		String s = "Vertex: " + name + "\n";
		s = s + " edgelist: \n";
		
		Iterator<Edge> itr = edgelist.iterator();
		while (itr.hasNext())
		{
			Edge n = itr.next();
			s =  s + n;
		}
		return s;
	}
	
	public void add_edge(Edge n)
	{
		if (edgelist == null)
			edgelist = new PriorityQueue<Edge>();
		edgelist.add(n);
		state++;
		}
	
	public Edge remove_edge()
	{
		Edge n = null;
		
		if (edgelist.size() > 0)
		{
			n = edgelist.peek();
			edgelist.remove(n);
			state--;
		}
		
		if ( n== null)
			state = 0;
		return n;
	}
	
	public int get_edge_count()
	{
		return state;
	}
	
	public String get_closer_edge()
	{
		Edge neigh = remove_edge();
		if(neigh != null)
			return neigh.name();
		else
			return null;
	}
	
	public String get_label()
	{
		return name;
	}
}

class Edge implements Comparable<Edge>
{
	private String source;
	private String destination;
	private Integer cost;
	
	public Edge(String s, String d, int c)
	{
		source = s; destination = d; cost = c;
	}
	
	public String toString()
	{
		return "  " + destination + ", cost: " + cost + "\n";
	}
	
	public int compareTo(Edge o)
	{
		if (this.cost < o.cost) return -1;
		else if (this.cost > o.cost) return 1;
		else return 0;
	}
	
	public String name()
	{
		return destination;
	}
}
