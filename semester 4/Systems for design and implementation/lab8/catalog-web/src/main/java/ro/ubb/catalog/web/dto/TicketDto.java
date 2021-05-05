package ro.ubb.catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TicketDto extends BaseDto {
    private Integer idMovie;
    private Integer idCinema;
    private float price;
}
