//package webs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;



public class chatServer
    extends JFrame {
  //declare some panel, scrollpanel, textarea for gui
  JPanel jPanel1 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea2 = new JTextArea();
  static Integer listen_port = null;
  
  ArrayList<chatServerThread> connectedClients = new ArrayList<chatServerThread>();
  ArrayList<String> clientNameList = new ArrayList<String>();
 
  private static HashMap<String,String> accountMap = new HashMap<String,String>();
 
  ServerSocket serverSocket;

  //basic class constructor
  public chatServer() {
    try {
      jbInit();
      
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  //the JavaAPI entry point
  //where it starts this class if run
  public static void main(String[] args) {
    //start server on port x, default 5000
    //use argument to main for what port to start on
    try {
      listen_port = new Integer(args[0]);
      //catch parse error
    }
    catch (Exception e) {
      listen_port = new Integer(5000);
    }
    //create an instance of this class
    chatServer webserver = new chatServer();
  }

  public static String refFormatNowDate() 
	{
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		return retStrFormatNowDate;
	}
  
  //set up the user interface
  private void jbInit() throws Exception {
    //oh the pretty colors
    jTextArea2.setBackground(new Color(255, 0, 255));
    jTextArea2.setForeground(new Color(0, 0, 0));
    jTextArea2.setBorder(BorderFactory.createLoweredBevelBorder());
    jTextArea2.setToolTipText("");
    jTextArea2.setEditable(false);
    jTextArea2.setColumns(30);
    jTextArea2.setRows(15);
    //change this to impress your friends
    this.setTitle(" Unitec Nancy's chatting room");
    //ugly inline listener, it's for handling closing of the window
    this.addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        this_windowClosed(e);
      }
    });
    //add the various to the proper containers
    jScrollPane1.getViewport().add(jTextArea2);
    jPanel1.add(jScrollPane1);
    this.getContentPane().add(jPanel1, BorderLayout.EAST);

    //tveak the apearance
    this.setVisible(true);
    this.setSize(420, 350);
    this.setResizable(false);
    //make sure it is drawn
    this.validate();
    //create the actual serverstuff,
    //all that is implemented in another class
    //new chatServerThread(listen_port.intValue(), this);
    
    CreateLoginData("src/account.txt");
    
    try {
            serverSocket = new ServerSocket(5031);
    }

	catch (IOException e)
    {
    	e.printStackTrace();
    }
    
    try
	{
		while(true) // keep accepting new clients
		{
			System.out.println("wait a client ");  
			send_message_to_window("wait a client ");
			
			
			Socket chatClient = serverSocket.accept(); // block and wait for a connection from a client
			
			String timeStr = refFormatNowDate();
			
			System.out.println("get a client  1959 ");
			send_message_to_window("get a client  1959 ");
			// construct a new server thread, to handle each client socket
			chatServerThread st = new chatServerThread(chatClient, this , timeStr);
			
			st.start();
			
			connectedClients.add(st);
			
		}
	}
	
    catch (IOException e)
	{
		e.printStackTrace();
	}
	
    
  }

  //exit program when "X" is pressed.
  void this_windowClosed(WindowEvent e) {
    System.exit(1);
  }

  //this is a method to get messages from the actual
  //server to the window
  public void send_message_to_window(String s) {
    jTextArea2.append(s);
  }
  
    public void addAccount(String s) throws IOException {
	    clientNameList.add(s);
	    
	    //send_message_to_window("\n addAccount "+s+" \n");
	    
	    String namelistStr = "";
	    for(String accountName:clientNameList) 
	    {
	    	namelistStr += ","+accountName;
	    }
	    
        for(chatServerThread otherClient: connectedClients)
		{
			otherClient.updateClientListItems(namelistStr);
        }
    }
	
	public ArrayList<String> getNameList() {
		return clientNameList;
	}
 
    public void private_message(String from, String to, String s) throws IOException {
	    for(chatServerThread otherClient: connectedClients)
		{
	    	send_message_to_window(otherClient.getClientName()+"\n\n");
	    	send_message_to_window(to+"is to \n\n");
			
			if(to.equals(otherClient.getClientName()))
			{
				send_message_to_window("\n eaual");
				s = "private message from " + from + " : "+ s;
				otherClient.recvBroadcast(s);
			    break;
			}
			
		}
	}
    
    public void private_messageObj(clientMessage msg) throws IOException {
	    
    	send_message_to_window("private_messageObj \n\n");
    	for(chatServerThread otherClient: connectedClients)
		{
	    	send_message_to_window(otherClient.getClientName()+"\n\n");
	    	send_message_to_window(msg.to+"is to \n\n");
			
			if((msg.to.equals(otherClient.getClientName()))||
			   (msg.from.equals(otherClient.getClientName())))
			{
				send_message_to_window("\n eqaual \n");
				
				otherClient.recvBroadcastObj(msg);
			    
			}
			
		}
	}
    
    public void broadcast_message(String s) throws IOException {
	    
	    for(chatServerThread otherClient: connectedClients)
		{
			otherClient.recvBroadcast(s);
		}
		
  }
    
	public void CreateLoginData(String FilePath) throws IOException
	{
		FileReader reader = new FileReader(FilePath); 
        BufferedReader br = new BufferedReader(reader);
        String str = null; 
        String pw = null; 
        int maxSplit = 2;
        while((str = br.readLine()) != null) {
            String[] sourceStrArray = str.split(",",maxSplit);
				
            if((sourceStrArray.length)>1)
                pw = sourceStrArray[1];
				
            accountMap.put(sourceStrArray[0],sourceStrArray[1]);	
            System.out.println(sourceStrArray[0]+"\n");
        }
		
	}	
	
	public boolean checkPassword(String name, String inputPw)
	{
		String rightPw = accountMap.get(name);
		//send_message_to_window("\n right pw is "+rightPw + "\n");
		
		if(rightPw==null)
		{
			return false;
		}
		if(inputPw.equals(rightPw))
			return true;
		else
			return false;
	}
	
	public void broadcast_messageObj(clientMessage msg) throws IOException {
	    
	    for(chatServerThread otherClient: connectedClients)
		{
			otherClient.recvBroadcastObj(msg);
		}
		
  }

	public void removeServerThread(String threadID){
		// TODO Auto-generated method stub
		
		send_message_to_window("\n removeClient "+threadID + "\n");
		
		String itemID = "";	
		
     Iterator <chatServerThread> sListIterator = connectedClients.iterator();
		
     while(sListIterator.hasNext()){
    	 chatServerThread e = sListIterator.next();
    	 itemID = e.id;
    	 if(itemID.equals(threadID)){
    		 send_message_to_window("\n removeClient " + threadID +" \n");
    		 sListIterator.remove();
    		 break;
    	 }
         
     }
		
	}

	public void removeAccount(String clientAccount) throws IOException {
		
		send_message_to_window("\n removeAccount "+clientAccount + "\n");
		// TODO Auto-generated method stub
		Iterator <String> sListIterator = clientNameList.iterator();

		 while(sListIterator.hasNext()){
		    	 String e = sListIterator.next();
		    	 
		    	 if(e.equals(clientAccount)){
		    		 send_message_to_window("\n removeClient " + clientAccount +" \n");
		    		 sListIterator.remove();
		    		 break;
		    	 }
		 }
		 
		 String namelistStr = "";
		    for(String accountName:clientNameList) 
		    {
		    	namelistStr += ","+accountName;
		    }
		    
	        for(chatServerThread otherClient: connectedClients)
			{
	        	if(otherClient!=null)
	        	{
	        		if(!(otherClient.ClientAccount).equals(clientAccount))
		        	    otherClient.updateClientListItems(namelistStr);
		        		
	        	}
				
			}
		 
	}
} //class end