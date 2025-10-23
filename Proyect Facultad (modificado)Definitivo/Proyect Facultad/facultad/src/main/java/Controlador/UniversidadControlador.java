package Controlador;

import Vista.MenuVista;
import Modelo.UniversidadControl;
import Modelo.Estudiante;

public class UniversidadControlador {
    private MenuVista vista;
    private UniversidadControl modelo;

    public UniversidadControlador() {
        this.vista = new MenuVista();
        this.modelo = new UniversidadControl();
    }

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

    private void listarEstudiantes() {
        String estructura = modelo.obtenerEstructuraCompleta();
        vista.mostrarEstructuraCompleta(estructura);
    }

    private void agregarEstudiante() {
        Estudiante nuevoEstudiante = vista.pedirDatosEstudiante();
        modelo.agregarEstudiante(nuevoEstudiante);
        vista.mostrarMensaje("Estudiante añadido correctamente.");
    }

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

    private void eliminarEstudiante() {
        String matricula = vista.pedirMatricula();
        boolean eliminado = modelo.eliminarEstudiantePorMatricula(matricula);
        if (eliminado) {
            vista.mostrarMensaje("Estudiante eliminado correctamente.");
        } else {
            vista.mostrarMensaje("Estudiante no encontrado.");
        }
    }

    private void mostrarInfoUniversidad() {
        String info = modelo.getInfoUniversidad();
        vista.mostrarInfoUniversidad(info);
    }

    private void calcularPresupuesto() {
        double presupuesto = modelo.calcularPresupuesto();
        vista.mostrarPresupuesto(presupuesto);
    }

    private void calcularPresupuestoPersonalizado() {
        double[] parametros = vista.pedirParametrosPresupuesto();
        double presupuesto = modelo.calcularPresupuestoPersonalizado(
            parametros[0], parametros[1], parametros[2], parametros[3]
        );
        vista.mostrarPresupuesto(presupuesto);
    }

    private void modificarDatosUniversidad() {
        String[] datos = vista.pedirDatosUniversidad();
        modelo.actualizarDatosUniversidad(datos[0], datos[1]);
        vista.mostrarMensaje("Datos de la universidad actualizados correctamente.");
    }
}