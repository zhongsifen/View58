package sample;

import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Created by zhongsifen on 6/4/2017.
 */
//    int pixel = data[k];
//
//    int alpha = ((pixel >> 24) & 0xff);
//    int red   = ((pixel >> 16) & 0xff);
//    int green = ((pixel >>  8) & 0xff);
//    int blue  = ((pixel >>   ) & 0xff);
public class ImageC4 {
    public int width;
    public int height;

    public int[] data;

    public ImageC4() {
        width = height = 0;
        data = null;
    }

    public ImageC4(int width, int height) {
        this.width  = width;
        this.height = height;
        this.data = new int[width*height];
    }

    public ImageC4(int width, int height, int[] data) {
        this.width  = width;
        this.height = height;
        this.data = data;
    }

    public ImageC4(Image image) {
        width  = new Double(image.getWidth()).intValue();
        height = new Double(image.getHeight()).intValue();
        data = new int[width*height];
        PixelReader pixelReader = image.getPixelReader();
        WritablePixelFormat<IntBuffer> writablePixelFormat = PixelFormat.getIntArgbInstance();
        pixelReader.getPixels(0, 0, width, height, writablePixelFormat, data, 0, width);
    }

    boolean isEmpty() {
        return (width <= 0 || height <= 0);
    }

    public Image getImage() {
        if (isEmpty()) return null;
        WritableImage wImage = new WritableImage(width, height);
        PixelWriter pixelWriter = wImage.getPixelWriter();
        int k=0;
        for(int readY=0;readY<height;readY++){
            for(int readX=0; readX<width;readX++){
                int pixel = data[k++];
                int alpha = ((pixel >> 24) & 0xff);
                int r = ((pixel >> 16) & 0xff);
                int g = ((pixel >>  8) & 0xff);
                int b = ((pixel      ) & 0xff);
                pixelWriter.setColor(readX, readY, Color.rgb(r, g, b));
            }
        }

        return wImage;
    }

    public void setImage(Image image) {
        width  = (int)image.getWidth();
        height = (int)image.getHeight();
        data = new int[width*height];
        PixelReader pixelReader = image.getPixelReader();
        WritablePixelFormat<IntBuffer> writablePixelFormat = PixelFormat.getIntArgbInstance();
        pixelReader.getPixels(0, 0, width, height, writablePixelFormat, data, 0, width);
    }
}
