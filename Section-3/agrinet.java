import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 ID: vschwartz
 LANG: JAVA
 PROB: agrinet
 */

public class agrinet {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("agrinet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
		int N = Integer.parseInt(f.readLine());
		int[][] input = new int[N][N];
		
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			set.add(i);
			int j = 0;
			
			for (j = 0; j < N && st.hasMoreTokens(); j++)
				input[i][j] = Integer.parseInt(st.nextToken());
			if (j < N) {
				while (j < N) {
					st = new StringTokenizer(f.readLine());
					for (; j < N && st.hasMoreTokens(); j++) 
						input[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		int result = 0;
		Set<Integer> toVisit = new HashSet<Integer>();
		toVisit.add(0);
		while (!set.isEmpty()) {
			int minIndex = -1;
			int min = Integer.MAX_VALUE;
			for (int v: toVisit) {
				set.remove(v);
				for (int j = 0; j < N; j++) {
					if (set.contains(j)) {
						if (min > input[v][j]) {
							min = input[v][j];
							minIndex = j;
						}
					}
				}
			}

			result += min;
			// mark both as visited
			set.remove(minIndex);
			toVisit.add(minIndex);
		}
		
		System.out.println(result);
		f.close();
		out.close();
	}

}
