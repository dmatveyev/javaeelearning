package services;

import dto.CityDTO;
import entities.City;

import java.util.List;

public interface CityService {

    String JNDI_NAME = "CityService";

    List<CityDTO> getCities();

    City getById(Long id);

    void save(CityDTO cityDTO);

    void remove(CityDTO cityDTO);
}
