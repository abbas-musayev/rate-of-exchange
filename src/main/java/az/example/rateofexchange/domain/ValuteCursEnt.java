package az.example.rateofexchange.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "valute_curs")
public class ValuteCursEnt {

    @Id
    public Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDate date;

    @OneToMany()
    List<ValuteTypeEnt> valuteTypes;

}
