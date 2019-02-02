package dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CalculateResults {

    @XmlElement(name = "calculateResult")
    @XmlElementWrapper(name = "result")
    private List<CalculateResultDTO> calculateResultDTOS;

    public List<CalculateResultDTO> getCalculateResultDTOS() {
        return calculateResultDTOS;
    }

    public void setCalculateResultDTOS(List<CalculateResultDTO> calculateResultDTOS) {
        this.calculateResultDTOS = calculateResultDTOS;
    }
}
