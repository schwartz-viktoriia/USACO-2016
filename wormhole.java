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
 PROB: wormhole
 */

public class wormhole {
	static int count = 0;
	static Point [] points;
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
		n = Integer.parseInt(f.readLine());
		points = new Point[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (points[j].x > points[i].x && points[j].y == points[i].y) {
					if (points[i].nextRight == null ||
							(points[j].x - points[i].x) < (points[i].nextRight.x - points[i].x))
						points[i].nextRight = points[j];
				}
			}
		}
//		for (int i = 0; i < n; i++)
//		System.out.println(i + ": " + points[i]);
		
		
		countThem();
		out.println(count);
		
		f.close();
		out.close();
	}
	
	static void countThem() {
		int i;
		for (i = 0; i < n; i++) {
			if (points[i].pair == null) break;
		}
		if (i == n) {
			if (isLoopy()) count++;
			return;
		}

		for (int j = i + 1; j < n; j++) {
			if (points[j].pair == null) {
				points[i].pair = points[j];
				points[j].pair = points[i];
				countThem();
				points[i].pair = null;
				points[j].pair = null;
			}
		}
		return;
	}
	
	static boolean isLoopy() {
		for (int i = 0; i < n; i++) {
			Point right = points[i];
			for (int j = 0; j < n; j++) {
				if (right != null)
					right = right.pair.nextRight;
			}
			if (right != null) return true;
		}
		return false;
	}
}

class Point {
	int x, y;
	Point pair;
	Point nextRight;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		if (nextRight == null)
			return "(" + x + ", " + y + ") -> null";
		else
			return "(" + x + ", " + y + ") -> (" + nextRight.x + ", " + nextRight.y + ")";
	}
}
