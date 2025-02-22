/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 * 
 * @author ReneEzequiel23
 */
public class MedicionEntidad {
    private int id;
    private int idPrueba;
    private int idParametro;

    public MedicionEntidad() {
    }

    public MedicionEntidad(int id, int idPrueba, int idParametro) {
        this.id = id;
        this.idPrueba = idPrueba;
        this.idParametro = idParametro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(int idPrueba) {
        this.idPrueba = idPrueba;
    }

    public int getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }

    @Override
    public String toString() {
        return "MedicionEntidad{" + "id=" + id + ", idPrueba=" + idPrueba + ", idParametro=" + idParametro + '}';
    }
    
    
}
