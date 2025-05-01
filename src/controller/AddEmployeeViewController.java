package controller;

import java.io.File;
import java.net.URL;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
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

import java.io.File;
import java.sql.*;


public class AddEmployeeViewController {
	
	@FXML
	private TextField firstNameField;
	
	@FXML
	private TextField lastNameField;

	@FXML
	private TextField usernameField;
	
	@FXML
	private TextField passwordField1;
	
	@FXML
	private TextField passwordField2;

	@FXML
	private TextField positionField;

	@FXML
	private TextField emailField;
	
	@FXML
	private TextField phoneField;
	
    @FXML
    private Label label;
    
//    public static String newFirstName;
//    public static String newLastName;
//    public static String newUsername;
//    public static String newPosition;
//    public static String newEmail;
//    public static String newPhoneNumber;
//    public static String newPassword1;
//    public static String newPassword2;
//    public static String newPassword;
    
    private static boolean addEmployee(
            String firstName,
            String lastName,
            String phone,
            String email,
            String role,
            String username,
            String pwdHash
    ){
    	
    	String sql = "INSERT INTO members " +
                 "(first_name, last_name, phone, email, role, username, pwd_hash) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?)";
    	try (Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);)
    	{
    		stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, phone);
            stmt.setString(4, email);
            stmt.setString(5, role);
            stmt.setString(6, username);
            stmt.setString(7, pwdHash);
 
            return stmt.executeUpdate() > 0; 
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
    }
    

    private static boolean isUsernameExist(String username) {
        String sql = "SELECT COUNT(*) FROM members WHERE username = ?";
        try (Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);) 
        {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
	@FXML
	private void handleSubmit() {
		String fn = firstNameField.getText().trim();
        String ln = lastNameField .getText().trim();
        String un = usernameField  .getText().trim();
        String pos= positionField  .getText().trim();
        String em = emailField     .getText().trim();
        String ph = phoneField     .getText().trim();
        String pw1= passwordField1 .getText();
        String pw2= passwordField2 .getText();
        
        // Make sure user input valid
        
        if (fn.isEmpty() || ln.isEmpty() || em.isEmpty() || un.isEmpty() || pos.isEmpty() || pw1.isEmpty() || ph.isEmpty() ) {
            label.setText("Please fill all cells");
            return;
        }
        if (!pw1.equals(pw2)) {
            label.setText("Passwords don't match!");
            return;
        }
        if (isUsernameExist(un)) {
        	label.setText("Username exists, change one.");
            return;
        }
        
        // Hash the password
        String salt = "salt";
    	String saltedPassword = salt + pw1;
        String hash_pwd = utils.PasswordUtil.hashPassword(saltedPassword);
        System.out.println(hash_pwd);
        
        boolean ok = addEmployee(fn, ln, ph, em, pos, un, hash_pwd);
        
        if(ok){
        	label.setText("User Added Successfully");
        	return;
        } else {
        	label.setText("Error Occurse");
        	return;
        }
	}
	
	

	@FXML
	private void handleReset() {
	    usernameField.clear();
	    positionField.clear();
	    emailField.clear();
	}

}
