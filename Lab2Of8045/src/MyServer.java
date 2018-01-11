//import java.io.IOException;
//import java.net.ServerSocket;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class MyServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		int number,temp;
		String sendStr;
        ServerSocket s1 = new ServerSocket(5000);
        Socket ss = s1.accept();
		
		Scanner sc = new Scanner(ss.getInputStream());
        
        sc.next();
        sendStr = "Hi, there, got your message!";
        
        PrintStream p = new PrintStream(ss.getOutputStream());
        
        p.println(sendStr);
        
	}

}




	
	

