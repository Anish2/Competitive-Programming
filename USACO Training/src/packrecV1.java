import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;


public class packrecV1 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader("packrec.in"));
		
		int[] wid = new int[4];
		int[] len = new int[4];
		int a, b;
		String p = in.readLine();
		
		for (int i = 0; i < 4; i++) {
			StringTokenizer t = new StringTokenizer(p);
			a = Integer.parseInt(t.nextToken());
			b = Integer.parseInt(t.nextToken());
			wid[i] = Math.min(a, b);
			len[i] = Math.max(a, b);
			p = in.readLine();
		}
		
		System.out.println(Arrays.deepToString((type2(wid, len))));

		
	}
	
	public static int[] type1(int[] w, int[] l) {
		
		int sum = 0;
		
		for (int i = 0; i < 4; i++) {
			sum += w[i];
		}
		
		Arrays.sort(l);
		int length = l[3];
		
		int[] result = {Math.min(length, sum), Math.max(length, sum), sum*length};
		
		return result;
		
	}
	
	public static int[][] type2(int[] w, int[] l) {
		
		int compBase, a, b, wid1, wid2, wid3, wid4, length1, length2, length3, length4;
		//int[] rect1, rect2, rect3, rect4;
		
		wid1 = w[0];
		wid2 = w[1];
		wid3 = w[2];
		wid4 = w[3];
		compBase = wid1 + wid2 + wid3;
		length1 = l[0];
		length2 = l[1];
		length3 = l[2];
		length4 = l[3];
		a = Math.max(compBase, length4);
		b = Math.max(Math.max(length1, length2), length3) + wid4;
		int[] rect1 = {Math.min(a, b), Math.max(a, b), a*b};
		
		
		//combo 2
		
		wid1 = w[0];
		wid2 = w[1];
		wid3 = w[3];
		wid4 = w[2];
		compBase = wid1 + wid2 + wid3;
		length1 = l[0];
		length2 = l[1];
		length3 = l[3];
		length4 = l[2];
		a = Math.max(compBase, length4);
		b = Math.max(Math.max(length1, length2), length3) + wid4;
		int[] rect2 = {Math.min(a, b), Math.max(a, b), a*b};
		
		// combo 3
		
		wid1 = w[1];
		wid2 = w[2];
		wid3 = w[3];
		wid4 = w[0];
		compBase = wid1 + wid2 + wid3;
		length1 = l[1];
		length2 = l[2];
		length3 = l[3];
		length4 = l[0];
		a = Math.max(compBase, length4);
		b = Math.max(Math.max(length1, length2), length3) + wid4;
		int[] rect3 = {Math.min(a, b), Math.max(a, b), a*b};
		
		//combo 4
		
		wid1 = w[0];
		wid2 = w[2];
		wid3 = w[3];
		wid4 = w[1];
		compBase = wid1 + wid2 + wid3;
		length1 = l[0];
		length2 = l[2];
		length3 = l[3];
		length4 = l[1];
		a = Math.max(compBase, length4);
		b = Math.max(Math.max(length1, length2), length3) + wid4;
		int[] rect4 = {Math.min(a, b), Math.max(a, b), a*b};
		
		// final computing
		
		int[] product = {rect1[2], rect2[2], rect3[2], rect4[2]};
		Arrays.sort(product);
		int[][] result = new int[4][3];
		int c = 0;
		if (product[3] == rect1[2]) {
			result[c] = rect1;
			c++;
		}
		if (product[3] == rect2[2]) {
			result[c] = rect2;
			c++;
		}
		if (product[3] == rect3[2]) {
			result[c] = rect3;
			c++;
		}
		if (product[3] == rect4[2]) {
			result[c] = rect4;
			c++;
		}
		
		return result;
		
		
	}

}
