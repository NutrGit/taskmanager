package test.util;

import java.util.HashSet;
import java.util.Set;

public class MagicCube {
    private int[][] magicCube;
    private int n;

    public MagicCube(int n) {
        this.n = n;
    }

    public int[][] findMagicCube() {
        int[][] a = new int[n][n];
        boolean isMagicCube = false;

        while (!isMagicCube) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a.length; j++) {
//                a[i][j] = 2;
                    a[i][j] = getRandom(1, 5);
                    isMagicCube = isMagicCube(a);
                    if (isMagicCube) {
                        break;
                    }
                }
                if (isMagicCube) {
                    break;
                }
            }
        }

        this.magicCube = a;
        return a;
    }

    public void printCube(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isMagicCube(int[][] a) {

        int[] sumH = new int[a.length];
        int[] sumV = new int[a.length];
        int sumd1 = getSumD1(a);
        int sumd2 = getSumD2(a);

        for (int j = 0; j < a.length; j++) {
            sumH[j] = getSumH(a, j);
            sumV[j] = getSumV(a, j);
        }

        Set<Integer> sumSet = new HashSet<>();

        for (int s : sumH) {
//            System.out.println("sumH = " + s);
            sumSet.add(s);
        }

        for (int s : sumV) {
//            System.out.println("sumV = " + s);
            sumSet.add(s);
        }

        sumSet.add(sumd1);
        sumSet.add(sumd2);
//        System.out.println("sumd1 = " + sumd1);
//        System.out.println("sumd2 = " + sumd2);
//
//        System.out.println(sumSet.size());
//        System.out.println();

        if (sumSet.size() == 1) {
            System.out.println("find");
            printCube(a);
            return true;
        } else {
            return false;
        }
    }

    private int getSumH(int[][] a, int jh) {
//        int sum = a[jv][0] + a[jv][1] + a[jv][2];
        int sum = 0;
        for (int j = 0; j < a.length; j++) {
            sum += a[jh][j];
        }
        return sum;
    }

    private int getSumV(int[][] a, int jv) {
//        int sum = a[0][j] + a[1][j] + a[2][j];
        int sum = 0;
        for (int j = 0; j < a.length; j++) {
            sum += a[j][jv];
        }
        return sum;
    }

    private int getSumD1(int[][] a) {
//        int sum = a[0][0] + a[1][1] + a[2][2];
        int sum = 0;
        for (int j = 0; j < a.length; j++) {
            sum += a[j][j];
        }
        return sum;
    }

    private int getSumD2(int[][] a) {
//        int sum = a[0][0] + a[1][1] + a[2][2];
        int sum = 0;

        int jd1 = 0;
        int jd2 = a.length - 1;
        while (jd1 < a.length) {
            sum += a[jd1][jd2];
            jd2--;
            jd1++;
        }
        return sum;
    }

    public static int getRandom(int min, int max) {
        int randomWithMathRandom = (int) ((Math.random() * (max - min)) + min);
        return randomWithMathRandom;
    }

}
