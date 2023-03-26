package com.example.vacationmodule.services;

import com.example.vacationmodule.domain.NationalHolidays;
import com.example.vacationmodule.domain.User;
import com.example.vacationmodule.repositories.NationalHolidayRepository;
import com.example.vacationmodule.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationalHolidaysServiceImpl implements NationalHolidaysService {
    private final NationalHolidayRepository nationalHolidayRepository;

    public NationalHolidaysServiceImpl(NationalHolidayRepository nationalHolidayRepository) {
        this.nationalHolidayRepository = nationalHolidayRepository;
    }

    @Override
    public void save(NationalHolidays nationalHolidays) {
        nationalHolidayRepository.save(nationalHolidays);
    }

    @Override
    public List<NationalHolidays> findAllByCountry(String country) {
        return nationalHolidayRepository.findAllByCountry(country);
    }

    @Override
    public NationalHolidays findByDateAndCountry(String date, String country) {
        return nationalHolidayRepository.findByDateAndCountry(date, country);
    }
}
