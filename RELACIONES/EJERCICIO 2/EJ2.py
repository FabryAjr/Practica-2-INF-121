class Empleado:
    def __init__(self, nombre, cargo, sueldo):
        self.nombre = nombre
        self.cargo = cargo
        self.sueldo = sueldo

    def GetNombre(self):
        return self.nombre

    def GetSueldo(self):
        return self.sueldo

    def SetSueldo(self, sueldo):
        self.sueldo = sueldo

    def __str__(self):
        return f"{self.nombre} - {self.cargo} - ${self.sueldo}"


class Departamento:
    def __init__(self, nombre, area):
        self.nombre = nombre
        self.area = area
        self.empleados = []

    def AgregarEmpleado(self, e):
        self.empleados.append(e)

    def MostrarEmpleados(self):
        print(f"\nDepartamento: {self.nombre}")
        if not self.empleados:
            print("No tiene empleados.")
        else:
            for e in self.empleados:
                print(" -", e)

    def CambioSalario(self, porcentaje):
        for e in self.empleados:
            nuevo = e.GetSueldo() + (e.GetSueldo() * porcentaje / 100)
            e.SetSueldo(nuevo)

    def TieneEmpleado(self, emp):
        return emp in self.empleados

    def MoverEmpleadosA(self, otro):
        for e in self.empleados:
            otro.AgregarEmpleado(e)
        self.empleados.clear()


def Main():

    d1 = Departamento("Informatica", "Tecnología")
    d2 = Departamento("Contabilidad", "Finanzas")

    d1.AgregarEmpleado(Empleado("Ana", "Analista", 3500))
    d1.AgregarEmpleado(Empleado("Luis", "Programador", 4200))
    d1.AgregarEmpleado(Empleado("Maria", "Tester", 3000))
    d1.AgregarEmpleado(Empleado("Jorge", "DevOps", 5000))
    d1.AgregarEmpleado(Empleado("Sofia", "Scrum Master", 4500))

    d2.MostrarEmpleados()
    d1.MostrarEmpleados()

    d1.CambioSalario(10)
    print("\nSalarios actualizados:")
    d1.MostrarEmpleados()

    prueba = d1.empleados[0]
    print("\n¿Ana está en d2? =>", d2.TieneEmpleado(prueba))

    d1.MoverEmpleadosA(d2)

    print("\nDespués de mover empleados:")
    d1.MostrarEmpleados()
    d2.MostrarEmpleados()


Main()
