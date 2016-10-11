"""Implement Kruskal's and dijkstra's algorithm for finding MST
1)	Graph ,it has vertices and edge, in vertices we gave -1 to those which dosn't have relation. vertices has values with its equivalent index that we can use to find the edge value
2)	dijkstra method with 3 parameterss, 1st is base node,from where the analysis should start, 2nd is all the vertices and in the end is all the edge
	dijkstra method will return the minimum traverse path using dijkstra's algorithum
3)	kruskal's method with 2 parameters,vertices and edges, and output will be minimum spanning tree.
4)	keyForValue will return key when passed value of an dict
5)	Exeution section
"""
vertices = {
         'a' : 0,
         'b' : 1,
         'c' : 2,
         'd' : 3,
         'e' : 4,
	 'f' : 5,
	 'g' : 6
        }
edge = [	[0,1,3,-1,-1,10,-1],
		[1,0,1, 7, 5,-1, 2],
		[3,1,0, 9, 3,-1,-1],
		[-1,7,9,0, 2, 1,12],
		[-1,5,3,2, 0, 2,-1],
		[10,-1,-1,1,2,0,-1],
		[-1,2,-1,12,-1,-1,0]
	]

"""------------------------------------------
...
....dijkstra
.....input param : baseNode : Base Node from where we have to explore all the nodes
		   vertices : This will have dict of relation between node and index,like a-0 b-1,c-2 and so on, as we are using 2 dimensional array to store edge value
		   edge: Its a 2 D array to store edge value		   
.....Output :    returns all nodes with minimum value to traverse
----------------------------------------------"""
def dijkstra(baseNode,vertices,edge):
	loopEnable=True;#intialize with true ,as when to terminate the loop
	baseEdges = edge[vertices[baseNode]]#intially baseEdges is kept as known edge to base
	lowerBound = 0 #kept lower bound as 0,from where the algo starts
	while loopEnable:
		position=0 #initializing position ,so that it can be used to fetch edge value to get edge between vertices
		m=lowerBound		
		if any(i>lowerBound for i in baseEdges):#sanitation check to avoid null exception and to terminate loop if all the nodes has been traverse accroding to its weight
			m=min(i for i in baseEdges if i > lowerBound) #take the inimum weighted node
		else:
			loopEnable=False
		#print m,lowerBound
		lowerBound = m
		selectedNode=baseEdges.index(m)
		selectedNodeEdge=edge[selectedNode]#for selected node,we take all the edges
		for a in selectedNodeEdge:
			if a >0:
			  tempComparisonValue= a+m
			  if tempComparisonValue< baseEdges[position] or baseEdges[position]==-1: #check if the coputed value is less thenoriginal value in our baseEdges,if so we replace the value
				baseEdges[position]=tempComparisonValue
			position+=1 #to keep a track of index
		#print baseEdges
		
	result=set()
	#Below code is used to change base edge to readable relation with nodes
	tempindex=0	
	for a in baseEdges:
		temp=keyForValue(tempindex),a
		result.add(temp)
		tempindex+=1	
	return result
"""------------------------------------------
...
....Kruskal
.....input param : baseNode : Base Node from where we have to explore all the nodes
		   vertices : This will have dict of relation between node and index, as we are using 2 dimensional array to store edge value
		   edge: Its a 2 D array to store edge value		   
.....Output :    returns all explored nodes with min value of traversing
----------------------------------------------"""
ROWS = 7
COLUMNS = 7
parent = dict()
rank = dict()

def initialize(vertice): #initiaize both parent and rank dic with individual set
    parent[vertice] = vertice #all set is set as themselfs,so here the values will be 'a':'a','b':'b',and so on
    rank[vertice] = 0 #all set as 0,as all are not related to any other set.['a':0,'b':0,'c':0,and so on
def find(vertice): #takes care of checking if vertice is already related to any set or not
    if parent[vertice] != vertice:
        parent[vertice] = find(parent[vertice])#recursively call to check if in a dic there is relation with its parent
    return parent[vertice] #returns itself when it finds same parent == child
"""Part to union part will take care for adding new element to rootUnion will add the value to its element for example
 			we are adding a and b to MST map,then our parent will have a:b and b:b,so we have added b with a as parent,now as we keep on growing 
			we will change the parent as well,suppose now there is a relation where b is related to c,then first will 
			become a:c and we will have new entry as b:c, this action is taken care by the find function, so here work of root is to 
			take care for selection of node which should be taken care as common. """

def kruskal(vertices,edge):
	edges=set()
	#converting our graph in form of edges,v1,v2 form
	for i in range(0,ROWS):
	  for j in range(0,COLUMNS):
	    if (edge[i][j]>0 and j>i): #j>i is used to avoid duplicacy
		edgeLocal = edge[i][j],	keyForValue(i),keyForValue(j)	
		edges.add(edgeLocal)
	edges=list(edges)
	edges.sort() #sorting all the edges in increasing order
	mst=set()#initialize our MST with nothing
	for vertice in vertices:
          initialize(vertice) #we invoke initialize to set all vertices as individual node and rank as 0
	for e in edges:
		weight,v1,v2 =e
		root1 = find(v1)
		root2 =find(v2)
		#Part to union
		if (root1!=root2):
			if rank[root1] > rank[root2]:
            		   parent[root2] = root1
        		else:
            		   parent[root1] = root2
            		   if rank[root1] == rank[root2]: rank[root2] += 1
		    	mst.add(e)
    	return mst		


"""------------------------------------------
...
....keyForValue
.....input param : n : Index of a node	   
.....Output :    value of the node
----------------------------------------------"""
def keyForValue(n):
	 for node,position in vertices.iteritems():
            if position == n:
                    return node
"""------------------------------------------
...
....Execution Block
....	This has ,1 intialize baseNode
....	Execution dijkstra algo and output
....	Execution kruskal's algo and MST as output 
----------------------------------------------"""
baseNode='a'
print edge[vertices[baseNode]]
print 'Min Distances to each node from base node is (using dijkstra\'s) : '+ str(dijkstra(baseNode,vertices,edge))
print 'MST for the given graph is (kruskal\'s Algo)'+str(kruskal(vertices,edge))


