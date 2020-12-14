package ru.vapima.butjet.butJet3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


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
}
