package services;

import dto.CityDTO;

import java.util.List;

public interface CityService {

    String JNDI_NAME = "CityService";

    List<CityDTO> getCities();

    CityDTO getById(Long id);

    void save(CityDTO cityDTO);

    CityDTO getByCoordinates(Double latitude, Double longitude);
}
