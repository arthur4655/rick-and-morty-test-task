package spring.boot.api.rickandmorty.mapper;

import org.springframework.stereotype.Service;
import spring.boot.api.rickandmorty.dto.CharacterResponseDto;
import spring.boot.api.rickandmorty.dto.api.ApiCharactersDto;
import spring.boot.api.rickandmorty.model.SeriesCharacter;

@Service
public class SeriesCharacterMapper {
    public SeriesCharacter toModel(ApiCharactersDto apiCharactersDto) {
        SeriesCharacter seriesCharacter = new SeriesCharacter();
        seriesCharacter.setExternalId(apiCharactersDto.getId());
        seriesCharacter.setName(apiCharactersDto.getName());
        seriesCharacter.setGender(SeriesCharacter.Gender.valueOf(apiCharactersDto
                .getGender().toUpperCase()));
        seriesCharacter.setStatus(SeriesCharacter.Status.valueOf(apiCharactersDto
                .getStatus().toUpperCase()));
        seriesCharacter.setType(apiCharactersDto.getType());
        return seriesCharacter;
    }

    public CharacterResponseDto toDto(SeriesCharacter seriesCharacter) {
        CharacterResponseDto dto = new CharacterResponseDto();
        dto.setId(seriesCharacter.getId());
        dto.setName(seriesCharacter.getName());
        dto.setGender(seriesCharacter.getGender().name());
        dto.setStatus(seriesCharacter.getStatus().name());
        dto.setType(seriesCharacter.getType());
        return dto;
    }
}
