import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.sound.midi.Synthesizer;
/*
 ID: vschwartz
 LANG: JAVA
 PROB: frac1
 */
public class frac1 {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		int N = Integer.parseInt(f.readLine());
		ArrayList<Fraction> list = new ArrayList<Fraction>();
		list.add(new Fraction(0, 1));
		list.add(new Fraction(1, 1));
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				if (i == 1 || GCD(i, j) == 1) list.add(new Fraction(i, j));
			}
		}
		
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++)
			out.println(list.get(i));
		
		f.close();
		out.close();
	}

	static int GCD(int x, int y) {
		if (x % y == 0) return y;
		return GCD(y, x % y);
	}
}

class Fraction implements Comparable{
	int a, b;
	double value;
	
	Fraction(int a, int b) {
		this.a = a;
		this.b = b;
		value = (double) a / b;
	}
	
	@Override
	public String toString() {
		return a + "/" + b;
	}

	@Override
	public int compareTo(Object o) {
		if (o.equals(this)) return 0;
		return (int)(value * 100000) - (int)(((Fraction)o).value * 100000);
	}
	
	@Override
	public boolean equals(Object o) {
		return a == ((Fraction)o).a && b == ((Fraction)o).b;
 	}
}