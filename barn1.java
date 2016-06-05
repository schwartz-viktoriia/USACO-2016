import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
/*
 ID: vschwartz
 LANG: JAVA
 PROB: barn1
 */

public class barn1 {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int m = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int cows = Integer.parseInt(st.nextToken());
		int[] stalls = new int[s + 1];  // stalls: 1-cow, 0-no cow
		int[] gaps = new int[s + 1];  // lengths of cows-1 gaps between cows
		
		for (int i = 0; i < cows; i++) {
			stalls[Integer.parseInt(f.readLine())] = 1;
		}
		
		// fill in the stalls, count "clumps" of cows
		
		int clumps = 0;
		for (int i = 0; i < stalls.length - 1; i++) {
			if (stalls[i] == 1 && stalls[i + 1] == 0) clumps++;
			int gap = 0;
			int gapIndex = i;
			while (i < stalls.length && stalls[i] == 0) {
				i++;
				gap++;
			}
			if (i == stalls.length) gap = 0;
			if (gap > 0) {
				gaps[gapIndex] = gap;
				i--;
			}
		}
		if (stalls[s] == 1) clumps++;
		
		// fill min gaps until clumps = m

		while (clumps > m) {
			int min = 1;
			while(gaps[min] == 0 && min < gaps.length - 1) min++;
			for (int i = 1; i < gaps.length; i++)
				if (gaps[min] > gaps[i] && gaps[i] != 0) {
					min = i;
				}
			for (int i = 0, j = min; i < gaps[min]; i++, j++) stalls[j] = 1;
			gaps[min] = 0;
			clumps--;
		}
		
		int stallsCovered = 0;
		for (int i = 0; i < stalls.length; i++) {
			stallsCovered += stalls[i];
		}
		
		out.println(stallsCovered);
		
		f.close();
		out.close();
	}

}
