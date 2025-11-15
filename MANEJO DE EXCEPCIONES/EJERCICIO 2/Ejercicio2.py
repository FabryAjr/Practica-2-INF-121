class NumeroInvalidoException(Exception):
    pass

class Calculadora:

    @staticmethod
    def Sumar(a, b):
        return a + b

    @staticmethod
    def Restar(a, b):
        return a - b

    @staticmethod
    def Multiplicar(a, b):
        return a * b

    @staticmethod
    def Dividir(a, b):
        if b == 0:
            raise ArithmeticException("No se puede dividir entre cero")
        return a / b

    @staticmethod
    def ConvertirAEntero(s):
        try:
            return int(s)
        except ValueError:
            raise NumeroInvalidoException(
                f"El valor '{s}' no es un número válido"
            )


def Main():
    try:
        print("Suma:", Calculadora.Sumar(5, 3))
        print("Resta:", Calculadora.Restar(10, 4))
        print("Multiplicación:", Calculadora.Multiplicar(6, 7))
        print("División:", Calculadora.Dividir(20, 5))
        print("División por cero:", Calculadora.Dividir(10, 0))

    except Exception as e:
        print("Error:", e)

    try:
        print("Convertir número:", Calculadora.ConvertirAEntero("123"))
        print("Convertir texto:", Calculadora.ConvertirAEntero("abc"))

    except NumeroInvalidoException as e:
        print("Error de conversión:", e)


Main()
