import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.StringTokenizer;

/*
 ID: vschwartz
 LANG: JAVA
 PROB: maze1
 */

public class maze1 {
	static short W, H;
	static short[][] maze;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		W = Short.parseShort(st.nextToken());
		H = Short.parseShort(st.nextToken());
		char[][] input = new char[2 * H + 1][2 * W + 1];
		for (int i = 0; i < input.length; i++) {
			String s = f.readLine();
			for (int j = 0; j < input[i].length; j++)
				input[i][j] = s.charAt(j);
		}
		
		maze = new short[W * H + 1][W * H + 1];
		for (int i = 0; i < maze.length; i++)
			for (int j = 0; j < maze[i].length; j++)
				maze[i][j] = (short) (W * H);
		
		ArrayList<Integer> exits = new ArrayList<Integer>();
		int cell = 0;
		for (int i = 1; i < input.length; i += 2) {
			for (int j = 1; j < input[i].length; j += 2) {
				cell++;
				if (input[i - 1][j] == ' ') {
					if (i == 1) exits.add(cell);
					else maze[cell][cell - W] = maze[cell - W][cell] = 1;
				}
				if (input[i + 1][j] == ' ') {
					if (i == input.length - 2) exits.add(cell);
					else maze[cell][cell + W] = maze[cell + W][cell] = 1;
				}
				if (input[i][j - 1] == ' ') {
					if (j == 1) exits.add(cell);
					else maze[cell][cell - 1] = maze[cell - 1][cell] = 1;
				}
				if (input[i][j + 1] == ' ') {
					if (j == input[i].length - 2) exits.add(cell);
					else maze[cell][cell + 1] = maze[cell + 1][cell] = 1;
				}
			}
		}

		int exit1 = exits.get(0);
		int exit2 = exits.get(1);
		
		
		short[] dist = new short[W * H + 1];
		BitSet set = new BitSet(W * H + 1);

		int from = exit1;
		dist = maze[from];
		int d = 0;
		set.set(from);
		dist[exit1] = 0;
		dist[exit2] = 0;
		
		for (int i = 1; i < dist.length; i++) {
			for (int j = 1; j < dist.length; j++) {
				if (maze[from][j] == 1) {	
					dist[j] = (short) Math.min(dist[j], maze[from][j] + d);
				}
			}
			int min = 0;
			for (int j = 1; j < dist.length; j++) {
				if (!set.get(j) && dist[min] > dist[j]) {
					min = j;
					d = dist[j];
				}
			}
			from = min;
			set.set(from);
		}
		
		int worst = 0;
		for (int i = 1; i < dist.length; i++) {
			if (worst < dist[i]) worst = dist[i];
		}
		out.println(worst + 1);

		f.close();
		out.close();
	}
}
