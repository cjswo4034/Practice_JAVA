package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 우편함 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();

		int[] arr, answer;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n, a, b;
			n = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			arr = new int[n];
			answer = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int min = 0, max = 0, size;
			for (int currTime = arr[min]; currTime <= 1000; currTime++) {
				boolean flag = false;
				// 가장 최근 도착한 편지 : max
				// currTime == arr[max] -> max + 1;
				if (arr.length > max && currTime == arr[max]) max++;
				size = max - min;
				
				// 현재시간에서 쌓인 편지의 개수가 A 이상인가?
				// 가장 오래 기다린 편지 : min
				// 가장 최근 도착한 편지 : max
				// max - min + 1 >= A
				if (size >= a) flag = true;
				
				// 현재시간에서 가장 오래 기다린 편지가 B시간 전에 온 편지인가?
				// -> 가장 오래 기다린 편지 : min
				// -> currTime == b + arr[min]
				if (arr.length > min && currTime == b + arr[min]) flag = true;
				
				// 쌓인 편지함을 편지 갯수 / 2 하라.
				// 쌓인 편지함 갯수: 홀수 -> size / 2 + 1
				if (flag) {
					int to = min + (size / 2) + size % 2;
					for (int i = min; i < to && i < arr.length; i++)
						answer[i] = currTime;
					min = to;
				}
			}
			
			System.out.println(Arrays.toString(answer));
			for (int ans : answer) {
				sb.append(ans).append(" ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
