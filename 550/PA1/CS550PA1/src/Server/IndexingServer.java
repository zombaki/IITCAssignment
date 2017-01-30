package Server;
//IMPORT PACKAGES
import java.rmi.Naming;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import Interface.IInserver;
/****************************************************************************************************************************************
 * Class :	IndexingServer
 * @author piyush and Priyanka
 *	RMI Indexing server takes care for initiating server for RMI system . This will initiate server for port passed as Argument 0. since we 
 *		are keeping the server and client in same server we have kept the server as localhost or 127.0.0.1. Either one will work fine with 
 *		This code.
 */
public class IndexingServer {
	/*****************************************************************
	 * Method Name : Main
	 * @param args(Server Port number) 0
	 * @throws Exception
	 */
	  public static void main(String[] args) throws Exception {
		  IInserver indexServImpl = new IndexServImp(); //INITIALIZING THE IMPLEMENTATION CLASS
		  Naming.rebind("rmi://localhost:"+args[0]+"/indexServImpl", indexServImpl); //REBINDING INSTANCE TO THE RMI REGISTRY
		  System.out.println("Index server bound and ready to use.'"); //INFORMATION TO USER THAT SERVER IS READY TO USE.
	  }
}
