package boj.brutal_force.n_m_3;

import java.util.Scanner;

public class Main {
    static StringBuilder builder = new StringBuilder();
    static int N; // 한 자리에 올 수 있는 수의 최대값
    static int M; // 수열 자리수
    static int[] array;



    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        array = new int[M + 1];
    }

    static void rec_func(int k) {
        // 만약 k가 M+1이라면 <- 다 고른상태 => 기존에 골랐던거 포문돌면서 builder append
        if(k == M + 1) {
            for(int i = 1; i <= M; i++) builder.append(array[i]).append(" ");
            builder.append("\n");
        }
        // 다 못골랐으면 rec_func로 조건에 맞는 값들 모두 고르기
        else {
            for (int a = 1; a <= N; a++) {
                array[k] = a;
                rec_func(k + 1);
            }
        }
    }

    public static void main(String[] args) {
        input();
        rec_func(1);
        System.out.println(builder);
    }
}
