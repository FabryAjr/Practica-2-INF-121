package p2a1e1;

class NumeroInvalidoException extends Exception {
    public NumeroInvalidoException(String msg) {
        super(msg);
    }
}

class Calculadora {

    public static int Sumar(int a, int b) {
        return a + b;
    }

    public static int Restar(int a, int b) {
        return a - b;
    }

    public static int Multiplicar(int a, int b) {
        return a * b;
    }

    public static int Dividir(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("No se puede dividir entre cero");
        }
        return a / b;
    }

    public static int ConvertirAEntero(String s) throws NumeroInvalidoException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new NumeroInvalidoException("El valor '" + s + "' no es un numero valido");
        }
    }
}

public class Principal {
    public static void main(String[] args) {
        try {
            System.out.println("Suma: " + Calculadora.Sumar(5, 3));
            System.out.println("Resta: " + Calculadora.Restar(10, 4));
            System.out.println("Multiplicacion: " + Calculadora.Multiplicar(6, 7));
            System.out.println("Division: " + Calculadora.Dividir(20, 5));

            System.out.println("División por cero: " + Calculadora.Dividir(10, 0));

        } catch (ArithmeticException e) {
            System.out.println("Error aritmetico: " + e.getMessage());
        }

        try {
            System.out.println("Convertir numero: " + Calculadora.ConvertirAEntero("123"));

            System.out.println("Convertir texto: " + Calculadora.ConvertirAEntero("abc"));

        } catch (NumeroInvalidoException e) {
            System.out.println("Error de conversion: " + e.getMessage());
        }
    }
}
