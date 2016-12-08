package CS401;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class CS401Vertex {
	private String name;
	private int state; 
	
	private boolean connected;
	
	private ArrayList<CS401Edge> CS401Edges;
	
	public CS401Vertex (String name) {
		this.name = name;
	}
	
	public void addCS401Edge(CS401Edge e) {
		if (CS401Edges == null) {
			CS401Edges = new ArrayList<>();
		}
		e.setSource(this);
		CS401Edges.add(e);
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<CS401Edge> getCS401Edges() {
		return CS401Edges;
	}
	
	public PriorityQueue<CS401Edge> getPriorityCS401Edges() {
		PriorityQueue<CS401Edge> pq = new PriorityQueue<>();
		for (CS401Edge CS401Edge : CS401Edges) {
			pq.add(CS401Edge);
		}
		return pq;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}
}
