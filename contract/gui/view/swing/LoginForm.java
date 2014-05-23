package contract.gui.view.swing;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ezware.dialog.task.TaskDialogs;

import contract.controller.AppController;

public class LoginForm {
	private AppController app;
	private JFrame mainContainer;
	private JPanel loginPanel = new JPanel();
	private JLabel loginL = new JLabel("Login:");
	private JTextField login = new JTextField(20);
	private JLabel passL = new JLabel("Password:");
	private JPasswordField pass = new JPasswordField(20);

	
	public LoginForm(AppController appController) {
		app = appController;
		mainContainer = new JFrame();
		mainContainer.setTitle("Login");
		
		loginPanel.setLayout(new GridLayout(2,2));
        
        loginPanel.add(loginL);
        loginPanel.add(login);
        loginPanel.add(passL);
        loginPanel.add(pass);
        
	}   
    
	public void showDialog(){
       	int input = JOptionPane.showConfirmDialog(mainContainer, loginPanel, "Enter your password:"
                   ,JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (input == 0){
        	char[] pwd = pass.getPassword();
        	//clear pass array
        	String password = new String(pwd);
        	for(int i = 0; i < pwd.length; i++){
        		pwd[i] = 0;
        	}
           	app.validate_login(login.getText(), password);
           	password = "";
        } else {
        	app.exit();
        }
    }
	
	public void showWrongCreds(){
		TaskDialogs.error(mainContainer, "Error", "Wrong username or password!");
	}
	
	public void showUserDisabled(){
		TaskDialogs.error(mainContainer, "Error", "User account disabled! Please contact system admin!");
	}
	

}
