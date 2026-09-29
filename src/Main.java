import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        System.out.println("INICIANDO SISTEMA");

        try {

            Path carpetaDatos = Paths.get("datos");

            // 1. Leer productos
            Map<Integer, Producto> productos =
                    LectorArchivos.leerProductos(
                            carpetaDatos.resolve("productos.txt"));

            // 2. Leer vendedores
            Map<Long, Vendedor> vendedores =
                    LectorArchivos.leerVendedores(
                            carpetaDatos.resolve("vendedores.txt"));

            // 3. Leer ventas
            List<Venta> ventas =
                    LectorArchivos.leerVentas(
                            carpetaDatos,
                            vendedores,
                            productos);

            System.out.println(
                    "Productos leídos: " + productos.size());

            System.out.println(
                    "Vendedores leídos: " + vendedores.size());

            System.out.println(
                    "Ventas válidas leídas: " + ventas.size());

            // 4. Calcular totales
            GeneradorReportes.calcularTotales(
                    ventas,
                    vendedores,
                    productos);

            // 5. Generar reporte de vendedores
            GeneradorReportes.generarReporteVendedores(
                    new ArrayList<Vendedor>(
                            vendedores.values()));

            // 6. Generar reporte de productos
            GeneradorReportes.generarReporteProductos(
                    new ArrayList<Producto>(
                            productos.values()));

            System.out.println(
                    "\nReportes generados correctamente.");

            System.out.println(
                    "EJECUCIÓN FINALIZADA");

        } catch (Exception e) {

            System.err.println(
                    "\nOcurrió un problema durante la ejecución: "
                            + e.getMessage());

            e.printStackTrace();
        }
    }
}
