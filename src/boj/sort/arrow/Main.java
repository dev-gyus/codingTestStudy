package boj.sort.arrow;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * BOJ 15970
 */
public class Main {
    static List<Point>[] listArray;
    static int N;

    static void init() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        listArray = new ArrayList[N + 1];
        scanner.nextLine();
        for(int a = 1; a <= N; a++) {
            String pointInfo = scanner.nextLine();
            String[] split = pointInfo.split(" ");
            int x = Integer.parseInt(split[0]);
            int color = Integer.parseInt(split[1]);
            if(listArray[color] == null) listArray[color] = new ArrayList<>();
            listArray[color].add(new Point(x, color));
        }
    }
    static void func() {
        int total = 0;
        for(int a = 1; a <= N; a++) {
            List<Point> pointList = listArray[a];
            if(pointList == null || pointList.size() <= 1) continue;
            // x 기준 오름차순 정렬
            Collections.sort(pointList);
            for (int b = 0; b < pointList.size(); b++) {
                if (b == 0) {
                    total += Math.abs(pointList.get(0).x - pointList.get(1).x);
                } else if (b < pointList.size() - 1) {
                    total += Math.min(Math.abs(pointList.get(b).x - pointList.get(b - 1).x), Math.abs(pointList.get(b).x - pointList.get(b + 1).x));
                } else {
                    total += Math.abs(pointList.get(b).x - pointList.get(b - 1).x);
                }
            }
        }
        System.out.println(total);
    }

    public static void main(String[] args) {
        init();
        func();
    }

    static class Point implements Comparable<Point>{
        public int color;
        public int x;

        public Point(int x, int color) {
            this.x = x;
            this.color = color;
        }

        @Override
        public int compareTo(Point o) {
            // 좌표의 오름차순
            return this.x - o.x;
        }
    }

}
