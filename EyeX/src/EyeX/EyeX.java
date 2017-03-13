package EyeX;

/**
 * Created by zhongsifen on 4/3/2017.
 */
public class EyeX {
    private static final int SR_e = 12;
    private static final int SR_f = 1 << SR_e;

    public static int SR_s(float v) { return (int)java.lang.Math.round(v * SR_f); }
    public static int SR_r(int a) { return (a >> SR_e);}
}
