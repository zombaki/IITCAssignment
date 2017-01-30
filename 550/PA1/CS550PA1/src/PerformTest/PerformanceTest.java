package PerformTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;

import Client.PeerDetail;
import Interface.IInserver;
/*****************************************************************************************************************************************
 * Class PerformanceTest This is the test stub created for solly testing the performance of the system. This is broadly categorized in two part:-
 * 		1.Register Data for testing -- To register the test data to server.
 * 		2.Lookup data -- this is to test the performance for lookup data functionality
 * @author piyush and Priynanka
 *
 */
public class PerformanceTest {
	private final static int TEST_COUNT = 1000;// SET TEST COUNT AS 1000 FOR TESTING PURPOSE
	  static String serverPort;//SERVER PORT
	  static String clientPort;//CLIENT PORT
	  static String serverLocation;//SERVER LOCATION "LOCALHOST"
	  static String peerName;//PEER NAME
	public static void main(String[] args) {
		clientPort=args[2];//WE PASS CLIENT PORT AS  SECOND PARAMETER 
		serverPort=args[1];//SERVER PORT AS FIRST PARA
		peerName=args[0];//AND PEER NAME
		//WE EXECUTE OUT TESTING FUNCTION IN A THREAD
		Thread tPer = new Thread(new PeerPerformanceTest());
		tPer.start();

	}
	/***************************************************************************************
	 * Class PeerPerformTest: This is the main structure for the class which is doing the testing part
	 * @author Piyush and Priyanka
	 *
	 */
	private static class PeerPerformanceTest implements Runnable  {
		public void run() {
			// TODO Auto-generated method stub
	
		BufferedReader input = null;
       
		try {
			input = new BufferedReader(new InputStreamReader(System.in));
			String name = "indexServImpl";//SERVER  REGISTER NAME FOR LOOKUP
            Registry registry = LocateRegistry.getRegistry("localhost",Integer.parseInt(serverPort));//WE CONNECT TO THE SERVER'S REGISTRY
            IInserver comp = (IInserver) registry.lookup(name);//LOOKUP FOR THE OBJECT FROM SERVER
            String fileName;
			// Display Test options
			System.out.println("\nWhich Part need to be tested tested?");
			System.out.println("1.Register Data for testing");
			System.out.println("2.Lookup data");
			System.out.print("Enter choice and press ENTER:");
			int option = 0;

			// Check if the user has entered only numbers.
			try {
				option = Integer.parseInt(input.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Wrong choice. Try again!!!");
				System.exit(0);
			}
			
			switch (option){
				case 1://FOR MOVING DATA TO SERVER
					System.out.println("Moving data to server...");
					File[] files = new  File(System.getProperty("user.dir")+"/"+peerName).listFiles();
					String port = "5001";//INITIALIZED PORT AS 5001 FOR PEER1
					String hostIPAddress = InetAddress.getLocalHost().getHostAddress();
					ArrayList<PeerDetail> fileList= new ArrayList<PeerDetail>();								
					HashMap<String,ArrayList<PeerDetail>> mapFileList = new HashMap<String,ArrayList<PeerDetail>>();
					for (int i=0;i<TEST_COUNT;i++){
						fileName ="test"+Integer.toString(i)+".txt";
						
					}
					//WE ARE CREATING FILES TO PEER LOCATION FOR TESTING PURPOSE. 100 FILES,THOUGH THESE COUNT CAN BE INCREATED
					for (int i =1 ;i<100;i++)
					{
						File file= new File(System.getProperty("user.dir")+"/"+peerName+"/test"+Integer.toString(i)+".txt");
					
						RandomAccessFile raf = new RandomAccessFile(file, "rw");
						try
						{
						    raf.setLength(5+(int)(Math.random()*(100000-1000)));//WE ARE SETTING THE SIZE OF THESE FILES ON RANDOM
						}
						finally
						{
						    raf.close();
						}
					}
					for (File f : files) {
					    if (f.isFile()) {
					        //FETCHING ALL THE FILES DETAILS IN LIST TO SEND TO SERVER
							PeerDetail newPeer = new PeerDetail(peerName,port,f.getName(),hostIPAddress);
							fileList.add(newPeer);
					    }
					}
					//System.out.println(fileList.size());		
					mapFileList.put(peerName,fileList);		
					if(mapFileList.size() >0){		
						String serverReply = comp.register(peerName,mapFileList);		
						System.out.println("Server Reply:" + serverReply);			
					}
					break;
				case 2://LOOKUP FILE FOR 100O TIME
					long startTime,startTimeFile, endTime,endTimeFile, totalTime = 0,avgTime=0;//INITIALIZED TIME FOR COMPUTING PURPOSE
					System.out.println("Finding Files ...");
					startTime=System.currentTimeMillis();//SET START TIME AS A TIME
					for (int i=0;i<TEST_COUNT;i++){
						fileName ="test"+Integer.toString(i)+".txt";//GIVE DIFFERENT FILE NAME WHILE EXECUTING SEARCH
						startTimeFile = System.currentTimeMillis();
						ArrayList<PeerDetail> lookUpPeer = comp.lookupFile(peerName,fileName);//LOOKUP FOR THE FILE TO BE SEARCHED
						endTimeFile = System.currentTimeMillis();
						if (lookUpPeer.isEmpty())
							System.out.println("File Not Found");
						else{
							System.out.print("size of peerlist"+lookUpPeer.size());
							int iLocal=0;
							for(PeerDetail p: lookUpPeer){
								iLocal++;
								System.out.print("\n"+iLocal+")\t Peer Name: " + p.getPeerId());
								System.out.print("\t Port Number: " + p.getPortNo());
								System.out.print("\t File Name: " + p.getFileName());
							} 
						}
						avgTime+=(endTimeFile-startTimeFile);//FIND THE TOTAL TIME FOR LOOKUP
					}
					endTime = System.currentTimeMillis();
					totalTime=endTime-startTime;
					avgTime=avgTime/TEST_COUNT;//COMPUTE THE TIME FOR LOOKUP
					System.out.print("Average time for searching " + TEST_COUNT + " avgTime " + avgTime + " Total Time taken is "+ totalTime+".");
				        // Check if the user has entered only numbers.
							        
		        	break;
					
			}
					
		}
		catch (Exception e){
			
		}
		
		}
	}
}
