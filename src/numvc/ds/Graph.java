package numvc.ds;

import java.util.Arrays;

public class Graph {
	Vertex[] vertexs;
	private int cutOff = 5;
	double rho = 0.3;
	double gamma = 0.5;
	double mean = 1;

	public Graph(int[][] ip) {
		vertexs = new Vertex[ip.length];

		for (int i = 0; i < ip.length; i++) {
			vertexs[i] = new Vertex(i + 1);
			vertexs[i].edges = new Edge[ip[i].length];

			for (int j = 0; j < ip[i].length; j++) {
				vertexs[i].edges[j] = new Edge(i + 1, ip[i][j]);
			}
		}
		gamma *= vertexs.length;
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
		computeGreedyVC();
		while (elapsedTime < cutOff) {
			if (uncoveredEdgeExists() == null) {
				Vertex u = getVertexWithHighestDScoreFromC(elapsedTime);
				removeFromC(u.id);
				vertexs[u.id - 1].time = elapsedTime;
				elapsedTime++;
				continue;
			}
			Vertex u = getVertexWithHighestDScoreFromC(elapsedTime);
			removeFromC(u.id);
			vertexs[u.id - 1].time = elapsedTime;
			u.confChange = 0;
			for (Edge e : u.edges) {
				vertexs[e.id - 1].confChange = 1;
			}
			Edge e = uncoveredEdgeExists();
			Vertex v = chooseOneVertex(e, elapsedTime);
			addToC(v.id);
			vertexs[v.id - 1].time = elapsedTime;
			for (Edge e1 : v.edges) {
				vertexs[e1.id - 1].confChange = 1;
			}
			weightUpdate();
			updateMean();
			if (mean >= gamma)
				updateWeight();
			elapsedTime++;
		}
	}

	private void updateWeight() {
		for (Vertex vertex : vertexs) {
			for (Edge e : vertex.edges) {
				e.w = (int) (rho * e.w);
			}
		}
	}

	private void updateMean() {
		double sum = 0;
		for (Vertex vertex : vertexs) {
			for (Edge e : vertex.edges) {
				sum += e.w;
			}
		}
		mean = sum / vertexs.length;
	}

	private void weightUpdate() {
		for (Vertex vertex : vertexs) {
			for (Edge e : vertex.edges) {
				if (!e.covered) {
					e.w++;
				}
			}
		}
	}

	private Vertex chooseOneVertex(Edge e, int k) {
		updateDScores();

		Vertex res = null;
		if (vertexs[e.from - 1].confChange == 1) {
			if (vertexs[e.id - 1].confChange == 1) {
				if (vertexs[e.id - 1].dscore > vertexs[e.from - 1].dscore) {
					res = vertexs[e.id - 1];
				} else if (vertexs[e.id - 1].dscore == vertexs[e.from - 1].dscore) {
					if ((k - vertexs[e.id - 1].time) > (k - vertexs[e.from - 1].time))
						res = vertexs[e.id - 1];
					else
						res = vertexs[e.from - 1];
				}
			} else {
				res = vertexs[e.from - 1];
			}
		} else if (vertexs[e.id - 1].confChange == 1) {
			res = vertexs[e.id - 1];
		}
		return res;
	}

	public Vertex getVertexWithHighestDScoreFromC(int k) {
		updateDScores();
		Vertex high_d = null;
		for (Vertex vertex : vertexs) {
			if (vertex.isInC) {
				if (high_d == null) {
					high_d = vertex;
					continue;
				}
				if (vertex.dscore > high_d.dscore) {
					high_d = vertex;
				} else if (vertex.dscore == high_d.dscore) {
					if ((k - vertex.time) > (k - high_d.time))
						high_d = vertex;
					else
						high_d = high_d;
				}
			}
		}
		return high_d;
	}

	private Vertex getVertexWithHighestDScore(int k) {
		updateDScores();
		Vertex high_d = vertexs[0];
		for (Vertex vertex : vertexs) {
			if (vertex.dscore > high_d.dscore) {
				high_d = vertex;
			} else if (vertex.dscore == high_d.dscore) {
				if ((k - vertex.time) > (k - high_d.time))
					high_d = vertex;
				else
					high_d = high_d;
			}
		}
		return high_d;
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
		return cost / 2;
	}
}
