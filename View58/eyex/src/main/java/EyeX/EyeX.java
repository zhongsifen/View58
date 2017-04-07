package EyeX;

/**
 * Created by zhongsifen on 4/3/2017.
 */
public class EyeX {
    private static final int SR_e = 12;
    private static final int SR_f = 1 << SR_e;

    public static byte TR(int a) { if (a < 0) a = 0; else if (a > 0xFF) a = 0xFF; return new Integer(a).byteValue();}
    public static int SR_s(float v) { return (int)java.lang.Math.floor(v * SR_f); }
    public static int SR_r(int a) { return (a >> SR_e);}

    public static float DR(int d) { return (float)(Math.PI/180)*d; }
}
