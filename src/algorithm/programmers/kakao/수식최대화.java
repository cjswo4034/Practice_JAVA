package algorithm.programmers.kakao;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class 수식최대화 {
	String[] expression;
	Map<Character, Integer> prior = new HashMap<>();;
	
	public long solution(String input) {
		String[] values = input.split("[\\D]");
		String[] operands = input.split("[\\d]+");
		expression = new String[values.length * 2 - 1];
        
		expression[0] = values[0];
		for (int i = 1; i < expression.length; i += 2) {
			expression[i] = operands[i / 2 + 1];
			expression[i + 1] = values[i / 2 + 1];
		}
        
		return setPrior();
	}
	
	long setPrior() {
		long result = 0;
		for (int plus = 0; plus < 3; plus++) {
			for (int mul = 0; mul < 3; mul++) {
				if (plus == mul) continue;
				for (int sub = 0; sub < 3; sub++) {
					if (plus == sub || mul == sub) continue;
					prior.put('+', plus);
					prior.put('*', mul);
					prior.put('-', sub);
					result = Math.max(result, calculate());
				}
			}
		}
		return result;
	}
	
	long calculate() {
		Stack<Long> v = new Stack<>();
		Stack<Character> op = new Stack<>();
		
		for (String e: expression) {
			char tmp = e.charAt(0);
			if (Character.isDigit(tmp)) v.push(Long.parseLong(e));
			else {
				int p = prior.get(tmp);
				while (!op.isEmpty() && prior.get(op.peek()) >= p) {
					long v2 = v.pop();
					long v1 = v.pop();
					v.push(eval(v1, op.pop(), v2));
				}
				op.push(tmp);
			}
		}
		
		while (!op.isEmpty()) {
			long v2 = v.pop();
			long v1 = v.pop();
			v.push(eval(v1, op.pop(), v2));
		}
		
		return Math.abs(v.pop());
	}
	
	long eval(long v1, char op, long v2) {
		switch (op) {
		case '-': return v1 - v2;
		case '+': return v1 + v2;
		default: return v1 * v2;
		}
	}
}