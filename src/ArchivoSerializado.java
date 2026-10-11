import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Guarda y lee listas de objetos en archivos serializados. Se usa para
 * conservar los resultados del programa (vendedores con su total
 * recaudado y productos con su cantidad vendida) y poder recuperarlos
 * sin volver a procesar los archivos planos.
 *
 * Materia: Conceptos fundamentales de programacion
 * Grupo GB02 - G2-TypeNull
 * Integrantes:
 * July Alejandra Morales Muñoz
 * Daniel Jose Riojas Gutierrez
 * David Insignares Vega
 * Juan Andres Leguizamon Suaza
 */
public class ArchivoSerializado {

    /**
     * Constructor privado: esta clase solo expone metodos estaticos y no
     * debe ser instanciada.
     */
    private ArchivoSerializado() {
    }

    /**
     * Guarda una lista de objetos en un archivo serializado. Si el archivo
     * ya existe, se reemplaza.
     *
     * @param objetos objetos a guardar. Su clase debe implementar
     *                {@link Serializable}.
     * @param archivo ruta del archivo que se va a crear.
     * @param <T>     tipo de los objetos de la lista.
     * @throws IOException si ocurre un error al escribir el archivo.
     */
    public static <T extends Serializable> void guardar(List<T> objetos,
                                                        Path archivo)
            throws IOException {

        try (ObjectOutputStream salida = new ObjectOutputStream(
                Files.newOutputStream(archivo))) {
            salida.writeObject(new ArrayList<T>(objetos));
        }
    }

    /**
     * Lee una lista de objetos desde un archivo serializado creado con
     * {@link #guardar(List, Path)}.
     *
     * @param archivo ruta del archivo serializado.
     * @param <T>     tipo de los objetos guardados en el archivo.
     * @return lista con los objetos recuperados.
     * @throws IOException            si ocurre un error al leer el archivo.
     * @throws ClassNotFoundException si el archivo contiene objetos de una
     *                                clase que no existe en el proyecto.
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> leer(Path archivo)
            throws IOException, ClassNotFoundException {

        try (ObjectInputStream entrada = new ObjectInputStream(
                Files.newInputStream(archivo))) {
            return (List<T>) entrada.readObject();
        }
    }
}