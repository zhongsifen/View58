package EyeX;

/**
 * Created by zhongsifen on 4/3/2017.
 */
public class EyeX {
    protected static int _SR = 1 << 12;

    static int _s(float f) { return (int)java.lang.Math.round(f * _SR); }
    static int _r(int a) { return (a >> 12);}
}
