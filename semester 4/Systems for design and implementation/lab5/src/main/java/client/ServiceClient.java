package client;

import common.IService;
import common.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServiceClient implements IService {

    private ExecutorService executorService;
    private TCPClient tcpClient;

    public ServiceClient(ExecutorService executorService, TCPClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    public Future<String> sendMessage(Message mes){
        return executorService.submit(() -> {
            Message response = tcpClient.sendAndReceive(mes);

            String result = response.getBody();

            return result;
        });

    }
}
