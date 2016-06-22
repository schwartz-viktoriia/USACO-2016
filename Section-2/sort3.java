import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 ID: vschwartz
 LANG: JAVA
 PROB: sort3
 */

public class sort3 {
	static int[] input;
	static int start = 0, end, count = 0, three = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		int N = Integer.parseInt(f.readLine());
		input = new int[N];
		for (int i = 0; i < N; i++)
			input[i] = Integer.parseInt(f.readLine());

		for (int i = 0; i < input.length; i++) 
			if (input[i] == 3) three++;

		swap(1, 3);
		start = 0;
		swap(1, 2);
		swap(2, 3);
		
		out.println(count);
		f.close();
		out.close();
	}
	
	static void swap(int a, int b) {
		int x = (b == 3) ? three : input.length;
		end = input.length - 1;
		while (start < end && end >= (input.length - x)) {
			if (input[end] == a) {
				if (input[start] == b) {
					input[end] = b;
					input[start] = a;
					count++;
					start++;
					end--;
				} else {
					start++;
				}
			} else {
				end--;
			}
		}
	}

}
