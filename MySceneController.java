package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MySceneController {
	
//------------------------------------------------------------------
	AccountList CurrentProfiles = new AccountList("C:/Users/jazme/eclipse-workspace/Social/src/application/Profiles");
//------------------------------------------------------------------
	@FXML
	private TextField Emailtxt;
	
	@FXML
	private TextField Usertxt;
	
	@FXML
	private PasswordField Passtxt;
	
	@FXML
	private PasswordField PasstxtR;
	
	@FXML
	private TextField Error;
	
	@FXML
	private Button NUsubmitClick;
	
	@FXML
	private Button FPsubmitClick;
	
	@FXML
	private Button submitClick;
	
	@FXML
	private TextField Welcometxt;
	
	//textField.setStyle("-fx-text-inner-color: red;");
	
	@FXML
	public void submitClick(ActionEvent e) throws InterruptedException, IOException {
		String Username=Usertxt.getText();
		String Password=Passtxt.getText();
		
		if((Username == null || Username.length()==0) || (Password == null || Password.length()==0)) {
			Error.setText("Please Enter Username and Password");
		}
		else {
			Error.setText("");
			ProcessLogin(Username, Password);
			
			//Stage mainWindow = (Stage) Usertxt.getScene().getWindow();
			//String title = Usertxt.getText();
			//mainWindow.setTitle(title);
		}
		
	}
	
	@FXML
	public void NUsubmitClick(ActionEvent e) throws InterruptedException, IOException {
		String Email=Emailtxt.getText();
		String Username=Usertxt.getText();
		String Password=Passtxt.getText();
		String PasswordR=PasstxtR.getText();
		
		if((Email == null || Email.length()==0) || (Username == null || Username.length()==0) || (Password == null || Password.length()==0) || (PasswordR == null || PasswordR.length()==0)) {
			Error.setText("Please Enter Required Information");
		}
		if(Password.equals(PasswordR)) {
			NewProfile(Email, Username, Password, PasswordR);
			
			//open app once successful login
		}
		else {
			Error.setText("Please Enter Matching Passwords");
			
		}
	}
	
	@FXML
	public void FPsubmitClick(ActionEvent e) throws InterruptedException {
		String Email=Emailtxt.getText();
		
		if(Email == null || Email.length() == 0) {
			Error.setText("Please Enter Email");
		}
		else {
			//System.out.println(CurrentProfiles.AccountList.get(0).Email);
			ProcessForgotPassword(Email);
			
		}
	}
	
	@FXML
	public void newUClick(ActionEvent e) throws IOException {
		FXMLLoader fxmlLoader = new 
		FXMLLoader(getClass().getResource("/application/NewUserScene.fxml"));
		    Parent root1 = (Parent) fxmlLoader.load();
		    Stage stage = new Stage();
		    //set what you want on your stage
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.setTitle("New User");
		    stage.setScene(new Scene(root1));
		    stage.setResizable(false);
		    stage.show();
		
	}
	
	@FXML
	public void fpClick(ActionEvent e) throws IOException {
		FXMLLoader fxmlLoader = new 
		FXMLLoader(getClass().getResource("/application/ForgotPasswordScene.fxml"));
				    Parent root2 = (Parent) fxmlLoader.load();
				    Stage stage = new Stage();
				    //set what you want on your stage
				    stage.initModality(Modality.APPLICATION_MODAL);
				    stage.setTitle("Forgot Password");
				    stage.setScene(new Scene(root2));
				    stage.setResizable(false);
				    stage.show();
	}
	
	@FXML
    private void initialize() {
    }
	
	private void ProcessLogin(String Username, String Password) throws InterruptedException, IOException {
		boolean userFound = false;
		for (int i =0; i<CurrentProfiles.AccountList.size(); i++) {
			String UN = CurrentProfiles.AccountList.get(i).Username;
			String PW = CurrentProfiles.AccountList.get(i).Password;
			
			
			if(Username.equals(UN)) {
				if(Password.equals(PW)) {
				userFound = true;
				//Error.setText("Successful Login!");
				TimeUnit.SECONDS.sleep(2);
				break;
				}
				else {
					Error.setText("Wrong Username or Password");
				}
			}
			else {
				Error.setText("Account wasn't found");
			}
		}
		if (userFound) {
			//close login stage
			Stage stage1 = (Stage) Usertxt.getScene().getWindow();
			stage1.close();
			Homepage();
			
							
		}
	}
	
	private void ProcessForgotPassword(String Email) throws InterruptedException {
		boolean emailFound = false;
		for (int i =0; i<CurrentProfiles.AccountList.size(); i++) {
			String E = CurrentProfiles.AccountList.get(i).Email;
			
			if(Email.equals(E)) {
				emailFound = true;
			}
		}
		if(emailFound) {
			Error.setText("Email Found");
			
			TimeUnit.SECONDS.sleep(2);
			//send email to Email
			
			Stage stage = (Stage) Emailtxt.getScene().getWindow();
			stage.close();
		}
		else {
			Error.setText("Email Not Found");
		}
	}
	
	public void NewProfile(String E, String U, String P, String RP) throws InterruptedException, IOException {
			//Accounts a = new Accounts(E, U, P);
			//CurrentProfiles.AccountList.add(a);
			//Doesn't update Profiles.txt with new account
			FileWriter fw = new FileWriter("C:/Users/jazme/eclipse-workspace/Social/src/application/Profiles", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter newP = new PrintWriter(bw);
			
			newP.print("\n"+E+"	"+U+"	"+P);
			newP.close();
			//Error.setText("New Profile Created");
			TimeUnit.SECONDS.sleep(2);
			
			Stage stage = (Stage) Emailtxt.getScene().getWindow();
			stage.close();
			
		}
		//------------------------------------------------------------------
	public void Homepage() throws IOException {
		//show application scene
		FXMLLoader fxmlLoader = new 
				FXMLLoader(getClass().getResource("/application/HomePageScene.fxml"));
						    Parent root2 = (Parent) fxmlLoader.load();
						    Stage stage = new Stage();
						    //set what you want on your stage
						    stage.initModality(Modality.APPLICATION_MODAL);
						    stage.setTitle("Home Page");
						    stage.setScene(new Scene(root2));
						    stage.setResizable(false);
						    stage.show();
	}
}
