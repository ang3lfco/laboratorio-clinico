/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 * 
 * @author ReneEzequiel23
 */
public class PruebaEntidad {
    private int id;
    private String nombre;
    private boolean estaBorrado;
    private int idCategoria;

    public PruebaEntidad() {
    }

    public PruebaEntidad(String nombre, boolean estaBorrado, int idCategoria) {
        this.nombre = nombre;
        this.estaBorrado = estaBorrado;
        this.idCategoria = idCategoria;
    }

    public PruebaEntidad(int id, String nombre, boolean estaBorrado, int idCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.estaBorrado = estaBorrado;
        this.idCategoria = idCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public boolean isEstaBorrado() {
        return estaBorrado;
    }

    public void setEstaBorrado(boolean estaBorrado) {
        this.estaBorrado = estaBorrado;
    }

    @Override
    public String toString() {
        return "PruebaEntidad{" + "id=" + id + ", nombre=" + nombre + ", idCategoria=" + idCategoria + ", estaBorrado=" + estaBorrado + '}';
    }
    
    
}
