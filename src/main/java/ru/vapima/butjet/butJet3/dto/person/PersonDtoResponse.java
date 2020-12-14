package ru.vapima.butjet.butJet3.dto.person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vapima.butjet.butJet3.model.Role;
import ru.vapima.butjet.butJet3.model.State;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDtoResponse {
    private Long id;
    private String name;
    private String mail;
    private Role role;
    private State state;
}
