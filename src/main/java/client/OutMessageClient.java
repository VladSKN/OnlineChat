package client;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.util.Scanner;

public class OutMessageClient implements Runnable {
    private final Logger clientLog = LogManager.getLogger("clientLog");
    private final PrintWriter printWriter;

    public OutMessageClient(PrintWriter printWriter){
        this.printWriter = printWriter;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String msg;
        while (true) {
            msg = scanner.nextLine();
            if ("/exit".equals(msg)) {
                break;
            }
            printWriter.println(msg);
            printWriter.flush();
            clientLog.log(Level.INFO, msg);
        }
    }
}
