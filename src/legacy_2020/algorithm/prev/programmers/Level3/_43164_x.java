package algorithm.prev.programmers.Level3;

import java.util.*;

public class _43164_x {
    public static void main(String[] args) {
        _43164_x prob = new _43164_x();
        System.out.println(Arrays.toString(prob.solution(new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}})));
        System.out.println(Arrays.toString(prob.solution(new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}})));
    }

    public String[] solution(String[][] tickets){
        HashMap<Ticket, Integer> map = new HashMap<>();
        Set<Ticket> set = new HashSet<>();

        Ticket tk;
        for (String[] ticket : tickets){
            tk = new Ticket(ticket[0], ticket[1]);
            if (map.containsKey(tk)) map.put(tk, map.get(tk) + 1);
            else map.put(tk, 1);
        }

        for (Map.Entry<Ticket, Integer> entry : map.entrySet()){
            entry.getKey().setCnt(entry.getValue());
            set.add(entry.getKey());
        }
        String[] answer = new String[tickets.length + 1];

        List<String> path = getPath("ICN", set);
        path.toArray(answer);

        return answer;
    }

    private LinkedList<String> getPath(String from, Set<Ticket> tickets){
        LinkedList<String> path = null;

        if (allUsed(tickets)){
            path = new LinkedList<>();
            path.add(from);
            return path;
        }

        for (Ticket ticket : tickets){
            if (ticket.cnt < 1 || !ticket.from.equals(from)) continue;

            ticket.decreaseCnt();
            path = getPath(ticket.to, tickets);
            ticket.increaseCnt();

            if (path != null){
                path.addFirst(from);
                break;
            }
        }

        return path;
    }

    private boolean allUsed(Set<Ticket> tickets){
        for (Ticket ticket : tickets){
            if (ticket.cnt > 0) return false;
        }
        return true;
    }

    class Ticket implements Comparable<Ticket>{
        String from, to;
        int cnt;

        Ticket(String from, String to){
            this.from = from;
            this.to = to;
            this.cnt = 1;
        }

        void setCnt(int cnt){
            this.cnt = cnt;
        }

        void increaseCnt(){
            this.cnt++;
        }

        void decreaseCnt(){
            this.cnt--;
        }

        @Override
        public int compareTo(Ticket o) {
            if (this.from.equals(o.from)) return this.to.compareTo(o.to);
            return this.from.compareTo(o.from);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Ticket)) return false;
            Ticket ticket = (Ticket) obj;
            return this.from.equals(ticket.from) && this.to.equals(ticket.to);
        }

        @Override
        public int hashCode() {
            return (this.from + ", " + this.to).hashCode();
        }

        @Override
        public String toString() {
            return "Ticket{" +
                    "from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    /*public String[] solution(String[][] tickets) {
        HashMap<String, Integer> map = new HashMap<>();

        int size = 0;
        for (String[] ticket : tickets){
            if (!map.containsKey(ticket[0])) map.put(ticket[0], size++);
            if (!map.containsKey(ticket[1])) map.put(ticket[1], size++);
        }


        ArrayList<String>[] list = new ArrayList[size];
        System.out.println(Arrays.toString(map.entrySet().toArray()) + " : " + size);
        for (int i = 0; i < size; i++) list[i] = new ArrayList<>();
        for (String[] ticket : tickets){
            list[map.get(ticket[0])].add(ticket[1]);
        }
        for (int i = 0; i < size; i++) Collections.sort(list[i]);

        boolean[][] visited = new boolean[size][];
        for (int i = 0; i < visited.length; i++)
            visited[i] = new boolean[list[i].size()];
        String[] answer = new String[tickets.length + 1];
        answer[0] = "ICN";

        dfs(map, list, visited, answer, "ICN", 1);

        return answer;
    }

    private void dfs(HashMap<String, Integer> map, ArrayList<String>[] list, boolean[][] visited, String[] answer, String current, int depth){
        for (int i = 0; i < list[map.get(current)].size(); i++) {
            String next = list[map.get(current)].get(i);
            if (!visited[map.get(current)][i]){
                answer[depth++] = next;
                visited[map.get(current)][i] = true;
                dfs(map, list, visited, answer, next, depth);
            }
        }
    }*/
}