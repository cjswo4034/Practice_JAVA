package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _1865 {
	static int n;
	static double result;
	static double[][] input;
	static boolean[] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			input = new double[n][n];
			check = new boolean[n];

			StringTokenizer st;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					input[i][j] = Double.parseDouble(st.nextToken()) / 100;
				}
			}

			result = 0;
			solve1();

			bw.write("#" + t + " " + String.format("%.6f", result) + "\n");
			bw.flush();
		}
	}

	static void solve1() {
		result = 0;

		dfs(0, 100);
	}

	static void dfs(int depth, double mul) {
		if(result >= mul) {
			return;
		}

		if (depth == n) {
			result = mul;
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!check[i]) {
				check[i] = true;
				dfs(depth + 1, mul * input[depth][i]);
				check[i] = false;
			}
		}
	}
}

/*
static double cash[][] = new double[17][(1<<16)];
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    for(int test_case = 1; test_case <= T; test_case++) {
        int N = sc.nextInt();
        double[][] data = new double[N+1][N+1];
        for(int i = 0; i < 17; i++) {
            for(int j = 0; j < (1<<16); j++){
                cash[i][j] = -1;
            }
        }
         
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                data[i][j] = sc.nextDouble()/100;
            }
        }
        for(int i = 0; i < N; i++) {
            int  chk = (1<< i);
            cash[1][chk] = data[1][i+1];
        }
        for(int i = 2; i <= N; i++) {
            for(int j = 0; j < (1<<N); j++) {
                    if(cash[i-1][j] == -1) continue;
                    //System.out.println("i : " +  i + ", j : " + j);
                    for(int k = 0; k < N; k++) {
                        int chk = (1<<k);
                        if((chk & j) == 0) {
                            //System.out.println("cash : " + cash[i][(chk|j)] + ", cash[i-1] : " + cash[i-1][j] + ", data" + data[i][k+1]);
                            cash[i][(chk | j)] = Math.max(cash[i][chk|j], cash[i-1][j]*data[i][k+1]);
                        }
                    }
            }
        }
        double max = cash[N][(1<<N)-1]*100;
        System.out.println("#" + test_case + " " + String.format("%.6f", max));
         
    }
}*/
