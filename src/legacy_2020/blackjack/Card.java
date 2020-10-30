package blackjack;

public class Card {
    private final Pattern pattern;  // 인스턴스 생성과 상속을 방지하여 상수값의 타입안정성이 보장된다.
    private final Denomination denomination;  // 카드의 점수는 Denomination 필드로 딜러가 계산한다.

    private Card() {    // 기본생성자로 생성 방지
        this(null, null);
    }

    // 인자를 가진 생성자를 사용한 이유
    // 패턴과 끗수를 가지고 Card가 어떤 행위를 하는지 Deck은 알 필요가 없다.
    // (= Card에서 어떤 행위를 하더라도 Deck은 영향이 없다.)
    // 패턴과 끗수를 가지지 않은 카드는 생성될 수 없다.
    public Card(Pattern pattern, Denomination denomination) {
        this.pattern = pattern;
        this.denomination = denomination;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public int getPoint() {
        return denomination.ordinal() + 1;
    }

    @Override
    public String toString() {
        return "Card{" +
                "pattern=" + pattern +
                ", denomination=" + getPoint() +
                '}';
    }
}
