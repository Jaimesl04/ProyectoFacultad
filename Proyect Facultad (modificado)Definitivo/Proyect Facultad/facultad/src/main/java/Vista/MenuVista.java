package Vista;

import Modelo.Estudiante;
import java.util.List;
import java.util.Scanner;

public class MenuVista {
    private Scanner sc;

    public MenuVista() {
        sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("Menú Universitario");
        System.out.println("1. Leer Estudiantes");
        System.out.println("2. Añadir Estudiante");
        System.out.println("3. Modificar Estudiante");
        System.out.println("4. Borrar Estudiante");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public int leerOpcion() {
        return Integer.parseInt(sc.nextLine());
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public Estudiante pedirDatosEstudiante() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(sc.nextLine());
        System.out.print("Matrícula: ");
        String matricula = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        return new Estudiante(nombre, edad, matricula, email);
    }

    public String pedirMatricula() {
        System.out.print("Introduce matrícula del estudiante: ");
        return sc.nextLine();
    }

    public Estudiante pedirDatosModificacion(Estudiante estudianteActual) {
        System.out.println("Estudiante actual: " + estudianteActual);
        System.out.print("Nuevo nombre (actual: " + estudianteActual.getNombre() + "): ");
        String nombre = sc.nextLine();
        System.out.print("Nueva edad (actual: " + estudianteActual.getEdad() + "): ");
        int edad = Integer.parseInt(sc.nextLine());
        System.out.print("Nuevo email (actual: " + estudianteActual.getEmail() + "): ");
        String email = sc.nextLine();

        return new Estudiante(nombre, edad, estudianteActual.getMatricula(), email);
    }

    public void mostrarEstudiantes(List<Estudiante> estudiantes) {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes para mostrar.");
            return;
        }
        
        for (Estudiante e : estudiantes) {
            System.out.println("    " + e);
        }
    }

    public void mostrarEstructuraCompleta(String estructura) {
        System.out.println(estructura);
    }
}