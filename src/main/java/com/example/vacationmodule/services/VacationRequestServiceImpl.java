package com.example.vacationmodule.services;

import com.example.vacationmodule.domain.User;
import com.example.vacationmodule.domain.VacationRequest;
import com.example.vacationmodule.repositories.VacationRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationRequestServiceImpl implements VacationRequestService {
    private final VacationRequestRepository vacationRequestRepository;

    public VacationRequestServiceImpl(VacationRequestRepository vacationRequestRepository) {
        this.vacationRequestRepository = vacationRequestRepository;
    }

    @Override
    public List<VacationRequest> findAll() {
        return (List<VacationRequest>) vacationRequestRepository.findAll();
    }

    @Override
    public void save(VacationRequest request) {
        vacationRequestRepository.save(request);
    }

    @Override
    public void delete(VacationRequest request) {
        vacationRequestRepository.delete(request);
    }

    @Override
    public List<VacationRequest> findByUserId(Long id) {
        return vacationRequestRepository.findVacationRequestByUserId(id);
    }

    @Override
    public VacationRequest findByID(Long id) {
        return vacationRequestRepository.findVacationRequestById(id);    }
}
