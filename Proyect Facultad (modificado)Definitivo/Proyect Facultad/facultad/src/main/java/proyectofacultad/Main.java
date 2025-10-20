package proyectofacultad;

import Controlador.UniversidadControlador;

/**
 * Punton de entrada del programa
 * Creo la instancia del menu y la llamo para mostrarla
 */

public class Main {
    public static void main(String[] args) {
        UniversidadControlador UC = new UniversidadControlador();
        UC.controlarVista();
    }
}
