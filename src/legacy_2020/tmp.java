import java.util.*;

class Solution {
    public String solution(String[] votes, int k) {
        PriorityQueue<Car> pq = new PriorityQueue<>();
        Map<String, Integer> map = new HashMap<>();
        List<Integer> values = new ArrayList<>();
        int acc = 0, sum = 0;

        for (String vote: votes) {
            map.compute(vote, (s, i) -> i == null ? 1 : i + 1);
        }

        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            pq.add(new Car(entry.getKey(), entry.getValue()));
            values.add(entry.getValue());
        }

        values.sort((o1, o2) -> -Integer.compare(o1, o2));
        for (int i = 0; i < k; i++) {
            sum += values.get(i);
        }

        Car prev = pq.poll(), curr;
        acc += prev.value;
        while (!pq.isEmpty()) {
            curr = pq.poll();

            if (acc + curr.value >= sum) break;
            acc += curr.value;

            prev = curr;
        }

        return prev.name;
    }

    static class Car implements Comparable<Car>{
        String name;
        int value;

        public Car(String name, int value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public int compareTo(Car o) {
            if (value != o.value) return Integer.compare(value, o.value);
            return -name.compareTo(o.name);
        }
    }
}