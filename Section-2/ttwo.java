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
 PROB: ttwo
 */

public class ttwo {
	static char[][] forest = new char[10][10];
	static int count = 0;
	static int xF, yF, xC, yC;
	static int dirF = 0, dirC = 0; // direction: 0-N, 1-E, 2-S, 3-W
	static Set<Integer> memo = new HashSet<Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
		for (int i = 0; i < forest.length; i++) {
			String s = f.readLine();
			for (int j = 0; j < forest.length; j++) {
				if (s.charAt(j) == 'F') {
					xF = i;
					yF = j;
					forest[i][j] = '.';
				} else if (s.charAt(j) == 'C') {
					xC = i;
					yC = j;
					forest[i][j] = '.';
				} else forest[i][j] = s.charAt(j);
			}
		}

		int i = 0;
		while (true) {
			
			switch (dirF) {
			case 0: if (xF > 0 && forest[xF - 1][yF] != '*') xF--; else dirF = (dirF + 1) % 4; break;
			case 1: if (yF < 9 && forest[xF][yF + 1] != '*') yF++; else dirF = (dirF + 1) % 4; break;
			case 2: if (xF < 9 && forest[xF + 1][yF] != '*') xF++; else dirF = (dirF + 1) % 4; break;
			case 3: if (yF > 0 && forest[xF][yF - 1] != '*') yF--; else dirF = (dirF + 1) % 4; break;
			}
			
			switch (dirC) {
			case 0: if (xC > 0 && forest[xC - 1][yC] != '*') xC--; else dirC = (dirC + 1) % 4; break;
			case 1: if (yC < 9 && forest[xC][yC + 1] != '*') yC++; else dirC = (dirC + 1) % 4; break;
			case 2: if (xC < 9 && forest[xC + 1][yC] != '*') xC++; else dirC = (dirC + 1) % 4; break;
			case 3: if (yC > 0 && forest[xC][yC - 1] != '*') yC--; else dirC = (dirC + 1) % 4; break;
			}
			
			int move = dirF * 100000 + xF * 10000 + yF * 1000 + dirC * 100 + xC * 10 + yC;
			if (memo.contains(move)) {
				out.println(0);
				break;
			}
			memo.add(move);
			i++;
			if (xF == xC && yF == yC) {
				out.println(i); 
				break;
			}
		}

		f.close();
		out.close();
	}
}
