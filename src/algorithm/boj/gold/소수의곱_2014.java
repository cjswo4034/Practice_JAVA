package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// gold 3
public class 소수의곱_2014 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long[] primes = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            primes[i] = Integer.parseInt(st.nextToken());
            pq.add(primes[i]);
        }

        long num;
        for (int i = 1; i < k; i++) {
            num = pq.poll();
            for (long prime: primes) {
                pq.add(prime * num);
                if (num % prime == 0) break;
            }
        }
        System.out.println(pq.poll());
    }
}
