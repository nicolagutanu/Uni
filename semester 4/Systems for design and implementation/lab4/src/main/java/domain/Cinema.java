package domain;

public class Cinema extends BaseEntity<Integer>{
    private String name;
    private Integer nrOfSeats;

    public Cinema() {}

    public Cinema(String name, Integer nrOfSeats) {
        this.name=name;
        this.nrOfSeats=nrOfSeats;
    }

    /**
     *
     * @return cinema name
     */
    public String getName() { return name; }

    /**
     *
     * @return nr of seats in cinema
     */
    public Integer getNrOfSeats() { return nrOfSeats; }

    /**
     *
     * @param //set name of cinema
     */
    public void setName(String name) { this.name = name; }

    /**
     *
     * @param //set nr of seats in cinema
     */
    public void setNrOfSeats(Integer nrOfSeats) { this.nrOfSeats = nrOfSeats; }

    /**
     *
     * @return printable string of cinema entity
     */
    @Override
    public String toString() {
        return this.getId()+" "+this.getName()+" "+this.getNrOfSeats()+"\n";
    }
}
