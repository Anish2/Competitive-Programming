import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class ballet {

	static int[][] grid;
	static int lowoneW = 0,lowoneL = 0, width = 0, length = 0;
	static ArrayList<Integer> totalW = new ArrayList<Integer>();
	static ArrayList<Integer> totalL = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("ballet.in"));
		
		// BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		

		PrintStream out = new PrintStream(new File("ballet.out"));

		String p = in.readLine();
		String foot;
		char dir = 'N', step;
		int col = 0;

		grid = new int[][] { {0, 1, 0, 1}, {0, 0, 1, 1} };
		p = in.readLine();
		while (p != null) {

			foot = p.substring(0, 2);
			step = p.charAt(2);
			//System.out.println(foot);
			
			if (foot.equals("RL"))
				col = 0;
			else if (foot.equals("RR"))
				col = 1;
			else if (foot.equals("FL"))
				col = 2;
			else if (foot.equals("FR"))
				col = 3;

			if (step == 'F') {
				if (dir == 'N')
					grid[1][col]++;
				else if (dir == 'E')
					grid[0][col]++;
				else if (dir == 'S')
					grid[1][col]--;
				else
					grid[0][col]--;
			}
			else if (step == 'B') {
				//System.out.println("Im yoona");
				//System.out.println(col);
				//System.out.println(foot);
				if (dir == 'N')
					grid[1][col]--;
				else if (dir == 'E')
					grid[0][col]--;
				else if (dir == 'S')
					grid[1][col]++;
				else
					grid[0][col]++;
			}
			if (step == 'R') {
				if (dir == 'N')
					grid[0][col]++;
				else if (dir == 'E')
					grid[1][col]--;
				else if (dir == 'S')
					grid[0][col]--;
				else
					grid[1][col]++;
			}
			if (step == 'L') {
				if (dir == 'N')
					grid[0][col]--;
				else if (dir == 'E')
					grid[1][col]++;
				else if (dir == 'S')
					grid[0][col]++;
				else
					grid[1][col]--;
			}
			else if (step == 'P') {
				
				rotate(col);
				if (dir == 'N')
					dir = 'E';
				else if (dir == 'E')
					dir = 'S';
				else if (dir == 'S')
					dir = 'W';
				else
					dir = 'N';
				
			}
			
			//System.out.println(Arrays.deepToString(grid));
			//System.out.println(dir);
			int[][] extra = new int[2][4];
			for(int i=0; i<2; i++)
				  for(int j=0; j<4; j++)
				    extra[i][j]=grid[i][j];
			//System.out.println(Arrays.deepToString(grid));
			score(extra);
			//System.out.println(Arrays.deepToString(grid));
			
			
			if (checkTrip()) {
				out.println("-1");
				System.exit(0);
			}
			
			//System.out.println(width+"X"+length);

			p = in.readLine();
		}
		int[] w = new int[totalW.size()];
		int[] l = new int[totalL.size()];
		for (int i = 0; i < totalW.size(); i++) {
			w[i] = totalW.get(i);
			l[i] = totalL.get(i);
		}
		
		Arrays.sort(w);
		Arrays.sort(l);
		
		width = w[w.length-1]-w[0]+1;
		length = l[l.length-1]-l[0]+1;
		
		out.println(width*length);


		in.close();
		out.close();
	}
	public static void rotate(int foot) {

		int[][] r = new int[2][4];

		for(int i=0; i<2; i++)
			for(int j=0; j<4; j++)
				r[i][j]=grid[i][j];
		
		if (foot == 0) {
			
			grid = new int[][] { {r[0][0], r[0][0]-r[1][0]+r[1][1], r[0][0]-r[1][0]+r[1][2], r[0][0]-r[1][0]+r[1][3]},
					{r[1][0], r[0][0]+r[1][0]-r[0][1], r[0][0]+r[1][0]-r[0][2], r[0][0]+r[1][0]-r[0][3]}
			};
			
		}
		
		else if (foot == 1) {
			grid = new int[][] { {r[0][foot]-r[1][foot]+r[1][0], r[0][foot], r[0][foot]-r[1][foot]+r[1][2], r[0][foot]-r[1][foot]+r[1][3]},
					{r[0][foot]+r[1][foot]-r[0][0], r[1][foot], r[0][foot]+r[1][foot]-r[0][2], r[0][foot]+r[1][foot]-r[0][3]}
			};
		}
		else if (foot == 2) {
			grid = new int[][] { {r[0][foot]-r[1][foot]+r[1][0], r[0][foot]-r[1][foot]+r[1][1], r[0][foot], r[0][foot]-r[1][foot]+r[1][3]},
					{r[0][foot]+r[1][foot]-r[0][0], r[0][foot]+r[1][foot]-r[0][1], r[1][foot], r[0][foot]+r[1][foot]-r[0][3]}
			};
		}
		else {
			grid = new int[][] { {r[0][foot]-r[1][foot]+r[1][0], r[0][foot]-r[1][foot]+r[1][1], r[0][foot]-r[1][foot]+r[1][2], r[0][foot]},
					{r[0][foot]+r[1][foot]-r[0][0], r[0][foot]+r[1][foot]-r[0][1], r[0][foot]+r[1][foot]-r[0][2], r[1][foot]}
			};
		}


	}
	
	public static void score(int [][] a) {
		
		/*int max = max(a[0]);
		int min = min(a[0]);
		if (min < 0 && max >= 0 && width < max-min+1)
			width = max-min+1;
		else if (max < 0 && max < lowoneW && width < width + lowoneW-max)
			width += lowoneW-max;
		else if (width < max+1)
			width = max+1;
		
		max = max(a[1]);
		min = min(a[1]);
		if (min < 0 && length < max-min+1)
			length = max-min+1;
		else if (max < 0 && max < lowoneL && length < length + lowoneL-max)
			length += lowoneL-max;
		else if (length < max +1)
			length = max+1;*/
		//System.out.println(grid[0][3]);
		for (int i = 0; i < 4; i++) {
			totalW.add(a[0][i]);
			totalL.add(a[1][i]);
		}
		
	}
	
	public static int max(int[] a) {
		Arrays.sort(a);
		return a[a.length-1];
	}
	public static int min(int[] a) {
		Arrays.sort(a);
		return a[0];
	}
	public static boolean checkTrip() {
		int g;
		for (int i = 0; i < 4; i++) {
			g = i+1;
			while (g < 4) {
				if (grid[0][i] == grid[0][g] && grid[1][i] == grid[1][g]) {
					System.out.println(grid[0][1]);
					return true;
				}
				g++;
			}
		}
		
		return false;
	}

}
