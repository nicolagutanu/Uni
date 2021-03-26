package server;

import common.IService;
import common.Message;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServerApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        TCPServer tcpServer = new TCPServer(executorService, IService.PORT);
        ServiceServer serv = new ServiceServer(executorService);

        tcpServer.addHandler("addMovie", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.addMovie(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("deleteMovie", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.deleteMovie(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("updateMovie", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.updateMovie(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("listMovies", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.listMovies();
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("filterMoviesByGenre", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.filterMoviesByGenre(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("filterGenresByMovie", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.filterGenresByMovie(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("filterMoviesByCinema", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.filterMoviesByCinema(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("filterCinemasByMovie", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.filterCinemasByMovie(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("addGenre", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.addGenre(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("deleteGenre", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.deleteGenre(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("updateGenre", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.updateGenre(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("listGenres", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.listGenres();
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("addCinema", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.addCinema(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("deleteCinema", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.deleteCinema(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("updateCinema", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.updateCinema(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("listCinemas", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.listCinemas();
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("addToCatalog", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.addToCatalog(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("deleteFromCatalog", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.deleteFromCatalog(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("updateCatalog", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.updateCatalog(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("listCatalog", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.listCatalog();
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("addTicket", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.addTicket(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("deleteTicket", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.deleteTicket(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("updateTicket", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.updateTicket(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("listTicket", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.listTicket();
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("availableSeats", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.availableSeats(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("availableMovies", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.availableMovies(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler("averageRating", request -> {
            Future<String> res = serv.processMessage(request.getBody());
            try {
                String result = res.get();
                String e=serv.averageRating(result);
                Message response = new Message(Message.OK, e);
                return response;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.startServer();
        System.out.println("bye server");
    }
}
