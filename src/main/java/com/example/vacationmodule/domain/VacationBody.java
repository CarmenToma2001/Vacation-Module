package com.example.vacationmodule.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VacationBody {

    private Long id;
    private String status;
    private String description;
    private String beginDate;
    private String endDate;
    private int days;
}
