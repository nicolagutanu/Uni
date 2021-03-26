package ui;

import domain.*;
import domain.validators.*;
import repository.*;
import repository.db.*;
import service.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UI {
    ServiceMovie serviceMovie;
    ServiceGenre serviceGenre;
    ServiceCinema serviceCinema;
    ServiceCatalog serviceCatalog;
    ServiceTicket serviceTicket;
    private static final String url="jdbc:postgresql://localhost:5432/Movie";
    private static final String user="postgres";
    private static final String password="password";
    HashMap<String, Runnable> commands;
    String menu;

    public UI() {
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
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();
            System.out.println("Name: ");
            Scanner keyboard2 = new Scanner(System.in);
            String name=keyboard2.nextLine();
            System.out.println("Rating: ");
            Float rating=keyboard.nextFloat();
            serviceMovie.addMovie(id, name,rating);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * delete a movie + cascade delete ticket and delete catalog
     */
    public void deleteMovie() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();
            StreamSupport.stream(serviceCatalog.getCatalog().spliterator(), false)
                    .filter(entry->entry.getIdMovie()==id)
                    .forEach(entry->serviceCatalog.deleteFromCatalog(entry.getId()));
            StreamSupport.stream(serviceTicket.getAllTickets().spliterator(), false)
                    .filter(entry->entry.getIdMovie()==id)
                    .forEach(entry->serviceTicket.deleteTicket(entry.getId()));
            serviceMovie.deleteMovie(id);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * update movie
     */
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
            serviceMovie.updateMovie(id, name,rating);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * lists all movies
     */
    public void listMovies() {
        serviceMovie.getAllMovies().forEach(movie -> System.out.println(movie.toString()));
    }

    /**
     * filter movies by given genre
     */
    public void filterMoviesByGenre() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();
        serviceMovie.filterMoviesByGenre(id).forEach(movie -> System.out.println(movie.toString()));
    }

    /**
     * filter genres by given movie
     */
    public void filterGenresByMovie() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();
        serviceMovie.filterGenresByMovie(id).forEach(genre -> System.out.println(genre.toString()));
    }

    /**
     * filter movies by given cinema
     */
    public void filterMoviesByCinema(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Cinema ID: ");
        int id = keyboard.nextInt();
        serviceMovie.filterMovieByCinema(id).forEach(movie -> System.out.println(movie.toString()));
    }

    /**
     * filter cinemas by given movie
     */
    public void filterCinemasByMovie(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Movie ID: ");
        int id = keyboard.nextInt();
        serviceMovie.filterCinemaByMovie(id).forEach(cinema -> System.out.println(cinema.toString()));
    }

    /**
     * adds a new genre
     */
    public void addGenre() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();
            System.out.println("Name: ");
            Scanner keyboard2 = new Scanner(System.in);
            String name=keyboard2.nextLine();
            serviceGenre.addGenre(id, name);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * delete a genre + cascade delete catalog
     */
    public void deleteGenre() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            int id = keyboard.nextInt();
            StreamSupport.stream(serviceCatalog.getCatalog().spliterator(), false)
                    .filter(entry->entry.getIdGenre()==id)
                    .forEach(entry->serviceCatalog.deleteFromCatalog(entry.getId()));
            serviceGenre.deleteGenre(id);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * update genre
     */
    public void updateGenre() {
        try {
            Scanner keyboard = new Scanner(System.in);

            System.out.println("ID: ");
            int id=keyboard.nextInt();
            keyboard.nextLine();
            System.out.println("New name: ");
            String name=keyboard.nextLine();

            serviceGenre.updateGenre(id,name);

        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * lists all genres
     */
    public void listGenres() { serviceGenre.getGenres().forEach(genre -> System.out.println(genre.toString()));}

    /**
     * adds a new cinema
     */
    public void addCinema() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();
            System.out.println("Name: ");
            Scanner keyboard2 = new Scanner(System.in);
            String name=keyboard2.nextLine();
            System.out.println("Nr. of seats: ");
            Integer nrOfSeats=keyboard.nextInt();
            serviceCinema.addCinema(id, name, nrOfSeats);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * delete a cinema + cascade delete ticket
     */
    public void deleteCinema() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();
            StreamSupport.stream(serviceTicket.getAllTickets().spliterator(), false)
                    .filter(entry->entry.getIdCinema()==id)
                    .forEach(entry->serviceTicket.deleteTicket(entry.getId()));
            serviceCinema.deleteCinema(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * update cinema
     */
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
            serviceCinema.updateCinema(id, name, nrOfSeats);
        } catch (ValidatorException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * lists all cinemas
     */
    public void listCinemas() {
        serviceCinema.getAllCinemas().forEach(cinema -> System.out.println(cinema.toString()));
    }

    /**
     * adds a new genre-movie entry
     */
    public void addToCatalog() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();
            System.out.println("Id movie: ");
            Integer idMovie=keyboard.nextInt();
            System.out.println("Id genre: ");
            Integer idGenre=keyboard.nextInt();
            serviceCatalog.addToCatalog(id, idMovie, idGenre);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * delete from catalog
     */
    public void deleteFromCatalog() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();
            serviceCatalog.deleteFromCatalog(id);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * update catalog
     */
    public void updateCatalog() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();
            System.out.println("New id movie: ");
            Integer idMovie=keyboard.nextInt();
            System.out.println("New id genre: ");
            Integer idGenre=keyboard.nextInt();
            serviceCatalog.updateCatalog(id, idMovie, idGenre);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * lists all genre-movie entries
     */
    public void listCatalog() {
        serviceCatalog.getCatalog().forEach(catalog -> System.out.println(catalog.toString()));
    }

    /**
     * adds a new ticket
     */
    public void addTicket(){
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();
            System.out.println("ID Movie: ");
            int movie =keyboard.nextInt();
            System.out.println("ID Cinema");
            int cinema = keyboard.nextInt();
            System.out.println("Price: ");
            float price = keyboard.nextFloat();

            serviceTicket.addTicket(id,movie,cinema,price);

        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * deletes a ticket
     */
    public void deleteTicket(){
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            int id=keyboard.nextInt();
            serviceTicket.deleteTicket(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * updates a ticket
     */
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
            serviceTicket.updateTicket(id, idMovie, idCinema,price);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * lists tickets
     */
    public void listTicket(){
        serviceTicket.getAllTickets().forEach(ticket -> System.out.println(ticket.toString()));

    }

    /**
     * report how many seats are available for a movie
     */
    public void availableSeats() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();
        Integer seats=serviceMovie.filterCinemaByMovie(id).stream()
                .map(entry->entry.getNrOfSeats())
                .collect(Collectors.summingInt(Integer::intValue));
        System.out.println("Seats available for the movie: "+seats);
    }

    /**
     * report how many movies can be seen in a cinema
     */
    public void availableMovies() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();
        long movies=serviceMovie.filterMovieByCinema(id).stream()
                .count();
        System.out.println("Available movies in cinema: "+movies);
    }

    /**
     * average rating for movies in a specific genre
     */
    public void averageRating() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("ID: ");
        Integer id=keyboard.nextInt();
        Float rating=serviceMovie.filterMoviesByGenre(id).stream()
                .map(entry->entry.getRating())
                .reduce(0.0f, Float::sum);
        long movies=serviceMovie.filterMoviesByGenre(id).stream()
                .count();
        System.out.println("Average rating for movies of genre: "+rating/movies);
    }

    public void inMemoryRepo() {
        InMemoryRepositoryMovie<Integer, Movie> repoMovie=new InMemoryRepositoryMovie<>();
        InMemoryRepositoryGenre<Integer, Genre> repoGenre=new InMemoryRepositoryGenre<>();
        InMemoryRepositoryCinema<Integer, Cinema> repoCinema = new InMemoryRepositoryCinema<>();
        InMemoryRepositoryCatalog<Integer, Catalog> repoCatalog = new InMemoryRepositoryCatalog<>();
        InMemoryRepositoryTicket<Integer, Ticket> repoTicket = new InMemoryRepositoryTicket<>();
        serviceMovie=new ServiceMovie(repoMovie, repoCinema, repoGenre, repoCatalog, repoTicket, new MovieValidator());
        serviceCinema=new ServiceCinema(repoCinema, new CinemaValidator());
        serviceGenre=new ServiceGenre(repoGenre, new GenreValidator());
        serviceCatalog=new ServiceCatalog(repoCatalog, new CatalogValidator());
        serviceTicket=new ServiceTicket(repoTicket, new TicketValidator());
    }

    public void dbRepo() {
        DBRepositoryMovie repositoryMovie=new DBRepositoryMovie(url, user, password);
        DBRepositoryCinema repositoryCinema=new DBRepositoryCinema(url, user, password);
        DBRepositoryGenre repositoryGenre=new DBRepositoryGenre(url, user, password);
        DBRepositoryCatalog repositoryCatalog=new DBRepositoryCatalog(url, user, password);
        DBRepositoryTicket repositoryTicket=new DBRepositoryTicket(url, user, password);
        serviceMovie=new ServiceMovie(repositoryMovie, repositoryCinema, repositoryGenre, repositoryCatalog, repositoryTicket, new MovieValidator());
        serviceCinema=new ServiceCinema(repositoryCinema, new CinemaValidator());
        serviceGenre=new ServiceGenre(repositoryGenre, new GenreValidator());
        serviceCatalog=new ServiceCatalog(repositoryCatalog, new CatalogValidator());
        serviceTicket=new ServiceTicket(repositoryTicket, new TicketValidator());
    }

    public void displayMenu() {
        System.out.println(menu);
    }

    /**
     * runs the program
     * @throws ValidatorException
     */
    public void run() throws ValidatorException {
        dbRepo();
        while (true) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println(menu);
            String comm = keyboard.nextLine();
            if (comm.equals("exit"))
                return;
            else if (commands.containsKey(comm))
                commands.get(comm).run();
            else
                System.out.println("Unknown command");
        }
    }
}
