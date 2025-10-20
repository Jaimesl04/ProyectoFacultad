package Modelo;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represento una Universidad que contiene un lista de las facultades
 */

@XmlRootElement(name = "universidad")
public class Universidad {
    private List<Facultad> facultades = new ArrayList<>();

    public Universidad() {}

    @XmlElementRef
    public List<Facultad> getFacultades() { return facultades; }
    public void setFacultades(List<Facultad> facultades) { this.facultades = facultades; }
}