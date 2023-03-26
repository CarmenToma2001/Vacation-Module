package com.example.vacationmodule.services;

import com.example.vacationmodule.domain.User;
import com.example.vacationmodule.domain.VacationRequest;

import java.util.List;

public interface VacationRequestService {
    List<VacationRequest> findAll();
    void save (VacationRequest request);
    void delete(VacationRequest request);

    List<VacationRequest> findByUserId(Long id);

    VacationRequest findByID(Long id);

}
