package sample;
import javafx.scene.image.Image;
import EyeX.EyeFs;
import EyeX.EyeMap;
import EyeX.EyeGtFun;
import EyeX.EyeX;

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

    public static final int cap_width  = 1500;
    public static final int cap_height = 1500;
    public static final int cap_c = 3;
    public static final float cap_fov = EyeX.DR(180);
    public static final int show_width  = 480;
    public static final int show_height = 480;
    public static final int show_c = 3;
    public static final float show_fov = EyeX.DR(60);

    public float[] pov_a[];
    public EyeMap[] pov_a_map;
    public int pov_a_count;
    public int pov_a_index;

    EyeFs eyeFs;
    ImageC4 imageF;
    ImageC4 imageH;

    public Fs() {
        eyeFs = new EyeFs();
        imageF = null;
        imageH = null;

        pov_a_count = 12;
        pov_a = new float[pov_a_count][2];
        float w = (float)Math.PI*2/pov_a_count;
        float r = EyeX.DR(210);
        float s = (float)Math.sin(w);
        float t = (float)Math.cos(w);
        pov_a[0][0] = 0*s;
        pov_a[0][1] = r*t;
        for (int i=1; i<pov_a_count; i++) {
            pov_a[i][0] = pov_a[i-1][0]*(+t) + pov_a[i-1][1]*(+s);
            pov_a[i][1] = pov_a[i-1][0]*(-s) + pov_a[i-1][1]*(+t);
        }
    }

    public boolean setup(Image image, float fov) {
        imageF = new ImageC4(image);
        imageH = new ImageC4(show_width, show_height);
        setup(imageF, fov, imageH, show_fov);

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

        setupMap();

        return true;
    }

    public boolean setupMap() {
        pov_a_map = new EyeMap[pov_a_count];
        int l = imageH.width*imageH.height;
        for (int i=0; i<pov_a_count; i++) {
            pov_a_map[i] = new EyeMap(l);
            eyeFs.setupPov(pov_a[i], pov_a_map[i]);
        }

        return true;
    }

    public boolean run(EyeMap map) {
        eyeFs.run(imageF.data, imageH.data, map);

        return true;
    }

    public boolean run_a(EyeMap map) {
        eyeFs.run_a(imageF.data, imageH.data, map);

        return true;
    }

    public boolean run_s(EyeMap map) {
        eyeFs.run_s(imageF.data, imageH.data, map);

        return true;
    }

    public boolean run() {
        eyeFs.run(imageF.data, imageH.data, pov_a_map[pov_a_index]);

        return true;
    }

}
