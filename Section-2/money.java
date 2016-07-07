import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
 ID: vschwartz
 LANG: JAVA
 PROB: money
 */
public class money {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("money.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int V = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] coins = new int[V];
		
		int i = 0;
		while (f.ready()) {
			st = new StringTokenizer(f.readLine());
			while (st.hasMoreTokens())
				coins[i++] = Integer.parseInt(st.nextToken());
		}
		
		long[] DP = new long[N + 1];
		DP[0] = 1;
		for (i = 0; i < V; i++) {
			for (int j = coins[i]; j <= N; j++) {
				DP[j] += DP[j - coins[i]]; 
			}
		}
		
		out.println(DP[N]);
		f.close();
		out.close();
	}
}
