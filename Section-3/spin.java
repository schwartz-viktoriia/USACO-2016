import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 ID: vschwartz
 LANG: JAVA
 PROB: spin
 */
public class spin {
	static int[] speedArr = new int[5];

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("spin.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
		int[][] wedge = new int[5][];
		Wheel[] wheel = new Wheel[5];
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int sp = Integer.parseInt(st.nextToken());
			wedge[i] = new int[2 * Integer.parseInt(st.nextToken())];
			for (int j = 0; j < wedge[i].length; j++) 
				wedge[i][j] = Integer.parseInt(st.nextToken());
			wheel[i] = new Wheel(i, sp, wedge[i]);
		}
		
		Set<Pos> set = new HashSet<Pos>();
		boolean solved = false;
		boolean none = false;
		int count = 0;
		set.add(new Pos(speedArr));
		while (!solved) {
			for (int j = 0; j < 360 && !solved; j++) {
				solved = true;
				for (int i = 0; i < 5; i++) {
					if (!wheel[i].w[j]) solved = false;
				}
			}
			for (int i = 0; i < 5; i++) {
				wheel[i].spin();
			}
			
			if (!set.add(new Pos(speedArr)) && !solved) {
				none = true;
				break;
			}
			count++;
		}
		
		if (none) out.println("none");
		else out.println(count - 1);
		
		f.close();
		out.close();
	}
}

class Wheel {
	int index;
	int speed;
	boolean[] w = new boolean[360];
	
	Wheel(int index, int speed, int[] wdg) {
		this.index = index;
		this.speed = speed;
		for (int i = 0; i < wdg.length - 1; i += 2) {
			for (int j = wdg[i], k = 0; k <= wdg[i + 1]; k++) {
				w[j] = true;
				j = (j + 1) % 360;
			}
		}
	}
	
	void spin() {
		boolean[] temp = new boolean[360];
		for (int i = 0; i < w.length; i++) {
			temp[(i + speed) % 360] = w[i];
		}
		w = temp;
		spin.speedArr[index] = (spin.speedArr[index] + speed) % 360;
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < w.length; i++) {
			s += w[i] ? "W" : "_";
			if (i != 0 && i % 90 == 0) s += "\t";
		}
		return s;
	}
}

class Pos {
	int[] arr = new int[5];
	
	Pos (int[] a) {
		arr = a.clone();
	}

	@Override
	public boolean equals(Object o) {
		Pos p = (Pos)o;
		for (int i = 0; i < 5; i++)
			if (arr[i] != p.arr[i]) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		int sum = 0;
		for (int i: arr)
			sum += i;
		return sum;
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < 5; i++)
			s += arr[i] + " ";
		return s;
	}
}
