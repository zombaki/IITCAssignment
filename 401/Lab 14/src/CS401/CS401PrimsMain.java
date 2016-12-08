package CS401;

import java.util.ArrayList;

public class CS401PrimsMain {
	public static void main(String[] args) 
	{
		PrimsDijkstra graph = new PrimsDijkstra();
		
		CS401Vertex a = new CS401Vertex("A");
		CS401Vertex b = new CS401Vertex("B");
		CS401Vertex c = new CS401Vertex("C");
		CS401Vertex d = new CS401Vertex("D");
		CS401Vertex e = new CS401Vertex("E");
		CS401Vertex f = new CS401Vertex("F");
		
		graph.add(a);
		graph.add(b);
		graph.add(c);
		graph.add(d);
		graph.add(e);
		graph.add(f);
		
		a.addCS401Edge(new CS401Edge(b, 6));
		a.addCS401Edge(new CS401Edge(c, 5));
		a.addCS401Edge(new CS401Edge(d, 1));
		
		b.addCS401Edge(new CS401Edge(a, 6));
		b.addCS401Edge(new CS401Edge(d, 5));
		b.addCS401Edge(new CS401Edge(e, 3));
		
		c.addCS401Edge(new CS401Edge(a, 5));
		c.addCS401Edge(new CS401Edge(d, 5));
		c.addCS401Edge(new CS401Edge(f, 2));
		
		d.addCS401Edge(new CS401Edge(a, 1));
		d.addCS401Edge(new CS401Edge(b, 5));
		d.addCS401Edge(new CS401Edge(c, 5));
		d.addCS401Edge(new CS401Edge(e, 6));
		d.addCS401Edge(new CS401Edge(f, 4));
		
		e.addCS401Edge(new CS401Edge(b, 3));
		e.addCS401Edge(new CS401Edge(d, 6));
		e.addCS401Edge(new CS401Edge(f, 5));
		
		f.addCS401Edge(new CS401Edge(c, 2));
		f.addCS401Edge(new CS401Edge(d, 4));
		f.addCS401Edge(new CS401Edge(e, 5));
		
		ArrayList<CS401Vertex> ver = graph.getCS401Vertexs();
		System.out.println("Spanning Tree Prim\n");
		for (CS401Vertex ver1 : ver) 
		{
			graph.primsMST(ver1);
			System.out.print("\n");
		}
		System.out.println("End Spanning Tree Prim");
	}
}
