import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Programa principal del proyecto de ventas de la ferreteria. Lee los
 * archivos de entrada de la carpeta datos, calcula el dinero recaudado por
 * cada vendedor y las unidades vendidas de cada producto, y genera los
 * reportes de vendedores y de productos.
 *
 * Materia: Conceptos fundamentales de programacion
 * Grupo GB02 - G2-TypeNull
 * Integrantes:
 * July Alejandra Morales Muñoz
 * Daniel Jose Riojas Gutierrez
 * David Insignares Vega
 * Juan Andres Leguizamon Suaza
 */
public class Main {

    /**
     * Ejecuta el flujo completo: leer, validar, calcular y generar reportes.
     * Muestra un mensaje de finalizacion exitosa o de error.
     *
     * @param args argumentos de consola. No se usan.
     */
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
