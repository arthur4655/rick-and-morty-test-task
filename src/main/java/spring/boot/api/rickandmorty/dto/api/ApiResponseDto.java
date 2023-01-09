package spring.boot.api.rickandmorty.dto.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseDto {
    private ApiInfoDto info;
    private ApiCharactersDto[] results;
}
