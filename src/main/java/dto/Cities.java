package dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name  = "cities")
@XmlSeeAlso(CityDTO.class)
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Cities  {

    private List<CityDTO> cities;

    @XmlElement(name = "city")
    public List<CityDTO> getCities() {
        return cities;
    }

    public void setCities(List<CityDTO> cities) {
        this.cities = cities;
    }
}
