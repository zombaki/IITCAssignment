package CS401;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
/************
 * PrimsDijkstra : This has code for both implementation, which is for Prims implementation and Dijkstra's.
 * @author piyush
 *
 */
public class PrimsDijkstra {
	private ArrayList<CS401Vertex> ver;
	
	public PrimsDijkstra() {
		ver = new ArrayList<>();
	}
	
	public void add(CS401Vertex v) {
		ver.add(v);
	}
	
	public CS401Vertex getCS401Vertex(String name) {
		for (CS401Vertex a : ver) {
			if (a.getName().equals(name)) {
				return a;
			}
		}
		throw new RuntimeException("No CS401Vertex : " + name);
	}
	
	public ArrayList<CS401Vertex> getCS401Vertexs() {
		return ver;
	}
	protected void visitCS401Vertex(CS401Vertex v) {
		v.setState(2);
		System.out.print(v.getName() + " ");
	}
	
	protected void discoverCS401Vertex(CS401Vertex v) {
		v.setState(1);
	}
	/**********
	 * PrimMST : This will take start vertex as i/p,based on which this program will print all the path with their edges.
	 * @param startV
	 */
	public void primsMST(CS401Vertex startV) {
		
		for (CS401Vertex a : this.ver) {
			a.setConnected(false);
		}
		
		System.out.println("Start CS401Vertex:" + startV.getName());
		//We create a priority queue, where we add all the edges
		PriorityQueue<CS401Edge> priorityQueue = new PriorityQueue<>();
		ArrayList<CS401Vertex> connectedCS401Vertexs = new ArrayList<>();
		startV.setConnected(true);
		connectedCS401Vertexs.add(startV);
		
		ArrayList<CS401Edge> neighborEdges = startV.getCS401Edges();
		//Add all the edges to queue
		for (CS401Edge edge : neighborEdges) {
			priorityQueue.add(edge);
		}
	
		int totalCost = 0;
		//This loop will run till the time all the vertices are covered
		while (connectedCS401Vertexs.size() < this.ver.size()) {
			if (priorityQueue.isEmpty()) {
				break;
			}
			CS401Edge edge = priorityQueue.remove();
			if (edge.getSource().isConnected() && !edge.getDestination().isConnected()) {
				
				System.out.println("  Add edge <" + edge.getSource().getName() +
						", " + edge.getDestination().getName() + ", " + (double)edge.getCost() + ">");
				totalCost += edge.getCost();
				edge.getDestination().setConnected(true);
				connectedCS401Vertexs.add(edge.getDestination());
				neighborEdges = edge.getDestination().getCS401Edges();
				for (CS401Edge nerghborEdge : neighborEdges) {
					if (edge.getDestination() != edge.getSource()) {
						priorityQueue.add(nerghborEdge);
					}
				}
			}
		}
		
		System.out.println("Total cost: " + totalCost);
	}
/********
 * readGraphFromFile : This will take file as input, will return matrix
 * @param fileName
 * @return
 */
public static List<List<Integer>> readGraphFromFile(String fileName) {
		
		List<List<Integer>> matrix = null;
		try {
			File file = new File(fileName);
			FileReader f = new FileReader(file);
			BufferedReader b = new BufferedReader(f);

			matrix = new ArrayList<List<Integer>>();
			String readLine = b.readLine(); 

			while (null != readLine) {

				String[] array = readLine.split(" ");

				List<Integer> row = new ArrayList<>();
				for (String value : array) {
					row.add(Integer.valueOf(value)); // reading values into row list
				}
				matrix.add(row); // reading rows into matrix
				readLine = b.readLine();// reading second to last line of file
			}
			b.close();// close reader

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Cannot read file");
		}catch (NumberFormatException e){
			System.out.println("File contents invalid");
		}

		return matrix;
	}

	/************
	 * performOperations
	 * @param source
	 * @param resultList
	 */
	public static void performOperations(int source,
			List<Object[]> resultList) {
		Object[] resultObj = resultList.get(source - 1);
		//read path from object
		int[] distance = (int[]) resultObj[0];
		String[] path = (String[]) resultObj[1];
		System.out.println("Start Vertex: "+source);
		for (int i = 1; i <= path.length; i++) {
			String inter;
			String[] pathValue = path[i-1].split(",");
			if(pathValue.length == 1){
				inter = pathValue[0];
			}else {
				inter = pathValue[1];
			}
			if(distance[i - 1]==-1){
				distance[i - 1]=0;
			}
			
			System.out.println(source+"-->" +i + ":   Cost is   " + distance[i - 1] + ",   Path is   "
					+ path[i - 1].replaceAll(",", "-->"));
		}
	}
	/*******************
	 * Setting up the array for the input text file
	 * @param matrix
	 * @param removedNodes
	 * @return
	 */
	public static List<Object[]> extractShortestPath(
			List<List<Integer>> matrix, List<Integer> removedNodes) {
		List<Object[]> resultList = new ArrayList<Object[]>();
		int size = matrix.size();
		int[][] arr = new int[size][size];
		int maxVal = 999;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				arr[i][j] = matrix.get(i).get(j);
				if (arr[i][j] <= -1)
					arr[i][j] = maxVal;
			}
		}
		for (int i = 0; i < matrix.size(); i++) {
			Object[] resultObj = shortestPath(arr, size,
					i + 1, maxVal, removedNodes);
			resultList.add(resultObj);
		}
		return resultList;
	}
/*********************************************************
 * shortestPath: This is the core for our program where we have implemented Dijkstra algorithm
 * @param arr:array for all the edges and vertices
 * @param size :matrix size
 * @param source:
 * @param maxVal:max value,which is equivalent to inf that is 999 in our case
 * @param removedNodes
 * @return
 */
	public static Object[] shortestPath(int[][] arr,
			int size, int source, int maxVal, List<Integer> removedNodes) {

		boolean[] visited = new boolean[size];

		int[] distance = new int[size];
		String[] path = new String[size];
		int min;
		int nextNode = 0;
		String prevPath;
		source--;

		for (int i = 0; i < size; i++) {
			visited[i] = false; 
			distance[i] = arr[source][i]; //store the distance for all the nodes
		}
		distance[source] = 0;

		for (int i = 0; i < size; i++) {
			//set inital values for path
			path[i] = String.valueOf(source + 1) + "," + String.valueOf(i + 1);
			if (distance[i] == maxVal) {
				path[i] = String.valueOf(source + 1);
			}
		}
		path[source] = "-";
		//change the source as true if visited
		visited[source] = true;

		for (int i = 0; i < size; i++) {
			if (removedNodes.contains(i + 1)) {
				path[i] = "-";
				visited[i] = true;
			}
		}

		for (int i = 0; i < size; i++) {
			min = maxVal;//We keep on making min as max value,so that previous case's maximum value is kept as reference to judge next lowest value

			for (int j = 0; j < size; j++)
				if (min > distance[j] && visited[j] != true) {
					min = distance[j];
					nextNode = j;
				}

			visited[nextNode] = true; //This node is set to visited =  true
			prevPath = path[nextNode]; //This will have value for previous node.

			for (int k = 0; k < size; k++) {
				if (visited[k] != true) {
					if (min + arr[nextNode][k] < distance[k]) {
						distance[k] = min + arr[nextNode][k];
						path[k] = prevPath + "," + String.valueOf(k + 1);
					}
				}
			}
		}
		for (int i = 0; i < path.length; i++) {
			if(distance[i] == 999){
				path[i] = "-";
				distance[i] = -1;
			}
		}
		Object[] obj = new Object[2];
		obj[0] = distance;
		obj[1] = path;
		return obj;
	}



}
