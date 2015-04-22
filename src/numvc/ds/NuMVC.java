package numvc.ds;

import java.io.IOException;

import numvc.utilities.Converter;

public class NuMVC {
	public static void main(String[] args) throws IOException {
		// int ip[][] = { { 2, 6 }, { 1, 6, 3 }, { 2, 4, 5 }, { 3 }, { 3, 6 },
		// { 1, 2, 5 } };
		Graph graph = new Graph(
				Converter
						.convertEdgeList("src/benchworks/bhoslib/frb30-15-1.mis"));
		Graph.cutOff = 200;
		graph.computeNuMVC();
		System.out.println(graph);
	}
}