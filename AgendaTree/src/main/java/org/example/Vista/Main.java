package org.example.Vista;
import org.example.Modelo.Contacto;
import org.example.Servicio.Agenda;
import org.example.Servicio.Persistencia;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Agenda agenda = new Agenda();

        // Agregar contactos
        agenda.agregarContacto("Mario", 123456789, "skbidi@gmail.com", LocalDate.of(1985, 9, 13));
        agenda.agregarContacto("Zelda", 987654321, "sdfesffse@gmail.com", LocalDate.of(1986, 2, 21));
        agenda.agregarContacto("Peach", 456789123, "dsggklmgdrdr@gmail.com", LocalDate.of(1984, 10, 1));
        Persistencia.guardarAgenda(agenda);

        // Mostrar contactos
        System.out.println("Contactos en la agenda:");
        agenda.mostrarContactos();

        // Buscar un contacto
        System.out.println("\nBuscando el contacto de Link:");
        Contacto contacto = agenda.buscarContacto("123456789");
        if (contacto != null) {
            System.out.println("Nombre: " + contacto.getNombre() + ", Teléfono: " + contacto.getTelefono()+", Correo electrónico: " + contacto.getCorreoElectronico() + ", Fecha de nacimiento: " + contacto.getFechaNacimiento());
        } else {
            System.out.println("Contacto no encontrado.");
        }

        // Eliminar un contacto
        System.out.println("\nEliminando el contacto de Peach.");
        agenda.eliminarContacto("Peach");

        // Mostrar contactos después de la eliminación
        System.out.println("Contactos en la agenda después de eliminar a Alice:");
        agenda.mostrarContactos();
    }
}