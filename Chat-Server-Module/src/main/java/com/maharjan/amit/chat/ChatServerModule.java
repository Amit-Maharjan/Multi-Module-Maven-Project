/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.maharjan.amit.chat;

import com.maharjan.amit.chat.util.ClientHandler;
import com.maharjan.amit.chat.util.ClientListener;
import com.maharjan.amit.model.dao.UserDAO;
import com.maharjan.amit.model.dao.impl.UserDAOImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Student99
 */
public class ChatServerModule {

    public static void main(String[] args) {
        int port = 9090;
        try{
            ServerSocket server = new ServerSocket(port);
            UserDAO userDAO = new UserDAOImpl();
            ClientHandler handler = new ClientHandler();

            while(true) {
                Socket socket = server.accept();
                System.out.println("Connection Request From :::: " + socket.getInetAddress().getHostAddress());

                ClientListener listener = new ClientListener(socket);
                listener.setUserDAO(userDAO);
                listener.setClientHandler(handler);
                listener.start();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println(ioe.getMessage());
        }

    }
}
