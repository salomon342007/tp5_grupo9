package ar.edu.unju.escmi.tp5.dominio;

import java.util.Objects;


public class Producto {
    private int codigo;
    private String descripcion;
    private double precioUnitario;
    private int descuento; // 0, 25, 30
    private int stock;

    public Producto(int codigo, String descripcion, double precioUnitario, int descuento, int stock) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.stock = stock;
    }

    public int getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public double getPrecioUnitario() { return precioUnitario; }
    public int getDescuento() { return descuento; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    // Precio unitario aplicando descuento del producto
    public double getPrecioConDescuentoProducto() {
        return precioUnitario * (1 - descuento / 100.0);
    }

    // Actualiza stock restando cantidad (unidades). Retorna true si pudo restar.
    public boolean actualizarStock(int cantidad) {
        if (cantidad <= stock) {
            stock -= cantidad;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return codigo + " - " + descripcion + " $ " + String.format("%.2f", precioUnitario) +
               " (stock:" + stock + ", desc:" + descuento + "%)";
    }
}
