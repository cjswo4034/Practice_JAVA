package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class _1228 {
	static int result;
	static LinkedList<String> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;
		for (int t = 1; t <= T; t++) {
			list = new LinkedList<>();
			
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				list.add(st.nextToken());
			}
			
			n = Integer.parseInt(br.readLine());
			String[] cmds = br.readLine().split("I");
			for (int i = 1; i < cmds.length; i++) {
				String[] cmd = cmds[i].split(" ");
				
				int off = Integer.parseInt(cmd[1]);
				for (int j = 3; j < cmd.length; j++) {
					list.add(off++, cmd[j]);
				}
			}			
			
			bw.write("#" + t + " ");			
			for (int i = 0; i < list.size(); i++) {
				if(i == 10) break;
				bw.write(list.get(i) + " ");
			}			
			bw.write("\n");
			bw.flush();
		}
	}
}
