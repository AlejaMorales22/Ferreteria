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

            // 5. Probar GeneradorReportes con datos de ejemplo
            System.out.println("\n Generando reportes");

            Producto producto3 = new Producto(3, "Destornillador", 15000);
            Vendedor vendedor2 = new Vendedor("CC", 1002, "Maria", "Gomez");

            Venta venta2 = new Venta(1001L, 1, 3);
            Venta venta3 = new Venta(1002L, 2, 2);
            Venta venta4 = new Venta(1002L, 3, 4);

            java.util.List<Venta> ventas = java.util.Arrays.asList(venta1, venta2, venta3, venta4);

            java.util.Map<Long, Vendedor> vendedoresPorDocumento = new java.util.HashMap<>();
            vendedoresPorDocumento.put(1001L, vendedor1);
            vendedoresPorDocumento.put(1002L, vendedor2);

            java.util.Map<Integer, Producto> productosPorId = new java.util.HashMap<>();
            productosPorId.put(1, producto1);
            productosPorId.put(2, producto2);
            productosPorId.put(3, producto3);

            GeneradorReportes.calcularTotales(ventas, vendedoresPorDocumento, productosPorId);
            GeneradorReportes.generarReporteVendedores(java.util.Arrays.asList(vendedor1, vendedor2));
            GeneradorReportes.generarReporteProductos(java.util.Arrays.asList(producto1, producto2, producto3));

            System.out.println("Reportes generados en la carpeta 'datos'.");
            System.out.println("\n EJECUCIÓN FINALIZADA");

        } catch (Exception e) {
            System.err.println("\n Ocurrió un problema durante la ejecución: " + e.getMessage());
            e.printStackTrace();
        }
    }
}