package ar.edu.unju.escmi.tp5.dominio;

public abstract class Cliente {
    protected String nombre;
    protected String apellido;
    protected String direccion;

    public Cliente(String nombre, String apellido, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    // IMPLEMENTACIÓN POR DEFECTO: no aplica descuento
    // Si una subclase necesita un comportamiento distinto, que haga @Override.
    public double calcularDescuento(double total) {
        return total;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + direccion;
    }
}