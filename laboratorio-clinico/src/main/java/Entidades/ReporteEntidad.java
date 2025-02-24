/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author ReneEzequiel23
 */
public class ReporteEntidad {
    private int idCliente;
    private String cliente;
    private String prueba;
    private int analisis;
    private LocalDateTime fechaHora;

    public ReporteEntidad() {
    }

    public ReporteEntidad(int idCliente, String cliente, String prueba, int analisis, LocalDateTime fechaHora) {
        this.idCliente = idCliente;
        this.cliente = cliente;
        this.prueba = prueba;
        this.analisis = analisis;
        this.fechaHora = fechaHora;
    }

    public ReporteEntidad(String cliente, String prueba, int analisis, LocalDateTime fechaHora) {
        this.cliente = cliente;
        this.prueba = prueba;
        this.analisis = analisis;
        this.fechaHora = fechaHora;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    public int getAnalisis() {
        return analisis;
    }

    public void setAnalisis(int analisis) {
        this.analisis = analisis;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "ReporteEntidad{" + "idCliente=" + idCliente + ", cliente=" + cliente + ", prueba=" + prueba + ", analisis=" + analisis + ", fechaHora=" + fechaHora + '}';
    }
    
}
