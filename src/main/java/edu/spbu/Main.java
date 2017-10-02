package edu.spbu;

import java.util.function.Function;

/**
 * Created by Миша on 24.09.2017.
 */
public class Main {
    public static class Matrix2x2 {
        int[][] matrix = new int[2][2];

        public Matrix2x2(int i, int i1, int i2, int i3) {
            matrix[0][0] = i;
            matrix[0][1] = i1;
            matrix[1][0] = i2;
            matrix[1][1] = i3;
        }

        public void printf() {
            System.out.println(matrix[0][0] + " " + matrix[0][1]);
            System.out.println(matrix[1][0] + " " + matrix[1][1] + "\n");
        }

        public int det() {
            Function<int[][], Integer> det = m -> m[0][0] * m[1][1] - m[1][0] * m[0][1];
            return det.apply(matrix);
        }
    }

    public static void main(String s[]) throws Exception {

        MyList<Matrix2x2> list = new MyList<>();
        Matrix2x2 m0 = new Matrix2x2(0, 0, 0, 0);
        Matrix2x2 m1 = new Matrix2x2(1, 2, 1, 1);
        Matrix2x2 m2 = new Matrix2x2(2, 2, 20, 2);
        Matrix2x2 m3 = new Matrix2x2(3, 3, 3, 3);
        list.add(m1);
        list.add(m0);
        list.add(m2);
        list.add(m3);
        list.add(m1);
        list.add(m0);

        list.sort(list);

        System.out.println();
        list.get(0).printf();
        list.get(1).printf();
        list.get(2).printf();
        list.get(3).printf();
        list.get(4).printf();
        list.get(5).printf();
    }
}
