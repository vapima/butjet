package ru.vapima.butjet.butJet3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "accrec")
public class AccRec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Please enter balance of Record")
    private Integer balance;
    @NotNull(message = "Please enter time of record")
    private LocalDateTime changeTime;
    @ManyToOne//(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name="acc_id", nullable=false)
    private Acc acc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccRec accRec = (AccRec) o;
        return Objects.equals(id, accRec.id) &&
                Objects.equals(balance, accRec.balance) &&
                Objects.equals(changeTime, accRec.changeTime) &&
                Objects.equals(acc, accRec.acc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, changeTime, acc);
    }
}
