package numvc.ds;

import java.util.Arrays;

public class Vertex {
	boolean isInC;
	int time;
	int dscore;
	int confChange;
	Edge[] edges;

	Vertex() {
		this.dscore = 0;
		this.isInC = false;
		this.time = 1;
		this.confChange = 0;
	}

	@Override
	public String toString() {
		return "\nVertex [isInC=" + isInC + ", time=" + time + ", dscore="
				+ dscore + ", confChange=" + confChange + ", edges="
				+ Arrays.toString(edges) + "]";
	}
}
