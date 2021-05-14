package ro.ubb.catalog.web.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketsDto {
    private Set<TicketDto> tickets;
}
