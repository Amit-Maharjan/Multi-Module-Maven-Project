package com.maharjan.amit.chat.command;

import com.maharjan.amit.chat.util.Client;

import java.io.IOException;
import java.io.PrintStream;

public class PMCommand extends ChatCommand{
    @Override
    public void execute(String[] tokens) throws IOException {
        if (tokens.length > 2) {
            Client receiverClient = handler.getByUsername(tokens[1]);
            if (receiverClient != null) {
                PrintStream printStream = new PrintStream(receiverClient.getSocket().getOutputStream());
                printStream.println("[PM from " + client.getUser().getUsername() + "] says>>" + tokens[2]);
            } else {
                out.println(tokens[1] + " username not found");
            }
        } else {
            out.println("Invalid PM Command");
        }
    }
}
