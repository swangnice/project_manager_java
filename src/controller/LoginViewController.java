package controller;


import java.io.File;
import java.net.URL;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.text.Text;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;

import utils.MacroDef;

public class LoginViewController {
    @FXML private Text actiontarget;
    
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
            Scene scene = new Scene(root, 1920, 1080);
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
}

