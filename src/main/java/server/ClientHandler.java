package server;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


public class ClientHandler implements Runnable {

    private final Logger serverLog = LogManager.getLogger("serverLog");
    private Server server;
    private PrintWriter outMessage;
    private Scanner inMessage;
    private static final AtomicInteger clients_count = new AtomicInteger(0);


    public ClientHandler(Socket socket, Server server) {
        clients_count.getAndIncrement();
        try {
            this.server = server;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            User userClient = new User(inMessage.nextLine());
            serverLog.log(Level.INFO, userClient.getNAME() + " connected to chat.");
            server.sendMessageToAllClients("Новый участник " + userClient.getNAME()
                    + " вошёл в чат! Клиентов в чате = " + clients_count);
            while (true) {
                if (inMessage.hasNext()) {
                    String clientMessage = inMessage.nextLine();
                    if (clientMessage.equals("/exit")) {
                        break;
                    }
                    System.out.println(userClient.getNAME() + ": " + clientMessage);
                    server.sendMessageToAllClients(userClient.getNAME() + ": " + clientMessage);
                    serverLog.log(Level.INFO, userClient.getNAME() + "-" + clientMessage);
                }
            }
        } finally {
            this.close();
        }
    }

    public void sendMsg(String msg) {
        try {
            outMessage.println(msg);
            outMessage.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        server.removeClient(this);
        clients_count.getAndDecrement();
        server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
    }
}