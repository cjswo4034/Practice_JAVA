package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Gold2
public class 놀이공원_1561 {
    static long n, m, max;
    static long[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new long[(int)m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (max < arr[i]) max = arr[i];
        }

        max *= n;   // 놀이기구의 최대 운영횟수는 운영시간이 가장 긴 놀이기구 하나만 n명을 태웠을 때이다.
        System.out.println(binarySearch());
    }

    // mid: 총 운영시간
    // sum: 운영시간이 mid일 때 놀이기구를 탄 사람들의 수 (mid일 때 탄 사람도 포함)
    static long binarySearch() {
        long l = 1, r = max, mid;
        long ans = 0, res = max, sum;
        while (l <= r) {
            mid = (l + r) >> 1;
            sum = 0;

            // 첫 번째에는 전부 탈 수 있으므로 mid - 1
            for (long opTime: arr)
                sum += (mid - 1) / opTime + 1;

            // n명 이상을 태울 수 있는 운영시간 중 최소값을 구한다.
            if (sum < n) l = mid + 1;
            else {
                r = mid - 1;
                if (mid < res) res = mid;
            }
        }

        res--; sum = 0;
        for (long opTIme: arr) sum += res / opTIme + 1;

        // sum: 운영시간이 res일 때 놀이기구를 탄 사람들의 합. 언제나 sum >= n을 만족한다.
        // cnt: 운영시간이 res일 때 사람을 태우지 않은 놀이기구의 수.
        // 따라서 뒤에서 cnt번 째 놀이기구가 사람을 마지막으로 태운 놀이기구다.
        int cnt = (int)(sum - n);
        for (int i = (int)(m - 1); i >= 0 && cnt >= 0 ; i--) {
            // 운영시간(res) % 놀이기구 i의 운영시간(arr[i]) == 0 이면
            // 운영시간이 res일 때 놀이기구 i는 손님을 태운 것.
            if (res % arr[i] == 0) {
                ans = i;
                cnt--;
            }
        }

        return ans + 1;
    }
}
