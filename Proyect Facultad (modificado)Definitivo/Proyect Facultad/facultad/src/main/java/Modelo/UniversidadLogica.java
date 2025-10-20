package Modelo;

import jakarta.xml.bind.*;
import java.util.*;
import java.io.File;

/**
 * Clase encargada de leer y guardar la informacion de Universidad.xml
 * y gestiona las operaciones sobre los Estudiantes
 */

public class UniversidadLogica {
    private final String XML_FILE = "universidad.xml";
    private Universidad universidad;

    public UniversidadLogica() {
        universidad = leerUniversidad();
    }

    // Leer el XML
    public Universidad leerUniversidad() {
        try {
            JAXBContext context = JAXBContext.newInstance(Universidad.class);
            Unmarshaller um = context.createUnmarshaller();
            return (Universidad) um.unmarshal(new File(XML_FILE));
        } catch (Exception e) {
            System.out.println("Error al leer XML: " + e.getMessage());
            return new Universidad();
        }
    }


    // Guardar en XML
    private void guardarXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(Universidad.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(universidad, new File(XML_FILE));
        } catch (Exception e) {
            System.out.println("Error al guardar XML: " + e.getMessage());
        }
    }

    public String obtenerEstructuraCompleta() {
        StringBuilder sb = new StringBuilder();
        for (Facultad f : universidad.getFacultades()) {
            sb.append("Facultad: ").append(f.getNombre()).append("\n");
            for (Carrera c : f.getCarreras()) {
                sb.append("  Carrera: ").append(c.getNombre()).append("\n");
                for (Estudiante e : c.getEstudiantes()) {
                    sb.append("    ").append(e).append("\n");
                }
            }
        }
        return sb.toString();
    }

    public List<Estudiante> obtenerTodosEstudiantes() {
        List<Estudiante> todosEstudiantes = new ArrayList<>();
        for (Facultad f : universidad.getFacultades()) {
            for (Carrera c : f.getCarreras()) {
                todosEstudiantes.addAll(c.getEstudiantes());
            }
        }
        return todosEstudiantes;
    }


    public void agregarEstudiante(Estudiante e) {
        // Por simplicidad, añade a la primera facultad y primera carrera
        if (!universidad.getFacultades().isEmpty() && 
            !universidad.getFacultades().get(0).getCarreras().isEmpty()) {
            universidad.getFacultades().get(0).getCarreras().get(0).getEstudiantes().add(e);
            guardarXML();
        }
    }

    public boolean eliminarEstudiantePorMatricula(String matricula) {
        boolean eliminado = false;
        for (Facultad f : universidad.getFacultades()) {
            for (Carrera c : f.getCarreras()) {
                eliminado = eliminado || c.getEstudiantes().removeIf(est -> est.getMatricula().equals(matricula));
            }
        }
        if (eliminado) {
            guardarXML();
        }
        return eliminado;
    }
    public Estudiante buscarEstudiantePorMatricula(String matricula) {
        for (Facultad f : universidad.getFacultades()) {
            for (Carrera c : f.getCarreras()) {
                for (Estudiante e : c.getEstudiantes()) {
                    if (e.getMatricula().equals(matricula)) {
                        return e;
                    }
                }
            }
        }
        return null;
    }

    public void modificarEstudiante(String matricula, Estudiante estudianteModificado) {
        for (Facultad f : universidad.getFacultades()) {
            for (Carrera c : f.getCarreras()) {
                for (Estudiante e : c.getEstudiantes()) {
                    if (e.getMatricula().equals(matricula)) {
                        e.setNombre(estudianteModificado.getNombre());
                        e.setEdad(estudianteModificado.getEdad());
                        e.setEmail(estudianteModificado.getEmail());
                        guardarXML();
                        return;
                    }
                }
            }
        }
    }
}

