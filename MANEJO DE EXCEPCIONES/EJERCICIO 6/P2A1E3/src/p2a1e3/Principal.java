package p2a1e3;

class FondosInsuficientesException extends Exception {
    public FondosInsuficientesException(String msg) {
        super(msg);
    }
}

class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;

    public CuentaBancaria(String numeroCuenta, String titular, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    public void Depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo.");
        }
        saldo += monto;
    }

    public void Retirar(double monto) throws FondosInsuficientesException {
        if (monto > saldo) {
            throw new FondosInsuficientesException("Fondos insuficientes para realizar el retiro.");
        }
        saldo -= monto;
    }

    public void MostrarInfo() {
        System.out.println("Cuenta: " + numeroCuenta);
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: " + saldo);
    }
}

public class Principal {
    public static void main(String[] args) {

        CuentaBancaria cuenta = new CuentaBancaria("12345", "Juan Pérez", 1000);

        cuenta.MostrarInfo();

        try {
            cuenta.Depositar(500);
            System.out.println("\nDepósito realizado correctamente.");
        } catch (Exception e) {
            System.out.println("Error en depósito: " + e.getMessage());
        }

        try {
            cuenta.Depositar(-200);
        } catch (Exception e) {
            System.out.println("Error en depósito: " + e.getMessage());
        }

        try {
            cuenta.Retirar(300);
            System.out.println("\nRetiro realizado correctamente.");
        } catch (Exception e) {
            System.out.println("Error en retiro: " + e.getMessage());
        }

        try {
            cuenta.Retirar(5000);
        } catch (Exception e) {
            System.out.println("Error en retiro: " + e.getMessage());
        }

        System.out.println("\nEstado final de la cuenta:");
        cuenta.MostrarInfo();
    }
}
