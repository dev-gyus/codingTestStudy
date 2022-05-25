package boj.sort.card;

import java.util.Arrays;
import java.util.Scanner;

/**
 * BOJ 11652
 */
public class Main {
    static int N;
    static long[] array;
    static long mode;

    static void init() {
        Scanner scanner = new Scanner(System.in);
        Main.N = scanner.nextInt();
        array = new long[Main.N + 1];
        for(int a = 1; a <= Main.N; a++) {
            Main.array[a] = scanner.nextLong();
        }
    }

    static void func() {
        if(N == 1) {
            mode = array[1];
            return;
        }
        Arrays.sort(array, 1, N+1);
        int maxCount = 1;
        int modeCount = 1;
        mode = array[1];
        for(int a = 2; a <= N; a++) {
            if(array[a] == array[a-1]) {
                modeCount++;
            } else {
                modeCount = 1;
            }
            if(modeCount > maxCount) {
                maxCount = modeCount;
                mode = array[a];
            }
        }
    }

    public static void main(String[] args) {
        init();
        func();
        System.out.println(mode);
    }
}
