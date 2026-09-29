import java.util.Map;

/**
 * Valida las lineas de los archivos de entrada antes de procesarlas.
 * Detecta formatos incorrectos, valores no numericos, IDs de producto que
 * no existen, precios y cantidades negativas, y archivos de ventas de
 * vendedores no registrados. Cada error se informa por consola.
 *
 * Materia: Conceptos fundamentales de programacion
 * Grupo GB02 - G2-TypeNull
 * Integrantes:
 * July Alejandra Morales Muñoz
 * Daniel Jose Riojas Gutierrez
 * David Insignares Vega
 * Juan Andres Leguizamon Suaza
 */
public class Validador {

    /**
     * Valida una linea del archivo de productos.
     * Formato esperado: id;nombre;precio
     *
     * @param linea       linea leida del archivo.
     * @param numeroLinea numero de la linea, usado en el mensaje de error.
     * @return true si la linea es valida; false en caso contrario.
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
     * Valida una linea del archivo de vendedores.
     * Formato esperado: tipoDocumento;documento;nombres;apellidos
     *
     * @param linea       linea leida del archivo.
     * @param numeroLinea numero de la linea, usado en el mensaje de error.
     * @return true si la linea es valida; false en caso contrario.
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
     * Valida una linea de un archivo de ventas.
     * Formato esperado: idProducto;cantidad
     * Tambien acepta el ; final que escribe GenerateInfoFiles:
     * idProducto;cantidad;
     *
     * @param linea       linea leida del archivo.
     * @param numeroLinea numero de la linea, usado en el mensaje de error.
     * @param productos   productos registrados, para verificar que el ID
     *                    exista.
     * @return true si la linea es valida; false en caso contrario.
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
     * Verifica que el documento de un archivo de ventas corresponda a un
     * vendedor registrado en vendedores.txt.
     *
     * @param documento     documento leido del encabezado del archivo.
     * @param vendedores    vendedores registrados, indexados por documento.
     * @param nombreArchivo nombre del archivo, usado en el mensaje de error.
     * @return true si el vendedor existe; false en caso contrario.
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
