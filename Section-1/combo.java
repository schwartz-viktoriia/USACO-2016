import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
/*
 ID: vschwartz
 LANG: JAVA
 PROB: combo
 */
public class combo {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("combo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
		int n = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		int[] farmer = new int[3];
		for (int i = 0; i < 3; i++)
			farmer[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		
		int[] master = new int[3];
		for (int i = 0; i < 3; i++)
			master[i] = Integer.parseInt(st.nextToken());
		
		out.println(countCombos(farmer, master, n));
		
		f.close();
		out.close();
	}
	
	static int countCombos(int [] a, int [] b, int n) {
		if (n < 6) return (int)(Math.pow(n, 3));
		
		// otherwise it's 250 - overlapping possibilities
		int[] overlap = new int[3];
		for (int i = 0; i < 3; i++) {
			int diff = Math.abs(a[i] - b[i]);
			if (diff < 5) overlap[i] = 5 - diff;			
			if (diff > (n - 6)) overlap[i] += 5 - (n - diff);
		}
//		for (int i = 0; i < 3; i++) System.out.println(overlap[i] + " ");
		
		int total = 1;
		for (int i = 0; i < 3; i++) {
			if (overlap[i] == 0) return 250;
			total *= overlap[i];
		}
		return 250 - total;
	}

}
