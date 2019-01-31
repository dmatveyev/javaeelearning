package dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name  = "ss")
public class CitiesForCalculate {

    @XmlElement(name = "mode")
    public CalculateMode mode;

    @XmlElementWrapper(name="from")
    @XmlElement(name = "city")
    public List<CityDTO> from;
    @XmlElementWrapper(name="to")
    @XmlElement(name = "city")
    public List<CityDTO> to;

    public List<CityDTO> getFrom() {
        return from;
    }

    public List<CityDTO> getTo() {
        return to;
    }
}
