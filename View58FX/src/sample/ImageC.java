package sample;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.function.DoubleToIntFunction;

/**
 * Created by zhongsifen on 18/3/2017.
 */

public class ImageC {
    public int width;
    public int height;
    public int c;

    public byte[] data;
    public int[] c4;

    public ImageC() {
        width = height = 0;
        data = null;
        c4 = null;
    }

    public ImageC(int width, int height, int c) {
        this.width  = width;
        this.height = height;
        this.c = c;
        this.data = new byte[width*height*c];
    }

    public ImageC(int width, int height, int c, byte[] data) {
        this.width  = width;
        this.height = height;
        this.c = c;
        this.data = data;
    }

    public ImageC(Image image) {
        width  = new Double(image.getWidth()).intValue();
        height = new Double(image.getHeight()).intValue();
        c = 3;
        data = new byte[width*height*c];
        PixelReader pixelReader = image.getPixelReader();

        int k=0;
        for (int readY = 0; readY < height; readY++) {
            for (int readX = 0; readX < width; readX++) {
                Color color = pixelReader.getColor(readX, readY);
                data[k++] = (byte)_R(color.getRed());
                data[k++] = (byte)_R(color.getGreen());
                data[k++] = (byte)_R(color.getBlue());
            }
        }
    }

    boolean isEmpty() {
        return (width <= 0 || height <= 0);
    }

    private static byte _R(double f) {
        int h = new Double(f*256).intValue();
        if (h > 0xFF) h = 0xFF;
        if (h < 0x00) h = 0x00;

        return (byte)h;
    }

    private static double _S(byte h) {
        double f = (double)h / 0x100;

        return f;
    }

    private static int _T(byte h) {
        int g = Byte.toUnsignedInt(h);

        return g;
    }

    public Image getImage() {
        if (isEmpty()) return null;

        WritableImage wImage = new WritableImage(width, height);
        PixelWriter pixelWriter = wImage.getPixelWriter();

        int k=0;
        for(int readY=0;readY<height;readY++){
            for(int readX=0; readX<width;readX++){
                int r = _T(data[k++]), g = _T(data[k++]), b = _T(data[k++]);
                pixelWriter.setColor(readX, readY, Color.rgb(r, g, b));
            }
        }

        return wImage;
    }

    public void setImage(Image image) {
        width  = (int)image.getWidth();
        height = (int)image.getHeight();
        int l = width*height;
        if (l <= 0) return;

        data = new byte[l*3];

        PixelReader pixelReader = image.getPixelReader();

        int k=0;
        for (int readY = 0; readY < height; readY++) {
            for (int readX = 0; readX < width; readX++) {
                Color color = pixelReader.getColor(readX, readY);
                data[k++] = (byte)_R(color.getRed());
                data[k++] = (byte)_R(color.getGreen());
                data[k++] = (byte)_R(color.getBlue());
            }
        }
    }
}
