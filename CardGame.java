/*
    * CardGame.java
    * 
    * This program simulates a simple card game (Blackjack) using a linked list to manage the deck of cards.
    * It reads card data from a file, allows the player to play against a dealer, and determines the winner.
    * Edited by: Anthony Madrigal-Murillo
    * Date: 4/29/2025
    * Edit allows for one round of blackjack where the player can hit or stand up to two times.
    * The dealer will draw cards until they bust, get a blackjack, or reach 17 or higher.
*/

//package linkedLists;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CardGame {

    private static LinkList cardList = new LinkList(); // make list

    public static void main(String[] args) {

        // File name to read from
        String fileName = "cards.txt"; // Ensure the file is in the working directory or specify the full path

        // Read the file and create Card objects
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into components
                String[] details = line.split(","); // Assuming comma-separated values
                if (details.length == 4) {
                    // Parse card details
                    String suit = details[0].trim();
                    String name = details[1].trim();
                    int value = Integer.parseInt(details[2].trim());
                    String pic = details[3].trim();

                    // Create a new Card object
                    Card card = new Card(suit, name, value, pic);

                    // Add the Card object to the list
                    cardList.add(card);
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        Card[] playerHand = new Card[5];
        Card[] dealerHand = new Card[5];

        // BLACKJACK TIME!
        int dealerTotal = 0;
        int playerTotal = 0;
        System.out.println("Welcome to Blackjack!");

        System.out.println("player draws:");
        playerHand[0] = cardList.getFirst();
        System.out.println(playerHand[0]);
        playerHand[1] = cardList.getFirst();
        System.out.println(playerHand[1]);
        playerTotal = playerHand[0].getCardValue() + playerHand[1].getCardValue();
        if (playerTotal == 21) {
            System.out.println("Blackjack! Player wins!");
            return;
        }

        System.out.println("dealer draws:");
        dealerHand[0] = cardList.getFirst();
        System.out.println(dealerHand[0]);

        System.out.println("player total: " + playerTotal);

        // Dealer draws second hand but is only shown if they get blackjack
        dealerHand[1] = cardList.getFirst();
        dealerTotal = dealerHand[0].getCardValue() + dealerHand[1].getCardValue();
        if (dealerTotal == 21) {
            System.out.println(dealerHand[1]);
            System.out.println("Blackjack! Dealer wins!");
            return;
        }

        System.out.println("dealer shows: " + dealerHand[0].getCardValue());
        System.out.println("Would the player like to hit or stand (1 for hit, 2 for stand)?");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        if (choice == 1) {
            System.out.println("Player hits.");
            playerHand[2] = cardList.getFirst();
            System.out.println("player draws:");
            System.out.println(playerHand[2]);
            playerTotal = playerHand[0].getCardValue() + playerHand[1].getCardValue() + playerHand[2].getCardValue();
            System.out.println("player total: " + playerTotal);
            if (playerTotal > 21) {
                System.out.println("player busts, dealer wins!");
            } else if (playerTotal == 21) {
                System.out.println("Blackjack! Player wins!");
            } else if (playerTotal < 21) {
                System.out.println("Dealer shows: " + dealerHand[0].getCardValue());
                System.out.println("Would the player like to hit or stand (1 for hit, 2 for stand)?");
                choice = scanner.nextInt();
                if (choice == 1) {
                    System.out.println("Player hits.");
                    playerHand[3] = cardList.getFirst();
                    System.out.println("player draws:");
                    System.out.println(playerHand[3]);
                    playerTotal = playerHand[0].getCardValue() + playerHand[1].getCardValue() + playerHand[2].getCardValue() + playerHand[3].getCardValue();
                    System.out.println("player total: " + playerTotal);
                    if (playerTotal > 21) {
                        System.out.println("player busts, dealer wins!");
                    } else if (playerTotal == 21) {
                        System.out.println("Blackjack! Player wins!");
                    } else if (playerTotal < 21) {
                        System.out.println("The player stands with a total of " + playerTotal + ".");
                        if (dealerTotal > playerTotal) {
                            System.out.println("dealer total: " + dealerTotal + " is higher than player total: " + playerTotal);
                            System.out.println("dealer wins!");
                        } else {
                            dealerHand[2] = cardList.getFirst();
                            System.out.println("dealer draws:");
                            System.out.println(dealerHand[2]);
                            dealerTotal = dealerHand[0].getCardValue() + dealerHand[1].getCardValue() + dealerHand[2].getCardValue();
                            System.out.println("dealer total: " + dealerTotal);
                            if (dealerTotal > 21) {
                                System.out.println("dealer busts, player wins!");
                            } else if (dealerTotal > playerTotal) {
                                System.out.println("dealer total: " + dealerTotal + " is higher than player total: " + playerTotal);
                                System.out.println("dealer wins!");
                            } else if (dealerTotal == playerTotal) {
                                System.out.println("push, no one wins!");
                            } else if (dealerTotal < playerTotal) {
                                System.out.println("dealer total: " + dealerTotal + " is lower than player total: " + playerTotal);
                                System.out.println("player wins!");
                            }
                        }
                    }
                }
            }
        } else if (choice == 2) {
            System.out.println("Player stands.");
            if (dealerTotal > playerTotal) {
                System.out.println("dealer total: " + dealerTotal + " is higher than player total: " + playerTotal);
                System.out.println("dealer wins!");
            } else {
                dealerHand[2] = cardList.getFirst();
                System.out.println("dealer draws:");
                System.out.println(dealerHand[2]);
                dealerTotal = dealerHand[0].getCardValue() + dealerHand[1].getCardValue() + dealerHand[2].getCardValue();
                System.out.println("dealer total: " + dealerTotal);
                if (dealerTotal > 21) {
                    System.out.println("dealer busts, player wins!");
                } else if (dealerTotal > playerTotal) {
                    System.out.println("dealer wins!");
                } else if (dealerTotal == playerTotal) {
                    System.out.println("push, no one wins!");
                }
            }
        } else {
            System.out.println("Invalid choice. Please enter 1 for hit or 2 for stand.");
        }
        System.out.println();
        scanner.close(); // Close the scanner to prevent resource leak
    } // end main
} // end class
