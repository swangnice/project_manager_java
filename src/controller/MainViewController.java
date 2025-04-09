package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainViewController {
    // 注解 @FXML 绑定 FXML 文件中 id 为 label 的控件
    @FXML
    private Label label;

    // 定义按钮点击时调用的方法，名称和 FXML 文件中的 onAction 方法名保持一致
    @FXML
    private void handleButtonAction() {
        // 改变 label 的文本内容
        label.setText("Button Clicked!");
    }

}
