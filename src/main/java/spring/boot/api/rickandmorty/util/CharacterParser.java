package spring.boot.api.rickandmorty.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import spring.boot.api.rickandmorty.dto.api.ApiCharactersDto;
import spring.boot.api.rickandmorty.dto.api.ApiResponseDto;
import spring.boot.api.rickandmorty.service.HttpClient;

@Component
public class CharacterParser {
    private final HttpClient httpClient;

    public CharacterParser(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public List<ApiCharactersDto> parseCharactersFromApi(String apiUrl) {
        ApiResponseDto apiResponseDto = httpClient.get(apiUrl, ApiResponseDto.class);
        List<ApiResponseDto> characterDtoList = new ArrayList<>();
        characterDtoList.add(apiResponseDto);
        while (apiResponseDto.getInfo().getNext() != null) {
            apiResponseDto = httpClient.get(apiResponseDto.getInfo().getNext(),
                    ApiResponseDto.class);
            characterDtoList.add(apiResponseDto);
        }
        return characterDtoList.stream()
                .map(ApiResponseDto::getResults)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }
}
