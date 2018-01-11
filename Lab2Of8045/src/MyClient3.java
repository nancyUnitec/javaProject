import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient3 {

	public static void main(String[] args) throws UnknownHostException, IOException
    {		
		Socket client = new Socket("localhost",5000);
        
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		DataInputStream dis = new DataInputStream(client.getInputStream());
		          
		String resp = dis.readUTF();
		System.out.println(resp);
		            
		client.close();

    }

	
}
