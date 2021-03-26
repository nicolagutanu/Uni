package domain;

public class Movie extends BaseEntity<Integer> {
    private String name;
    private Float rating;

    public Movie() {}

    public Movie(String n, Float r) {
        name=n;
        rating=r;
    }

    /**
     *
     * @return movie name
     */
    public String getName() { return name; }

    /**
     *
     * @return movie rating
     */
    public Float getRating() { return rating; }

    /**
     *
     * @param //set movie name
     */
    public void setName(String n) { name=n; }

    /**
     *
     * @param //set movie rating
     */
    public void setRating(Float r) { rating=r; }

    /**
     *
     * @return printable string of movie entity
     */
    @Override
    public String toString() {
        return this.getId()+" "+this.getName()+" "+this.getRating()+"\n";
    }
}
