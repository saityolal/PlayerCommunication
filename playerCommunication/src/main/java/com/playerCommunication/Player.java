
package com.playerCommunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Represents a player in a communication system. Each player can send and receive
 * messages to/from another player. The communication is controlled by a message
 * counter to ensure it terminates after 10 messages.
 * <p>
 * Responsibilities:
 * - Send messages to another player with/without using sockets.
 * - Receive messages from another player with/without using sockets.
 * - Manage message counters and control the termination condition.
 */
public class Player {

    // Fields
    private String name; // Name of the player
    private String receiverName; // Name of the player to communicate with
    private Player otherPlayer; // Reference to the other player for communication
    private int messagesSent; // Counter for sent messages
    private BufferedReader in, consoleInput; // Input streams for receiving messages and console input
    private PrintWriter out; // Output stream for sending messages
    private boolean initiator; // Flag to indicate if this player starts the communication


    private Scanner scanner = new Scanner(System.in);


    /**
     * Constructs a player with the specified name.
     *
     * @param name The name of the player
     */
    public Player(String name) {
        this.name = name;
        this.messagesSent = 0;
    }


    /**
     * Constructs a player for communication with specified parameters.
     *
     * @param name         The name of this player
     * @param receiverName The name of the player to communicate with
     * @param in           Input stream for receiving messages
     * @param out          Output stream for sending messages
     * @param consoleInput Input stream for console-based user input
     * @param initiator    Flag to indicate if this player starts communication
     */
    public Player(String name, String receiverName, BufferedReader in, PrintWriter out, BufferedReader consoleInput, boolean initiator) {
        this.initiator = initiator;
        this.name = name;
        this.receiverName = receiverName;
        this.messagesSent = 0;
        this.consoleInput = consoleInput;
        this.in = in;
        this.out = out;

    }

    /**
     * Sends a message to the other player.
     *
     * @param message Takes redirected message to send new message.
     */
    public void sendMessage(String message) {
        if (messagesSent == 10 && otherPlayer.messagesSent == 10) {
            System.out.println(name + " terminated the communication.");
            return;
        }
        if (messagesSent != 0) {
            System.out.println(name + ", please enter a message for " + otherPlayer.name);
            message = scanner.nextLine();
        }
        messagesSent++;
        otherPlayer.receiveMessage(message);
    }


    /**
     * Receives a message from the other player and redirects it to the sendMessage method.
     *
     * @param message The received message
     */
    public void receiveMessage(String message) {
        System.out.println(name + ", received the message from " + otherPlayer.name + ", message:  " + message + "\n " + name + " Number of messages sent so far " + "-> " + messagesSent);
        if (messagesSent == 0) {
            System.out.println(name + ", please enter message for " + otherPlayer.name);
            message = scanner.nextLine();
            sendMessage(message);
        }
        sendMessage("");
    }

    /**
     * Handles the send-and-receive logic between two players.
     *
     * @throws IOException If an I/O error occurs
     */
    public void sendandreceiveMessage() throws IOException {
        int messagesReceived = 0;
        boolean finished = false;
        while (!finished) {
            String message;
            try {
                if (!initiator) {
                    System.out.println(name + ", please wait until you receive a message...");
                    message = in.readLine();

                    if (message == null) {
                        System.out.println("Connection lost. Exiting communication...");
                        break;
                    }
                    System.out.println(receiverName + " sent you a message: " + message);
                    messagesReceived++;
                    initiator = true;
                } else {
                    clearConsoleInput();
                    System.out.print("Enter Your Message: ");
                    message = consoleInput.readLine();
                    if (message == null || message.equalsIgnoreCase("exit")) {
                        System.out.println("Exiting communication...");
                        break;
                    }
                    out.println(message + " (Message Count: " + (messagesSent + 1) + ")");
                    System.out.println("Message sent! Total messages " + name + " sent: " + (messagesSent + 1));
                    messagesSent++;
                    initiator = false;
                }
                if (messagesSent >= 10 && messagesReceived >= 10) {
                    out.println("DONE");
                    finished = true;
                }
            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
                break;
            }
        }
        System.out.println(name + ": Communication finished!");
    }


    /**
     * Clears any residual input from the console.
     * This ensures no leftover input affects the current execution.
     */
    private static void clearConsoleInput() throws IOException {
        while (System.in.available() > 0) {
            System.in.read();
        }
    }

    public int getMessagesSent() {
        return messagesSent;
    }


    public void setOtherPlayer(Player otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

   public String getName() {
        return name;
    }
}


