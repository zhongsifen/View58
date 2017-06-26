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

    public Fl Cap;
    public Fl Show;

    public void setupCap(int l_x, int l_y, float fov, float z_x, float z_y, float r_x, float r_y) {
        this.Cap = new Fl(l_x, l_y, fov, z_x, z_y, r_x, r_y);
    }

    public void setupCap(int l_x, int l_y, float fov) {
        float r = EyeGtFun.fun1_af(fov)*2/l_x;
        this.Cap = new Fl(l_x, l_y, fov, (float)l_x/2, (float)l_y/2, r, r);
    }

    public void setupShow(int l_x, int l_y, float fov) {
        float r = EyeGtFun.fun1_ag(fov)*2/l_x;
        this.Show = new Fl(l_x, l_y, fov, (float)l_x/2, (float)l_y/2, r, r);
    }

    public void setupPov(EyeGtFun.FunG funG, float[] param, EyeMap map) {
        EyeGt.kwmap(funG, param, Cap.l_x, Cap.l_y, Cap.z_x, Cap.z_y, Cap.r_x, Cap.r_y, Show.l_x, Show.l_y, Show.z_x, Show.z_y, Show.r_x, Show.r_y, map.mapK, map.mapW);
    }

    public void setupPov(float[] param, EyeMap map) {
        EyeGtFun.FunG_bf funG = new EyeGtFun().fung_bf;
        EyeGt.kwmap(funG, param, Cap.l_x, Cap.l_y, Cap.z_x, Cap.z_y, Cap.r_x, Cap.r_y, Show.l_x, Show.l_y, Show.z_x, Show.z_y, Show.r_x, Show.r_y, map.mapK, map.mapW);
    }

    public void run(int[] f, int[] h, EyeMap map) {
        EyeGt.remapC4(f, Cap.l_x, Cap.l_y, h, Show.l_x, Show.l_y, map.mapK, map.mapW);
    }

    public void run_a(int[] f, int[] h, EyeMap map) {
        EyeGt.remapC4_a(f, Cap.l_x, Cap.l_y, h, Show.l_x, Show.l_y, map.mapK);
    }

    public void run_s(int[] f, int[] h, EyeMap map) {
        EyeGt.remapC4_s(f, Cap.l_x, Cap.l_y, h, Show.l_x, Show.l_y, map.mapK);
    }

}
