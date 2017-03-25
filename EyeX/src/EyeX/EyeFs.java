package EyeX;

/**
 * Created by zhongsifen on 9/3/2017.
 */
public class EyeFs {
    private class Fl {
        int l_x, l_y;
        float fov;
        float z_x, z_y;
        float r_x, r_y;

        public Fl(int l_x, int l_y, float fov, float z_x, float z_y, float r_x, float r_y) {
            this.l_x = l_x;
            this.l_y = l_y;
            this.fov = fov;
            this.z_x = z_x;
            this.z_y = z_y;
            this.r_x = r_x;
            this.r_y = r_y;
        }
    };

//    private EyeGtFun fun;

    public Fl Cap;
    public Fl Show;

    public int[] mapK;
    public int[][] mapW;

    public void setupCap(int l_x, int l_y, float fov, float z_x, float z_y, float r_x, float r_y) {
        this.Cap = new Fl(l_x, l_y, fov, z_x, z_y, r_x, r_y);
    }

    public void setupShow(int l_x, int l_y, float fov) {
        float r = EyeGtFun.fun1_ag(fov)*2/l_x;
        this.Show = new Fl(l_x, l_y, fov, (float)l_x/2, (float)l_y/2, r, r);
        this.mapK = new int[l_x*l_y];
        this.mapW = new int[l_x*l_y][4];
    }

    public void setupShowPov(float[] pov) {
        EyeGt.kwmap(new EyeGtFun().fung_hf, pov, Cap.l_x, Cap.l_y, Cap.z_x, Cap.z_y, Cap.r_x, Cap.r_y, Show.l_x, Show.l_y, Show.z_x, Show.z_y, Show.r_x, Show.r_y, mapK,mapW);
    }

    public void run(byte[] f, byte[] h) {
        EyeGt.remapC(f, Cap.l_x, Cap.l_y, h, Show.l_x, Show.l_y, mapK, mapW, 3);
    }

    public void run(int[] f, int[] h) {
        EyeGt.remap(f, Cap.l_x, Cap.l_y, h, Show.l_x, Show.l_y, mapK, mapW);
    }

    public void run(int[][] f, int[][] h, int c) {
        for (int p=0; p<c; p++) {
            EyeGt.remap(f[p], Cap.l_x, Cap.l_y, h[p], Show.l_x, Show.l_y, mapK, mapW);
        }
    }
}
