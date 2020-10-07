package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _11723 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		long num = 0;
		
		while(n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			int com;
			if(str.equals("add")) {
				com = Integer.parseInt(st.nextToken());
				num = (num | (1 << com));
			} else if(str.equals("remove")) {
				com = Integer.parseInt(st.nextToken());
				num = (num & ~(1 << com));
			} else if(str.equals("check")) {
				com = Integer.parseInt(st.nextToken());
				if((num & (1 << com)) == 0) {
					bw.write("0\n");
				} else {
					bw.write("1\n");
				}
			} else if(str.equals("toggle")) {
				com = Integer.parseInt(st.nextToken());
				num = (num ^ (1 << com)); 
			} else if(str.equals("all")) {
				num = (1 << 21) - 1;
			} else{
				num = 0;
			}
		}
		bw.flush();
		
	}

}
