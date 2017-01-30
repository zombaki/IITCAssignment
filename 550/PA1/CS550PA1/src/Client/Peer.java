package Client;

//IMPORT PACKAGES
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import Interface.IInPeerIF;
import Interface.IInserver;


/***********************************************************************************************************************************
 * Class Name : Peer - This is the fundamental main class for peer which takes care for initiating server ,which can be used by other peer to 
 * 					Download file from this peer.
 * 				It has the main execution block which will invoke three threads.
 * 			Thread 1 : To start Peer as a server for letting other Peer to download file.
 * 			Thread 2 : To run the execution part for peer to take action according to the user input.
 * 			Thread 3 : To run a async process to update server periodically file values to server.so that server is constantly updated with files 
 * 						which are there with the peer.
 * @author Piyush and Priyanka
 *
 */
public class Peer {
	private static Integer intPort;//Peer port number which is passed at time of execution
	  static HashMap<String,PeerDetail> p = new HashMap<String,PeerDetail>(); //HASH MAP TO PUSH VALUES TO SERVER
	  static String serverPort;//SERVER PORT NUMBER
	  static String clientPort;//CLIENT PORT NUMBER
	  static String serverLocation;//SERVER LOCATION "LOCALHOST"
	  static String peerName;//PEER NAME ,WHICH IS FEEDED BY THE USER
	  /*********************************************************************************************************************
	   * Method name main : This is the entry point of the code where we execute all the three threads mentioned above in class.
	   * @param args
	   * args 0 :  	server location ,which is kept as localhost for this assignment.
	   * args 1 :	server port number, this is the port number on which rmi is running.
	   * args 2 :	client port number, where we are expecting to run client server,so that other system can communicate using this port. 
	   */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		clientPort=args[2];
		serverPort=args[1];
		serverLocation=args[0];
		//WHILE LOOP WILL RUN UNTIL A PEER NAME IS NOT GIVEN BY THE USER.
        do{
        System.out.println("Please Enter Your Peer Name.");
        peerName = System.console().readLine();
        }while(peerName.isEmpty());
        //THREAD TO MAKE PEER AS SERVER FOR DOWNLOAD FUNCTIONALITY
		Thread tServer = new Thread(new PeerServer());
		tServer.start();
		//THREAD TO RUN CLIENT SERVER INTERACTION
        Thread tClient = new Thread(new PeerClient());
        tClient.start();
        //THREAD TO RUN CONSTANT UPDATE TO SERVER
        Thread uServer = new Thread(new PeerUpdate());
		uServer.start();
		
	}
	/*******************************************************************************************************************************
	 * Method :FetchFile : This method is used by the peer to connect with other peer from which files need to be fetched. In this 
	 * 						method we have to do the connection with the other peer.
	 * @param peer : This is the peer details which is selected by the user from whome the files need to be pulled.
	 * @throws IOException
	 */
	private static void fetchFile(PeerDetail peer) throws IOException{
		//iNFO TO USER AS THE FILES ARE BEING FETCHED
		//System.out.println("\t Fetching file from Peer \t:"+peer.getPeerId());
		//INITIALIZED THE PORT NUMBER OF THE SOURCE PEER
		String clientName = "rmi://localhost:"+peer.getPortNo()+"/peerImp";
    	IInPeerIF compLocal;
		try {
			compLocal = (IInPeerIF) Naming.lookup(clientName);//THIS WILL STABLISH THE CONNECTION WITH THE REMOTE PEER TO FETCH THE FILE
			//CREATED A LOCAL FOLDER WHERE WE WANT TO CREATE THE SAME FILE WHICH WE WANT TO COPY AND GIVE THE FILE NAME AS SAME FILE NAME WHICH IS REQUIRED.
			FileOutputStream fos=new FileOutputStream(System.getProperty("user.dir")+"/"+peerName+"/"+peer.getFileName());
			//BELOW WE WILL GET BITWISE OUTPUT FROM THE PEER WITH THE FILE NAME
			byte[] btFile=compLocal.downloadFile(peer.getFileName(),peer.getPeerId());
			fos.write(btFile);	//WRITE THE CONTENT TO NEWLY CREATED FILE
	        fos.close();
        	System.out.println("successfully downloaded");
        	//CATCH BLOCK
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
	}
/*********************************************************************************************************************************
 * Class name : PeerClient : This class has the major functionality to do the necessary actions at peer end,that is interaction of peer and server
 * 							where we do necessary actions like Register for files, lookup unregister files and can exit the peer.
 * 			There it expects 4 actions based on users input.(it is running in th thread)
 * 
 * 1.Register files with indexing server.	-- Register all the files present in the client with folder name as peerid(if no peerid folder is
 * 												there then system will create the folder and  nevigate to move files to that folder manually.
 * 2.Lookup for a file at index server.		--	System will ask user to key in different files which need to be searched in server,which will return to
 * 												list of files. based on user selection we will download the files to requesting peer's folder.
 * 3.Un-register all files of this peer from the indexing server. -- this  will delink the server from peer details.
 * 4.Exit.									--	This will auto delink ther peer from server as well  shut the process down.
 * @author Piyush and Priyanka
 *
 */
private static class PeerClient implements Runnable  {
		
        
        // Services this thread's peer client by sending the requested file.
		public void run() {
			intPort=Integer.parseInt(clientPort);
			try {
				/******
				 * Code to run thread in background for 
				 */
	          

	            boolean flgLoopEnable=true;//LOOP ENABLED WILL MAKE WHILE LOOP RUN UNTIL THIS FLAG IS TURNED TO FALSE.
	            String name = "indexServImpl";//SERVER REGISTRY ENTRY
	            Registry registry = LocateRegistry.getRegistry(serverLocation,Integer.parseInt(serverPort));//THIS CODE WILL SEARCH FO SERVER LOCATION'S PORT
	            IInserver comp = (IInserver) registry.lookup(name);//LOOKUP FOR THE REGISTRY ENTRY AND CONVERT TO INTERFACE SO THAT WE CAN CONSUME SERVER'S METHOD
	            String fileName;
	            do{
	            	//DIFFERENT ACTIONS WHCIH CAN BE DONE BY THE PEER,METHIONED IN DETAIL ABOVE.
					System.out.println("\nWhat do you want to do?");
			        System.out.println("1.Register files with indexing server.");
			        System.out.println("2.Lookup for a file at index server.");
			        System.out.println("3.Un-register all files of this peer from the indexing server.");
			        System.out.println("4.Exit.");
			        
			        System.out.print("Enter choice and press ENTER:");
			        int option=0;
			        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			        // Check if the user has entered only numbers.
			        try {
			        	option = Integer.parseInt(input.readLine());//USER INPUT
					} catch (NumberFormatException e) {
						System.out.println("Wrong choice. Try again!!!");
						
					}
			        
			        switch(option){
			        case 1://WHEN USER TRIES TO REGISTER FILES TO SERVER
			        	
			        	//System.out.println(peerName);
						File theDir = new File(peerName);//WE WRITE THE FILE NAME TO SEARCH FOR FOLDER IF IT EXISITS.
						if (!theDir.exists()){//TO CHECK IF THE DIRECTORY IS THERE OR NOT
							System.out.println("Creating Folder for file -Please put files there and try again."+peerName);
							theDir.mkdir();
						}
						File[] files = new  File(System.getProperty("user.dir")+"/" +peerName).listFiles();//FETCH ALL THE FILES IN THE FOLDER AS LIST
						//GET DIFFERENT INFORMATION THAT NEEDED TO BE SENT TO SERVER
						String port = clientPort;
						String hostIPAddress = InetAddress.getLocalHost().getHostAddress();
						ArrayList<PeerDetail> fileList= new ArrayList<PeerDetail>();								
						HashMap<String,ArrayList<PeerDetail>> mapFileList = new HashMap<String,ArrayList<PeerDetail>>();
						
						for (File f : files) {
						    if (f.isFile()) {
								//MOVE FILE TO LIST WITH CONTAINER CLASS AND ADD TO FILE LIST
						    	PeerDetail newPeer = new PeerDetail(peerName,port,f.getName(),hostIPAddress);
								fileList.add(newPeer);
						    }
						}
						//ADD FILE LIST TO MAPFILE SO THAT MAPFILE CAN BE SENT TO SERVER		
						mapFileList.put(peerName,fileList);		
						//IF MAPFILE IS NOT EMPTY WE WILL PUSH THE CREATED FILE TO SERVER USING REGISTER METHOD 
						if(mapFileList.size() >0){		
							String serverReply = comp.register(peerName,mapFileList);		
							System.out.println("Server Reply:" + serverReply);			
						}		
														
			        	
						
						break;
			        case 2: //WHEN USER TRIES TO SEARCH FILE FROM SERVER
						System.out.println("Enter the file name to search");
						fileName = System.console().readLine();
						//ADD THE INPUT GIVEN BY  USER AS FILENAME AND CALL LOOKUPFILE TO FETCH THE OUTPUT RETURNED BY THE SERVER
						ArrayList<PeerDetail> lookUpPeer = comp.lookupFile(peerName,fileName);
						if (lookUpPeer.isEmpty())//IF NO FILE IS FOUND
							System.out.println("File Not Found");
						else{//IF THERE ARE FILES RETURNED BY THE SERVER
							int i=0;
							//WE WILL GIVE OPTION TO CLIENT TO ENTER THE FILE THAT NEED TO BE DOWNLOADED
							for(PeerDetail p: lookUpPeer){
								i++;
								System.out.print("\n"+i+")\t Peer Name: " + p.getPeerId());
								System.out.print("\t Port Number: " + p.getPortNo());
								System.out.print("\t File Name: " + p.getFileName());
							} 
							System.out.print("\nPlease select the file you want to download :\t");
							option = Integer.parseInt(input.readLine());
						}
						
					        // Check if the user has entered only numbers.
						option=0;
						//WE TAKE USER INPUT FOR THE FILE THAT NEED TO BE DOWNLOADED
					        try {
					        	
					        	if (option >lookUpPeer.size() || option==0) //VALIDATION TO NOT TO INCLUDE OTHER OPTION
					        		System.out.println("Please Enter Option within given range.");
					        	else
					        	{
					        		//Logic to download code.
					        		fetchFile(lookUpPeer.get(option-1));
					        	}
							} catch (NumberFormatException e) {
								System.out.println("Wrong choice. Try again!!!");
								
							}
					        
			        	break;
			        case 3://TO UNREGISTER PEER FROM SERVER.
			        	if (comp.unregister(peerName)){
			        		peerName=null;
			        		System.out.println("Successfully deregistered.");
			        	}
			        	else
			        		System.out.println("Peer data does not exisits in server.");
			        	break;
			        	
			        case 4://TO TERMINATE THE PROGRAM
			        	System.out.println("Closing Process...");
			        	comp.unregister(peerName);//WE FIRST UNREGISTER THE CODE FROM SERVER THEN TERMINATE THE PROCESS
			        	flgLoopEnable=false;//COME OUT OF THE LOOP
			        	break;
			        }
	            }while(flgLoopEnable);
	            
	            System.exit(0);//TERMINATE  PROGRAM
	        } catch (Exception e) {
	            System.err.println("Client side execution exception :");
	            e.printStackTrace();
	        }
			
				
		}


	}
/*********************************************************************************************************************************
 * Class name : PeerServer : This class takes care for the part where client acts as a server.
 * @author Piyush And Priyanka
 *
 */
	private static class PeerServer implements Runnable  {
		
        
        // Services this thread's peer client by sending the requested file.
		public void run() {
			try {
				IInPeerIF peerImp = new PeerImp();
				Naming.rebind("rmi://localhost:"+intPort+"/peerImp", peerImp);//REBIND THE CLIENT SO THAT IT CAN ACT AS A SERVER.
				System.out.println("Client is ready to accept request.....");
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		}
	}
	/**************************************************************************************************************************
	 * Class name : PeerUpdate : This class is used to update peer server periodically,so that server is up to date with the client file status.
	 * @author Piyush and Priyanka
	 *
	 */
	private static class PeerUpdate implements Runnable  {
		///Prev file details
        File[] prev=null;
        // Services this thread's peer client by sending the requested file.
		public void run() {
			try {if (peerName!=null){//to check if peername is not !,so that it will only run if peername is set by the client
				//update server periodically
				do{
					File theDir = new File(peerName);

					File[] files=null;
					if (theDir.exists())//CHECKS IF THE DIR EXISITS 
						files = new  File(System.getProperty("user.dir")+"/" +peerName).listFiles();
		
	            if (files!= null){//IF THERE ARE NO FILES THEN IT WON'T SEND THE UPDATE TO SERVER.
	            	if (prev==null|| !prev.equals(files)){
	            		if (prev!=null){
	            			//UPDATE THE PEER DETAILS
	            			String port = clientPort;
							String hostIPAddress = InetAddress.getLocalHost().getHostAddress();
							String name = "indexServImpl";
							ArrayList<PeerDetail> fileList= new ArrayList<PeerDetail>();
				            Registry registry = LocateRegistry.getRegistry(serverLocation,Integer.parseInt(serverPort));
				            IInserver comp = (IInserver) registry.lookup(name);
			            	for (File f : files) {
							    if (f.isFile()) {
							
									PeerDetail newPeer = new PeerDetail(peerName,port,f.getName(),hostIPAddress);
									//CREATE  FILE LIST
										fileList.add(newPeer);
									
							    }
							}
			            	HashMap<String,ArrayList<PeerDetail>> mapFileList = new HashMap<String,ArrayList<PeerDetail>>();
							mapFileList.put(peerName,fileList);		
							if(mapFileList.size() >0){		
								String serverReply = comp.register(peerName,mapFileList);	//PUSH THE FILE LIST TO SERVER	
								//System.out.println("Server Reply:" + serverReply);			
							}		
	            		}
	            	}
	            }
				prev=files;
				Thread.sleep(100000);//SLEEP THE THREAD SO THAT IT WILL RUN THREAD WITH PERIODIC OF 100000 ms
				}while(true);}
			} catch (RemoteException e) {
				e.printStackTrace();
			}catch (NotBoundException e) {
				e.printStackTrace();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}


	}
}

