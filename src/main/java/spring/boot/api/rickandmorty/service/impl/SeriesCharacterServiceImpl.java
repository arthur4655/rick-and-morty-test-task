package spring.boot.api.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import spring.boot.api.rickandmorty.dto.CharacterResponseDto;
import spring.boot.api.rickandmorty.dto.api.ApiResponseDto;
import spring.boot.api.rickandmorty.model.SeriesCharacter;
import spring.boot.api.rickandmorty.repository.SeriesCharacterRepository;
import spring.boot.api.rickandmorty.service.HttpClient;
import spring.boot.api.rickandmorty.service.SeriesCharacterMapper;
import spring.boot.api.rickandmorty.service.SeriesCharacterService;

@Service
public class SeriesCharacterServiceImpl implements SeriesCharacterService {
    public static final int EXTERNAL_ID = 0;
    public static final int INTERNAL_ID = 1;
    public static final String API_URL = "https://rickandmortyapi.com/api/character";
    private HttpClient httpClient;
    private SeriesCharacterRepository seriesCharacterRepository;
    private SeriesCharacterMapper mapper;
    private Map<Long, Long> currentIdsInDb;

    public SeriesCharacterServiceImpl(HttpClient httpClient,
                                      SeriesCharacterRepository seriesCharacterRepository,
                                      SeriesCharacterMapper mapper) {
        this.httpClient = httpClient;
        this.seriesCharacterRepository = seriesCharacterRepository;
        this.mapper = mapper;
    }

    @PostConstruct
    @Scheduled(cron = "0 0 8 * * ?")
    @Override
    public void parseCharactersFromApi() {
        ApiResponseDto apiResponseDto = httpClient.get(API_URL, ApiResponseDto.class);
        currentIdsInDb = seriesCharacterRepository.getAllIdsInDb().stream()
                .collect(Collectors.toMap(l -> l[EXTERNAL_ID], l -> l[INTERNAL_ID]));
        saveOrdUpdateCharacters(apiResponseDto);
        while (apiResponseDto.getInfo().getNext() != null) {
            apiResponseDto = httpClient.get(apiResponseDto.getInfo().getNext(),
                            ApiResponseDto.class);
            saveOrdUpdateCharacters(apiResponseDto);
        }
    }

    @Override
    public SeriesCharacter randomCharacter() {
        long count = seriesCharacterRepository.count();
        return seriesCharacterRepository.getReferenceById((long) (Math.random() * count));
    }

    @Override
    public List<CharacterResponseDto> findAllByNameContains(String namePart) {
        return seriesCharacterRepository.findAllByNameContains(namePart).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    private void saveOrdUpdateCharacters(ApiResponseDto apiResponseDto) {
        Arrays.stream(apiResponseDto.getResults())
                .map(mapper::toModel)
                .forEach(m -> {
                    if (currentIdsInDb.get(m.getExternalId()) != null) {
                        m.setId(currentIdsInDb.get(m.getExternalId()));
                        seriesCharacterRepository.save(m);
                    } else {
                        seriesCharacterRepository.save(m);
                    }
                });
    }

}
