package ar.edu.unju.escmi.tp5.dominio;

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
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    public int getDescuento() { return descuento; }
    public void setDescuento(int descuento) { this.descuento = descuento; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public double obtenerPrecioConDescuento() {
        if (descuento <= 0) return precioUnitario;
        return precioUnitario * (1 - descuento / 100.0);
    }

    @Override
    public String toString() {
        return String.format("%d | %s | $%.2f | desc: %d%% | stock: %d",
                codigo, descripcion, precioUnitario, descuento, stock);
    }
}