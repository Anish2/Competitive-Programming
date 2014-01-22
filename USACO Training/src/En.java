import java.util.ArrayList;
import java.util.HashMap;


public class En {

	public static void main(String[] args) {



		String[] n = {"AXE", "TOOTH", "BANANA", "PEEPS", "APPLE"};

		//System.out.println("test");

		//System.out.println(filter(n, "XJJXI"));

		for (String s: filter(n, "XJJXI"))
			System.out.println(s);

		System.out.println(totalValue("IX"));

	}


	public static ArrayList<String> filter(String[] words, String key) {

		ArrayList<String> good = new ArrayList<String>();

		HashMap<Character, Character> m = new HashMap<Character, Character>();

		for (int i = 0; i < words.length; i++) {

			if (words[i].length() == key.length()) {

				//System.out.println("good");

				for (int x = 0; x < key.length(); x++) {

					m.put(key.charAt(x), words[i].charAt(x));

				}

				String make = "";

				for (int x = 0; x < key.length(); x++) {

					make += m.get(key.charAt(x));

				}

				if (make.equals(words[i])) {
					//System.out.println(make);
					good.add(words[i]);
				}

			}

			m.clear();


		}


		return good;



	}

	public static int calc(String s) {

		int fin = 0;
		ArrayList<Integer> a = new ArrayList<Integer>();

		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) == 'Z')
				a.add(1);
			else if (s.charAt(i) == 'Y')
				a.add(5);
			else
				a.add(10);

		}


		int i = 0;

		while (a.get(i) == 10) {

			fin += a.get(i);
			i++;

		}

		fin += rest(i, a);

		return fin;



	}

	public  static int totalValue(String val)
	{
		String aux=val.toUpperCase();
		int sum=0, max=aux.length(), i=0;
		while(i<max)
		{
			if ((i+1)<max && valueOf(aux.charAt(i+1))>valueOf(aux.charAt(i)))
			{
				sum+=valueOf(aux.charAt(i+1)) - valueOf(aux.charAt(i));
				i+=2;
			}
			else
			{
				sum+=valueOf(aux.charAt(i));
				i+=1;
			}
		}
		return sum;
	}

	public static int valueOf(Character c)
	{
		char aux = Character.toUpperCase(c);
		switch(aux)
		{
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			return 0;
		}
	}

	public static int rest(int i, ArrayList<Integer> a) {

		int ones = 0;
		int fin = 0;

		if (a.get(i) == 5) {
			fin += 5;
			i++;
			while (a.get(i) == 1 && i <= a.size()-1) {
				ones++;
				i++;
			}
			fin += ones;
		}

		else {

			while (a.get(i) == 1 && i <= a.size()-1) {
				ones++;
				i++;
			}
			if (i == a.size()-1)
				fin += ones;
			else {
				fin = a.get(a.size()-1)-1;
			}

		}

		return fin;

	}

}
