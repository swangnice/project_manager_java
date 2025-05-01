/**
 * @file 
 * @brief The first shown window
 */

package controller;


import java.io.File;
import java.net.URL;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import utils.GlobalState;
import utils.MacroDef;
import utils.PasswordUtil.*;
import db.DatabaseManager; 

public class LoginViewController {
    @FXML private Text actiontarget;
    

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
    }
    
    @FXML private void handleOpenMainWindow(ActionEvent event) {
//    	System.out.println("handleOpenMainWindow");
    	try {
        	String fxmlPath = MacroDef.ROOT_DIRECTORY + MacroDef.MAIN_FXML_DIR;
        	URL fxmlUrl = new File(fxmlPath).toURI().toURL();
        	Parent root = FXMLLoader.load(fxmlUrl);
        	
        	Stage mainStage = new Stage();
        	mainStage.setTitle("Project Manager - Main");
            
            // New scene
            Scene scene = new Scene(root, 800, 600);
            mainStage.setScene(scene);
            
            // optional
            mainStage.initModality(Modality.APPLICATION_MODAL);
            
            // show new window
            mainStage.show();
            
            Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            currentStage.close();
    		
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    @FXML private void isMatch(ActionEvent event) {
    	String username = usernameField.getText();
        String password = passwordField.getText();
        
        if (username.isEmpty() || password.isEmpty()) {
            actiontarget.setText("Please enter username and password.");
        }
        System.out.println(password);
        
        
        String inputHash = utils.PasswordUtil.hashPassword("salt" + password);
        String storedHash = db.DatabaseManager.getStoredPwdHash(username);
        System.out.println(inputHash);
        System.out.println(storedHash);
        String storedLastName = db.DatabaseManager.getStoredLastName(username);
        
        if (inputHash.equals(storedHash)) {
        	actiontarget.setText("Welcome, " + storedLastName);
        	
        	GlobalState.getInstance().setCurrentUser(storedLastName);
        	handleOpenMainWindow(event);
        	
        }
        else {
        	actiontarget.setText("Username/Password Wrong!");
        }
        
        
           
    }
    
}

