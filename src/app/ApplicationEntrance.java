/**
 * @file This is Entrance file, start the application through this class.
 * 
 * @brief The entrance file, run this to start our app
 */

package app;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.GlobalState;
import utils.MacroDef;
import utils.PasswordUtil;
import db.DatabaseManager;


public class ApplicationEntrance extends Application{
    @Override
    public void start(Stage stage) throws Exception {
    	
//    	String hash = PasswordUtil.hashPassword("password");
//    	
//    	System.out.println(hash);
    	
    	DatabaseManager.initializeDB();

    	String fxmlPath = MacroDef.ROOT_DIRECTORY + MacroDef.LOGIN_FXML_DIR;
    	URL fxmlUrl = new File(fxmlPath).toURI().toURL();
    	Parent root = FXMLLoader.load(fxmlUrl);
        
        // set the window size 400x300
        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("User Login Portal");
        stage.setScene(scene);
        
        stage.show();  // show the window
    }
    
    @Override
    public void stop() throws Exception {
        DatabaseManager.closeConnection();
        System.out.println("Database connection closed.");
    }

    public static void main(String[] args) {
        launch(args);  // launch the app
    }
}
