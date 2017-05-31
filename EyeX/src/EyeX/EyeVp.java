package EyeX;

/**
 * Created by zhongsifen on 13/3/2017.
 */
public class EyeVp {
    public static void bc(float g[], float f[], float p[]) {
        float w = 1 - p[0]*g[0] - p[1]*g[1];
        f[0] = (g[0] + p[0])/w;
        f[1] = (g[1] + p[1])/w;
    }

    public static void rt(float g[], float f[], float p[]) {
        float x = p[0];
        float y = p[1];
        float r = (float)Math.sqrt(x*x + y*y);
        if (r > Float.MIN_NORMAL) {
            x /= r;
            y /= r;
        }
        else {
            x = 0;
            y = 1;
        }
        f[0] = g[0]*(+y) + g[1]*(+x);
        f[1] = g[0]*(-x) + g[1]*(+y);
    }
}
