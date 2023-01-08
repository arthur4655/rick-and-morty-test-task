package spring.boot.api.rickandmorty.service;

import java.util.List;
import spring.boot.api.rickandmorty.dto.CharacterResponseDto;
import spring.boot.api.rickandmorty.model.SeriesCharacter;

public interface SeriesCharacterService {
    void parseCharactersFromApi();

    SeriesCharacter randomCharacter();

    List<CharacterResponseDto> findAllByNameContains(String namePart);
}
