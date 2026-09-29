import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Lee los archivos planos de entrada de la ferreteria (productos,
 * vendedores y ventas) y los convierte en objetos del modelo. Cada linea
 * se valida con {@link Validador} antes de usarse; las lineas invalidas se
 * descartan sin detener la lectura.
 *
 * Materia: Conceptos fundamentales de programacion
 * Grupo GB02 - G2-TypeNull
 * Integrantes:
 * July Alejandra Morales Muñoz
 * Daniel Jose Riojas Gutierrez
 * David Insignares Vega
 * Juan Andres Leguizamon Suaza
 */
public class LectorArchivos {

    /**
     * Lee el archivo de productos y los guarda en un mapa por su ID.
     *
     * @param archivo ruta del archivo de productos (id;nombre;precio).
     * @return mapa de productos validos, indexados por su identificador.
     * @throws IOException si ocurre un error al leer el archivo.
     */
    public static Map<Integer, Producto> leerProductos(Path archivo)
            throws IOException {

        Map<Integer, Producto> productos =
                new HashMap<Integer, Producto>();

        try (BufferedReader lector = Files.newBufferedReader(
                archivo, StandardCharsets.UTF_8)) {

            String linea;
            int numeroLinea = 0;

            while ((linea = lector.readLine()) != null) {

                numeroLinea++;

                if (linea.trim().isEmpty()) {
                    continue;
                }

                if (!Validador.validarProducto(linea, numeroLinea)) {
                    continue;
                }

                String[] datos = linea.split(";", -1);

                int id = Integer.parseInt(datos[0].trim());
                String nombre = datos[1].trim();
                double precio = Double.parseDouble(datos[2].trim());

                Producto producto =
                        new Producto(id, nombre, precio);

                productos.put(id, producto);
            }
        }

        return productos;
    }

    /**
     * Lee el archivo de vendedores y los guarda en un mapa por documento.
     *
     * @param archivo ruta del archivo de vendedores
     *                (tipo;documento;nombres;apellidos).
     * @return mapa de vendedores validos, indexados por su documento.
     * @throws IOException si ocurre un error al leer el archivo.
     */
    public static Map<Long, Vendedor> leerVendedores(Path archivo)
            throws IOException {

        Map<Long, Vendedor> vendedores =
                new HashMap<Long, Vendedor>();

        try (BufferedReader lector = Files.newBufferedReader(
                archivo, StandardCharsets.UTF_8)) {

            String linea;
            int numeroLinea = 0;

            while ((linea = lector.readLine()) != null) {

                numeroLinea++;

                if (linea.trim().isEmpty()) {
                    continue;
                }

                if (!Validador.validarVendedor(linea, numeroLinea)) {
                    continue;
                }

                String[] datos = linea.split(";", -1);

                String tipoDocumento = datos[0].trim();
                long documento = Long.parseLong(datos[1].trim());
                String nombres = datos[2].trim();
                String apellidos = datos[3].trim();

                Vendedor vendedor =
                        new Vendedor(
                                tipoDocumento,
                                documento,
                                nombres,
                                apellidos);

                vendedores.put(documento, vendedor);
            }
        }

        return vendedores;
    }

    /**
     * Lee todos los archivos vendedor_*.txt de la carpeta indicada. El
     * vendedor se identifica por la primera linea de cada archivo, por lo
     * que un mismo vendedor puede tener varios archivos de ventas.
     *
     * @param carpeta    carpeta donde estan los archivos de ventas.
     * @param vendedores vendedores registrados, indexados por documento.
     * @param productos  productos registrados, indexados por su ID.
     * @return lista con todas las ventas validas encontradas.
     * @throws IOException si ocurre un error al leer los archivos.
     */
    public static List<Venta> leerVentas(
            Path carpeta,
            Map<Long, Vendedor> vendedores,
            Map<Integer, Producto> productos)
            throws IOException {

        List<Venta> ventas = new ArrayList<Venta>();

        try (DirectoryStream<Path> archivos =
                     Files.newDirectoryStream(
                             carpeta,
                             "vendedor_*.txt")) {

            for (Path archivo : archivos) {

                try (BufferedReader lector =
                             Files.newBufferedReader(
                                     archivo,
                                     StandardCharsets.UTF_8)) {

                    String linea = lector.readLine();

                    if (linea == null) {
                        System.err.println(
                                "Archivo " + archivo.getFileName()
                                        + ": esta vacio.");
                        continue;
                    }

                    String[] datosVendedor =
                            linea.split(";", -1);

                    if (datosVendedor.length != 2) {
                        System.err.println(
                                "Archivo " + archivo.getFileName()
                                        + ": encabezado invalido.");
                        continue;
                    }

                    long documento;

                    try {
                        documento =
                                Long.parseLong(
                                        datosVendedor[1].trim());
                    } catch (NumberFormatException e) {

                        System.err.println(
                                "Archivo " + archivo.getFileName()
                                        + ": documento invalido.");
                        continue;
                    }

                    if (!Validador.validarVendedorRegistrado(
                            documento,
                            vendedores,
                            archivo.getFileName().toString())) {

                        continue;
                    }

                    int numeroLinea = 1;

                    while ((linea = lector.readLine()) != null) {

                        numeroLinea++;

                        if (linea.trim().isEmpty()) {
                            continue;
                        }

                        if (!Validador.validarVenta(
                                linea,
                                numeroLinea,
                                productos)) {

                            continue;
                        }

                        String[] datosVenta =
                                linea.split(";", -1);

                        int idProducto =
                                Integer.parseInt(
                                        datosVenta[0].trim());

                        int cantidad =
                                Integer.parseInt(
                                        datosVenta[1].trim());

                        Venta venta =
                                new Venta(
                                        documento,
                                        idProducto,
                                        cantidad);

                        ventas.add(venta);
                    }
                }
            }
        }

        return ventas;
    }
}
