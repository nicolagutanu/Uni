package client;

import common.Message;
import domain.*;
import domain.validators.*;
import repository.*;
import repository.db.*;
import service.*;
import ui.UI;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UIClient {
    ServiceClient serv;
    HashMap<String, Runnable> commands;
    String menu;

    public UIClient(ServiceClient serv) {
        this.serv = serv;

        commands=new HashMap<>();

        menu="\n~~Menu~~\n";
        menu+="add movie\n";
        menu+="delete movie\n";
        menu+="update movie\n";
        menu+="list movies\n";
        menu+="add genre\n";
        menu+="delete genre\n";
        menu+="update genre\n";
        menu+="list genres\n";
        menu+="filter movies by genre\n";
        menu+="filter movies by cinema\n";
        menu+="filter cinemas by movie\n";
        menu+="filter genres by movie\n";
        menu+="add cinema\n";
        menu+="delete cinema\n";
        menu+="update cinema\n";
        menu+="list cinemas\n";
        menu+="add to catalog\n";
        menu+="delete from catalog\n";
        menu+="update catalog\n";
        menu+="list catalog\n";
        menu+="add ticket\n";
        menu+="delete ticket\n";
        menu+="update ticket\n";
        menu+="list tickets\n";
        menu+="available seats for movie\n";
        menu+="available movies in cinema\n";
        menu+="average rating for movie of genre\n";
        menu+="exit\n";
        commands.put("add movie", this::addMovie);
        commands.put("delete movie", this::deleteMovie);
        commands.put("update movie", this::updateMovie);
        commands.put("list movies", this::listMovies);
        commands.put("filter movies by genre", this::filterMoviesByGenre);
        commands.put("filter movies by cinema", this::filterMoviesByCinema);
        commands.put("filter cinemas by movie", this::filterCinemasByMovie);
        commands.put("filter genres by movie", this::filterGenresByMovie);
        commands.put("add genre", this::addGenre);
        commands.put("delete genre", this::deleteGenre);
        commands.put("update genre", this::updateGenre);
        commands.put("list genres", this::listGenres);
        commands.put("add cinema", this::addCinema);
        commands.put("delete cinema", this::deleteCinema);
        commands.put("update cinema", this::updateCinema);
        commands.put("list cinemas", this::listCinemas);
        commands.put("add to catalog", this::addToCatalog);
        commands.put("delete from catalog", this::deleteFromCatalog);
        commands.put("update catalog", this::updateCatalog);
        commands.put("list catalog", this::listCatalog);
        commands.put("add ticket", this::addTicket);
        commands.put("delete ticket", this::deleteTicket);
        commands.put("update ticket", this::updateTicket);
        commands.put("list tickets", this::listTicket);
        commands.put("available seats for movie", this::availableSeats);
        commands.put("available movies in cinema", this::availableMovies);
        commands.put("average rating for movie of genre", this::averageRating);
    }

    /**
     * adds a new movie
     */
    public void addMovie() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();
        System.out.println("Name: ");
        Scanner keyboard2 = new Scanner(System.in);
        String name=keyboard2.nextLine();
        System.out.println("Rating: ");
        Float rating=keyboard.nextFloat();

        String command=id+" "+name+" "+rating;
        Message mes = new Message("addMovie", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete a movie + cascade delete ticket and delete catalog
     */
    public void deleteMovie() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();

        String command=id.toString();
        Message mes = new Message("deleteMovie", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * update movie
     */
    public void updateMovie() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();
        System.out.println("New name: ");
        Scanner keyboard2 = new Scanner(System.in);
        String name=keyboard2.nextLine();
        System.out.println("New rating: ");
        Float rating=keyboard.nextFloat();

        String command=id+" "+name+" "+rating;
        Message mes = new Message("updateMovie", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * lists all movies
     */
    public void listMovies() {
        String command="";
        Message mes = new Message("listMovies", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            String finalResponse = "";
            if (result!=null) {
                String[] parts = result.split(";");
                for (int i = 0; i < parts.length; i++) {
                    finalResponse += parts[i] + "\n";
                }
            }
            System.out.println("received response:\n" + finalResponse);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * filter movies by given genre
     */
    public void filterMoviesByGenre() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();

        String command=id.toString();
        Message mes = new Message("filterMovieByGenre", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            String finalResponse = "";
            if (result!=null) {
                String[] parts = result.split(";");
                for (int i = 0; i < parts.length; i++) {
                    finalResponse += parts[i] + "\n";
                }
            }
            System.out.println("received response:\n" + finalResponse);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * filter genres by given movie
     */
    public void filterGenresByMovie() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();

        String command=id.toString();
        Message mes = new Message("filterGenresByMovie", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            String finalResponse = "";
            if (result!=null) {
                String[] parts = result.split(";");
                for (int i = 0; i < parts.length; i++) {
                    finalResponse += parts[i] + "\n";
                }
            }
            System.out.println("received response:\n" + finalResponse);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * filter movies by given cinema
     */
    public void filterMoviesByCinema(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id = keyboard.nextInt();

        String command=id.toString();
        Message mes = new Message("filterMoviesByCinema", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            String finalResponse = "";
            if (result!=null) {
                String[] parts = result.split(";");
                for (int i = 0; i < parts.length; i++) {
                    finalResponse += parts[i] + "\n";
                }
            }
            System.out.println("received response:\n" + finalResponse);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * filter cinemas by given movie
     */
    public void filterCinemasByMovie(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Movie ID: ");
        Integer id = keyboard.nextInt();

        String command=id.toString();
        Message mes = new Message("filterCinemasByMovie", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            String finalResponse = "";
            if (result!=null) {
                String[] parts = result.split(";");
                for (int i = 0; i < parts.length; i++) {
                    finalResponse += parts[i] + "\n";
                }
            }
            System.out.println("received response:\n" + finalResponse);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * adds a new genre
     */
    public void addGenre() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();
        System.out.println("Name: ");
        Scanner keyboard2 = new Scanner(System.in);
        String name=keyboard2.nextLine();

        String command=id+" "+name;
        Message mes = new Message("addGenre", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete a genre + cascade delete catalog
     */
    public void deleteGenre() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id = keyboard.nextInt();

        String command=id.toString();
        Message mes = new Message("deleteGenre", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * update genre
     */
    public void updateGenre() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();
        System.out.println("Name: ");
        Scanner keyboard2 = new Scanner(System.in);
        String name=keyboard2.nextLine();

        String command=id+" "+name;
        Message mes = new Message("updateGenre", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * lists all genres
     */
    public void listGenres() {
        String command="";
        Message mes = new Message("listGenres", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            String finalResponse = "";
            if (result!=null) {
                String[] parts = result.split(";");
                for (int i = 0; i < parts.length; i++) {
                    finalResponse += parts[i] + "\n";
                }
            }
            System.out.println("received response:\n" + finalResponse);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * adds a new cinema
     */
    public void addCinema() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();
        System.out.println("Name: ");
        Scanner keyboard2 = new Scanner(System.in);
        String name=keyboard2.nextLine();
        System.out.println("Nr. of seats: ");
        Integer nrOfSeats=keyboard.nextInt();

        String command=id+" "+name+" "+nrOfSeats;
        Message mes = new Message("addCinema", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete a cinema + cascade delete ticket
     */
    public void deleteCinema() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();

        String command=id.toString();
        Message mes = new Message("deleteCinema", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * update cinema
     */
    public void updateCinema() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();
        System.out.println("Name: ");
        Scanner keyboard2 = new Scanner(System.in);
        String name=keyboard2.nextLine();
        System.out.println("Nr. of seats: ");
        Integer nrOfSeats=keyboard.nextInt();

        String command=id+" "+name+" "+nrOfSeats;
        Message mes = new Message("updateCinema", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * lists all cinemas
     */
    public void listCinemas() {
        String command="";
        Message mes = new Message("listCinemas", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            String finalResponse = "";
            if (result!=null) {
                String[] parts = result.split(";");
                for (int i = 0; i < parts.length; i++) {
                    finalResponse += parts[i] + "\n";
                }
            }
            System.out.println("received response:\n" + finalResponse);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * adds a new genre-movie entry
     */
    public void addToCatalog() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();
        System.out.println("Id movie: ");
        Integer idMovie=keyboard.nextInt();
        System.out.println("Id genre: ");
        Integer idGenre=keyboard.nextInt();

        String command=id+" "+idMovie+" "+idGenre;
        Message mes = new Message("addToCatalog", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete from catalog
     */
    public void deleteFromCatalog() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();

        String command=id.toString();
        Message mes = new Message("deleteFromCatalog", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * update catalog
     */
    public void updateCatalog() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();
        System.out.println("Id movie: ");
        Integer idMovie=keyboard.nextInt();
        System.out.println("Id genre: ");
        Integer idGenre=keyboard.nextInt();

        String command=id+" "+idMovie+" "+idGenre;
        Message mes = new Message("updateCatalog", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * lists all genre-movie entries
     */
    public void listCatalog() {
        String command="";
        Message mes = new Message("listCatalog", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            String finalResponse = "";
            if (result!=null) {
                String[] parts = result.split(";");
                for (int i = 0; i < parts.length; i++) {
                    finalResponse += parts[i] + "\n";
                }
            }
            System.out.println("received response:\n" + finalResponse);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * adds a new ticket
     */
    public void addTicket(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();
        System.out.println("ID Movie: ");
        int movie =keyboard.nextInt();
        System.out.println("ID Cinema");
        int cinema = keyboard.nextInt();
        System.out.println("Price: ");
        float price = keyboard.nextFloat();

        String command=id+" "+movie+" "+cinema+" "+price;
        Message mes = new Message("addTicket", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * deletes a ticket
     */
    public void deleteTicket(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();

        String command=id.toString();
        Message mes = new Message("deleteTicket", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * updates a ticket
     */
    public void updateTicket(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();
        System.out.println("ID Movie: ");
        int movie =keyboard.nextInt();
        System.out.println("ID Cinema");
        int cinema = keyboard.nextInt();
        System.out.println("Price: ");
        float price = keyboard.nextFloat();

        String command=id+" "+movie+" "+cinema+" "+price;
        Message mes = new Message("updateTicket", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * lists tickets
     */
    public void listTicket(){
        String command="";
        Message mes = new Message("listTicket", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            String finalResponse = "";
            if (result!=null) {
                String[] parts = result.split(";");
                for (int i = 0; i < parts.length; i++) {
                    finalResponse += parts[i] + "\n";
                }
            }
            System.out.println("received response:\n" + finalResponse);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * report how many seats are available for a movie
     */
    public void availableSeats() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();

        String command=id.toString();
        Message mes = new Message("availableSeats", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * report how many movies can be seen in a cinema
     */
    public void availableMovies() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();

        String command=id.toString();
        Message mes = new Message("availableMovies", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * average rating for movies in a specific genre
     */
    public void averageRating() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();

        String command=id.toString();
        Message mes = new Message("averageRating", command);
        Future<String> resultFuture = serv.sendMessage(mes);
        try {
            String result = resultFuture.get();
            System.out.println("received response:\n" + result);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void runConsole() throws ExecutionException, InterruptedException {
        System.out.println("Client works, what's your request? ");
        System.out.println(menu);
        while (true) {
            Scanner keyboard = new Scanner (System.in);
            System.out.println(menu);
            String command = keyboard.nextLine();
            if (command.equals("exit"))
                return;
            else if (commands.containsKey(command))
                commands.get(command).run();
            else
                System.out.println("Unknown command");
        }
    }
}
