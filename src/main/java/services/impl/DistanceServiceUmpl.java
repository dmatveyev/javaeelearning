package services.impl;

import dto.CityDTO;
import dto.MatrixDistanceDTO;
import entities.MatrixDistance;
import services.CalculateDistanceService;
import services.CityService;
import services.DistanceService;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless(name = DistanceService.JNDI_NAME)
@Local(DistanceService.class)
public class DistanceServiceUmpl implements DistanceService {

    @PersistenceContext(unitName = "TestPersistence")
    private EntityManager entityManager;

    @EJB
    private CityService cityService;

    private Logger logger = Logger.getLogger(CalculateDistanceService.JNDI_NAMI);

    @Override
    public MatrixDistanceDTO getDistanceByCities(CityDTO from, CityDTO to) {
        MatrixDistance matrixDistance;
        if(from.getId() != null && to.getId() != null) {
            try {
                matrixDistance = entityManager.createQuery("select md from MatrixDistance md " +
                        "where md.fromCity.id = :fromId and md.toCity.id = :toId", MatrixDistance.class)
                        .setParameter("fromId", from.getId())
                        .setParameter("toId", to.getId())
                        .getSingleResult();
                return fillDTO(matrixDistance);
            } catch (NoResultException e) {
                logger.log(Level.WARNING, "No saved distance between cities");
            } catch (NonUniqueResultException e) {
                logger.log(Level.WARNING, "Different distance between cities");
            }
        }
        return new MatrixDistanceDTO();
    }

    private MatrixDistanceDTO fillDTO(MatrixDistance matrixDistance) {
        MatrixDistanceDTO matrixDistanceDTO = new MatrixDistanceDTO();
        matrixDistanceDTO.setFrom(cityService.getById(matrixDistance.getFromCity().getId()));
        matrixDistanceDTO.setFrom(cityService.getById(matrixDistance.getToCity().getId()));
        matrixDistanceDTO.setDistance(matrixDistance.getDistance());
        return matrixDistanceDTO;
    }
}
