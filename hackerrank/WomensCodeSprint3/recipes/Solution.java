package sctions.hackerrank.recipes;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int r = in.nextInt();
            int p = in.nextInt();
            int n = in.nextInt();
            int m = in.nextInt();

            if (p==0|| r==0) {System.out.print(0); return;}

            Integer[] pantry = new Integer[m];
            for(int pantry_i=0; pantry_i < m; pantry_i++){
                pantry[pantry_i] = in.nextInt();
            }
            List<Integer> pan = Arrays.asList(pantry);
            int[] cost = new int[p];
            for(int cost_i=0; cost_i < p; cost_i++){
                cost[cost_i] = in.nextInt();
            }

            int[][] recipe = new int[r][p];
            int[] recipe2 = new int[r];
            for(int row=0; row < r; row++){
                for(int col=0; col < p; col++){
                    int idxNeed = in.nextInt();
                    idxNeed= pan.contains(col)?0:idxNeed;
                    recipe[row][col] = idxNeed;
                    recipe2[row] += idxNeed<<col;
                }
            }

            Integer ret = Integer.MAX_VALUE;
            for (int i=0; i< (1<<recipe[0].length); i++){
                int res=0;
                for (int j=0; j<recipe2.length; j++){
                    if ( (recipe2[j] & i) == recipe2[j]){
                        res++;
                        if (res==n){
                            ret = Math.min(ret, calculateCash(i, cost));
                            break;
                        }
                    }

                }
            }

            System.out.println(ret);
        }
    }

    private static int calculateCash(int ingr, int[] cost) {
        int res=0;
        for (int i=0; i<cost.length ; i++){
            if ((ingr & (1<<i)) != 0) res +=cost[i];
        }

        return res;
    }
}