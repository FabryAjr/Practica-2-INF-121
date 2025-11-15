class ProductoNoEncontradoException(Exception):
    pass

class StockInsuficienteException(Exception):
    pass

class Producto:
    def __init__(self, codigo, nombre, precio, stock):
        self.codigo = codigo
        self.nombre = nombre
        self.precio = precio
        self.stock = stock

    def GetCodigo(self):
        return self.codigo

    def GetNombre(self):
        return self.nombre

    def GetPrecio(self):
        return self.precio

    def GetStock(self):
        return self.stock

    def SetStock(self, stock):
        self.stock = stock


class Inventario:
    def __init__(self, tamano):
        self.productos = []
        self.tamano = tamano

    def AgregarProducto(self, p):
        # Código repetido
        for prod in self.productos:
            if prod.GetCodigo() == p.GetCodigo():
                raise Exception("El código del producto ya existe")

        # Validación
        if p.GetPrecio() < 0 or p.GetStock() < 0:
            raise Exception("Precio o stock no pueden ser negativos")

        if len(self.productos) >= self.tamano:
            raise Exception("Inventario lleno")

        self.productos.append(p)

    def BuscarProducto(self, codigo):
        for prod in self.productos:
            if prod.GetCodigo() == codigo:
                return prod
        raise ProductoNoEncontradoException(f"Producto con código {codigo} no encontrado")

    def VenderProducto(self, codigo, cantidad):
        producto = self.BuscarProducto(codigo)

        if producto.GetStock() < cantidad:
            raise StockInsuficienteException("Stock insuficiente para vender")

        producto.SetStock(producto.GetStock() - cantidad)


def Main():
    try:
        inv = Inventario(10)

        inv.AgregarProducto(Producto("P001", "Laptop", 4500, 5))
        inv.AgregarProducto(Producto("P002", "Mouse", 40, 20))

        print("Buscando:", inv.BuscarProducto("P001").GetNombre())

        inv.VenderProducto("P001", 2)
        print("Stock luego de venta:", inv.BuscarProducto("P001").GetStock())

    
        inv.VenderProducto("P001", 20)

    except Exception as e:
        print("ERROR:", e)

Main()
