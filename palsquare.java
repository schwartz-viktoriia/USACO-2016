import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
 ID: vschwartz
 LANG: JAVA
 PROB: palsquare
 */

public class palsquare {
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
		int base = Integer.parseInt(f.readLine());
		
		for (int i = 1; i <= 300; i++) {
			String num = Integer.toString(i * i, base);
			if (isPalindromic(num)) {
				out.println(Integer.toString(i, base).toUpperCase() + " " + num.toUpperCase());
			}
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
