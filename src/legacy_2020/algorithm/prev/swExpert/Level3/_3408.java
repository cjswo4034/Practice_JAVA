package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _3408 {
	static int[] array;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int start = Integer.parseInt(br.readLine());
		int T = Integer.parseInt(br.readLine());
		for (int t = start; t <= T; t++) {
			sb.append("#" + t + " ");
			
			String test = String.valueOf(t);
			
			String num = test;// br.readLine();
			long test_num = Long.parseLong(test);//br.readLine());
			
			long test1, test2, test3;
			test1 = test_num % 2 == 0 ? (test_num / 2) * (test_num + 1) : test_num * ((test_num + 1) / 2);
			test2 = test_num * test_num;
			test3 = test2 + test_num;
			String str1 = test1 + " " + test2 + " " + test3;
			
			
			if(num.length() <= 5) {
				int tmp = Integer.parseInt(num);
				int s1 = (tmp * (tmp + 1)) / 2;
				int s2 = tmp * tmp;
				int s3 = s2 + tmp;
				
				sb.append(s1 + " " + s2 + " " + s3 + "\n");
				continue;
			}
			
			
			array = new int[num.length() * 2];
			
			String num1 = num.substring(0, num.length() / 2);
			String num2 = num.substring(num.length() / 2, num.length());
			
			long n1 = Long.parseLong(num1);
			long n2 = Long.parseLong(num2);
			int pow = num2.length();
			
			long z0 = n1 * n1;
			long z1 = 2 * (n1 * n2);
			long z2 = n2 * n2;

			// s2
			func3(z0, z1, z2, pow);

			String s2 = result();
			
			// s3
			Long tmp = Long.parseLong(num);
			for (int i = array.length - 1; tmp > 0; i--) {
				array[i] += (int)(tmp % 10);
				tmp /= 10;
				
				if(array[i] >= 10) {
					func(i);
				}
			}

			String s3 = result();
			
			// s1
			tmp = Long.parseLong(num);
			array = new int[num.length() * 2];
			if(tmp % 2 == 0) {
				func2(tmp + 1, tmp / 2);	
			} else {
				func2(tmp, (tmp + 1) / 2);
			}
			
			String s1 = result();
			
			String str2 = s1 + " " + s2 + " " + s3;
			
			if(!str1.equals(str2)) {
				System.out.println(str1);
				System.out.println(str2);
				System.out.println(num);
			}
			//sb.append(s1 + " " + s2 + " " + s3);
			//sb.append("\n");
		}
		//System.out.println(sb);
	}
	
	static String result() {
		StringBuilder sb = new StringBuilder();
		boolean check = false;
		for(int i : array) {
			if(i != 0) {
				check = true;
			}
			if(!check && i == 0) {
				continue;
			}
			sb.append(i);
		}
		
		return sb.toString();
	}
	
	static void func(int idx) {		// 올림
		if(idx < 0) return;
		if(array[idx] >= 10) {
			array[idx] %= 10;
			array[idx - 1]++;
			if(array[idx - 1] >= 10) {
				func(idx - 1);
			}
		}
	}
	
	static void func2(long A, long B) {
		int pow = String.valueOf(A).length() / 2;
		
		int a1 = (int)(A / Math.pow(10, pow));
		int b1 = (int)(B / Math.pow(10, pow));
		int a2 = (int)(A % Math.pow(10, pow));
		int b2 = (int)(B % Math.pow(10, pow));
				
		long z0 = a1 * b1;
		long z1 = a1 * b2 + b1 * a2;
		long z2 = a2 * b2;
		
		func3(z0, z1, z2, pow);
	}
	
	static void func3(long z0, long z1, long z2, int pow) {
		for (int i = array.length - pow * 2 - 1; z0 > 0; i--) {
			array[i] = (int)(z0 % 10);
			z0 /= 10;
		}
		
		for (int i = array.length - pow - 1; z1 > 0; i--) {
			array[i] += (int)(z1 % 10);
			z1 /= 10;
			
			if(array[i] >= 10) {
				func(i);
			}
		}
		
		for (int i = array.length - 1; z2 > 0; i--) {
			array[i] += (int)(z2 % 10);
			z2 /= 10;
			
			if(array[i] >= 10) {
				func(i);
			}
		}
	}
}
