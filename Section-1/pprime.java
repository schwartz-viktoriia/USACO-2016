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
 PROB: pprime
 */

public class pprime {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int pal;
		int[] primes = {2, 3, 5, 7};
		if (a < 10) {
			for (int i: primes)
				if (i >= a && i <= b)
					out.println(i);
		}
		
		// 2 digit
		if (a < 100 && b > 9) {
			for (int d1 = 1; d1 <= 9; d1 += 2) {
				pal = 10 * d1 + d1;
				if (pal <= b && pal >= a) {
					if (isPrime(pal)) 
						out.println(pal);
				}
			}
		}
		// 3 digit
		if (a < 1000 && b > 99) {
			for (int d1 = 1; d1 <= 9; d1 += 2)
				for (int d2 = 0; d2 <= 9; d2++) {
						pal = 100 * d1 + 10 * d2 + d1;
						if (pal <= b && pal >= a) {
							if (isPrime(pal)) 
								out.println(pal);
						}
					}
		}
		// 4 digit
		if (a < 10000 && b > 999) {
			for (int d1 = 1; d1 <= 9; d1 += 2)
				for (int d2 = 0; d2 <= 9; d2++) {
						pal = 1000 * d1 + 100 * d2 + 10 * d2 + d1;
						if (pal <= b && pal >= a) {
							if (isPrime(pal)) 
								out.println(pal);
						}
					}
		}
		// 5 digit
		if (a < 100000 && b > 9999) {
			for (int d1 = 1; d1 <= 9; d1 += 2)
				for (int d2 = 0; d2 <= 9; d2++)
					for (int d3 = 0; d3 <= 9; d3++) {
						pal = 10000 * d1 + 1000 * d2 + 100 * d3 + 10 * d2 + d1;
						if (pal <= b && pal >= a) {
							if (isPrime(pal)) 
								out.println(pal);
						}
					}
		}
		// 6 digit
		if (a < 1000000 && b > 99999) {
			for (int d1 = 1; d1 <= 9; d1 += 2)
				for (int d2 = 0; d2 <= 9; d2++)
					for (int d3 = 0; d3 <= 9; d3++) {
						pal = 100000 * d1 + 10000 * d2 + 1000 * d3 + 100 * d3 + 10 * d2 + d1;
						if (pal <= b && pal >= a) {
							if (isPrime(pal)) 
								out.println(pal);
						}
					}
		}
		// 7 digit
		if (a < 10000000 && b > 999999) {
			for (int d1 = 1; d1 <= 9; d1 += 2)
				for (int d2 = 0; d2 <= 9; d2++)
					for (int d3 = 0; d3 <= 9; d3++) 
						for (int d4 = 0; d4 <= 9; d4++) {
							pal = 1000000 * d1 + 100000 * d2 + 10000 * d3 + 1000 * d4 + 100 * d3 + 10 * d2 + d1;
							if (pal <= b && pal >= a) {
								if (isPrime(pal)) 
									out.println(pal);
							}
						}
		}
		// 8 digit
		if (a < 100000000 && b > 9999999) {
			for (int d1 = 1; d1 <= 9; d1 += 2)
				for (int d2 = 0; d2 <= 9; d2++)
					for (int d3 = 0; d3 <= 9; d3++) 
						for (int d4 = 0; d4 <= 9; d4++) {
							pal = 10000000 * d1 + 1000000 * d2 + 100000 * d3 + 10000 * d4 + 1000 * d4 + 100 * d3 + 10 * d2 + d1;
							if (pal <= b && pal >= a) {
								if (isPrime(pal)) 
									out.println(pal);
							}
						}
		}
		f.close();
		out.close();
	}
	
	static boolean isPrime(int num) {
		int stop = (int)(Math.sqrt(num));
		for (int i = 3; i <= stop; i += 2)
			if (num % i == 0) return false;
		return true;
	}
	
}
