package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp5.dominio.Cliente;
import ar.edu.unju.escmi.tp5.dominio.ClienteMayorista;
import ar.edu.unju.escmi.tp5.dominio.ClienteMinorista;


 // Collection de clientes.
public class CollectionCliente {
    public static List<Cliente> clientes = new ArrayList<>();

    public static boolean registroCliente(Cliente c) {
        // evita duplicados por codigo si es mayorista
        if (c.getCodigoCliente() > 0) {
            for (Cliente ex : clientes) if (ex.getCodigoCliente() == c.getCodigoCliente()) return false;
        }
        clientes.add(c);
        return true;
    }

    public static Cliente buscarClientePorCodigo(int cod) {
        for (Cliente c : clientes) {
            if (c.getCodigoCliente() == cod) return c;
        }
        return null;
    }

    public static void precargarClientes() {
        clientes.clear();
        ClienteMayorista cm = new ClienteMayorista("Juan", "Pérez", "Av. San Martín 123", 9001);
        ClienteMinorista cmin1 = new ClienteMinorista("Ana", "Gómez", "Calle 9 de Julio 456", "12345678", true);
        cmin1.asignarCodCliente(9002);
        ClienteMinorista cmin2 = new ClienteMinorista("Luis", "Sosa", "Belgrano 789", "87654321", false);
        cmin2.asignarCodCliente(9003);
        clientes.add(cm);
        clientes.add(cmin1);
        clientes.add(cmin2);
    }
}
