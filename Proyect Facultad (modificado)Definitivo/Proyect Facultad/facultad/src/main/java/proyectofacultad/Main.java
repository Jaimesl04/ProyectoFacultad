package proyectofacultad;

import Controlador.UniversidadControlador;

/*
 * Punto de entrada del programa
 * Crea la instancia del menu y se llama para mostrarla
 */

public class Main {
    public static void main(String[] args) {
        // Crea una instancia del controlador principal de la universidad
        UniversidadControlador UC = new UniversidadControlador();
        UC.controlarVista();
    }
}