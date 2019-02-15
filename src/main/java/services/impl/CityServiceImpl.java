package services.impl;

import dto.CityDTO;
import entities.City;
import services.CityService;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
@Stateless(name = CityService.JNDI_NAME)
@Local(CityService.class)
public class CityServiceImpl implements CityService {

    private Logger logger = Logger.getLogger(CityService.JNDI_NAME);

    @PersistenceContext(unitName = "TestPersistence")
    private EntityManager entityManager;

    public List<CityDTO> getCities() {
        List<City> cities = entityManager.createQuery("select c from City c", City.class)
                .getResultList();
        return cities
                .stream()
                .map(this::fillDTO)
                .collect(Collectors.toList());
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
        City city;
        try {
            city = entityManager.createQuery("select c from City c" +
                    " where c.latitude = :latitude and c.longitude = :longitude", City.class)
                    .setParameter("latitude", latitude)
                    .setParameter("longitude", longitude)
                    .getSingleResult();
            return fillDTO(city);
        } catch (NoResultException e) {
            logger.log(Level.WARNING, "No saved city");
        } catch (NonUniqueResultException e) {
            logger.log(Level.WARNING, "Different cities with same coordinates!");
        }
        return new CityDTO();
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
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setLatitude(city.getLatitude());
        cityDTO.setLongitude(city.getLongitude());
        return cityDTO;
    }
}
