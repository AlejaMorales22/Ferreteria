/** Representa un vendedor de la ferreteria, con su informacion personal y
 * el dinero total que ha recaudado por sus ventas.
 *
 * Materia: Conceptos fundamentales de programacion
 * Grupo GB02 - G2-TypeNull
 * Integrantes:
 * David Insignares 
 * July Alejandra Morales Muñoz*/
public class Vendedor {

    /** Tipo de documento de identidad del vendedor (por ejemplo, CC). */
    private String tipoDocumento;

    /** Numero de documento de identidad del vendedor. */
    private long numeroDocumento;

    /** Nombres del vendedor. */
    private String nombres;

    /** Apellidos del vendedor. */
    private String apellidos;

    /** Dinero total recaudado por el vendedor, en pesos. */
    private double totalRecaudado;

    /**
     * Crea un vendedor sin ventas registradas.
     *
     * @param tipoDocumento tipo de documento de identidad.
     * @param numeroDocumento numero de documento de identidad.
     * @param nombres nombres del vendedor.
     * @param apellidos apellidos del vendedor.
     */
    public Vendedor(String tipoDocumento, long numeroDocumento, String nombres, String apellidos) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.totalRecaudado = 0;
    }

    /**
     * Suma el valor de una venta al total recaudado por el vendedor.
     *
     * @param valor dinero obtenido en la venta (precio por cantidad).
     */
    public void agregarVenta(double valor){
        totalRecaudado += valor;
    }

    /**
     * Obtiene el nombre completo del vendedor.
     *
     * @return nombres y apellidos separados por un espacio.
     */
    public String getNombreCompleto(){
        return nombres + " " + apellidos;
    }

    /**
     * Obtiene el tipo de documento del vendedor.
     *
     * @return tipo de documento de identidad.
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Obtiene el numero de documento del vendedor.
     *
     * @return numero de documento de identidad.
     */
    public long getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Obtiene los nombres del vendedor.
     *
     * @return nombres del vendedor.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Obtiene los apellidos del vendedor.
     *
     * @return apellidos del vendedor.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Obtiene el dinero total recaudado por el vendedor.
     *
     * @return total recaudado en pesos.
     */
    public double getTotalRecaudado() {
        return totalRecaudado;
    }


}
