package ar.edu.unju.escmi.tp5.dominio;


 // Empleado simple con legajo y método para mostrar datos.
 
public class Empleado {
    private String apellido;
    private String nombre;
    private String legajo;

    public Empleado(String apellido, String nombre, String legajo) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.legajo = legajo;
    }

    public String mostrarDatos() {
        return legajo + " - " + nombre + " " + apellido;
    }

    public String getLegajo() {
        return legajo;
    }
}
