import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Calcula los totales de ventas y genera los reportes finales de la
 * ferreteria: el dinero recaudado por cada vendedor y la cantidad de
 * unidades vendidas de cada producto.
 * <p>
 * Los reportes se escriben como archivos de texto separados por punto y
 * coma ({@code ;}), en el mismo formato usado por los demas archivos del
 * proyecto, sin fila de encabezado.
 * Materia: Conceptos fundamentales de programacion
 * Grupo GB02 - G2-TypeNull
 * Integrantes:
 * July Alejandra Morales Muñoz
 * Daniel Jose Riojas Gutierrez
 * David Insignares Vega
 * Juan Andres Leguizamon Suaza
 * */
public class GeneradorReportes {

    /** Carpeta donde se almacenan los archivos de datos y los reportes generados. */
    private static final Path CARPETA_DATOS = Paths.get("datos");

    /** Nombre del archivo de reporte de vendedores. */
    private static final String ARCHIVO_REPORTE_VENDEDORES = "reporte_vendedores.csv";

    /** Nombre del archivo de reporte de productos. */
    private static final String ARCHIVO_REPORTE_PRODUCTOS = "reporte_productos.csv";

    /**
     * Constructor privado: esta clase solo expone metodos estaticos y no
     * debe ser instanciada.
     */
    private GeneradorReportes() {
    }

    /**
     * Recorre todas las ventas registradas y, por cada una, acumula el
     * dinero recaudado en el vendedor correspondiente (precio unitario del
     * producto multiplicado por la cantidad vendida) y suma la cantidad de
     * unidades vendidas al producto correspondiente.
     * <p>
     * Si una venta hace referencia a un documento de vendedor o a un
     * identificador de producto que no existe en los mapas recibidos, esa
     * venta se ignora y no afecta ningun total.
     *
     * @param ventas                 lista de ventas registradas.
     * @param vendedoresPorDocumento vendedores de la ferreteria, indexados
     *                               por su numero de documento.
     * @param productosPorId         productos de la ferreteria, indexados
     *                               por su identificador.
     */
    public static void calcularTotales(List<Venta> ventas,
                                        Map<Long, Vendedor> vendedoresPorDocumento,
                                        Map<Integer, Producto> productosPorId) {
        for (Venta venta : ventas) {
            Vendedor vendedor = vendedoresPorDocumento.get(venta.getDocumentoVendedor());
            Producto producto = productosPorId.get(venta.getIdProducto());

            if (vendedor == null || producto == null) {
                continue;
            }

            double valorVenta = producto.getPrecioUnitario() * venta.getCantidad();
            vendedor.agregarVenta(valorVenta);
            producto.agregarCantidad(venta.getCantidad());
        }
    }

    /**
     * Genera el reporte de vendedores en la ruta por defecto
     * ({@code datos/reporte_vendedores.csv}).
     *
     * @param vendedores vendedores a incluir en el reporte. Se espera que
     *                   ya tengan su total recaudado calculado, por ejemplo
     *                   mediante {@link #calcularTotales}.
     * @throws IOException si ocurre un error al escribir el archivo.
     */
    public static void generarReporteVendedores(List<Vendedor> vendedores) throws IOException {
        generarReporteVendedores(vendedores, CARPETA_DATOS.resolve(ARCHIVO_REPORTE_VENDEDORES));
    }

    /**
     * Escribe el reporte de vendedores en el archivo indicado. Cada linea
     * contiene el nombre completo del vendedor y su total recaudado,
     * separados por punto y coma ({@code Nombre Apellido;Total}). Las
     * filas quedan ordenadas de mayor a menor segun el total recaudado.
     *
     * @param vendedores    vendedores a incluir en el reporte.
     * @param archivoSalida ruta del archivo CSV que se va a generar.
     * @throws IOException si ocurre un error al escribir el archivo.
     */
    public static void generarReporteVendedores(List<Vendedor> vendedores, Path archivoSalida)
            throws IOException {
        List<Vendedor> vendedoresOrdenados = new ArrayList<>(vendedores);
        vendedoresOrdenados.sort(
                Comparator.comparingDouble(Vendedor::getTotalRecaudado).reversed());

        Path directorioPadre = archivoSalida.toAbsolutePath().getParent();
        if (directorioPadre != null) {
            Files.createDirectories(directorioPadre);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(archivoSalida,
                StandardCharsets.UTF_8)) {
            for (Vendedor vendedor : vendedoresOrdenados) {
                writer.write(vendedor.getNombreCompleto() + ";"
                        + String.format("%.0f", vendedor.getTotalRecaudado()));
                writer.newLine();
            }
        }
    }

    /**
     * Genera el reporte de productos en la ruta por defecto
     * ({@code datos/reporte_productos.csv}).
     *
     * @param productos productos a incluir en el reporte. Se espera que ya
     *                  tengan su cantidad vendida calculada, por ejemplo
     *                  mediante {@link #calcularTotales}.
     * @throws IOException si ocurre un error al escribir el archivo.
     */
    public static void generarReporteProductos(List<Producto> productos) throws IOException {
        generarReporteProductos(productos, CARPETA_DATOS.resolve(ARCHIVO_REPORTE_PRODUCTOS));
    }

    /**
     * Escribe el reporte de productos en el archivo indicado. Cada linea
     * contiene el nombre del producto, su precio unitario y la cantidad
     * total vendida, separados por punto y coma
     * ({@code Nombre;Precio;Cantidad}). La tercera columna con la cantidad
     * vendida se incluye para que el criterio de orden sea visible en el
     * archivo. Las filas quedan ordenadas de mayor a menor segun la
     * cantidad vendida.
     *
     * @param productos     productos a incluir en el reporte.
     * @param archivoSalida ruta del archivo CSV que se va a generar.
     * @throws IOException si ocurre un error al escribir el archivo.
     */
    public static void generarReporteProductos(List<Producto> productos, Path archivoSalida)
            throws IOException {
        List<Producto> productosOrdenados = new ArrayList<>(productos);
        productosOrdenados.sort(
                Comparator.comparingInt(Producto::getCantidadVendida).reversed());

        Path directorioPadre = archivoSalida.toAbsolutePath().getParent();
        if (directorioPadre != null) {
            Files.createDirectories(directorioPadre);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(archivoSalida,
                StandardCharsets.UTF_8)) {
            for (Producto producto : productosOrdenados) {
                writer.write(producto.getNombre() + ";"
                        + String.format("%.0f", producto.getPrecioUnitario()) + ";"
                        + producto.getCantidadVendida());
                writer.newLine();
            }
        }
    }
}

