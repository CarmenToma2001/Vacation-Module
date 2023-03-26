package com.example.vacationmodule.repositories;

import com.example.vacationmodule.domain.NationalHolidays;
import com.example.vacationmodule.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NationalHolidayRepository extends CrudRepository<NationalHolidays,Long> {
    List<NationalHolidays> findAllByCountry(String country);
    NationalHolidays findByDateAndCountry(String date, String country);


}
