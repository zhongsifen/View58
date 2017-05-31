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
        deg = "135";
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
//            view.setImage(imageInp);

            fs.imageF = new ImageC4(imageInp);
            imageShow = fs.imageF.getImage();
            view.setImage(imageShow);

            fs.setup(imageInp, Fs.cap_fov);
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
        fs.setupPov(0, 0);
        fs.run();
        view.setImage(fs.imageH.getImage());
    }

    @FXML
    protected void menuItem_View_N(ActionEvent event) {
        fs.povIx--;     if (fs.povIx < 0) fs.povIx += Fs.pov_count;
        fs.setupPov(fs.povIx, fs.povIy);
        fs.run();
        view.setImage(fs.imageH.getImage());
    }

    @FXML
    protected void menuItem_View_P(ActionEvent event) {
        fs.povIx++;      if (fs.povIx == Fs.pov_count) fs.povIx = 0;
        fs.setupPov(fs.povIx, fs.povIy);
        fs.run();
        view.setImage(fs.imageH.getImage());
    }

    @FXML
    protected void menuItem_View_S(ActionEvent event) {
        fs.setupPov(Fs.pov_S[0], Fs.pov_S[1]);
        fs.run();
        view.setImage(fs.imageH.getImage());
    }

}
