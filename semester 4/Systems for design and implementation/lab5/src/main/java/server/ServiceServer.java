package server;

import domain.validators.*;
import repository.db.*;
import service.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceServer {
    private ExecutorService executorService;
    ServiceMovie serviceMovie;
    ServiceGenre serviceGenre;
    ServiceCinema serviceCinema;
    ServiceCatalog serviceCatalog;
    ServiceTicket serviceTicket;
    private static final String url="jdbc:postgresql://localhost:5432/Movie";
    private static final String user="postgres";
    private static final String password="password";


    public ServiceServer(ExecutorService executorService) {
        this.executorService = executorService;
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

    public Future<String> processMessage(String mes) {
        return executorService.submit(() -> {
            return mes;
        });
    }

    /**
     * adds a new movie
     */
    public String addMovie(String params) {
        try {
            String[] parts=params.split(" ");
            Integer id=Integer.parseInt(parts[0]);
            String name=parts[1];
            Float rating=Float.parseFloat(parts[2]);
            serviceMovie.addMovie(id, name,rating);
            return "Movie added";
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    /**
     * delete a movie + cascade delete ticket and delete catalog
     */
    public String deleteMovie(String params) {
        try {
            String[] parts=params.split(" ");
            Integer id=Integer.parseInt(parts[0]);
            StreamSupport.stream(serviceCatalog.getCatalog().spliterator(), false)
                    .filter(entry->entry.getIdMovie()==id)
                    .forEach(entry->serviceCatalog.deleteFromCatalog(entry.getId()));
            StreamSupport.stream(serviceTicket.getAllTickets().spliterator(), false)
                    .filter(entry->entry.getIdMovie()==id)
                    .forEach(entry->serviceTicket.deleteTicket(entry.getId()));
            serviceMovie.deleteMovie(id);
            return "Movie deleted";
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    /**
     * update movie
     */
    public String updateMovie(String params) {
        try {
            String[] parts=params.split(" ");
            Integer id=Integer.parseInt(parts[0]);
            String name=parts[1];
            Float rating=Float.parseFloat(parts[2]);
            serviceMovie.updateMovie(id, name,rating);
            return "Movie updated";
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    /**
     * lists all movies
     */
    public String listMovies() {
        String movies=StreamSupport.stream(serviceMovie.getAllMovies().spliterator(), false)
                .map(movie -> "Id movie: "+movie.getId()+", movie name: "+movie.getName()+", rating: "+movie.getRating())
                .collect(Collectors.joining("; "));
        return movies;
    }

    /**
     * filter movies by given genre
     */
    public String filterMoviesByGenre(String params) {
        String[] parts=params.split(" ");
        Integer id=Integer.parseInt(parts[0]);
        String filtered=serviceMovie.filterMoviesByGenre(id).stream()
                .map(movie -> "Id movie: "+movie.getId()+", movie name: "+movie.getName()+", rating: "+movie.getRating())
                .collect(Collectors.joining("; "));
        return filtered;
    }

    /**
     * filter genres by given movie
     */
    public String filterGenresByMovie(String params) {
        String[] parts=params.split(" ");
        Integer id=Integer.parseInt(parts[0]);
        String filtered=serviceMovie.filterGenresByMovie(id).stream()
                .map(genre->"Id genre: "+genre.getId()+", genre name: "+genre.getName())
                .collect(Collectors.joining("; "));
        return filtered;
    }

    /**
     * filter movies by given cinema
     */
    public String filterMoviesByCinema(String params){
        String[] parts=params.split(" ");
        Integer id=Integer.parseInt(parts[0]);
        String filtered=serviceMovie.filterMovieByCinema(id).stream()
                .map(movie -> "Id movie: "+movie.getId()+", movie name: "+movie.getName()+", rating: "+movie.getRating())
                .collect(Collectors.joining("; "));
        return filtered;
    }

    /**
     * filter cinemas by given movie
     */
    public String filterCinemasByMovie(String params){
        String[] parts=params.split(" ");
        Integer id=Integer.parseInt(parts[0]);
        String filtered=serviceMovie.filterCinemaByMovie(id).stream()
                .map(cinema->"Id cinema: "+cinema.getId()+", cinema name: "+cinema.getName()+", cinema seats: "+cinema.getNrOfSeats())
                .collect(Collectors.joining("; "));
        return filtered;
    }

    /**
     * adds a new genre
     */
    public String addGenre(String params) {
        try {
            String[] parts=params.split(" ");
            Integer id=Integer.parseInt(parts[0]);
            String name=parts[1];
            serviceGenre.addGenre(id, name);
            return "Genre added";
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    /**
     * delete a genre + cascade delete catalog
     */
    public String deleteGenre(String params) {
        try {
            String[] parts=params.split(" ");
            Integer id=Integer.parseInt(parts[0]);
            StreamSupport.stream(serviceCatalog.getCatalog().spliterator(), false)
                    .filter(entry->entry.getIdGenre()==id)
                    .forEach(entry->serviceCatalog.deleteFromCatalog(entry.getId()));
            serviceGenre.deleteGenre(id);
            return "Genre deleted";
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    /**
     * update genre
     */
    public String updateGenre(String params) {
        try {
            String[] parts=params.split(" ");
            Integer id=Integer.parseInt(parts[0]);
            String name=parts[1];
            serviceGenre.updateGenre(id,name);
            return "Genre updated";
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    /**
     * lists all genres
     */
    public String listGenres() {
        String genres=StreamSupport.stream(serviceGenre.getGenres().spliterator(), false)
                .map(genre -> "Id genre: "+genre.getId()+", genre name: "+genre.getName())
                .collect(Collectors.joining("; "));
        return genres;
    }

    /**
     * adds a new cinema
     */
    public String addCinema(String params) {
        try {
            String[] parts=params.split(" ");
            Integer id=Integer.parseInt(parts[0]);
            String name=parts[1];
            Integer nrOfSeats=Integer.parseInt(parts[2]);
            serviceCinema.addCinema(id, name, nrOfSeats);
            return "Cinema added";
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    /**
     * delete a cinema + cascade delete ticket
     */
    public String deleteCinema(String params) {
        try {
            String[] parts=params.split(" ");
            Integer id=Integer.parseInt(parts[0]);
            StreamSupport.stream(serviceTicket.getAllTickets().spliterator(), false)
                    .filter(entry->entry.getIdCinema()==id)
                    .forEach(entry->serviceTicket.deleteTicket(entry.getId()));
            serviceCinema.deleteCinema(id);
            return "Cinema deleted";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    /**
     * update cinema
     */
    public String updateCinema(String params) {
        try {
            String[] parts=params.split(" ");
            Integer id=Integer.parseInt(parts[0]);
            String name=parts[1];
            Integer nrOfSeats=Integer.parseInt(parts[2]);
            serviceCinema.updateCinema(id, name, nrOfSeats);
            return "Cinema updated";
        } catch (ValidatorException | IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    /**
     * lists all cinemas
     */
    public String listCinemas() {
        String cinemas=StreamSupport.stream(serviceCinema.getAllCinemas().spliterator(), false)
                .map(cinema -> "Id cinema: "+cinema.getId()+", cinema name: "+cinema.getName()+", cinema seats: "+cinema.getNrOfSeats())
                .collect(Collectors.joining("; "));
        return cinemas;
    }

    /**
     * adds a new genre-movie entry
     */
    public String addToCatalog(String params) {
        try {
            String[] parts=params.split(" ");
            Integer id=Integer.parseInt(parts[0]);
            Integer idMovie=Integer.parseInt(parts[1]);
            Integer idGenre=Integer.parseInt(parts[2]);
            serviceCatalog.addToCatalog(id, idMovie, idGenre);
            return "Added to catalog";
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    /**
     * delete from catalog
     */
    public String deleteFromCatalog(String params) {
        try {
            String[] parts=params.split(" ");
            Integer id=Integer.parseInt(parts[0]);
            serviceCatalog.deleteFromCatalog(id);
            return "Deleted from catalog";
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    /**
     * update catalog
     */
    public String updateCatalog(String params) {
        try {
            String[] parts=params.split(" ");
            Integer id=Integer.parseInt(parts[0]);
            Integer idMovie=Integer.parseInt(parts[1]);
            Integer idGenre=Integer.parseInt(parts[2]);
            serviceCatalog.updateCatalog(id, idMovie, idGenre);
            return "Catalog updated";
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    /**
     * lists all genre-movie entries
     */
    public String listCatalog() {
        String catalog=StreamSupport.stream(serviceCatalog.getCatalog().spliterator(), false)
                .map(cat -> "Id: "+cat.getId()+", id movie: "+cat.getIdMovie()+", id genre: "+cat.getIdGenre())
                .collect(Collectors.joining("; "));
        return catalog;
    }

    /**
     * adds a new ticket
     */
    public String addTicket(String params){
        try {
            String[] parts=params.split(" ");
            Integer id=Integer.parseInt(parts[0]);
            Integer movie=Integer.parseInt(parts[1]);
            Integer cinema=Integer.parseInt(parts[2]);
            Float price=Float.parseFloat(parts[3]);
            serviceTicket.addTicket(id,movie,cinema,price);
            return "Ticket added";
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    /**
     * deletes a ticket
     */
    public String deleteTicket(String params){
        try {
            String[] parts=params.split(" ");
            Integer id=Integer.parseInt(parts[0]);
            serviceTicket.deleteTicket(id);
            return "Ticket deleted";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

    }

    /**
     * updates a ticket
     */
    public String updateTicket(String params){
        try {
            String[] parts=params.split(" ");
            Integer id=Integer.parseInt(parts[0]);
            Integer movie=Integer.parseInt(parts[1]);
            Integer cinema=Integer.parseInt(parts[2]);
            Float price=Float.parseFloat(parts[3]);
            serviceTicket.updateTicket(id, movie, cinema,price);
            return "Ticket updated";
        } catch (ValidatorException e) {
            return e.getMessage();
        }

    }

    /**
     * lists tickets
     */
    public String listTicket(){
        String tickets=StreamSupport.stream(serviceTicket.getAllTickets().spliterator(), false)
                .map(ticket -> "Id ticket: "+ticket.getId()+", id movie: "+ticket.getIdMovie()+", id cinema: "+ticket.getIdCinema()+", ticket price: "+ticket.getPrice())
                .collect(Collectors.joining("; "));
        return tickets;
    }

    /**
     * report how many seats are available for a movie
     */
    public String availableSeats(String params) {
        String[] parts=params.split(" ");
        Integer id=Integer.parseInt(parts[0]);
        Integer seats=serviceMovie.filterCinemaByMovie(id).stream()
                .map(entry->entry.getNrOfSeats())
                .collect(Collectors.summingInt(Integer::intValue));
        return "Seats available for the movie: "+seats;
    }

    /**
     * report how many movies can be seen in a cinema
     */
    public String availableMovies(String params) {
        String[] parts=params.split(" ");
        Integer id=Integer.parseInt(parts[0]);
        long movies=serviceMovie.filterMovieByCinema(id).stream()
                .count();
        return "Available movies in cinema: "+movies;
    }

    /**
     * average rating for movies in a specific genre
     */
    public String averageRating(String params) {
        String[] parts=params.split(" ");
        Integer id=Integer.parseInt(parts[0]);
        Float rating=serviceMovie.filterMoviesByGenre(id).stream()
                .map(entry->entry.getRating())
                .reduce(0.0f, Float::sum);
        long movies=serviceMovie.filterMoviesByGenre(id).stream()
                .count();
        return "Average rating for movies of genre: "+rating/movies;
    }
}
