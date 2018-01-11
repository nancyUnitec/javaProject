import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.omg.CORBA.portable.InputStream;

public class MyServer4 {

	ServerSocket sSocket;
	Socket cSocket;
	String timeStr;
	
	DataInputStream dis;
	DataOutputStream dos;
	
	public MyServer4()
	{
		// construct ServerSocket
		try {
			sSocket = new ServerSocket(5014);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		MyServer4 server = new MyServer4();
		server.start();

	}

	public void start()
	{
	    while(true)
	    {
	    	try
		    {
		        cSocket = sSocket.accept();
		        
		        try {
					 dis = new DataInputStream(cSocket.getInputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					dos = new DataOutputStream(cSocket.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        Thread thread = new Thread()
		        {
		        	public void run()
		        	{
		        		timeStr = refFormatNowDate();
		        		
		                //only write for once
		        		try
		        		{
		        		    dos.writeUTF(timeStr);
		        		}
		        		catch (IOException e) {
		        			// TODO Auto-generated catch block
		        			e.printStackTrace();
		        		}
		        	}
		        };
				thread.start();
				
		    }
		    catch(IOException e)
		    {
		        e.printStackTrace();
		    }
	    	
	    }
	}
	
	public static String refFormatNowDate() 
	{
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		return retStrFormatNowDate;
	}
}
