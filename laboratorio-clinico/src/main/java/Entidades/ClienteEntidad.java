/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;

/**
 * 
 * @author ReneEzequiel23
 */
public class ClienteEntidad {
    private int id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private boolean estaBorrado;

    public ClienteEntidad() {
    }

    public ClienteEntidad(String nombres, String apeliidoPaterno, String apeliidoMaterno, LocalDate fechaNacimiento, boolean estaBorrado) {
        this.nombres = nombres;
        this.apellidoPaterno = apeliidoPaterno;
        this.apellidoMaterno = apeliidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.estaBorrado = estaBorrado;
    }

    public ClienteEntidad(int id, String nombres, String apeliidoPaterno, String apeliidoMaterno, LocalDate fechaNacimiento, boolean estaBorrado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apeliidoPaterno;
        this.apellidoMaterno = apeliidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.estaBorrado = estaBorrado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isEstaBorrado() {
        return estaBorrado;
    }

    public void setEstaBorrado(boolean estaBorrado) {
        this.estaBorrado = estaBorrado;
    }

    @Override
    public String toString() {
        return "ClienteEntidad{" + "id=" + id + ", nombres=" + nombres + ", apeliidoPaterno=" + apellidoPaterno + ", apeliidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", estaBorrado=" + estaBorrado + '}';
    }
}
