//package webs;
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class chatServerThread
    extends Thread {
	
	Socket threadClient;
	String ClientStr;
	String ClientAccount;
	
	DataInputStream dis;
	DataOutputStream dos;
	
	clientMessage cMsg;
	public String id;

    public chatServerThread(Socket client, chatServer to_send_message_to, String timeID) {
    message_to = to_send_message_to;
    //port = listen_port;
    threadClient = client;
    cMsg = new clientMessage();
    
    id = timeID;

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

  private void s(String s2) { //an alias to avoid typing so much!
    message_to.send_message_to_window(s2);
  }

  private chatServer message_to; //the starter class, needed for gui
  private int port; //port we are going to listen to

  private String name;
  private String pw;
  
//this is a overridden method from the Thread class we extended from
  public void run() {
   
    //go in a infinite loop, wait for connections, process request, send response
    while (true) {
    	int message;
    	try {
    		s("\n "+id+" Server thread try \n\n");
    		message = dis.read();
    		
    		// decode the message based on message id
            if(message == ServerConstants.SET_ACCOUNT)
            {
				s("\n "+id+"ServerConstants.SET_ACCOUNT\n\n");
				
				
				ClientStr = dis.readUTF();
				name = ClientStr;
				pw = dis.readUTF();
				boolean res = message_to.checkPassword(name, pw);
				if(res == false)
				{
					s("\n "+id+"log in fail\n\n");
					dos.write(ServerConstants.VERIFY_FAIL);
					//dos.writeUTF(s);
				}
               	else
               	{
               		s("\n "+id+"log in sucess\n\n");
               		dos.write(ServerConstants.VERIFY_SUCCESS);
					
               		ClientAccount = name;
               		message_to.addAccount(ClientAccount);
               	}
					
				
            }
            else if(message == ServerConstants.CLOSE)
            {
            	if(ClientAccount != null)
            	    message_to.removeAccount(ClientAccount);
            	s("\n "+id+"ServerConstants.CLOSE\n\n");
            	//jump out the loop then the thread will come to end
            	break;
            }
            
            else if(message == ServerConstants.BROADCAST_MESSAGE)
            {
				s("\n "+id+"ServerConstants.BROADCAST_MESSAGE\n\n");
				
				cMsg.message = dis.readUTF();
				s(cMsg.message+" cMsg.message \n\n");
				
				message = dis.read();
				cMsg.from = dis.readUTF();
				s(cMsg.from+" cMsg.from \n\n");
				
				cMsg.to = "";
				s(cMsg.to+" cMsg.to \n\n");
				
				message_to.broadcast_messageObj(cMsg);
				
            }
			else if(message == ServerConstants.PRIVATE_MESSAGE)
            {
				
                s("ServerConstants.PRIVATE_MESSAGE\n\n");
				
				cMsg.message = dis.readUTF();
				
				s(cMsg.message+" cMsg.message \n\n");
				
				message = dis.read();
				cMsg.from = dis.readUTF();
				s(cMsg.from+" cMsg.from \n\n");
				message = dis.read();
				cMsg.to = dis.readUTF();
				s(cMsg.to+" cMsg.to \n\n");
				
				message_to.private_messageObj(cMsg);
                
            }
			else if(message == ServerConstants.SETUP_MESSAGE)
            {
                
            }
			else
    		{
				
			}
    		
    		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    } //go back in loop, wait for next request
  
          s("\n "+id+ "end run \n\n");
          message_to.removeServerThread(id);
  }
  
  public void recvBroadcast(String s) throws IOException
  {
	  s("recvBroadcast \n\n");
	  dos.write(ServerConstants.CHAT_ACK);
	  dos.writeUTF(s);
	  System.out.println(s);
  }
  
  public void recvBroadcastObj(clientMessage  msg) throws IOException
  {
	  s("recvBroadcastObj \n\n");
	  dos.write(ServerConstants.CHAT_ACK);
	  
	  dos.writeUTF(msg.from);
	  s(msg.from+" \n\n");
	  dos.writeUTF(msg.to);
	  dos.writeUTF(msg.message);
	  s(msg.message+" \n\n");
	  
	  
  }
  
  public void addClientListItem(String s) throws IOException
  {
	  s(s+"addClientListItem \n\n");
	  dos.write(ServerConstants.ADD_ACCOUNT);
	  dos.writeUTF(s);
	  System.out.println(s);
	 
  }
  
    public String getClientName()
    {
	    return name;
    }

	public void updateClientListItems(String s) throws IOException {
		// TODO Auto-generated method stub
		s(s+" updateClientListItems \n\n");
	  dos.write(ServerConstants.UPDATE_ACCOUNT);
	  dos.writeUTF(s);
	}
  


} //class phhew caffeine yes please!