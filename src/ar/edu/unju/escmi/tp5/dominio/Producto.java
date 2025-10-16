package ar.edu.unju.escmi.tp5.dominio;

import java.util.Objects;

/**
 * Representa un producto con su stock interno (cantidadTotal).
 * Contiene operaciones para actualizar stock y calcular precio con descuento.
 */
public class Producto {
    // atributos (lowerCamelCase, estilo Java)
    private int codigoProducto;
    private String descripcion;
    private double precioUnitario;
    private int descuentoPorc; // 0, 25, 30, etc.
    private int cantidadTotal;

    // Constructor (necesario para precarga)
    public Producto(int codigoProducto, String descripcion, double precioUnitario, int descuentoPorc,
            int cantidadTotal) {
        this.codigoProducto = codigoProducto;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.descuentoPorc = descuentoPorc;
        this.cantidadTotal = cantidadTotal;
    }

    // Actualiza stock relativo (positivo para sumar, negativo para restar)
    public void actualizarStock(int delta) {
        this.cantidadTotal += delta;
        if (this.cantidadTotal < 0)
            this.cantidadTotal = 0; // protección simple
    }

    // Precio unitario aplicando el descuento del producto (no incluye descuentos
    // por tipo de cliente)
    public double obtenerPrecioConDescuento() {
        return precioUnitario * (1.0 - (descuentoPorc / 100.0));
    }

    // Getters mínimos para uso interno (no getters/setters en UML, pero necesarios
    // en código)
    public int getCodigoProducto() {
        return codigoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getDescuentoPorc() {
        return descuentoPorc;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    @Override
    public String toString() {
        return "[" + codigoProducto + "] " + descripcion + " $ " + precioUnitario + " (desc " + descuentoPorc
                + "%) stock: " + cantidadTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Producto))
            return false;
        Producto p = (Producto) o;
        return codigoProducto == p.codigoProducto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoProducto);
    }
}
