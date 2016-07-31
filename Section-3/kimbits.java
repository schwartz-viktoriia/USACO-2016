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
 PROB: kimbits
 */
public class kimbits {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("kimbits.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		long I = (int)(Long.parseLong(st.nextToken()) - 1);
		
		long[][] dp = new long[N + 1][L + 1];
		for (int i = 0; i < L; i++) 
			dp[0][i] = 1;
	
		for (int i = 1; i <= N; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= L; j++) {
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
			}
		}
		
		StringBuilder s = new StringBuilder();

		for (int i = N; i > 0; i--) {
			if (I > dp[i - 1][L]) {
				s.append('1');
				I -= dp[i - 1][L];
				L--;
			} else {
				s.append('0');
			}
		}
		out.println(s.toString());

		f.close();
		out.close();
	}
}
