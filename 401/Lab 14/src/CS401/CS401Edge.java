package CS401;

public class CS401Edge implements Comparable<CS401Edge> {

	private CS401Vertex source;
	private CS401Vertex destination;
	private int cost;
	
	public CS401Edge(CS401Vertex vert, int cost) {
		this.destination = vert;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(CS401Edge e) {
		if (this.cost < e.cost) {
			return -1;
		} else if (this.cost > e.cost) {
			return 1;
		} else {
			return 0;
		}
	}

	public CS401Vertex getSource() {
		return source;
	}

	public void setSource(CS401Vertex source) {
		this.source = source;
	}

	public CS401Vertex getDestination() {
		return destination;
	}

	public void setDestination(CS401Vertex destination) {
		this.destination = destination;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
