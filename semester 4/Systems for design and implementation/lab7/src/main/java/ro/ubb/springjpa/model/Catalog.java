package ro.ubb.springjpa.model;

import javax.persistence.Entity;

@Entity
public class Catalog extends BaseEntity<Integer> {
    private Integer idMovie;
    private Integer idGenre;

    public Catalog() {}

    public Catalog(Integer idM, Integer idG) {
        idMovie=idM;
        idGenre=idG;
    }

    /**
     *
     * @return movie id
     */
    public Integer getIdMovie() { return idMovie; }

    /**
     *
     * @return genre id
     */
    public Integer getIdGenre() { return idGenre; }

    /**
     *
     * @param //set new movie id
     */
    public void setIdMovie(Integer idM) { idMovie=idM; }

    /**
     *
     * @param //set new genre id
     */
    public void setIdGenre(Integer idG) { idGenre=idG; }

    /**
     *
     * @return printable string of catalog entity
     */
    @Override
    public String toString() {
        return this.getId()+" "+this.getIdMovie()+" "+this.getIdGenre()+"\n";
    }

}
