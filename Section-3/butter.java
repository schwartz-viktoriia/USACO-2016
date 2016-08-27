import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
ID: vschwartz
LANG: JAVA
PROB: butter
*/

public class butter {
	public static int N, P, C;
	public static int[] cowP;
	public static Node[][] adj;
	public static int[] edgesP;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("butter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		cowP = new int[1000];
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(f.readLine());
			cowP[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		adj = new Node[P][P];
		edgesP = new int [P];
		for (int i = 0; i < C; i++){
			st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int dis = Integer.parseInt(st.nextToken());
			adj[start][edgesP[start]] = new Node(end, dis);
			edgesP[start]++;
			adj[end][edgesP[end]] = new Node(start, dis);
			edgesP[end]++;
		}
		
		int minSum = Integer.MAX_VALUE;
		for (int i = 0; i < P; i++){
			int sum = MinAllD(i);
			if (sum < minSum) minSum = sum;
		}
		out.println(minSum);

		f.close();
		out.close(); 
	}
	
	public static int MinAllD(int src){
		int[] dist = new int[P];
		for (int i = 0; i < P; i++) dist[i] = 10000;
		dist[src] = 0;
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(src);
		while (!q.isEmpty()){
			int x = q.remove();
			for (int j = 0; j < edgesP[x]; j++){
				if (dist[x] + adj[x][j].dist < dist[adj[x][j].end]){
					dist[adj[x][j].end] = dist[x] + adj[x][j].dist;
					q.add(adj[x][j].end);
				}
			}
		}
		
		int ans = 0;
		for (int i = 0; i < N; i++){
			ans += dist[cowP[i]];
		}
		return ans;
	}
}

class Node{
	public int end, dist;
	
	public Node(int end, int len){
		this.end = end;
		this.dist = len;
	}
}