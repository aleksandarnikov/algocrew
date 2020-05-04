import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (!sc.hasNext()) {
                break;
            }
            int A = sc.nextInt();
            int B = sc.nextInt();
            int x = Math.abs(A - B);
            int z = 1;
            if (x != 0) {
                z = A % x;
            }
            long num1 = A + z;
            long num2 = B + z;
            long gcd = gcd(num1, num2);
            System.out.println(gcd);
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
