package numvc.ds;

import java.util.Arrays;

public class Graph {
	Vertex[] vertexs;
	private int cutOff = 100;

	public Graph(int[][] ip) {
		vertexs = new Vertex[ip.length];

		for (int i = 0; i < ip.length; i++) {
			vertexs[i] = new Vertex(i + 1);
			vertexs[i].edges = new Edge[ip[i].length];

			for (int j = 0; j < ip[i].length; j++) {
				vertexs[i].edges[j] = new Edge(ip[i][j]);
			}
		}
	}

	@Override
	public String toString() {
		return "Graph [vertexs=" + Arrays.toString(vertexs) + "]";
	}

	public void computeGreedyVC() {
		Edge e;
		while ((e = uncoveredEdgeExists()) != null) {
			if (!e.covered)
				addToC(e.id);
		}
	}

	private Edge uncoveredEdgeExists() {
		for (Vertex vertex : vertexs) {
			for (Edge e : vertex.edges) {
				if (!e.covered) {
					return e;
				}
			}
		}
		return null;
	}

	public void setEdge(int n, boolean v) {
		vertexs[n - 1].isInC = true;
		for (Edge e : vertexs[n - 1].edges) {
			e.covered = v;
			for (Edge e1 : vertexs[e.id - 1].edges) {
				if (e1.id == n) {
					e1.covered = v;
					break;
				}
			}
		}
	}

	public void addToC(int n) {
		setEdge(n, true);
	}

	public void removeFromC(int n) {
		setEdge(n, false);
		vertexs[n - 1].isInC = false;
		for (Edge e : vertexs[n - 1].edges) {
			if (vertexs[e.id - 1].isInC)
				addToC(e.id);
		}
	}

	public void computeNuMVC() {
		int elapsedTime = 1;
		while (elapsedTime < cutOff) {
			if (uncoveredEdgeExists() == null) {
				getVertexWithHighestDScore();
			}
		}
	}

	private void getVertexWithHighestDScore() {
		updateDScores();
		for (Vertex vertex : vertexs) {
			
		}
	}

	public void updateDScores() {
		int x = getCost();
		for (Vertex vertex : vertexs) {
			int y;
			if (vertex.isInC) {
				removeFromC(vertex.id);
				y = getCost();
				addToC(vertex.id);
			} else {
				addToC(vertex.id);
				y = getCost();
				removeFromC(vertex.id);
			}
			vertex.dscore = x - y;
		}
	}

	private int getCost() {
		int cost = 0;
		for (Vertex vertex : vertexs) {
			for (Edge e : vertex.edges) {
				if (!e.covered) {
					cost += e.w;
				}
			}
		}
		return cost/2;
	}
}
