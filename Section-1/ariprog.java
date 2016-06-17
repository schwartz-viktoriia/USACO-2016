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
 PROB: ariprog
 */
public class ariprog {

	public static void main(String[] args) throws IOException {
//		long startT = System.nanoTime();
		BufferedReader f = new  BufferedReader(new FileReader("ariprog.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
		int len = Integer.parseInt(f.readLine());
		int upper = Integer.parseInt(f.readLine());
		
//		Set<Integer> bisquares = new HashSet<Integer>();
//		for (int q = 0; q <= upper; q++)
//			for (int p = 0; p <= upper; p++)
//				bisquares.add(q * q + p * p);
		
		boolean [] bisquares = new boolean [upper * upper * 2 + 1];
		for (int q = 0; q <= upper; q++)
			for (int p = q; p <= upper; p++)
				bisquares[q * q + p * p] = true;
		
		int biggest = upper * upper * 2;
		int count = 0;
		
		for (int step = 1; step <= biggest; step++) {
			int stop = biggest - step * (len - 1);
			for (int a = 0; a <= stop; a++) {
				if (bisquares[a]) {
					int j;
					int jStop = (len - 1) * step + a;
					for (j = a + step; j <= jStop; j += step) {
						if (!bisquares[j]) break;
					
					}
					if (j == jStop + step) {
						out.println(a + " " + step);
						count++;
					}
				}
			}
		}
		
//		for (int step = 1; step <= biggest; step++) {
//			int stop = biggest - step * (len - 1);
//			for (int a = 0; a <= stop; a++) {
//				if (bisquares.contains(a)) {
//					int j;
////					for (j = 1; j < len; j++) {
////						if (!bisquares.contains(j * step + a)) break;
//						
//					int jStop = (len - 1) * step + a;
//					for (j = a + step; j <= jStop; j += step) {
//						if (!bisquares.contains(j)) break;
//					
//					}
////					if (j == len) {
//					if (j == jStop + step) {
//						System.out.println(a + " " + step);
//						count++;
//					}
//				}
//			}
//		}
		
		if (count == 0) out.println("NONE");
//		System.out.println("time: " + ((System.nanoTime() - startT)) / 1000000000.0);
		f.close();
		out.close();
	}

}
