package y2015.p7176_LCM;


import java.io.*;
import java.util.*;

class Main7176Alex {

    static String ReadLn(int maxLg) // utility function to read from stdin
    {
        byte lin[] = new byte[maxLg];
        int lg = 0, car = -1;

        try {
            while (lg < maxLg) {
                car = System.in.read();
                if ((car < 0) || (car == '\n'))
                    break;
                lin[lg++] += car;
            }
        } catch (IOException e) {
            return (null);
        }

        if ((car < 0) && (lg == 0))
            return (null); // eof
        return (new String(lin, 0, lg));
    }

    public static void main(String args[]) // entry point from OS
    {
        Begin(); // the true entry point
    }

    static void Begin() {
        String input;
        StringTokenizer idata;

        while ((input = ReadLn(255)) != null) {
            idata = new StringTokenizer(input);
            int A = Integer.parseInt(idata.nextToken());
            int B = Integer.parseInt(idata.nextToken());
            int x = Math.abs(A - B);
            int z = 1;
            long mingcd = Long.MAX_VALUE;
            long minc = Long.MAX_VALUE;
            if (x != 0) {
                if (x > Math.min(A, B)) {
                    for (int i = 2; i*i <= x; i++) {
                        if (x % i == 0) {
                            long xx = i;
                            long zz = xx - A % xx;
                            long num1 = A + zz;
                            long num2 = B + zz;
                            long lcm = lcm(num1, num2);
                            if (lcm < mingcd) {
                                mingcd = lcm;
                                minc = zz;
                            } else if (lcm == mingcd) {
                                if (zz < minc) {
                                    minc = zz;
                                }
                            }
                            xx = x / i;
                            zz = xx - A % xx;
                            num1 = A + zz;
                            num2 = B + zz;
                            lcm = lcm(num1, num2);
                            if (lcm < mingcd) {
                                mingcd = lcm;
                                minc = zz;
                            } else if (lcm == mingcd) {
                                if (zz < minc) {
                                    minc = zz;
                                }
                            }
                        }
                    }
                }
                z = x - A % x;
            }
            long num1 = A + z;
            long num2 = B + z;
            long lcm = lcm(num1, num2);
            if (lcm < mingcd) {
                mingcd = lcm;
                minc = z;
            } else if (lcm == mingcd) {
                if (z < minc) {
                    minc = z;
                }
            }
            System.out.println(minc);
        }
    }

    private static long lcm(long num1, long num2) {
        return num1 * num2 / gcd(num1, num2);
    }

    private static long gcd(long num1, long num2) {
        long a = Math.max(num1, num2);
        long b = Math.min(num1, num2);
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
