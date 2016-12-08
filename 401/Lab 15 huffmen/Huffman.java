import java.util.*;
import java.io.*;
public class Huffman {
	 public static void main (String[] args)
	 {
		 String directory=System.getProperty("user.dir");
		 System.out.println(directory);
	        //new CS401HuffmanUser().run();
		//System.out.print (args[0]+args[1]);
		if (args[0].equals("encode")){
				System.out.println(args[2]);
				CS401Encoding huffman = new CS401Encoding();
			  PrintWriter printWriter = null;
				createEncoding (args[1],huffman);
				saveEncodedMessage (printWriter, directory+'/'+args[2],args[1], huffman);
				System.out.println("done");

	    } // method main
			else if(args[0].equals("decode")) {
					saveDecodedMessage(directory+'/'+args[1]);
						System.out.println("done");
			}
		}


	    /**
	     *  Creates the Huffman encoding by scanning over a file to be encoded.
	     *  The worstTime(n) is O(n).
	     *
	     *  @param fileScannera  scanner over the file to be encoded.
	     *  @param huffman an instance of the Huffman class.
	     *
	     * @return - a String consisting of each character and its encoding
	     *
	     */
	    public static String createEncoding (String line, CS401Encoding huffman)
	    {

	        huffman.updateFrequencies (line);
					System.out.println(line);
	        huffman.createPQ();
	        huffman.createHuffmanTree();
	        huffman.calculateHuffmanCodes();
	        return huffman.getCodes();
	    } // method createEncoding

	   /**
	    *  Saves the Huffman codes and the encoded message to a file.
	    *  The worstTime(n) is O(n).
	    *
	    *  @param printWriter - the PrintWriter object that holds the Huffman codes
	    *                      and the encoded message.
	    *  @param inFilePath - the String object that holds the path for the file
	    *                      that contains the original message.
	    *  @param huffmanan instance of the Huffman class.
	    *
	    */
	    public static void saveEncodedMessage (PrintWriter printWriter, String inFilePath,String inputString,
	    		CS401Encoding huffman)
	    {
					//File homedir = new File(System.getProperty("user.home"));
	        try
	        {
						printWriter = new PrintWriter (new FileWriter (inFilePath));
	            printWriter.print (huffman.getCodes());
	            printWriter.println ("**"); // to separate codes from encoded message

	                printWriter.println (huffman.getEncodedLine (inputString));

	            printWriter.close();

	        } // try
	        catch (IOException e)
	        {
	            System.out.println (e);
	        } // catch IOException
	    } // method saveEncodedMessage

		   /**
		    *  Saves the Huffman codes and the encoded message to a file.
		    *  The worstTime(n) is O(n).
		    *
		    *  @param printWriter - the PrintWriter object that holds the Huffman codes
		    *                      and the encoded message.
		    *  @param inFilePath - the String object that holds the path for the file
		    *                      that contains the original message.
		    *  @param huffman  an instance of the Huffman class.
		    *
		    */
		    public static void saveDecodedMessage (String a){
		    	CS401Decoding tree=new CS401Decoding();
		    	//File f = new File("C:/Users/piyus/workspace/CS401Assignment/src/CS401Huffmen/r.txt");
	                Scanner fr = null;

	            try {
	                fr = new Scanner(new File(a));
									String temp=fr.nextLine();
	                while(fr.hasNext()){
	                	if(temp.charAt(0)==' ' && temp.charAt(1)==' ')
		                {
	                		tree.add(' ', temp.trim());
		                }
	                	else
	                        tree.add(temp.charAt(0), temp.substring(1));

	                    //System.out.println(temp);
	                	temp = fr.nextLine();
	                	if(temp.equals("**")){
	                        break;
	                    }


	                }

	                System.out.println(tree.getDecodedMessage(fr.nextLine()));


	            } catch (Exception ex) {
	               System.out.println(ex.getMessage());
	            }
		    }
}
