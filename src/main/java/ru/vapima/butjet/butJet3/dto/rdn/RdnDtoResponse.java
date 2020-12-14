package ru.vapima.butjet.butJet3.dto.rdn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RdnDtoResponse {
    private Integer rdn;
    private Integer allPlans;
    private Integer allAccounts;
    private Integer allActiveAccounts;
    private Integer allPlansInThisMounth;
}
