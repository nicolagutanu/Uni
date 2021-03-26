package client;

import ui.UI;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        TCPClient tcpClient = new TCPClient(executorService);
        ServiceClient service = new ServiceClient(executorService,tcpClient);
        UIClient clientConsole = new UIClient(service);
        clientConsole.runConsole();

        System.out.println("bye client");

        executorService.shutdown();
    }
}
