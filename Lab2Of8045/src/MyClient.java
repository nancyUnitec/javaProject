import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyClient {
	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		// TODO Auto-generated method stub
        int number,temp;
        String recvStr;
        Socket s = new Socket("127.0.0.1",5000);
        Scanner sc1 = new Scanner(s.getInputStream());
        
        PrintStream p = new PrintStream(s.getOutputStream());
        p.println("Hello");
        recvStr = sc1.nextLine();
        System.out.println(recvStr);
        
    }

}

