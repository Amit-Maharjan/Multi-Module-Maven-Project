package com.maharjan.amit.chat.command;

import com.maharjan.amit.chat.util.Client;

import java.io.IOException;

public class ListCommand extends ChatCommand{
    @Override
    public void execute(String[] tokens) throws IOException {
        StringBuilder content = new StringBuilder();
        content.append("Listing Clients").append("\n===========\n");
        for (Client c : handler.getClients()) {
            if (!c.hasBlocked(client)) {
                content.append(c.getUser().getUsername());
            }
            if (c.equals(client)) {
                content.append(" (me)");
            }
            content.append("\n");
        }
        out.println(content.toString());
    }
}
