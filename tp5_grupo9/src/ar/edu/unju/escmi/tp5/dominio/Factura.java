package ar.edu.unju.escmi.tp5.dominio;

import java.util.List;

public class Factura {
    private int numero;
    private Cliente cliente;
    private String fecha;
    private double total;
    private List<DetalleFactura> detalles;

    public Factura(int numero, Cliente cliente, String fecha, double totalFactura, List<DetalleFactura> detalles) {
        this.numero = numero;
        this.cliente = cliente;
        this.fecha = fecha;
        this.detalles = detalles;
        this.total = calcularTotal();
    }

    private double calcularTotal() {
        double suma = 0;
        for (DetalleFactura d : detalles) {
            suma += d.getImporte();
        }
        return suma;
    }

    public int getNumero() { return numero; }
    public Cliente getCliente() { return cliente; }
    public String getFecha() { return fecha; }
    public double getTotal() { return total; }
    public List<DetalleFactura> getDetalles() { return detalles; }
}
