package Server;
//IMPORT PACKAGES
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.ArrayList;

import Client.PeerDetail;
import Interface.IInserver;
/*************************************************************************************************************************
 * Class Name : IndexServImp This is implementation class for indexing server . This is the source code for operation that 
 * 				are expected at server end.
 * @author Piyush and Priyanka
 *		UnicastRemoteObject is used to export remote object.
 */
public class IndexServImp extends UnicastRemoteObject implements IInserver {
	//DECLARATION SECTION
	public static HashMap<String,ArrayList<PeerDetail>> mapPeer = new HashMap<String,ArrayList<PeerDetail>>() ;
	private static final long  serialVersionUID = 1L;
	private static String fileName = "indexFile.txt";
	//DEFAULT CONSTRUCTOR
	protected IndexServImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	static{
		mapPeer=new HashMap<String,ArrayList<PeerDetail>>();
	}


/****************************************************************************************
 * Method Name:unregister This is used for unregister the Peer from the files details.
 * Input parameter : peer . This is peerid which is stored with the server to unregister the files associated from server's details
 */
	public synchronized boolean unregister(String peer) {
		// TODO Auto-generated method stub
		System.out.println(peer);
		System.out.println(mapPeer);
		if (mapPeer.containsKey(peer)){
			mapPeer.remove(peer);
			return true;
		}
		else
			return false;
	}
	/****************************************************************************************
	 * Method Name:lookupFile This method is used to search for the file in Server's hash map with details if there data exists in server.
	 * Parameters : peer : Peer name which is requesting for data.
	 * 				filename: File which is requested by the server.
	 */
	public synchronized ArrayList<PeerDetail> lookupFile(String peer,String fileName) throws RemoteException {
		ArrayList<PeerDetail> peerList = new ArrayList<PeerDetail>();
		for(String key: mapPeer.keySet()){
			System.out.println("Request Made by : "+peer);
			if(!(peer.trim().equals(key))){//IGORE THE PEER WHO REQUESTED FOR THE FILE
				ArrayList<PeerDetail> peernew = mapPeer.get(key);
				//SEARCH ALGO TO FIND THE FILE IN THE PEER LIST		
				for(PeerDetail pDetail:peernew){
						if(fileName.trim().equals(pDetail.getFileName())){
					      peerList.add(pDetail);//CONCATINATE ALL THE RESULT OF FILES IN A LIST TO RETURN TO SERVER.
					}
				}
				
			}
		}
		
		return peerList;
		
	}

	/****************************************************************************************
	 * Method Name:register This is the first method which is invoked by the system to register all  the files which need to be register to server
	 * Perameter : 	name : Name of the peer to which data is to be associated.
	 * 				peer : This will have the hash map details which would be created by the peer and sent to server.
	 */

	public synchronized String register(String name,
			HashMap<String,ArrayList<PeerDetail>> peer) throws RemoteException {
		try{		
			System.out.println("Files registerd by Peer \t:"+name); //INFO TO SERVER AS FILE REGISTERD BY THE SERVER.
			mapPeer.putAll(peer);//ADDING FFILES TO MAP PEER HASH TABLE AT SERVER END
			//HASH TABLE BACKUP IS TAKEN TO THE FILE AT LOCAL DIRECTORY
			FileOutputStream fos=new FileOutputStream(fileName);
		    ObjectOutputStream oos=new ObjectOutputStream(fos);		
	        oos.writeObject(mapPeer);
	        oos.flush();
	        oos.close();
	        fos.close();
	    }catch(Exception e){
			System.out.println("Could NOT Write to indexFile"); 
		}
		//RESPONSE TO SERVER WITH SUCCESS INFORMATION.
		return "\nServer says: Hi " +name+ " You have succefully registered\n" ;
	}
	
}

