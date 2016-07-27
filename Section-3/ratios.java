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
 PROB: ratios
 */
public class ratios {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ratios.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int[] goal = new int[3];
		for (int i = 0; i < 3; i++) 
			goal[i] = Integer.parseInt(st.nextToken());
		
		int[][] inputRatio = new int[3][3];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(f.readLine());
			for (int j = 0; j < 3; j++) {
				inputRatio[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] units = new int[3];
		int best = Integer.MAX_VALUE;
		
		int[] temp = new int[3];
		int times = 0;
		for (int i = 0; i < 101; i++) {
			for (int m = 0; m < 3; m++)
				temp[m] = inputRatio[0][m] * i;
			for (int j = 0; j < 101; j++) {
				for (int m = 0; m < 3; m++)
					temp[m] += inputRatio[1][m] * j;
				for (int k = 0; k < 101; k++) {
					for (int m = 0; m < 3; m++)
						temp[m] += inputRatio[2][m] * k;
					
					int gcd = gcd(temp[0], temp[1], temp[2]);

					int sum = i + j + k;
					if (temp[0] == goal[0] && temp[1] == goal[1] && temp[2] == goal[2]) {
						if (sum < best) {
							best = i + j + k;
							units[0] = i;
							units[1] = j;
							units[2] = k;
							times = 1;
						}
					}
					
					if (gcd > 1 && temp[0] / gcd == goal[0] && 
							temp[1] / gcd == goal[1] && temp[2] / gcd == goal[2]) {

						if (sum < best) {
							best = sum;
							units[0] = i;
							units[1] = j;
							units[2] = k;
							times = gcd;
						}
					}
					for (int m = 0; m < 3; m++)
						temp[m] -= inputRatio[2][m] * k;
				}
				for (int m = 0; m < 3; m++)
					temp[m] -= inputRatio[1][m] * j;
			}
		}
		
		if (best == Integer.MAX_VALUE) out.println("NONE");
		else {
			for (int i: units) out.print(i + " ");
			out.println(times);
		}

		f.close();
		out.close();
	}

	static int gcd(int a, int b, int c) {
		return gcd(gcd(a, b), c);
	}
	
	static int gcd(int a, int b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}
}
