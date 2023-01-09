package spring.boot.api.rickandmorty.dto.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiCharactersDto {
    private Long id;
    private String name;
    private String status;
    private String gender;
    private String type;
}
