/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dtos;

/**
 *
 * @author ang3lfco
 */
public class ResultadoDTO {
    private int id;
    private int idAnalisis;
    private int idParametro;
    private String valor;

    public ResultadoDTO() {
    }

    public ResultadoDTO(int idAnalisis, int idParametro, String valor) {
        this.idAnalisis = idAnalisis;
        this.idParametro = idParametro;
        this.valor = valor;
    }

    public ResultadoDTO(int id, int idAnalisis, int idParametro, String valor) {
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
        return "ResultadoDTO{" + "id=" + id + ", idAnalisis=" + idAnalisis + ", idParametro=" + idParametro + ", valor=" + valor + '}';
    }
}
