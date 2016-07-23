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
 PROB: humble
 */
public class humble {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("humble.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		
		int[] input = new int[K];
		for (int i = 0; i < K; i++)
			input[i] = Integer.parseInt(st.nextToken());

		int[] index = new int[K];
		int[] hum = new int[N + 1];
		hum[0] = 1;
		
		for (int i = 1; i < hum.length; i++) {
			int last = hum[i - 1];
			int min = Integer.MAX_VALUE;
			
			for (int j = 0; j < K; j++) {
				for (int k = index[j]; k < i; k++) {
					int prod = input[j] * hum[k];
	
					if (prod > last) {
						min = Math.min(min, prod);
						break;
					} else index[j] = k;
				}
			}
			hum[i] = min;
		}

		out.println(hum[N]);		
		f.close();
		out.close();
	}
}
