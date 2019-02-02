package dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "calculateResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class CalculateResultDTO {

    private Double crowflightDistance;
    private Double matrixDistance;
    private String error;
    private CityDTO from;
    private CityDTO to;

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

    public Double getCrowflightDistance() {
        return crowflightDistance;
    }

    public void setCrowflightDistance(Double crowflightDistance) {
        this.crowflightDistance = crowflightDistance;
    }

    public Double getMatrixDistance() {
        return matrixDistance;
    }

    public void setMatrixDistance(Double matrixDistance) {
        this.matrixDistance = matrixDistance;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
