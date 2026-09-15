import java.io.IOException;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Genera los archivos planos de entrada para el proyecto  de ventas de una ferreteria.
 
 * Materia: Conceptos fundamentales de programacion
 * Grupo GB02 - G2-TypeNull
 * Integrantes:
 * July Alejandra Morales Muñoz
 * David Insignares 
 * Juan Andres Leguizamon Suaza
 */
public class GenerateInfoFiles {

    /** Generador de numeros pseudoaleatorios. */
    private static final Random RANDOM = new Random();

    /** Carpeta donde se almacenaran los archivos de entrada generados. */
    private static final Path CARPETA_DATOS = Paths.get("datos");

    /** Cantidad de productos que tendra el archivo generado. */
    private static final int CANTIDAD_PRODUCTOS = 15;

    /** Cantidad de vendedores que tendra el archivo generado. */
    private static final int CANTIDAD_VENDEDORES = 10;

private static final String[] NOMBRES = {
    "Juan",
    "Maria",
    "Carlos",
    "Laura",
    "Andres",
    "Camila",
    "Daniel",
    "Sofia",
    "Miguel",
    "Valentina"
};

private static final String[] APELLIDOS = {
    "Perez",
    "Gomez",
    "Rodriguez",
    "Martinez",
    "Lopez",
    "Garcia",
    "Hernandez",
    "Torres",
    "Ramirez",
    "Castro"
};

private static final String[] PRODUCTOS_NOMBRES = {
    "Martillo",
    "Taladro",
    "Destornillador",
    "Alicate",
    "Llave Inglesa",
    "Cinta Metrica",
    "Sierra",
    "Brocha",
    "Pintura",
    "Tornillos",
    "Clavos",
    "Cemento",
    "Pegante",
    "Lija",
    "Nivel"
};

    /**
     * Genera todos los archivos de entrada requeridos por el proyecto.
     *
     * @param args argumentos de consola. No se usan.
     */
    public static void main(String[] args) {
        try {
            Files.createDirectories(CARPETA_DATOS);
            createProductsFile(CANTIDAD_PRODUCTOS);
            createSalesManInfoFile(CANTIDAD_VENDEDORES);

            for (int i = 0; i < CANTIDAD_VENDEDORES; i++) {
                long id = 1001 + i;
                int randomSalesCount = RANDOM.nextInt(10) + 1;
                String name = "vendedor_" + id;

                createSalesMenFile(randomSalesCount, name, id);
            }

            System.out.println("Archivos generados correctamente en: "
                    + CARPETA_DATOS.toAbsolutePath());
        } catch (IOException exception) {
            System.err.println("Error al generar los archivos: "
                    + exception.getMessage());
        }
    }

    /**
     * Genera un archivo de ventas pseudoaleatorias para un vendedor.
     *
     * @param randomSalesCount cantidad de ventas a generar.
     * @param name nombre usado para identificar el archivo.
     * @param id numero de documento del vendedor.
     * @throws IOException si ocurre un error al escribir el archivo.
     */
    public static void createSalesMenFile(int randomSalesCount, String name,
                                          long id) throws IOException {
        Path salesFile = CARPETA_DATOS.resolve(name + ".txt");

        try (BufferedWriter writer = Files.newBufferedWriter(salesFile,
                StandardCharsets.UTF_8)) {
            writer.write("CC;" + id);
            writer.newLine();

            for (int i = 0; i < randomSalesCount; i++) {
                int idProducto = RANDOM.nextInt(CANTIDAD_PRODUCTOS) + 1;
                int cantidad = RANDOM.nextInt(10) + 1;

                writer.write(idProducto + ";" + cantidad + ";");
                writer.newLine();
            }
        }
    }

    /**
     * Genera el archivo plano con los productos de la ferreteria.
     *
     * @param productsCount cantidad de productos a generar.
     * @throws IOException si ocurre un error al escribir el archivo.
     */
    public static void createProductsFile(int productsCount) throws IOException {
        Path productsFile = CARPETA_DATOS.resolve("productos.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(productsFile,
                StandardCharsets.UTF_8)) {
            for (int i = 0; i < productsCount; i++) {
                int precio = 5000 + RANDOM.nextInt(246000);
                String nombreProducto = PRODUCTOS_NOMBRES[
                        i % PRODUCTOS_NOMBRES.length];

                if (i >= PRODUCTOS_NOMBRES.length) {
                    nombreProducto += " " + (i + 1);
                }

                writer.write((i + 1) + ";" + nombreProducto + ";" + precio);
                writer.newLine();
            }
        }
    }

    /**
     * Genera el archivo plano con la informacion pseudoaleatoria de vendedores.
     *
     * @param salesmanCount cantidad de vendedores a generar.
     * @throws IOException si ocurre un error al escribir el archivo.
     */
    public static void createSalesManInfoFile(int salesmanCount)
            throws IOException {
        Path salesmenFile = CARPETA_DATOS.resolve("vendedores.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(salesmenFile,
                StandardCharsets.UTF_8)) {
            for (int i = 0; i < salesmanCount; i++) {
                long numeroDocumento = 1001 + i;
                String nombre = NOMBRES[RANDOM.nextInt(NOMBRES.length)];
                String apellido = APELLIDOS[RANDOM.nextInt(APELLIDOS.length)];

                writer.write("CC;" + numeroDocumento + ";" + nombre + ";"
                        + apellido);
                writer.newLine();
            }
        }
    }

}
