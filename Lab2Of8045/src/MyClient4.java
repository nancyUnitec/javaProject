import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MyClient4 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	    Socket client = new Socket("localhost",5031);
	
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
	    DataInputStream dis = new DataInputStream(client.getInputStream());
	
	    String resp = dis.readUTF();
	    System.out.println(resp);
	
	    client.close();
	}

}
