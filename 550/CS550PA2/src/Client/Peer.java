package Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

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
	public static void main(String[] args) {
        int opt=0;
        intPort=args[0];
        //THREAD TO MAKE PEER AS SERVER FOR DOWNLOAD FUNCTIONALITY
      	Thread tServer = new Thread(new PeerServer());
      	tServer.start();
        Scanner sc=new Scanner(System.in);
		do{
			System.out.println("Enter Some value.");
			opt=sc.nextInt();
		}while(opt!=0);
        
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
				try {
				
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
}
