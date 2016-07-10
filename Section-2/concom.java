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
 PROB: concom
 */
public class concom {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("concom.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		int n = Integer.parseInt(f.readLine());
		
		int[][] input = new int[n][3];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			for (int j = 0; j < 3; j++)
				input[i][j] = Integer.parseInt(st.nextToken());
		}

		int[][] DP = new int[101][101];
		int[][] helper = new int[101][101];
		
		// Fill out who owns who directly
		for (int A = 0; A < input.length; A++) {
			DP[input[A][0]][input[A][1]] += input[A][2];
		}
		
		// travel by row, looking up who controls a company, and transfer all controlled ones
		// helper prevents from adding controlled company more than once
		for (int x = 0; x <= 100; x++) {
			for (int j = 1; j < DP.length; j++) {
				for (int i = 1; i < DP.length; i++) {
					if (DP[i][j] > 50) {
						for (int k = 1; k < DP.length; k++) {
							if (DP[j][k] > 50)
								DP[i][k] = DP[j][k];
							else {
								if (helper[j][k] == 0) {
									DP[i][k] += DP[j][k];
									helper[j][k] = 1;
								}
							}
						}
					}
				}
			}
		}
		
		for (int i = 1; i < DP.length; i++) 
			for (int j = 1; j < DP.length; j++)
				if (i != j && DP[i][j] > 50)
					out.println(i + " " + j);
		
		f.close();
		out.close();
	}
}
