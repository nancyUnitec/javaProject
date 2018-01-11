import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class lclient {
	public static void main(String[] args) throws UnknownHostException, IOException
    {
		//common client
		/*
        try
        {

            Socket client = new Socket("localhost",5026);

            OutputStream os = client.getOutputStream();

            os.write("Hello ISCG8045 this is our first network connection".getBytes());
            os.close();

            client.close();

        }

        catch(IOException e)
        {
           e.printStackTrace();
        }
        */
		
		Socket client = new Socket("localhost",5000);
        
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		DataInputStream dis = new DataInputStream(client.getInputStream());
		            
		dos.writeUTF("Test Message");
		            
		String resp = dis.readUTF();
		System.out.println(resp);
		            
		client.close();

    }
}
