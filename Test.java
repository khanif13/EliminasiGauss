package AljabarLinear;

import java.util.Scanner;

public class Test {

    public static void eliminasiGauss(double[][] matrix) {
        int n = matrix.length;

        // Eliminasi maju
        for (int i = 0; i < n; i++) {
            // Pivot utama
            for (int j = i + 1; j < n; j++) {
                double ratio = matrix[j][i] / matrix[i][i];
                for (int k = 0; k <= n; k++) {
                    matrix[j][k] -= ratio * matrix[i][k];
                }
            }
        }

        // Back substitution
        double[] solusi = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            solusi[i] = matrix[i][n];
            for (int j = i + 1; j < n; j++) {
                solusi[i] -= matrix[i][j] * solusi[j];
            }
            solusi[i] /= matrix[i][i];
        }

        System.out.println("Hasil Eliminasi Gauss:");
        System.out.println("x = " + solusi[0]);
        System.out.println("y = " + solusi[1]);
    }

    public static void refleksi(double x, double y, String sumbu) {
        double refleksiX = x;
        double refleksiY = y;

        if (sumbu.equalsIgnoreCase("X")) {
            refleksiY = -y;
        } else if (sumbu.equalsIgnoreCase("Y")) {
            refleksiX = -x;
        } else if (sumbu.equalsIgnoreCase("XY")) {
            double temp = refleksiX;
            refleksiX = refleksiY;
            refleksiY = temp;
        } else {
            System.out.println("Sumbu tidak valid!");
            return;
        }

        System.out.println("Hasil Refleksi terhadap sumbu " + sumbu + ":");
        System.out.println("x = " + refleksiX);
        System.out.println("y = " + refleksiY);
    }

    public static void rotasi(double x, double y, double degree) {
        double radians = Math.toRadians(degree);

        double hasilX = x * Math.cos(radians) - y * Math.sin(radians);
        double hasilY = x * Math.sin(radians) + y * Math.cos(radians);

        System.out.println("Hasil Rotasi " + degree + "° :");
        System.out.println("x = " + hasilX);
        System.out.println("y = " + hasilY);
    }

    public static void translasi(double x, double y, double tx, double ty) {
        double hasilX = x + tx;
        double hasilY = y + ty;

        System.out.println("Hasil Translasi:");
        System.out.println("x = " + hasilX);
        System.out.println("y = " + hasilY);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input persamaan linear 2 variabel
        System.out.println("Masukkan koefisien dan konstanta untuk persamaan linear:");
        double[][] matrix = new double[2][3];
        for (int i = 0; i < 2; i++) {
            System.out.print("Masukkan koefisien x" + (i + 1) + ": ");
            matrix[i][0] = sc.nextDouble();
            System.out.print("Masukkan koefisien y" + (i + 1) + ": ");
            matrix[i][1] = sc.nextDouble();
            System.out.print("Masukkan konstanta persamaan ke-" + (i + 1) + ": ");
            matrix[i][2] = sc.nextDouble();
        }

        // Solusi menggunakan metode eliminasi Gauss
        eliminasiGauss(matrix);

        System.out.print("\nMasukkan koordinat x awal: ");
        double x = sc.nextDouble();
        System.out.print("Masukkan koordinat y awal: ");
        double y = sc.nextDouble();

        // Rotasi
        System.out.print("\nMasukkan derajat rotasi: ");
        double degree = sc.nextDouble();
        rotasi(x, y, degree);

        // Refleksi
        System.out.print("\nMasukkan sumbu refleksi (X, Y, XY): ");
        String sumbu = sc.next();
        refleksi(x, y, sumbu);

        // Translasi
        System.out.print("\nMasukkan nilai translasi untuk x: ");
        double tx = sc.nextDouble();
        System.out.print("Masukkan nilai translasi untuk y: ");
        double ty = sc.nextDouble();
        translasi(x, y, tx, ty);

        sc.close();
    }
}
