package org.example.Servicio;

import org.example.Modelo.NodoContacto;
import org.example.Modelo.Contacto;

import java.time.LocalDate;

// Esta clase representa una agenda de contactos
public class Agenda {
    private NodoContacto raiz; // Nodo raíz de la estructura de datos de tipo árbol binario de búsqueda

    // Constructor de la clase Agenda
    public Agenda() {
        this.raiz = null; // Al iniciar la agenda, la raíz se establece como nula, es decir, la agenda está vacía
    }

    // Método para agregar un nuevo contacto a la agenda
    public void agregarContacto(String nombre, long telefono, String correoElectronico, LocalDate fechaNacimiento) {
        // Se crea un nuevo objeto de tipo Contacto con los datos proporcionados
        Contacto nuevoContacto = new Contacto(nombre, telefono, correoElectronico, fechaNacimiento);
        // Si la agenda está vacía, el nuevo contacto se convierte en la raíz
        if (this.raiz == null) {
            this.raiz = new NodoContacto(nuevoContacto);
        } else {
            this.insertar(this.raiz, nuevoContacto); // Si la agenda no está vacía, se inserta el contacto en su lugar correspondiente en el árbol
        }
    }

    // Método privado para insertar un nuevo contacto en el árbol
    private void insertar(NodoContacto padre, Contacto contacto) {
        // Se compara el nombre del nuevo contacto con el nombre del contacto en el nodo actual del árbol
        // Si es menor, se inserta en el subárbol izquierdo; si es mayor, en el subárbol derecho
        if (contacto.getNombre().compareTo(padre.getContacto().getNombre()) < 0) {
            if (padre.getIzdo() == null) {
                padre.setIzdo(new NodoContacto(contacto)); // Si el subárbol izquierdo está vacío, se inserta el nuevo contacto aquí
            } else {
                insertar(padre.getIzdo(), contacto); // Si no está vacío, se realiza una llamada recursiva para seguir buscando el lugar adecuado
            }
        } else if (contacto.getNombre().compareTo(padre.getContacto().getNombre()) > 0) {
            if (padre.getDcho() == null) {
                padre.setDcho(new NodoContacto(contacto)); // Si el subárbol derecho está vacío, se inserta el nuevo contacto aquí
            } else {
                insertar(padre.getDcho(), contacto); // Si no está vacío, se realiza una llamada recursiva para seguir buscando el lugar adecuado
            }
        }
    }

    // Método público para buscar un contacto por nombre, teléfono o correo electrónico
    public Contacto buscarContacto(String parametro) {
        return buscar(this.raiz, parametro); // Se inicia la búsqueda desde la raíz del árbol
    }

    // Método privado para buscar un contacto en el árbol de forma recursiva
    private Contacto buscar(NodoContacto nodo, String parametro) {
        if (nodo == null) { // Si el nodo actual es nulo, significa que no se encontró el contacto
            return null;
        }
        // Se compara el parámetro de búsqueda con los datos del contacto en el nodo actual
        // Si coincide con el nombre, teléfono o correo electrónico, se devuelve el contacto
        if (parametro.equals(nodo.getContacto().getNombre()) || parametro.equals(String.valueOf(nodo.getContacto().getTelefono())) || parametro.equals(nodo.getContacto().getCorreoElectronico())) {
            return nodo.getContacto();
        } else if (parametro.compareTo(nodo.getContacto().getNombre()) < 0) {
            return buscar(nodo.getIzdo(), parametro); // Si el parámetro es menor, se busca en el subárbol izquierdo
        } else {
            return buscar(nodo.getDcho(), parametro); // Si el parámetro es mayor, se busca en el subárbol derecho
        }
    }

    // Método público para eliminar un contacto de la agenda
    public void eliminarContacto(String nombre) {
        this.raiz = eliminar(this.raiz, nombre); // Se inicia la eliminación desde la raíz del árbol
    }

    // Método privado para eliminar un contacto del árbol de forma recursiva
    private NodoContacto eliminar(NodoContacto nodo, String nombre) {
        if (nodo == null) { // Si el nodo actual es nulo, significa que no se encontró el contacto a eliminar
            return null;
        }
        // Se compara el nombre del contacto a eliminar con el nombre del contacto en el nodo actual
        // Si es menor, se elimina del subárbol izquierdo; si es mayor, del subárbol derecho
        if (nombre.compareTo(nodo.getContacto().getNombre()) < 0) {
            nodo.setIzdo(eliminar(nodo.getIzdo(), nombre)); // Se realiza una llamada recursiva para seguir buscando el contacto a eliminar
        } else if (nombre.compareTo(nodo.getContacto().getNombre()) > 0) {
            nodo.setDcho(eliminar(nodo.getDcho(), nombre)); // Se realiza una llamada recursiva para seguir buscando el contacto a eliminar
        } else {
            // Si se encontró el contacto a eliminar, se procede a realizar la eliminación
            if (nodo.getIzdo() == null) {
                return nodo.getDcho(); // Si el subárbol izquierdo es nulo, se devuelve el subárbol derecho como nuevo nodo
            } else if (nodo.getDcho() == null) {
                return nodo.getIzdo(); // Si el subárbol derecho es nulo, se devuelve el subárbol izquierdo como nuevo nodo
            }
            // Si el nodo tiene ambos hijos, se busca el nodo con el valor mínimo en el subárbol derecho para reemplazar al nodo a eliminar
            NodoContacto temp = minValorNodo(nodo.getDcho());
            nodo.getContacto().setTelefono(temp.getContacto().getTelefono());
            nodo.getContacto().setNombre(temp.getContacto().getNombre());
            nodo.setDcho(eliminar(nodo.getDcho(), temp.getContacto().getNombre())); // Se realiza una llamada recursiva para eliminar el nodo con el valor mínimo
        }
        return nodo;
    }

    // Método privado para encontrar el nodo con el valor mínimo en un subárbol
    private NodoContacto minValorNodo(NodoContacto nodo) {
        NodoContacto actual = nodo;
        while (actual.getIzdo() != null) {
            actual = actual.getIzdo(); // Se recorre el subárbol izquierdo hasta encontrar el nodo
        }
        return actual; // Se devuelve el nodo encontrado
    }

    // Método público para mostrar todos los contactos de la agenda en orden alfabético
    public void mostrarContactos() {
        inOrden(this.raiz); // Se inicia el recorrido del árbol en orden inorden (izquierda, raíz, derecha)
    }

    // Método privado para recorrer el árbol en orden inorden de forma recursiva
    private void inOrden(NodoContacto nodo) {
        if (nodo != null) { // Si el nodo actual no es nulo, se sigue recorriendo el árbol
            inOrden(nodo.getIzdo()); // Se realiza una llamada recursiva para recorrer el subárbol izquierdo
            // Se imprime la información del contacto almacenado en el nodo actual
            System.out.println("Nombre: " + nodo.getContacto().getNombre() + ", Teléfono: " + nodo.getContacto().getTelefono()+ ", Correo electrónico: " + nodo.getContacto().getCorreoElectronico() +", Fecha de nacimiento: " + nodo.getContacto().getFechaNacimiento());
            inOrden(nodo.getDcho()); // Se realiza una llamada recursiva para recorrer el subárbol derecho
        }
    }

    public NodoContacto getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoContacto raiz) {
        this.raiz = raiz;
    }
}
