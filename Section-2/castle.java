import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.sound.midi.Synthesizer;
/*
 ID: vschwartz
 LANG: JAVA
 PROB: castle
 */
public class castle {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int [][] input = new int[N + 1][M + 1];
		int[][] floor = new int[M * N + 1][M * N + 1];
		
		// 1 - existing passage 
		// -2 - removable wall
		for (int i = 1; i < floor.length; i++) {
			if (i % M != 0) floor[i][i + 1] = -2;
			if (i % M != 1) floor[i][i - 1] = -2;
			if (i > M) floor[i][i - M] = -2;
			if ((i / M) < (N - 1)) floor[i][i + M] = -2;
		}
		int counter = 1;
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(f.readLine());
			for (int j = 1; j < M + 1; j++) {
				int walls = Integer.parseInt(st.nextToken());
				input[i][j] = counter;
				if ((walls & 1) != 1) floor[counter][counter - 1] = 1;
				if ((walls & 2) != 2) floor[counter - M][counter] = 1;
				if ((walls & 4) != 4) floor[counter][counter + 1] = 1;
				if ((walls & 8) != 8) floor[counter + M][counter] = 1;
				counter++;
			}
		}
		
		// use 0 column to store room numbers for each square
		int startRoom = 1, roomNum = 1;
		while (startRoom < floor.length) {
			makeRooms(floor, startRoom, roomNum);
			while (startRoom < floor.length && floor[startRoom][0] != 0) startRoom++;
			roomNum++;
		}
		roomNum--;
		
		int[] rooms = new int[roomNum + 1];
		for (int i = 1; i < floor.length; i++) 
			rooms[floor[i][0]]++;
		int largestRoom = 1;
		for (int i = 0; i < rooms.length; i++) 
			if (rooms[i] > rooms[largestRoom])
				largestRoom = i;
		
		// which square belong to each room
		int[][] squares = new int[rooms.length][];
		for (int i = 0; i < squares.length; i++) {
			squares[i] = new int[rooms[i]];
			int k = 1;
			for (int j = 0; j < squares[i].length; j++) {
				while (floor[k][0] != i) k++;
				squares[i][j] = k;
				k++;
			}
		}

		out.println(roomNum);
		out.println(rooms[largestRoom]);
		if (squares.length == M * N + 1) {
			out.println(2);
			if (N == 1) out.println("1 1 E");
			else out.println(N + " 1 N");
		} else {
		
			int[] comboRoom = {0, 0};
			comboRoom = findThem(squares,  floor, comboRoom, squares);
			int room1 = comboRoom[0];
			int room2 = comboRoom[1];
			
			out.println(squares[comboRoom[0]].length + squares[comboRoom[1]].length);
			
			boolean solution = false;
			for (int m = 1; m <= M && !solution; m ++) {
				for (int n = N; n > 0 && !solution; n--) {
					if (floor[input[n][m]][0] == room2) {
						for (int k = 1; k < floor.length; k++) {
							if (floor[input[n][m]][k] == -2) {
								// see if room1 connects to room2
								if (floor[k][0] == room1) {
									if (k < input[n][m]) {
										out.println(n + " " + m + " N");
										solution = true;
										break;
									} else {
										out.println(n + " " + m + " E");
										solution = true;
										break;
									}
								}
							}
						}
					}
					
					if (floor[input[n][m]][0] == room1) {
						for (int k = 1; k < floor.length; k++) {
							if (floor[input[n][m]][k] == -2) {
								// see if room2 connects to room1
								if (floor[k][0] == room2) { 
									if (k < input[n][m]) {
										out.println(n + " " + m + " N");
										solution = true;
										break;
									} else {
										out.println(n + " " + m + " E");
										solution = true;
										break;
									}
								}
							}
						}
					}
	
				}
			}
		}
		
		
		f.close();
		out.close();
	}
	
	static void makeRooms(int[][] floor, int startRoom, int roomNum) {
		floor[startRoom][0] = roomNum;
		for (int i = 1; i < floor.length; i++) {
			if (floor[startRoom][i] == 1) {
				if (floor[i][0] == 0) {
					floor[i][0] = roomNum;
					makeRooms(floor, i, roomNum);
				}
			}
		}
	}
	
	static int[] findThem(int[][] temp, int[][] floor, int[] prevResult, int[][] originalSQ) {
		
		int[] hold = {};
		int[][] squares = temp.clone(); 
		int max = findMax(squares, 0);
		int nextMax = findMax(squares, max);
		
		// find out if rooms connect
		int count = 0;
		for (int i = 0; i < temp.length; i++) 
			if (squares[i].length > 0) count++;
		if (count > 2) {
			boolean notConnect = true;
			while (notConnect && count > 2) {
				
				for (int i = 0; i < squares[max].length && notConnect; i++) {
					for (int j = 0; j < squares[nextMax].length && notConnect; j++) {
						if (floor[squares[max][i]] [squares[nextMax][j]] == -2)
							notConnect = false; // can connect!
					}
				}
				if (notConnect) {
					squares[nextMax] = hold;
					nextMax = findMax(squares, max);
				}
				count = 0;
				for (int i = 0; i < temp.length; i++) 
					if (squares[i].length > 0) count++;
			}
		}
		
		squares = temp.clone();
		
		int [] result = {max, nextMax}; 
		int combined = originalSQ[result[0]].length + originalSQ[result[1]].length;			
		int currentMax = originalSQ[prevResult[0]].length + originalSQ[prevResult[1]].length;
		
		squares[max] = hold;
		count = 0;
		for (int i = 0; i < temp.length; i++) 
			if (squares[i].length > 0) count++;
		if (count >= 2) {
			 if (currentMax < combined) return findThem(squares, floor, result, originalSQ);
			 else return findThem(squares, floor, prevResult, originalSQ);	
		}
		else {
			if (squares.length == 3) return result;
			return prevResult;
		}
	}
	
	static int findMax(int[][] squares, int no){
		int temp = 0;
		for (int i = 1; i< squares.length; i++)
			if (squares[i].length > squares[temp].length && i != no)
				temp = i;
		return temp;
	}
	

	static void printArr(int[][] arr) {
		System.out.print("     ");
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("%-4d", i);
		}
		System.out.println();
		
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("%4d|", i);
			for (int j = 0; j < arr[i].length; j++) {
				System.out.printf("%-4d", arr[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static void printArr(int[]arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("%-4d", arr[i]);
		}
	}
}
