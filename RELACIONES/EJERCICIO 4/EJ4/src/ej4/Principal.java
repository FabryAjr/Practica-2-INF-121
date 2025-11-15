package ej4;


import java.util.ArrayList;

class Empleado {
    private String nombre;
    private String cargo;
    private double sueldo;

    public Empleado(String nombre, String cargo, double sueldo) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.sueldo = sueldo;
    }

    public String GetNombre() { return nombre; }
    public double GetSueldo() { return sueldo; }
    public void SetSueldo(double sueldo) { this.sueldo = sueldo; }

    @Override
    public String toString() {
        return nombre + " - " + cargo + " - $" + sueldo;
    }
}

class Departamento {
    private String nombre;
    private String area;
    private ArrayList<Empleado> empleados;

    public Departamento(String nombre, String area) {
        this.nombre = nombre;
        this.area = area;
        this.empleados = new ArrayList<>();
    }

    public void AgregarEmpleado(Empleado e) {
        empleados.add(e);
    }

    public void MostrarEmpleados() {
        System.out.println("\nDepartamento: " + nombre);
        if (empleados.isEmpty()) {
            System.out.println("No tiene empleados.");
        } else {
            for (Empleado e : empleados) {
                System.out.println(" - " + e);
            }
        }
    }

    public void CambioSalario(double porcentaje) {
        for (Empleado e : empleados) {
            double nuevo = e.GetSueldo() + (e.GetSueldo() * porcentaje / 100);
            e.SetSueldo(nuevo);
        }
    }

    public boolean TieneEmpleado(Empleado emp) {
        return empleados.contains(emp);
    }

    public void MoverEmpleadosA(Departamento otro) {
        for (Empleado e : empleados) {
            otro.AgregarEmpleado(e);
        }
        empleados.clear();
    }
}

public class Principal {
    public static void main(String[] args) {

        // a) Instanciar departamentos
        Departamento d1 = new Departamento("Informatica", "Tecnología");
        Departamento d2 = new Departamento("Contabilidad", "Finanzas");

        // 5 empleados en d1
        d1.AgregarEmpleado(new Empleado("Ana", "Analista", 3500));
        d1.AgregarEmpleado(new Empleado("Luis", "Programador", 4200));
        d1.AgregarEmpleado(new Empleado("Maria", "Tester", 3000));
        d1.AgregarEmpleado(new Empleado("Jorge", "DevOps", 5000));
        d1.AgregarEmpleado(new Empleado("Sofia", "Scrum Master", 4500));

        // d2 sin empleados
        d2.MostrarEmpleados();

        // b) Mostrar empleados
        d1.MostrarEmpleados();

        // c) Cambiar salario
        d1.CambioSalario(10); // 10% aumento
        System.out.println("\nSalarios actualizados:");
        d1.MostrarEmpleados();

        // d) Verificar si un empleado de d1 está en d2
        Empleado prueba = new Empleado("Ana", "Analista", 3500); // igual pero OBJETO diferente
        System.out.println(
            "\n¿Empleado 'Ana' está en Departamento 2? => " +
            d2.TieneEmpleado(prueba)
        );

        // e) Mover empleados
        d1.MoverEmpleadosA(d2);

        System.out.println("\nDespués de mover empleados:");
        d1.MostrarEmpleados();
        d2.MostrarEmpleados();
    }
}
