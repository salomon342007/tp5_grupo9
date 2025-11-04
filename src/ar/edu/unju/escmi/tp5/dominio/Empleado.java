package ar.edu.unju.escmi.tp5.dominio;

public abstract class Empleado {
    protected int legajo;
    protected String contrasenia;
    protected String nombre;

    public Empleado(int legajo, String nombre, String contrasenia) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }

    public int getLegajo() { return legajo; }
    public String getNombre() { return nombre; }
    public String getContrasenia() { return contrasenia; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {legajo=" + legajo + ", nombre=" + nombre + "}";
    }
}
