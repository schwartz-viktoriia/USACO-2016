import java.io.BufferedReader;
import java.io.BufferedWriter;
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
 PROB: lamps
 */
public class lamps{
	static boolean[] lamps;
	static int C;
	static int count = 0;
	static boolean[] on;
	static boolean[] off;
	static boolean IMPOSSIBLE = true;
	static PrintWriter out;
	static Set<String> set = new LinkedHashSet<String>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
		int N = Integer.parseInt(f.readLine());
		C = Integer.parseInt(f.readLine());
		on = new boolean[N + 1];
		off = new boolean[N + 1];
		lamps = new boolean[N + 1];
		for (int i = 0; i < lamps.length; i++) 
			lamps[i] = true;
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int input = Integer.parseInt(st.nextToken());
		// mark "on" lamps as true
		while (input != -1) {
			on[input] = true;
			input = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(f.readLine());
		input = Integer.parseInt(st.nextToken());
		// mark "off" lamps as true
		while (input != -1) {
			off[input] = true;
			input = Integer.parseInt(st.nextToken());
		}
		
		switchThem();

		if (set.isEmpty()) out.println("IMPOSSIBLE");
		else {
			ArrayList<String> list = new ArrayList<String>(set);
			Collections.sort(list);
			for (String i: list)
				out.println(i);
		}

		f.close();
		out.close();
	}
	
	static void switchThem() {
		if (C == 0) {
			if (checkOns() && checkOffs()) {
				set.add(makeString());
			}
		}
		
		if (count == C % 8) return;
		
		b1();
		if (checkOns() && checkOffs())
			set.add(makeString());
		count++;
		switchThem();
		b1();
		count--;
		b2();
		if (checkOns() && checkOffs()) 
			set.add(makeString());
		count++;
		switchThem();
		b2();
		count--;
		b3();
		if (checkOns() && checkOffs())
			set.add(makeString());
		count++;
		switchThem();
		b3();
		count--;
		b4();
		if (checkOns() && checkOffs()) 
			set.add(makeString());
		count++;
		switchThem();
		b4();
		count--;
	}
	
	static boolean checkOns() {
		boolean flag = true;
		for (int i = 1; i < on.length; i++) {
			if (on[i]) {
				if (!lamps[i]) flag = false;
			}
		}
		return flag;
	}
	
	static boolean checkOffs() {
		boolean flag = true;
		for (int i = 1; i < on.length; i++) {
			if (off[i]) {
				if (lamps[i]) flag = false;
			}
		}
		return flag;
	}
	
	static String makeString() {
		String result = "";
		for (int i = 1; i < lamps.length; i++)
			if (lamps[i]) result += "1";
			else result += "0";
		return result;
	}
	
	static void b1() {
		for (int i = 1; i < lamps.length; i++)
			lamps[i] = !lamps[i];
	}
	
	static void b2() {
		for (int i = 1; i < lamps.length; i += 2)
			lamps[i] = !lamps[i];
	}

	static void b3() {
		for (int i = 2; i < lamps.length; i += 2)
			lamps[i] = !lamps[i];
	}
	
	static void b4() {
		for (int i = 1; i < lamps.length; i += 3)
			lamps[i] = !lamps[i];
	}
}
