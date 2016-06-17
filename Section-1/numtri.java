import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
/*
 ID: vschwartz
 LANG: JAVA
 PROB: numtri
 */
public class numtri {
	static int [][] tri;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
		int R = Integer.parseInt(f.readLine());
		tri = new int[R][];
		for (int i = 0; i < R; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			tri[i] = new int[i + 1];
			for (int j = 0; j < i + 1; j++) {
				tri[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = tri.length - 2; i >= 0; i--) {
			for (int j = 0; j < tri[i].length; j++) {
				tri[i][j] = Math.max(tri[i + 1][j], tri[i + 1][j + 1]) + tri[i][j];
			}
		}
		
		out.println(tri[0][0]);
		
		f.close();
		out.close();
	}
	
	// too long
//	static int maxSum(int row, int col) {
//		if (row == tri.length - 1) return tri[row][col];
//		return Math.max(maxSum(row + 1, col), maxSum(row + 1, col + 1)) + tri[row][col];
//	}
	

}
