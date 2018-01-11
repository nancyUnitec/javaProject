import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class chatClient extends Application {

	@FXML
	private Button btnSend;
	
	@FXML
	private Button btnMode;
	
	@FXML
	private CheckBox checkBoxBlocked;
	
	@FXML
	private Label myAccount;
	
	@FXML
	private Label modeLable;
	
	@FXML
	private TextField txtField;
	
	@FXML
	private TextArea txtArea;
	
	@FXML
	private ListView<String> lst;
	
	@FXML
	private Button btnLogin;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtPassword;
	
	
	static clientController theController;
	
	static int mode;
	
	
	@FXML
	void btnSendOnClick(ActionEvent event) throws IOException {
		
		
		String userInput = txtField.getText(); 
		
		if(mode == 1)
		{
			System.out.println(userInput);
			theController.sendToServer(userInput, mode, "");
			
		}
		else
		{
			String selected = lst.getSelectionModel().getSelectedItem();
			theController.sendToServer(userInput, mode, selected);
		}
		
		txtField.setText("");
		
		
	}
	
	@FXML
	void accountLstOnClick(MouseEvent event) throws IOException {
		
		String selected = lst.getSelectionModel().getSelectedItem();
		boolean blocked = theController.getBlockstatus(selected);
		
		if(blocked == true)
		{
			//showBroadcast("true \n");
			checkBoxBlocked.setSelected(blocked);
		}
		else if(blocked == false)
		{
			//showBroadcast("false \n");
			checkBoxBlocked.setSelected(blocked);
		}
			
	}
	
	
	@FXML
	void checkBoxBlockOnClick(ActionEvent event) throws IOException {
		 //showBroadcast("checkBoxBlockOnClick \n");
		boolean block = checkBoxBlocked.isSelected();
		String selected = lst.getSelectionModel().getSelectedItem();
		theController.setBlockstatus(selected,block);
		//showBroadcast(selected +" \n");
		//showBroadcast("true \n");
	}
	
	@FXML
	void btnModeOnClick(ActionEvent event) throws IOException {
		if(mode == 1)
		{
			mode = 2;
			//modeLable.
			modeLable.setText("private");
		}
		else if(mode == 2)
		{
			mode = 1;
			modeLable.setText("public");
		}
			
	}
	
	@FXML
	void btnLogOnClick(ActionEvent event) throws IOException {
		mode = 0;
		
		if(mode == 0)
		{
			myAccount.setText(txtName.getText());
			mode = 1;
		}
				
		initController();
		theController.linkToServer(txtName.getText(),txtPassword.getText(),this);
		
	}
	
	@FXML
	void btnLogOutClick(ActionEvent event) throws IOException {
		//theController.sendToServer(userInput, mode, "");
		theController.logOut();
		myAccount.setText("");
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		
		launch(args);

	}

	public void initController()
	{
		theController = new clientController();
		theController.setUI(this);
	}
	
	public void showBroadcast(String s)
	{
		 //jTextArea2.append(s);
		 txtArea.appendText(s);
	}
	
	public void loginSuccess()
	{
		
		showBroadcast("loginSuccess() \n");
		btnSend.setDisable(false);
		txtField.setDisable(false);
		
		txtName.setDisable(true);
		txtPassword.setDisable(true);
		btnLogin.setDisable(true);
		
	}
	
	public void disconnected()
	{
		showBroadcast("disconnected! please input the correct login info! \n");
		
		btnSend.setDisable(true);
		txtField.setDisable(true);
		
		txtName.setDisable(false);
		txtPassword.setDisable(false);
		btnLogin.setDisable(false);
	    
	}
	
	public void showClientList(ArrayList<String> accountList)
	{
		//showBroadcast("showClientList() \n");
		
		ObservableList<String> strList = FXCollections.observableArrayList(accountList);
		
		lst.setItems(null);
		lst.setItems(strList);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("clientUI.fxml"));
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("clientDesign.fxml"));
			
			Scene scene = new Scene(root,300,300);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle ("Chat Client");
			primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
