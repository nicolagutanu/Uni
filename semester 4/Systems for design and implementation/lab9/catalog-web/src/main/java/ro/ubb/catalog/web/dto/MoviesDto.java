package ro.ubb.catalog.web.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MoviesDto {
    private Set<MovieDto> movies;
}
