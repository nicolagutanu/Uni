package client;

import common.IService;
import common.Message;
import common.ServiceException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.concurrent.ExecutorService;

import static common.IService.HOST;
import static common.IService.PORT;


public class TCPClient {
    private ExecutorService executorService;

    public TCPClient(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Message sendAndReceive(Message request) {
        try (var socket = new Socket(HOST, PORT);
             InputStream inStream = socket.getInputStream();
             OutputStream outStream = socket.getOutputStream()) {

            System.out.println("sending request: " + request.getHeader());
            request.writeTo(outStream);
            System.out.println("request sent");

            Message response = new Message();
            response.readFrom(inStream);
            return response;

        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("exception in send and receive", e);
        }

    }
}
