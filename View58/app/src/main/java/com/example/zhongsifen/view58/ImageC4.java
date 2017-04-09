package com.example.zhongsifen.view58;

import android.graphics.Bitmap;

/**
 * Created by zhongsifen on 7/4/2017.
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

    public ImageC4(Bitmap image) {
        width  = image.getWidth();
        height = image.getHeight();
        data = new int[width*height];
        image.getPixels(data, 0, width, 0, 0, width, height);
    }

    boolean isEmpty() {
        return (width <= 0 || height <= 0);
    }

    public Bitmap getImage() {
        if (isEmpty()) return null;
        Bitmap wImage = Bitmap.createBitmap(data, width, height, Bitmap.Config.ARGB_8888);
        return wImage;
    }

    public void setImage(Bitmap image) {
        width  = image.getWidth();
        height = image.getHeight();
        data = new int[width*height];
        image.setPixels(data, 0, width, 0, 0, width, height);
    }
}
