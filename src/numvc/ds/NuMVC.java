package numvc.ds;

public class NuMVC {
	public static void main(String[] args) {
		int ip[][] = { {2, 6 }, { 1, 6, 3 }, { 2, 4, 5 }, { 3 }, {3, 6}, {1, 2, 5} };
		Graph graph = new Graph(ip);
		graph.computeNuMVC();
		System.out.println(graph);
	}
}