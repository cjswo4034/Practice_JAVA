package oop.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer {
    private static final int CAN_RECEIVE_POINT = 16;
    private final List<Card> cards;

    public Dealer() {
        cards = new ArrayList<>();
    }

    public void init() {
        cards.clear();
    }

    // 새로운 게임이 시작하면 카드덱을 섞는다.
    public void shuffle(CardDeck cardDeck) {
        cardDeck.setIsShuffled(true);
        List<Card> cards = cardDeck.getCards().orElseThrow(IndexOutOfBoundsException::new);
        Collections.shuffle(cards);
    }

    // 카드를 배부한다.
    public void handOutCardOn(CardDeck cardDeck, Player player){
        Card card = draw(cardDeck);
        player.receivedCard(card);
    }

    // 카드를 소유한다.
    public void takeCard(CardDeck cardDeck) {
        cards.add(draw(cardDeck));
    }

    // 카드덱에서 카드를 한 장 뽑는다.
    private Card draw(CardDeck cardDeck) {
        return cardDeck.getCard().orElseThrow(NullPointerException::new);
    }

    // 카드 점수 합계가 16점을 초과할 때까지 카드를 한 장 뽑는다.
    public boolean isAbleToTakeAnotherCard(CardDeck cardDeck) {
        if (getPoints(cards) > CAN_RECEIVE_POINT) return false;
        System.out.println("Dealer is drawing a card...");
        takeCard(cardDeck);
        return true;
    }

    // 게임이 끝나면(더 이상 카드를 뽑는 게이머가 없으면) 게이머의 카드 덱을 보고 점수를 계산한다.
    private int getPoints(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    private void showCards() {
        System.out.println("Dealer's cards");
        for (Card card: cards)
            System.out.println(card);
    }

    // 승패를 판단한다.
    public int compare(Player player) {
        showCards();
        player.showCards();

        int playerPoint = getPoints(player.getCards());
        int dealerPoint = getPoints(cards);

        if (playerPoint > 21) playerPoint = 0;
        if (dealerPoint > 21) dealerPoint = 0;
        return Integer.compare(dealerPoint, playerPoint);
    }
}
