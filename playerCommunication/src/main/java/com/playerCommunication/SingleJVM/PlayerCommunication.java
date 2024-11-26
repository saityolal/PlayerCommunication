package com.playerCommunication.SingleJVM;


import com.playerCommunication.Player;

import java.util.Scanner;

/**
 * This is the main class for a communication application between two players.
 * The application is made for a single JVM, it runs on a single machine.
 * This class is responsible for setting up the communication between two players.
 * It first creates two Player objects, sets them to talk to each other, and then
 * it starts the communication by asking the first player to enter a message.
 * The application then waits until the communication is finished.
 */
public class PlayerCommunication {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Player1 please enter your name");
        Player player1 = new Player(scan.nextLine());

        System.out.println("Player2 please enter your name");
        Player player2 = new Player(scan.nextLine());


        player1.setOtherPlayer(player2);
        player2.setOtherPlayer(player1);

        String response;
        while (true) {
            System.out.println(player1.getName() + ", Do you want to start conversation? (type \"yes\" or \"no\")");
            response = scan.nextLine();

            if (response.equalsIgnoreCase("yes")) {
                System.out.println("To start the conversation, " + player1.getName() + " please enter a message:");
                player1.sendMessage(scan.nextLine());
                break;
            } else if (response.equalsIgnoreCase("no")) {
                System.out.println("To start the conversation, " + player2.getName() + " please enter a message:");
                player2.sendMessage(scan.nextLine());
                break;
            } else {
                System.out.println("Invalid response. Please enter 'yes' or 'no'.");
            }
        }


        System.out.println("Conversation ended. Total messages sent by " + player1.getName() + ": " + player1.getMessagesSent());
        System.out.println("Conversation ended. Total messages sent by " + player2.getName() + ": " + player2.getMessagesSent());


        scan.close();
    }
}
