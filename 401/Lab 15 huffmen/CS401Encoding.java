import java.util.*;

public class CS401Encoding {
	 public final static int SIZE = 256;

	    protected Entry [ ] leafEntries;

	    protected CS401PriorityQueue<Entry> pq;


	    /**
	     *  Initializes this Huffman object.
	     *
	     */
	     public CS401Encoding()
	     {
	         Entry entry;

	         leafEntries = new Entry [SIZE];
	         for (int i = 0; i < SIZE; i++)
	         {
	              leafEntries [i] = new Entry();
	              entry = leafEntries [i];
	              entry.freq = 0;
	              entry.left = null;
	              entry.right = null;
	              entry.parent = null;
	         } // initializing leafEntries

	         pq = new CS401PriorityQueue<Entry>();
	     } // default constructor


	     /**
	      *  Updates the frequencies of the characters in a specified line.
	      *
	      *  @param line - the line that holds the characters whose
	      *                frequencies are to be updated.
	      *  @return - a cumulative array of type Entry in which the frequencies have been
	      *            updated for each character in the line.
	      *
	      */
	     public Entry[ ] updateFrequencies (String line)
	     {
	         Entry entry;

	         for (int j = 0; j < line.length(); j++)
	         {
	              entry = leafEntries [(int)(line.charAt (j))];
	              entry.freq++;
	         } // for

	         // Account for the end-of-line marker:

	         return leafEntries;
	     } // method updateFrequencies


	     /**
	      *  Creates the priority queue from the frequencies.
	      *
	      *  @return - the priority queue of frequencies (in increasing order).
	      *
	      */
	     public CS401PriorityQueue<Entry> createPQ()
	     {
	         Entry entry;

	         for (int i = 0; i < SIZE; i++)
	         {
	              entry = leafEntries [i];
	              if (entry.freq > 0){
	                  pq.add (entry);
	                 //System.out.println(entry.freq);
	              }
	         } // for
	         return pq;
	     } // createPQ


	     /**
	      *  Creates the Huffman tree.
	      *
	      *  @return - an Entry representing the root of the Huffman tree.
	      *
	      */
	     public Entry createHuffmanTree()
	     {
	         Entry left,
	               right,
	               sum;

	         while (pq.size() > 1)
	         {
	              left = pq.remove();
	              left.code = "0";

	              right = pq.remove();
	              right.code = "1";

	              sum = new Entry();
	              sum.parent = null;
	              sum.freq = left.freq  + right.freq;
	              sum.left = left;
	              sum.right = right;
	              left.parent = sum;
	              right.parent = sum;

	              pq.add (sum);
	         } // while
	         return pq.element();
	     } // method createHuffmanTree


	     /**
	      *  Calculates the Huffman codes.
	      *
	      *  @return - an array of type Entry, with the Huffman code
	      *            for each character.
	      *
	      */
	     public Entry[ ] calculateHuffmanCodes()
	     {
	         String code;

	         Entry entry;

	         for (int i = 0; i < SIZE; i++)
	         {
	              code = "";
	              entry = leafEntries [i];
	              if (entry.freq > 0)
	              {
	                  while (entry.parent != null)
	                  {
	                      code = entry.code + code;  // current bit prepended to
	                      entry = entry.parent;      // code as we go up the tree
	                  } // while
	                  leafEntries [i].code = code;
	              } // if
	         } // for
	         return leafEntries;
	     } // calculateHuffmanCodes


	     /**
	      *  Returns, as a String object, the characters and their Huffman codes.
	      *
	      *  @return the characters and their Huffman codes.
	      *
	      */
	     public String getCodes()
	     {
	         Entry entry;

	         String codes = new String();

	         for (int i = 0; i < SIZE; i++)
	         {
	              entry = leafEntries [i];
	              if (entry.freq > 0)
	                  codes += (char)i + " " + entry.code + "\n";
	              	  //System.out.println("code:\t"+i+"\tentry code :"+entry.code);}
	         } // for
					 System.out.println(codes);
	         return codes;
	     } // method getCodes


	     /**
	      *  Returns a String representation of the encoding of a specified line.
	      *
	      *  @param line - the line whose encoding is returned.
	      *
	      *  @return a String representation of the encoding of line.
	      *
	      */
	     public String getEncodedLine (String line)
	     {
	         Entry entry;

	         String encodedLine = new String();

	         for (int j = 0; j < line.length(); j++)
	         {
	              entry = leafEntries [(int)(line.charAt (j))];
	              encodedLine += entry.code;
	         } // for

	         return encodedLine;
	     } // method getEncodedLine
}
