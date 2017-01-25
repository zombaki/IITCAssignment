package Client;

import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import Interface.IInserver;


public class Peer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            String name = "indexServImpl";
            Registry registry = LocateRegistry.getRegistry(args[0],Integer.parseInt(args[1]));
            IInserver comp = (IInserver) registry.lookup(name);
            ArrayList<String> fileDetails = comp.lookupFile("test");
            System.out.println(fileDetails);
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
		
	}

}
