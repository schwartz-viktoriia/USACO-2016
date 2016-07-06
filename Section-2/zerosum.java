import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
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
 PROB: zerosum
 */
public class zerosum {
	static int N;
	static int[] arr;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		N = Integer.parseInt(f.readLine());
		arr = new int[N * 2 - 1]; 
		// numbers with operations: 0 for ' ', -1 for '+', -2 for '-'
		int k = 1;
		for (int i = 0; i < arr.length; i += 2)
			arr[i] = k++;
		
		recur(1);
		f.close();
		out.close();
	}
	
	static void recur(int index) {
		if (index > arr.length - 2) return;
		
		for (int op = 0; op > -3; op--) {
			arr[index] = op;
			if (index == arr.length - 2) 
				if (eval() == 0) 
					printArr();
			recur(index + 2);
			arr[index] = 0;
		}
	}
	
	static int eval() {
		int result = 0, temp = 0, op = -1;
		for (int i = 0; i < arr.length; i += 2) {
			temp = temp * 10 + arr[i];
			if (i == (arr.length - 1) || arr[i + 1] != 0) {	
				if (op == -2) {
						result -= temp;
					}
				if (op == -1) {
					result += temp;
				}
				temp = 0;
				if (i < (arr.length - 1)) op = arr[i + 1];
			}
		}
		result += temp;
		
		return result;
	}

	static void printArr() {
		for (int i = 0; i < arr.length; i++) {
			switch (arr[i]) {
			case 0: out.print(' '); break;
			case -1: out.print('+'); break;
			case -2: out.print('-'); break;
			default: out.print(arr[i]);
			}
		}
		out.println();
	}
}
