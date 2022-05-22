package boj.brutal_force.n_m_2;

import java.util.Scanner;

public class Main {
    static StringBuilder builder = new StringBuilder();
    static int N;
    static int M;
    static int[] select;
    static int[] available;

    static void init() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        select = new int[M + 1];
        available = new int[N + 1];
    }

    // 1번째 자리수 부터 1~N값 까지의 값을 재귀적으로 고르고, 다 고르면 문자열 입력해줘
    static void rec_func(int k) {
        // 다 골랐으면 수열 쭉 나열 해줘
        if(k == M + 1) {
            for(int i = 1; i <= M; i++) {
                builder.append(select[i]).append(" ");
            }
            builder.append("\n");
        }
        // 다 안골랐으면 수를 골라줘
        else {
            for(int cand = 1; cand <= N; cand++) {
                if(select[k - 1] < cand) {
                    select[k] = cand;
                    rec_func(k + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        init();
        rec_func(1);
        System.out.println(builder.toString());
    }
}
