package boj.brutal_force;

public class N_M_3 {
    static StringBuilder builder = new StringBuilder();
    static int N;
    static int M;
    static int[] array;

    public static void exec(int n, int m) {
        N = n;
        M = m;
        array = new int[M+1];
    }

    public static void rec_func(int column) {
        if(column == M + 1) {
            for(int i=1; i <= M; i++) builder.append(array[i]).append(" ");
        } else {
            for(int cand = 1; cand <= N; cand++) {
                array[column] = cand;

                rec_func(column + 1);
                array[column] = 0; // 다음줄 초기화
            }
        }
        builder.append("\n");
    }

    public static void main(String[] args) {
        exec(4,2);

        rec_func(1);
        System.out.println(builder.toString());
    }
}
