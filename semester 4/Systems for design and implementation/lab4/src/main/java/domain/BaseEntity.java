package domain;

public class BaseEntity<ID> {

    private ID id;

    /**
     *
     * @return id
     */
    public ID getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(ID id) {
        this.id = id;
    }

    /**
     *
     * @return printable string of base entity
     */
    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
