package controller;

import javafx.scene.control.TextArea;
import java.io.File;
import java.net.URL;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import java.time.LocalDate;

public class AddTaskViewController {
	
	@FXML private TextField       nameField;
    @FXML private TextArea        descriptionArea;
    @FXML private DatePicker      startDatePicker;
    @FXML private DatePicker      deadlinePicker;
    @FXML private ComboBox<Integer> priorityCombo;
    @FXML private ComboBox<String>  statusCombo;
    @FXML private Label           statusLabel;

	@FXML
    private void handleSubmit(ActionEvent event) {
        String name        = nameField.getText().trim();
        String description = descriptionArea.getText().trim();
        LocalDate start    = startDatePicker.getValue();
        LocalDate deadline = deadlinePicker.getValue();
        Integer priority   = priorityCombo.getValue();
        String status      = statusCombo.getValue();
        
        
        //Check user input is valid
        if (name.isEmpty()) {
            statusLabel.setText("Name canot be empty");
            return;
        }
        if (start == null || deadline == null) {
            statusLabel.setText("Please select start date and deadline");
            return;
        }
        if (!deadline.isAfter(start)) {
            statusLabel.setText("Deadline must be after start date");
            return;
        }
        
        
        
        
        String insertSQL =
                "INSERT INTO projects " +
                "(name, description, start_date, deadline, priority, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

//        System.out.println("Name:        " + name);
//        System.out.println("Description: " + description);
//        System.out.println("Start Date:  " + start);
//        System.out.println("Deadline:    " + deadline);
//        System.out.println("Priority:    " + priority);
//        System.out.println("Status:      " + status);
        
        try (Connection conn = DatabaseManager.getConnection()){
        	try (Statement stmt = conn.createStatement()) {
                stmt.execute(createTableSQL);
            }
        }catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error：" + e.getMessage());
        }

        statusLabel.setText("读取完成，见控制台输出");
    }
	
    @FXML
    private void handleReset() {
        nameField.clear();
        descriptionArea.clear();
        startDatePicker.setValue(null);
        deadlinePicker.setValue(null);
        priorityCombo.setValue(3);
        statusCombo.setValue("NEW");
        statusLabel.setText("");
    }
	
}
