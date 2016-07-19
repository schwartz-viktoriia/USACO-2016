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
 PROB: inflate
 */

public class inflate {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("inflate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] input = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int points = Integer.parseInt(st.nextToken());
			int min = Integer.parseInt(st.nextToken());
			input[i][0] = points;
			input[i][1] = min;
		}
		// Knapsak		
		int[] sack = new int[M + 1];
		for (int j = 0; j < M + 1; j++) 
			sack[j] = j / input[0][1] * input[0][0];
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < M + 1; j++) {
				if (input[i][1] <= j)
					sack[j] = Math.max(sack[j], sack[j - input[i][1]] + input[i][0]);
			}
		}
		
		out.println(sack[M]);
		f.close();
		out.close();
	}
}
