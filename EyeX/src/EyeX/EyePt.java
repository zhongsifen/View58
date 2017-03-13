package EyeX;

import static java.lang.Math.abs;

import org.jblas.FloatMatrix;
import org.jblas.Solve;

/**
 * Created by zhongsifen on 5/3/2017.
 */
public class EyePt {
    public static void fungPer(float f[], float g[], float a[]) {
        float w = f[0]*a[6] + f[1]*a[7] + 1;
        assert(abs(w) > Float.MIN_NORMAL);

        g[0] = (f[0]*a[0] + f[1]*a[1] + a[2])/w;
        g[1] = (f[0]*a[3] + f[1]*a[4] + a[5])/w;
    }

    private void _per_pt(float f[], float g[], float a0[], float a1[], float b0[], float b1[]) {
        a0[0] = a1[3] = f[0];
        a0[1] = a1[4] = f[1];
        a0[2] = a1[5] = 1;
        a0[3] = a0[4] = a0[5] =
                a1[0] = a1[1] = a1[2] = 0;
        a0[6] = -f[0] * g[0];
        a0[7] = -f[1] * g[0];
        a1[6] = -f[0] * g[1];
        a1[7] = -f[1] * g[1];
        b0[0] = g[0];
        b1[0] = g[1];
    }

    private static void _per_pt(float f_x, float f_y, float g_x, float g_y, float a0[], float a1[], float b0[], float b1[]) {
        a0[0] = a1[3] = f_x;
        a0[1] = a1[4] = f_y;
        a0[2] = a1[5] = 1;
        a0[3] = a0[4] = a0[5] =
                a1[0] = a1[1] = a1[2] = 0;
        a0[6] = -f_x * g_x;
        a0[7] = -f_y * g_x;
        a1[6] = -f_x * g_y;
        a1[7] = -f_y * g_y;
        b0[0] = g_x;
        b1[0] = g_y;
    }

    public static float[] perPt(float pt_f[], float pt_g[], int pt_count) {
        int n = pt_count*2;
        float a[][] = new float[n][8];
        float b[][] = new float[n][1];
        for (int k=0; k<n; k+=2) {
            _per_pt(pt_f[k], pt_f[k+1], pt_g[k], pt_g[k+1], a[k], a[k+1], b[k], b[k+1]);
        }

        FloatMatrix am = new FloatMatrix(a);
        FloatMatrix bm = new FloatMatrix(b);
        FloatMatrix xm = Solve.solveLeastSquares(am, bm);

        return xm.toArray();
    }

}

/*

//
//  EyeP_g.cpp
//
//  Created by SIFEN ZHONG on 6/10/14.
//  Copyright (c) 2014 ___ZHONGSIFEN___. All rights reserved.
//

#include "_EyeP.hpp"
#include "EyeP_g.hpp"
#include "_opencv2.hpp"
using namespace cv;

void EyeP_g(float f_x, float f_y, float& g_x, float& g_y, void* param)
{
	float* a = (float*)param;
	float w = 1;

	g_x = f_x*a[0] + f_y*a[1] + a[2];
	g_y = f_x*a[3] + f_y*a[4] + a[5];
	w += f_x*a[6] + f_y*a[7];

	assert(fabsf(w) > 1e-8);
	w = 1.0F / w;
	g_x *= w;
	g_y *= w;
}

// f_x*a11 + f_y*a12 + b1 = g_x*w = g_x*(f_x*c1 + f_y*c2 + 1)
// f_x*a21 + f_y*a22 + b2 = g_y*w
// f_x*c1  + f_y*c2  + 1  = w

// degree == 8
// f_x*a11 + f_y*a12 + b1 - (f_x*g_x)*c1 - (f_y*g_x)*c2 = g_x
// f_x*a21 + f_y*a22 + b2 - (f_x*g_y)*c1 - (f_y*g_y)*c2 = g_y

// [a11 = a22 = 1]
// [a12 = a21 = 0]
// degree == 4
// f_x + b1 - (f_x*g_x)*c1 - (f_y*g_x)*c2 = g_x
// f_y + b2 - (f_x*g_y)*c1 - (f_y*g_y)*c2 = g_y
//
// fn_x + b1 - (fn_x*gn_x)*c1 - (fn_y*gn_x)*c2 = gn_x
// fn_y + b2 - (fn_x*gn_y)*c1 - (fn_y*gn_y)*c2 = gn_y
// fp_x + b1 - (fp_x*gp_x)*c1 - (fp_y*gp_x)*c2 = gp_x
// fp_y + b2 - (fp_x*gp_y)*c1 - (fp_y*gp_y)*c2 = gp_y
//
// [fn_y = gn_y = 0]
// b2 = 0
// degree == 3
// fn_x + b1 - (fn_x*gn_x)*c1                  = gn_x
// fp_x + b1 - (fp_x*gp_x)*c1 - (fp_y*gp_x)*c2 = gp_x
// fp_y      - (fp_x*gp_y)*c1 - (fp_y*gp_y)*c2 = gp_y
//
// [fp_y = gp_y = 0]
// c2 = 0
// degree == 2
// fn_x + b1 - (fn_x*gn_x)*c1 = gn_x
// fp_x + b1 - (fp_x*gp_x)*c1 = gp_x
//
// b1 - (fn_x*gn_x)*c1 = gn_x - fn_x
// b1 - (fp_x*gp_x)*c1 = gp_x - fp_x
//
// ((fp_x*gp_x)- (fn_x*gn_x))*c1 = (fp_x - gp_x) - (fn_x - gn_x)
//
// c = ((fp_x - gp_x) - (fn_x - gn_x)) / ((fp_x*gp_x)- (fn_x*gn_x))
// b = (fn_x*gn_x)*c - (fn_x-  gn_x)


// f_x*a + b1      - (f_x*g_x)*c1 = g_x
// f_y*a      + b2 - (f_x*g_y)*c1 = g_y

void _per_pt(double a0[], double a1[], double b0[], double b1[], float f[], float g[])
{
	a0[0] = a1[3] = f[0];
	a0[1] = a1[4] = f[1];
	a0[2] = a1[5] = 1;
	a0[3] = a0[4] = a0[5] =
	a1[0] = a1[1] = a1[2] = 0;
	a0[6] = -f[0] * g[0];
	a0[7] = -f[1] * g[0];
	a1[6] = -f[0] * g[1];
	a1[7] = -f[1] * g[1];
	b0[0] = g[0];
	b1[0] = g[1];
}

void _per_ln(double a0[], double a1[], double b0[], double b1[], float f[], float g[])
{
	a0[0] = a1[1] = g[0];
	a0[3] = a1[4] = g[1];
	a0[6] = a1[7] = 1;
	a0[1] = a0[4] = a0[7] =
	a1[0] = a1[3] = a1[6] = 0;
	a0[2] = -g[0] * f[0];
	a0[5] = -g[1] * f[0];
	a1[2] = -g[0] * f[1];
	a1[5] = -g[1] * f[1];
	b0[0] = f[0];
	b1[0] = f[1];
}

void EyeP_per_pt(const float pt_f[], const float pt_g[], int pt_count, float u[])
{
	float (*src)[2] = (float(*)[2])pt_f;
	float (*dst)[2] = (float(*)[2])pt_g;
	int n = pt_count * 2;
	double A_[8][8], B_[8], X_[8];
	Mat A(n, 8, CV_64F, A_), B(n, 1, CV_64F, B_);
	Mat X(8, 1, CV_64F, X_);
	double* x = X.ptr<double>();

	double **a = new double*[n];
	double **b = new double*[n];
	for (int k = 0; k<n; k++) {
		a[k] = A.ptr<double>(k);
		b[k] = B.ptr<double>(k);
	}

	for (int i = 0, k0 = 0, k1 = 1; i<pt_count; i++, k0 += 2, k1 += 2) {
		_per_pt(a[k0], a[k1], b[k0], b[k1], src[i], dst[i]);
	}

	solve(A.t()*A, A.t()*B, X, DECOMP_SVD);

	for (int j = 0; j<8; j++) {
		u[j] = (float)x[j];
	}
}

void EyeP_per_ln(const float ln_f[], const float ln_g[], int ln_count, float u[])
{
	float (*f)[2] = (float(*)[2])ln_f;
	float (*g)[2] = (float(*)[2])ln_g;
	int l = ln_count * 2;
	Mat A(l, 8, CV_64F), B(l, 1, CV_64F);
	Mat X(8, 1, CV_64F);
	double* x = X.ptr<double>();

	double **a = new double*[l];
	double **b = new double*[l];
	for (int k = 0; k<l; k++) {
		a[k] = A.ptr<double>(k);
		b[k] = B.ptr<double>(k);
	}

	for (int i = 0, k0 = 0, k1 = 1; i<ln_count; i++, k0 += 2, k1 += 2) {
		_per_ln(a[k0], a[k1], b[k0], b[k1], f[i], g[i]);
	}

	solve(A.t()*A, A.t()*B, X, DECOMP_SVD);

	for (int j = 0; j<8; j++) {
		u[j] = (float)x[j];
	}
}

void EyeP_per_pt_ln(const float pt_f[], const float pt_g[], int pt_count, const float ln_f[], const float ln_g[], const int ln_count, float u[])
{
	int count = pt_count + ln_count;
	int l = count * 2;
	Mat A(l, 8, CV_64F), B(l, 1, CV_64F);
	Mat X(8, 1, CV_64F);
	double **a = new double*[l];
	double **b = new double*[l];
	for (int k = 0; k<l; k++) {
		a[k] = A.ptr<double>(k);
		b[k] = B.ptr<double>(k);
	}
	double* x = X.ptr<double>();

	float (*f)[2] = (float(*)[2])pt_f;
	float (*g)[2] = (float(*)[2])pt_g;
	int k0 = 0, k1 = 1;
	for (int i=0; i<pt_count; i++, k0 += 2, k1 += 2) {
		_per_pt(a[k0], a[k1], b[k0], b[k1], f[i], g[i]);
	}
	f = (float(*)[2])ln_f;
	g = (float(*)[2])ln_g;
	for (int i=0; i<ln_count; i++, k0 += 2, k1 += 2) {
		_per_ln(a[k0], a[k1], b[k0], b[k1], f[i], g[i]);
	}

	solve(A.t()*A, A.t()*B, X, DECOMP_SVD);

	for (int j = 0; j<8; j++) {
		u[j] = (float)x[j];
		printf("u = %f\n", u[j]);
	}
}

void EyeP_pt_ln(Point2f& pt_1, Point2f& pt_2, Point2f& ln)
{
	double _a[4], _b[2], _x[2];
	Mat a(Size(2, 2), CV_64F, _a);
	Mat b(Size(1, 2), CV_64F, _b);
	Mat x(Size(1, 2), CV_64F, _x);
	_a[0] = pt_1.x;
	_a[1] = pt_1.y;
	_a[2] = pt_2.x;
	_a[3] = pt_2.y;
	_b[0] = -1.0;
	_b[1] = -1.0;
	solve(a, b, x);
	ln.x = (float)_x[0];
	ln.y = (float)_x[1];
}
 */