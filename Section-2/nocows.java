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
 PROB: nocows
 */
public class nocows {
	static int N, K;
	static int[][] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); 
		memo = new int[N + 1][K + 1];
		for (int i = 0; i <= N; i++)
			for (int j = 0; j <= K; j++)
				memo[i][j] = -1;

		if (K > (N + 1) / 2) out.println(0);
		else out.println((solve(N, K) - solve(N, K - 1) + 9901) % 9901);
		f.close();
		out.close();
	}

	static int solve(int n, int k) {
	    if(n==1) return 1;
	    if(k==1) return 0;
	    if(memo[n][k] != -1) return memo[n][k];
	    
	    memo[n][k] = 0;
	    for(int i = 1; i < n - 1; i++) 
	    	memo[n][k] = (memo[n][k] + solve(i, k - 1) * solve(n - 1 - i, k - 1)) % 9901;
	    
	    return memo[n][k];
	}
}
