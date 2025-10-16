package ar.edu.unju.escmi.tp5.dominio;

import java.util.List;

/**
 * Cliente que compra por unidad. Si presenta dni y obraSocialPAMI true -> 10%
 * descuento sobre total.
 */
public class ClienteMinorista extends Cliente {
    private String dni;
    private boolean obraSocialPAMI;

    public ClienteMinorista(String apellido, String nombre, String direccion, String dni, boolean obraSocialPAMI) {
        super(apellido, nombre, direccion);
        this.dni = dni;
        this.obraSocialPAMI = obraSocialPAMI;
    }

    public String getDni() {
        return dni;
    }

    public boolean hasObraSocialPAMI() {
        return obraSocialPAMI;
    }

    @Override
    public double calcularTotalCompra(List<DetalleFactura> items) {
        double total = 0.0;
        for (DetalleFactura d : items) {
            // precio con descuento del producto si aplica
            double precioProducto = d.getProducto().obtenerPrecioConDescuento();
            total += d.getCantidad() * precioProducto;
        }
        // descuento adicional por PAMI (10%)
        if (obraSocialPAMI && dni != null && !dni.isEmpty()) {
            total = total * 0.9;
        }
        return total;
    }
}
