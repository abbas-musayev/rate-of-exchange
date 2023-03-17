package az.example.rateofexchange.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "valute_type")
public class ValuteTypeEnt {

    @Id
    public Long id;

    @OneToMany
    private List<ValuteEnt> valute;

}
