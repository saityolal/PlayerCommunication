package com.playerCommunication.MultiJVM;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.playerCommunication.Player;



/**
 * Represents the server-side player in a multi-JVM communication system.
 * This class acts as a server that listens for client connections and
 * facilitates communication with the client-side player.
 *
 * Responsibilities:
 * - Start a server socket to accept client connections.
 * - Initialize the server-side player and manage communication.
 * - Ensure the communication stops gracefully after the required number of messages.
 */
public class PlayerServer {
    /**
     * Starts the server-side player.
     * - Opens a server socket and waits for client connections.
     * - Initializes communication between server and client.
     */
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("Server is started. Waiting for the client...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection established!");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter your name, once both sides enter their names, the conversation will begin:");
            String name = scan.nextLine();

            out.println(name);
            String clientPlayerName = in.readLine();

            Player player = new Player(name, clientPlayerName, in, out, consoleInput, false);
            player.sendandreceiveMessage();


            serverSocket.close();
            in.close();
            out.close();
            consoleInput.close();
            scan.close();
        } catch (IOException e) {
            System.err.println("An error occurred during communication: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

}
