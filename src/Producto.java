/**
 * Representa un producto disponible para la venta en la ferreteria,
 * incluyendo su identificador, nombre, precio y la cantidad de unidades vendidas
 *
 * Materia: Conceptos fundamentales de programacion
 * Grupo GB02 - G2-TypeNull
 * Integrantes:
 * July Alejandra Morales Muñoz
 * Daniel Jose Riojas Gutierrez
 * David Insignares Vega
 * Juan Andres Leguizamon Suaza
 * */

public class Producto {

    /** Identificador unico del producto. */
    private int id;

    /** Nombre del producto. */
    private String nombre;

    /** Precio de venta de una unidad del producto, en pesos. */
    private double precioUnitario;

    /** Total de unidades vendidas del producto entre todos los vendedores. */
    private int cantidadVendida;

    /**
     * Crea un producto de la ferreteria sin unidades vendidas.
     *
     * @param id identificador unico del producto
     * @param nombre nombre del producto
     * @param precioUnitario precio de venta de una unidad.
     */
    public Producto(int id, String nombre, double precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.cantidadVendida = 0;
    }

    /**
     * Suma unidades vendidas al total acumulado del producto.
     * @param cantidad unidades vendidas en una venta.
     */
    public void agregarCantidad(int cantidad) {
        cantidadVendida += cantidad;
    }

    /**
     * Obtiene el identificador del producto.
     *
     * @return identificador unico del producto.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del producto.
     * @return nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio de una unidad del producto.
     * @return precio unitario en pesos.
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Obtiene el total de unidades vendidas del producto.
     * @return cantidad total vendida.
     */
    public int getCantidadVendida() {
        return cantidadVendida;
    }
}
