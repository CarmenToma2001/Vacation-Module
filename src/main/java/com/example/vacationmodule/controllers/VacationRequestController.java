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
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/v1.0/vacationRequests")
public class VacationRequestController {
    private final UserRepository userRepository;
    private final VacationRequestService vacationRequestService;
    private final NationalHolidaysService nationalHolidaysService;

    public VacationRequestController(VacationRequestService vacationRequestService, NationalHolidaysService nationalHolidaysService,
                                     UserRepository userRepository) {
        this.vacationRequestService = vacationRequestService;
        this.nationalHolidaysService = nationalHolidaysService;
        this.userRepository = userRepository;
    }

    @PostMapping("/createVacationRequest")
    public String createVacationRequest(@RequestBody VacationRequestBody vacationRequestBody){
        User user = userRepository.findUserById(vacationRequestBody.getUserId());

        LocalDate beginDate = LocalDate.parse(vacationRequestBody.getBeginDate());
        LocalDate endDate = LocalDate.parse(vacationRequestBody.getEndDate());
        if(beginDate.get(ChronoField.DAY_OF_WEEK) == 6 || beginDate.get(ChronoField.DAY_OF_WEEK) == 7){
            return "Beginning date is in the weekend";
        }
        if(endDate.get(ChronoField.DAY_OF_WEEK) == 6 || endDate.get(ChronoField.DAY_OF_WEEK) == 7){
            return "Ending date is in the weekend";
        }
        if(nationalHolidaysService.findByDateAndCountry(vacationRequestBody.getBeginDate(), user.getCountry()) != null){
            return "Beginning date is a national holiday";
        }
        if(nationalHolidaysService.findByDateAndCountry(vacationRequestBody.getEndDate(), user.getCountry()) != null){
            return "Ending date is a national holiday";
        }

        List<LocalDate> dates = beginDate.datesUntil(endDate).collect(Collectors.toList());
        int count = 1;
        for(LocalDate date : dates){
            if(date.get(ChronoField.DAY_OF_WEEK) != 6 && date.get(ChronoField.DAY_OF_WEEK) != 7 && nationalHolidaysService.findByDateAndCountry(date.toString(), user.getCountry()) == null){
                count += 1;
            }
        }

        if(count > user.getVacationDays()){
            return "Not enough vacation days available";
        }

        VacationRequest vacationRequest = new VacationRequest();
        vacationRequest.setStatus("Pending");
        vacationRequest.setDescription(vacationRequestBody.getDescription());
        vacationRequest.setBeginDate(vacationRequestBody.getBeginDate());
        vacationRequest.setEndDate(vacationRequestBody.getEndDate());
        vacationRequest.setDays(count);
        vacationRequest.setUser(user);
        vacationRequestService.save(vacationRequest);
        return "Request created and waiting for approval";
    }

    @PostMapping("/modifyVacationRequest")
    public String modifyVacationRequest(@RequestBody ModifyRequestBody modifyRequestBody){
        User user = userRepository.findUserById(modifyRequestBody.getUserId());
        VacationRequest request = vacationRequestService.findByID(modifyRequestBody.getRequestId());

        LocalDate beginDate = LocalDate.parse(modifyRequestBody.getBeginDate());
        LocalDate endDate = LocalDate.parse(modifyRequestBody.getEndDate());
        if(beginDate.get(ChronoField.DAY_OF_WEEK) == 6 || beginDate.get(ChronoField.DAY_OF_WEEK) == 7){
            return "Beginning date is in the weekend";
        }
        if(endDate.get(ChronoField.DAY_OF_WEEK) == 6 || endDate.get(ChronoField.DAY_OF_WEEK) == 7){
            return "Ending date is in the weekend";
        }
        if(nationalHolidaysService.findByDateAndCountry(modifyRequestBody.getBeginDate(), user.getCountry()) != null){
            return "Beginning date is a national holiday";
        }
        if(nationalHolidaysService.findByDateAndCountry(modifyRequestBody.getEndDate(), user.getCountry()) != null){
            return "Ending date is a national holiday";
        }

        List<LocalDate> dates = beginDate.datesUntil(endDate).collect(Collectors.toList());
        int count = 1;
        for(LocalDate date : dates){
            if(date.get(ChronoField.DAY_OF_WEEK) != 6 && date.get(ChronoField.DAY_OF_WEEK) != 7 && nationalHolidaysService.findByDateAndCountry(date.toString(), user.getCountry()) == null){
                count += 1;
            }
        }

        if(Objects.equals(request.getStatus(),"Approved")){
            request.setStatus("Pending");
            user.setVacationDays(user.getVacationDays() + request.getDays());
        }

        if(count > user.getVacationDays()){
            return "Not enough vacation days available";
        }

        request.setBeginDate(modifyRequestBody.getBeginDate());
        request.setEndDate(modifyRequestBody.getEndDate());
        request.setDescription(modifyRequestBody.getDescription());
        request.setDays(count);
        vacationRequestService.save(request);
        return "Request modified successfully";
    }

    @GetMapping("/getRequests/id/{id}")
    public String getRequest(@PathVariable String id){
        User user = userRepository.findUserById(Long.parseLong(id));
        List<VacationRequest> list = vacationRequestService.findByUserId(user.getId());
        List<VacationBody> vacationBodyList = new ArrayList<>();

        for(VacationRequest vacation : list){
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

    @GetMapping("/getNationalHolidays/country/{country}")
    public String getNationalHolidays(@PathVariable String country){
        List<NationalHolidays> nationalHolidays = nationalHolidaysService.findAllByCountry(country);
        List<NationalHolidayBody> nationalHolidayBodyList = new ArrayList<>();

        for(NationalHolidays holiday : nationalHolidays){
            nationalHolidayBodyList.add(new NationalHolidayBody(holiday.getDate(), holiday.getCountry()));
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            return mapper.writeValueAsString(nationalHolidayBodyList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "{}";
    }

    @GetMapping("/getVacationDays/id/{id}")
    public String getVacationDays(@PathVariable String id){
        User user = userRepository.findUserById(Long.parseLong(id));
        return "{ \"days\": " + user.getVacationDays() + " }";
    }
}
