package com.example.vacationmodule.services;

import com.example.vacationmodule.domain.NationalHolidays;
import com.example.vacationmodule.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NationalHolidaysService {
    void save(NationalHolidays nationalHolidays);
    List<NationalHolidays> findAllByCountry(String country);
    NationalHolidays findByDateAndCountry(String date, String country);

}
