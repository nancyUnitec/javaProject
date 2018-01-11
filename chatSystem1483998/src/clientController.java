import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

public class clientController {

	static DataOutputStream dos;
	static DataInputStream dis;
	static chatClient chatUI;
	static String serverStr;
	static String nouseStr;
	static String account;
	static String fromAcc;
	static String toAcc;
	
	private static ArrayList<String> accountList;
	
	private static HashMap<String,Boolean> map = new HashMap<String,Boolean>();
	
	public static void sendToServer(String userStr, int mode, String to) throws UnknownHostException, IOException
	{
		if(mode == 1)
		{
			System.out.println("sendToServer");
		    dos.write(ServerConstants.BROADCAST_MESSAGE);
		    //dos.writeUTF(account + " : " + userStr + "\n");
			dos.writeUTF(userStr + "\n");
			dos.write(ServerConstants.FROM);
			dos.writeUTF(account);
		    System.out.println(userStr);
		}
		else{
			System.out.println("private message");
		    dos.write(ServerConstants.PRIVATE_MESSAGE);
			//userStr = account + "-" + to + ":"+userStr+"\n";
		    dos.writeUTF(userStr+ "\n");
		    dos.write(ServerConstants.FROM);
			dos.writeUTF(account);
			dos.write(ServerConstants.TO);
			dos.writeUTF(to);
		    System.out.println(userStr);
		}
		
	}
	
	public void setUI(chatClient belongToUI)
	{
		chatUI = belongToUI;
	}
	
	public static void linkToServer(String userStr, String password,chatClient chatWindow) throws UnknownHostException, IOException
	{
		account = userStr;
		
		System.out.println("linkToServer");
		chatUI = chatWindow;
		
		Socket client = new Socket("localhost",5031);
		
		dos = new DataOutputStream(client.getOutputStream());
	    dis = new DataInputStream(client.getInputStream());
		
		accountList = new ArrayList<String>();
	    
		String toServerStr = account;
	    dos.write(ServerConstants.SET_ACCOUNT);
	    dos.writeUTF(toServerStr);
	    dos.writeUTF(password);
	    
	    serverStr = "";
	    nouseStr = "";
	
	    Thread thread = new Thread()
        {
	    	clientController parent;
        	public void run()
        	{
        		while(true)
        		{
        			int message;
        			
        			try
	        		{
        				message = dis.read();
	        		    
        				if(message == ServerConstants.VERIFY_FAIL)
            				{
        					    chatUI.showBroadcast("VERIFY_FAIL toAcc\n\n");
        					    dos.write(ServerConstants.CLOSE);
        					    //dos.writeUTF(account);
        					    chatUI.disconnected(); ;
        					    break;
        					    
            				}
        				
        				if(message == ServerConstants.VERIFY_SUCCESS)
            			{
        					//chatUI.showBroadcast("VERIFY_SUCCESS  \n\n");
        					chatUI.loginSuccess();
        					
            			}
        				if(message == ServerConstants.ADD_ACCOUNT)
        				{
        					//chatUI.showBroadcast("ADD_ACCOUNT  \n\n");
							serverStr = dis.readUTF();
							accountList.add(serverStr);
							map.put(serverStr,false);
        	    		    
        				    //chatUI.showClientList(accountList);
        				}
						else if(message == ServerConstants.INIT_ACCOUNT)
        				{
        					serverStr = dis.readUTF();
							accountList.add(serverStr);
							map.put(serverStr,false);
        	    		    //chatUI.showClientList(accountList);
        				}
						else if(message == ServerConstants.UPDATE_ACCOUNT)
	        				{
							    accountList.clear();
	        					serverStr = dis.readUTF();
	        					//chatUI.showBroadcast("  \n serverStr: "+serverStr+"  \n");
	        					
	        					String[] sourceStrArray = serverStr.split(",");
	        					int num = sourceStrArray.length;
	        					int index = 0;
	        					String anAcc = "";
	        					while(index < num)
	        					{
	        						//chatUI.showBroadcast(" while \n");
	        						
	        						anAcc = sourceStrArray[index];
	        						//chatUI.showBroadcast(anAcc + " \n");
	        						if(anAcc.length()>0)
	        						{
	        							//chatUI.showBroadcast("\n add "+anAcc + "\n");
	        							accountList.add(anAcc);
		    							map.put(anAcc,false);
		    							
	        						}
	        						index++;
	        						
	        					}
								
	        				   chatUI.showClientList(accountList);
	        				}
						else if(message == ServerConstants.CHAT_ACK)
        				{
							//chatUI.showBroadcast("CHAT_ACK  \n\n");
								fromAcc = dis.readUTF();
								//chatUI.showBroadcast(fromAcc+" fromAcc\n\n");
								
								//get to
								//message = dis.read();
								toAcc = dis.readUTF();
								//chatUI.showBroadcast(toAcc+" toAcc\n\n");
								
								//get message
									serverStr = dis.readUTF();
							
							if(getBlockstatus(fromAcc) == true)
							{
								//do nothing
							}    
							else if(toAcc.equals(account))
							    chatUI.showBroadcast(fromAcc + " to you : " + serverStr + "\n");
							else if((fromAcc.equals(account))&&(!(toAcc.equals(""))))
								chatUI.showBroadcast("from you to " + toAcc + " : " + serverStr + "\n");
							else
								chatUI.showBroadcast(fromAcc + " : " + serverStr + "\n");
							
        				}
						else
						{
							
						}
        			}
	        		catch (IOException e) {
	        			// TODO Auto-generated catch block
	        			//e.printStackTrace();
	        		}	
        		}
        		try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        };
		
        thread.start();

	}

	
	public static boolean getBlockstatus(String selected) {
		// TODO Auto-generated method stub
		boolean block  = false;
		block = map.get(selected);
		
		return block;
	}

	public void setBlockstatus(String selected, boolean block) {
		// TODO Auto-generated method stub
		map.put(selected, block);
	}

	public void trylogin(String text, String text2, chatClient chatWindow) {
		// TODO Auto-generated method stub
		
	}

	public void logOut() throws IOException {
		// TODO Auto-generated method stub
		
		dos.write(ServerConstants.CLOSE);
	}
}
