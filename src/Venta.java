/** Representa una venta realizada por un vendedor, indicadon el producto vendido y la cantidad correspondiente.
 *
 * Materia: Conceptos fundamentales de programacion
 * Grupo GB02 - G2-TypeNull
 * Integrantes:
 * July Alejandra Morales Muñoz
 * David Insignares 
 * */

public class Venta {

    private long documentoVendedor;
    private int idProducto;
    private int cantidad;

    public Venta(long documentoVendedor, int idProducto, int cantidad) {
        this.documentoVendedor = documentoVendedor;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }
}
