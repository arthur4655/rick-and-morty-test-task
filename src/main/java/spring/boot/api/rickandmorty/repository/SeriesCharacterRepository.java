package spring.boot.api.rickandmorty.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.boot.api.rickandmorty.model.SeriesCharacter;

public interface SeriesCharacterRepository extends JpaRepository<SeriesCharacter, Long> {
    @Query(value = "SELECT sc.externalId, sc.id FROM SeriesCharacter sc")
    List<Long[]> getAllIdsInDb();

    List<SeriesCharacter> findAllByNameContains(String namePart);
}
