import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class MyThread4 extends Thread{

	Socket threadClient;
	String timeStr;
	
	DataInputStream dis;
	DataOutputStream dos;

	
	MyThread4(Socket client) {
		threadClient = client;
		
		try {
			 dis = new DataInputStream(threadClient.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			dos = new DataOutputStream(threadClient.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	  }
	
	public void run() {
	     // do something
		System.out.println("test java thread");
		
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
	
	public static String refFormatNowDate() 
	{
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		return retStrFormatNowDate;
	}


	

}
