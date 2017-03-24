package sample;

import javafx.scene.image.*;
import javafx.scene.paint.Color;

/**
 * Created by zhongsifen on 24/3/2017.
 */
public class ImageP {
    int width;
    int height;
    int c;
    int data[][];

    private static int _R(double f) {
        return new Double(f*255).intValue();
    }

    private static double _S(int h) {
        return (double)h / 255;
    }

    public ImageP(int width, int height, int c) {
        this.width  = width;
        this.height = height;
        this.c = c;
        this.data = new int[c][width*height];
    }

    public ImageP(Image image) {
        width  = new Double(image.getWidth ()).intValue();
        height = new Double(image.getHeight()).intValue();
        c = 3;
        data = new int[c][width*height];

        PixelReader pixelReader = image.getPixelReader();
        for (int k=0,readY = 0; readY < height; readY++) {
            for (int readX = 0; readX < width;  readX++,k++) {
                Color color = pixelReader.getColor(readX, readY);
                data[0][k] = _R(color.getRed());
                data[1][k] = _R(color.getGreen());
                data[2][k] = _R(color.getBlue());
            }
        }

    }

    public Image getImage() {
        if (isEmpty()) return null;

        WritableImage wImage = new WritableImage(width, height);
        PixelWriter pixelWriter = wImage.getPixelWriter();

        for(int k=0,readY=0; readY<height; readY++){
            for(int readX=0; readX<width;  readX++,k++){
                pixelWriter.setColor(readX, readY, Color.rgb(data[0][k], data[1][k], data[2][k]));
            }
        }

        return wImage;
    }

    boolean isEmpty() {
        return (width <= 0 || height <= 0);
    }

}
