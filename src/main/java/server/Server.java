package server;

import settings.SettingsFileReader;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
    private final Logger serverLog = LogManager.getLogger("serverLog");
    private final SettingsFileReader settingsFileReader = new SettingsFileReader();
    private final int PORT = settingsFileReader.getPort();
    private final BlockingQueue<ClientHandler> clients = new LinkedBlockingQueue<>();

    public Server() throws IOException {
    }

    public void start() {
        System.out.println("Start Server!");
        serverLog.log(Level.INFO, "Start Server!");
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        ExecutorService pool = Executors.newCachedThreadPool();
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                clientSocket = serverSocket.accept();
                ClientHandler client = new ClientHandler(clientSocket, this);
                clients.add(client);
                pool.execute(client);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                serverSocket.close();
                pool.shutdown();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    public void sendMessageToAllClients(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }
}
