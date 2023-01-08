package spring.boot.api.rickandmorty.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.api.rickandmorty.dto.CharacterResponseDto;
import spring.boot.api.rickandmorty.service.SeriesCharacterMapper;
import spring.boot.api.rickandmorty.service.SeriesCharacterService;

@RestController
@RequestMapping("/rick-and-morty-characters")
public class SeriesCharacterController {
    private final SeriesCharacterService characterService;
    private final SeriesCharacterMapper mapper;

    public SeriesCharacterController(SeriesCharacterService characterService,
                                     SeriesCharacterMapper mapper) {
        this.characterService = characterService;
        this.mapper = mapper;
    }

    @GetMapping("/random")
    @ApiOperation(value = "Get random character from Rick and Morty series")
    public CharacterResponseDto randomCharacter() {
        return mapper.toDto(characterService.randomCharacter());
    }

    @GetMapping("/by-name")
    @ApiOperation(value = "Get a list of characters from Rick and Morty series whose names "
                + "contain the entered string")
    public List<CharacterResponseDto> getCharactersWhereNameLike(@RequestParam
                                                                     @ApiParam(defaultValue = "")
                                                                     String name) {
        return characterService.findAllByNameContains(name);
    }
}
