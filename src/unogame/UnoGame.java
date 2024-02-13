/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package unogame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Jeyur Patel
 */


public class UnoGame {

    public static void main(String[] args) {
        UnoGame game = new UnoGame();
        game.play();
    }

    private List<UnoCard> deck;
    private List<UnoCard> discardPile;
    private List<UnoCard> playerHand;
    private Scanner scanner;

    public UnoGame() {
        deck = new ArrayList<>();
        discardPile = new ArrayList<>();
        playerHand = new ArrayList<>();
        scanner = new Scanner(System.in);
        initializeDeck();
    }

    private void initializeDeck() {
        // Create and add Uno cards to the deck
        for (UnoColor color : UnoColor.values()) {
            for (UnoValue value : UnoValue.values()) {
                deck.add(new UnoCard(color, value));
            }
        }
        // Shuffle the deck
        Collections.shuffle(deck);
    }

    private void dealCards(int numCards) {
        // Deal specified number of cards to the player
        for (int i = 0; i < numCards; i++) {
            playerHand.add(deck.remove(0));
        }
    }

private void play() {
        dealCards(7); // Deal initial 7 cards to the player
        discardPile.add(deck.remove(0)); // Draw a card from the deck to start the discard pile

        // Game loop
        while (true) {
            UnoCard topCard = discardPile.get(discardPile.size() - 1); // Get the top card of the discard pile
            System.out.println("Top card: " + topCard);
            System.out.println("Your hand: " + playerHand);

            // Inside the while loop of the play() method, before asking for input
        System.out.println("Your hand:");
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.println(i + ": " + playerHand.get(i));
        }
        System.out.println("Enter the index of the card you want to play, or 'draw' to draw a card:");

            
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("draw")) {
                playerHand.add(deck.remove(0));
                System.out.println("You drew a card.");
            } else {
                try {
                    int index = Integer.parseInt(input);
                    UnoCard selectedCard = playerHand.get(index);
                    if (selectedCard.matches(topCard)) {
                        playerHand.remove(index);
                        discardPile.add(selectedCard);
                        System.out.println("You played: " + selectedCard);
                        if (playerHand.isEmpty()) {
                            System.out.println("Congratulations, you won!");
                            break;
                        }
                        // Add a random card of the same color from the deck to the discard pile
                        List<UnoCard> sameColorCards = new ArrayList<>();
                        for (UnoCard card : deck) {
                            if (card.getColor() == selectedCard.getColor()) {
                                sameColorCards.add(card);
                            }
                        }
                        UnoCard randomCard = sameColorCards.get((int) (Math.random() * sameColorCards.size()));
                        deck.remove(randomCard);
                        discardPile.add(randomCard);
                        System.out.println("Random card of the same color added: " + randomCard);
                    } else {
                        System.out.println("You can't play that card.");
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("Invalid input, please try again.");
                }
            }
        }

        scanner.close();
    }
}