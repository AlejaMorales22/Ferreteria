import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Programa principal del proyecto de ventas de la ferreteria. Lee los
 * archivos de entrada de la carpeta datos, calcula el dinero recaudado por
 * cada vendedor y las unidades vendidas de cada producto, genera los
 * reportes de vendedores y de productos, y guarda los resultados en
 * archivos serializados.
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
     * Ejecuta el flujo completo: leer, validar, calcular, generar reportes
     * y serializar los resultados. Muestra un mensaje de finalizacion
     * exitosa o de error.
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

            List<Vendedor> listaVendedores =
                    new ArrayList<Vendedor>(vendedores.values());

            List<Producto> listaProductos =
                    new ArrayList<Producto>(productos.values());

            // 5. Generar reporte de vendedores
            GeneradorReportes.generarReporteVendedores(listaVendedores);

            // 6. Generar reporte de productos
            GeneradorReportes.generarReporteProductos(listaProductos);

            System.out.println(
                    "\nReportes generados correctamente.");

            // 7. Guardar y verificar los resultados serializados
            Path archivoVendedores = carpetaDatos.resolve("vendedores.ser");
            Path archivoProductos = carpetaDatos.resolve("productos.ser");

            ArchivoSerializado.guardar(listaVendedores, archivoVendedores);
            ArchivoSerializado.guardar(listaProductos, archivoProductos);

            List<Vendedor> vendedoresRecuperados =
                    ArchivoSerializado.leer(archivoVendedores);

            List<Producto> productosRecuperados =
                    ArchivoSerializado.leer(archivoProductos);

            System.out.println(
                    "Archivos serializados verificados: "
                            + vendedoresRecuperados.size() + " vendedores, "
                            + productosRecuperados.size() + " productos.");

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