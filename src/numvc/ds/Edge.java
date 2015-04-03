package numvc.ds;

public class Edge {
	int id;
	int w;
	boolean covered;

	public Edge(int id) {
		this.id = id;
		this.w = 1;
		this.covered = false;
	}

	@Override
	public String toString() {
		return "Edge [id=" + id + ", w=" + w + ", covered=" + covered + "]";
	}
}
