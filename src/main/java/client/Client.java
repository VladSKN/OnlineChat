package client;

import settings.SettingsFileReader;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Logger clientLog = LogManager.getLogger("clientLog");
    private final SettingsFileReader settingsFileReader = new SettingsFileReader();
    private final int PORT = settingsFileReader.getPort();
    private final String HOST = settingsFileReader.getHost();

    public Client() throws IOException {
    }

    public void start() throws IOException {

        Socket socket = new Socket(HOST, PORT);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {
            String msg;
            System.out.println("Добро пожаловать в сетевой чат для обмена сообщениями \n" +
                    "Введите свое Имя, для завершения введите /exit");
            while (true) {
                msg = scanner.nextLine();
                if ("/exit".equals(msg)) {
                    break;
                }
                out.println(msg);
                String input = in.readLine();
                System.out.println(input);
                clientLog.log(Level.INFO, msg);
                clientLog.log(Level.INFO, input);
            }
        }
    }
}

