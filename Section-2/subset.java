import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 ID: vschwartz
 LANG: JAVA
 PROB: subset
 */
public class subset {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("subset.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out"))); 
		int N = Integer.parseInt(f.readLine()); 
		int sum = 0;
		long count = 0;
		for (int i = 1; i <= N; i++)
			sum += i;
		
		if ((sum & 1) != 1) { // if sum is odd can't partition
			long [][] DP = new long[N + 1][sum + 1];
			
			for(int i = 0; i <= N; i++) DP[i][0] = 1;
		     for(int i = 1; i <= N; i++){
		          for(int j = 1; j <= sum; j++){
		               DP[i][j] += DP[i - 1][j];
		               if(i <= j) DP[i][j] += DP[i - 1][j - i];
		          }
		     }
		     count = DP[N][sum / 2] / 2;
		}
		out.println(count);

		f.close();
		out.close();
	}
	
}
