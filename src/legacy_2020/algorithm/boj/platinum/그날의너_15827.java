package algorithm.boj.platinum;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

/* [Platinum 4] 구현, 수학
 * HAPPY에 대해 각각의 변수를 편미분한다.
 * 1. 계수를 리프노드부터 구한다.
 * 2. 미분값을 루트노드부터 아래로 구한다.
 * */
public class 그날의너_15827 {
    static final Fraction NULL_FRACTION = new Fraction(0, 1);
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        Map<String, Factor> factors = new HashMap<>();

        int n = Integer.parseInt(st.nextToken());
        char type;
        String token;
        Factor factor;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            token = st.nextToken();
            type = st.nextToken().charAt(0);
            factor = factors.computeIfAbsent(token, Factor::new);
            factor.type = type;
            if (type == 'E') {
                factor.setFraction(new Fraction(Integer.parseInt(st.nextToken()), 1));
            } else {
                factor.op1 = factors.computeIfAbsent(st.nextToken(), Factor::new);
                factor.op2 = factors.computeIfAbsent(st.nextToken(), Factor::new);
            }
        }

        factor = factors.get("HAPPY");
        factor.eval();
        factor.eval(new Fraction(1, 1));

        factors.values()
                .stream()
                .sorted()
                .forEach(sb::append);

        System.out.println(sb);
    }

    static class Factor implements Comparable<Factor> {
        Fraction fraction, res;
        Factor op1, op2;
        String token;
        char type;

        public Factor(String token) {
            this.token = token;
        }

        public void setFraction(Fraction fraction) {
            this.fraction = fraction;
        }

        public void eval() {
            if (op1 != null) op1.eval();
            if (op2 != null) op2.eval();
            if (op1 != null && op2 != null) {
                fraction = Fraction.apply(op1.fraction, type, op2.fraction);
            }
        }

        // 미분 구하기
        // 구하려는 항을 x로 두고 미분한다.
        public void eval(Fraction fraction) {
            res = fraction;
            if (op1 != null && op2 != null) {
                if (type == 'A' || type == 'S') {
                    op1.eval(fraction);
                    op2.eval(type == 'A' ? fraction : fraction.copy().toMinus());
                } else if (type == 'M') {
                    op1.eval(Fraction.apply(fraction, type, op2.fraction));
                    op2.eval(Fraction.apply(fraction, type, op1.fraction));
                } else {
                    op1.eval(Fraction.apply(fraction, type, op2.fraction));
                    op2.eval(Fraction.apply(fraction, 'M', Fraction.div(op1.fraction, op2.fraction.pow())).toMinus());
                }
            }
        }

        @Override
        public String toString() {
            if (res == null) res = NULL_FRACTION;
            return token + ' ' + res + '\n';
        }

        @Override
        public int compareTo(Factor o) {
            return token.compareTo(o.token);
        }
    }

    private static long getGcd(long p, long q) {
        if (q == 0) return p;
        return getGcd(q, p % q);
    }

    static class Fraction {
        long numerator, denominator;
        boolean isNegative;

        public Fraction(long numerator, long denominator) {
            if (numerator < 0) {
                isNegative = true;
                numerator *= -1;
            }
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public Fraction(long numerator, long denominator, boolean isNegative) {
            this.numerator = numerator;
            this.denominator = denominator;
            this.isNegative = isNegative;
        }

        public Fraction copy() {
            return new Fraction(numerator, denominator, isNegative);
        }

        private Fraction toIrreducibleFraction() {
            long gcd = getGcd(numerator, denominator);
            if (gcd == 1) return this;
            numerator /= gcd;
            denominator /= gcd;
            return this;
        }

        private Fraction pow() {
            return mul(this, this).toIrreducibleFraction();
        }

        private Fraction toMinus() {
            isNegative ^= true;
            return this;
        }

        public static Fraction apply(Fraction f1, char ch, Fraction f2) {
            BiFunction<Fraction, Fraction, Fraction> function;
            switch (ch) {
                case 'M': function = Fraction::mul; break;
                case 'D': function = Fraction::div; break;
                case 'A': function = Fraction::add; break;
                default: function = Fraction::sub; break;
            }
            return function.apply(f1, f2).toIrreducibleFraction();
        }

        public static Fraction mul(Fraction f1, Fraction f2) {
            long numerator = f1.numerator * f2.numerator;
            long denominator = f1.denominator * f2.denominator;
            return new Fraction(numerator, denominator, f1.isNegative ^ f2.isNegative);
        }

        public static Fraction div(Fraction f1, Fraction f2) {
            long numerator = f1.numerator * f2.denominator;
            long denominator = f1.denominator * f2.numerator;
            return new Fraction(numerator, denominator, f1.isNegative ^ f2.isNegative);
        }

        public static Fraction add(Fraction f1, Fraction f2) {
            long numerator, denominator;
            long f1N = f1.numerator * (f1.isNegative ? -1 : 1);
            long f2N = f2.numerator * (f2.isNegative ? -1 : 1);
            if (f1.denominator == f2.denominator) {
                numerator = f1N + f2N;
                denominator = f1.denominator;
            } else {
                numerator = (f1N * f2.denominator) + (f2N * f1.denominator);
                denominator = f1.denominator * f2.denominator;
            }
            return new Fraction(numerator, denominator);
        }

        public static Fraction sub(Fraction f1, Fraction f2) {
            long numerator, denominator;
            long f1N = f1.numerator * (f1.isNegative ? -1 : 1);
            long f2N = f2.numerator * (f2.isNegative ? -1 : 1);
            if (f1.denominator == f2.denominator) {
                numerator = f1N - f2N;
                denominator = f1.denominator;
            } else {
                numerator = (f1N * f2.denominator) - (f2N * f1.denominator);
                denominator = f1.denominator * f2.denominator;
            }
            return new Fraction(numerator, denominator);
        }

        @Override
        public String toString() {
            return (isNegative ? "-" : "") + numerator + "/" + denominator;
        }
    }
}