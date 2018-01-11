import java.net.*;

public class NSLookup {

    public static void main (String args[]) {
	 
    	//question 1
    	
    	try{
		    
    		InetAddress[] addresses = InetAddress.getAllByName(args[0]);
		    
    		System.out.println("Host name: " + args[0]);
    		System.out.println("IP address: " + addresses[0]);
    		
	    }
	    catch (UnknownHostException e) 
	    {
	        System.out.println("Could not find www.whitehouse.gov");
	    }
	  }
}
