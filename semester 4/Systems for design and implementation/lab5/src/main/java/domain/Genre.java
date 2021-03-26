package domain;


public class Genre extends BaseEntity<Integer>{
    private String name;

    public Genre(){}
    public Genre(String name){this.name = name;}

    /**
     *
     * @param //set genre name
     */
    public void setName(String name) {this.name = name;}

    /**
     *
     * @return genre name
     */
    public String getName(){return this.name;}

    /**
     *
     * @return printable string of genre entity
     */
    @Override
    public String toString() {
         return this.getId()+" "+this.name+"\n";
    }
}
