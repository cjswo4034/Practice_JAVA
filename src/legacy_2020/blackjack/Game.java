package blackjack;

import java.util.Scanner;

public class Game {
    private static final int INIT_RECEIVE_CARD_COUNT = 2;
    private static final String SIGNAL_EXIT = "0";

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    public void play() {
        Dealer dealer = new Dealer();
        Player player = new Player();
        CardDeck deck = new CardDeck();

        playingPhase(dealer, player, deck);
    }

    private void initPhase(Dealer dealer, Player player, CardDeck cardDeck) {
        // 0. player와 dealer를 초기화한다.
        dealer.init();
        player.init();

        // 1. 게임 시작 전 덱을 정리한다.
        cardDeck.prepForNewGame();

        // 2. 딜러가 덱을 섞는다.
        dealer.shuffle(cardDeck);

        // 3. 딜러가 자신과 플레이어에게 카드를 나눠준다.
        for (int i = 0; i < INIT_RECEIVE_CARD_COUNT; i++) {
            dealer.handOutCardOn(cardDeck, player);
            dealer.takeCard(cardDeck);
        }

        // 4. 플레이어에게 카드를 보여준다.
        player.showCards();
    }

    private void playingPhase(Dealer dealer, Player player, CardDeck cardDeck) {
        Scanner sc = new Scanner(System.in);
        String gamerInput;
        boolean isPlayerTurn = true, isDealerTurn;

        while (true) {
            System.out.println("게임을 진행하시겠습니까? 종료를 원하시면 0을 입력하세요");
            gamerInput = sc.nextLine();
            if (gamerInput.equals(SIGNAL_EXIT)) break;

            // 새 게임 시작
            initPhase(dealer, player, cardDeck);
            while (true) {
                if (isPlayerTurn) {
                    System.out.println("카드를 뽑으려면 1을 입력하세요");
                    gamerInput = sc.nextLine();
                    isPlayerTurn = gamerInput.equals("1");
                }

                isDealerTurn = dealer.isAbleToTakeAnotherCard(cardDeck);

                // 플레이어가 카드를 뽑길 원하면 딜러가 카드덱에서 플레이어에게 카드를 나눠준다.
                if (isPlayerTurn) dealer.handOutCardOn(cardDeck, player);
                // 플레이어와 딜러 둘 다 카드를 안뽑으면 종료한다.
                else if (!isDealerTurn) break;
            }

            int gameResult = dealer.compare(player);
            if (gameResult == -1) System.out.println("You win");
            else if (gameResult == 0) System.out.println("End in a draw");
            else System.out.println("You lose");
        }

    }
}
