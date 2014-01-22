import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class gifts {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader in = new BufferedReader(new FileReader("gifts.in"));

		PrintStream out = new PrintStream(new File("gifts.out"));
		
		StringTokenizer t = new StringTokenizer(in.readLine());
		
		int cows = Integer.parseInt(t.nextToken());
		int budget = Integer.parseInt(t.nextToken());
		
		Comparator<String> comparator = new Rank();
		PriorityQueue<String> q = 
	            new PriorityQueue<String>(4, comparator);
		
		// price then shipping, coupon half price then add shipping
		
		for (int i = 0; i < cows; i++) {
			q.add(in.readLine());
		}
		
		System.out.println(q.toString());

	}

}


class Rank implements Comparator<String>
{
    @Override
    public int compare(String x, String y)
    {
        
    	StringTokenizer t = new StringTokenizer(x);
    	int pX = Integer.parseInt(t.nextToken());
    	int sX = Integer.parseInt(t.nextToken());
    	
    	
    	StringTokenizer tok = new StringTokenizer(y);
    	int pY = Integer.parseInt(tok.nextToken());
    	int sY = Integer.parseInt(tok.nextToken());
    	
        if (pX+sX < pY+sY)
        {
            return -1;
        }
        if (pX+sX > pY+sY)
        {
            return 1;
        }
        if (pX+sX == pY+sY) {
        	if (pX/2.0+sX < pY/2.0+sY)
        		return -1;
        	else
        		return 1;
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