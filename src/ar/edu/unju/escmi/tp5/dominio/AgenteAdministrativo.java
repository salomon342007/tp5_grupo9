package ar.edu.unju.escmi.tp5.dominio;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unju.escmi.tp5.collections.CollectionFactura;
import ar.edu.unju.escmi.tp5.collections.CollectionProducto;


 // AgenteAdministrativo: carga productos y realiza ventas.

 
public class AgenteAdministrativo extends Empleado {
    public AgenteAdministrativo(int legajo, String nombre, String contrasenia) {
        super(legajo, nombre, contrasenia);
    }

    // Da de alta un producto en la CollectionProducto (verifica existencia por
    // código)
    public boolean altaProducto(Producto p) {
        return CollectionProducto.agregar(p);
    }

    // Realiza una venta:
    // Ajusta precios unitarios de cada DetalleFactura según tipo de cliente
     
     
    public void realizarVenta(Factura factura) {
        factura.calcularTotal(); // valida stock y actualiza
        CollectionFactura.agregar(factura);
    }
}
