"""Implement Prim's algorithm for finding MST
1)	Graph ,it has edges and vertices, in vertices we gave -1 to those which dosn't have relation. edges has values with its equivalent index that we can use to find the vertices value
2)	prims method with 3 parameterss, 1st is base node,from where the analysis should start, 2nd is all the edges and in the end is all the vertices
	prims method will return the minimum traverse path using prim's algorithum
3)	keyForValue will return key when passed value of an dict
4)	Exeution section
"""
edges = {
         'a' : 0,
         'b' : 1,
         'c' : 2,
         'd' : 3,
         'e' : 4,
	 'f' : 5
        }
vertices = [	[0,3,-1,1,-1,-1],
		[3,0, 1,3,-1,-1],
		[-1,1,0,1, 4, 5],
		[1,3,1, 0,-1, 6],
		[-1,-1,4,-1,0,2],
		[-1,-1,5, 6,2,0]
	]
"""------------------------------------------
...
....prims
.....input param : baseNode : Base Node from where we have to explore all the nodes
		   edges : This will have dict of relation between node and index, as we are using 2 dimensional array to store vertices value
		   vertices: Its a 2 D array to store vertices value		   
.....Output :    returns all explored nodes with min value of traversing
----------------------------------------------"""
def prims(baseNode,edges,vertices):
	heap={'a':99,'b':99,'c':99,'d':99,'e':99,'f':99} #initially store all the value with 99 represent infinity as when nodes are not explored they are considered as infinity

	exploredNode = {} #intially explorednode is kept as blank
	
	heap[baseNode] = 0 #updating basenode to 0,as it is known node
	
	while heap:
		position=0 #initializing position ,so that it can be used to fetch vertices value to get vertices between edges
		node= min(heap, key=heap.get) #fetch min of explored heap
		print edges[node]
		del heap[node] #remove the node from heap, i.e min value.
		for a in vertices[edges[node]]: #for all values of vertices in our graph for min value node
			if a >0 : #if the value is non negative (it will be negative if its not pointing to that node and 0 value when pointing to itself)
				nodeNext=keyForValue(position) #get the node using index
				if heap.has_key(nodeNext):				
				  if heap[nodeNext]>a:
					heap[nodeNext]=a
					#temp = node+str(nodeNext)
					#print temp
					#Below Mentioned logic will take care for adding element to explored node or update if value is lower.
					if exploredNode.has_key(nodeNext):
					   if exploredNode[nodeNext]>a:
						exploredNode[nodeNext]=a
					else:
						temp={nodeNext:a}
						exploredNode.update(temp)
					print exploredNode

			position +=1		
		
		#print heap,vertices[2],keyForValue(2),heap['c']
		#print exploredNode
		#print node,heap
	return exploredNode

def keyForValue(n):
	 for node,position in edges.iteritems():
            if position == n:
                    return node



minSpaningTree = prims('b',edges,vertices)
print "minimum Steps required to traverse is "+ str(sum(minSpaningTree.values()))
