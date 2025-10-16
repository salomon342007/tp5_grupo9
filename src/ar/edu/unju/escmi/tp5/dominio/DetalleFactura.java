package ar.edu.unju.escmi.tp5.dominio;

/**
 * Detalle de factura: asocia un producto con cantidad y calcula importe.
 * Es parte de Factura (composición).
 */
public class DetalleFactura {
    private Producto producto;
    private int cantidad;
    private double precioUnitario; // se fija al generar la factura
    private double importe;

    public DetalleFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        // Por defecto, precioUnitario y importe se calculan cuando se agregue al
        // documento
        this.precioUnitario = producto.getPrecioUnitario(); // por defecto sin descuentos de cliente
        this.importe = calcularImporte();
    }

    // Calcula importe local (cantidad * precioUnitario)
    public double calcularImporte() {
        return cantidad * precioUnitario;
    }

    // setters/getters simples (necesarios en lógica)
    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double p) {
        this.precioUnitario = p;
        this.importe = calcularImporte();
    }

    public double getImporte() {
        return importe;
    }
}
