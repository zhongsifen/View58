package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load(); // FXMLLoader.load(getClass().getResource("sample.fxml"));
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
