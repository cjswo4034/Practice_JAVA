package oop.blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CardDeck {
    private static final List<Card> cards = new ArrayList<>();
    // 아직 사용하지 않은 카드의 가장 낮은 인덱스. 섞지 않으면 순차적으로 뽑힌다.
    private static boolean isShuffled;
    private static int currentUsed;

    // 생성자 카드들을 생성하라는 역할만 있으므로 비지니스 로직을 알 필요가 없다.
    public CardDeck() {}

    public void prepForNewGame() {
        isShuffled = false;
        currentUsed = 0;
        generatedCards();
    }

    private void generatedCards() {
        cards.clear();
        Arrays.stream(Pattern.values())
                .forEach(pattern -> Arrays.stream(Denomination.values())
                .forEach(denomination -> cards.add(new Card(pattern, denomination))));
    }

    public void setIsShuffled(boolean shuffled) {
        isShuffled = shuffled;
    }

    // 카드 한장을 준다.
    // 섞이지 않았거나 모든 카드가 배부되었으면 null
    public Optional<Card> getCard() {
        if (!isShuffled || currentUsed == cards.size()) return Optional.empty();
        return Optional.of(cards.get(currentUsed++));
    }

    // 딜러가 덱을 섞기 전에 카드를 얻을 수 없다.
    public Optional<List<Card>> getCards() {
        if (isShuffled) return Optional.of(cards);
        else return Optional.empty();
    }
}