package az.example.rateofexchange.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "valute")
public class ValuteEnt {

    @Id
    public Long id;

    @Column(name = "nominal")
    private String nominal;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private BigDecimal value;

}
