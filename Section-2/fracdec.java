import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 ID: vschwartz
 LANG: JAVA
 PROB: fracdec
 */
public class fracdec {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fracdec.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		StringBuilder res = new StringBuilder(N / D + "");
		res.append('.');
		StringBuilder result = new StringBuilder();
		String rep = "";
		if (N % D == 0)
			result.append('0');
		else {
			// record all remainders, when remainder repeats - solution found
			// set for a quick lookup, list - for finding index
			ArrayList<Integer> list = new ArrayList<Integer>();
			Set<Integer> set = new HashSet<Integer>();
			int rem = N;
			boolean found = false;
			while (!found) {
				rem %= D;
				if (set.contains(rem)) {
					found = true;
					rep = result.substring(list.indexOf(rem));
				} else {
					list.add(rem);
					set.add(rem);
					if (rem == 0) break;
					rem *= 10;
					result.append(rem / D);
				}
			}
		}
		result.delete(result.length() - rep.length(), result.length());
		res.append(result);
		if (rep.length() > 0) res.append('(');
		res.append(rep);
		if (rep.length() > 0) res.append(')');
		
		int i = 76;
		while (i < res.length()) {
			res.insert(i, "\n");
			i = i + 77;
		}
		
		out.println(res);
		f.close();
		out.close();
	}

}
