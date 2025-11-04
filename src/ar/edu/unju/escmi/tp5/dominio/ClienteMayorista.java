package ar.edu.unju.escmi.tp5.dominio;

import java.util.List;


public class ClienteMayorista extends Cliente {
    private int codCliente;

    public ClienteMayorista(String nombre, String apellido, String direccion, int codCliente) {
        super(nombre, apellido, direccion);
        this.codCliente = codCliente;
        asignarCodCliente(codCliente);
    }

    public int getCodCliente() { return codCliente; }

    
     // Calcula total donde cada unidad sale al 50% del precio unitario base.
    
    public double calcularPrecioFinal(DetalleFactura d) {
        // usar getPrecioConDescuentoProducto() en lugar de obtenerPrecioConDescuento()
        double precioProducto = d.getProducto().getPrecioConDescuentoProducto() / 2.0;
        return precioProducto * d.getCantidad() * 10; // 10 unidades por bulto
    }
}
