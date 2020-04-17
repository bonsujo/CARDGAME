package CardGame;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class GoFish {

    static final Random rnd = new Random(); //creates random object
    static private ArrayList<Card> cards; // Stores cards in arraylist
    static public Player[] Players; //Array of player

    //draws/removes card from deck
    public static Card draw() {
        return cards.remove(rnd.nextInt(cards.size()));
    }

    public static int deckSize() {
        return cards.size();
    }

    //Main method that stimulates game
    public static void main(String[] args) {
        boolean flag = true;
        Scanner scan = new Scanner(System.in);

        while (flag) {
            System.out.print("Enter a number from the Main Menu: ");
            System.out.println("\n 1: Play Game \n 2: Rules \n 3: Exit");
            int menuIn = scan.nextInt();

            switch (menuIn) {

                case 1:
                    cards = new ArrayList<>();
                    for (int i = 0; i < 4; i++) {
                        for (Card c : Card.values()) {
                            cards.add(c);
                        }
                    }

                    Player h = new User(); //new player/user object
                    Player ai = new AIPlayer(); // new player/computer object
                    Players = new Player[]{h, ai}; //stores player and computer in array

                    while (Players[0].getNumBooks() + Players[1].getNumBooks() < 13) {
                        Players[0].haveTurn();
                        System.out.println("----------");
                        Players[1].haveTurn();
                        System.out.println("----------");
                    }

                    // end of game message 
                    int yScore = Players[0].getNumBooks();
                    int aiScore = Players[1].getNumBooks();
                    if (yScore > aiScore) {
                        System.out.println("Congratulations, you win " + yScore + " to " + aiScore + "!");
                    } else if (aiScore > yScore) {
                        System.out.println("The terrible AI beat you " + yScore + " to " + aiScore + "...");
                    } else {
                        System.out.println("It's a tie at " + yScore + " each!");
                    }

                case 2:
                    System.out.println("Rules: \n If the opponent has the card, they must give it to the player who earns a point for making a match. "
                            + "\n If the opponent does not have the card they say \"Go Fish!\" The player must then pick a single card from the messed up, face down pile of cards."
                            + "\n If they make a pair, they place it down and earn a point.");

                    break;
                case 3:
                    return;

                default:
                    System.out.println("Not a menu option, try again");

            }
        }

    }
}

//enum of cards
enum Card {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
}

//abstract player class
abstract class Player {

    protected ArrayList<Card> hand = new ArrayList<Card>();
    private int numBooks;

    public Player() {
        for (int i = 0; i < 8; i++) {
            fish();
        }
    }

    //checks if player has given cards
    public boolean hasGiven(Card cType) {
        return hand.contains(cType);
    }

    public ArrayList<Card> giveAll(Card cType) {
        ArrayList<Card> x = new ArrayList<Card>();
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i) == cType) {
                x.add(hand.get(i));
            }
        }
        for (int c = 0; c < x.size(); c++) {
            hand.remove(cType);
        }
        return x;
    }

    //boolean method that ask computer for card type
    protected boolean askFor(Card cardType) {
        int tmp = 0;
        if (this instanceof User) {
            tmp = 1;
        }
        Player other = GoFish.Players[tmp];

        if (tmp == 1) {
            ((AIPlayer) other).que.add(cardType);
        }

        if (other.hasGiven(cardType)) {
            for (Card c : other.giveAll(cardType)) {
                hand.add(c);
            }
            return true;
        } else {
            return false;
        }
    }

    protected void fish() {
        if (GoFish.deckSize() > 0) {
            hand.add(GoFish.draw());
        } else {
            System.out.println("But that's impossible since the deck is empty.");
        }
    }

    public int getNumBooks() {
        return numBooks;
    }

    // check for when player has books (4 of the same card)
    protected Card checkForBooks() {
        for (Card c : hand) {
            int num = 0;
            for (Card d : hand) {
                if (c == d) {
                    num++;
                }
            }
            if (num == 4) {
                for (int i = 0; i < 4; i++) {
                    hand.remove(c);
                }
                numBooks++;
                return c;
            }
        }
        return null;

    }

    public abstract void haveTurn();

}

class User extends Player {

    public void haveTurn() {
        Scanner scn = new Scanner(System.in);
        boolean playing = true;
        do {
            Card book = checkForBooks();
            if (book != null) {
                System.out.println("You got a book of " + book + "s!");
            }

            if (hand.isEmpty()) {
                System.out.print("Your hand is empty, you must "); //"Go fish!"
                break;
            } else {
                System.out.print("Your hand:");
                for (Card c : hand) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }

            System.out.println("Ask opponent for what card?");

            Card req;
            try {
                req = Card.valueOf(scn.next().toUpperCase());
            } catch (IllegalArgumentException e) { //If card is not in deck
                System.out.println("Card not present in this deck. Try again:");
                continue;
            }

            if (!hand.contains(req)) {
                System.out.println("You may not ask for a card you have none of. Try again:");
                continue;
            }

            System.out.println("You ask for a " + req);
            playing = askFor(req); //If you get card(s), askFor returns true and loops
        } while (playing);
        System.out.println("Go fish!");
        fish();
    }
}

class AIPlayer extends Player {

    public ArrayList<Card> que = new ArrayList<Card>();
    private int age = 0;

    public void haveTurn() {
        boolean playing;
        do {
            Card book = checkForBooks();
            if (book != null) {
                System.out.println("Your opponent got a book of " + book + "s...");
            }
            if (hand.size() == 0) {
                System.out.print("Your opponent's hand is empty.");
                break;
            }
            Card req = aiMagic();
            System.out.println("Your opponent asks for cards by the name of " + req);
            playing = askFor(req);
            age++;
        } while (playing);
        System.out.println("Your opponent goes fishing.");
        fish();
    }

    private Card aiMagic() {
        if (age > 2) {
            que.remove(que.size() - 1); //gets rid of old input so it won't 
            age = 0;                           //be asking 
        }
        for (int i = que.size() - 1; i > -1; i--) {
            if (hand.contains(que.get(i))) {
                return que.remove(i);
            }
        }
        return hand.get(GoFish.rnd.nextInt(hand.size()));
    }
}
