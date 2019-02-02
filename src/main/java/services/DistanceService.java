package services;

import dto.CityDTO;
import dto.MatrixDistanceDTO;
import entities.MatrixDistance;

public interface DistanceService {

    String JNDI_NAME = "DistanceService";

    MatrixDistanceDTO getDistanceByCities(CityDTO from, CityDTO to);

}
