package boj.binary_search.basic.boj2470;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N;
    static int[] array;

    static void init() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        array = new int[N + 1];
        scanner.nextLine();
        String infos = scanner.nextLine();
        String[] infoArray = infos.split(" ");
        for(int a = 1; a <= N; a++) {
            array[a] = Integer.parseInt(infoArray[a-1]);
        }
    }

    static void proc() {
        Arrays.sort(array, 1, N + 1);
        int best_sum = Integer.MAX_VALUE;
        int v1 = 0;
        int v2 = 0;
        for(int a = 1; a <= N; a++) {
            int cand = searchBeatCaseIndex(a + 1, -array[a]);

            // cand -1 인덱스와 cand 인덱스 모두를 비교하는 이유는 boj 2470 문제에서 특성값이 모두 다르다는 전제조건이 있기 때문에
            // 정렬을 한 이상 cand 혹은 cand -1 인덱스에 있는 값이 가장 a 인덱스 값과 비슷하게 나올것이므로 둘 중 합이 0과 더 가까운 경우를 찾는 것임
            // array = {-100, 99, 103, 104, 106} 일때 a = -100 / array[cand-1] = 99, array[cand] = 103 이면 array[cand-1] + array[a] 가 가장 0과 가까움
            if(a + 1 <= cand-1 && cand-1 <= N && Math.abs(array[a] + array[cand-1]) < best_sum) {
                best_sum = Math.abs(array[a] + array[cand-1]);
                v1 = array[a];
                v2 = array[cand-1];
            }

            if(a + 1 <= cand && cand <= N && Math.abs(array[a] + array[cand]) < best_sum) {
                best_sum = Math.abs(array[a] + array[cand]);
                v1 = array[a];
                v2 = array[cand];
            }
        }
        System.out.println(v1 + " " + v2);
    }

    static int searchBeatCaseIndex(int s, int tar) {
        int L = s;
        int R = N;
        // 원하는 값이 없으면 R + 1 리턴
        int result = R + 1;
        while(L <= R) {
            int mid = (L + R) / 2;
            if(array[mid] >= tar) {
                result = mid;
                R = mid - 1;
            }
            else {
                L = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        init();
        proc();
    }
}
