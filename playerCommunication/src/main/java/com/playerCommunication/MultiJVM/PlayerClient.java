package com.playerCommunication.MultiJVM;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.playerCommunication.Player;

/**
 * Represents the client-side player in a multi-JVM communication system.
 * This class connects to the PlayerServer via a socket and handles communication.
 *
 * Responsibilities:
 * - Establish connection to the server.
 * - Initialize communication with the server-side player.
 * - Send and receive messages in coordination with PlayerServer.
 */
public class PlayerClient {
    public static void main(String[] args) {
        /**
         * Main method to start the client-side player.
         * - Establishes a connection to the server.
         * - Creates a Player instance for communication.
         * - Manages the communication lifecycle.
         *
         * @param args Command-line arguments (not used in this implementation)
         */
        Socket socket = null;
        try {
            socket = null;
            socket = new Socket("localhost", 8080);
            System.out.println("Connection established!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter your name, once both sides enter their names, the conversation will begin:");
            String name = scan.nextLine();

            out.println(name);
            String serverPlayerName = in.readLine();

            Player player = new Player(name, serverPlayerName, in, out, consoleInput, true);
            player.sendandreceiveMessage();

            socket.close();
            in.close();
            out.close();
            consoleInput.close();
            scan.close();

        } catch (IOException e) {
            System.err.println("An error occurred during communication: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }


}
