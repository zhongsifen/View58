/**
 * Created by zhongsifen on 6/4/2017.
 */
package com.example.zhongsifen.view58;

import android.graphics.Bitmap;

import EyeX.EyeX;
import EyeX.EyeFs;
import EyeX.EyeGtFun;

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

    public static final int cap_width  = 600;
    public static final int cap_height = 600;
    public static final int cap_c = 3;
    public static final float cap_fov = EyeX.DR(180);
    public static final float cap_z_x = (float)cap_width /2;
    public static final float cap_z_y = (float)cap_height/2;
    public static final float cap_r_x = EyeGtFun.fun1_af(cap_fov)*2/cap_width;
    public static final float cap_r_y = cap_r_x;

    public static final int show_width  = 640;
    public static final int show_height = 640;
    public static final int show_c = 3;
    public static final float show_fov = EyeX.DR(60);
    
    public static final float u = EyeX.DR(30);
    public static final float pov[] = {
            0,
            +1*u,
            +2*u,
            +3*u,
            +4*u,
            +5*u,
            +6*u,
            +7*u,
            -7*u,
            -6*u,
            -5*u,
            -4*u,
            -3*u,
            -2*u,
            -1*u,
    };

    EyeFs eyeFs;
    ImageC4 imageF;
    ImageC4 imageH;
    int povIx, povIy;
    public float pov_a[][];
    public int pov_a_count;

    public Fs() {
        eyeFs = new EyeFs();
        imageF = null;
        imageH = null;

        pov_a_count = 12;
        pov_a = new float[pov_a_count][2];
        float w = (float)Math.PI*2/pov_a_count;
        float r = w*7/2;
        float s = (float)Math.sin(w);
        float t = (float)Math.cos(w);
        pov_a[0][0] = 0*s;
        pov_a[0][1] = r*t;
        for (int i=1; i<pov_a_count; i++) {
            pov_a[i][0] = pov_a[i-1][0]*(+t) + pov_a[i-1][1]*(+s);
            pov_a[i][1] = pov_a[i-1][0]*(-s) + pov_a[i-1][1]*(+t);
        }
    }

    public boolean setup() {
        eyeFs.setupCap(cap_width, cap_height, cap_fov, cap_z_x, cap_z_y, cap_r_x, cap_r_y);
        eyeFs.setupShow(show_width, show_height, show_fov);
        eyeFs.setupShowPov(pov_a[0]);

        return true;
    }

    public boolean setup(Bitmap image, int deg) {
        imageF = new ImageC4(image);
        imageH = new ImageC4(show_width, show_height);
        setup(imageF, EyeX.DR(deg)/2, imageH, show_fov);

        return true;
    }

    public boolean setup(ImageC4 imageF, float fovF, ImageC4 imageH, float fovH) {
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
        eyeFs.setupShowPov(show_pov[pov_zero]);

        return true;
    }

    public boolean setupPov(float Pov[]) {
        eyeFs.setupShowPov(Pov);

        return true;
    }

    public boolean run() {
        eyeFs.run(imageF.data, imageH.data);

        return true;
    }

    public boolean run(byte[] dataF, byte[] dataH) {
        eyeFs.run(dataF, dataH);

        return true;
    }
}
