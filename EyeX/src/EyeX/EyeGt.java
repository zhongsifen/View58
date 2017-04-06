package EyeX;

/**
 * Created by zhongsifen on 3/3/2017.
 */

import static EyeX.EyeX.*;
import static java.lang.Math.*;

public class EyeGt {
    private static void kw(float x, float y, int k[], int w[]) {
        k[0] = (int)floor(x);
        k[1] = (int)floor(y);
        float p1 = x - k[0];
        float p2 = y - k[1];
        float q1 = 1.0F - p1;
        float q2 = 1.0F - p2;
        w[0] = SR_s(q1*q2);
        w[1] = SR_s(p1*q2);
        w[2] = SR_s(q1*p2);
        w[3] = SR_s(1) - w[0] - w[1] - w[2];
    }

    public static void kwmap(
            EyeGtFun.FunG funG, float param[],
            int l_x, int l_y, float z_x, float z_y, float r_x, float r_y,
            int m_x, int m_y, float w_x, float w_y, float s_x, float s_y,
            int mapK[], int mapW[][])
    {
        float m0_x = -(s_x*m_x / 2);
        float m0_y = -(s_x*m_y / 2);
        float f[] = new float[2];
        float g[] = new float[2];
        float f_x, f_y, g_x, g_y;
        for (int i = 0, i2 = 0; i2<m_y; i2++) {
            g[1] = m0_y + s_y*(i2 + 0.5F);
            for (int i1 = 0; i1<m_x; i1++, i++) {
                g[0] = m0_x + s_x*(i1 + 0.5F);
                funG.fung(g, f, param);
                float t_x = (f[0]/r_x) + z_x;
                float t_y = (f[1]/r_y) + z_y;

                if (t_x < 0) t_x = 0;
                if (t_x > l_x - 2) t_x = (float)l_x - 2;
                if (t_y < 0) t_y = 0;
                if (t_y > l_y - 2) t_y = (float)l_y - 2;

                int k[] = new int[2];
                int w[] = new int[4];
                kw(t_x, t_y, k, w);
                mapK[i] = k[0] + k[1]*l_x;
                mapW[i][0] = w[0];
                mapW[i][1] = w[1];
                mapW[i][2] = w[2];
                mapW[i][3] = w[3];
            }
        }
    }

    public static void remap(
            byte f[], int l_x, int l_y,
            byte g[], int m_x, int m_y,
            int mapK[], int mapW[][])
    {
        for (int i=0,i_y=0; i_y<m_y; i_y++) {
            for (int i_x=0; i_x<m_x; i_x++,i++) {
                int k = mapK[i];

                int w_0 = mapW[i][0];
                int w_1 = mapW[i][1];
                int w_2 = mapW[i][2];
                int w_3 = mapW[i][3];

                int f_0 = f[k      ];
                int f_1 = f[k+1    ];
                int f_2 = f[k+  l_x];
                int f_3 = f[k+1+l_x];

                g[i] = (byte) SR_r(f_0*w_0 + f_1*w_1 + f_2*w_2 + f_3*w_3);
            }
        }
    }

    public static void remap(
            int f[], int l_x, int l_y,
            int g[], int m_x, int m_y,
            int mapK[], int mapW[][])
    {
        for (int i=0,i_y=0; i_y<m_y; i_y++) {
            for (int i_x=0; i_x<m_x; i_x++,i++) {
                int k = mapK[i];

                int w_0 = mapW[i][0];
                int w_1 = mapW[i][1];
                int w_2 = mapW[i][2];
                int w_3 = mapW[i][3];

                int f_0 = f[k      ];
                int f_1 = f[k+1    ];
                int f_2 = f[k+  l_x];
                int f_3 = f[k+1+l_x];

                g[i] = SR_r(f_0*w_0 + f_1*w_1 + f_2*w_2 + f_3*w_3);
            }
        }
    }

    public static void remapC(
            byte f[], int l_x, int l_y,
            byte g[], int m_x, int m_y,
            int mapK[], int mapW[][],
            int c)
    {
        int m = c;
        int n = l_x*c;
        for (int i=0, i_y=0; i_y<m_y; i_y++) {
            for (int  i_x=0; i_x<m_x; i_x++, i++) {
                int k = mapK[i];

                int w_0 = mapW[i][0];
                int w_1 = mapW[i][1];
                int w_2 = mapW[i][2];
                int w_3 = mapW[i][3];

                int kc = k*c;
                int ic = i*c;
                for (int j=0; j<c; j++) {
                    int kj = kc+j;
                    int ij = ic+j;
                    int f_0 = f[kj    ];
                    int f_1 = f[kj+m  ];
                    int f_2 = f[kj+  n];
                    int f_3 = f[kj+m+n];

                    g[ij] = TR(SR_r(f_0*w_0 + f_1*w_1 + f_2*w_2 + f_3*w_3));
                }
            }
        }
    }

    public static void remapC(
            int f[], int l_x, int l_y,
            int g[], int m_x, int m_y,
            int mapK[], int mapW[][],
            int c)
    {
        int m = c;
        int n = l_x*c;
        for (int i=0, i_y=0; i_y<m_y; i_y++) {
            for (int  i_x=0; i_x<m_x; i_x++, i++) {
                int k = mapK[i];

                int w_0 = mapW[i][0];
                int w_1 = mapW[i][1];
                int w_2 = mapW[i][2];
                int w_3 = mapW[i][3];

                int kc = k*c;
                int ic = i*c;
                for (int j=0; j<c; j++) {
                    int kj = kc+j;
                    int ij = ic+j;
                    int f_0 = f[kj    ];
                    int f_1 = f[kj+m  ];
                    int f_2 = f[kj+  n];
                    int f_3 = f[kj+m+n];

                    g[ij] = SR_r(f_0*w_0 + f_1*w_1 + f_2*w_2 + f_3*w_3);
                }
            }
        }
    }

    public static void remapC4(
            int f[], int l_x, int l_y,
            int g[], int m_x, int m_y,
            int mapK[], int mapW[][])
    {
        int m = 1;
        int n = l_x;
        for (int i=0, i_y=0; i_y<m_y; i_y++) {
            for (int  i_x=0; i_x<m_x; i_x++, i++) {
                int k = mapK[i];

                int w_0 = mapW[i][0];
                int w_1 = mapW[i][1];
                int w_2 = mapW[i][2];
                int w_3 = mapW[i][3];

                {
                    int f_0 = f[k    ];
                    int f_1 = f[k+m  ];
                    int f_2 = f[k+  n];
                    int f_3 = f[k+m+n];

                    int a = 0;
                    for (int j=0; j<32; j+=8) {
                        int h_0 = (f_0>>j)&0xFF;
                        int h_1 = (f_1>>j)&0xFF;
                        int h_2 = (f_2>>j)&0xFF;
                        int h_3 = (f_3>>j)&0xFF;
                        a |= (SR_r(h_0*w_0 + h_1*w_1 + h_2*w_2 + h_3*w_3))<<j;
                    }
                    g[i] = a;
                }
            }
        }
    }

}