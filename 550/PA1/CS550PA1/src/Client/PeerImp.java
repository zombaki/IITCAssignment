package Client;
//IMPORT PACKAGES
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Interface.IInPeerIF;
/********************************************************************************************************
 * PeerImp Class: This has the implementation of server part.
 * @author Piyush and Priyanka
 *
 */
public class PeerImp extends UnicastRemoteObject implements IInPeerIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected PeerImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/*********************************************************************************************************
	 * Method name :downloadFile this takes care for downloading files from server.
	 */
	public byte[] downloadFile(String fileName,String peerName) throws RemoteException {
		System.out.println("Printing for download requested for file - " +fileName);
		File file = new File(peerName+"/"+fileName);//Path to download file
		byte[] buffer= new byte[(int)file.length()];
		BufferedInputStream input;
		try {
			input= new BufferedInputStream(new FileInputStream(file));//READ THE FILE FROM CLIENT AS SERVER AND SEND THE BYTE TO THE REQUESTED PEER
			input.read(buffer, 0, buffer.length);
			return buffer;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}
