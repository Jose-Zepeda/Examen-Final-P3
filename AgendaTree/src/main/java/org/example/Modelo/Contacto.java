package org.example.Modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Contacto implements Serializable {
    private String nombre;
    private long telefono;

    private String correoElectronico;
    private LocalDate fechaNacimiento;

    public Contacto(String nombre, long telefono, String correoElectronico, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }0

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    //hacer el set para nombre
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String toString() {
        return "Nombre: " + this.nombre + ", Teléfono: " + this.telefono + ", Correo electrónico: " + this.correoElectronico + ", Fecha de nacimiento: " + this.fechaNacimiento;
    }
}
