import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer5 {

ServerSocket sSocket;
	
	public MyServer5()
	{
		// construct ServerSocket
		try {
			sSocket = new ServerSocket(5055);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("server for question5 is running...");
		MyServer5 server = new MyServer5();
		server.start();

	}

	public void start()
	{
	    while(true)
	    {
	    	try
		    {
	    		System.out.println("before...");
		        Socket cSocket = sSocket.accept();
		        MultithreadedServer thread = new MultithreadedServer(cSocket);
				thread.start();
				System.out.println("after...");
				
		    }
		    catch(IOException e)
		    {
		        e.printStackTrace();
		    }
	    	
	    }
	}


}
