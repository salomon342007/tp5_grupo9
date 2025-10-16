package ar.edu.unju.escmi.tp5.dominio;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unju.escmi.tp5.collections.CollectionFactura;
import ar.edu.unju.escmi.tp5.collections.CollectionProducto;

/**
 * AgenteAdministrativo: carga productos y realiza ventas.
 * No usa reflexión; utiliza Factura.setTotal(...) cuando hace descuentos
 * globales.
 */
public class AgenteAdministrativo {

    // Da de alta un producto en la CollectionProducto (verifica existencia por
    // código)
    public static boolean altaProducto(Producto producto) {
        return CollectionProducto.agregarProducto(producto);
    }

    /**
     * Realiza una venta:
     * - Ajusta precios unitarios de cada DetalleFactura según tipo de cliente
     * (mayorista/minorista)
     * - Actualiza stock de cada producto
     * - Genera Factura y la guarda en CollectionFactura
     */
    public static Factura realizarVenta(Cliente cliente, List<DetalleFactura> items) {
        // crear factura con número simple (por ejemplo, size+1)
        int nro = CollectionFactura.facturas.size() + 1;
        Factura factura = new Factura(nro, LocalDate.now(), cliente);

        // Si es mayorista: precio unitario = precio con descuento del producto / 2
        if (cliente instanceof ClienteMayorista) {
            for (DetalleFactura d : items) {
                double precio = d.getProducto().obtenerPrecioConDescuento() / 2.0;
                d.setPrecioUnitario(precio);
                factura.agregarDetalle(d);
                d.getProducto().actualizarStock(-d.getCantidad());
            }
            // total ya calculado por los detalles
            factura.recalcularTotal();
        }
        // Si es minorista: precio unitario = precio con descuento del producto,
        // luego el cliente puede aplicar su descuento (p.ej. PAMI 10%)
        else if (cliente instanceof ClienteMinorista) {
            for (DetalleFactura d : items) {
                double precio = d.getProducto().obtenerPrecioConDescuento();
                d.setPrecioUnitario(precio);
                factura.agregarDetalle(d);
                d.getProducto().actualizarStock(-d.getCantidad());
            }
            // calcular total final según lógica del cliente (incluye PAMI si corresponde)
            double totalCalculado = cliente.calcularTotalCompra(factura.getDetalles());
            // actualizamos el total usando el setter (no reflexión)
            factura.setTotal(totalCalculado);
        }
        // Caso genérico: aplicar precio con descuento del producto
        else {
            for (DetalleFactura d : items) {
                double precio = d.getProducto().obtenerPrecioConDescuento();
                d.setPrecioUnitario(precio);
                factura.agregarDetalle(d);
                d.getProducto().actualizarStock(-d.getCantidad());
            }
            factura.recalcularTotal();
        }

        // Guardar factura en collection
        CollectionFactura.agregarFactura(factura);
        return factura;
    }
}
