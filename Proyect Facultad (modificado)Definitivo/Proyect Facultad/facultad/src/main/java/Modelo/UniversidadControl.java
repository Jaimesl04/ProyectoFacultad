package Modelo;

import jakarta.xml.bind.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
 * Clase encargada de leer y guardar la informacion de Universidad.xml
 * y gestiona las operaciones sobre los estudiantes
 */

public class UniversidadControl {
    private final String XML_FILE = "universidad.xml";
    private Universidad universidad;

    // Carga la universidad desde el XML y crea la estructura inicial en caso de que esté vacia
    public UniversidadControl() {
        universidad = leerUniversidad();
        if (universidad.getFacultades().isEmpty()) {
            inicializarEstructura();
        }
    }

    // Verifica si el XML existe antes de leerlo
    public Universidad leerUniversidad() {
        try {
            File xmlFile = new File(XML_FILE);
            if (!xmlFile.exists()) {
                System.out.println("Archivo " + XML_FILE + " no encontrado. Se creará uno nuevo.");
                return new Universidad();
            }
            
            JAXBContext context = JAXBContext.newInstance(Universidad.class);
            Unmarshaller um = context.createUnmarshaller();
            return (Universidad) um.unmarshal(xmlFile);
        } catch (Exception e) {
            System.out.println("Error al leer XML: " + e.getMessage());
            return new Universidad();
        }
    }

    // Si no existe crea una estructura inicial basica
    private void inicializarEstructura() {
        System.out.println("Inicializando estructura universitaria...");
        
        universidad.setNombre("UPO");
        universidad.setDireccion("Ctra. de Utrera, km. 1, 41013 Sevilla");
        
        Facultad facultad = new Facultad();
        facultad.setNombre("Facultad de Ingeniería");
        
        Carrera carrera = new Carrera();
        carrera.setNombre("Ingeniería Informática");
        
        facultad.getCarreras().add(carrera);
        universidad.getFacultades().add(facultad);
        
        // Calcula el presupuesto inicial y lo guarda
        universidad.calcularPresupuesto();
        guardarXML();
        System.out.println("Estructura inicial creada correctamente.");
    }

    // Guarda los datos en el XML
    private void guardarXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(Universidad.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(universidad, new File(XML_FILE));
            System.out.println("Datos guardados correctamente en " + XML_FILE);
        } catch (Exception e) {
            System.out.println("Error al guardar XML: " + e.getMessage());
        }
    }

    // Genera una representacion de la estructura universitaria
    public String obtenerEstructuraCompleta() {
        StringBuilder sb = new StringBuilder();
        
        if (universidad.getFacultades().isEmpty()) {
            return "No hay datos universitarios disponibles.";
        }
        
        // Construye la estructura
        for (Facultad f : universidad.getFacultades()) {
            sb.append("Facultad: ").append(f.getNombre()).append("\n");
            for (Carrera c : f.getCarreras()) {
                sb.append("  Carrera: ").append(c.getNombre()).append("\n");
                if (c.getEstudiantes().isEmpty()) {
                    sb.append("    No hay estudiantes en esta carrera\n");
                } else {
                    for (Estudiante e : c.getEstudiantes()) {
                        sb.append("    ").append(e).append("\n");
                    }
                }
            }
        }
        return sb.toString();
    }

    // Obtiene una lista de todos los estudiantes
    public List<Estudiante> obtenerTodosEstudiantes() {
        List<Estudiante> todosEstudiantes = new ArrayList<>();
        for (Facultad f : universidad.getFacultades()) {
            for (Carrera c : f.getCarreras()) {
                todosEstudiantes.addAll(c.getEstudiantes());
            }
        }
        return todosEstudiantes;
    }

    // Agrega un estudiante nuevo a la primera carrera que esta disponible
    public void agregarEstudiante(Estudiante e) {
        if (universidad.getFacultades().isEmpty()) {
            System.out.println("Error: No hay facultades disponibles.");
            return;
        }
        
        if (universidad.getFacultades().get(0).getCarreras().isEmpty()) {
            System.out.println("Error: No hay carreras disponibles.");
            return;
        }
        
        // Verifica que no haya un estudiante con la misma matricula y lo agrega
        if (buscarEstudiantePorMatricula(e.getMatricula()) != null) {
            System.out.println("Error: Ya existe un estudiante con la matrícula " + e.getMatricula());
            return;
        }
        
        universidad.getFacultades().get(0).getCarreras().get(0).getEstudiantes().add(e);
        guardarXML();
        System.out.println("Estudiante agregado correctamente.");
    }

    // Elimina un estudiante por su matricula
    public boolean eliminarEstudiantePorMatricula(String matricula) {
        boolean eliminado = false;
        for (Facultad f : universidad.getFacultades()) {
            for (Carrera c : f.getCarreras()) {
                eliminado = eliminado || c.getEstudiantes().removeIf(est -> est.getMatricula().equals(matricula));
            }
        }
        if (eliminado) {
            guardarXML();
            System.out.println("Estudiante con matrícula " + matricula + " eliminado correctamente.");
        } else {
            System.out.println("No se encontró estudiante con matrícula " + matricula);
        }
        return eliminado;
    }

    // Busca un estudiante por matricula
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

    // Modifica los datos de un estudiante que existe actualizando los campos excepto el campo matricula
    public void modificarEstudiante(String matricula, Estudiante estudianteModificado) {
        for (Facultad f : universidad.getFacultades()) {
            for (Carrera c : f.getCarreras()) {
                for (Estudiante e : c.getEstudiantes()) {
                    if (e.getMatricula().equals(matricula)) {
                        e.setNombre(estudianteModificado.getNombre());
                        e.setEdad(estudianteModificado.getEdad());
                        e.setEmail(estudianteModificado.getEmail());
                        guardarXML();
                        System.out.println("Estudiante modificado correctamente.");
                        return;
                    }
                }
            }
        }
        System.out.println("No se encontró estudiante con matrícula " + matricula);
    }

    // Calcula el presupuesto con los valores por defecto
    public double calcularPresupuesto() {
        double presupuesto = universidad.calcularPresupuesto();
        guardarXML();
        return presupuesto;
    }

    // Calcula el presupuesto con valores personalizados
    public double calcularPresupuestoPersonalizado(double costeBase, double costeFacultad, double costeCarrera, double costeEstudiante) {
        double presupuesto = universidad.calcularPresupuesto(costeBase, costeFacultad, costeCarrera, costeEstudiante);
        guardarXML();
        return presupuesto;
    }

    // Obtiene la informacion de la univerdidad
    public String getInfoUniversidad() {
        return universidad.getInfoUniversidad();
    }

    // Actualiza los datos de la universidad
    public void actualizarDatosUniversidad(String nombre, String direccion) {
        universidad.setNombre(nombre);
        universidad.setDireccion(direccion);
        guardarXML();
    }
}