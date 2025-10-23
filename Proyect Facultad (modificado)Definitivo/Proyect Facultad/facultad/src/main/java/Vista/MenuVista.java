package Vista;

import Modelo.Estudiante;
import java.util.List;
import java.util.Scanner;
import java.util.Locale;

public class MenuVista {
    private Scanner sc;

    public MenuVista() {
        sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);
    }

    public void mostrarMenu() {
        System.out.println("\n=== MENÚ UNIVERSITARIO ===");
        System.out.println("1. Leer Estudiantes");
        System.out.println("2. Añadir Estudiante");
        System.out.println("3. Modificar Estudiante");
        System.out.println("4. Borrar Estudiante");
        System.out.println("5. Información de la Universidad");
        System.out.println("6. Calcular Presupuesto");
        System.out.println("7. Calcular Presupuesto Personalizado");
        System.out.println("8. Modificar Datos Universidad");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public int leerOpcion() {
        return Integer.parseInt(sc.nextLine());
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(">>> " + mensaje);
    }

    public Estudiante pedirDatosEstudiante() {
        System.out.println("\n--- AGREGAR NUEVO ESTUDIANTE ---");
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
        System.out.println("\n--- MODIFICAR ESTUDIANTE ---");
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
        
        System.out.println("\n--- LISTA DE ESTUDIANTES ---");
        for (Estudiante e : estudiantes) {
            System.out.println("    " + e);
        }
    }

    public void mostrarEstructuraCompleta(String estructura) {
        System.out.println("\n--- ESTRUCTURA UNIVERSITARIA ---");
        if (estructura.contains("No hay datos")) {
            System.out.println("La universidad no tiene datos. Agrega facultades, carreras y estudiantes.");
        } else {
            System.out.println(estructura);
        }
    }

    public void mostrarInfoUniversidad(String info) {
        System.out.println("\n--- INFORMACIÓN DE LA UNIVERSIDAD ---");
        System.out.println(info);
    }

    public void mostrarPresupuesto(double presupuesto) {
        System.out.println("\n--- PRESUPUESTO UNIVERSITARIO ---");
        System.out.printf(Locale.US, "Presupuesto total calculado: %,.2f%n", presupuesto );
        System.out.println("Este presupuesto incluye:");
        System.out.println("- Coste base de mantenimiento");
        System.out.println("- Coste por facultad");
        System.out.println("- Coste por carrera");
        System.out.println("- Coste por estudiante");
    }

    public double[] pedirParametrosPresupuesto() {
        System.out.println("\n--- CÁLCULO PERSONALIZADO DE PRESUPUESTO ---");
        System.out.print("Coste base de la universidad (€): ");
        double costeBase = Double.parseDouble(sc.nextLine());
        System.out.print("Coste por facultad (€): ");
        double costeFacultad = Double.parseDouble(sc.nextLine());
        System.out.print("Coste por carrera (€): ");
        double costeCarrera = Double.parseDouble(sc.nextLine());
        System.out.print("Coste por estudiante (€): ");
        double costeEstudiante = Double.parseDouble(sc.nextLine());
        
        return new double[]{costeBase, costeFacultad, costeCarrera, costeEstudiante};
    }

    public String[] pedirDatosUniversidad() {
        System.out.println("\n--- MODIFICAR DATOS DE LA UNIVERSIDAD ---");
        System.out.print("Nuevo nombre de la universidad: ");
        String nombre = sc.nextLine();
        System.out.print("Nueva dirección: ");
        String direccion = sc.nextLine();
        
        return new String[]{nombre, direccion};
    }
}