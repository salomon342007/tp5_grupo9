package ar.edu.unju.escmi.tp5.dominio;

public abstract class Empleado {
    protected int legajo;
    protected String nombre;
    protected String apellido;

    public Empleado(int legajo, String nombre, String apellido) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getLegajo() { return legajo; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    @Override
    public String toString() {
        return "Legajo: " + legajo + " | " + nombre + " " + apellido;
    }

    // Cada empleado tendrá que mostrar su propio menú
    public abstract void mostrarMenu();
}
