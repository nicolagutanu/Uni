package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Ticket extends BaseEntity<Integer> {
    private Integer idMovie;
    private Integer idCinema;
    private float price;
}
