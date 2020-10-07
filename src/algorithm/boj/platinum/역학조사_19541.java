package algorithm.boj.platinum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// * 집단 감염되지 않은 모임의 참가자는 최초 감염자가 아니다.
// 집단감염되지 않은 모임의 참가자는 초기 감염자 후보에서 제외된다.

public class 역학조사_19541 {
    static int n, m;
    static int[] isInitialInfected;
    static boolean[] isFinalInfected, isSuspectedOfInitialInfection;
    static List<Integer>[] gatherings;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        isInitialInfected = new int[n + 1];
        isFinalInfected = new boolean[n + 1];
        isSuspectedOfInitialInfection = new boolean[n + 1];
        gatherings = new ArrayList[m];

        for (int gathering = 0, cnt, participant; gathering < m; gathering++) {
            st = new StringTokenizer(br.readLine());
            cnt = Integer.parseInt(st.nextToken());

            gatherings[gathering] = new ArrayList<>();
            while (cnt-- > 0) {
                participant = Integer.parseInt(st.nextToken());
                gatherings[gathering].add(participant);
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            isSuspectedOfInitialInfection[i] = isFinalInfected[i] = st.nextToken().equals("1");
        }

        for (int gathering = m - 1; gathering >= 0; gathering--) {
            boolean flag = true;

            for (int participant: gatherings[gathering]) {
                if (!isSuspectedOfInitialInfection[participant]) {
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                for (int participant: gatherings[gathering])
                    isSuspectedOfInitialInfection[participant] = false;
            }
        }

        for (int participant = 1; participant <= n; participant++)
            isInitialInfected[participant] = isSuspectedOfInitialInfection[participant] ? 1 : 0;

        infect(isSuspectedOfInitialInfection, 0);
        System.out.println(getAnswer(isFinalInfected, isSuspectedOfInitialInfection, isInitialInfected));
    }

    static String getAnswer(boolean[] arr1, boolean[] arr2, int[] res) {
        StringBuilder sb = new StringBuilder("YES\n");
        for (int i = 1; i <= n; i++) {
            if (arr1[i] != arr2[i]) return "NO";
            sb.append(res[i]).append(" ");
        }
        return sb.toString();
    }

    static void infect(boolean[] isInfected, int gathering) {
        if (m == gathering) return;

        for (int p1: gatherings[gathering]) {
            if (isInfected[p1]) {
                for (int p2: gatherings[gathering]) isInfected[p2] = true;
                break;
            }
        }
        infect(isInfected, gathering + 1);
    }
}