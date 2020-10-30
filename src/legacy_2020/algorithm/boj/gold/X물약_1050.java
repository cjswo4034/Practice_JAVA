package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class X물약_1050 {
	static Pattern pattern = Pattern.compile("([\\d]+)([A-Z]+)");
	static Market market = new Market();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int answer = 0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			market.addIngredient(st.nextToken(), Integer.parseInt(st.nextToken()));
		}
		
		Map<String, Potion> potionMap = new HashMap<>();
		String[] input;
		for (int i = 0; i < m; i++) {
			input = br.readLine().split("=");
			String potion = input[0];
			Recipe recipe = split(input[1]);
			
			potionMap.computeIfAbsent(potion, (key) -> new Potion(key)).addRecipe(recipe);
		}
		
		Queue<Potion> potions = new LinkedList<>(potionMap.values());
		Potion potion;
		while (!potions.isEmpty()) {
			int size = potions.size();
			int previous = potions.size();
			
			while(size-- > 0) {
				potion = potions.poll();

				int failCount = potion.make();
				if (failCount == 0) market.addPotion(potion);
				else potions.add(potion);
			}
			
			if (previous == potions.size()) {
				answer = -1;
				break;
			}
		}
		
		if (answer != -1) answer = market.getPrice("LOVE");
		System.out.println(answer);
	}
	
	static Recipe split(String recipe) {
		Recipe res = new Recipe();
		Matcher matcher = pattern.matcher(recipe);
		
		while (matcher.find()) {
			res.add(matcher.group(2), Integer.parseInt(matcher.group(1)));
		}
		
		return res;
	}
	
	// potion
	static class Recipe{
		List<String> ingredients = new ArrayList<>();
		List<Integer> counts = new ArrayList<>();
		boolean isMade;
		int price;

		void add(String ingredients, int count) {
			this.ingredients.add(ingredients);
			this.counts.add(count);
		}

		@Override
		public String toString() {
			return "Recipe [ingrdients=" + Arrays.toString(ingredients.toArray()) + ", counts=" + Arrays.toString(counts.toArray()) + "]";
		}
	}
	
	// 포션을 제외한 재료들 오름차순 정렬
	static class Potion{
		List<Recipe> recipes = new ArrayList<>();
		String name;
		int price;
		
		public Potion(String name) {
			this.name = name;
			this.price = Integer.MAX_VALUE;
		}

		public Potion(String name, Recipe recipe) {
			this.name = name;
			this.recipes.add(recipe);
		}

		void addRecipe(Recipe recipe) {
			recipes.add(recipe);
		}
		
		// 만들 수 없다면 실패
		int make() {
			int flag = 0;
			for (Recipe recipe : recipes) {
				if (!recipe.isMade) {
					if (market.isMakeable(recipe)) {
						recipe.price = market.make(recipe);
						recipe.isMade = true;
						price = Math.min(price, recipe.price);
					} else flag++;
				}
			}
			return flag;
		}
	}
	
	static class Market {
		Map<String, Integer> prices = new HashMap<>();
		
		void addIngredient(String ingredient, int price) {
			prices.put(ingredient, price);
		}
		
		void addPotion(Potion potion) {
			prices.put(potion.name, potion.price);
		}
		
		int getPrice(String name) {
			if (prices.containsKey(name)) return prices.get(name);
			return -1;
		}
		
		boolean isMakeable(Recipe recipe) {
			for (String ingredient: recipe.ingredients) {
				if (!prices.containsKey(ingredient)) return false;
			}
			return true;
		}
		
		int make(Recipe recipe) {
			int price = 0;
			
			List<Integer> counts = recipe.counts;
			List<String> ingredients = recipe.ingredients;
			
			int count;
			String ingredient;
			for (int i = 0, len = ingredients.size(); i < len; i++) {
				ingredient = ingredients.get(i);
				count = counts.get(i);
				price += prices.get(ingredient) * count;
			}
			
			return recipe.price = price;
		}
	}
}
