Illinois Institute of Technology CS 401 Laboratory assignment #14

1 ) Prim's Implementation

For this implementation i have created file :CS401Vertex.java ,CS401Edge.java,PrimsDijkstra.java and CS401Primsmain.java
	CS401Vertex.java and CS401Edge.java 	are used to store information regarding Vertices and edges for a graph.
	PrimsDijkstra.java 			this has the core logic for the algo ,which works to give us desired output 
						for the graph. (ps- this also has code logic for Dijkstra)
	CS401PrimsMain.java 			This is the execution block for running the code for the tree which is given in the lab assignment.

Execution steps:

execute file "CS401PrimsMain"


Output:
-----------

Spanning Tree Prim

Start CS401Vertex:A
  Add edge <A, D, 1.0>
  Add edge <D, F, 4.0>
  Add edge <F, C, 2.0>
  Add edge <F, E, 5.0>
  Add edge <E, B, 3.0>
Total cost: 15

Start CS401Vertex:B
  Add edge <B, E, 3.0>
  Add edge <E, F, 5.0>
  Add edge <F, C, 2.0>
  Add edge <F, D, 4.0>
  Add edge <D, A, 1.0>
Total cost: 15

Start CS401Vertex:C
  Add edge <C, F, 2.0>
  Add edge <F, D, 4.0>
  Add edge <D, A, 1.0>
  Add edge <D, B, 5.0>
  Add edge <B, E, 3.0>
Total cost: 15

Start CS401Vertex:D
  Add edge <D, A, 1.0>
  Add edge <D, F, 4.0>
  Add edge <F, C, 2.0>
  Add edge <F, E, 5.0>
  Add edge <E, B, 3.0>
Total cost: 15

Start CS401Vertex:E
  Add edge <E, B, 3.0>
  Add edge <B, D, 5.0>
  Add edge <D, A, 1.0>
  Add edge <D, F, 4.0>
  Add edge <F, C, 2.0>
Total cost: 15

Start CS401Vertex:F
  Add edge <F, C, 2.0>
  Add edge <F, D, 4.0>
  Add edge <D, A, 1.0>
  Add edge <D, B, 5.0>
  Add edge <B, E, 3.0>
Total cost: 15

End Spanning Tree Prim


2) Dijkstra's Implementation
For this implementation i have created file :PrimsDijkstra.java and CS401DijkstraMain.java(attached tree diagram with expected output as tree.jpg)
	PrimsDijkstra.java 		this has the core logic for the algo ,which works to give us desired output 
						for the graph. (ps- this also has code logic for Dijkstra)
	CS401DijkstraMain.java		This is the execution block for running the code for the tree,the tree structure is also given in the same file with name tree.jpg.

Execution Steps:

change file path @ CS401DijkstraMain (line number 13 with current path for Graph.txt File)
execute file (CS401DijkstraMain)
There will be a prompt to enter "Please enter a start vertex (any from A,B,C,D,E,F,G):"
	Entered node will be the starting node from where we want to show all other nodes and their min path.

Output for A:
---------------

Please enter a start vertex (any from A,B,C,D,E,F,G):
A
Start Vertex: 1
1-->1:   Cost is   0,   Path is   -
1-->2:   Cost is   6,   Path is   1-->2
1-->3:   Cost is   5,   Path is   1-->3
1-->4:   Cost is   8,   Path is   1-->3-->4
1-->5:   Cost is   9,   Path is   1-->3-->4-->5
1-->6:   Cost is   11,   Path is   1-->3-->6
1-->7:   Cost is   17,   Path is   1-->3-->6-->7
