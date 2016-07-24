import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 ID: vschwartz
 LANG: JAVA
 PROB: fact4
 */
public class fact4 {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fact4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
		int N = Integer.parseInt(f.readLine());
		
		String fact = "1";
		for (int i = 2; i <= N; i++) {
			fact = mult(fact, Integer.toString(i));
		}
		int i = fact.length() - 1;
		for (; i >= 0; i--)
			if (fact.charAt(i) != '0') break;
		out.println(fact.charAt(i));
//		System.out.println(fact);
		
		// 143 = 385...3376640000000000000000000000000000000000
		// 645 = ....82624000000000000000...0000000000000000000

		f.close();
		out.close();
	}

	static String mult(String a, String b) {
		StringBuilder result = new StringBuilder();
		StringBuilder[] inter = new StringBuilder[b.length()];
		int maxLen = 0;
		
		for (int i = b.length() - 1; i >=0; i--) {
			inter[i] = new StringBuilder();
			int carry = 0;
			for (int j = a.length() - 1; j >=0; j--) {
				int n1 = Integer.parseInt(a.substring(j, j + 1));
				int n2 = Integer.parseInt(b.substring(i, i + 1));
				inter[i].insert(0, (n1 * n2 + carry) % 10);
				carry = (n1 * n2 + carry) / 10;
			}
			if (carry != 0) inter[i].insert(0, carry);
			
			for (int j = 0; j < b.length() - i - 1; j++)
				inter[i].append("0");
			if (inter[i].length() > maxLen) maxLen = inter[i].length();
		}
		
		for (int i = 0; i < inter.length; i++) {
			while (inter[i].length() < maxLen)
				inter[i].insert(0, '0');
		}
		
		int carry = 0;
		for (int i = maxLen - 1; i >= 0; i--) {
			int sum = 0;
			for (int j = 0; j < inter.length; j++) {
				int num = Integer.parseInt(inter[j].substring(i, i + 1));
				sum += num;
			}
			if ((sum + carry) % 10 != 0) result.insert(0, (sum + carry) % 10);
			carry = (sum + carry) / 10;
		}
		if (carry != 0) result.insert(0, carry);
		return result.toString();
	}
}
