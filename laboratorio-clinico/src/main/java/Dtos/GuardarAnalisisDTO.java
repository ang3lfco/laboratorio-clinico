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
public class GuardarAnalisisDTO {
    private LocalDateTime fechaHora;
    private int idCliente;

    public GuardarAnalisisDTO() {
    }

    public GuardarAnalisisDTO(LocalDateTime fechaHora, int idCliente) {
        this.fechaHora = fechaHora;
        this.idCliente = idCliente;
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
    
    
}
