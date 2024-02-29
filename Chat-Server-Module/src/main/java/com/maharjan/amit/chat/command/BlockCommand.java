package com.maharjan.amit.chat.command;

import com.maharjan.amit.chat.util.Client;

import java.io.IOException;
import java.io.PrintStream;

public class BlockCommand extends ChatCommand{
    @Override
    public void execute(String[] tokens) throws IOException {
        if (tokens.length > 1) {
            Client receiverClient = handler.getByUsername(tokens[1]);
            if (receiverClient != null) {
                if (!client.equals(receiverClient)) {
                    client.block(receiverClient);
                    out.println(tokens[1] + " has been blocked");
                    String msg = client.getUser().getUsername() + " has logged out !!!";
                    receiverClient.getSocket().getOutputStream().write(msg.getBytes());
                } else {
                    out.println("You can't block yourself !!!");
                }
            } else {
                out.println(tokens[1] + " username not found");
            }
        } else {
            out.println("Not enough parameters");
        }
    }
}
