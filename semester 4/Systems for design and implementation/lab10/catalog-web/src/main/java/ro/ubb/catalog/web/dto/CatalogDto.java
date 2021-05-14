package ro.ubb.catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class CatalogDto extends BaseDto {
    private Integer idMovie;
    private Integer idGenre;
}
