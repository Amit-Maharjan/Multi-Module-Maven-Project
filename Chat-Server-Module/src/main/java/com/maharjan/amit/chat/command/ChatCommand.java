package com.maharjan.amit.chat.command;

import com.maharjan.amit.chat.util.Client;
import com.maharjan.amit.chat.util.ClientHandler;

import java.io.IOException;
import java.io.PrintStream;

public abstract class ChatCommand {
    protected Client client;
    protected ClientHandler handler;
    protected PrintStream out;

    public void setClient(Client client) {
        this.client = client;
    }

    public void setClientHandler(ClientHandler handler) {
        this.handler = handler;
    }

    public void setWriter(PrintStream out) {
        this.out = out;
    }

    public abstract void execute(String[] tokens) throws IOException;
}
