package numvc.ds;

public class NuMVC {
	public static void main(String[] args) {
		int ip[][] = { { 3, 4, 2 }, { 1, 3, 4 }, { 1, 2, 4 }, { 3, 1, 2 } };
		Graph graph = new Graph(ip);
		graph.computeNuMVC();
		System.out.println(graph);
	}
}