import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.PrintWriter;

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

    /*** Generador de numeros aleatorios. */
    private static final Random RANDOM = new Random();

    /* * Carpeta donde se almacenaran los archivos generados.*/
    private static final String CARPETA_DATOS = "datos";

    /** Cantidad de productos que tendra el archivo generado.*/
  
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

private static final double[] PRODUCTOS_PRECIOS = {
    35000,
    250000,
    18000,
    22000,
    30000,
    15000,
    45000,
    12000,
    35000,
    8000,
    7000,
    28000,
    10000,
    5000,
    18000
};
  public static void main(String[] args) {

    try {

        createProductsFile(CANTIDAD_PRODUCTOS);

        createSalesManInfoFile(CANTIDAD_VENDEDORES);

        for (int i = 0; i < CANTIDAD_VENDEDORES; i++) {

            long id = 1001 + i;

            int randomSalesCount = RANDOM.nextInt(10) + 1;

            String name = "vendedor_" + id;

            createSalesMenFile(
                randomSalesCount,
                name,
                id
            );
        }

        System.out.println(
            "Archivos generados correctamente."
        );

    } catch (Exception e) {

        System.out.println(
            "Error al generar los archivos: "
            + e.getMessage()
        );
    }
}

  /** * Genera un archivo de ventas para un vendedor. */
public static void createSalesMenFile(
        int randomSalesCount, String name, long id) throws Exception {

    try (PrintWriter writer = new PrintWriter(name + ".txt", "UTF-8")) {

        writer.println("CC;" + id);

        for (int i = 0; i < randomSalesCount; i++) {

            int idProducto = RANDOM.nextInt(CANTIDAD_PRODUCTOS) + 1;

            int cantidad = RANDOM.nextInt(10) + 1;

            writer.println(
                idProducto + ";" + cantidad + ";"
            );
        }
    }
}

  
  
    public static void createProductsFile(int productsCount) throws Exception {
    try (PrintWriter writer = new PrintWriter("productos.csv", "UTF-8")) {
        for (int i = 0; i < productsCount; i++) {
            writer.println(
                (i + 1) + ";"
                + PRODUCTOS_NOMBRES[i] + ";"
                + PRODUCTOS_PRECIOS[i]
            );
        } 
     }
    }

    public static void createSalesManInfoFile(int salesmanCount) throws Exception {

    try (PrintWriter writer = new PrintWriter("vendedores.csv", "UTF-8")) {

        for (int i = 0; i < salesmanCount; i++) {

            int numeroDocumento = 1000 + i + 1;

            String nombre = NOMBRES[
                RANDOM.nextInt(NOMBRES.length)
            ];

            String apellido = APELLIDOS[
                RANDOM.nextInt(APELLIDOS.length)
            ];

            writer.println(
                "CC;"
                + numeroDocumento + ";"
                + nombre + ";"
                + apellido
            );
        }
    }
 }

}
