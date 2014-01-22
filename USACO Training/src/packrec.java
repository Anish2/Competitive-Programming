import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;





class Permute<E> implements  Iterator<E[]>{

	private E[] arr;
	private int[] ind;
	private boolean has_next;

	public E[] output;//next() returns this array, make it public

	public Permute(E[] arr){
		this.arr = arr.clone();
		ind = new int[arr.length];
		//convert an array of any elements into array of integers - first occurrence is used to enumerate
		Map<E, Integer> hm = new HashMap<E, Integer>();
		for(int i = 0; i < arr.length; i++){
			Integer n = hm.get(arr[i]);
			if (n == null){
				hm.put(arr[i], i);
				n = i;
			}
			ind[i] = n.intValue();
		}
		Arrays.sort(ind);//start with ascending sequence of integers


		//output = new E[arr.length]; <-- cannot do in Java with generics, so use reflection
		output = (E[]) Array.newInstance(arr.getClass().getComponentType(), arr.length);
		has_next = true;
	}

	public boolean hasNext() {
		return has_next;
	}

	/**
	 * Computes next permutations. Same array instance is returned every time!
	 * @return
	 */
	public E[] next() {
		if (!has_next)
			throw new NoSuchElementException();

		for(int i = 0; i < ind.length; i++){
			output[i] = arr[ind[i]];
		}


		//get next permutation
		has_next = false;
		for(int tail = ind.length - 1;tail > 0;tail--){
			if (ind[tail - 1] < ind[tail]){//still increasing

				//find last element which does not exceed ind[tail-1]
				int s = ind.length - 1;
				while(ind[tail-1] >= ind[s])
					s--;

				swap(ind, tail-1, s);

				//reverse order of elements in the tail
				for(int i = tail, j = ind.length - 1; i < j; i++, j--){
					swap(ind, i, j);
				}
				has_next = true;
				break;
			}

		}
		return output;
	}

	private void swap(int[] arr, int i, int j){
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	public void remove() {

	}

	public static void main(String[] args) {
		Permute<Integer> perm = new Permute<Integer>(new Integer[]{3,3,4,4,4,5,5});
		int count = 0;
		while(perm.hasNext()){
			System.out.println(Arrays.toString(perm.next()));
			count++;
		}
		System.out.println("total: " + count);
	}
}



public class packrec {

	static Comparator<String> comparator = new Sorter();
	static PriorityQueue<String> q = 
			new PriorityQueue<String>(4, comparator);



	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("packrec.in"));

		String p = in.readLine();

		ArrayList<Rectangle> rect = new ArrayList<Rectangle>();

		for (int i = 0; i < 4; i++) {
			StringTokenizer t = new StringTokenizer(p);
			rect.add(new Rectangle(Integer.parseInt(t.nextToken()), Integer.parseInt(t.nextToken())));
			p = in.readLine();
		}

		/*for (Rectangle a: rect) {
			System.out.println(a);
		}*/

		//System.out.println(generate(rect));

		ArrayList<Rectangle[]> re = generate(rect);

		for (Rectangle[] r: re) {
			putIn(r);
		}
		
		
		while (q.size() > 0) {
			System.out.println(q.poll());
		}


		// done with data entry

	}

	public static ArrayList<Rectangle[]> generate(ArrayList<Rectangle> rect) {

		ArrayList<Rectangle[]> combo = new ArrayList<Rectangle[]>();

		for (int i = 0; i < 16; i++) {

			String b = bin(i);

			Rectangle[] temp = new Rectangle[4];

			for (int x = 0; x < b.length(); x++) {
				if (b.charAt(x) == '1') {
					temp[x] = reverse(rect.get(x));
				}
				else
					temp[x] = rect.get(x);
			}

			combo.add(temp);

		}

		ArrayList<Rectangle[]> fin = new ArrayList<Rectangle[]>();

		for (int i = 0; i < combo.size(); i++) {

			Permute<Rectangle> perm = new Permute<Rectangle>(combo.get(i));

			while(perm.hasNext()) {
				fin.add(perm.next());
			}

		}

		return fin;

	}

	public static void putIn(Rectangle[] r) {

		// case 1
		int W = r[0].width+r[1].width+r[2].width+r[3].width;
		int H = max(new int[] {r[0].height+r[1].height+r[2].height+r[3].height});

		q.offer(W+" "+H+" "+W*H);

		// case 2
		W = max(new int[] {r[0].width+r[1].width+r[2].width, r[3].width});
		H = max(new int[] {r[0].height, r[1].height, r[2].height})+r[3].height;

		q.offer(W+" "+H+" "+W*H);

		// case 3
		W = max(new int[] {r[0].width+r[1].width, r[3].width}) + r[2].width;
		H = max(new int[] {max(new int[] {r[0].height, r[1].height})+r[3].height, r[2].height});

		q.offer(W+" "+H+" "+W*H);

		// case 4
		W = r[0].width + max(new int[] {r[1].width, r[2].width}) + r[3].width;
		H = max(new int[] {r[0].height, r[1].height+r[2].height, r[3].height});

		q.offer(W+" "+H+" "+W*H);

		// case 5
		W = max(new int[] {r[0].width, r[1].width}) + r[2].width + r[3].width;
		H = max(new int[] {r[0].height+r[1].height, r[2].height, r[3].height});

		q.offer(W+" "+H+" "+W*H);

		// case 6
		/*if (r[2].width-r[3].width >= 0 && r[2].width-r[3].width >=r[1].width-r[0].width) {
			W = r[1].width+r[3].width;
			H = max(new int[] {r[0].height+r[1].height, r[2].height+ r[3].height});
		}
		else {
			W = max(new int[] {r[0].width,r[1].width}) + max(new int[] {r[2].width,r[3].width});
			H = max(new int[] {r[0].height+r[1].height, r[2].height+ r[3].height});
		}*/

		if (Math.abs(r[0].width-r[1].width)>= r[2].width - r[3].width && r[2].width > r[3].width) {
			W = r[1].width + r[3].width;
		}
		else {
			W = max(new int[] {r[0].width,r[1].width}) + max(new int[] {r[2].width,r[3].width});
		}

		H = max(new int[] {r[0].height+r[1].height, r[2].height+ r[3].height});

		q.offer(W+" "+H+" "+W*H);


	}

	public static int max(int[] a) {
		Arrays.sort(a);
		return a[a.length-1];
	}

	public static Rectangle reverse(Rectangle r) {
		return new Rectangle(r.height, r.width);
	}

	public static String bin(int i) {
		String out = Integer.toString(i, 2);

		while (out.length() < 4) {
			out = '0'+out;
		}

		return out;
	}

}


class Sorter implements Comparator<String>
{
	@Override
	public int compare(String x, String y)
	{

		StringTokenizer t = new StringTokenizer(x);
		int width = Integer.parseInt(t.nextToken());
		int height = Integer.parseInt(t.nextToken());
		int area = Integer.parseInt(t.nextToken());


		StringTokenizer tok = new StringTokenizer(y);
		int width2 = Integer.parseInt(tok.nextToken());
		int height2 = Integer.parseInt(tok.nextToken());
		int area2 = Integer.parseInt(tok.nextToken());

		if (area < area2)
		{
			return -1;
		}
		else
		{
			return 1;
		}
		/*else if (area == area2) {
			if (width < width2)
				return 1;
			else if (width > width2)
				return -1;
        }
		return 0;*/
	}
}


