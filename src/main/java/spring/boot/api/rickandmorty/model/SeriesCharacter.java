package spring.boot.api.rickandmorty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "series_character")
public class SeriesCharacter {
    @Id
    @GeneratedValue(generator = "series_character_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "series_character_id_seq",
            sequenceName = "series_character_id_seq",
            allocationSize = 1)
    private Long id;
    @Column(name = "external_id")
    private Long externalId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String type;

    public enum Gender {
        MALE,
        FEMALE,
        GENDERLESS,
        UNKNOWN;
    }

    public enum Status {
        ALIVE,
        DEAD,
        UNKNOWN;
    }
}
