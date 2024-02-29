package com.maharjan.amit.chat.util;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class Broadcaster {
    public static void broadcastMessage(List<Client> clients, Client client, String message) throws IOException {
        for (Client c : clients) {
            if (!c.equals(client)) {
                PrintStream out = new PrintStream(c.getSocket().getOutputStream());
                out.println(message);
            }
        }
    }
}
