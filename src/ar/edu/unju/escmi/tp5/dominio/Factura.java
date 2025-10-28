package ar.edu.unju.escmi.tp5.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Factura {
    private int numero;
    private LocalDate fecha;
    private double total;
    private Cliente cliente;
    private List<DetalleFactura> detalles;

    public Factura(int numero, LocalDate fecha, Cliente cliente) {
        this.numero = numero;
        this.fecha = fecha;
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
        this.total = 0.0;
    }

    // Agrega detalle: el detalle "pertenece" a la factura
    public void agregarDetalle(DetalleFactura det) {
        this.detalles.add(det);
        recalcularTotal();
    }

    // Recalcula total a partir de los detalles
    public double calcularTotal() {
        double suma = 0.0;
        for (DetalleFactura d : detalles) {
            suma += d.getImporte();
        }
        this.total = suma;
        return total;
    }

    // Recalcula total y deja el campo total actualizado
    public void recalcularTotal() {
        calcularTotal();
    }

    
    public void setTotal(double total) {
        this.total = total;
    }


    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public int getNumero() {
        return numero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getTotal() {
        return total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "Factura #" + numero + " fecha:" + fecha + " cliente:"
                + (cliente != null ? cliente.getNombreCompleto() : "N/A") + " total: " + total;
    }
}
