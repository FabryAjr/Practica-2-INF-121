package ej14;

import java.util.ArrayList;

class Empleado {
    private String nombre;
    private String puesto;
    private double salario;

    public Empleado(String nombre, String puesto, double salario) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public String toString() {
        return "Empleado: " + nombre +
               " | Puesto: " + puesto +
               " | Salario: " + salario;
    }
}

class Empresa {
    private String nombre;
    private ArrayList<Empleado> empleados;

    public Empresa(String nombre) {
        this.nombre = nombre;
        empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado e) {
        empleados.add(e);
    }

    public void mostrarEmpresa() {
        System.out.println("=== Empresa: " + nombre + " ===");
        System.out.println("Empleados:");
        for (Empleado e : empleados) {
            System.out.println(e);
        }
        System.out.println();
    }

    public Empleado buscarEmpleado(String nombre) {
        for (Empleado e : empleados) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }

    public boolean eliminarEmpleado(String nombre) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getNombre().equalsIgnoreCase(nombre)) {
                empleados.remove(i);
                return true;
            }
        }
        return false;
    }

    public double promedioSalarial() {
        if (empleados.isEmpty()) return 0;

        double suma = 0;
        for (Empleado e : empleados) {
            suma += e.getSalario();
        }
        return suma / empleados.size();
    }

    public void listarEmpleadosSalarioMayor(double valor) {
        System.out.println("Empleados con salario mayor a " + valor + ":");
        for (Empleado e : empleados) {
            if (e.getSalario() > valor) {
                System.out.println(e);
            }
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {

        Empresa emp = new Empresa("Tech Innovators");

        emp.agregarEmpleado(new Empleado("Juan Perez", "Ingeniero", 3500));
        emp.agregarEmpleado(new Empleado("Maria Lopez", "Analista", 2800));
        emp.agregarEmpleado(new Empleado("Carlos Ramos", "Gerente", 5000));
        emp.agregarEmpleado(new Empleado("Ana Torres", "Programadora", 3200));

        emp.mostrarEmpresa();

        Empleado encontrado = emp.buscarEmpleado("Ana Torres");
        if (encontrado != null)
            System.out.println("Empleado encontrado: " + encontrado);
        else
            System.out.println("No existe ese empleado.");
        System.out.println();

        emp.eliminarEmpleado("Maria Lopez");

        System.out.println("Después de eliminar a Maria Lopez:");
        emp.mostrarEmpresa();

        System.out.println("Promedio salarial: " + emp.promedioSalarial());
        System.out.println();

        emp.listarEmpleadosSalarioMayor(3000);
    }
}

