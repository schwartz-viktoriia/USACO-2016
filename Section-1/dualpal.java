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
 PROB: dualpal
 */

public class dualpal {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		
		int i = 0;
		int number = s + 1;
		while (i < n) {
			int countPals = 0, countTotal = 2;
			while (countPals < 2 && countTotal < 11) {
				if (isPalindromic(Integer.toString(number, countTotal))) {
					countPals++;
				}
				countTotal++;
			}
			if (countPals >= 2) {
				out.println(number);
				i++;
			}
			number++;
		}
		
		f.close();
		out.close();
	}
	
	static boolean isPalindromic(String s) {
		for (int i = 0; i < s.length() / 2; i++)
			if (s.charAt(i) != s.charAt(s.length() - 1 - i)) return false;
		return true;
	}

}
