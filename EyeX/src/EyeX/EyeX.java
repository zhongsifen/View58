package EyeX;

/**
 * Created by zhongsifen on 4/3/2017.
 */
public class EyeX {
    private static final int _e = 12;
    private static final int _SR = 1 << _e;

    static int _s(float f) { return (int)java.lang.Math.round(f * _SR); }
    static int _r(int a) { return (a >> _e);}
}
