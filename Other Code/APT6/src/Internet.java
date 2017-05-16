import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Internet {
	Map<String, Set<String>> myGraph;
	int count = 0;

	public Set<String> bfs(String start, String avoid) {
		Set<String> visited = new TreeSet<String>();
		Queue<String> qu = new LinkedList<String>();
		visited.add(start);
		qu.add(start);

		while (qu.size() > 0) {
			String v = qu.remove();
			for (String adj : myGraph.get(v)) {
				if (!adj.equals(avoid) && !visited.contains(adj)) {
					visited.add(adj);
					qu.add(adj);
				}
			}
		}
		return visited;
	}

	public int articulationPoints(String[] routers) {
		// create graph adjacency list
		myGraph = new TreeMap<String, Set<String>>();
		for (int k = 0; k < routers.length; k++) {
			String vertex = k + "";
			Set<String> set = new TreeSet<String>();
			myGraph.put(vertex, set);
			Scanner neigh = new Scanner(routers[k]);
			while (neigh.hasNext())
				set.add(neigh.next());
			neigh.close();
		}
		// Print out adjacency lists
		for (Map.Entry<String, Set<String>> k : myGraph.entrySet())
			System.out.println(k.getKey() + ": " + k.getValue());
		for (int i = 0; i < routers.length; i++) {
			Set<String> current;
			if (i == 0) {
				current = bfs("1", "0");
			} else {
				current = bfs(Integer.toString(i-1), Integer.toString(i));
			}
			if(current.size() == routers.length-1){
				count++;
			}
		}
		return routers.length-count;
	}

	public static void main(String[] args) {
		String[][] input = { { "2", "2 3", "0 1 3 4", "1 2", "2 5 6", "4 6", "4 5" },
				{ "3 10", "8 4", "7 10", "0 9", "6 1", "9", "4 11", "11 2", "1", "3 5", "0 2", "7 6" },
				{ "1 9", "0 2", "1 3", "2 4", "3 5", "4 6", "5 7", "6 8", "7 9", "8 0" },
				{ "1 7 12 13 15", "0 2 6 4 7", "3 5 6 1", "4 5 2", "3 1", "3 2", "2 1", "1 0 8", "7 9 10 11", "10 11 8",
						"9 8", "9 8", "0 13", "0 12 14 15", "13", "13 0" } };
		Internet run = new Internet();
		for (String[] routers : input) {
			int artics = run.articulationPoints(routers);
			System.out.printf("%s -> %d\n", Arrays.toString(routers), artics);
		}

	}

}