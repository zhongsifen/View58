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

    @FXML
    private ImageView currentFrame;

    @FXML
    protected void menuItem_Quit(ActionEvent event) {
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        ByteBuffer byteBuffer = webcam.getImageBytes();
        Image image = new Image(new ByteInputStream(byteBuffer.array()));
        currentFrame.setImage(webcam.getImage());

//        System.exit(0);
    }

    @FXML
    protected void menuItem_Open(ActionEvent event) {
        try {
            fImage = new Image(new FileInputStream(new FileChooser().showOpenDialog(null)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (!fImage.isError()) {
            show = fImage;
            currentFrame.setImage(show);

            width  = (int)fImage.getWidth();
            height = (int)fImage.getHeight();
            data = new byte[width*height*4];

            PixelReader pr = fImage.getPixelReader();
            WritablePixelFormat<ByteBuffer> wpf = WritablePixelFormat.getByteBgraInstance();
            pr.getPixels(0, 0, width, height, wpf, data, 0, width*4);
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

    private byte data[];
    private int width;
    private int height;

    public byte[] getData() { return data; }
    public int getWidth () { return width; }
    public int getHeight() { return height;}
}
