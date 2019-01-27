package dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlRootElement(name  = "cities")
@XmlSeeAlso(CityDTO.class)
public class Cities extends ArrayList<CityDTO> {

    public Cities() {
        super();
    }

    public Cities(Collection<? extends CityDTO> cityDTOS) {
        super(cityDTOS);
    }

    @XmlElement(name = "city")
    public List<CityDTO> getCities() {
        return this;
    }

    public void setCities(List<CityDTO> cities) {
        this.addAll(cities);
    }
}
