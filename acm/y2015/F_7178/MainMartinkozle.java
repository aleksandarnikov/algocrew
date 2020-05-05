package y2015.F_7178;

import java.io.*;
import java.util.*;

class MainMartinkozle {
    static String ReadLn(int maxLg)  // utility function to read from stdin
    {
        byte lin[] = new byte[maxLg];
        int lg = 0, car = -1;
        String line = "";

        try {
            while (lg < maxLg) {
                car = System.in.read();
                if ((car < 0) || (car == '\n')) break;
                lin[lg++] += car;
            }
        } catch (IOException e) {
            return (null);
        }

        if ((car < 0) && (lg == 0)) return (null);  // eof
        return (new String(lin, 0, lg));
    }

    public static void main(String args[])  // entry point from OS
    {
        Begin();            // the true entry point
    }

    static int pow(int x, int n) {
        int result = 1;
        for (int i = 0; i < n; i++) {
            result *= x;
        }
        return result;
    }

    static int solve(int[] c, int x, int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += pow(x, i) * c[i];
        }
        return sum;
    }

    static int findNextSolution(int[] c, int n) {
        int z = Math.abs(c[0]);
        for (int i = 1; i <= z; i++) {
            if (z % i == 0) {
                if (solve(c, i, n) == 0) {
                    return i;
                }
                if (solve(c, -i, n) == 0) {
                    return -i;
                }
            }
        }
        return 0;
    }

    static void Begin() {
        String input;
        StringTokenizer idata;
        int n;

        while ((input = ReadLn(255)) != null) {
            idata = new StringTokenizer(input);
            n = Integer.parseInt(idata.nextToken());
            input = ReadLn(255);
            idata = new StringTokenizer(input);
            int[] c = new int[n + 1];
            c[n] = 1;
            for (int i = 0; i < n; i++) {
                c[n - i - 1] = Integer.parseInt(idata.nextToken());
            }
            int counter = 0;
            while (true){
                // System.out.println(Arrays.toString(c));
                int z = findNextSolution(c, n);
                if (z != 0) {
                    // System.out.println(" - " + z);
                    counter++;
                } else {
                    break;
                }
                for (int i = n - 1; i >= 0; i--) {
                    c[i] += z * c[i + 1];
                }
                System.arraycopy(c, 1, c, 0, n);
                c[n] = 0;
            }
            System.out.println(n - counter);
        }
    }
}
