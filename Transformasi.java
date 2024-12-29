package AljabarLinear;
import java.util.Arrays;
import java.util.Scanner;

public class Transformasi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double x1 = sc.nextInt();
        double y1 = sc.nextInt();
        double x2 = sc.nextInt();
        double y2 = sc.nextInt();
        double konstanta1 = sc.nextInt();
        double konstanta2 = sc.nextInt();
        // Matriks koefisien
        double[][] A = {
                {x1, y1},
                {x2, y2}
        };
        
        // Matriks konstanta
        double[] B = {konstanta1, konstanta2};

        // Solusi persamaan linear
        double[] solution = solveLinearEquation(A, B);
        System.out.println("Solusi (x, y): " + Arrays.toString(solution));

        // Transformasi Rotasi 90 derajat
        double[][] rotation90 = {
                {0, -1},
                {1, 0}
        };
        double[] rotatedSolution = applyTransformation(rotation90, solution);
        System.out.println("Setelah Rotasi 90°: " + Arrays.toString(rotatedSolution));

        // Transformasi Refleksi terhadap sumbu y
        double[][] reflectionY = {
                {-1, 0},
                {0, 1}
        };
        double[] reflectedSolution = applyTransformation(reflectionY, solution);
        System.out.println("Setelah Refleksi terhadap sumbu y: " + Arrays.toString(reflectedSolution));

        // Transformasi Translasi (geser 2 ke kanan, 3 ke atas)
        double[] translation = {2, 3};
        double[] translatedSolution = translate(solution, translation);
        System.out.println("Setelah Translasi: " + Arrays.toString(translatedSolution));
    }

    // Metode untuk menyelesaikan SPL
    public static double[] solveLinearEquation(double[][] A, double[] B) {
        double det = A[0][0] * A[1][1] - A[0][1] * A[1][0];
        if (det == 0) {
            throw new IllegalArgumentException("Matriks tidak memiliki invers, solusi tidak ada atau tak hingga.");
        }

        double x = (B[0] * A[1][1] - B[1] * A[0][1]) / det;
        double y = (A[0][0] * B[1] - A[1][0] * B[0]) / det;
        return new double[]{x, y};
    }

    // Metode untuk menerapkan transformasi
    public static double[] applyTransformation(double[][] transformationMatrix, double[] vector) {
        double xPrime = transformationMatrix[0][0] * vector[0] + transformationMatrix[0][1] * vector[1];
        double yPrime = transformationMatrix[1][0] * vector[0] + transformationMatrix[1][1] * vector[1];
        return new double[]{xPrime, yPrime};
    }

    // Metode untuk menerapkan translasi
    public static double[] translate(double[] vector, double[] translation) {
        return new double[]{vector[0] + translation[0], vector[1] + translation[1]};
    }
}
