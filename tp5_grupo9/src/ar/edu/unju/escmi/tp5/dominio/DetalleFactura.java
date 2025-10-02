
package ar.edu.unju.escmi.tp5.dominio;

public class DetalleFactura {
    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    private double importe;

    public DetalleFactura(Producto producto, int cantidad, double precioUnitario) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = cantidad * precioUnitario;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getImporte() { return importe; }

    @Override
    public String toString() {
        return cantidad + " x " + producto.getDescripcion() +
               " | Unit: $" + precioUnitario +
               " | Importe: $" + importe;
    }
}