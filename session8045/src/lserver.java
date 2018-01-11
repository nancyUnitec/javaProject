import java.net.*;
import java.io.*;

public class lserver {

	public static void main(String[] args) throws IOException
    {
		
        ServerSocket server = null;
        try
        {
            server = new ServerSocket(5000);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

      //common server
        /*
		while(true)
		{
		    try
		    {
		        Socket socket = server.accept();
		        
		        InputStream is = socket.getInputStream();
		        StringBuffer mesg = new StringBuffer();
		        while(true)
		        {
		            int data = is.read();
		            
		            if(data == -1)
		            {
		                break;
		            }
		            else
		            {
		                mesg.append((char)data);
		            }
		        }
		        System.out.println(mesg);
		    }
		    catch(IOException e)
		    {
		        e.printStackTrace();
		    }
		}
		*/

		Socket socket = server.accept();

		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

		while(true)
		{
			try
			{
			String mesg = dis.readUTF();
			dos.writeUTF("Echo response:"+mesg);
			}
			catch(EOFException e)
			{
				break;
			}
		}

    }
	
}
