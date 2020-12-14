package ru.vapima.butjet.butJet3.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

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
}
