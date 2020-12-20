package com.jayden.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 输入任意（用户，成绩）序列，可以获得成绩从高到低或从低到高的排列,相同成绩
 * 都按先录入排列在前的规则处理。
 * <p>
 * 示例：
 * jack      70
 * peter     96
 * Tom       70
 * smith     67
 * <p>
 * 从高到低  成绩
 * peter     96
 * jack      70
 * Tom       70
 * smith     67
 * <p>
 * 从低到高
 * <p>
 * smith     67
 * <p>
 * jack      70
 * Tom      70
 * peter     96
 */
public class StudentScoreSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (!br.ready()) {
            System.out.println("请输入学生人数：");
            int stuNum = Integer.parseInt(br.readLine());
            if (stuNum <= 2) {
                System.out.println("学生人数只接受大于2的，请重新运行本程序！");
                break;
            }

            System.out.println("请输入学生成绩的排序方式 0-降序，1-升序：");
            int stuSortType = Integer.parseInt(br.readLine());

            Student[] stuArray = new Student[stuNum];
            for (int i = 0; i < stuNum; i++) {
                System.out.println("请输入他们的名字和成绩，以一个空格隔开：");
                String[] str = br.readLine().split(" ");
                stuArray[i] = new Student(str[0], Integer.parseInt(str[1]));
            }

            if (stuSortType == 0) {
                for (int end = stuArray.length - 1; end > 0; end--) {
                    for (int i = 0; i < end; i++) {
                        if (stuArray[i].score < stuArray[i + 1].score) {
                            swap(stuArray, i, i + 1);
                        }
                    }
                }
            }

            if (stuSortType == 1) {
                for (int end = stuArray.length - 1; end > 0; end--) {
                    for (int i = 0; i < end; i++) {
                        if (stuArray[i].score > stuArray[i + 1].score) {
                            swap(stuArray, i, i + 1);
                        }
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < stuNum; i++) {
                sb.append(stuArray[i].name + " " + stuArray[i].score + "\n");
            }
            System.out.println(sb.toString());
            break;
        }
    }

    public static void swap(Student[] arr, int i, int j) {
        Student tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static class Student {
        public String name;
        public int score;


        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }
}
