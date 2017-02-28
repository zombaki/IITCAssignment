package Client;

import java.io.FileOutputStream;
import java.io.IOException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

 


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

public class Peer{
	protected static String intPort;
	public static volatile Queue<MsgDetails> queueBroadcast;
	public static volatile Queue<MsgDetails> queryHitBroadcast;
	public static volatile Queue<String> queue;
	//Keep a track of history as well
	//public static volatile HashMap<String,String> RequestorDetail;
	public static ArrayList<Integer> neighbour;
	public static int localMsgID; 
	public static volatile ArrayList<String> reqtdMsgID;
	static final int maxTTL = 10;
	public static volatile boolean blnSearching;
	static long startTime;//INITIALIZED TIME FOR COMPUTING PURPOSE
	static long endTime;
	protected static void initlaizePeer() {
		//RequestorDetail =  new HashMap<String,String>();
		queueBroadcast= new LinkedList<MsgDetails>();
		queryHitBroadcast = new LinkedList<MsgDetails>();
		queue = new LinkedList<String>();
		neighbour =  new ArrayList<Integer>();
		localMsgID=0;
		reqtdMsgID=new ArrayList<String>();
		blnSearching=false;
	}
	public static void main(String[] args) throws InterruptedException {
        int opt=0;
        intPort=args[0];
        initlaizePeer();
        //THREAD TO MAKE PEER AS SERVER FOR DOWNLOAD FUNCTIONALITY
      	Thread tServer = new Thread(new PeerServer());
      	tServer.start();
      	Thread bServer = new Thread(new PeerBroadCastReq());
      	bServer.start();
        Scanner sc=new Scanner(System.in);
		do{
			System.out.println("\nWhat do you want to do?");
	        System.out.println("1.Register neighbour ports.");
	        System.out.println("2.Lookup for a file in N/W.");
	        System.out.println("3.Show connected peer.");
	        System.out.println("0.Exit.");
			System.out.println("Enter Some value.");
			opt=sc.nextInt();
			switch(opt){
			case 1:
				System.out.println("Enter the Port number (and IP of neighbour)");//Currently taking only the port number
				Integer ab =sc.nextInt();
				System.out.println(ab);
				neighbour.add(ab);
				break;
			case 2:
				System.out.println("Please enter the file which you want to search and download.");
				String strFileName = sc.next();
				for (int i =0 ; i<=200 ;i++){//FOR PERFORMANCE TESTING 
				localMsgID++;
				String MsgID = intPort+"-"+localMsgID;//MSGID format is portnumber with; msgid to make it unique in the network
				String name = "peerImp";
				reqtdMsgID.add(MsgID);
				//blnSearching=true;
				startTime=System.currentTimeMillis();//SET START TIME AS A TIME
				
		            for (int a : neighbour){
		            	//Registry registry;
						try {
							//System.out.println(a+"trying to connect to....");
							String registry = "rmi://localhost:"+a+"/peerImp";
							//registry = LocateRegistry.getRegistry("localhost",a);
							//InClientIF comp = (InClientIF) registry.lookup(name);
							InClientIF comp = (InClientIF) Naming.lookup(registry);
							if (comp.query(MsgID, maxTTL, strFileName))
								System.out.println("Your request is initiated..");
							else
								System.out.println("Some problem with the system, please get in touch with admin..");
							//TimeUnit.SECONDS.sleep(10);//SET 10 seconds as time.
							if (blnSearching){
								System.out.println("Unable to find the files, which is requested by you.");
								blnSearching=false;//blnSearching if true means that search is still going and we didnt found the outputf
							}
						} catch (RemoteException | NotBoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			            
		            }
				}
	            break;
			case 3:
				for (int b : neighbour){
					System.out.println("Connected to -->"+b);
				}
			}
			
		}while(opt!=0);
        
	}
	/*******************************************************************************************************************************
	 * Method :FetchFile : This method is used by the peer to connect with other peer from which files need to be fetched. In this 
	 * 						method we have to do the connection with the other peer.
	 * @param peer : This is the peer details which is selected by the user from whome the files need to be pulled.
	 * @throws IOException
	 */
	private static void fetchFile(int intPortNumber,String fileName) throws IOException{
		//iNFO TO USER AS THE FILES ARE BEING FETCHED
		//System.out.println("\t Fetching file from Peer \t:"+peer.getPeerId());
		//INITIALIZED THE PORT NUMBER OF THE SOURCE PEER
		String clientName = "rmi://localhost:"+intPortNumber+"/peerImp";
    	InClientIF compLocal;
		try {
			compLocal = (InClientIF) Naming.lookup(clientName);//THIS WILL STABLISH THE CONNECTION WITH THE REMOTE PEER TO FETCH THE FILE
			//CREATED A LOCAL FOLDER WHERE WE WANT TO CREATE THE SAME FILE WHICH WE WANT TO COPY AND GIVE THE FILE NAME AS SAME FILE NAME WHICH IS REQUIRED.
			FileOutputStream fos=new FileOutputStream(System.getProperty("user.dir")+"/"+fileName);
			//BELOW WE WILL GET BITWISE OUTPUT FROM THE PEER WITH THE FILE NAME
			byte[] btFile=compLocal.downloadFile(fileName);
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
	 * Class name : PeerServer : This class takes care for the part where client acts as a server.
	 * @author Piyush And Priyanka
	 *
	 */
	private static class PeerServer implements Runnable  {
			
	        
	        // Services this thread's peer client by sending the requested file.
			public void run() {
				try {
					InClientIF peerImp = new PeerImp();
					Naming.rebind("rmi://localhost:"+intPort+"/peerImp", peerImp);//REBIND THE CLIENT SO THAT IT CAN ACT AS A SERVER.
					System.out.println("Client is ready to accept request.....");
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

			}
		}
	/*********************************************************************************************************************************
	 * Class name : PeerBroadCastReq : This class takes care for picking up the value from queue and broadcast to its neighbor
	 * @author Piyush And Priyanka
	 *
	 */
	private static class PeerBroadCastReq implements Runnable  {
			

	        // Services this thread's peer client by sending the requested file.
			public void run() {
				MsgDetails msgDetail=null;
				while(true){
				try {
					while(!queueBroadcast.isEmpty()){
						/*for (MsgDetails c : queueBroadcast){
							System.out.println(c.getPortNo()+"TTL is "+c.getTTL());
						}*/
						msgDetail=queueBroadcast.poll();//msg id to broadcast for query
						//System.out.println("got new request to push."+msgDetail.getFileName());
						break;
					}
					if (msgDetail != null){
						//System.out.println(msgDetail.getTTL());
						if (msgDetail.getTTL()>0){
							String name = "peerImp";
				            //Registry registry = LocateRegistry.getRegistry(msgDetail.getPeerIP(),msgDetail.getPortNo());
				            //InClientIF comp = (InClientIF) registry.lookup(name);
				            for (int a : neighbour){
				            	Registry registry2;
								try {
									//if (a!=Integer.parseInt(RequestorDetail.get(msgDetail).split("-")[0])){
									if (a!=msgDetail.portNo){
										//System.out.println("Port number --> "+a+"---->"+msgDetail.portNo);
										registry2 = LocateRegistry.getRegistry("localhost",a);
										InClientIF comp2 = (InClientIF) registry2.lookup(name);
										comp2.query(msgDetail.getmsgID(), msgDetail.getTTL()-1, msgDetail.getFileName());
										
									}
									
									
								} catch (RemoteException | NotBoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					            
				            }
						}
					}
					msgDetail=null;
					while(!queryHitBroadcast.isEmpty()){
						msgDetail=queryHitBroadcast.poll();//msg id to send to requester with a hit
						break;
					}
					if (msgDetail != null){
						if (reqtdMsgID.contains(msgDetail.getmsgID())){
							if (true){
								blnSearching=false;
								System.out.println("Your requested item is here,we are proceeding with the download.");
								//fetchFile(msgDetail.getPortNo(),msgDetail.getFileName()); //Commenting to test performance
								endTime=System.currentTimeMillis();//SET START TIME AS A TIME
								System.out.println("Total time taken by system to fetch is : "+Long.toString(endTime-startTime) +" ms.");
							}
						}
						else{
							if (msgDetail.getTTL()>0){
								String name = "peerImp";
					            //Registry registry = LocateRegistry.getRegistry(msgDetail.getPeerIP(),msgDetail.getPortNo());
					            //InClientIF comp = (InClientIF) registry.lookup(name);
					            for (int a : neighbour){
					            	Registry registry2;
									try {
										//if (a!=Integer.parseInt(RequestorDetail.get(msgDetail).split("-")[0])){
										if (a!=msgDetail.portNo){
											//System.out.println("Port number --> "+a+"---->"+msgDetail.portNo);
											registry2 = LocateRegistry.getRegistry("localhost",a);
											InClientIF comp2 = (InClientIF) registry2.lookup(name);
											comp2.queryhit(msgDetail.getmsgID(), msgDetail.getTTL()-1, msgDetail.getFileName(), "localhost",msgDetail.getPortNo());
											
										}
										
										
									} catch (RemoteException | NotBoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
						            
					            }
							}
						}
						msgDetail=null;
					}
					
					//Code change to remove old msgid from the queue so that its not filled with too many junk data
					if (queue.size()>30)
					{
						String tempMsgId = queue.poll();
						//RequestorDetail.remove(tempMsgId);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
			}
		}
}
