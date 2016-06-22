import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 ID: vschwartz
 LANG: JAVA
 PROB: hamming
 */
public class hamming {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		ArrayList<Integer> list = new ArrayList<Integer>(N);

		list.add(0);
		int x = 0;
		for (int i = 1; i < N;) {
			x++;
			int j;
			for (j = 0; j < list.size(); j++) {
				if (ham(x, list.get(j)) < D) break;
			}
			if (j == list.size()) {
				list.add(x);
				i++;
			}
		}
		
		int i;
		for (i = 0; i < list.size() - 1; i++) {
			if (i % 10 == 9) out.println(list.get(i));
			else out.print(list.get(i) + " ");
		}
		out.println(list.get(i));

		f.close();
		out.close();
	}

	static int ham(int a, int b) {
		int total = 0;
		String s = Integer.toBinaryString(a ^ b);
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) == '1')
				total++;
		return total;
	}
}
