package sctions.hackerrank.flowers;

import java.io.*;
import java.util.*;

public class Solution {

    static final long modulo = (long)Math.pow(10,9) +7;
    static long[][][][][][][] storage = new long[11][11][11][11][11][11][];

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int rb = in.nextInt();
        int rg = in.nextInt();
        int br = in.nextInt();
        int bg = in.nextInt();
        int gr = in.nextInt();
        int gb = in.nextInt();

        long[] vector1 = transitions(rb,rg,br,bg,gr,gb);
        long[] vector2 = transitions(br,bg,rb,rg,gb,gr);
        long[] vector3 = transitions(gr,gb,rg,rb,bg,br);

        long[] vector4 = new long[Math.max(Math.max(vector1.length,vector2.length),vector3.length)];

        for (int i=0;i <vector4.length; i++){
            vector4[i]=(((vector1.length-1>=i)?vector1[i]:0)
                    +((vector2.length-1>=i)?vector2[i]:0)
                    +((vector3.length-1>=i)?vector3[i]:0))%modulo;
        }
        long sum=0;
        long nFact = factorial(n-1);
        for (int i=0;i <vector4.length; i++){
            //C 0 OF N-1
            sum+=vector4[i]*combinations(n-1,i, nFact)%modulo;
            sum%=modulo;
        }

        System.out.print(sum);
    }

    private static long[] transitions(int rb, int rg, int br, int bg, int gr, int gb) {
        if (rb==0 && rg==0) return new long[]{1};
        if (storage[rb][rg][br][bg][gr][gb] !=null && storage[rb][rg][br][bg][gr][gb].length>0)
            return storage[rb][rg][br][bg][gr][gb];
        long[] a1 = new long[0];
        long[] a2 = new long[0];
        if (rb>0) {
            a1 = transitions(br, bg, rb-1, rg, gb, gr);
        }
        if (rg>0) {
            a2 = transitions(gr, gb, rg-1, rb, bg, br);
        }
        long[] a3 = new long[Math.max(a1.length,a2.length)+1];
        a3[0]=1;
        for (int i=1; i<a3.length; i++){
            a3[i]=(((a1.length-1>=i-1)?a1[i-1]:0)+
                    ((a2.length-1>=i-1)?a2[i-1]:0))% modulo;
        }
        storage[rb][rg][br][bg][gr][gb]=a3;

        return storage[rb][rg][br][bg][gr][gb];
    };

    static long combinations(long n, int k, long nFact) {
        if (k>n) return 0;
        long kFact = factorial(k);
        long nsubkFact = factorial(n - k);
        long x = (kFact * nsubkFact) % modulo;
        long comb = pow(x, modulo -2);
        return (nFact*comb) % modulo;

    }

    static long factorial(long n) {
        long res = 1;
        for (long i = 1; i <= n; ++i) res = (res*i)% modulo;
        return res;
    }

    static long pow(long x, long n) {
        if (n==0) return 1;

        long v = pow((x * x)% modulo, n / 2);
        if (n%2==0) {
            return v;
        }
        else{
            return (v * x)% modulo;
        }
    }
}

