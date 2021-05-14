package ro.ubb.catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class TicketDto extends BaseDto {
    private Integer idMovie;
    private Integer idCinema;
    private float price;
}
