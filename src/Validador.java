import java.util.Map;

public class Validador {

    /**
     * Valida una linea de producto.
     * Formato esperado:
     * id;nombre;precio
     */
    public static boolean validarProducto(String linea, int numeroLinea) {

        String[] datos = linea.split(";", -1);

        if (datos.length != 3) {
            System.err.println("Producto - linea " + numeroLinea
                    + ": formato invalido. Se esperaba id;nombre;precio.");
            return false;
        }

        try {
            int id = Integer.parseInt(datos[0].trim());
            String nombre = datos[1].trim();
            double precio = Double.parseDouble(datos[2].trim());

            if (id <= 0) {
                System.err.println("Producto - linea " + numeroLinea
                        + ": el ID debe ser positivo.");
                return false;
            }

            if (nombre.isEmpty()) {
                System.err.println("Producto - linea " + numeroLinea
                        + ": el nombre no puede estar vacio.");
                return false;
            }

            if (precio < 0) {
                System.err.println("Producto - linea " + numeroLinea
                        + ": el precio no puede ser negativo.");
                return false;
            }

        } catch (NumberFormatException e) {
            System.err.println("Producto - linea " + numeroLinea
                    + ": ID o precio no tiene un formato numerico valido.");
            return false;
        }

        return true;
    }

    /**
     * Valida una linea de vendedor.
     * Formato esperado:
     * tipoDocumento;documento;nombres;apellidos
     */
    public static boolean validarVendedor(String linea, int numeroLinea) {

        String[] datos = linea.split(";", -1);

        if (datos.length != 4) {
            System.err.println("Vendedor - linea " + numeroLinea
                    + ": formato invalido. Se esperaba tipo;documento;nombres;apellidos.");
            return false;
        }

        try {
            String tipoDocumento = datos[0].trim();
            long documento = Long.parseLong(datos[1].trim());
            String nombres = datos[2].trim();
            String apellidos = datos[3].trim();

            if (tipoDocumento.isEmpty()) {
                System.err.println("Vendedor - linea " + numeroLinea
                        + ": el tipo de documento esta vacio.");
                return false;
            }

            if (documento <= 0) {
                System.err.println("Vendedor - linea " + numeroLinea
                        + ": el documento debe ser positivo.");
                return false;
            }

            if (nombres.isEmpty() || apellidos.isEmpty()) {
                System.err.println("Vendedor - linea " + numeroLinea
                        + ": nombres y apellidos son obligatorios.");
                return false;
            }

        } catch (NumberFormatException e) {
            System.err.println("Vendedor - linea " + numeroLinea
                    + ": el documento no tiene un formato numerico valido.");
            return false;
        }

        return true;
    }

    /**
     * Valida una linea de venta.
     * Formato esperado:
     * idProducto;cantidad
     *
     * Tambien acepta el ; final utilizado por GenerateInfoFiles:
     * idProducto;cantidad;
     */
    public static boolean validarVenta(String linea, int numeroLinea,
                                       Map<Integer, Producto> productos) {

        String[] datos = linea.split(";", -1);

        if (datos.length == 3 && !datos[2].trim().isEmpty()) {
            System.err.println("Venta - linea " + numeroLinea
                    + ": formato invalido.");
            return false;
        }

        if (datos.length != 2 && datos.length != 3) {
            System.err.println("Venta - linea " + numeroLinea
                    + ": formato invalido. Se esperaba idProducto;cantidad.");
            return false;
        }

        try {
            int idProducto = Integer.parseInt(datos[0].trim());
            int cantidad = Integer.parseInt(datos[1].trim());

            if (idProducto <= 0) {
                System.err.println("Venta - linea " + numeroLinea
                        + ": el ID del producto debe ser positivo.");
                return false;
            }

            if (cantidad < 0) {
                System.err.println("Venta - linea " + numeroLinea
                        + ": la cantidad no puede ser negativa.");
                return false;
            }

            if (!productos.containsKey(idProducto)) {
                System.err.println("Venta - linea " + numeroLinea
                        + ": el producto con ID " + idProducto
                        + " no existe.");
                return false;
            }

        } catch (NumberFormatException e) {
            System.err.println("Venta - linea " + numeroLinea
                    + ": ID de producto o cantidad no tiene formato numerico valido.");
            return false;
        }

        return true;
    }

    /**
     * Verifica que el documento del archivo de ventas corresponda
     * a un vendedor registrado en vendedores.txt.
     */
    public static boolean validarVendedorRegistrado(long documento,
                                                    Map<Long, Vendedor> vendedores,
                                                    String nombreArchivo) {

        if (!vendedores.containsKey(documento)) {
            System.err.println("Archivo " + nombreArchivo
                    + ": el vendedor con documento " + documento
                    + " no existe en vendedores.txt.");
            return false;
        }

        return true;
    }
}
