package net.rebix.engine.discordchatbot;

import net.rebix.engine.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatBot {



    ServerSocket serverSocket;
    List<Socket> sockets = new ArrayList<>();

    public void Listen() {
        new Thread(() -> {
            while (true)
            try {
               Socket socket = serverSocket.accept();
               sockets.add(socket);
                Main.plugin.getLogger().info("connected to chat-bot");
                receiver(socket);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    public ChatBot() {
        try {
            serverSocket = new ServerSocket(8891);
            Listen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void receiver(Socket socket) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                receiveMessage(line);
            }} catch (IOException e) {}
    }



    public void sendMessage(String message) {
        for (Socket socket : sockets) {
            PrintWriter out;
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                System.out.println("sending message to chat-bot " + message);
                out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        public void receiveMessage (String message){
            new MessageManager().received(message);
        }


}
