import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/*
 ID: vschwartz
 LANG: JAVA
 PROB: namenum
 */
public class namenum {
	static BufferedReader realNames;
	static PrintWriter out;
	static StringBuilder possibleName = new StringBuilder();
	static String [] letters = {"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PRS", "TUV", "WXZ"};
//	static Map
	
	public static void main(String[] args) throws IOException {
//		long startTime = System.nanoTime();
		
		BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		String num = f.readLine();
//		String num = "234643";
		
//		printNames(num, "");
		
		mappingMethod(num);
		f.close();
		realNames.close();
		out.close();
		
//		long endTime = System.nanoTime();
//		System.out.println("Took "+ (endTime - startTime) / 1_000_000_0 + " s");
	}
	
	// reverse approach - map all the names in dict first
	static void mappingMethod(String num) throws IOException {
		HashMap <String, String> map = new HashMap <String, String> ();
		map.put("A", "2");
		map.put("B", "2");
		map.put("C", "2");
		map.put("D", "3");
		map.put("E", "3");
		map.put("F", "3");
		map.put("G", "4");
		map.put("H", "4");
		map.put("I", "4");
		map.put("J", "5");
		map.put("K", "5");
		map.put("L", "5");
		map.put("M", "6");
		map.put("N", "6");
		map.put("O", "6");
		map.put("P", "7");
		map.put("R", "7");
		map.put("S", "7");
		map.put("T", "8");
		map.put("U", "8");
		map.put("V", "8");
		map.put("W", "9");
		map.put("X", "9");
		map.put("Y", "9");

		realNames = new BufferedReader(new FileReader("dict.txt"));
		StringBuilder temp = new StringBuilder();
		int counter = 0;
		while (realNames.ready()) {
			String name = realNames.readLine();
			temp.setLength(0);
			if (name.charAt(0) != 'Q' && name.charAt(0) != 'Z') {
				for (int i = 0; i < name.length(); i++) {
					String l = map.get(name.substring(i, i + 1));
					if (l != null) temp.append(l);
					else temp.append('-');
				}
				
				if (num.equals(temp.toString())) {
					out.println(name);
					counter++;
				}
			}
		}
		if (counter == 0) out.println("NONE");
	}
	
	// takes too long..
	static void printNames(String num, String name) throws IOException {
		String l = letters[Integer.parseInt(num.substring(0, 1))];
		String toCheck = num.substring(1);
		
		for (int i = 0; i < 3; i++) {
			String result = name + l.charAt(i);
			if (toCheck.length() == 0) {
				realNames = new BufferedReader(new FileReader("dict.txt"));
				while (realNames.ready()) {
					if (result.equals(realNames.readLine()))
						System.out.println(result);
				}
			} else printNames(toCheck, result);
				
		}
	}

}

