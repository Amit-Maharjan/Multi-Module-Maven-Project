package com.maharjan.amit.chat.command;

import com.maharjan.amit.chat.util.Broadcaster;

import java.io.IOException;

public class ExitCommand extends ChatCommand{
    @Override
    public void execute(String[] tokens) throws IOException {
        client.getSocket().close();
        handler.removeClient(client);
        Broadcaster.broadcastMessage(handler.getClients(), client, client.getUser().getUsername() + " has logged out !!!");
    }
}
