package com.example.vacationmodule.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VacationRequestBody {
    private Long userId;
    private String description;
    private String beginDate;
    private String endDate;


}
