package Client;

import java.rmi.RemoteException;

/******************************************************************************************************************
 * Interface : InClientIF This interface is created for client, when we interact with other client to broadcast message
 * 				and we aresending two message script, query and query hit
 * @author Piyush and Priyanka
 *
 */
public interface InClientIF {

	public boolean query(String msgID,int intTTL,String fileName) throws RemoteException;
	public boolean queryhit(String msgID,int intTTL,String fileName,String peerIP,int portNum) throws RemoteException;
}
