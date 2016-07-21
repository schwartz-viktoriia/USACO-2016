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
 PROB: contact
 */
public class contact {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("contact.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] hash = new int[B - A + 1][];
		for (int i = A; i <= B; i++)
			hash[i - A] = new int[(int)(Math.pow(2, i))];
		
		StringBuilder seq = new StringBuilder();
		while (f.ready())
			seq.append(f.readLine());
		// treat substrings as numbers
		for (int i = 0; i < seq.length(); i++) {
			for (int j = A; j <= B; j++) {
				if (i <= seq.length() - j) {
					int num = Integer.parseInt(seq.substring(i, i + j), 2);
					hash[j - A][num]++;
				}
			}
		}
		
		StringBuilder[] output = new StringBuilder[2 * N];
		int count = 0;
		while (count < 2 * N) {
			output[count] = new StringBuilder();
			output[count + 1] = new StringBuilder();
			int max = hash[0][0];
			for (int i = 0; i < hash.length; i++) 
				for (int j = 0; j < hash[i].length; j++)
					if (hash[i][j] > max) max = hash[i][j];
			
			if (max == 0) break;
			output[count].append(max);
			output[count].append('\n');
			
			int split = 80;
			for (int i = 0; i < hash.length; i++) {
				for (int j = 0; j < hash[i].length; j++) {
					if (hash[i][j] == max) {
						hash[i][j] = 0;
						String s = "000000000000";
						s += Integer.toBinaryString(j);
						s = s.substring(s.length() - (A + i));
						if (output[count + 1].length() + s.length() >= split)
							split = output[count + 1].length() + 80;
							output[count + 1].setCharAt(output[count + 1].length() - 1, '\n');
						}
						output[count + 1].append(s + " ");
					}
				}
			}
			output[count + 1].deleteCharAt(output[count + 1].length() - 1);
			output[count + 1].append('\n');
			count += 2;
		}
		
		for (StringBuilder s: output) {
			if (s != null)
				out.print(s);
		}

		f.close();
		out.close();
	}
}
