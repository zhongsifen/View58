package sample;

import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Created by zhongsifen on 6/4/2017.
 */
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

//    int pixel = array[rowstart + x];
//
//    int alpha = ((pixel >> 24) & 0xff);
//    int red   = ((pixel >> 16) & 0xff);
//    int green = ((pixel >>  8) & 0xff);
//    int blue  = ((pixel >>   ) & 0xff);

    public ImageC4(Image image) {
        width  = new Double(image.getWidth()).intValue();
        height = new Double(image.getHeight()).intValue();
        data = new int[width*height];
        PixelReader pixelReader = image.getPixelReader();
        WritablePixelFormat<IntBuffer> writablePixelFormat = PixelFormat.getIntArgbInstance();
        pixelReader.getPixels(0, 0, width, height, writablePixelFormat, data, 0, width);
//        int k=0;
//        for (int readY = 0; readY < height; readY++) {
//            for (int readX = 0; readX < width; readX++) {
//                Color color = pixelReader.getColor(readX, readY);
//
//                data[k++] = (byte)_R(color.getRed());
//                data[k++] = (byte)_R(color.getGreen());
//                data[k++] = (byte)_R(color.getBlue());
//            }
//        }
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
        int l = width*height;
        if (l <= 0) return;

        data = new int[l];

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
