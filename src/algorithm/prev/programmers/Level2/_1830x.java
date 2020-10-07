package algorithm.prev.programmers.Level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1830x {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		char [] sentence = input.toCharArray();
		int [] cnt = new int[26];
		
		for(char ch : sentence) {
			if(ch >= 'a')
				cnt[ch - 'a']++;
		}
		
		StringBuilder ans = new StringBuilder();
		for(int i = 0, leng = sentence.length ; i < leng ; i++) {
			char ch = sentence[i];
			if(ch < 'a') {	// 대문자 or 공백
				if(ch == ' '){
					ans = new StringBuilder("invalid");
					break;
				}
				
				if(i > 0 && Character.isUpperCase(input.charAt(i - 1)))
					ans.append(" " + ch);
				else
					ans.append(ch);
			} else {		// 소문자
				if(cnt[ch - 'a'] != 2 && cnt[ch - 'a'] != 0) {	// 규칙 1인 경우
					int start = input.indexOf(ch);
					int end = input.lastIndexOf(ch) + 2;
					
					if(sentence[start - 1] == '*' || sentence[end - 1] == '*') {
						ans = new StringBuilder("invalid");
						break;
					}
					
					String tmp = trim(input.substring(start, end).toCharArray(), ch);
					ans.append(tmp);
					i = end - 1;
					
					for(int j = start - 1 ; j < end ; j++) {
						sentence[j] = '*';
					}
				} else if(cnt[ch - 'a'] == 2) {	// 규칙 1, 2인 경우
					int start = input.indexOf(ch) + 1;
					int end = input.lastIndexOf(ch);
					
					ans.append(" " + input.substring(start, end) + " ");
					i = end;
				}
			}
		}
		
		System.out.println(ans.toString().trim());
	}

	static String trim(char [] str, char ch) {
		StringBuilder result = new StringBuilder();
		
		for(char tmp : str) {
			if(tmp != ch) {
				result.append(tmp);
			}
		}
		
		return result.toString();
	}

}
