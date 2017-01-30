package Interface;
//IMPORT PACKAGES
import java.rmi.Remote;
import java.rmi.RemoteException;
/******************************************************************************************************************
 * Interface : IInPeerIF This interface is created for client,when it acts as a server. we needed a structure for 
 * 				download functionality and hence we created interface with download file function so that other peer can
 * 				download from server using this interface.
 * @author Piyush and Priyanka
 *
 */
public interface IInPeerIF extends Remote {
	public byte[] downloadFile(String fileName,String peerName) throws RemoteException;; 
}
