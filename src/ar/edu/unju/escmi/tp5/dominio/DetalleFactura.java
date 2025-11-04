package ar.edu.unju.escmi.tp5.dominio;

public class DetalleFactura {
    private Producto producto;
    private int cantidad; // si cliente mayorista -> bultos; si minorista -> unidades
    private double subtotal;

    public DetalleFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getSubtotal() { return subtotal; }

    // Calcula subtotal según tipo de cliente y descuento por producto.
    public void calcularSubtotal(Cliente cliente) {
        if (cliente instanceof ClienteMayorista) {
            // cantidad = bultos, 1 bulto = 10 unidades, precio por unidad = mitad
            int unidades = cantidad * 10;
            double precioUnidad = producto.getPrecioUnitario() / 2.0;
            subtotal = unidades * precioUnidad;
            // aplicar descuento del producto sobre el importe (si corresponde)
            if (producto.getDescuento() > 0) {
                subtotal = subtotal * (1 - producto.getDescuento() / 100.0);
            }
        } else {
            // minorista u otro: cantidad en unidades, precio unitario con descuento producto ya aplicado
            subtotal = cantidad * producto.getPrecioConDescuentoProducto();
        }
    }

    // Comprueba y resta stock según cliente (cantidad convertida a unidades)
    public boolean aplicarActualizacionStock(Cliente cliente) {
        int unidadesARestar = (cliente instanceof ClienteMayorista) ? cantidad * 10 : cantidad;
        return producto.actualizarStock(unidadesARestar);
    }

    @Override
    public String toString() {
        return producto.getDescripcion() + " x " + cantidad + " -> $" + String.format("%.2f", subtotal);
    }
}
