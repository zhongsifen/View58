package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.stage.FileChooser;
//import org.opencv.core.Mat;
//import org.opencv.core.MatOfByte;
//import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.URL;

public class Controller {

    @FXML
    private ImageView currentFrame;

    @FXML
    protected void menuItem_Quit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    protected void menuItem_Open(ActionEvent event) {
        try {
            fImage = new Image(new FileInputStream(new FileChooser().showOpenDialog(null)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (!fImage.isError()) {
            currentFrame.setImage(fImage);

//            width  = (int)fImage.getWidth();
//            height = (int)fImage.getHeight();
//            PixelFormat pf = fImage.getPixelReader().getPixelFormat();

        }
        else {
            System.out.println(path + " is not image");
        }
    }

    protected void setClosed()
    {
        return;
    }

    private File file;
    private String path;
    private Image fImage;
    private Image show;

    private byte f[];
    private int width;
    private int height;
}
