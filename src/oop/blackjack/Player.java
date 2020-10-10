package oop.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> cards;

    public Player() {
        this.cards = new ArrayList<>();
    }

    public void init() {
        cards.clear();
    }

    // 카드를 받는다.
    public void receivedCard(Card card) {
        cards.add(card);
        if (cards.size() > 2) showCards();
    }

    // 카드를 한 장 요청한다.
    public void requestCard(Dealer dealer, CardDeck cardDeck) {
        dealer.handOutCardOn(cardDeck, this);
    }

    // 카드 첫 장을 오픈한다.
    public Card showCard() {
        return cards.get(0);
    }

    public void showCards() {
        StringBuilder sb = new StringBuilder();
        sb.append("카드 목록\n");
        for (Card card: cards) {
            sb.append(card).append("\n");
        }
        System.out.println(sb);
    }

    public List<Card> getCards() {
        return cards;
    }
}
