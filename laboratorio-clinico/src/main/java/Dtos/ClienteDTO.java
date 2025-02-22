/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dtos;

import java.time.LocalDateTime;

/**
 * 
 * @author ReneEzequiel23
 */
public class ClienteDTO {
    private int id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDateTime fechaNacimiento;
    private boolean estaBorrado;
    
    public ClienteDTO() {
    }

    public ClienteDTO(String nombres, String apellidoPaterno, String apellidoMaterno, LocalDateTime fechaNacimiento, boolean estaBorrado) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.estaBorrado = estaBorrado;
    }

    public ClienteDTO(int id, String nombres, String apellidoPaterno, String apellidoMaterno, LocalDateTime fechaNacimiento, boolean estaBorrado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.estaBorrado = estaBorrado;
    }

    public boolean isEstaBorrado() {
        return estaBorrado;
    }

    public void setEstaBorrado(boolean estaBorrado) {
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

    public String getApeliidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apeliidoPaterno) {
        this.apellidoPaterno = apeliidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apeliidoMaterno) {
        this.apellidoMaterno = apeliidoMaterno;
    }

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" + "id=" + id + ", nombres=" + nombres + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", estaBorrado=" + estaBorrado + '}';
    }
}