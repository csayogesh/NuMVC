package numvc.ds;

public class NuMVC {
	public static void main(String[] args) {
		int ip[][] = { {2, 6 }, { 1, 6, 3 }, { 2, 4, 5 }, { 3 }, {3, 6}, {1, 2, 5} };
		Graph graph = new Graph(ip);
		graph.addToC(2);graph.addToC(4);graph.addToC(1);
		graph.addToC(3);graph.addToC(5);
		graph.addToC(6);
		graph.computeNuMVC();
		System.out.println(graph);
	}
}