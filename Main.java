package nulp;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final int N = ReadFile.vertex();

    static void findWay(int[][] array, int[] min, int[] way) {
        int v = 1;
        int u, i = 0;
        Queue<Integer> plan = new LinkedList<Integer>();
        plan.add(--v);

        array[v][v] = 1;
        way[i] = v;
        for (u = 0; u < N; u++) {
            if (array[v][u] > 0 && !(array[u][u]==1)) {
                i++;
                if (array[v][N - 1] != 0) {
                    u = N - 1;
                    plan.add(u);
                    array[u][u] = 1;
                    System.out.println(v + ", " + u + " - " + array[v][u]);
                    min[u] = array[v][u];
                    way[i] = u;
                } else {
                    plan.add(u);
                    array[u][u] = 1;
                    System.out.println(v + ", " + u + " - " + array[v][u]);
                    min[u] = array[v][u];
                    v = u;
                    way[i] = v;
                }
            }
        }
    }

    static void findFlow(int[][] matr) {
        int[] min = new int[N];
        int[] way = new int[N];
        int minim;
        int max = 0;
        int w;
        boolean flag;

        do {
            w = 0;
            minim = Integer.MAX_VALUE;
            flag = false;
            System.out.println("Path:");
            findWay(matr, min, way);

            for (int i = 0; i < N; i++) {
                if (minim >= min[i] && min[i] > 0) {
                    minim = min[i];
                }
            }
            System.out.println("Minimum path - " + minim + "\n");

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (matr[i][i] != 0) {
                        if (i != N - 1) {
                            matr[i][i] = 0;
                            matr[way[w]][way[w + 1]] = matr[way[w]][way[w + 1]] - minim;
                            w++;
                        } else {
                            matr[i][i] = 0;
                        }
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                if (way[i] == N - 1) {
                    flag = true;
                }
            }

            if (flag) {
                max += minim;
                for (int i = 0; i < N; i++) {
                    way[i] = 0;
                }
            }


        } while (flag);

        System.out.println("\tMaximum flow - " + max);
    }

    public static void main(String[] args) {
        int[][] matr = ReadFile.matrix();

        System.out.println("Number of vertex: " + N);
        System.out.println("Matrix of weight: ");
        ReadFile.printMatrix(matr);

        findFlow(matr);

    }
}