package services.impl;

import dto.CityDTO;
import dto.MatrixDistanceDTO;
import entities.MatrixDistance;
import services.CityService;
import services.DistanceService;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless(name = DistanceService.JNDI_NAME)
@Local(DistanceService.class)
public class DistanceServiceUmpl implements DistanceService {

    @PersistenceContext(unitName = "TestPersistence")
    private EntityManager entityManager;

    @EJB
    private CityService cityService;

    @Override
    public MatrixDistanceDTO getDistanceByCities(CityDTO from, CityDTO to) {
        MatrixDistance matrixDistance;
        try {
            matrixDistance = entityManager.createQuery("select md from MatrixDistance md " +
                    "where md.fromCity.id = :fromId and md.toCity.id = :toId", MatrixDistance.class)
                    .setParameter("fromId", from.getId())
                    .setParameter("toId", to.getId())
                    .getSingleResult();
        } catch (NoResultException e) {
            matrixDistance = new MatrixDistance();
        }
        return fillDTO(matrixDistance);
    }

    private MatrixDistanceDTO fillDTO(MatrixDistance matrixDistance) {
        MatrixDistanceDTO matrixDistanceDTO = new MatrixDistanceDTO();
        if (matrixDistance.getId() != null) {
            matrixDistanceDTO.setFrom(cityService.getById(matrixDistance.getFromCity().getId()));
            matrixDistanceDTO.setFrom(cityService.getById(matrixDistance.getToCity().getId()));
            matrixDistanceDTO.setDistance(matrixDistance.getDistance());
        }
        return matrixDistanceDTO;
    }
}
