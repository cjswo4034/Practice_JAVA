package algorithm.prev.swExpert.Level4;

import java.util.Arrays;

public class _3812_x {
    static int x, y, z;
    static int n, result;

    /*public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            sb.append("#").append(t).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            Integer.parseInt(st.nextToken());

            sb.append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }*/

    public void solve(){
        x = 4;
        y = 3;
        z = 3;
        n = 3;

        int[] arr = new int[n];
        int[] tmpArr = new int[n];
        for (int i = 0; i < n; i++) {
            tmpArr[i] = x / n;
            if (i < x % n) tmpArr[i] += 1;
        }   // first-line

        if (y >= n){
            sum(arr, tmpArr, n);
            for (int i = 0; i < n; i++) {
                arr[i] = arr[i] * (y / n);
            }
        }
        sum(arr, tmpArr,y % n);

        System.arraycopy(arr, 0, tmpArr, 0, n);

        if (z >= n){
            sum(arr, tmpArr, n - 1);
            for (int i = 0; i < n; i++) {
                arr[i] = arr[i] * (z / n);
            }
        }
        sum(arr, tmpArr,z % n - 1);

        System.out.println(Arrays.toString(arr));
    }

    public void sum(int[] arr, int[] tmpArr, int leng){
        for (int i = 0; i < leng; i++) {
            for (int j = 0; j < n; j++) arr[j] += tmpArr[j];
            int tmp = tmpArr[n - 1];
            System.arraycopy(tmpArr, 0, tmpArr, 1, n - 1);
            tmpArr[0] = tmp;
        }
    }
}
