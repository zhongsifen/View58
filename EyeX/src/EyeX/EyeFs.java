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

    float[][] cnrO;
    float[][] cnrP;

    public int[] mapK;
    public int[][] mapW;

    public void setCap(int l_x, int l_y, float fov, float z_x, float z_y, float r_x, float r_y) {
        this.Cap = new Fl(l_x, l_y, fov, z_x, z_y, r_x, r_y);
    }

    public void setShow(int l_x, int l_y, float fov) {
        this.Show = new Fl(l_x, l_y, fov, (float)l_x/2, (float)l_y/2, fov*2/l_x, fov*2/l_y);
    }
}
