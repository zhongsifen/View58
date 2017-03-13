package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root;
        root = loader.load();
        primaryStage.setTitle("View58");
        primaryStage.setScene(new Scene(root, 1280, 960));
        primaryStage.show();

        Controller controller = loader.getController();
        primaryStage.setOnCloseRequest((new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we)
            {
                controller.setClosed();
            }
        }));

    }

//    static {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//    }

    public static void main(String[] args) {
        launch(args);
    }
}
