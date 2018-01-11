import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class MyServer3 {

	public static void main(String[] args) throws IOException
    {
		
        ServerSocket server = null;
        String timeStr = "";
        try
        {
            server = new ServerSocket(5000);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

		Socket socket = server.accept();

		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

		timeStr = refFormatNowDate();
		
        //only write for once
		try
		{
		    dos.writeUTF(timeStr);
		}
		catch(EOFException e)
		{
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
