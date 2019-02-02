package services.impl;


import dto.CityDTO;
import entities.City;
import services.CityService;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
@Stateless(name = CityService.JNDI_NAME)
@Local(CityService.class)
public class CityServiceImpl implements CityService {

    @PersistenceContext(unitName = "TestPersistence")
    private EntityManager entityManager;

    public List<CityDTO> getCities() {
        List<City> cities = entityManager.createQuery("select c from City c", City.class)
                .getResultList();
        return cities
                .stream()
                .collect(ArrayList::new,
                        (list, city) ->
                                list.add(fillDTO(city)),
                        List::addAll);
    }

    public CityDTO getById(Long id) {
        City city = entityManager.find(City.class, id);
        return fillDTO(city);
    }

    public void save(CityDTO cityDTO) {
        CityDTO temp = getByCoordinates(cityDTO.getLatitude(), cityDTO.getLongitude());
        if (temp.getId() == null) {
            City city = fillEntity(cityDTO);
            if (city.getId() != null) {
                entityManager.merge(city);
            } else {
                entityManager.persist(city);
            }
        }
    }

    @Override
    public CityDTO getByCoordinates(Double latitude, Double longitude) {
        City city = entityManager.createQuery("select c from City c" +
                " where c.latitude = :latitude and c.longitude = :longitude", City.class)
                .setParameter("latitude", latitude)
                .setParameter("longitude", longitude)
                .getSingleResult();
        return fillDTO(city);
    }

    private City fillEntity(CityDTO cityDTO) {
        City city = new City();
        city.setId(cityDTO.getId());
        city.setName(cityDTO.getName());
        city.setLatitude(cityDTO.getLatitude());
        city.setLongitude(cityDTO.getLongitude());
        return city;
    }

    private CityDTO fillDTO(City city) {
        CityDTO cityDTO = new CityDTO();
        if (city != null) {
            cityDTO.setId(city.getId());
            cityDTO.setName(city.getName());
            cityDTO.setLatitude(city.getLatitude());
            cityDTO.setLongitude(city.getLongitude());
        }
        return cityDTO;
    }
}
