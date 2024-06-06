package org.example.Servicio;

import org.example.Modelo.NodoContacto;

import java.io.*;

public class Persistencia {

    // Constante que define la ruta y el nombre del archivo donde se guardará la agenda.
    private static final String RUTA_ARCHIVO = "C:\\P3\\agenda.dat";

    // Método que guarda la agenda en un archivo.
    public static void guardarAgenda(Agenda agenda) {
        try {
            // Obtiene la raíz de la agenda.
            NodoContacto raiz = agenda.getRaiz();

            // Crea un flujo de salida para escribir en el archivo especificado por RUTA_ARCHIVO.
            FileOutputStream archivo = new FileOutputStream(RUTA_ARCHIVO);
            // Crea un flujo de salida de objetos para escribir el objeto en el archivo.
            ObjectOutputStream escritor = new ObjectOutputStream(archivo);
            // Escribe el objeto raíz de la agenda en el archivo.
            escritor.writeObject(agenda.getRaiz());
            // Cierra el flujo de salida de objetos.
            escritor.close();
            // Cierra el flujo de salida del archivo.
            archivo.close();

            // Imprime un mensaje indicando que la agenda se guardó correctamente.
            System.out.println("Agenda guardada correctamente en " + RUTA_ARCHIVO);
        } catch (IOException e) {
            // Si ocurre un error durante la operación de guardado, imprime la traza del error.
            e.printStackTrace();
        }
    }

    // Método que carga la agenda desde un archivo.
    public static Agenda cargarAgenda() {
        try {
            // Crea una nueva instancia de la agenda.
            Agenda agenda = new Agenda();
            // Crea un flujo de entrada para leer desde el archivo especificado por RUTA_ARCHIVO.
            FileInputStream archivo = new FileInputStream(RUTA_ARCHIVO);
            // Crea un flujo de entrada de objetos para leer el objeto desde el archivo.
            ObjectInputStream lector = new ObjectInputStream(archivo);
            // Lee el objeto raíz de la agenda desde el archivo.
            NodoContacto r = (NodoContacto) lector.readObject();
            // Cierra el flujo de entrada de objetos.
            lector.close();
            // Cierra el flujo de entrada del archivo.
            archivo.close();

            // Imprime un mensaje indicando que la agenda se cargó correctamente.
            System.out.println("Agenda cargada correctamente desde " + RUTA_ARCHIVO);
            // Devuelve una nueva instancia de la agenda con la raíz leída del archivo.
            return new Agenda(r);
        } catch (IOException | ClassNotFoundException e) {
            // Si ocurre un error durante la operación de carga, imprime la traza del error.
            e.printStackTrace();
            // Devuelve una nueva instancia vacía de la agenda en caso de error.
            return new Agenda();
        }
    }
}

