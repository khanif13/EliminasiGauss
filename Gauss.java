package AljabarLinear;

import java.util.Scanner;

public class Gauss {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Proses pencarian solusi persamaan linear
        System.out.println("Mencari Solusi X dan Y menggunakan eliminasi Gauss Jordan");
        System.out.println("Masukkan Persamaannya: ");
        double[][] spl = new double[2][3];
        for (int i = 0; i < 2; i++) {
            System.out.print("Nilai X: ");
            spl[i][0] = sc.nextDouble();
            System.out.print("Nilai Y: ");
            spl[i][1] = sc.nextDouble();
            System.out.print("Konstanta: ");
            spl[i][2] = sc.nextDouble();
        }
        
        printMatrix(spl);
        double skalar;
        int baris;
        int target;

        // Eliminasi Gauss-Jordan
        if (spl[0][0] != 1 && spl[1][0] == 1) {
            System.out.println("Eliminasi baris 1 kolom 1");
            metode1(spl);
        } else if (spl[0][0] != 1) {
            skalar = spl[0][0];
            System.out.println("Eliminasi baris 1 kolom 1");
            metode2(spl, skalar, 0);
        }

        if (spl[1][0] != 0) {
            System.out.println("Eliminasi baris 2 kolom 1");
            skalar = -spl[1][0];
            baris = 0;
            target = 1;
            metode3(spl, skalar, baris, target);
        }

        if (spl[1][0] == 0 && spl[1][1] == 0 && spl[1][2] == 0) {
            System.out.println("SPL mempunyai solusi tak hingga banyak");
            return;
        }

        if (spl[1][0] == 0 && spl[1][1] == 0) {
            System.out.println("SPL tidak mempunyai solusi");
            return;
        }

        if (spl[1][1] != 1) {
            System.out.println("Eliminasi baris 2 kolom 2");
            skalar = spl[1][1];
            baris = 1;
            metode2(spl, skalar, baris);
        }

        if (spl[0][1] != 0) {
            System.out.println("Eliminasi baris 1 kolom 2");
            skalar = -spl[0][1];
            baris = 1;
            target = 0;
            metode3(spl, skalar, baris, target);
        }

        System.out.println("SPL mempunyai solusi tunggal");
        System.out.println("X: " + spl[0][2]);
        System.out.println("Y: " + spl[1][2]);

        // Proses transformasi linear
        double[][] variabel = new double[2][1];
        System.out.println("Masukkan Koordinat:");
        System.out.print("Nilai X: ");
        variabel[0][0] = sc.nextDouble();
        System.out.print("Nilai Y: ");
        variabel[1][0] = sc.nextDouble();

        System.out.println("Transformasi Rotasi");
        System.out.print("Masukkan sudut (derajat): ");
        double sudut = Math.toRadians(sc.nextDouble());
        rotasi(sudut, variabel);

        System.out.println("Transformasi Refleksi terhadap sumbu X");
        refleksiX(variabel);

        System.out.println("Transformasi Refleksi terhadap sumbu Y");
        refleksiY(variabel);

        System.out.println("Transformasi Refleksi terhadap sumbu Y=X");
        refleksiYX(variabel);

        System.out.println("Transformasi Translasi Sumbu X");
        System.out.print("Masukkan nilai translasi: ");
        int a = sc.nextInt();
        translasiX(a, variabel);

        System.out.println("Transformasi Translasi Sumbu Y");
        System.out.print("Masukkan nilai translasi: ");
        a = sc.nextInt();
        translasiY(a, variabel);
    }

    // Menukar baris pada matrix
    static void metode1(double[][] m) {
        double[] temp = m[0];
        m[0] = m[1];
        m[1] = temp;
        printMatrix(m);
    }

    static void metode2(double[][] m, double skalar, int baris) {
        for (int i = 0; i < m[0].length; i++) {
            m[baris][i] /= skalar;
            if (m[baris][i] == -0) m[baris][i] = 0;
        }
        printMatrix(m);
    }

    static void metode3(double[][] m, double skalar, int baris, int target) {
        for (int i = 0; i < m[0].length; i++) {
            m[target][i] += skalar * m[baris][i];
            if (m[target][i] == -0) m[target][i] = 0;
        }
        printMatrix(m);
    }

    static void printMatrix(double[][] m) {
        for (double[] ds : m) {
            for (double d : ds) {
                System.out.print(Math.round(d * 1e10) / 1e10 + "\t");
            }
            System.out.println();
        }
    }

    static void rotasi(double x, double[][] variabel) {
        double[][] T = {
            {Math.cos(x), -Math.sin(x)},
            {Math.sin(x), Math.cos(x)}
        };
        printMatrix(perkalianMatrix(T, variabel));
    }

    static void refleksiX(double[][] variabel) {
        double[][] Rx = {
            {1, 0},
            {0, -1}
        };
        printMatrix(perkalianMatrix(Rx, variabel));
    }

    static void refleksiY(double[][] variabel) {
        double[][] Ry = {
            {-1, 0},
            {0, 1}
        };
        printMatrix(perkalianMatrix(Ry, variabel));
    }

    static void refleksiYX(double[][] variabel) {
        double[][] Ryx = {
            {0, 1},
            {1, 0}
        };
        printMatrix(perkalianMatrix(Ryx, variabel));
    }

    static void translasiX(int a, double[][] variabel) {
        double[][] transX = {
            {1, a},
            {0, 1}
        };
        printMatrix(perkalianMatrix(transX, variabel));
    }

    static void translasiY(int a, double[][] variabel) {
        double[][] transY = {
            {1, 0},
            {a, 1}
        };
        printMatrix(perkalianMatrix(transY, variabel));
    }

    static double[][] perkalianMatrix(double[][] a, double[][] b) {
        double[][] hasil = new double[a.length][1];
        for (int i = 0; i < hasil.length; i++) {
            hasil[i][0] = a[i][0] * b[0][0] + a[i][1] * b[1][0];
        }
        return hasil;
    }
}