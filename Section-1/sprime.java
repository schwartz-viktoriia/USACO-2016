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
 PROB: sprime
 */


public class sprime {
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		int N = Integer.parseInt(f.readLine());
		int top = (int)(Math.pow(10, N));
		int [] primes = {2, 3, 5, 7};
		for (int i: primes)
			makePrimes(N - 1, i);
		
		f.close();
		out.close();
	}
	
	static boolean isPrime(int num) {	
		int stop = (int)(Math.sqrt(num));
		for (int i = 3; i <= stop; i += 2)
			if (num % i == 0) return false;
		return true;
	}
	
	static void makePrimes(int n, int num) {
		if (n == 0) {
			if (isPrime(num)) out.println(num);
		} else {
			if (isPrime(num))
				for (int k = 1; k < 10; k += 2) {
					makePrimes(n - 1, num * 10 + k);
				}
		}
	}
}


