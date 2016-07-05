import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 ID: vschwartz
 LANG: JAVA
 PROB: prefix
 */
public class prefix {
	static ArrayList<String> primitives = new ArrayList<String>();
	static String sequence = "";
	static int[] memoi;
	static int currentPos = 0;
	static int maxCount = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		String str = f.readLine();
		StringTokenizer st = new StringTokenizer(str);

		while (!str.equals(".")) {
			while (st.hasMoreTokens())
				primitives.add(st.nextToken());
			str = f.readLine();
			st = new StringTokenizer(str);
		}

		int maxPrim = 0;
		char[][]prims = new char[primitives.size()][];
		for (int i = 0; i < prims.length; i++) {
			prims[i] = primitives.get(i).toCharArray();
			if (primitives.get(i).length() > maxPrim) maxPrim = primitives.get(i).length();
		}
		
		while (f.ready()) {
			st = new StringTokenizer(f.readLine());
			sequence = sequence.concat(st.nextToken());
		}
		memoi = new int[sequence.length()];
		
		
		memoi[0] = 1;
		int result = 0, bestResult = 0;
		int gap = 0;
		for (int i = 0; i < sequence.length(); i++) {
			if (gap > maxPrim) break; // no reason to continue
			if (memoi[i] == 1) { // mark as 1 - "can get there"
				result = i;
				gap = 0;
				for (char[] ch: prims) {
					boolean flag = ((i + ch.length) <= sequence.length());//true;
					for (int j = 0; j < ch.length && (j + i) < sequence.length(); j++) 
						if (sequence.charAt(i + j) != ch[j]) flag = false;
					
					if (flag) {
						if ((i + ch.length) < sequence.length()) 
							memoi[i + ch.length] = 1;
						result = Math.max(result, i + ch.length);
					}
					
				}
				bestResult = Math.max(bestResult, result);
			} else gap++;
		}
		out.println(bestResult);

		f.close();
		out.close();
	}
}
