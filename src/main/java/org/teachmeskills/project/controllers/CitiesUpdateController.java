package org.teachmeskills.project.controllers;

import org.springframework.stereotype.Component;
import org.teachmeskills.project.dao.CitiesDao;
import org.teachmeskills.project.entitiy.City;
import java.util.List;

@Component
public class CitiesUpdateController implements UpdateController<City> {
    CitiesDao citiesDao;

    public CitiesUpdateController(CitiesDao citiesDBdao) {
        this.citiesDao = citiesDBdao;
    }

    public void add(City city) { citiesDao.addCity(city);}

    public List<City> getAll() { return citiesDao.getAllCities(); }

    public void remove(City city) { citiesDao.removeCity(city); }

    public void update(City city) { citiesDao.updateCity(city); }
}
