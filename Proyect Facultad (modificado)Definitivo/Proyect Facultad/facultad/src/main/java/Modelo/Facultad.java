package Modelo;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Representa una facultad dentro de una Universidad que contiene una lista de carreras
 */

@XmlRootElement(name = "facultad")
public class Facultad {
    private String nombre;
    private List<Carrera> carreras = new ArrayList<>();

    @XmlAttribute
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @XmlElementRef
    public List<Carrera> getCarreras() { return carreras; }
    public void setCarreras(List<Carrera> carreras) { this.carreras = carreras; }
}