import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;





/*************************************************************************
 *  Compilation:  javac Graph.java
 *  Dependencies: ST.java SET.java In.java
 *  
 *  Undirected graph data type implemented using a symbol table
 *  whose keys are vertices (String) and whose values are sets
 *  of neighbors (SET of Strings).
 *
 *  Remarks
 *  -------
 *   - Parallel edges are not allowed
 *   - Self-loop are allowed
 *   - Adjacency lists store many different copies of the same
 *     String. You can use less memory by interning the strings.
 *
 *************************************************************************/

/**
 *  The <tt>Graph</tt> class represents an undirected graph of vertices
 *  with string names.
 *  It supports the following operations: add an edge, add a vertex,
 *  get all of the vertices, iterate over all of the neighbors adjacent
 *  to a vertex, is there a vertex, is there an edge between two vertices.
 *  Self-loops are permitted; parallel edges are discarded.
 *  <p>
 *  For additional documentation, see <a href="http://introcs.cs.princeton.edu/45graph">Section 4.5</a> of
 *  <i>Introduction to Programming in Java: An Interdisciplinary Approach</i> by Robert Sedgewick and Kevin Wayne.
 */

/*************************************************************************
 *  Compilation:  javac SET.java
 *  Execution:    java SET
 *  
 *  Set implementation using Java's TreeSet library.
 *  Does not allow duplicates.
 *
 *  % java SET
 *  128.112.136.11
 *  208.216.181.15
 *  null
 *
 *************************************************************************/

import java.util.TreeSet;
import java.util.Iterator;
import java.util.SortedSet;



/**
 *  The <tt>SET</tt> class represents an ordered set. It assumes that
 *  the elements are <tt>Comparable</tt>.
 *  It supports the usual <em>add</em>, <em>contains</em>, and <em>delete</em>
 *  methods. It also provides ordered methods for finding the <em>minimum</em>,
 *  <em>maximum</em>, <em>floor</em>, and <em>ceiling</em>.
 *  <p>
 *  This implementation uses a balanced binary search tree.
 *  The <em>add</em>, <em>contains</em>, <em>delete</em>, <em>minimum</em>,
 *  <em>maximum</em>, <em>ceiling</em>, and <em>floor</em> methods take
 *  logarithmic time.
 *  <p>
 *  For additional documentation, see <a href="http://introcs.cs.princeton.edu/44st">Section 4.4</a> of
 *  <i>Introduction to Programming in Java: An Interdisciplinary Approach</i> by Robert Sedgewick and Kevin Wayne.
 *  */



/*************************************************************************
 *  Compilation:  javac ST.java
 *  Execution:    java ST
 *  
 *  Sorted symbol table implementation using a java.util.TreeMap.
 *  Does not allow duplicate keys.
 *
 *  % java ST
 *
 *************************************************************************/

import java.util.SortedMap;
import java.util.TreeMap;

/**
 *  This class represents an ordered symbol table. It assumes that
 *  the elements are <tt>Comparable</tt>.
 *  It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 *  and <em>delete</em> methods.
 *  It also provides ordered methods for finding the <em>minimum</em>,
 *  <em>maximum</em>, <em>floor</em>, and <em>ceiling</em>.
 *  <p>
 *  The class uses the convention that values cannot be null. Setting the
 *  value associated with a key to null is equivalent to removing the key.
 *  <p>
 *  This implementation uses a balanced binary search tree.
 *  The <em>add</em>, <em>contains</em>, <em>delete</em>, <em>minimum</em>,
 *  <em>maximum</em>, <em>ceiling</em>, and <em>floor</em> methods take
 *  logarithmic time.
 *  <p>
 *  For additional documentation, see <a href="http://introcs.cs.princeton.edu/44st">Section 4.4</a> of
 *  <i>Introduction to Programming in Java: An Interdisciplinary Approach</i> by Robert Sedgewick and Kevin Wayne. 
 *
 */
class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    private TreeMap<Key, Value> st;

    /**
     * Create an empty symbol table.
     */
    public ST() {
        st = new TreeMap<Key, Value>();
    }

    /**
     * Put key-value pair into the symbol table. Remove key from table if
     * value is null.
     */
    public void put(Key key, Value val) {
        if (val == null) st.remove(key);
        else             st.put(key, val);
    }

    /**
     * Return the value paired with given key; null if key is not in table.
     */
    public Value get(Key key) {
        return st.get(key);
    }

    /**
     * Delete the key (and paired value) from table.
     * Return the value paired with given key; null if key is not in table.
     */
    public Value delete(Key key) {
        return st.remove(key);
    }

    /**
     * Is the key in the table?
     */
    public boolean contains(Key key) {
        return st.containsKey(key);
    }

    /**
     * How many keys are in the table?
     */
    public int size() {
        return st.size();
    }

    /**
     * Return an <tt>Iterator</tt> for the keys in the table.
     * To iterate over all of the keys in the symbol table <tt>st</tt>, use the
     * foreach notation: <tt>for (Key key : st)</tt>.
     */ 
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }


    /**
     * Return an <tt>Iterable</tt> for the keys in the table.
     * To iterate over all of the keys in the symbol table <tt>st</tt>, use the
     * foreach notation: <tt>for (Key key : st.keys())</tt>.
     */ 
    public Iterable<Key> keys() {
        return st.keySet();
    }

    /**
     * Return the smallest key in the table.
     */ 
    public Key min() {
        return st.firstKey();
    }

    /**
     * Return the largest key in the table.
     */ 
    public Key max() {
        return st.lastKey();
    }


    /**
     * Return the smallest key in the table >= k.
     */ 
    public Key ceil(Key k) {
        SortedMap<Key, Value> tail = st.tailMap(k);
        if (tail.isEmpty()) return null;
        else return tail.firstKey();
    }

    /**
     * Return the largest key in the table <= k.
     */ 
    public Key floor(Key k) {
        if (st.containsKey(k)) return k;

        // does not include key if present (!)
        SortedMap<Key, Value> head = st.headMap(k);
        if (head.isEmpty()) return null;
        else return head.lastKey();
    }

}

class SET<Key extends Comparable<Key>> implements Iterable<Key> {
    private TreeSet<Key> set;

    /**
     * Create an empty set.
     */
    public SET() {
        set = new TreeSet<Key>();
    }

    /**
     * Is this set empty?
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }
 
    /**
     * Add the key to this set.
     */
    public void add(Key key) {
        set.add(key);
    }

    /**
     * Does this set contain the given key?
     */
    public boolean contains(Key key) {
        return set.contains(key);
    }

    /**
     * Delete the given key from this set.
     */
    public void delete(Key key) {
        set.remove(key);
    }

    /**
     * Return the number of keys in this set.
     */
    public int size() {
        return set.size();
    }

    /**
     * Return an Iterator for this set.
     */
    public Iterator<Key> iterator() {
        return set.iterator();
    }

    /**
     * Return the key in this set with the maximum value.
     */
    public Key max() {
        return set.last();
    }

    /**
     * Return the key in this set with the minimum value.
     */
    public Key min() {
        return set.first();
    }

    /**
     * Return the smallest key in this set >= k.
     */
    public Key ceil(Key k) {
        SortedSet<Key> tail = set.tailSet(k);
        if (tail.isEmpty()) return null;
        else return tail.first();
    }

    /**
     * Return the largest key in this set <= k.
     */
    public Key floor(Key k) {
        if (set.contains(k)) return k;

        // does not include key if present (!)
        SortedSet<Key> head = set.headSet(k);
        if (head.isEmpty()) return null;
        else return head.last();
    }

    /**
     * Return the union of this set with that set.
     */
    public SET<Key> union(SET<Key> that) {
        SET<Key> c = new SET<Key>();
        for (Key x : this) { c.add(x); }
        for (Key x : that) { c.add(x); }
        return c;
    }

    /**
     * Return the intersection of this set with that set.
     */
    public SET<Key> intersects(SET<Key> that) {
        SET<Key> c = new SET<Key>();
        if (this.size() < that.size()) {
            for (Key x : this) {
                if (that.contains(x)) c.add(x);
            }
        }
        else {
            for (Key x : that) {
                if (this.contains(x)) c.add(x);
            }
        }
        return c;
    }

   

}

class Graph {

    // symbol table: key = string vertex, value = set of neighboring vertices
    private ST<String, SET<String>> st;

    // number of edges
    private int E;

   /**
     * Create an empty graph with no vertices or edges.
     */
    public Graph() {
        st = new ST<String, SET<String>>();
    }

   /**
     * Create an graph from given input stream using given delimiter.
     */
    

   /**
     * Number of vertices.
     */
    public int V() {
        return st.size();
    }

   /**
     * Number of edges.
     */
    public int E() {
        return E;
    }

   /**
     * Degree of this vertex.
     */
    public int degree(String v) {
        if (!st.contains(v)) throw new RuntimeException(v + " is not a vertex");
        else return st.get(v).size();
    }

   /**
     * Add edge v-w to this graph (if it is not already an edge)
     */
    public void addEdge(String v, String w) {
        if (!hasEdge(v, w)) E++;
        if (!hasVertex(v)) addVertex(v);
        if (!hasVertex(w)) addVertex(w);
        st.get(v).add(w);
        st.get(w).add(v);
    }

   /**
     * Add vertex v to this graph (if it is not already a vertex)
     */
    public void addVertex(String v) {
        if (!hasVertex(v)) st.put(v, new SET<String>());
    }


   /**
     * Return the set of vertices as an Iterable.
     */
    public Iterable<String> vertices() {
        return st;
    }

   /**
     * Return the set of neighbors of vertex v as in Iterable.
     */
    public Iterable<String> adjacentTo(String v) {
        // return empty set if vertex isn't in graph
        if (!hasVertex(v)) return new SET<String>();
        else               return st.get(v);
    }

   /**
     * Is v a vertex in this graph?
     */
    public boolean hasVertex(String v) {
        return st.contains(v);
    }

   /**
     * Is v-w an edge in this graph?
     */
    public boolean hasEdge(String v, String w) {
        if (!hasVertex(v)) return false;
        return st.get(v).contains(w);
    }

   /**
     * Return a string representation of the graph.
     */
    public String toString() {
        String s = "";
        for (String v : st) {
            s += v + ": ";
            for (String w : st.get(v)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }


}


/*************************************************************************
 *  Compilation:  javac AllPaths.java
 *  Execution:    java AllPaths
 *  Depedencies:  Graph.java
 *  
 *  Find all paths from s to t.
 *  
 *  % java AllPaths
 *  A: B C 
 *  B: A F 
 *  C: A D F 
 *  D: C E F G 
 *  E: D G 
 *  F: B C D 
 *  G: D E 
 * 
 *  [A, B, F, C, D, E, G]
 *  [A, B, F, C, D, G]
 *  [A, B, F, D, E, G]
 *  [A, B, F, D, G]
 *  [A, C, D, E, G]
 *  [A, C, D, G]
 *  [A, C, F, D, E, G]
 *  [A, C, F, D, G]
 *
 *  [B, A, C, D, F]
 *  [B, A, C, F]
 *  [B, F]
 *
 *  Remarks
 *  --------
 *   -  Currently prints in reverse order due to stack toString()
 *
 *************************************************************************/

class AllPaths<Vertex> {

    private static Stack<String> path  = new Stack<String>();   // the current path
    private static SET<String> onPath  = new SET<String>();     // the set of vertices on the path
    private static ArrayList<String[]> entries = new ArrayList<String[]>();
    

    // use DFS
    public static ArrayList<String[]> enumerate(Graph G, String v, String t) {

        // add node v to current path from s
        path.push(v);
        onPath.add(v);

        // found path from s to t - currently prints in reverse order because of stack
        if (v.equals(t)) {
        	String[] array = new String[path.size()];
        	for(int i = 0; i < path.size(); i++) array[i] = path.get(i);
        	entries.add(array);
        }

        // consider all neighbors that would continue path with repeating a node
        else {
            for (String w : G.adjacentTo(v)) {
                if (!onPath.contains(w)) enumerate(G, w, t);
            }
        }

        // done exploring from v, so remove from path
        path.pop();
        onPath.delete(v);
       // System.out.println(entries);
        return entries;
    }


}

public class hshoe {
	
	static Map<String,String> map = new HashMap<String,String>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		
		
		BufferedReader in = new BufferedReader(new FileReader("hshoe.in"));

		PrintStream out = new PrintStream(new File("hshoe.out"));
		
		int dim = Integer.parseInt(in.readLine());
		//System.out.println(dim);
		int c = 1;
		
		for (int i = 0; i < dim; i++) {
			for (int x = 0; x < dim; x++) {
				map.put(Integer.toString(c), Character.toString((char)in.read()));
				c++;
			}
			in.readLine();
		}
		
		if (map.get("1").equals(")")) {
        	out.println(0);
        	System.exit(0);
        }
		//System.out.println(map.toString());
		//System.out.println(7/4);
		Graph G = new Graph();
		
		for (int i = 1; i <= dim*dim; i++) {
			if (i+1 <= ((i/dim)+1)*dim && i%dim!=0)
				G.addEdge(Integer.toString(i),Integer.toString(i+1));
			if (i-1 >= ((int)(i)/dim)*dim+1)
				G.addEdge(Integer.toString(i),Integer.toString(i-1));
			if (i+dim <= dim*dim)
				G.addEdge(Integer.toString(i),Integer.toString(i+dim));
			if (i-dim >= 1)
				G.addEdge(Integer.toString(i),Integer.toString(i-dim));
		}
		
       // System.out.println(G);
        ArrayList<Integer> len = new ArrayList<Integer>();
        ArrayList<String[]> m;
        for (int i = 2; i <= dim*dim; i++) {
        	if (map.get(Integer.toString(i)).equals(")")) {
        		m = AllPaths.enumerate(G, "1", Integer.toString(i));
        		System.out.println(m.get(0));
        		len.add(compute(m));
        	}
        }
        
        int[] leng = new int[len.size()];
        for (int i = 0; i < len.size(); i++) {
        	leng[i] = len.get(i);
        }
        Arrays.sort(leng);
        //System.out.println(Arrays.toString(leng));
        out.println(leng[leng.length-1]);
        
        out.close();
        in.close();
        
		//AllPaths allpaths1 = new AllPaths(G, "1", "3");
        
	}
	
	public static int compute(ArrayList<String[]> a) {
		int[] len = new int[a.size()];
		for (int i = 0; i < a.size(); i++) {
			//System.out.println(Arrays.toString(a.get(i)));
			if (beatiful(a.get(i))) {
				len[i] = a.get(i).length;
			}
			else {
				len[i] = 0;
			}
			
			//System.out.println(len[i]);
		}
		
		Arrays.sort(len);
		//System.out.println(len[len.length-1]);
		return len[len.length-1];
	}
	
	public static boolean beatiful(String[] a) {
		if (a.length%2!=0 || a.length <2)
			return false;
		String d = "";
		for (int i  = 0; i < a.length; i++) {
			d += map.get(a[i]);
		}
		String first = d.substring(0, d.length()/2);
		String second = d.substring(d.length()/2);
		
		for (int i = 0; i < first.length(); i++) {
			if (first.charAt(i) != '(')
				return false;
			if (second.charAt(i) != ')')
				return false;
		}
		
		return true;
		
		
		
		
		
		//bad version
		/*Stack<String> s = new Stack<String>();
		boolean is = false;
		for (int i  = 0; i < a.length; i++) {
			if (map.get(a[i]).equals("(")) {
				is = true;
				s.push("(");
			}
			else {
				if (!s.empty())
					s.pop();
				else
					return false;
			}
		}
		if (s.empty() && is)
			return true;
		else
			return false;*/
		
	}

}