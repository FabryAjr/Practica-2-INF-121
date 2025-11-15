package ej12;

import java.util.ArrayList;

class Doctor {
    private String nombre;
    private String especialidad;

    public Doctor(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public String toString() {
        return nombre + " (" + especialidad + ")";
    }
}

class Hospital {
    private String nombre;
    private ArrayList<Doctor> doctores;

    public Hospital(String nombre) {
        this.nombre = nombre;
        this.doctores = new ArrayList<>();
    }

    public void agregarDoctor(Doctor d) {
        doctores.add(d);
    }

    public void mostrarDoctores() {
        System.out.println("Hospital: " + nombre);
        System.out.println("Doctores asignados:");
        for (Doctor d : doctores) {
            System.out.println("- " + d);
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {

        Doctor d1 = new Doctor("Juan Perez", "Cardiología");
        Doctor d2 = new Doctor("María Flores", "Pediatría");
        Doctor d3 = new Doctor("Luis Ramos", "Neurología");

        Hospital h1 = new Hospital("Hospital Central");
        Hospital h2 = new Hospital("Clínica del Sur");

        h1.agregarDoctor(d1);
        h1.agregarDoctor(d2);

        h2.agregarDoctor(d2);
        h2.agregarDoctor(d3);

        h1.mostrarDoctores();
        h2.mostrarDoctores();
    }
}
