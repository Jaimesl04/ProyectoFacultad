package Modelo;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
 * Representa una Univerdidad que contiene una lista de las facultades
 */

@XmlRootElement(name = "universidad")
@XmlType(propOrder = {"nombre", "direccion", "presupuesto", "facultades"})
public class Universidad {
    private String nombre = "UPO";
    private String direccion = "Ctra. de Utrera, km. 1, 41013 Sevilla";
    private double presupuesto;
    private List<Facultad> facultades = new ArrayList<>();

    public Universidad() {}

    public Universidad(String nombre, String direccion, double presupuesto) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.presupuesto = presupuesto;
    }

    @XmlElement(name = "nombre")
    public String getNombre() { 
        return nombre; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    @XmlElement(name = "direccion")
    public String getDireccion() { 
        return direccion; 
    }
    public void setDireccion(String direccion) { 
        this.direccion = direccion; 
    }

    @XmlElement(name = "presupuesto")
    public double getPresupuesto() { 
        return presupuesto; 
    }
    public void setPresupuesto(double presupuesto) { 
        this.presupuesto = presupuesto; 
    }

    @XmlElementRef
    public List<Facultad> getFacultades() { 
        return facultades; 
    }
    public void setFacultades(List<Facultad> facultades) { 
        this.facultades = facultades; 
    }

    // Calcula el presupuesto con valores predefinidos
    public double calcularPresupuesto() {
        double presupuestoTotal = 0.0;
        double costeBaseUniversidad = 500000.0;
        double costePorFacultad = 200000.0;
        double costePorCarrera = 75000.0;
        double costePorEstudiante = 3000.0;
        
        presupuestoTotal += costeBaseUniversidad;
        
        // Se recorre la estructura sumando los costes
        for (Facultad facultad : facultades) {
            presupuestoTotal += costePorFacultad;
            
            for (Carrera carrera : facultad.getCarreras()) {
                presupuestoTotal += costePorCarrera;
                presupuestoTotal += carrera.getEstudiantes().size() * costePorEstudiante;
            }
        }
        
        this.presupuesto = presupuestoTotal;
        return presupuestoTotal;
    }

    // Calcula el presupuesto con los parametros personalizados
    public double calcularPresupuesto(double costeBase, double costeFacultad, double costeCarrera, double costeEstudiante) {
        double presupuestoTotal = costeBase;
        
        for (Facultad facultad : facultades) {
            presupuestoTotal += costeFacultad;
            
            for (Carrera carrera : facultad.getCarreras()) {
                presupuestoTotal += costeCarrera;
                presupuestoTotal += carrera.getEstudiantes().size() * costeEstudiante;
            }
        }
        
        this.presupuesto = presupuestoTotal;
        return presupuestoTotal;
    }

    // Genera un resumen de la univerdidad y calcula las estadisticas
    public String getInfoUniversidad() {
        int totalEstudiantes = 0;
        int totalCarreras = 0;
        
        for (Facultad facultad : facultades) {
            totalCarreras += facultad.getCarreras().size();
            for (Carrera carrera : facultad.getCarreras()) {
                totalEstudiantes += carrera.getEstudiantes().size();
            }
        }
        
        return String.format(Locale.US, "Universidad: %s\nDirección: %s\nFacultades: %d\nCarreras: %d\nEstudiantes: %d\nPresupuesto: €%,.2f",
                nombre, direccion, facultades.size(), totalCarreras, totalEstudiantes, presupuesto);
    }
}