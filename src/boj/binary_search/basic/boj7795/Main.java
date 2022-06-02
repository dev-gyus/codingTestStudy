package boj.binary_search.basic.boj7795;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int M;
    static int N;
    static int[] A;
    static int[] B;
//

    static void init() {
        N = scanner.nextInt();
        M = scanner.nextInt();
        A = new int[N + 1];
        for(int a = 1; a <= N; a++) {
            A[a] = scanner.nextInt();
        }
        B = new int[M + 1];
        for(int b = 1; b <= M; b++) {
            B[b] = scanner.nextInt();
        }
    }

    static int searching(int L, int R, int target) {
        int result = L-1;
        while(L <= R) {
            int index = (L+R) / 2;

            if(target <= B[index]) {
                R = index - 1;
            } else {
                result = index;
                L = index + 1;
            }
        }
        return result;
    }

    static void proc() {
        Arrays.sort(B, 1, M + 1);
        int ans = 0;
        for(int a = 1; a < A.length; a++) {
            ans += searching(1, M, A[a]);
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for(int a = 0; a < t; a++) {
            init();
            proc();
        }
    }
}
