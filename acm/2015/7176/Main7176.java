
import java.io.*;
import java.util.*;

class Main7176 {

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
            if (x != 0) {
                z = x - A % x;
            }
            long num1 = A + z;
            long num2 = B + z;
            long gcd = gcd(num1, num2);
            System.out.println(z);
        }
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
