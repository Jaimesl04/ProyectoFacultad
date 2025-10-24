package Controlador;

import Vista.MenuVista;
import Modelo.UniversidadControl;
import Modelo.Estudiante;

/*
 * Clase controladora que coordina la interaccion entre la vista y el modelo y gestiona
 * el flujo de la informacion
 */

public class UniversidadControlador {
    private MenuVista vista;
    private UniversidadControl modelo;

    public UniversidadControlador() {
        this.vista = new MenuVista();
        this.modelo = new UniversidadControl();
    }

    // Muestra el menu principal y lee la opcion del usuario
    public void controlarVista() {
        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerOpcion();

            switch (opcion) {
                case 1 -> listarEstudiantes();
                case 2 -> agregarEstudiante();
                case 3 -> modificarEstudiante();
                case 4 -> eliminarEstudiante();
                case 5 -> mostrarInfoUniversidad();
                case 6 -> calcularPresupuesto();
                case 7 -> calcularPresupuestoPersonalizado();
                case 8 -> modificarDatosUniversidad();
                case 0 -> vista.mostrarMensaje("Saliendo...");
                default -> vista.mostrarMensaje("Opción no válida");
            }

        } while (opcion != 0);
    }

    // Obtiene la estructura de facultades, carreras y estudiantes
    private void listarEstudiantes() {
        String estructura = modelo.obtenerEstructuraCompleta();
        vista.mostrarEstructuraCompleta(estructura);
    }

    // Solicita los datos para el nuevo estudiante a traves de la vista y lo agrega
    private void agregarEstudiante() {
        Estudiante nuevoEstudiante = vista.pedirDatosEstudiante();
        modelo.agregarEstudiante(nuevoEstudiante);
        vista.mostrarMensaje("Estudiante añadido correctamente.");
    }

    // Solicita la matricula del estudiante que quieres modificar y si existe solicita los datos nuevos y los actualiza excepto la propia matricula
    private void modificarEstudiante() {
        String matricula = vista.pedirMatricula();
        Estudiante estudianteActual = modelo.buscarEstudiantePorMatricula(matricula);
        
        if (estudianteActual != null) {
            Estudiante estudianteModificado = vista.pedirDatosModificacion(estudianteActual);
            modelo.modificarEstudiante(matricula, estudianteModificado);
            vista.mostrarMensaje("Estudiante modificado correctamente.");
        } else {
            vista.mostrarMensaje("Estudiante no encontrado.");
        }
    }

    // Solicita la matricula del estudiante que quieres eliminar, lo intenta eliminar y obtiene el resultado
    private void eliminarEstudiante() {
        String matricula = vista.pedirMatricula();
        boolean eliminado = modelo.eliminarEstudiantePorMatricula(matricula);
        if (eliminado) {
            vista.mostrarMensaje("Estudiante eliminado correctamente.");
        } else {
            vista.mostrarMensaje("Estudiante no encontrado.");
        }
    }

    // Muestra la informacion de la universidad
    private void mostrarInfoUniversidad() {
        String info = modelo.getInfoUniversidad();
        vista.mostrarInfoUniversidad(info);
    }

    // Calcula el presupuesto de la universidad con valores predefinidos
    private void calcularPresupuesto() {
        double presupuesto = modelo.calcularPresupuesto();
        vista.mostrarPresupuesto(presupuesto);
    }

    // Calcula el presupuesto con los valores personalizados
    private void calcularPresupuestoPersonalizado() {
        double[] parametros = vista.pedirParametrosPresupuesto();
        double presupuesto = modelo.calcularPresupuestoPersonalizado(
            parametros[0], parametros[1], parametros[2], parametros[3]
        );
        vista.mostrarPresupuesto(presupuesto);
    }

    // Modifica los datos basicos de la universidada que son nombre y direccion
    private void modificarDatosUniversidad() {
        String[] datos = vista.pedirDatosUniversidad();
        modelo.actualizarDatosUniversidad(datos[0], datos[1]);
        vista.mostrarMensaje("Datos de la universidad actualizados correctamente.");
    }
}