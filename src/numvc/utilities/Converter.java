package numvc.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Converter {
	private static BufferedReader reader;

	@SuppressWarnings("unchecked")
	public static void convertEdgeList(String filename) throws IOException {
		reader = new BufferedReader(new FileReader(filename));
		String line;
		ArrayList<Integer> list[] = null;
		// { {2, 6 }, { 1, 6, 3 }, { 2, 4, 5 }, { 3 }, {3, 6}, {1, 2, 5} };
		while ((line = reader.readLine()) != null) {
			String[] ls = line.split(" ");
			if (ls[0].equals("p")) {
				list = new ArrayList[Integer.parseInt(ls[2])];
				for (int i = 0; i < list.length; i++) {
					list[i] = new ArrayList<Integer>();
				}
			} else if (ls[0].equals("e")) {
				int from = Integer.parseInt(ls[1]);
				int to = Integer.parseInt(ls[2]);
				list[from - 1].add(to);
			}
		}
		for (ArrayList<Integer> arrayList : list) {
			System.out.println(arrayList);
		}
	}

	public static void main(String[] args) throws IOException {
		Converter.convertEdgeList("src/benchworks/frb30-15-1.mis");
	}
}
