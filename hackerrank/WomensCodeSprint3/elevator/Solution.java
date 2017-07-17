package sctions.hackerrank.elevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int w = in.nextInt();
        int c = in.nextInt();
        if (n==0 || c==0) {System.out.printf("%d %d", 0,0); return;}
        int m = in.nextInt();

        Deque<Integer[]> people = new LinkedList<>();
        for (int a0 = 0; a0 < n; a0++) {
            int id = in.nextInt();
            int t = in.nextInt();
            int f = in.nextInt();
            people.add(new Integer[]{id, t, f, (m - 1 == a0 ? 1 : 0)});
        }

        Deque<Integer[]> teacher = new LinkedList<>();
        Deque<Integer[]> student = new LinkedList<>();

        int current_time = 0;
        int trip = 0;
        int rory = 0;
        Integer[] lastPerson=null;
        while (true) {
            if (teacher.isEmpty() && student.isEmpty()) {
                current_time = Math.max(people.peek()[1], current_time);
            }
            while (!people.isEmpty() && people.peek()[1] <= current_time + w) {
                Integer[] person = people.poll();
                if (person[0] == 2) teacher.offer(person);
                else student.offer(person);
            }

            int maxfloor = 0;
            for (int i = 0; i < c; i++) {
                if (!teacher.isEmpty()) {
                    Integer[] pp = teacher.poll();
                    maxfloor = Math.max(pp[2], maxfloor);
                    if (pp[3] == 1) rory = trip + 1;
                    lastPerson=pp;
                } else if (!student.isEmpty()) {
                    Integer[] ss = student.poll();
                    maxfloor = Math.max(ss[2], maxfloor);
                    if (ss[3] == 1) rory = trip + 1;
                    lastPerson=ss;
                }
            }

            if (student.isEmpty() && teacher.isEmpty() && people.isEmpty()){
                System.out.printf("%d %d", rory, current_time + w + lastPerson[2]);
                return;
            }
            current_time = current_time + w + maxfloor * 2;
            trip++;
        }
    }
}