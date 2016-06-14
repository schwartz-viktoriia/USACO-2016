import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 ID: vschwartz
 LANG: JAVA
 PROB: skidesign
 */
public class skidesign {
	static int[] hills;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("skidesign.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
		int n = Integer.parseInt(f.readLine());
		hills = new int[n];
		for (int i = 0; i < n; i++) 
			hills[i] = Integer.parseInt(f.readLine());

		Arrays.sort(hills);

		int low = hills[0];  
	    int high = hills[n - 1];  
	    int minCost = Integer.MAX_VALUE;  
	    for (int i = low;i <= high-17; i++) {  
	        int cost = 0;  
	        int current_high = i + 17;  
	        for (int j = 0; j < n; j++) {  
	            if(hills[j] < i) 
	                cost += (i - hills[j]) * (i - hills[j]);    
	            else if(hills[j] > current_high)  
	                cost += (hills[j] - current_high) * (hills[j] - current_high);   
	        }  
	        minCost = cost < minCost ? cost : minCost;  
	    }  
		
		out.println(minCost);
		
		f.close();
		out.close();
	}
}
