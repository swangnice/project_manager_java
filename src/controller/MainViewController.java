package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;

public class MainViewController {

    @FXML
    private Label label;

    @FXML
    private StackPane contentPane;
    
    @FXML
    private VBox sidebar;

    

    @FXML
    public void initialize() {
        // 获取 SidebarController 实例
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Sidebar.fxml"));
            VBox sidebarContent = loader.load();
            SidebarController sidebarController = loader.getController();
            
            sidebarController.welcome();

            // 
            sidebarController.setContentPane(contentPane);

            // 
            sidebar.getChildren().setAll(sidebarContent.getChildren());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }	
    
    @FXML
    private void handleButtonAction() {
        label.setText("按钮已点击！");
    }

    @FXML
    private void handleHome() {
        label.setText("欢迎来到主页！");
    }

    @FXML
    private void handleSettings() {
        label.setText("这是设置页面！");
    }

    @FXML
    private void handleAbout() {
        label.setText("关于我们内容展示！");
    }
}