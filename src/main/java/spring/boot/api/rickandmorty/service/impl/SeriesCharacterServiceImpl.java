package spring.boot.api.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import spring.boot.api.rickandmorty.dto.CharacterResponseDto;
import spring.boot.api.rickandmorty.dto.api.ApiCharactersDto;
import spring.boot.api.rickandmorty.mapper.SeriesCharacterMapper;
import spring.boot.api.rickandmorty.model.SeriesCharacter;
import spring.boot.api.rickandmorty.repository.SeriesCharacterRepository;
import spring.boot.api.rickandmorty.service.SeriesCharacterService;
import spring.boot.api.rickandmorty.util.CharacterParser;

@Service
public class SeriesCharacterServiceImpl implements SeriesCharacterService {
    public static final int EXTERNAL_ID = 0;
    public static final int INTERNAL_ID = 1;
    @Value("${api.url.value}")
    private String apiUrl;
    private final SeriesCharacterRepository seriesCharacterRepository;
    private final SeriesCharacterMapper mapper;
    private final CharacterParser characterParser;

    public SeriesCharacterServiceImpl(SeriesCharacterRepository seriesCharacterRepository,
                                      SeriesCharacterMapper mapper,
                                      CharacterParser characterParser) {
        this.seriesCharacterRepository = seriesCharacterRepository;
        this.mapper = mapper;
        this.characterParser = characterParser;
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

    @PostConstruct
    @Scheduled(cron = "0 0 8 * * ?")
    public void syncCharactersInDb() {
        Map<Long, Long> currentIdsInDb = seriesCharacterRepository.getAllIdsInDb().stream()
                .collect(Collectors.toMap(l -> l[EXTERNAL_ID], l -> l[INTERNAL_ID]));
        List<ApiCharactersDto> apiCharactersDtos = characterParser.parseCharactersFromApi(apiUrl);
        List<SeriesCharacter> charactersToSync = apiCharactersDtos.stream()
                .map(mapper::toModel)
                .peek(m -> {
                    if (currentIdsInDb.get(m.getExternalId()) != null) {
                        m.setId(currentIdsInDb.get(m.getExternalId()));
                    }
                })
                .collect(Collectors.toList());
        seriesCharacterRepository.saveAll(charactersToSync);
    }
}
