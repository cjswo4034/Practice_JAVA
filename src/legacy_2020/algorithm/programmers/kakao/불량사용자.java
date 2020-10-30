package algorithm.programmers.kakao;

import java.util.HashSet;
import java.util.Set;

public class 불량사용자 {
	public static void main(String[] args) {
		System.out.println("123496789".matches("123456789".replace("5", "[\\w\\d]")));
	}
	
	Set<Integer> res;
    public int solution(String[] user_id, String[] banned_id) {
        res = new HashSet<>();
        backtracking(user_id, banned_id, 0, 0);
        return res.size();
    }
    
    void backtracking(String[] userId, String[] bannedId, int visited, int depth) {
    	if (depth == bannedId.length) {
    		res.add(visited);
    		return;
    	}
    	
    	for (int i = 0; i < userId.length; i++) {
			if ((visited & (1 << i)) != 0 || match(userId[i], bannedId[depth])) continue;
			backtracking(userId, bannedId, visited | 1 << i, depth + 1);
		}
    }
    
    boolean match(String uid, String bid) {
    	if (uid.length() != bid.length()) return false;
    	for (int i = 0; i < uid.length(); i++) {
			if (uid.charAt(i) == bid.charAt(i) || bid.charAt(i) == '*') continue;
			return false;
		}
    	return true;
    }
}
