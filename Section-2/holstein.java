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
 PROB: holstein
 */
public class holstein {
	static int[] total; 
	static int[] vit;
	static int[][] feeds;
	static ArrayList<Integer> record = new ArrayList<Integer>(); 
	static ArrayList<Integer> bestRecord = new ArrayList<Integer>();
	static int currentBest = Integer.MAX_VALUE;
	static int I = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		int V = Integer.parseInt(f.readLine());
		total = new int[V];
		vit = new int[V];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 0; i < V; i++) {
			vit[i] = Integer.parseInt(st.nextToken());
		}
		int G = Integer.parseInt(f.readLine());

		feeds = new int[G][V];
		for (int i = 0; i < G; i++) {
			st = new StringTokenizer(f.readLine());
			for (int j = 0; j < V; j++)
				feeds[i][j] = Integer.parseInt(st.nextToken());
		}
		scoops();
//		System.out.println(bestRecord);
		out.print(bestRecord.size() + " ");
		for (int i = 0; i < bestRecord.size() - 1; i++)
			out.print(bestRecord.get(i) + " ");
		out.println(bestRecord.get(bestRecord.size() - 1));
		
		f.close();
		out.close();
	}
	
	static void scoops() {
		for (int i = I; i < feeds.length; i++) {
			// list record keeps track of feeds
			record.add(i + 1);
			
			for (int j = 0; j < total.length; j++)
				total[j] += feeds[i][j];
			
			boolean healthy = true;
			for (int j = 0; j < vit.length && healthy; j++)
				if (total[j] < vit[j]) 
					healthy = false;
			
			if (healthy) {
				if (record.size() < currentBest ) {
					currentBest = record.size();
					bestRecord = (ArrayList<Integer>)record.clone();
				}
			} else {
				I = i + 1;
				scoops();
			}
			for (int j = 0; j < total.length; j++)
				total[j] -= feeds[i][j];	
			
			record.remove(record.size() - 1);
		}
	}
}
