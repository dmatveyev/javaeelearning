package dto;

public class MatrixDistanceDTO {

    private CityDTO from;
    private CityDTO to;
    private Double distance;

    public CityDTO getFrom() {
        return from;
    }

    public void setFrom(CityDTO from) {
        this.from = from;
    }

    public CityDTO getTo() {
        return to;
    }

    public void setTo(CityDTO to) {
        this.to = to;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
