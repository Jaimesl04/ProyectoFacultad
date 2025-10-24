package Modelo;

import jakarta.xml.bind.annotation.*;

/*
 * Representa un estudiante con sus datos personales y academicos
 */

@XmlRootElement(name = "estudiante")
public class Estudiante {
    private String nombre;
    private int edad;
    private String matricula;
    private String email;

    public Estudiante() {}

    public Estudiante(String nombre, int edad, String matricula, String email) {
        this.nombre = nombre;
        this.edad = edad;
        this.matricula = matricula;
        this.email = email;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return String.format("Nombre: %s, Edad: %d, Matrícula: %s, Email: %s",
                nombre, edad, matricula, email);
    }
}