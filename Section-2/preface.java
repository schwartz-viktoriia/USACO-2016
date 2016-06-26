import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 ID: vschwartz
 LANG: JAVA
 PROB: preface
 */
public class preface {
	// result storage: [0] - I's, [1] = V's, ..., [6] - M's
	static int[] result = new int[7];
	
	static int[] pattern1 = {0, 1, 2, 3, 1, 0, 1, 2, 3, 1, 0};
	static int[] pattern5 = {0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0};
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("preface.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out"))); 
		int N = Integer.parseInt(f.readLine());

		int tens = N / 10;
		// I's
		result[0] = 14 * tens;
		for (int i = 1; i <= (N % 10); i++) 
			result[0] += pattern1[i];
		
		// V's
		result[1] = 5 * tens;
		for (int i = 1; i <= (N % 10); i++)
			result[1] += pattern5[i];
		
		// X's
		int hundreds = N / 100;
		result[2] = 150 * hundreds;
		for (int i = 1; i <= (N % 100); i++) {
			result[2] += pattern1[i / 10];
			if (i % 10 == 9) result[2]++;
		}
		
		// L's
		result[3] = 50 * hundreds;
		for (int i = 0; i <= (N % 100); i++)
			result[3] += pattern5[i / 10];

		// C's
		int thousands = N / 1000;
		result[4] = 1500 * thousands;
		for (int i = 0; i <= (N % 1000); i++) {
			result[4] += pattern1[i / 100];
			if (i / 10 % 10 == 9) result[4]++;
		}
		
		// D's
		result[5] = 500 * thousands;
		for (int i = 0; i <= (N % 1000); i++)
			result[5] += pattern5[i / 100];
		
		// M's
		int tenThousands = N / 10000;
		result[6] = 14000 * tenThousands;
		for (int i = 0; i <= (N % 10000); i++) {
			result[6] += pattern1[(i + 100) / 1000];
		}

		printResult();
		f.close();
		out.close();
	}

	static void printResult() {
		for (int i = 0; i < result.length; i++) {
			if (result[i] == 0) break;
			char r = ' ';
			switch (i) {
			case 0: r = 'I'; break;
			case 1: r = 'V'; break;
			case 2: r = 'X'; break;
			case 3: r = 'L'; break;
			case 4: r = 'C'; break;
			case 5: r = 'D'; break;
			case 6: r = 'M'; break;
			}
			out.println(r + " " + result[i]);
		}
	}
}
