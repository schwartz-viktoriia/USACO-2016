import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 ID: vschwartz
 LANG: JAVA
 PROB: comehome
 */
public class comehome {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("comehome.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
		int P = Integer.parseInt(f.readLine());
		String[] input = new String[P];
		int[][] temp = new int[60][60];
		final int INF = 1000000;
		for (int i = 0; i < 60; i++)
			for (int j = 0; j < 60; j++)
				temp[i][j] = INF;
		
		Map<Character, Integer> map = new HashMap<Character, Integer>();

		int index = 0;
		for (int i = 0; i < P; i++) {
			input[i] = f.readLine();
			char node1 = input[i].charAt(0);
			char node2 = input[i].charAt(2);
			int len = Integer.parseInt(input[i].substring(4));
			if (node1 == node2) len = 0;
			
			if (!map.containsKey(node1))
				map.put(node1, index++);
			if (!map.containsKey(node2)) 
				map.put(node2, index++);;
			temp[map.get(node1)][map.get(node2)] = Math.min(len, temp[map.get(node1)][map.get(node2)]);
			temp[map.get(node2)][map.get(node1)] = Math.min(len, temp[map.get(node2)][map.get(node1)]);;
		}
		
		// find the shortest paths from 'Z' to all pastures
		int[] dijkstra = new int[map.size() + 1];
		int indexZ = map.get('Z');
		for (int i = 0; i < dijkstra.length; i++)
			dijkstra[i] = INF;
		
		Set<Integer> set = new HashSet<Integer>();
		int d = 0, from = indexZ;
		set.add(indexZ);
		dijkstra[indexZ] = 0;
		
		boolean flag = true;
		for (int i = 0; i < dijkstra.length - 1 && flag; i++) {
			for (int j = 0; j < dijkstra.length - 1; j++) {	
				dijkstra[j] = (int) Math.min(dijkstra[j], temp[from][j] + d);
			}
			int min = dijkstra.length - 1;
			for (int j = 0; j < dijkstra.length - 1; j++) {
				if (set.size() == dijkstra.length - 1) {
					flag = false;
					break;
				}
					
				if (!set.contains(j) && dijkstra[min] > dijkstra[j]) {
					min = j;
					d = dijkstra[j];
				}
			}
			from = min;
			set.add(from);
		}		
		
		boolean[] cow = new boolean[map.size()];
		for (char ch: map.keySet()) {
			if (Character.isUpperCase(ch) && ch != 'Z')
				cow[map.get(ch)] = true;
		}

		int resultIndex = dijkstra.length - 1;
		for (int i = 1; i < dijkstra.length - 1; i++) {
			if (cow[i] && dijkstra[i] < dijkstra[resultIndex])
				resultIndex = i;
		}
		char resultChar = ' ';
		for (char ch: map.keySet()) {
			if (map.get(ch) == resultIndex)
				resultChar = ch;
		}
		out.println(resultChar + " " + dijkstra[resultIndex]);

		f.close();
		out.close();
	}
}
