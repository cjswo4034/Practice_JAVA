package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Gold 2
public class 도토리숨기기_15732 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        Box[] boxies = new Box[k];
        for (int i = 0, from, to, step; i < k; i++) {
        	st = new StringTokenizer(br.readLine());
        	from = Integer.parseInt(st.nextToken());
        	to = Integer.parseInt(st.nextToken());
        	step = Integer.parseInt(st.nextToken());
			boxies[i] = new Box(from, to, step);
		}
        
        long l = 0, r = n, ans = 0;
        while (l <= r) {
        	long mid = (l + r) >> 1;
        	long sum = 0;
        	for (Box box: boxies) {
        		if (mid < box.from) continue;
        		else if (mid > box.to) sum += box.getCount(box.to);	// static method로 바꾸면 더 빠름
        		else sum += box.getCount(mid);
        	}
        	
//        	System.out.printf("%d, %d, %d => %d\n", l, mid, r, sum);
        	if (sum >= d) {
        		ans = mid;
        		r = mid - 1;
        	} else l = mid + 1;
        }
        System.out.println(ans);
	}
	
	static class Box {
		int from, to, step;
		public Box(int from, int to, int step) {
			this.from = from;
			this.to = to;
			this.step = step;
		}
		
		long getCount(long value) {
			return (value - from) / step + 1;
		}
	}
	
	// 무작정 풀기 (시간초과)
	// 개선: 이분탐색
	static int solution1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        
        int min = Integer.MAX_VALUE, max = 0;
        int[] arr = new int[n + 1];
        for (int i = 0; i < k; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int step = Integer.parseInt(st.nextToken());
        	for (int j = from; j <= to; j += step) {
				arr[j]++;
			}
        	
        	min = Math.min(min, from);
        	max = Math.max(max, to);
		}
        
        int sum = 0, i =0;
        for (i = min; i <= max; i++) {
			sum += arr[i];
			if (sum >= d) break;
        }
        return i;
	}
}
