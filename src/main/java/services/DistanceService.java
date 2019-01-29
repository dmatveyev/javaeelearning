package services;

import entities.City;
import entities.MatrixDistance;

public interface DistanceService {

    MatrixDistance getDistanceByCities(City from, City to);

    void saveDistance(MatrixDistance matrixDistance);
}
