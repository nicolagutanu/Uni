package ro.ubb.catalog.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.catalog.core.model.Catalog;
import ro.ubb.catalog.core.model.Genre;
import ro.ubb.catalog.core.model.Ticket;
import ro.ubb.catalog.web.dto.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.StreamSupport;

@Component
public class ClientConsole {

    @Autowired
    private RestTemplate restTemplate;
    private String url = "http://localhost:8080/Gradle___ro_ubb_catalog_rest_template___catalog_web_1_0_SNAPSHOT_war/api";
    String menu = "";
    HashMap<String, Runnable> commands = new HashMap<>();

    public void makeMenu() {
        menu = "add movie\n";
        menu += "delete movie\n";
        menu += "update movie\n";
        menu += "list movies\n";
        menu += "filter movies by rating\n";
        menu += "add cinema\n";
        menu += "delete cinema\n";
        menu += "update cinema\n";
        menu += "list cinemas\n";
        menu += "filter cinemas by nr of seats\n";
        menu += "add genre\n";
        menu += "delete genre\n";
        menu += "update genre\n";
        menu += "list genres\n";
        menu += "add catalog\n";
        menu += "delete catalog\n";
        menu += "update catalog\n";
        menu += "list catalog\n";
        menu += "add ticket\n";
        menu += "delete ticket\n";
        menu += "update ticket\n";
        menu += "list tickets\n";
    }

    public void makeCommands() {
        commands.put("add movie", this::addMovie);
        commands.put("delete movie", this::deleteMovie);
        commands.put("update movie", this::updateMovie);
        commands.put("list movies", this::listMovies);
        commands.put("filter movies by rating", this::filterMovies);
        commands.put("add cinema", this::addCinema);
        commands.put("delete cinema", this::deleteCinema);
        commands.put("update cinema", this::updateCinema);
        commands.put("list cinemas", this::listCinemas);
        commands.put("filter cinemas by nr of seats", this::filterCinemas);
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

    private void addMovie() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Name: ");
            Scanner keyboard2 = new Scanner(System.in);
            String name = keyboard2.nextLine();
            System.out.println("Rating: ");
            Float rating = keyboard.nextFloat();

            restTemplate.postForObject(url + "/add-movie", new MovieDto(name, rating), MovieDto.class);
            System.out.println("movie added\n");
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

            MovieDto movie=new MovieDto(name, rating);
            movie.setId(id);

            restTemplate.put(url + "/update-movie/{id}", movie, movie.getId());
            System.out.println("movie updated\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteMovie() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();

            restTemplate.delete(url + "/delete-movie/{id}", id);
            System.out.println("movie deleted\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listMovies() {
        MoviesDto movies = restTemplate.getForObject(url+"/get-all-movies", MoviesDto.class);
        System.out.println(movies);
    }

    public void filterMovies() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Rating: ");
        Float rating = keyboard.nextFloat();

        MoviesDto movies = restTemplate.getForObject(url+"/movies-by-rating/"+rating, MoviesDto.class);
        System.out.println(movies);
    }

    public void addCinema() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Name: ");
            Scanner keyboard2 = new Scanner(System.in);
            String name=keyboard2.nextLine();
            System.out.println("Nr. of seats: ");
            Integer nrOfSeats=keyboard.nextInt();

            restTemplate.postForObject(url + "/add-cinema", new CinemaDto(name, nrOfSeats), CinemaDto.class);
            System.out.println("cinema added\n");
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

            CinemaDto cinema = new CinemaDto(name, nrOfSeats);
            cinema.setId(id);

            restTemplate.put(url + "/update-cinema/{id}", cinema, cinema.getId());
            System.out.println("cinema updated\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCinema() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();

            restTemplate.delete(url + "/delete-cinema/{id}", id);
            System.out.println("cinema deleted\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listCinemas() {
        CinemasDto cinemas = restTemplate.getForObject(url+"/get-all-cinemas", CinemasDto.class);
        System.out.println(cinemas);
    }

    public void filterCinemas() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Nr. of seats: ");
        Integer nrOfSeats=keyboard.nextInt();

        CinemasDto cinemas = restTemplate.getForObject(url+"/cinemas-by-nrofseats/"+nrOfSeats, CinemasDto.class);
        System.out.println(cinemas);
    }

    public void addGenre() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Name: ");
            Scanner keyboard2 = new Scanner(System.in);
            String name=keyboard2.nextLine();

            restTemplate.postForObject(url + "/add-genre", new GenreDto(name), GenreDto.class);
            System.out.println("genre added\n");
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

            GenreDto genre=new GenreDto(name);
            genre.setId(id);

            restTemplate.put(url + "/update-genre/{id}", genre, genre.getId());
            System.out.println("genre updated\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteGenre() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            int id = keyboard.nextInt();

            restTemplate.delete(url + "/delete-genre/{id}", id);
            System.out.println("genre deleted\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listGenres() {
        GenresDto genres = restTemplate.getForObject(url+"/get-all-genres", GenresDto.class);
        System.out.println(genres);
    }

    public void addToCatalog() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Id movie: ");
            Integer idMovie=keyboard.nextInt();
            System.out.println("Id genre: ");
            Integer idGenre=keyboard.nextInt();

            restTemplate.postForObject(url + "/add-catalog", new CatalogDto(idMovie, idGenre), CatalogDto.class);
            System.out.println("catalog added\n");
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

            CatalogDto catalog=new CatalogDto(idMovie, idGenre);
            catalog.setId(id);

            restTemplate.put(url + "/update-catalog/{id}", catalog, catalog.getId());
            System.out.println("catalog updated\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteFromCatalog() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            Integer id=keyboard.nextInt();

            restTemplate.delete(url + "/delete-catalog/{id}", id);
            System.out.println("catalog deleted\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listCatalog() {
        CatalogsDto catalogs = restTemplate.getForObject(url+"/get-all-catalogs", CatalogsDto.class);
        System.out.println(catalogs);
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

            restTemplate.postForObject(url + "/add-ticket", new TicketDto(movie, cinema, price), TicketDto.class);
            System.out.println("ticket added\n");
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

            TicketDto ticket=new TicketDto(idMovie, idCinema, price);
            ticket.setId(id);

            restTemplate.put(url + "/update-ticket/{id}", ticket, ticket.getId());
            System.out.println("ticket updated\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTicket(){
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("ID: ");
            int id=keyboard.nextInt();

            restTemplate.delete(url + "/delete-ticket/{id}", id);
            System.out.println("ticket deleted\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listTicket(){
        TicketsDto tickets = restTemplate.getForObject(url+"/get-all-tickets", TicketsDto.class);
        System.out.println(tickets);
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
    }
}
