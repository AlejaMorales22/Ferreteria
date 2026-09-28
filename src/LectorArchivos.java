
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

    // Lee los productos y los guarda en un mapa por su ID.
    public static Map<Integer, Producto> leerProductos(Path archivo)
            throws IOException {

        Map<Integer, Producto> productos = new HashMap<Integer, Producto>();

        try (BufferedReader lector = Files.newBufferedReader(
                archivo, StandardCharsets.UTF_8)) {

            String linea;

            while ((linea = lector.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";");

                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                double precio = Double.parseDouble(datos[2]);

                Producto producto = new Producto(id, nombre, precio);

                productos.put(id, producto);
            }
        }

        return productos;
    }

    // Lee los vendedores y los guarda en un mapa por documento.
    public static Map<Long, Vendedor> leerVendedores(Path archivo)
            throws IOException {

        Map<Long, Vendedor> vendedores = new HashMap<Long, Vendedor>();

        try (BufferedReader lector = Files.newBufferedReader(
                archivo, StandardCharsets.UTF_8)) {

            String linea;

            while ((linea = lector.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";");

                String tipoDocumento = datos[0];
                long documento = Long.parseLong(datos[1]);
                String nombres = datos[2];
                String apellidos = datos[3];

                Vendedor vendedor = new Vendedor(
                        tipoDocumento, documento, nombres, apellidos);

                vendedores.put(documento, vendedor);
            }
        }

        return vendedores;
    }

    // Lee todos los archivos de ventas de la carpeta.
    public static List<Venta> leerVentas(Path carpeta)
            throws IOException {

        List<Venta> ventas = new ArrayList<Venta>();

        try (DirectoryStream<Path> archivos = Files.newDirectoryStream(
                carpeta, "vendedor_*.txt")) {

            for (Path archivo : archivos) {

                try (BufferedReader lector = Files.newBufferedReader(
                        archivo, StandardCharsets.UTF_8)) {

                    // La primera línea identifica al vendedor.
                    String linea = lector.readLine();

                    if (linea == null) {
                        continue;
                    }

                    String[] datosVendedor = linea.split(";");
                    long documento = Long.parseLong(datosVendedor[1]);

                    // Las demás líneas contienen ID del producto y cantidad.
                    while ((linea = lector.readLine()) != null) {
                        if (linea.trim().isEmpty()) {
                            continue;
                        }

                        String[] datosVenta = linea.split(";");

                        int idProducto = Integer.parseInt(datosVenta[0]);
                        int cantidad = Integer.parseInt(datosVenta[1]);

                        Venta venta = new Venta(
                                documento, idProducto, cantidad);

                        ventas.add(venta);
                    }
                }
            }
        }

        return ventas;
    
    }

public static void main(String[] args) {
    try {
        Path carpeta = java.nio.file.Paths.get("datos");

        Map<Integer, Producto> productos =
                leerProductos(carpeta.resolve("productos.txt"));

        Map<Long, Vendedor> vendedores =
                leerVendedores(carpeta.resolve("vendedores.txt"));

        List<Venta> ventas = leerVentas(carpeta);

        System.out.println("Productos: " + productos.size());
        System.out.println("Vendedores: " + vendedores.size());
        System.out.println("Ventas: " + ventas.size());

    } catch (Exception e) {
    System.out.println(
        "Error al leer archivos: " + e.getMessage()
    );
}
}
}