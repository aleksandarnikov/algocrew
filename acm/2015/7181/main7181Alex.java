package algocrew;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main7181Alex{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < N; i++) {
            int n = sc.nextInt();
            int z = 1;
            if (map.containsKey(n)) {
                z = map.get(n);
            }
            map.put(n, z);
        }
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) iterator.next();
            if (entry.getValue() % K != 0) {
                System.out.println(entry.getKey());
            }
        }
    }
}
