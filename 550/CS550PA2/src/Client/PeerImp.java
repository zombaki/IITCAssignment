package Client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PeerImp extends UnicastRemoteObject  implements InClientIF {

	protected PeerImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean query(String msgID, int intTTL, String fileName) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean queryhit(String msgID, int intTTL, String fileName, String peerIP, int portNum)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
