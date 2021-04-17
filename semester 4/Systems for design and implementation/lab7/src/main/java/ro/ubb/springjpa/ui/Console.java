package ro.ubb.springjpa.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.springjpa.model.*;
import ro.ubb.springjpa.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.StreamSupport;

/**
 * Created by les aristocats.
 */
@Component
public class Console {
    @Autowired
    private MovieService movieService;
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private TicketService ticketService;
    String menu="";
    HashMap<String, Runnable> commands=new HashMap<>();

    public void makeCommands() {
        commands.put("add movie", this::addMovie);
        commands.put("delete movie", this::deleteMovie);
        commands.put("update movie", this::updateMovie);
        commands.put("list movies", this::listMovies);
        commands.put("add cinema", this::addCinema);
        commands.put("delete cinema", this::deleteCinema);
        commands.put("update cinema", this::updateCinema);
        commands.put("list cinemas", this::listCinemas);
        commands.put("add genre", this::addGenre);
        commands.put("delete genre", this::deleteGenre);
        commands.put("update genre", this::updateGenre);
        commands.put("list genres", this::listGenres);
        commands.put("add catalog", this::addToCatalog);
        commands.put("delete catalog", this::deleteFromCatalog);
        commands.put("update catalog", this::updateCatalog);
        commands.put("list catalog", this::listCatalog);
        commands.put("add ticket", this::addTicket);
        commands.put("delete ticket", this::deleteTicket);
        commands.put("update ticket", this::updateTicket);
        commands.put("list tickets", this::listTicket);
        makeMenu();
    }

    public void makeMenu() {
        menu="add movie\n";
        menu+="delete movie\n";
        menu+="update movie\n";
        menu+="list movies\n";
        menu+="add cinema\n";
        menu+="delete cinema\n";
        menu+="update cinema\n";
        menu+="list cinemas\n";
        menu+="add genre\n";
        menu+="delete genre\n";
        menu+="update genre\n";
        menu+="list genres\n";
        menu+="add catalog\n";
        menu+="delete catalog\n";
        menu+="update catalog\n";
        menu+="list catalog\n";
        menu+="add ticket\n";
        menu+="delete ticket\n";
        menu+="update ticket\n";
        menu+="list tickets\n";
    }

    public void addMovie() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Name: ");
            Scanner keyboard2 = new Scanner(System.in);
            String name=keyboard2.nextLine();
            System.out.println("Rating: ");
            Float rating=keyboard.nextFloat();
            movieService.saveMovie(new Movie(name, rating));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteMovie() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();
            StreamSupport.stream(catalogService.getAllCatalog().spliterator(), false)
                    .filter(entry->entry.getIdMovie()==id)
                    .forEach(entry->catalogService.deleteCatalogById(entry.getId()));
            StreamSupport.stream(ticketService.getAllTickets().spliterator(), false)
                    .filter(entry->entry.getIdMovie()==id)
                    .forEach(entry->ticketService.deleteTicketById(entry.getId()));
            movieService.deleteMovieById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateMovie() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();
            System.out.println("New name: ");
            Scanner keyboard2 = new Scanner(System.in);
            String name=keyboard2.nextLine();
            System.out.println("New rating: ");
            Float rating=keyboard.nextFloat();
            Movie movie=new Movie(name, rating);
            movie.setId(id);
            movieService.updateMovie(movie);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listMovies() {
        movieService.getAllMovies().forEach(movie -> System.out.println(movie.toString()));
    }

    public void addCinema() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Name: ");
            Scanner keyboard2 = new Scanner(System.in);
            String name=keyboard2.nextLine();
            System.out.println("Nr. of seats: ");
            Integer nrOfSeats=keyboard.nextInt();
            cinemaService.saveCinema(new Cinema(name, nrOfSeats));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteCinema() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();
            StreamSupport.stream(ticketService.getAllTickets().spliterator(), false)
                    .filter(entry->entry.getIdCinema()==id)
                    .forEach(entry->ticketService.deleteTicketById(entry.getId()));
            cinemaService.deleteCinemaById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCinema() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();
            System.out.println("New name: ");
            Scanner keyboard2 = new Scanner(System.in);
            String name=keyboard2.nextLine();
            System.out.println("New nr. of seats: ");
            Integer nrOfSeats=keyboard.nextInt();
            Cinema cinema=new Cinema(name, nrOfSeats);
            cinema.setId(id);
            cinemaService.updateCinema(cinema);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listCinemas() {
        cinemaService.getAllCinemas().forEach(cinema -> System.out.println(cinema.toString()));
    }

    public void addGenre() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Name: ");
            Scanner keyboard2 = new Scanner(System.in);
            String name=keyboard2.nextLine();
            genreService.saveGenre(new Genre(name));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteGenre() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            int id = keyboard.nextInt();
            StreamSupport.stream(catalogService.getAllCatalog().spliterator(), false)
                    .filter(entry->entry.getIdGenre()==id)
                    .forEach(entry->catalogService.deleteCatalogById(entry.getId()));
            genreService.deleteGenreById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateGenre() {
        try {
            Scanner keyboard = new Scanner(System.in);

            System.out.println("ID: ");
            int id=keyboard.nextInt();
            keyboard.nextLine();
            System.out.println("New name: ");
            String name=keyboard.nextLine();
            Genre genre=new Genre(name);
            genre.setId(id);
            genreService.updateGenre(genre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listGenres() { genreService.getAllGenres().forEach(genre -> System.out.println(genre.toString()));}

    public void addToCatalog() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Id movie: ");
            Integer idMovie=keyboard.nextInt();
            System.out.println("Id genre: ");
            Integer idGenre=keyboard.nextInt();
            catalogService.saveCatalog(new Catalog(idMovie, idGenre));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteFromCatalog() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();
            catalogService.deleteCatalogById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCatalog() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();
            System.out.println("New id movie: ");
            Integer idMovie=keyboard.nextInt();
            System.out.println("New id genre: ");
            Integer idGenre=keyboard.nextInt();
            Catalog catalog=new Catalog(idMovie, idGenre);
            catalog.setId(id);
            catalogService.updateCatalog(catalog);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listCatalog() {
        catalogService.getAllCatalog().forEach(catalog -> System.out.println(catalog.toString()));
    }

    public void addTicket(){
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID Movie: ");
            int movie =keyboard.nextInt();
            System.out.println("ID Cinema");
            int cinema = keyboard.nextInt();
            System.out.println("Price: ");
            float price = keyboard.nextFloat();
            ticketService.saveTicket(new Ticket(movie, cinema, price));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTicket(){
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            int id=keyboard.nextInt();
            ticketService.deleteTicketById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTicket(){
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();
            System.out.println("New id movie: ");
            Integer idMovie=keyboard.nextInt();
            System.out.println("New id cinema: ");
            Integer idCinema=keyboard.nextInt();
            System.out.println("New price: ");
            Integer price=keyboard.nextInt();
            Ticket ticket=new Ticket(idMovie, idCinema, price);
            ticket.setId(id);
            ticketService.updateTicket(ticket);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listTicket(){
        ticketService.getAllTickets().forEach(ticket -> System.out.println(ticket.toString()));
    }

    public void runConsole() {
        makeCommands();
        Scanner keyboard = new Scanner(System.in);
        while (true) {
            System.out.println(menu);
            String comm = keyboard.nextLine();
            if (comm.equals("exit"))
                return;
            else if (commands.containsKey(comm))
                commands.get(comm).run();
            else
                System.out.println("Unknown command");
        }
        /*
        //todo: logs

        System.out.println("save...........");
        movieService.saveMovie(new Movie("s1", 1.0f));
        printAllMovies();

        System.out.println("\nupdate................");
        Movie movie = movieService.getAllMovies().get(0);
        movie.setRating(9.0f);
        movieService.updateMovie(movie);
        printAllMovies();


        System.out.println("\ndelete...........");
        movieService.deleteMovieById(movie.getId());
        printAllMovies();
         */

    }
}
