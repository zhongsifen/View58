package EyeX;

import static java.lang.Math.*;
import static java.lang.Math.abs;

/**
 * Created by zhongsifen on 4/3/2017.
 */
public class EyeGtFun {
    public EyeGtFun() {
        fung_gf = new FunG_gf();
        fung_hf = new FunG_hf();
    }

    public interface FunG {
        void fung(float f[], float g[], float param[]);
    }

    public interface Fun1 {
        public float fun1(float a);
    }

    public class Fun1_gf implements Fun1 {
        public float fun1(float a) {
            return fun1_gf(a);
        }
    }

    public class FunG_hf implements FunG {
        public void fung(float h[], float f[], float p[]) {
            float g[] = new float[2];
            EyeVp.bc(h, g, p);
            fun2r(g, f, new Fun1_gf());
        }
    }

    public class FunG_gf implements FunG {
        public void fung(float g[], float f[], float p[]) {
            fun2r(g, f, new Fun1_gf());
        }
    }

    public FunG_gf fung_gf;

    public FunG_hf fung_hf;

    public static void fun2r(float f[], float g[], Fun1 fun) {
        float r = (float) sqrt(f[0] * f[0] + f[1] * f[1]);
        if (r < Float.MIN_NORMAL) {
            g[0] = 0;
            g[1] = 0;
            return;
        }
        float t = fun.fun1(r) / r;
        g[0] = f[0] * t;
        g[1] = f[1] * t;
    }

    public static float fun1_aa(float a) {
        return a;
    }

    public static float fun1_af(float a) {
        return (float)tan(a / 2) * 2;
    }

    public static float fun1_fa(float f) {
        return (float)atan(f / 2) * 2;
    }

    public static float fun1_ag(float a) {
        return (float)tan(a);
    }

    public static float fun1_ga(float g) {
        return (float)atan(g);
    }

    public static float fun1_gf(float g) {
        if (abs(g) > Float.MIN_NORMAL) {
            return (float) ((-1 + sqrt(1 + g * g)) * 2 / g);
        }
        else {
            return 0;
        }
    }

    public static float fun1_fg(float f) {
        if (abs(f) < (2 - Float.MIN_NORMAL)) {
            return (float) (f / (1 - f * f / 4));
        } else {
            return Float.MAX_VALUE;
        }
    }
}
