package y2015.p7181_OlympicParade;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.*;
import java.util.*;

class Main7181Alex {

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
            int N = Integer.parseInt(idata.nextToken());
            int K = Integer.parseInt(idata.nextToken());
            boolean good = true;
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int i = 0; i < N; i++) {
                input = ReadLn(255);
                if (input == null) {
                    good = false;
                    break;
                }
                idata = new StringTokenizer(input);
                int n = Integer.parseInt(idata.nextToken());
                int z = 1;
                if (map.containsKey(n)) {
                    z = map.get(n) + 1;
                }
                map.put(n, z);
            }
            if (!good) {
                break;
            }
            for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
                Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) iterator.next();
                if (entry.getValue() % K != 0) {
                    System.out.println(entry.getKey());
                    break;
                }
            }
        }
    }
}

