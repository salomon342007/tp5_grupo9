package ar.edu.unju.escmi.tp5.dominio;

public class DetalleFactura {
    private Producto producto;
    private int cantidad;

    public DetalleFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double getImporte() {
        return cantidad * producto.getPrecioUnitario();
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
}