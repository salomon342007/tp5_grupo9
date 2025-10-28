package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp5.dominio.Empleado;


 // Collection de empleados (public static) y métodos de precarga.

public class CollectionEmpleado {
    public static List<Empleado> empleados = new ArrayList<>();

    public static void precargarEmpleados() {
        empleados.clear();
        empleados.add(new Empleado("Martinez", "Laura", "EMP001"));
        empleados.add(new Empleado("Sanchez", "Miguel", "EMP002"));
    }

    public static void agregarEmpleado(Empleado e) {
        empleados.add(e);
    }

    public static Empleado buscarPorLegajo(String legajo) {
        for (Empleado e : empleados) {
            if (e.getLegajo().equalsIgnoreCase(legajo))
                return e;
        }
        return null;
    }
}
