package ar.edu.unju.escmi.tp5.dominio;

import java.util.List;


public abstract class Cliente {
    protected String nombre;
    protected String apellido;
    protected String direccion;
    protected int codigoCliente = -1;

    public Cliente(String nombre, String apellido, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    public void asignarCodCliente(int codigo) { this.codigoCliente = codigo; }
    public int getCodigoCliente() { return codigoCliente; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDireccion() { return direccion; }

    public String getNombreCompleto() { return apellido + ", " + nombre; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" + getNombreCompleto() + ", dir=" + direccion + ", codigo=" + codigoCliente + "}";
    }

    // Permite consultar una factura por número (usa CollectionFactura)
    public Factura buscarFactura(int numero) {
        return ar.edu.unju.escmi.tp5.collections.CollectionFactura.buscar(numero);
    }
}
