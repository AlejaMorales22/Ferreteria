/** * Representa una venta realizada por un vendedor, indicando el producto
 * vendido y la cantidad correspondiente.
 *
 * Materia: Conceptos fundamentales de programacion
 * Grupo GB02 - G2-TypeNull
 * Integrantes:
 * July Alejandra Morales Muñoz
 * David Insignares 
 * */

public class Venta {

    /** Numero de documento del vendedor que realizo la venta. */
    private long documentoVendedor;

    /** Identificador del producto vendido. */
    private int idProducto;

    /** Unidades vendidas del producto. */
    private int cantidad;

    /**
     * Crea el registro de una venta.
     *
     * @param documentoVendedor numero de documento del vendedor.
     * @param idProducto identificador del producto vendido.
     * @param cantidad unidades vendidas.
     */
    public Venta(long documentoVendedor, int idProducto, int cantidad) {
        this.documentoVendedor = documentoVendedor;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el documento del vendedor que realizo la venta.
     *
     * @return numero de documento del vendedor.
     */
    public long getDocumentoVendedor() {
        return documentoVendedor;
    }

    /**
     * Obtiene el identificador del producto vendido.
     *
     * @return identificador del producto.
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * Obtiene la cantidad de unidades vendidas.
     *
     * @return unidades vendidas.
     */
    public int getCantidad() {
        return cantidad;
    }
}
