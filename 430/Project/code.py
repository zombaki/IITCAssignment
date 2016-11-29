##C:\Users\piyus\OneDrive\Documents\GitHub\IITCAssignment\430\Project\code.py
"""Implement dijkstra's algorithm for finding MST in NY subway problem ,which is the Main project for the problem
1)	Graph ,it has vertices and edge, in vertices we gave -1 to those which dosn't have relation. vertices has values with its equivalent index that we can use to find the edge value
2)	dijkstra method with 3 parameterss, 1st is base node,from where the analysis should start, 2nd is all the vertices and in the end is all the edge
	dijkstra method will return the minimum traverse path using dijkstra's algorithum
3)	kruskal's method with 2 parameters,vertices and edges, and output will be minimum spanning tree.
4)	keyForValue will return key when passed value of an dict
5)	Exeution section
"""
print("hello")
import mysql.connector
conn=mysql.connector.connect(user='root',password='gadha',host='localhost',database='padb')
mycur=conn.cursor()
mycur.execute("select * from  padb.subway_graph")
print(mycur.fetchall())