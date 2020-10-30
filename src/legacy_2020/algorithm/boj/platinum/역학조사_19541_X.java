package algorithm.boj.platinum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* 역학조사 (XXXXXXXXXXXXXXXXXXX)
* 1. 참가자 전원이 최종 감염자인 모임을 구한다.
* 2. 참가자 i가 참가한 모든 모임이 집단 감염되었다면 i는 최초 감염자이다.
* 3. 2를 토대로 0번째 모임부터 시뮬레이션을 한다.
* 4. 3의 결과와 입력된 최종 감염자 리스트를 비교하여 일치하면 결과를 출력하고 다르면 No를 출력한다.
* */

// 집단 감염되었지만 최초 감염자가 아닐 수 있다.
// ---> 집단 감염되지 않은 모임의 참가자는 최초 감염자가 아니다. 로 구현하니까 해결됨.

public class 역학조사_19541_X {
    static int n, m;
    static int[] isInitialInfected;
    static boolean[] isFinalInfected, isInfectedMass, isSuspectedOfInitialInfection;
    static List<Integer>[] participants, gatherings;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        isInitialInfected = new int[n];
        
        isFinalInfected = new boolean[n];                   // i번째 참가자의 감염여부
        isInfectedMass = new boolean[m];                    // i번째 모임의 참가자 전원이 감염되었다면 True (집단감염)
        isSuspectedOfInitialInfection = new boolean[n];     // i번째 참가자의 초기감염여부
        
        participants = new ArrayList[n];                    // i번째 참가자가 참여한 모임리스트
        gatherings = new ArrayList[m];                      // i번째 모임의 참가자 리스트
        
        for (int i = 0; i < n; i++) participants[i] = new ArrayList<>();
        
        for (int gathering = 0, cnt, participant; gathering < m; gathering++) {
            st = new StringTokenizer(br.readLine());
            cnt = Integer.parseInt(st.nextToken());

            gatherings[gathering] = new ArrayList<>();
            while (cnt-- > 0) {
                participant = Integer.parseInt(st.nextToken()) - 1;
                participants[participant].add(gathering);
                gatherings[gathering].add(participant);
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            isFinalInfected[i] = st.nextToken().equals("1");
            isSuspectedOfInitialInfection[i] = isFinalInfected[i];
        }

        // * 1. 집단 감염된 모임 구하기 (참가자 전원이 최종 감염자인 모임을 구한다)
        for (int gathering = 0; gathering < m; gathering++) {
            boolean flag = true;
            for (int participant: gatherings[gathering]) {
                if (!isSuspectedOfInitialInfection[participant]) {        // 참가자 중 한명이라도 최종 감염자가 아니면
                    flag = false;
                    break;
                }
            }
            isInfectedMass[gathering] = flag;
        }

        /* 2. 최초 감염자 구하기 (참가자가 참가한 모든 모임이 집단 감염되었다면 i는 최초 감염자이다)
        *     a) 최초 감염자가 참여한 모든 모임의 참가자는 감염자이다.
        *     b) 최초 감염자는 최종 감염자 리스트에 있다.
        * (모임의 참여자 중 한명이라도 최종 감염자가 아니라면 해당 모임의 참가자들은 초기 감염자가 아니다.)
        * */
        for (int participant = 0; participant < n; participant++) {
            boolean flag = true;
            for (int gathering: participants[participant]) {
                if (!isInfectedMass[gathering]) {
                    flag = false;
                    break;
                }
            }
            if (flag && isFinalInfected[participant]) {        // 참가한 모든 모임이 집단 감염되었고 최종 감염자라면
                isSuspectedOfInitialInfection[participant] = true;
                isInitialInfected[participant] = 1;
            }
        }

        /* 역추적이 불가능한 경우
        *   1. 초기 감염자가 최종 감염자로 의심되지 않은 경우
        *   2. 최종 감염자가 참가한 모임의 참가자가 이후 참여한 모임에서 전원이 감염되지 않은 경우
        *   3. 시작 전 감염된 참가자가 없는데 모임 후 감염된 사람이 있는 경우
        * */

        // 3. 시뮬레이션 (초기 감염자로 0번 모임부터 전파한다.)
        infect(isSuspectedOfInitialInfection, 0);

        // 시뮬레이션 결과와 입력값이 다르면 No, 같으면 res 출력
        System.out.println(getAnswer(isFinalInfected, isSuspectedOfInitialInfection, isInitialInfected));
    }

    static String getAnswer(boolean[] arr1, boolean[] arr2, int[] res) {
        StringBuilder sb = new StringBuilder("YES\n");
        for (int i = 0; i < n; i++) {
            if (arr1[i] != arr2[i]) return "NO";    // 예측과 다르면 NO
            sb.append(res[i]).append(" ");
        }
        return sb.toString();
    }

    // 모임에 감염자가 있다면 해당 모임의 참가자 전원을 감염시킨다.
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