package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import utils.GlobalState;

/*
 * @TODO Change to load fxml to content
 *
 */

public class SidebarController {
	
	private StackPane contentPane;
	
	@FXML
	private Label label;
	
    public void welcome() {
        // 改变 label 的文本内容
    	System.out.print("Name: "+ GlobalState.getInstance().getCurrentUser());
        label.setText("Hello, "+ GlobalState.getInstance().getCurrentUser());
    }

    //  contentPane
    public void setContentPane(StackPane contentPane) {
        this.contentPane = contentPane;
    }


    @FXML
    private void handleHome() {
    	loadContent("/view/HomeView.fxml");
    }

    @FXML
    private void handleTasks() {
        loadContent("/view/TaskView.fxml");
    }

    @FXML
    private void handleEmployees() {
    	loadContent("/view/EmployeeView.fxml");
    }
    
    @FXML
    private void addTask() {
    	loadContent("/view/AddTaskView.fxml");
    }
    
    @FXML
    private void addEmployee() {
    	loadContent("/view/AddEmployeeView.fxml");
    }
    
    
    private void loadContent(String fxmlPath) {
        try {
            Node node = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentPane.getChildren().setAll(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}