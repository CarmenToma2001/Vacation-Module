package com.example.vacationmodule.repositories;

import com.example.vacationmodule.domain.User;
import com.example.vacationmodule.domain.VacationRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VacationRequestRepository extends CrudRepository<VacationRequest,Long> {

    List<VacationRequest> findVacationRequestByUserId(Long id);
    VacationRequest findVacationRequestById(Long Id);
}
