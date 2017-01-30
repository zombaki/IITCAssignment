package Interface;
//IMPORT PACKAGES
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import Client.PeerDetail;
/********************************************************************************************************************
 * Interface : IInserver This is the interface which is going to be shared with both server and the client. This is going to help
 * 				Peer to let system interact with server for below mentioned functions so that we can register, de-register and lookup files
 * 				from server.
 * @author Piyush and Priyanka
 *
 */
public interface IInserver extends Remote {
		public ArrayList<PeerDetail> lookupFile(String peer,String fileName) throws RemoteException;
		public String register(String name,HashMap<String, ArrayList<PeerDetail>> peer) throws RemoteException;
		public boolean unregister(String peerAddress) throws RemoteException; 

}
