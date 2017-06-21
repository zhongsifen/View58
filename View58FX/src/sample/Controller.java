package sample;

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

    protected void setClosed() {
        imageShow = null;
        view.setImage(imageShow);
    }

    @FXML
    private ImageView view;
    @FXML
    private String deg;

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
            fs.imageF = new ImageC4(imageInp);
            imageShow = fs.imageF.getImage();
            view.setImage(imageShow);

            fs.setup(imageInp, Fs.cap_fov);
            fs.setupMap();
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

    @FXML
    protected void menuItem_View_0(ActionEvent event) {
        fs.pov_a_index = 0;
    }

    @FXML
    protected void menuItem_View_S(ActionEvent event) {
        fs.pov_a_index = 0;
        fs.run();
        view.setImage(fs.imageH.getImage());
    }

    @FXML
    protected void menuItem_View_W(ActionEvent event) {
        fs.pov_a_index--;   if (fs.pov_a_index < 0) fs.pov_a_index += fs.pov_a_count;
        fs.run();
        view.setImage(fs.imageH.getImage());
    }

    @FXML
    protected void menuItem_View_E(ActionEvent event) {
        fs.pov_a_index++;   if (fs.pov_a_index == fs.pov_a_count) fs.pov_a_index = 0;
        fs.run();
        view.setImage(fs.imageH.getImage());
    }

    @FXML
    protected void menuItem_View_N(ActionEvent event) {
        fs.pov_a_index--;   if (fs.pov_a_index < 0) fs.pov_a_index += fs.pov_a_count;
    }

    @FXML
    protected void menuItem_View_P(ActionEvent event) {
        fs.pov_a_index++;   if (fs.pov_a_index == fs.pov_a_count) fs.pov_a_index = 0;
    }

}
