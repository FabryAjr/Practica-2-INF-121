package ej8;

import java.util.ArrayList;

class Persona {
    String nombre;
    int edad;
    String ci;

    public Persona(String nombre, int edad, String ci) {
        this.nombre = nombre;
        this.edad = edad;
        this.ci = ci;
    }
}

class Facultad {
    String nombre;
    String sigla;
    ArrayList<Bailarin> listaBailarines = new ArrayList<>();

    public Facultad(String nombre, String sigla) {
        this.nombre = nombre;
        this.sigla = sigla;
    }

    public void AgregarBailarin(Bailarin b) {
        listaBailarines.add(b);
    }
}

class Fraternidad {
    String nombre;
    Persona encargado;
    ArrayList<Bailarin> listaBailarines = new ArrayList<>();

    public Fraternidad(String nombre, Persona encargado) {
        this.nombre = nombre;
        this.encargado = encargado;
    }

    public void AgregarBailarin(Bailarin b) throws Exception {
        if (b.fraternidad != null && b.fraternidad != this) {
            throw new Exception("El bailarín ya está registrado en otra fraternidad.");
        }

        listaBailarines.add(b);
        b.fraternidad = this;
    }
}

class Bailarin {
    Persona persona;
    Facultad facultad;
    Fraternidad fraternidad;

    public Bailarin(Persona persona) {
        this.persona = persona;
    }

    public void AsignarFacultad(Facultad f) {
        this.facultad = f;
        f.AgregarBailarin(this);
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            Facultad f1 = new Facultad("Ingeniería", "FNI");
            Facultad f2 = new Facultad("Economía", "FCE");

            Persona enc1 = new Persona("Carlos Encargado", 40, "1111");
            Persona enc2 = new Persona("María Encargada", 38, "2222");

            Fraternidad frat1 = new Fraternidad("Caporales Central", enc1);
            Fraternidad frat2 = new Fraternidad("Tinkus San Simón", enc2);

            Bailarin b1 = new Bailarin(new Persona("Luis", 20, "3001"));
            Bailarin b2 = new Bailarin(new Persona("Ana", 22, "3002"));
            Bailarin b3 = new Bailarin(new Persona("Mario", 21, "3003"));
            Bailarin b4 = new Bailarin(new Persona("Paola", 19, "3004"));
            Bailarin b5 = new Bailarin(new Persona("Lucía", 20, "3005"));

            b1.AsignarFacultad(f1);
            b2.AsignarFacultad(f1);
            b3.AsignarFacultad(f2);
            b4.AsignarFacultad(f2);
            b5.AsignarFacultad(f1);

            // Asignación a fraternidad
            frat1.AgregarBailarin(b1);
            frat1.AgregarBailarin(b2);

            frat2.AgregarBailarin(b3);
            frat2.AgregarBailarin(b4);

            System.out.println("Fraternidad: " + frat1.nombre + " (Encargado: " + frat1.encargado.nombre + ")");
            for (Bailarin b : frat1.listaBailarines)
                System.out.println(" - " + b.persona.nombre + ", Edad: " + b.persona.edad +
                        ", Facultad: " + b.facultad.nombre);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}

