package ro.ubb.catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class CinemaDto extends BaseDto {
    private String name;
    private Integer nrOfSeats;
}
