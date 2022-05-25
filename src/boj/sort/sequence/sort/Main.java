package boj.sort.sequence.sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * BOJ 1015
 */
public class Main {
    static int[] A;
    static int[] B;
    static int[] P;
    static int N;

    static void init() {
        Scanner scanner = new Scanner(System.in);
        Main.N = scanner.nextInt();
        Main.A = new int[Main.N];
        Main.B = new int[Main.A.length];
        Main.P = new int[Main.A.length];
        scanner.nextLine();
        String aInfos = scanner.nextLine();
        String[] aStrings = aInfos.split(" ");
        for(int a = 0; a < Main.N; a++) {
            Main.A[a] = Integer.parseInt(aStrings[a]);
        }
        for(int a = 0; a < Main.N; a++) {
            Main.B[a] = Main.A[a];
        }
        Arrays.sort(B);
    }

    static void exe_func() {
        for(int a = 0; a < Main.A.length; a++) {
            for(int b = 0; b < Main.B.length; b++) {
                if(Main.A[a] == Main.B[b]) {
                    Main.P[a] = b;
                    Main.B[b] = -1;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        init();
        exe_func();
        StringBuilder builder = new StringBuilder();
        for(int p : Main.P) {
            builder.append(p).append(" ");
        }
        System.out.println(builder);
    }

}
