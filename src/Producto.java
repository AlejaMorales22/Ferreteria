/** Representa un producto disponible para la venta en la ferreteria, incluyendo su identificador, nombre y precio.
 *
 * Materia: Conceptos fundamentales de programacion
 * Grupo GB02 - G2-TypeNull
 * Integrantes:
 * David Insignares 
 * July Alejandra Morales Muñoz*/
public class Producto {

    private int id;
    private String nombre;
    private double precioUnitario;

    public Producto(int id, String nombre, double precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
    }
}
