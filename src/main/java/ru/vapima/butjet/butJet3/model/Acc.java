package ru.vapima.butjet.butJet3.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "accs")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Acc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @Transient
    private Integer balance;
    @Transient
    private LocalDateTime changeTime;
    @NotNull
    private Boolean active;
    @ManyToOne//(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name="person_id", nullable=false)
    private Person person;
    @OneToMany(mappedBy="acc", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<AccRec> accRecList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acc acc = (Acc) o;
        return Objects.equals(id, acc.id) &&
                Objects.equals(name, acc.name) &&
                Objects.equals(balance, acc.balance) &&
                Objects.equals(changeTime, acc.changeTime) &&
                Objects.equals(active, acc.active) &&
                Objects.equals(person, acc.person) &&
                Objects.equals(accRecList, acc.accRecList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, balance, changeTime, active, person, accRecList);
    }
}
