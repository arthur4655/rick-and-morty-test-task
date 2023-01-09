package spring.boot.api.rickandmorty.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterResponseDto {
    private Long id;
    private String name;
    private String status;
    private String gender;
    private String type;
}
