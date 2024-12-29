package AljabarLinear;

import java.util.Scanner;

public class Final {

    public static void refleksiX(double x, double y) {
        double[][] reflekX = {
                { 1, 0 },
                { 0, -1 },
        };
        System.out.println("Hasil Refleksi X : ");
        double hasilreflekX = reflekX[0][0] * x + reflekX[0][1] * y;
        double hasilreflekY = reflekX[1][0] * x + reflekX[1][1] * y;

        System.out.println("Hasil Refleksi x = " + hasilreflekX);
        System.out.println("Hasil Refleksi y = " + hasilreflekY);
    }

    public static void refleksiY(double x, double y) {
        double[][] reflekY = {
                { -1, 0 },
                { 0, 1 },
        };
        System.out.println("Hasil Refleksi Y : ");
        double hasilreflekX = reflekY[0][0] * x + reflekY[0][1] * y;
        double hasilreflekY = reflekY[1][0] * x + reflekY[1][1] * y;

        System.out.println("Hasil Refleksi x = " + hasilreflekX);
        System.out.println("Hasil Refleksi y = " + hasilreflekY);
    }

    public static void refleksiXY(double x, double y) {
        double[][] reflekXY = {
                { 0, 1 },
                { 1, 0 },
        };
        System.out.println("Hasil Refleksi X = Y : ");
        double hasilreflekX = reflekXY[0][0] * x + reflekXY[0][1] * y;
        double hasilreflekY = reflekXY[1][0] * x + reflekXY[1][1] * y;

        System.out.println("Hasil Refleksi x = " + hasilreflekX);
        System.out.println("Hasil Refleksi y = " + hasilreflekY);
    }

    static void rotasi(double x, double y, double radian) {
        double[][] rotasi = {
            {Math.cos(radian), -Math.sin(radian)},
            {Math.sin(radian), Math.cos(radian)}
        };
        System.out.println("Setelah Rotasi : ");
        double hasilx = rotasi[0][0] * x + rotasi[0][1] * y;
        double hasily = rotasi[1][0] * x + rotasi[1][1] * y;

        hasilx = Math.round(hasilx * 100.0) / 100.0;
        hasily = Math.round(hasily * 100.0) / 100.0;

        System.out.println("Rotasi X = " + hasilx);
        System.out.println("Rotasi Y = " + hasily);
    }

    public static void translasiX(double x, double y, double ax) {
        double[][] translasiX = {
                { 1, ax },
                { 0, 1 },
        };
        System.out.println("Hasil Translasi Sumbu X");
        double hasilx = translasiX[0][0] * x + translasiX[0][1] * y;
        double hasily = translasiX[1][0] * x + translasiX[1][1] * y;
        System.out.println("Translasi X = " + hasilx);
        System.out.println("Translasi Y = " + hasily);
    }

    public static void translasiY(double x, double y, double ay) {
        double[][] translasiX = {
                { 1, 0 },
                { ay, 1 },
        };
        System.out.println("Hasil Translasi Sumbu Y");
        double hasilx = translasiX[0][0] * x + translasiX[0][1] * y;
        double hasily = translasiX[1][0] * x + translasiX[1][1] * y;
        System.out.println("Translasi X = " + hasilx);
        System.out.println("Translasi Y = " + hasily);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] spl = new double[2][3];
        for (int i = 0; i < 2; i++) {
            System.out.print("Nilai X: ");
            spl[i][0] = sc.nextDouble();
            System.out.print("Nilai Y: ");
            spl[i][1] = sc.nextDouble();
            System.out.print("Konstanta: ");
            spl[i][2] = sc.nextDouble();
        }

        double det = spl[0][0] * spl[1][1] - spl[0][1] * spl[1][0];
        if (det == 0) {
            System.out.println("Punya banyak solusi");
            return;
        }
        double x = ((spl[1][1] * spl[0][2]) - (spl[0][1] * spl[1][2])) / det;
        double y = ((spl[0][0] * spl[1][2]) - (spl[1][0] * spl[0][2])) / det;

        System.out.println("X = " + x);
        System.out.println("Y = " + y + "\n");

        System.out.println();
        refleksiX(x, y);
        System.out.println();
        refleksiY(x, y);
        System.out.println();
        refleksiXY(x, y);

        System.out.print("Masukkan derajat rotasi : ");
        double derajat = sc.nextDouble();
        double radian = Math.toRadians(derajat);
        rotasi(x, y, radian);

        System.out.println("Lanjut ke Translasi ? (y/n): ");
        char apa = sc.next().charAt(0);
        if (apa == 'y' || apa == 'Y') {
            System.out.print("ax : ");
            double ax = sc.nextDouble();
            System.out.print("ay : ");
            double ay = sc.nextDouble();
            translasiX(x, y, ax);
            translasiY(x, y, ay);
        } else {
            return;
        }
        sc.close();
    }
}
