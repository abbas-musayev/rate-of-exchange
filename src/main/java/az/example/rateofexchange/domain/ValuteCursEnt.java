package az.example.rateofexchange.domain;

import az.example.rateofexchange.config.LocalDateAttributeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "valute_curs")
@SQLDelete(sql = "update valute_curs set is_deleted = true , is_active = false where id = ?")
@Where(clause = "is_deleted = false")
public class ValuteCursEnt{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate date;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "valute_curs_id")
    Set<ValuteTypeEnt> valuteTypes;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;
    @Column(name = "is_active")
    private Boolean isActive = Boolean.FALSE;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    @JsonIgnore
    private Instant createdDate = Instant.now();

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    @JsonIgnore
    private Instant lastModifiedDate = Instant.now();

    @PrePersist
    public void prePersist(){
        setIsActive(true);
        setIsDeleted(false);
    }
}
