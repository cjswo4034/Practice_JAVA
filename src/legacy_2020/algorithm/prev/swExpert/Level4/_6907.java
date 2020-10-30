package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class _6907 {
    static int pre;
    static String result, number, figure;;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("yotta",  24); map.put("zetta",  21); map.put("exa",  18);
        map.put("peta",  15); map.put("tera",  12); map.put("giga",  9);
        map.put("mega",  6); map.put("kilo",  3); map.put("hecto", 2);
        map.put("deca",  1); map.put("deci",  -1); map.put("centi",  -2);
        map.put("milli",  -3); map.put("micro",  -6); map.put("nano",  -9);
        map.put("pico",  -12); map.put("femto", -15); map.put("ato",  -18);
        map.put("zepto",  -21); map.put("yocto",  -24);

        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            if(st.countTokens() == 3){
                number = st.nextToken();
                pre = map.get(st.nextToken());
                figure = st.nextToken();
            } else {
                number = st.nextToken();
                pre = 0;
                figure = st.nextToken();
            }

            result = solve() + " * " + "10^" + pre + " " + figure;

            bw.write("#" + t + " " + result + "\n");
        }
        bw.flush();
    }

    public static String solve(){
        boolean zero = true, comma = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0, length = number.length(); i < length; i++) {
            if(number.charAt(i) != '0' && number.charAt(i) != '.'){
                zero = false;
            }
            if(number.charAt(i) != '.'){
                if(zero){
                    pre--;
                } else {
                    sb.append(number.charAt(i));
                }
            } else {
                comma = true;
                pre += (i - 1);
            }
        }
        if(!comma){
            pre += (number.length() - 1);
        }

        if(sb.length() != 1) sb.insert(1, '.');
        return sb.toString();
    }
}
