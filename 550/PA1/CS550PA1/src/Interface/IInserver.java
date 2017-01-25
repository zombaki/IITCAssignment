package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IInserver extends Remote {
	public ArrayList<String> lookupFile(String fileName) throws RemoteException;; 
	public boolean register(ArrayList<String> files, String peerAddress) throws RemoteException;; 
	public boolean unregister(String peerAddress) throws RemoteException;; 
}
