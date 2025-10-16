package ar.edu.unju.escmi.tp5.dominio;

import java.util.List;

/**
 * Cliente que compra por mayor: cada bulto = 10 unidades y precio por unidad es
 * la mitad.
 */
public class ClienteMayorista extends Cliente {
    private String codigoCliente;

    public ClienteMayorista(String apellido, String nombre, String direccion, String codigoCliente) {
        super(apellido, nombre, direccion);
        this.codigoCliente = codigoCliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * Calcula total donde cada unidad sale al 50% del precio unitario base.
     * (El detalle viene con cantidad en unidades)
     */
    @Override
    public double calcularTotalCompra(List<DetalleFactura> items) {
        double total = 0.0;
        for (DetalleFactura d : items) {
            // Para mayorista precio = mitad del precio unitario luego aplicar descuento del
            // producto si tiene
            double precioProducto = d.getProducto().obtenerPrecioConDescuento() / 2.0;
            total += d.getCantidad() * precioProducto;
        }
        return total;
    }
}
