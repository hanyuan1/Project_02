import java.util.Scanner;

import CalcApp.*;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextExtPackage.*;
import org.omg.CORBA.*;

class CalcClient {
	static Calc calcImpl;
	
	public static void main(String args[]) {
		try {
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

  			// get the root naming context
    		org.omg.CORBA.Object objRef = 
        		orb.resolve_initial_references("NameService");
    		// Use NamingContextExt instead of NamingContext. This is 
    		// part of the Interoperable naming Service.  
    		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

    		// resolve the Object Reference in Naming
    		String name = "Calc";
    		calcImpl = CalcHelper.narrow(ncRef.resolve_str(name));

    		// System.out.println("Obtained a handle on server object: " + calcImpl);
    		System.out.println("Obtained a handle on server object");
				
    		// Prompt the user to type 
    		boolean choose = true;    		
    		while (choose) {
    			System.out.println("Please type in the order of \"opcode, op1 and op2\"");  
    			String opcd;
    			int opcode;
    			int op1;
    			int op2;
    			Scanner in = new Scanner(System.in);
    			opcd = in.next();
    			op1 = in.nextInt();
    			op2 = in.nextInt();
    			opcode = opcd.charAt(0);
    			System.out.println("=" +  " " + calcImpl.calculate(opcode, op1, op2));
    			System.out.print("\nContinue Calculator? (Y/N)");
    			String chooseStr = in.next();
    			if (chooseStr.equals("Y") || chooseStr.equals("y"))
    			choose = true;
    			else 
    				choose = false;
    		}
    		calcImpl.exit();
    		
			
		} catch (Exception ex) {
		      System.out.println("ERROR : " + ex) ;
	          ex.printStackTrace(System.out);
			
		}
	}

}
