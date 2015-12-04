// CalculatorServer.java

import CalcApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextExtPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import java.util.Properties;

class CalcImpl extends CalcPOA {
	private ORB orb;
	
	public void setORB(ORB orb_val) {
		orb = orb_val;
	}
	
	// implement calculate() method
	// (+)43 (-)45 (*)42 (/)47 (%)37
	public int calculate(int opcode, int op1,int op2) {
		
		switch (opcode) {
			case 43:
				return op1 + op2;
			case 45:
				return op1 - op2;
			case 42:
				return op1 * op2;
			case 47:	
				return op1 / op2;
		}
		return opcode;	
	}
	
	// implement exit() method
	public void exit() {
		try {
			orb.shutdown(false);
			System.out.println("I am out");
			// return 0;
		}
		catch (org.omg.CORBA.BAD_INV_ORDER ex) {
			// return -1;
		}
	}
}

public class CalcServer {
	
	public static void main(String args[]) {
		try {
			// Create and initialize the ORB
			ORB orb = ORB.init(args, null);
			
			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			
			// create servant and register it with the ORB
			CalcImpl calcImpl = new CalcImpl();
			calcImpl.setORB(orb);
			
			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(calcImpl);
			Calc href = CalcHelper.narrow(ref);
			
			// get the root naming context
			// NameService invokes the name service 
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			
			// Use NamingContextExt which is part of the Interoperable
			// Naming Service (INS) specification.
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// bind the Object Reference in Naming
	      		String name = "Calc";
	     		NameComponent path[] = ncRef.to_name( name );
	      		ncRef.rebind(path, href);
			
			System.out.println("CalculatorServer ready and waiting ...");
			
			// wait for invocations from clients
			orb.run();
		}
		catch (Exception ex) {
			System.out.println("ERROR: " + ex);
		}
		
		 System.out.println("CalculatorServer Exiting ...");
		
	}

}

