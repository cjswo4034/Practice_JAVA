package algorithm.미해결;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// [Platinum 2] BNF, 구현, 파싱
// TODO 시간초과
// 1. 좌항과 우항을 그룹으로 묶음...
public class 삼항연산자_19534 {
    static Expression expression;
    static List<Character> variables;
    static Map<Character, Value> values;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();

        values = input.chars().mapToObj(i -> (char)i)
                .filter(Character::isLowerCase)
                .distinct()
                .collect(Collectors.toMap(ch -> ch, Value::new));

        variables = new ArrayList<>(values.keySet());

        values.put('0', new Value('0', false));
        values.put('1', new Value('1', true));

        expression = new Expression(input);

        System.out.println(dfs(0) * (int)Math.pow(2, n - variables.size()));
    }

    static int dfs(int depth) {
        if (depth == variables.size()) return expression.eval() ? 0 : 1;

        Value value = values.get(variables.get(depth));
        int res = 0;

        value.eval = false;
        res += dfs(depth + 1);
        value.eval = true;
        res += dfs(depth + 1);
        return res;
    }

    static class Expression {
        Value value;
        Condition condition;
        Expression expression1, expression2;

        public Expression(String exp) {
            int qmPos = exp.indexOf('?');
            if (qmPos == -1) {      // 1. expression ::= value
                value = values.get(exp.charAt(0));
            } else {                // 2. expression ::= condition ? expression : expression
                condition = new Condition(exp.substring(0, qmPos));
                setExpression(exp.substring(qmPos + 1));
            }
        }

        // expression ::= condition ? expression : expression
        public void setExpression(String exp) {
            int qmPos = exp.indexOf('?');
            int colonPos = exp.indexOf(':');
            if (qmPos != -1) {
                int qmCnt = 1, colonCnt = 1;
                while (qmCnt >= colonCnt && qmPos < colonPos) {
                    qmPos = exp.indexOf('?', qmPos + 1);
                    if (qmPos != -1) qmCnt++;

                    colonPos = exp.indexOf(':', colonPos + 1);
                    colonCnt++;
                }
            }

            expression1 = new Expression(exp.substring(0, colonPos));
            expression2 = new Expression(exp.substring(colonPos + 1));
        }

        public boolean eval() {
            if (value != null) return value.eval();
            else return condition.eval() ? expression1.eval() : expression2.eval();
        }

        @Override
        public String toString() {
            if (value != null) return (value.eval() ? "1" : "0");
            else return condition + "?" + expression1 + ":" + expression2;
        }
    }

    static class Condition {
        Value v1, v2;

        // condition ::= <value> == <value>
        public Condition(String condition) {
            v1 = values.get(condition.charAt(0));
            v2 = values.get(condition.charAt(3));
        }

        public boolean eval() {
            return v1.eval() == v2.eval();
        }

        @Override
        public String toString() {
            return v1 + "==" + v2;
        }
    }

    static class Value {
        final char value;
        boolean eval;

        // lowerCharacter ::= <boolean>
        // variable ::= [a-z] (= <lowerCharacter>)
        // boolean ::= true | false
        // value ::= <boolean> | <variable>
        public Value(char value) {
            this.value = value;
        }

        public Value(char value, boolean eval) {
            this.value = value;
            this.eval = eval;
        }

        public boolean isVariable() {
            return value != '0' && value != '1';
        }

        public boolean eval() {
            return eval;
        }

        @Override
        public String toString() {
            return eval ? "1" : "0";
        }
    }
}
