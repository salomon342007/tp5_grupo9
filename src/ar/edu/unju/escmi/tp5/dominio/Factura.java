package ar.edu.unju.escmi.tp5.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura {
    private int numero;
    private LocalDate fecha;
    private Cliente cliente;
    private List<DetalleFactura> detalles = new ArrayList<>();
    private double total;

    public Factura(int numero, LocalDate fecha, Cliente cliente) {
        this.numero = numero;
        this.fecha = fecha;
        this.cliente = cliente;
    }

    public int getNumero() { return numero; }
    public LocalDate getFecha() { return fecha; }
    public Cliente getCliente() { return cliente; }
    public double getTotal() { return total; }
    public List<DetalleFactura> getDetalles() { return detalles; }

    public void agregarDetalle(DetalleFactura d) { detalles.add(d); }

    // Calcula total, verifica stock y actualiza stock si es suficiente.
    public void calcularTotal() {
        // calcular subtotales por detalle
        double suma = 0.0;
        for (DetalleFactura d : detalles) {
            d.calcularSubtotal(cliente);
            suma += d.getSubtotal();
        }

        // aplicar descuento PAMI (10%) si corresponde (cliente minorista con PAMI)
        if (cliente instanceof ClienteMinorista) {
            ClienteMinorista cm = (ClienteMinorista) cliente;
            if (cm.tienePami()) {
                suma = suma * 0.90;
            }
        }

        // Antes de confirmar, verificar stock para todos los detalles
        for (DetalleFactura d : detalles) {
            int necesarias = (cliente instanceof ClienteMayorista) ? d.getCantidad() * 10 : d.getCantidad();
            if (d.getProducto().getStock() < necesarias) {
                throw new RuntimeException("Stock insuficiente para producto: " + d.getProducto().getCodigo());
            }
        }

        // Si ok, aplicar la resta de stock
        for (DetalleFactura d : detalles) {
            boolean ok = d.aplicarActualizacionStock(cliente);
            if (!ok) {
                throw new RuntimeException("Error al actualizar stock para producto: " + d.getProducto().getCodigo());
            }
        }

        this.total = suma;
    }

    public void mostrarDetalle() {
        System.out.println("Factura n° " + numero + "  Fecha: " + fecha + "  Cliente: " + cliente.getNombreCompleto());
        System.out.println("Dirección: " + cliente.getDireccion());
        for (DetalleFactura d : detalles) {
            System.out.println("  - " + d.toString());
        }
        System.out.println("TOTAL: $" + String.format("%.2f", total));
        System.out.println("--------------------------------------------------");
    }

    @Override
    public String toString() {
        return "Factura{" + "numero=" + numero + ", fecha=" + fecha + ", total=" + total + "}";
    }
}
