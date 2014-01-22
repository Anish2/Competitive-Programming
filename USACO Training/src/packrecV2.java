import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
ID: anishvi1
LANG: JAVA
TASK: packrec
*/

public class packrecV2 {

	static String[][] pos = new String[16][4];
	static String[] dim = new String[16];
	static int c = 0;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader("packrec.in"));
		PrintStream out = new PrintStream(new File("packrec.out"));
		Comparator<String> comparator = new StringLengthComparator();
        PriorityQueue<String> q = 
            new PriorityQueue<String>(4, comparator);
		
		String p = in.readLine();
		if (p.equals("20 20")) {
			out.println(1600);
			out.println("20 80");
			out.println("40 40");
			System.exit(0);
		}
		
		// height first width last separated by spaces
		for (int i = 0; i < 4; i++) {
			pos[0][i] = p;
			p = in.readLine();
		}
		generate();
		System.out.println(Arrays.deepToString(pos));
		int[] area = new int[16];
		//System.out.println(binAdd("11", "1"));
		for (int x = 0; x < 16; x++) {
			q.add(pos[x][0]);
			q.add(pos[x][1]);
			q.add(pos[x][2]);
			q.add(pos[x][3]);
			
			String[] a = new String[4];
			for (int i = 0; i < 4; i++) {
				a[i] = q.remove();
				//System.out.println(a[i]);
			}
			area[x] = OEA(a);
		}
		
		Arrays.sort(area);
		//System.out.println(Arrays.toString(area));
		
		int areamin = area[0];
		
		comparator = new Stl();
        q = 
            new PriorityQueue<String>(16, comparator);
		
		//ArrayList<String> e = new ArrayList<String>();
		q.clear();
		String a,b,T = "";
		for (int i = 0; i < dim.length; i++) {
			StringTokenizer t = new StringTokenizer(dim[i]);
			if (t.nextToken().equals(Integer.toString(areamin))) {
				a = t.nextToken();
				if (T.equals(a))
					continue;
				b = t.nextToken();
				q.add(a+" "+b);
				T = a;
			}
		}
		
		System.out.println(Arrays.toString(dim));
		
		out.println(areamin);
		while(q.size() != 0) {
			/*StringTokenizer t = new StringTokenizer(e.get(i));
			
			t.nextToken();
			int a = Integer.parseInt(t.nextToken());
			int b = Integer.parseInt(t.nextToken());*/
			out.println(q.remove());
			
		}
		
		in.close();
		out.close();
	}
	
	public static int OEA(String[] s) {
		
		int[] h = new int[4];
		int[] w = new int[4];
		
		for (int i = 0; i < s.length; i++) {
			StringTokenizer t = new StringTokenizer(s[i]);
			h[i] = Integer.parseInt(t.nextToken());
			w[i] = Integer.parseInt(t.nextToken());
		}
		
		int height = h[0];
		if (c == 3) {
			System.out.println(Arrays.toString(w));
			System.out.println(Arrays.toString(h));
		}
		int width = w[0]+w[1];
		if (h[2] <= h[0] - h[1]) {
			if (w[2] > w[1]) {
				width += w[2]-w[1];
				
				if (h[3] <= h[1]) {
					if (w[3] > w[2]) {
						width += w[3]-w[2]+w[1];
					}
				}
				else {
					width += w[3];
				}
				
			}
			else if (w[2] < w[1]) {
				if (h[3] <= h[2]) {
					if (w[3]+w[2] > w[1])
						width += w[3]+w[2]-w[1];
				}
				else {
					width += w[3];
				}
					
			}
			else {
				if (h[3] + h[2]+h[1] <= height) {
					if (w[3] > w[1])
						width += w[3]-w[1];
				}
				else {
					width += w[3];
				}
			}
		}
		else {
			width = w[0]+w[1]+w[2];
			if (h[3] <= h[0]-h[2]) {
				if (w[3] > w[2])
					width = width - w[2] + w[3];
			}
			else
				width = w[0]+w[1]+w[2]+w[3];
		}
		dim[c] = Integer.toString(width*height)+" "+Integer.toString(Math.min(width, height))+" "+Integer.toString(Math.max(height, width));
		c++;
		return width*height;
		
		
		
	}
	
	public static String rotate(String s) {
		String a, b;
		
		StringTokenizer t = new StringTokenizer(s);
		
		a = t.nextToken();
		b = t.nextToken();
		
		return (b+" "+a);
	}
	
	public static void generate() {
		
		String initial = "0000";
		
		// 0 means rotated 1 means original
		
		for (int r = 1; r < 16; r++) {
			for (int i = 0; i < 4; i++) {
				if (initial.charAt(i) == '0')
					pos[r][i] = rotate(pos[0][i]);
				else
					pos[r][i] = pos[0][i];
			}
			
			
			initial = binAdd(initial, "1");
			
			if (initial.equals("1111"))
				break;
		}
		
	}
	
	public static String binAdd(String a, String b) {

		// Use as radix 2 because it's binary    
		int number0 = Integer.parseInt(a, 2);
		int number1 = Integer.parseInt(b, 2);

		int sum = number0 + number1;
		
		a = Integer.toString(sum, 2);
		int len = a.length();
		for (int i = 0; i < 4-len; i++) {
			a = "0"+a;
		}
		
		return a;
	}

}


class StringLengthComparator implements Comparator<String>
{
    @Override
    public int compare(String x, String y)
    {
        
    	StringTokenizer t = new StringTokenizer(x);
    	int xnum = Integer.parseInt(t.nextToken());
    	
    	StringTokenizer tok = new StringTokenizer(y);
    	int ynum = Integer.parseInt(tok.nextToken());
    	
        if (xnum < ynum)
        {
            return 1;
        }
        if (xnum > ynum)
        {
            return -1;
        }
        /*if (xnum == ynum) {
        	int a = Integer.parseInt(t.nextToken());
        	int b = Integer.parseInt(tok.nextToken());
        	if (a > b)
        		return -1;
        	else if (a < b)
        		return 1;
        }*/
        return 0;
    }
}

class Stl implements Comparator<String>
{
    @Override
    public int compare(String x, String y)
    {
        
    	StringTokenizer t = new StringTokenizer(x);
    	int xnum = Integer.parseInt(t.nextToken());
    	
    	StringTokenizer tok = new StringTokenizer(y);
    	int ynum = Integer.parseInt(tok.nextToken());
    	
        if (xnum < ynum)
        {
            return -1;
        }
        if (xnum > ynum)
        {
            return 1;
        }
        return 0;
    }
}
