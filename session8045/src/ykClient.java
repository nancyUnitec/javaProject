import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ykClient {

	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		// TODO Auto-generated method stub
        int number,temp;
        String recvStr;
        //Scanner sc = new Scanner(System.in);
        Socket s = new Socket("127.0.0.1",1342);
        Scanner sc1 = new Scanner(s.getInputStream());
        //System.out.println("Enter any number");
        //number = sc.nextInt();
        
        PrintStream p = new PrintStream(s.getOutputStream());
        //p.println(number);
        p.println("Hello");
        //temp = sc1.nextInt();
        recvStr = sc1.nextLine();
        //while(recvStr != "")
        //{
        //System.out.println(temp);
        System.out.println(recvStr);
        //recvStr = sc1.next();
        //}
    }

}
