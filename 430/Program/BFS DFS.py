"""Graph contains the detail for all the connection with nodes to different node
This code contains in five section
1)	Graph ,which contains the relation as a to which all node. here we have used dictionary to achieve that
2)	bfs with 2 parameters ,pass graph and second is base node,from where search need to be started
3)	dfs with 2 parameters ,pass graph and second is base node,from where search need to be started
4)	bfsPath with 3 parameters, graph, basenode and end point. this program will return path or error in case node is not there in path.
5)	Exeution section
"""
graph = {
        'a': ['b', 'c', 'd'],
        'b': ['e', 'f','a'],
        'e': ['i', 'j','b'],
        'd': ['g', 'h','a','b'],
        'g': ['k', 'l','d']
        }
"""------------------------------------------
...
....bfs
.....input param : graph : dictonary values
		   baseNode : Starting node
		   
.....Output :    returns message when all nodes are explored
----------------------------------------------"""
def bfs(graph,baseNode):
	stack=[] #empty Stack to maintain the paths
	exploredNode =set() #explored node
	stack.append([baseNode])
	while stack:
		path=stack.pop(0) #Popout the first element from the stack
		node = path[-1] #Consider the right most element of the path
		for nxtNodes in graph.get(node, []):
		    #To avoid circular loop and not to enter node which is already explored
		    if nxtNodes not in exploredNode:
			    newpath = list(path)  
			    newpath.append(nxtNodes)  #Add nxtNode ti path and add to stack
			    stack.append(newpath)
		exploredNode.add(node)                #Add explored node to explored Node
		#print stack
	return 'All the nodes are explored using BFS'
	#return exploredNode	
"""------------------------------------------
...
....dfs 
.....input param : graph : dictonary values
		   baseNode : Starting node
		   exploredNode : This we will pass explored nodes,so that fo recursive serch that can be considered and same node need not be explored twice.
This will run in recursion till the last element as we want to explore till the last element for a node.
.....Output :    Returns all explored nodes using dfs
----------------------------------------------"""
def dfs(graph,baseNode,exploredNode=None):
	if exploredNode is None:#First time will be executed to set the value of exploredNode as set()
         exploredNode = set()
        exploredNode.add(baseNode)#add the node which is pass for DFS search algo
        for next in graph.get(baseNode,[]):#Associate the next available node when 
	 #print next
         if next not in exploredNode:
		dfs(graph, next, exploredNode)
		#print next
        return exploredNode
	
"""------------------------------------------
...
....bfsPath 
.....input param : graph : dictonary values
		   baseNode : Starting node
		   endNode  : Destination Node
.....Output :    Path from base to destination
		or exception error if no path found
----------------------------------------------"""	
def bfsPath(graph,baseNode,endNode):
	stack=[] #empty Stack to maintain the paths
	exploredNode =set() #explored node
	stack.append([baseNode])
	while stack:
		path=stack.pop(0) #Popout the first element from the stack
		node = path[-1] #Consider the right most element of the path
		if node == endNode:
			return 'Optimum path to reach from ' + str(baseNode) +' to ' +\
 str(endNode) + ' is '+ str(path)
		for nxtNodes in graph.get(node, []):
		    #To avoid circular loop and not to enter node which is already explored
		    if nxtNodes not in exploredNode:
			    newpath = list(path)
			    newpath.append(nxtNodes)
			    stack.append(newpath)
		exploredNode.add(node)
	return 'Path Not found'
	
"""

baseNode = 'a'
stack=[] #empty Stack
stack.append(baseNode)
stack.append('b')
print stack

stack=graph.get('a',[])
while True:
 stack

for node in graph.get('a', []):
	print node	
	stack = graph.get(node,[])
"""
print bfs(graph,'b')
print bfsPath(graph,'a','k')
print dfs(graph,'a')	

