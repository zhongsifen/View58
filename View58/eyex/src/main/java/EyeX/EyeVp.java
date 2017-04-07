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
}
