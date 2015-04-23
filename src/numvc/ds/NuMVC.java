package numvc.ds;

import java.io.IOException;

import numvc.utilities.Converter;

public class NuMVC {
	public static void main(String[] args) throws IOException {
		// int ip[][] = { { 2, 6 }, { 1, 6, 3 }, { 2, 4, 5 }, { 3 }, { 3, 6 },
		// { 1, 2, 5 } };
		// int ip1[][] = { { 2, 3, 4 }, { 1, 3, 4 }, { 1, 4, 2 }, { 1, 2, 3 } };
		
		long start = System.currentTimeMillis();
		Graph graph = new Graph(
				Converter
						.convertEdgeList("src/benchworks/bhoslib/frb30-15-1.mis"));
		//Graph graph = new Graph(ip);
		Graph.cutOff = 50;
		graph.computeNuMVC();
		System.out.println(graph);
		System.out.println("Execution time : "
				+ (System.currentTimeMillis()- start ) + " ms");
	}
}