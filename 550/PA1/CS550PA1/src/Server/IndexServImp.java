package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Interface.IInserver;

public class IndexServImp extends UnicastRemoteObject implements IInserver {

	/*protected IndexServImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}*/
	public IndexServImp(int portnumber) throws RemoteException {
		super(portnumber);
		}
	@Override
	public ArrayList<String> lookupFile(String fileName) {
		// TODO Auto-generated method stub
		ArrayList<String> a= new ArrayList<String>();
		a.add("this is server");
		return a;
	}

	@Override
	public boolean register(ArrayList<String> files, String peerAddress) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unregister(String peerAddress) {
		// TODO Auto-generated method stub
		return false;
	}

}
