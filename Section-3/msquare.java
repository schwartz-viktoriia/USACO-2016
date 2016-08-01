import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 ID: vschwartz
 LANG: JAVA
 PROB: msquare
 */
public class msquare {
	static int[] start = {1, 2, 3, 4, 8, 7, 6, 5}; 
	static int[] target = new int[8];
	static StringBuilder bestS = new StringBuilder();
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("msquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 0; i < 4; i++)
			target[i] = Integer.parseInt(st.nextToken());
		for (int i = 7; i > 3; i--)
			target[i] = Integer.parseInt(st.nextToken());
		
		bfs();
		
		int breakLine = 60;
		while (bestS.length() > breakLine) {
			bestS.insert(breakLine, '\n');
			breakLine += 61;
		}
		
		out.println(count);
		out.println(bestS);

		f.close();
		out.close();
	}
	
	static void bfs() {
		Queue<Square> qFrom = new LinkedList<Square>();
		Queue<Square> qTo = new LinkedList<Square>();
		Queue<StringBuilder> strFrom = new LinkedList<StringBuilder>();
		Queue<StringBuilder> strTo = new LinkedList<StringBuilder>();
		
		qFrom.add(new Square(start));
		strFrom.add(new StringBuilder());
		Set<Integer> set = new HashSet<Integer>();
		
		boolean found = false;
		
		while (!found) {
			if (same(start, target)) break;
			count++;
			while (!qFrom.isEmpty()) {
				Square s = qFrom.poll();
				int[] a = s.sq;
				StringBuilder sb = strFrom.poll();
				
				A(a);
				sb.append('A');
				if (same(a, target)) {
					bestS = sb;
					found = true;
					break;
				}
				if (set.add(toInt(a))) {
					qTo.add(new Square(a));
					strTo.add(new StringBuilder(sb));
				}
				A(a);
				sb.deleteCharAt(sb.length() - 1);
				
				B(a);
				sb.append('B');
				if (same(a, target)) {
					bestS = sb;
					found = true;
					break;
				}
				if (set.add(toInt(a))) {
					qTo.add(new Square(a));
					strTo.add(new StringBuilder(sb));
				}
				unB(a);
				sb.deleteCharAt(sb.length() - 1);

				C(a);
				sb.append('C');
				if (same(a, target)) {
					bestS = sb;
					found = true;
					break;
				}
				if (set.add(toInt(a))) {
					qTo.add(new Square(a));
					strTo.add(new StringBuilder(sb));
				}
				unC(a);
				sb.deleteCharAt(sb.length() - 1);
			}
				
			qFrom.addAll(qTo);
			qTo.clear();
			strFrom.addAll(strTo);
			strTo.clear();
		}
	}
	
	static int toInt(int[] a) {
		int sum = 0;
		for (int i = 0; i < a.length; i++)
			sum += (int)Math.pow(10, i) * a[i];
		return sum;
	}
	
	static boolean same(int[] a, int[] b) {
		for (int i = 0; i < 8; i++)
			if (a[i] != b[i]) return false;
		return true;
	}

	static void A(int[] a) {
		for (int i = 0; i < 4; i++) {
			int temp = a[i];
			a[i] = a[i + 4];
			a[i + 4] = temp;
		}
	}
	
	static void B(int[] a) {
		int x = a[3];
		int y = a[7];
		for (int i = 3; i > 0; i--) {
			a[i] = a[i - 1];
			a[i + 4] = a[i + 3];
		}
		a[0] = x;
		a[4] = y;
	}
	
	static void unB(int[] a) {
		int x = a[0];
		int y = a[4];
		for (int i = 0; i < 3; i++) {
			a[i] = a[i + 1];
			a[i + 4] = a[i + 5];
		}
		a[3] = x;
		a[7] = y;
	}
	
	static void C(int[] a) {
		int x = a[1];
		a[1] = a[5];
		a[5] = a[6];
		a[6] = a[2];
		a[2] = x;
	}
	
	static void unC(int[] a) {
		int x = a[1];
		a[1] = a[2];
		a[2] = a[6];
		a[6] = a[5];
		a[5] = x;
	}
}

class Square {
	int[] sq = new int[8];
	
	Square(int[] arr) {
		for (int i = 0; i < sq.length; i++)
			sq[i] = arr[i];
	}
	
	@Override
	public boolean equals(Object o) {
		Square other = (Square)o;
		for (int i = 0; i < sq.length; i++)
			if (sq[i] != other.sq[i]) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		int sum = 0;
		for (int i = 0; i < sq.length; i++)
			sum += (int)Math.pow(10, i) * sq[i];
		return sum;
	}
}
