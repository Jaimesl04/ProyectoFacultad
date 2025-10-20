package Modelo;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represento una carrera dentro de una facultad que contiene una lista de los estudiantes que hay
 */

@XmlRootElement(name = "carrera")
public class Carrera {
    private String nombre;
    private List<Estudiante> estudiantes = new ArrayList<>();

    @XmlAttribute
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @XmlElementRef
    public List<Estudiante> getEstudiantes() { return estudiantes; }
    public void setEstudiantes(List<Estudiante> estudiantes) { this.estudiantes = estudiantes; }
}