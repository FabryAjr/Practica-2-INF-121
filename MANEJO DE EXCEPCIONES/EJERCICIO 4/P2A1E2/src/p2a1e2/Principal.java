package p2a1e2;

class ProductoNoEncontradoException extends Exception {
    public ProductoNoEncontradoException(String msg) {
        super(msg);
    }
}

class StockInsuficienteException extends Exception {
    public StockInsuficienteException(String msg) {
        super(msg);
    }
}

class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String codigo, String nombre, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String GetCodigo() { return codigo; }
    public String GetNombre() { return nombre; }
    public double GetPrecio() { return precio; }
    public int GetStock() { return stock; }

    public void SetStock(int stock) {
        this.stock = stock;
    }
}

class Inventario {
    private Producto[] productos;
    private int contador;

    public Inventario(int tamano) {
        productos = new Producto[tamano];
        contador = 0;
    }

    public void AgregarProducto(Producto p) throws Exception {
        // Código repetido
        for (int i = 0; i < contador; i++) {
            if (productos[i].GetCodigo().equals(p.GetCodigo())) {
                throw new Exception("El código del producto ya existe");
            }
        }

        // Precio y stock válidos
        if (p.GetPrecio() < 0 || p.GetStock() < 0) {
            throw new Exception("Precio o stock no pueden ser negativos");
        }

        productos[contador++] = p;
    }

    public Producto BuscarProducto(String codigo) throws ProductoNoEncontradoException {
        for (int i = 0; i < contador; i++) {
            if (productos[i].GetCodigo().equals(codigo)) {
                return productos[i];
            }
        }

        throw new ProductoNoEncontradoException("Producto con código " + codigo + " no encontrado");
    }

    public void VenderProducto(String codigo, int cantidad)
            throws ProductoNoEncontradoException, StockInsuficienteException {

        Producto p = BuscarProducto(codigo);

        if (p.GetStock() < cantidad) {
            throw new StockInsuficienteException("Stock insuficiente para vender");
        }

        p.SetStock(p.GetStock() - cantidad);
    }
}

public class Principal {
    public static void main(String[] args) {
        try {
            Inventario inv = new Inventario(10);

            inv.AgregarProducto(new Producto("P001", "Laptop", 4500, 5));
            inv.AgregarProducto(new Producto("P002", "Mouse", 40, 20));

            // Buscar
            System.out.println("Buscando Laptop: " + inv.BuscarProducto("P001").GetNombre());

            // Vender
            inv.VenderProducto("P001", 2);
            System.out.println("Venta realizada. Stock restante: " + inv.BuscarProducto("P001").GetStock());

            // Error por stock
            inv.VenderProducto("P001", 10);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
