package ar.edu.unju.escmi.tp5.dominio;

public class Producto {
    private int codigo;
    private String descripcion;
    private double precioUnitario;
    private double descuento;  //si nho teine es cero, si aplica es 25 o 30 :b
    private int stock;

    public Producto(int codigo, String descripcion, double precioUnitario, double descuento, int stock) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.stock = stock;
    }

    public int getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getDescuento() { return descuento; }
    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }
}
