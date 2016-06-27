import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/*
 ID: vschwartz
 LANG: JAVA
 PROB: runround
 */
public class runround {
	static StringBuilder sb;
	static int startIndex = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("runround.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out"))); 
		String M = f.readLine();
		sb = new StringBuilder(M);		
		int num = Integer.parseInt(sb.toString());
		
		boolean found = false;
		while (!found) {
			num++;
			sb = new StringBuilder();
			sb.append(num);
			if (sb.indexOf("0") >= 0)
				continue;
			
			Set<Integer> set = new HashSet<Integer>();
			// set to check if after each rotation new digit is in the end

			int end = sb.length();
			StringBuilder rotate = new StringBuilder(sb.toString());
			startIndex = 1;
			found = true;
			char firstDigit = sb.charAt(0);
			for (int i = 0; i <= end; i++) {
				if (i == end) {
					if (rotate.charAt(rotate.length() - 1) != firstDigit)
						// didn't end up with first digit at the end 
						found = false;
				}
				
				if (i > 0) {
					if (set.contains(Integer.parseInt(rotate.substring(rotate.length() - 1)))) {
						found = false;
						break;
					} else set.add(Integer.parseInt(rotate.substring(rotate.length() - 1)));
				}
				
				rotate = run(rotate, i);
			}			
		}
		
		out.println(num);
		f.close();
		out.close();
	}

	static StringBuilder run(StringBuilder s, int k) {
		int n = (k == 0) ? Integer.parseInt(s.substring(0, 1)) : Integer.parseInt(s.substring(s.length() - 1));
		startIndex = sb.indexOf(n + "");
		int i = 0;
		StringBuilder temp = new StringBuilder();
		int start = startIndex + 1;
		while (i < n) { // && i < s.length() - 1) {
			if (start == sb.length()) 
				start = 0;
			temp.append(sb.charAt(start));;
			i++;
			start++;
		}
		return temp;
	}
}
