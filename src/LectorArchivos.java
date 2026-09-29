/**
 * Materia: Conceptos fundamentales de programacion
 * Grupo GB02 - G2-TypeNull
 * Integrantes:
 * July Alejandra Morales Muñoz
 * Daniel Jose Riojas Gutierrez
 * David Insignares Vega
 * Juan Andres Leguizamon Suaza
 * */

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

public class LectorArchivos {

    // Lee los productos y los guarda en un mapa por su ID
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

    // Lee los vendedores y los guarda en un mapa por documento
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
     * Lee todos los archivos vendedor_XXXX.txt.
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
