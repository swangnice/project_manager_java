package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // 加载位于 resources/sample/Main.fxml 的 FXML 文件
        Parent root = FXMLLoader.load(getClass().getResource("/sample/Main.fxml"));
        
        // 创建场景，设置窗口大小为 400x300
        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("JavaFX + FXML Example");
        stage.setScene(scene);
        stage.show();  // 显示窗口
    }

    public static void main(String[] args) {
        launch(args);  // 启动 JavaFX 应用
    }
}
