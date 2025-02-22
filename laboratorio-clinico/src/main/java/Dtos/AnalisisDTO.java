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
public class AnalisisDTO {
    private int id;
    private LocalDateTime fechaHora;
    private boolean estaBorrado;
    private int idCliente;

    public AnalisisDTO() {
    }

    public AnalisisDTO(LocalDateTime fechaHora, boolean estaBorrado, int idCliente) {
        this.fechaHora = fechaHora;
        this.estaBorrado = estaBorrado;
        this.idCliente = idCliente;
    }

    public AnalisisDTO(int id, LocalDateTime fechaHora, boolean estaBorrado, int idCliente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.estaBorrado = estaBorrado;
        this.idCliente = idCliente;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public boolean isEstaBorrado() {
        return estaBorrado;
    }

    public void setEstaBorrado(boolean estaBorrado) {
        this.estaBorrado = estaBorrado;
    }

    @Override
    public String toString() {
        return "AnalisisDTO{" + "id=" + id + ", fechaHora=" + fechaHora + ", estaBorrado=" + estaBorrado + ", idCliente=" + idCliente + '}';
    }
}
