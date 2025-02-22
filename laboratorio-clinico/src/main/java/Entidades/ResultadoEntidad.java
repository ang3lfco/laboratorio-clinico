/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 * 
 * @author ReneEzequiel23
 */
public class ResultadoEntidad {
    private int id;
    private int idAnalisis;
    private int idParametro;
    private String valor;

    public ResultadoEntidad() {
    }

    public ResultadoEntidad(int idAnalisis, int idParametro, String valor) {
        this.idAnalisis = idAnalisis;
        this.idParametro = idParametro;
        this.valor = valor;
    }

    public ResultadoEntidad(int id, int idAnalisis, int idParametro, String valor) {
        this.id = id;
        this.idAnalisis = idAnalisis;
        this.idParametro = idParametro;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(int idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public int getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "ResultadoEntidad{" + "id=" + id + ", idAnalisis=" + idAnalisis + ", idParametro=" + idParametro + ", valor=" + valor + '}';
    }
}
