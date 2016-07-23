import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 ID: vschwartz
 LANG: JAVA
 PROB: stamps
 */
public class stamps {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("stamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] stamp = new int[N];
		int i = 0;
		while (f.ready()) {
			st = new StringTokenizer(f.readLine());
			while (st.hasMoreTokens()) {
				stamp[i++] = Integer.parseInt(st.nextToken());
			}
		}
		Arrays.sort(stamp);

		int[] dp = new int[K * stamp[stamp.length - 1] + 1];
		for (i = 1; i < dp.length; i++)
			dp[i] = Integer.MAX_VALUE;
		for (i = stamp[0]; i < (stamp[0] + K); i++)
			dp[i] = i * stamp[0];
		
		for (i = 1; i < N; i++) {
			for (int j = stamp[i]; j < dp.length; j++) {
				dp[j] = Math.min(dp[j], dp[j - stamp[i]] + 1);
			}
		}
		
		for (i = 1; i < dp.length; i++)
			if (dp[i] > K) break;

		out.println(i - 1);
		f.close();
		out.close();
	}

}
