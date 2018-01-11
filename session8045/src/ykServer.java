import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ykServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		int number,temp;
		String sendStr;
        //Scanner sc = new Scanner(System.in);
        ServerSocket s1 = new ServerSocket(1342);
        Socket ss = s1.accept();
		
		//Socket s = new Socket("127.0.0.1",1342);
        Scanner sc = new Scanner(ss.getInputStream());
        System.out.println("Enter any number");
        
        sc.next();
        //number = sc.nextInt();
        
        //temp = number*2;
        sendStr = "got it";
        
        PrintStream p = new PrintStream(ss.getOutputStream());
        //p.println(temp);
        p.println(sendStr);
        
	}

}
