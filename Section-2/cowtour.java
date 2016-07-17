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
 PROB: cowtour
 */
public class cowtour {
	static int[][] groups;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowtour.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
		int N = Integer.parseInt(f.readLine());
		Point[] pastures = new Point[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			pastures[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		int[][] matrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			String s = f.readLine();
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = Integer.parseInt(s.substring(j, j + 1));
			}
		}
		
		double[][] dist = new double[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				dist[i][j] = Double.POSITIVE_INFINITY;
				if (i == j) dist[i][j] = 0;
				if (matrix[i][j] == 1)
					dist[i][j] = dist[j][i] = computeDist(pastures[i], pastures[j]);
			}
		}
		
		double[][] distances = FW(dist);
		
		// find out which pastures are in the same group
		groups = makeGroups(distances);
		double minDiameter = Double.MAX_VALUE;
		
		// largest in i + minDiam + largest in j
		double currentDiameter = 0;
		for (int i = 0; i < distances.length; i++) {
			for (int j = i + 1; j < distances.length; j++) {
				if (groups[i][j] != 0) {
					if (distances[i][j] > currentDiameter)
						currentDiameter = distances[i][j];
				}
			}
		}
//		System.out.println("currentDiameter "+currentDiameter);
		
		double[] subDiameters = new double[N];
		for (int i = 0; i < subDiameters.length; i++) {
			double max = 0;
			for (int j = 0; j < subDiameters.length; j++) {
				if (groups[i][j] != 0) {
					if (distances[i][j] > max) max = distances[i][j];
				}
			}
			subDiameters[i] = max;
		}

		for (int i = 0; i < distances.length; i++) {
			for (int j = i + 1; j < distances.length; j++) {
				if (groups[i][j] == 0) {
					distances[i][j] = computeDist(pastures[i], pastures[j]);
					if (minDiameter > distances[i][j] + subDiameters[i] + subDiameters[j])
						minDiameter = distances[i][j] + subDiameters[i] + subDiameters[j];
					distances[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
		
		if (currentDiameter > minDiameter) minDiameter = currentDiameter;
		out.printf("%.6f\n", minDiameter);
		f.close();
		out.close();
	}
	
	static double computeDist(Point p1, Point p2) {
		return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
	}
	
	static double[][] FW(double[][] darr) {
		int len = darr.length; 
		double[][] temp = new double[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				temp[i][j] = darr[i][j];
			}
		}

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				for (int k = 0; k < len; k++) {
					if (temp[j][k] > temp[j][i] + temp[i][k])
						temp[j][k] = temp[k][j] = temp[j][i] + temp[i][k];
				}
			}
		}
		return temp;
	}
	
	static int[][] makeGroups(double[][] darr) {
		int[][] temp = new int[darr.length][darr.length];
		for (int i = 0; i < temp.length; i++)
			for (int j = 0; j < temp.length; j++)
				if (darr[i][j] < Double.POSITIVE_INFINITY)
					temp[i][j] = 1;
		
		return temp;
	}
}

class Point {
	int x, y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
