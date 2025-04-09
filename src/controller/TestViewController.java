package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TestViewController {

    // bundle label in FXML
    @FXML
    private Label label;

    // onAction
    @FXML
    private void handleButtonAction() {
        // 改变 label 的文本内容
        label.setText("Button Clicked!");
    }
}
