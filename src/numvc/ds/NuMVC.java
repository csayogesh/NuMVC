package numvc.ds;

import java.io.IOException;

import numvc.utilities.Converter;


public class NuMVC {
	public static void main(String[] args) throws IOException {
		// int ip[][] = { { 2, 6 }, { 1, 6, 3 }, { 2, 4, 5 }, { 3 }, { 3, 6 },
		//	{ 1, 2, 5 } };
		Graph graph = new Graph(Converter.convertEdgeList("src/benchworks/dimacs/brock200_2.clq"));
		graph.computeNuMVC();
		System.out.println(graph);
	}
}