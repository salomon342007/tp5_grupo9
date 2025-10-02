package ar.edu.unju.escmi.tp5.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura {
    
    private static int contador = 1;

    private int numero;
    private LocalDate fecha;
    private Cliente cliente;
    private List<DetalleFactura> detalles;
    private double total;

    public Factura(Cliente cliente) {
        this.numero = contador++;
        this.fecha = LocalDate.now();
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
        this.total = 0;
    }

    public void agregarDetalle(DetalleFactura detalle) {
        detalles.add(detalle);
        total += detalle.getImporte();
    }

    public int getNumero() { return numero; }
    public LocalDate getFecha() { return fecha; }
    public Cliente getCliente() { return cliente; }
    public List<DetalleFactura> getDetalles() { return detalles; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nFactura N° ").append(numero)
          .append(" | Fecha: ").append(fecha)
          .append("\nCliente: ").append(cliente.getNombre()).append(" ").append(cliente.getApellido())
          .append("\nDetalles:\n");
        for (DetalleFactura d : detalles) {
            sb.append("   ").append(d).append("\n");
        }
        sb.append("TOTAL: $").append(total);
        return sb.toString();
    }
}