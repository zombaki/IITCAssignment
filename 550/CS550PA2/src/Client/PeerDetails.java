package Client;

import java.rmi.RemoteException;

public class PeerDetails implements InClientIF {

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
