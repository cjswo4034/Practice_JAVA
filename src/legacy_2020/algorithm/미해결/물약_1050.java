package algorithm.미해결;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* [Gold] 위상정렬, 구현, 시뮬레이션
 * TODO 문제 잘못 이해함 -> 포션 'LOVE'를 만드는데 '최솟값' 구하기
 * */
public class 물약_1050 {
    static final Pattern pattern = Pattern.compile("([\\d]+)([A-Z]+)");

    static Map<String, Integer> indices = new HashMap<>();
    static Map<Integer, Long> prices = new HashMap<>();
    static Map<Integer, Recipe> recipes = new HashMap<>();
    static List<List<Integer>> adj = new ArrayList<>();
    static int n, m;
    static int[] indegree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        indegree = new int[n + m];

        for (int i = 0; i < n + m; i++) adj.add(new ArrayList<>());
        for (int i = 0, idx; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            idx = indices.size();
            indices.put(st.nextToken(), idx);
            prices.put(idx, Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < m; i++) split(br.readLine());

        int loveKey = indices.getOrDefault("LOVE", -1);
        if (loveKey == -1) {
            System.out.println(-1);
            return;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < m + n; i++) {
            if (indegree[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next: adj.get(cur)) {
                prices.put(next, prices.get(next) + prices.get(cur) * recipes.get(next).ingredients.get(cur));
                if (--indegree[next] == 0) {
                    q.add(next);
                    recipes.remove(next);
                }
            }
        }

        Long ans = -1L;
        if (!recipes.containsKey(loveKey)) {
            ans = prices.get(indices.get("LOVE"));
            if (ans > 1000000000) ans = 1000000001L;
        }
        System.out.println(ans);
    }

    static void split(String input) {
        String[] splitedStr = input.split("=");
        String recipeName = splitedStr[0];
        int recipeIdx, cnt, idx;

        indices.putIfAbsent(recipeName, indices.size());
        recipeIdx = indices.get(recipeName);
        prices.putIfAbsent(recipeIdx, 0L);

        Recipe recipe = new Recipe();
        Matcher matcher = pattern.matcher(splitedStr[1]);
        while (matcher.find()) {
            indices.putIfAbsent(matcher.group(2), indices.size());
            cnt = Integer.parseInt(matcher.group(1));
            idx = indices.get(matcher.group(2));

            indegree[recipeIdx]++;
            prices.putIfAbsent(idx, 0L);
            adj.get(idx).add(recipeIdx);
            recipe.ingredients.put(idx, cnt);
        }

        recipes.putIfAbsent(recipeIdx, recipe);
    }

    static class Recipe {
        Map<Integer, Integer> ingredients = new HashMap<>();
    }
}
