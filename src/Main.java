public class Main {
    public static void main(String[] args) {
        System.out.println("INICIANDO SISTEMA");

        try {
            // 1. Generar los archivos planos de prueba (.csv)
            System.out.println("\nGenerando archivos");
            GenerateInfoFiles.createProductsFile(15);
            GenerateInfoFiles.createSalesManInfoFile(10);
            System.out.println("Archivos 'productos.csv' y 'vendedores.csv' generados con éxito.");

            // 2. Instanciar objetos de la clase Producto
            Producto producto1 = new Producto(1, "Martillo", 35000);
            Producto producto2 = new Producto(2, "Taladro", 250000);
            System.out.println("Productos instanciados en memoria.");

            // 3. Instanciar objetos de la clase Vendedor
            System.out.println("\n Registrando Vendedores");
            Vendedor vendedor1 = new Vendedor("CC", 1001, "Juan", "Perez");
            System.out.println("Vendedor: " + vendedor1.getNombreCompleto() + " (Doc: 1001)");

            // 4. Interconectar todo creando un registro de Venta
            System.out.println("\n Registrando una Venta");
            // Parámetros de Venta: (long documentoVendedor, int idProducto, int cantidad)
            // Usamos la 'L' al final de 1001 para indicar que es un tipo 'long' como pide tu clase Venta
            Venta venta1 = new Venta(1001L, 2, 5); 
            System.out.println("Venta registrada: El vendedor 1001 vendió 5 unidades del producto 2.");

            System.out.println("\n EJECUCIÓN FINALIZADA");

        } catch (Exception e) {
            System.err.println("\n Ocurrió un problema durante la ejecución: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
