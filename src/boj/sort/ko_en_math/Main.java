package boj.sort.ko_en_math;

import java.util.*;

public class Main {
    static int total;
    static Student[] studentArray;

    static class Student implements Comparable<Student> {
        public int ko;
        public int en;
        public int math;
        public String name;

        public Student(String name, int ko, int en, int math) {
            this.name = name;
            this.ko = ko;
            this.en = en;
            this.math = math;
        }

        @Override
        public int compareTo(Student o) {
            if(this.ko != o.ko) return o.ko - this.ko;
            if(this.en != o.en) return this.en - o.en;
            if(this.math != o.math) return o.math - this.math;
            if(this.name.equals(o.name)) return 0;
            return name.compareTo(o.name);
        }
    }

    static void init() {
        Scanner scanner = new Scanner(System.in);
        Main.total = scanner.nextInt();
        studentArray = new Student[Main.total];
        scanner.nextLine();
        for(int a = 0; a < Main.total; a++) {
            String studentInfo = scanner.nextLine();
            String[] infos = studentInfo.split(" ");
            String name = infos[0];
            int ko = Integer.parseInt(infos[1]);
            int en = Integer.parseInt(infos[2]);
            int math = Integer.parseInt(infos[3]);
            studentArray[a] = new Student(name, ko, en, math);
        }
    }

    public static void main(String[] args) {
        init();
        Arrays.sort(Main.studentArray);
        StringBuilder builder = new StringBuilder();
        for(Student student : studentArray) {
            builder.append(student.name).append("\n");
        }
        System.out.println(builder.toString());
    }
}
