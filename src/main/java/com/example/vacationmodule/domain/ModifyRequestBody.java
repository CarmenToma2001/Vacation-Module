package com.example.vacationmodule.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModifyRequestBody {
    private Long userId;
    private Long requestId;
    private String description;
    private String beginDate;
    private String endDate;


}
