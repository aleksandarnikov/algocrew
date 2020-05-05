package y2015.E_7177;

import java.util.Iterator;
import java.io.*;
import java.util.*;

class MainAlex {

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
            Node[] nodes = new Node[N];
            for (int i = 0; i < N; i++) {
                nodes[i] = new Node();
                nodes[i].i = i;
            }

            int M = Integer.parseInt(idata.nextToken());
            boolean good = true;
            for (int i = 0; i < M; i++) {
                input = ReadLn(255);
                if (input == null) {
                    good = false;
                    break;
                }
                idata = new StringTokenizer(input);
                int twoway = Integer.parseInt(idata.nextToken());
                int u = Integer.parseInt(idata.nextToken());
                int v = Integer.parseInt(idata.nextToken());
                int weight = Integer.parseInt(idata.nextToken());
                Node n1 = nodes[u - 1];
                Node n2 = nodes[v - 1];
                n1.nodes.add(n2);
                n1.weights.add(weight);
                if (twoway == 2) {
                    n2.nodes.add(n1);
                    n2.weights.add(weight);
                }
            }
            if (!good) {
                break;
            }
            input = ReadLn(255);
            if (input == null) {
                break;
            }
            idata = new StringTokenizer(input);
            int boardingfee = Integer.parseInt(idata.nextToken());
            input = ReadLn(255);
            if (input == null) {
                break;
            }
            idata = new StringTokenizer(input);
            int companyindex = Integer.parseInt(idata.nextToken()) - 1;
            input = ReadLn(255);
            if (input == null) {
                break;
            }
            idata = new StringTokenizer(input);
            int K = Integer.parseInt(idata.nextToken());
            input = ReadLn(255);
            if (input == null) {
                break;
            }
            idata = new StringTokenizer(input);
            int[] people = new int[K + 1];
            for (int i = 0; i < K; i++) {
                people[i + 1] = Integer.parseInt(idata.nextToken()) - 1;
            }
            people[0] = companyindex;

            int[][] m = new int[K + 1][K + 1];
            for (int i = 0; i < K + 1; i++) {
                int[] visited = new int[N];
                List<Integer> queue = new LinkedList<Integer>();
                queue.add(people[i]);
                visited[people[i]] = 1;
                while (!queue.isEmpty()) {
                    int p = queue.remove(0);
                    Node P = nodes[p];
                    Iterator<Node> i1 = P.nodes.iterator();
                    Iterator<Integer> i2 = P.weights.iterator();
                    while (i1.hasNext()) {
                        Node n1 = i1.next();
                        int weight = i2.next();
                        if (visited[n1.i] == 0 || visited[P.i] + weight < visited[n1.i]) {
                            visited[n1.i] = visited[P.i] + weight;
                            queue.add(n1.i);
                        }
                    }
                }

                for (int j = 0; j < K + 1; j++) {
                    m[i][j] = visited[people[j]] - 1;
                }
            }

            int[] mask = new int[1 << K];
            List<Integer> masks = new ArrayList<Integer>();
            for (int i = 0; i < 1 << K; i++) {
                int k = 0;
                int ii = i;
                int[] ind = new int[4];
                int q = 0;
                while (ii > 0) {
                    int z = ii % 2;
                    if (z > 0) {
                        if (k < 4) {
                            ind[k] = q + 1;
                        }
                        k++;
                    }
                    ii /= 2;
                    q++;
                }
                if (k <= 0 || k > 4) {
                    continue;
                }
                for (int i1 = 0; i1 < k; i1++) {
                    if (k == 1) {
                        int len = m[0][ind[i1]];
//                        if ((m[0][ind[i1]] != 0))
//                        {
                            if (mask[i] == 0 || len < mask[i]) {
                                mask[i] = len;
                            }
//                        }
                        continue;
                    }
                    for (int i2 = 0; i2 < k; i2++) {
                        if (i2 == i1) {
                            continue;
                        }
                        if (k == 2) {
                            int len = m[0][ind[i1]] + m[ind[i1]][ind[i2]];
//                            if ((m[0][ind[i1]] != 0) && (m[ind[i1]][ind[i2]] != 0))
//                            {
                                if (mask[i] == 0 || len < mask[i]) {
                                    mask[i] = len;
                                }
//                            }
                            continue;
                        }
                        for (int i3 = 0; i3 < k; i3++) {
                            if (i3 == i2 || i3 == i1) {
                                continue;
                            }
                            if (k == 3) {
                                int len = m[0][ind[i1]] + m[ind[i1]][ind[i2]] + m[ind[i2]][ind[i3]];
//                                if ((m[0][ind[i1]] != 0) && (m[ind[i1]][ind[i2]] != 0) && (m[ind[i2]][ind[i3]] != 0) )
//                                {
                                    if (mask[i] == 0 || len < mask[i]) {
                                        mask[i] = len;
                                    }
//                                }
                                continue;
                            }
                            for (int i4 = 0; i4 < k; i4++) {
                                if (i4 == i3 || i4 == i2 || i4 == i1) {
                                    continue;
                                }
                                int len = m[0][ind[i1]] + m[ind[i1]][ind[i2]] + m[ind[i2]][ind[i3]] + m[ind[i3]][ind[i4]];
//                                if ((m[0][ind[i1]] != 0) && (m[ind[i1]][ind[i2]] != 0) && (m[ind[i2]][ind[i3]] != 0) && (m[ind[i3]][ind[i4]] != 0))
//                                {
                                    if (mask[i] == 0 || len < mask[i]) {
                                        mask[i] = len;
//                                    }
                                }
                            }
                        }
                    }
                }
                if (mask[i] > 0) {
                    mask[i] += boardingfee;
                    masks.add(i);
                }
            }

            int[] fullmask = new int[1 << K];
            for (int msk : masks) {
                if (fullmask[msk] == 0 || mask[msk] < fullmask[msk]) {
                    fullmask[msk] = mask[msk];
                }
            }
            for (int msk = 0; msk < 1 << K; msk++) {
                if (fullmask[msk] == 0) {
                    continue;
                }
                for (int msk2: masks) {
                    if ((msk & msk2) > 0) {
                        continue;
                    }
                    int nmask = msk | msk2;
                    if (fullmask[nmask] == 0 || mask[msk2] + fullmask[msk] < fullmask[nmask]) {
                        fullmask[nmask] = mask[msk2] + fullmask[msk];
                    }
                }
            }

            System.out.println(fullmask[(1 << K) - 1]);
        }

    }
}
class Node {
    int i = 0;
    List<Node> nodes = new ArrayList<Node>();
    List<Integer> weights = new ArrayList<Integer>();
}
