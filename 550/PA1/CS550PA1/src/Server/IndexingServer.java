package Server;

import java.rmi.Naming;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class IndexingServer {
	private static ConcurrentHashMap<String, ArrayList<String>> peerLocations = new ConcurrentHashMap<String, ArrayList<String>>();
	  public static void main(String[] args) throws Exception {
	        
		  IndexServImp indexServImpl = new IndexServImp(Integer.parseInt(args[0]));
			Naming.rebind("indexServImpl", indexServImpl);
			System.out.println("RMIDemo object bound to name'RMI Demo and is ready for use'");
	  }
}
