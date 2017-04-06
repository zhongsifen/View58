/**
 * Created by zhongsifen on 6/4/2017.
 */
package com.example.zhongsifen.view58;

import android.graphics.Color;

import EyeX.EyeFs;
import EyeX.EyeGtFun;
import EyeX.EyeX;

/**
 * Created by zhongsifen on 20/3/2017.
 */
public class Fs {
    private static void ZR(int width, int heigit, float fov, float z[], float r[], EyeGtFun.Fun1 gtFun) {
        float l_x = new Integer(width ).floatValue();
        float l_y = new Integer(heigit).floatValue();
        float u = gtFun.fun1(fov);
        z[0] = l_x/2;
        z[1] = l_y/2;
        r[0] = u*2/l_x;
        r[1] = r[0];
    }

    public static final int cap_width  = 1280;
    public static final int cap_height =  960;
    public static final int cap_c = 3;
    public static final float cap_fov = EyeX.DR(135)/2;
    public static final float cap_z_x = (float)cap_width/2;
    public static final float cap_z_y = (float)cap_height/2;
    public static final float cap_r_x = EyeGtFun.fun1_af(cap_fov)*2/cap_width;
    public static final float cap_r_y = cap_r_x;

    public static final int show_width  = 640;
    public static final int show_height = 640;
    public static final int show_c = 3;
    public static final float show_fov = cap_fov/3; // EyeX.DR(60)/2;

    public static final float show_pov[][] = {
            {show_fov * 0, 0},
            {show_fov * 1, 0},
            {show_fov * 2, 0},
    };

    EyeFs eyeFs;
    ImageP imageF;
    ImageP imageH;

    public Fs() {
        eyeFs = new EyeFs();
        imageF = null;
        imageH = null;
    }

    public boolean setup() {
        eyeFs.setupCap(cap_width, cap_height, cap_fov, cap_z_x, cap_z_y, cap_r_x, cap_r_y);
        eyeFs.setupShow(show_width, show_height, show_fov);
        eyeFs.setupShowPov(show_pov[2]);

        return true;
    }

    public boolean setup(Image image, int deg) {
        imageF = new ImageP(image);
        imageH = new ImageP(show_width, show_height, show_c);
        setup(imageF, EyeX.DR(deg)/2, imageH, show_fov);

        return true;
    }

    public boolean setup(ImageP imageF, float fovF, ImageP imageH, float fovH) {
        int l_x = imageF.width;
        int l_y = imageF.height;
        float z_x = (float)l_x/2;
        float z_y = (float)l_y/2;
        float r_x = EyeGtFun.fun1_af(fovF)*2/l_x;
        float r_y = r_x;
        eyeFs.setupCap(l_x, l_y, fovF, z_x, z_y, r_x, r_y);

        int m_x = imageH.width;
        int m_y = imageH.height;
        float w_x = (float)m_x/2;
        float w_y = (float)m_y/2;
        eyeFs.setupShow(m_x, m_y, fovH);
        eyeFs.setupShowPov(show_pov[2]);

        return true;
    }

    public boolean setupPov(float Pov[]) {

        return true;
    }

    public boolean run() {
        eyeFs.run(imageF.data, imageH.data, imageF.c);

        return true;
    }

    public boolean run(byte[] dataF, byte[] dataH) {
        eyeFs.run(dataF, dataH);

        return true;
    }
}
