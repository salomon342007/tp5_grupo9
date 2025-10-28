package ar.edu.unju.escmi.tp5.dominio;

import java.util.List;

import ar.edu.unju.escmi.tp5.collections.CollectionFactura;
import ar.edu.unju.escmi.tp5.collections.CollectionProducto;
import ar.edu.unju.escmi.tp5.collections.CollectionEmpleado;


public class EncargadoDeVentas {

    // Muestra todas las facturas (usa CollectionFactura)
    public static List<Factura> mostrarVentas() {
        return CollectionFactura.facturas;
    }

    // Verificar stock de un producto por código
    public static int verificarStock(int codigoProducto) {
        Producto p = CollectionProducto.buscarPorCodigo(codigoProducto);
        if (p == null)
            return -1; // no encontrado
        return p.getCantidadTotal();
    }

    // Mostrar total acumulado de ventas (suma totales de facturas)
    public static double mostrarTotalVentas() {
        double sum = 0.0;
        for (Factura f : CollectionFactura.facturas) {
            sum += f.getTotal();
        }
        return sum;
    }

    // Ejemplo de uso de empleados 
    public static List<Empleado> listarEmpleados() {
        return CollectionEmpleado.empleados;
    }
}
