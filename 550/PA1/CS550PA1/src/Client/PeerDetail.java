package Client;
/********************************************************************************************************************************
 * Class PeerDetail : this is the container class which is used by both server and client to communcate data between them
 * @author Piyush and Priyanka
 *
 */
public class PeerDetail implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	String peerId;//PEER ID
	String portNo;//PORT NUMBER
	String fileName;//FILE NAME
	String hostIP;//HOST IP
	/*****************************************************************************************************************************
	 * Constructor assigning value on creation.
	 * @param peerId //PEER ID
	 * @param portNo //PORT NUMBER OF PEER
	 * @param fileName //FILENAME
	 * @param hostIP //HOSTIP
	 */
	public PeerDetail(String peerId, String portNo, String fileName,String hostIP){
		this.peerId = peerId;
		this.portNo = portNo;
		this.fileName = fileName;
		this.hostIP=hostIP;
		
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public String getPeerId() {
		return peerId;
	}

	public void setPeerId(String peerId) {
		this.peerId = peerId;
	}

	public String getPortNo() {
		return portNo;
	}

	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
