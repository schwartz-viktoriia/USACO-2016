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
 PROB: crypt1
 */

public class crypt1 {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
		int n = Integer.parseInt(f.readLine());
		int[] choices = new int[n];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 0; i < n; i++)
			choices[i] = Integer.parseInt(st.nextToken());
		int [] num = new int [5];
		int [] p1 = new int[3];
		int [] p2 = new int[3];
		int [] total = new int [4];
		int counter = 0;
		for (int a = 0; a < n; a++) {
			num[0] = choices[a];
			for (int b = 0; b < n; b++) {
				num[1] = choices[b];
				for (int c = 0; c < n; c++) {
					num[2] = choices[c];
					for (int d = 0; d < n; d++) {
						num[3] = choices[d];
						for (int e = 0; e < n; e++) {
							num[4] = choices[e];
							int prod1 = num[4] * (100 * num[0] + 10 * num[1] + num[2]);
							if (prod1 > 999) continue;
							
							int prod2 = num[3] * (100 * num[0] + 10 * num[1] + num[2]);
							if (prod2 > 999) continue;
							
							int t = prod1 + 10 * prod2;
							if (goodToGo(numToArr(prod1, p1), choices) && 
									goodToGo(numToArr(prod2, p2), choices) &&
										goodToGo(numToArr(t, total), choices))
								counter++;
						}
					}
				}
			}
		}
		out.println(counter);
		
		f.close();
		out.close();
	}

	static boolean goodToGo(int [] a, int [] choices) {
		boolean flag = true;
		for (int i = 0; i < a.length; i++) {
			boolean temp = false;
			for (int j = 0; j < choices.length; j++) {
				if (a[i] == choices[j]) temp = true;
			}
			flag = flag && temp;
		}
		return flag;
	}
	
	static int[] numToArr(int n, int[] a) {
		for (int i = a.length - 1; i >= 0; i--) {
			a[i] = n % 10;
			n /= 10;
		}
		return a;
	}
}
