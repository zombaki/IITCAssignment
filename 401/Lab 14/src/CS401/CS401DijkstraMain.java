package CS401;

import java.io.*;
import java.util.*;

public class CS401DijkstraMain {
	public static void main(String[] args) {

		Scanner s1 = new Scanner(System.in);
		int source = 0;
		String vertex;
		
		String file = "C:/Users/piyus/workspace/CS401Assignment/Graph.txt" ;
		
		List<List<Integer>> matrix;
		List<Integer> removedNodes = new ArrayList<Integer>();
		//Logic to read graph from file and form a matrix
		matrix = PrimsDijkstra.readGraphFromFile(file);
			//Extract Shortest Path uses dijkstra's algorithm and convert the matrix and return result list ,which has shortest path with min weight to all the nodes.
			List<Object[]> resultList = PrimsDijkstra.extractShortestPath(matrix,
					removedNodes);
			//User input for getting the start of the vertex
			System.out.println("Please enter a start vertex (any from A,B,C,D,E,F,G):");
			vertex = s1.nextLine();
			switch(vertex)
			{
			case "A":
				source =1;
				break;
			case "B":
				source =2;
				break;
			case "C":
				source = 3;
				break;
			case "D":
				source =4;
				break;
			case "E":
				source =5;
				break;
			case "F":
				source =6;
				break;
			case "G":
				source = 7;
				break;
			}
		//Perform operation to show output ,this method just makes result list a readable file
		PrimsDijkstra.performOperations(source, resultList);
		
		s1.close();
	}

	
}
