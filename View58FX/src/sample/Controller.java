package sample;

import com.github.sarxos.webcam.Webcam;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.image.*;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.*;
import java.nio.ByteBuffer;

import static javafx.scene.image.PixelFormat.getByteBgraInstance;

public class Controller {

    private Image imageInp;
    private Image imageShow;
    private Image imageBack;

    public Fs fs;

    public void init() {
        imageBack = view.getImage();
        fs = new Fs();
    }

    @FXML
    private ImageView view;

    @FXML
    protected void menuItem_Quit(ActionEvent event) {

        System.exit(0);
    }

    @FXML
    protected void menuItem_Open(ActionEvent event) {
        try {
            imageInp = new Image(new FileInputStream(new FileChooser().showOpenDialog(null)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (!imageInp.isError()) {
//            view.setImage(imageInp);

            fs.imageF = new ImageC4(imageInp);
            imageShow = fs.imageF.getImage();
            view.setImage(imageShow);

            fs.setup(imageInp, 135);
        }
    }

    @FXML
    protected void menuItem_Close(ActionEvent event) {
        view.setImage(imageBack);
    }

    @FXML
    protected void menuItem_Play(ActionEvent event) {
        fs.run();
        view.setImage(fs.imageH.getImage());
    }

    protected void setClosed() {
        imageShow = null;
        view.setImage(imageShow);
    }
}
