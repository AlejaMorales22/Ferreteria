/** Representa un vendedor de la ferreteria, con su informacion personal.
 *
 * Materia: Conceptos fundamentales de programacion
 * Grupo GB02 - G2-TypeNull
 * Integrantes:
 * July Alejandra Morales Muñoz*/
public class Vendedor {

    private String tipoDocumento;
    private int numeroDocumento;
    private String nombres;
    private String apellidos;

    public Vendendor(String tipoDocumento, int numeroDocumento, String nombres, String apellidos) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public String getNombreCompleto(){
        return nombres + " " + apellidos;
    }
}
