class FondosInsuficientesException(Exception):
    pass

class CuentaBancaria:
    def __init__(self, numeroCuenta, titular, saldo):
        self.numeroCuenta = numeroCuenta
        self.titular = titular
        self.saldo = saldo

    def Depositar(self, monto):
        if monto <= 0:
            raise ValueError("El monto a depositar debe ser positivo.")
        self.saldo += monto

    def Retirar(self, monto):
        if monto > self.saldo:
            raise FondosInsuficientesException("Fondos insuficientes para realizar el retiro.")
        self.saldo -= monto

    def MostrarInfo(self):
        print(f"Cuenta: {self.numeroCuenta}")
        print(f"Titular: {self.titular}")
        print(f"Saldo: {self.saldo}")


def Main():
    cuenta = CuentaBancaria("143245", "Johnny Felipez", 1000)

    cuenta.MostrarInfo()

    try:
        cuenta.Depositar(500)
        print("\nDepósito realizado correctamente.")
    except Exception as e:
        print("Error en depósito:", e)

    try:
        cuenta.Depositar(-200)
    except Exception as e:
        print("Error en depósito:", e)

    try:
        cuenta.Retirar(300)
        print("\nRetiro realizado correctamente.")
    except Exception as e:
        print("Error en retiro:", e)

    try:
        cuenta.Retirar(5000)
    except Exception as e:
        print("Error en retiro:", e)

    print("\nEstado final de la cuenta:")
    cuenta.MostrarInfo()

Main()
