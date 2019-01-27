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

@Stateless(name = CityService.JNDI_NAME)
@Local(CityService.class)
public class CityServiceImpl implements CityService {

    @PersistenceContext(unitName = "TestPersistence")
    private EntityManager entityManager;

    public List<CityDTO> getCities() {
        List<City> cities = entityManager.createQuery("select c from City c", City.class)
                .getResultList();
        List<CityDTO> c = cities
                .stream()
                .collect(ArrayList::new,
                        (list, city) -> {
                            CityDTO cityDTO = new CityDTO();
                            cityDTO.setId(city.getId());
                            cityDTO.setName(city.getName());
                            cityDTO.setLatitude(city.getLatitude());
                            cityDTO.setLongitude(city.getLongitude());
                            list.add(cityDTO);
                        },
                        List::addAll);
        return c;
    }

    public City getById(Long id) {
        return entityManager.find(City.class, id);
    }

    public void save(CityDTO cityDTO) {
        City city = fillEntity(cityDTO);
        if (city.getId() != null) {
            entityManager.merge(city);
        } else {
            entityManager.persist(city);
        }
    }

    public void remove(CityDTO cityDTO) {

    }

    private City fillEntity(CityDTO cityDTO) {
        City city = new City();
        city.setId(cityDTO.getId());
        city.setName(cityDTO.getName());
        city.setLatitude(cityDTO.getLatitude());
        city.setLongitude(cityDTO.getLongitude());
        return city;
    }
}
