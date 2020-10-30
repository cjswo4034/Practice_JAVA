package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.math.BigInteger;

public class _4366 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            String str2 = br.readLine();
            String str3 = br.readLine();

            BigInteger two = new BigInteger(str2, 2);
            BigInteger three = new BigInteger(str3, 3);

            String transTwo = two.toString(2);
            String transThr = three.toString(3);

            if (str2.length() != transTwo.length()) transTwo = appender(str2, transTwo);
            if (str3.length() != transThr.length()) transThr = appender(str3, transThr);

            char[] tw = transTwo.toCharArray();
            char[] th = transThr.toCharArray();

            sb.append("#").append(t).append(" ").append(solve(tw, th)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static BigInteger solve(char[] tw, char[] th){
        BigInteger result = new BigInteger("-1");
        for (int i = 0; i < tw.length; i++) {
            tw[i] = tw[i] == '0' ? '1' : '0';
            for (int j = 0; j < th.length; j++) {
                char tmp = th[j];
                for (char k = '0'; k < '3'; k++) {
                    if(tmp == k) continue;
                    th[j] = k;
                    if(isSame(tw, th)){
                        return new BigInteger(new String(tw), 2);
                    }
                }
                th[j] = tmp;
            }
            tw[i] = tw[i] == '0' ? '1' : '0';
        }
        return result;
    }

    public static boolean isSame(char[] tw, char[] th){
        BigInteger two = new BigInteger(new String(tw), 2);
        BigInteger three = new BigInteger(new String(th), 3);
        return two.equals(three);
    }

    public static String appender(String str, String target){
        int diff = str.length() - target.length();
        while(diff-- > 0){
            target = "0" + target;
        }
        return target;
    }
}
