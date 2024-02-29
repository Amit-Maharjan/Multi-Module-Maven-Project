package com.maharjan.amit.chat.util;

import java.util.ArrayList;
import java.util.List;

public class ClientHandler {
    private List<Client> clients = new ArrayList<>();

    public List<Client> getClients(){
        return clients;
    }

    public Client getByUsername(String username){
        for (Client c : clients) {
            if (c.getUser().getUsername().equalsIgnoreCase(username)) {
                return c;
            }
        }
        return null;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public Boolean removeClient(Client client) {
        return clients.remove(client);
    }
}
