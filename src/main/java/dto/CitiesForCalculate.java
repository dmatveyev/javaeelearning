package dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlRootElement(name  = "ss")
@XmlSeeAlso({Cities.class, CityDTO.class})
public class CitiesForCalculate {

    @XmlElement(name = "from")
    public Cities from;
    @XmlElement(name = "to")
    public Cities to;

    public CitiesForCalculate() {
        super();
    }

    public Cities getFrom() {
        return from;
    }

    public Cities getTo() {
        return to;
    }
}
