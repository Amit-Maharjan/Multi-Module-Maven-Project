package com.maharjan.amit.chat.util;

import com.maharjan.amit.chat.command.ChatCommand;
import com.maharjan.amit.chat.command.ChatCommandFactory;
import com.maharjan.amit.model.User;
import com.maharjan.amit.model.dao.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientListener extends Thread {
    private BufferedReader reader;
    private PrintStream out;
    private Socket socket;
    private UserDAO userDAO;
    private ClientHandler handler;
    private Client client;

    public ClientListener(Socket socket) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream());
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setClientHandler(ClientHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        try{
            while(!login()) {
                out.println("Invalid username and password.");
                out.println("Please try again.");
            }

            while(!isInterrupted()){
                out.println("(me)>>");
                String line = reader.readLine();
                String[] tokens = line.split(";;");
                ChatCommand cmd = ChatCommandFactory.get(tokens[0]);
                if (cmd != null) {
                    cmd.setClient(client);
                    cmd.setClientHandler(handler);
                    cmd.setWriter(out);
                    cmd.execute(tokens);
                } else {
                    String msg = client.getUser().getUsername() + " says>>" + line;
                    Broadcaster.broadcastMessage(handler.getClients(), client, msg);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    private Boolean login() throws IOException {
        out.println("Enter Username:");
        String username = reader.readLine();
        out.println("Enter Password:");
        String password = Md5.getMd5(reader.readLine());
        try{
            User user = userDAO.login(username, password);
            if (user != null) {
                client = new Client(user, socket);
                handler.addClient(client);
                Broadcaster.broadcastMessage(handler.getClients(), client, username + " has joined !!!");
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            return false;
        }
        return false;
    }
}
