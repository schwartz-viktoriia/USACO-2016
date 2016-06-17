import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
/*
 ID: vschwartz
 LANG: JAVA
 PROB: milk
 */

public class milk {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("milk.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int milkNeeded = Integer.parseInt(st.nextToken());
		int numFarmers = Integer.parseInt(st.nextToken());
		int [][] offers = new int[numFarmers][2];
		for (int i = 0; i < numFarmers; i++) {
			st = new StringTokenizer(f.readLine());
			offers[i][0] = Integer.parseInt(st.nextToken());
			offers[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int milkBought = 0;
		int cost = 0;
		while (milkBought < milkNeeded) {
			int bestPriceIndex = minPriceIndex(offers);
			int milkPortion = offers[bestPriceIndex][1];
			if (milkPortion > (milkNeeded - milkBought))
				milkPortion = milkNeeded - milkBought;
			cost += milkPortion * offers[bestPriceIndex][0];
			offers[bestPriceIndex][0] = -1;
			milkBought += milkPortion;
		}
		out.println(cost);
		
		f.close();
		out.close();
	}

	static int minPriceIndex(int [][] a) {
		int index = 0;
		while (a[index][0] == -1) index++;
		for (int i = 0; i < a.length; i++)
			if (a[i][0] < a[index][0] && a[i][0] != -1) 
				index = i;
		return index;
	}
}
