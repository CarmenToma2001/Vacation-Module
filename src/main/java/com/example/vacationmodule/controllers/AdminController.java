package com.example.vacationmodule.controllers;

import com.example.vacationmodule.domain.*;
import com.example.vacationmodule.repositories.UserRepository;
import com.example.vacationmodule.services.NationalHolidaysService;
import com.example.vacationmodule.services.VacationRequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1.0/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final VacationRequestService vacationRequestService;
    private final NationalHolidaysService nationalHolidaysService;

    public AdminController(VacationRequestService vacationRequestService, NationalHolidaysService nationalHolidaysService,
                                     UserRepository userRepository) {
        this.vacationRequestService = vacationRequestService;
        this.nationalHolidaysService = nationalHolidaysService;
        this.userRepository = userRepository;
    }

    @PostMapping("/approveRequest/id/{id}")
    public String approveRequest(@PathVariable String id){
        VacationRequest vacationRequest = vacationRequestService.findByID(Long.parseLong(id));
        vacationRequest.setStatus("Approved");
        vacationRequestService.save(vacationRequest);
        User user = vacationRequest.getUser();
        user.setVacationDays(user.getVacationDays() - vacationRequest.getDays());
        userRepository.save(user);
        return "Request approved";
    }

    @GetMapping("/getAllRequests")
    public String getAllRequest(){
        List<VacationRequest> allRequests = vacationRequestService.findAll();
        List<VacationBody> vacationBodyList = new ArrayList<>();

        for(VacationRequest vacation : allRequests){
            vacationBodyList.add(new VacationBody(vacation.getId(), vacation.getStatus(), vacation.getDescription(), vacation.getBeginDate(), vacation.getEndDate(), vacation.getDays()));
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            return mapper.writeValueAsString(vacationBodyList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "{}";

    }

    @PostMapping("/addNationalHoliday")
    public String addNationalHoliday(@RequestBody NationalHolidayBody nationalHolidayBody){
        NationalHolidays nat = new NationalHolidays();
        nat.setDate(nationalHolidayBody.getDate());
        nat.setCountry(nationalHolidayBody.getCountry());
        nationalHolidaysService.save(nat);
        return "National Holiday added";
    }
}
