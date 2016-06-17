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
 PROB: milk3
 */
public class milk3 {
	static int A, B, C;
	static Set<Trio> setA = new HashSet<Trio>();
	static Set<Trio> setB = new HashSet<Trio>();
	static Set<Trio> setC = new HashSet<Trio>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		distributeC(0, 0, C);

		Set<Integer> subSet = new HashSet<Integer>();
		for (Trio trio: setA)
			if (trio.a == 0)
				subSet.add(trio.c);
		for (Trio trio: setB)
			if (trio.a == 0)
				subSet.add(trio.c);
		for (Trio trio: setC)
			if (trio.a == 0)
				subSet.add(trio.c);
		List <Integer> ans = new ArrayList<Integer>();
		ans.addAll(subSet);
		Collections.sort(ans);

		int i;
		for (i = 0; i < ans.size() - 1; i++)
			out.print(ans.get(i) + " ");
		out.println(ans.get(i));
		f.close();
		out.close();
	}

	public static void distributeA(int ax, int bx, int cx) {
		Trio trio = new Trio(ax, bx, cx);
		if (setA.contains(trio)) return;
		setA.add(trio);
		
		// pour into B
		int b = (B - bx > ax) ? bx + ax : B;
		int a = (B - bx > ax) ? 0 : ax - B + bx;
		distributeB(a, b, cx);
		// then A
		distributeC(a, b, cx);
		
		// pour into C
		int c = (C - cx > ax) ? ax + cx : C;
		a = (C - cx > ax) ? 0 : ax - C + cx;
		distributeC(a, bx, c);
		// then B
		distributeB(a, bx, c);
	}
	
	public static void distributeB(int ax, int bx, int cx) {
		Trio trio = new Trio(ax, bx, cx);
		if (setB.contains(trio)) return;
		setB.add(trio);
		
		// pour into A 
		int a = (A - ax > bx) ? ax + bx : A;
		int b = (A - ax > bx) ? 0 : bx - A + ax;
		distributeA(a, b, cx);
		//	then C
		distributeC(a, b, cx);
		
		// pour into C
		b = (B - bx > cx) ? bx + cx : B;
		int c = (B - bx > cx) ? 0 : cx - B + bx;
		distributeC(ax, b, c);
		// then A
		distributeA(ax, b, c);
	}
	
	public static void distributeC(int ax, int bx, int cx) {
		Trio trio = new Trio(ax, bx, cx);
		if (setC.contains(trio)) return;
		setC.add(trio);
		
		// pour into B
		int b = (B - bx > cx) ? bx + cx : B;
		int c = (B - bx > cx) ? 0 : cx - B + bx;
		distributeB(ax, b, c);
		//	then A
		distributeA(ax, b, c);
		
		// pour into A
		int a = (A - ax > cx) ? ax + cx : A;
		c = (A - ax > cx) ? 0 : cx - A + ax;
		distributeA(a, bx, c);
		//	then B
		distributeB(a, bx, c);
	}
}

class Trio {
	int a, b, c;
	
	Trio(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	@Override
	public boolean equals(Object other) {
		return a == ((Trio)other).a && b == ((Trio)other).b && c == ((Trio)other).c;
	}
	
	@Override
	public int hashCode() {
		return 100 * a + 10 * b + c;
	}
}
