package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _1168 {
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> list = new ArrayList<>();
		StringBuilder ans = new StringBuilder();
		
		String str = br.readLine();
		int n = Integer.parseInt(str.split(" ")[0]);
		int m = Integer.parseInt(str.split(" ")[1]);
		
		for(int i = 1 ; i <= n ; i++) {
			list.add(i);
		}
		
		int cnt = m - 1;
		for(int i = 0 ; i < n ; i++) {
			ans.append(list.get(cnt));
			list.remove(cnt);
			
			if(list.isEmpty())
				break;
			
			ans.append(", ");
			cnt += m - 1;
			cnt %= list.size();
		}
		System.out.println("<" + ans + ">");
	}
}
