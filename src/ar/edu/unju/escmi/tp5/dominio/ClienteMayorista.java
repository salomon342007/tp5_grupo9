package ar.edu.unju.escmi.tp5.dominio;

import java.util.List;


public class ClienteMayorista extends Cliente {
    private String codigoCliente;

    public ClienteMayorista(String apellido, String nombre, String direccion, String codigoCliente) {
        super(apellido, nombre, direccion);
        this.codigoCliente = codigoCliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    
     // Calcula total donde cada unidad sale al 50% del precio unitario base.
    
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
