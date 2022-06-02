package boj.binary_search.advanced.parametric_search.boj2805;

import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static int[] H;

    static void init() {
        Scanner scanner = new Scanner(System.in);
        String nmInfos = scanner.nextLine();
        String[] nmSplit = nmInfos.split(" ");
        N = Integer.parseInt(nmSplit[0]);
        M = Integer.parseInt(nmSplit[1]);
        H = new int[N + 1];
        String hInfos = scanner.nextLine();
        String[] hSplit = hInfos.split(" ");
        for(int a = 1; a <= N; a++) {
            H[a] = Integer.parseInt(hSplit[a - 1]);
        }
    }

    static void proc() {
        long ans = 0;
        int L = 1, R = 1000000000;
        while(L <= R) {
            int mid = (L + R) / 2;
            long sum = calc(mid);
            if(sum >= M) {
                L = mid + 1;
                ans = mid;
            }
            else {
                R = mid - 1;
            }
        }
        System.out.println(ans);
    }

    static long calc(int h) {
        long sum = 0;
        for(int a = 1; a <= N; a++) {
            if(H[a] >= h) {
                sum += H[a] - h;
            }
            if(sum >= M) break;
        }
        return sum;
    }

    public static void main(String[] args) {
        init();
        proc();
    }
}
