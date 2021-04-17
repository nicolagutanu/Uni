package ro.ubb.springjpa.model;

import javax.persistence.Entity;

@Entity
public class Ticket extends BaseEntity<Integer> {
    private Integer idMovie;
    private Integer idCinema;
    private float price;

    public Ticket(){}

    /**
     *
     * @param idMovie represents the id of the movie to be seen
     * @param idCinema represents the id of the cinema in which the movie is played
     * @param price represents the price of the ticket
     */
    public Ticket(Integer idMovie, Integer idCinema, float price){
        this.idCinema = idCinema;
        this.idMovie = idMovie;
        this.price = price;

    }

    /**
     *
     * @return id of movie
     */
    public Integer getIdMovie() {
        return idMovie;
    }

    /**
     *
     * @return id of cinema
     */
    public Integer getIdCinema() {
        return idCinema;
    }

    /**
     *
     * @return price of ticket
     */
    public float getPrice() {
        return price;
    }

    /**
     *
     * @param idMovie gives the new movie id
     */
    public void setIdMovie(Integer idMovie) {
        this.idMovie = idMovie;
    }

    /**
     *
     * @param idCinema gives the new cinema id
     */
    public void setIdCinema(Integer idCinema) {
        this.idCinema = idCinema;
    }

    /**
     *
     * @param price gives the new price
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.getId() + " " + idMovie + " " + idCinema + " " + price;
    }

}
