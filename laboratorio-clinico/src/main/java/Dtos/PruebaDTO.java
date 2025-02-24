/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dtos;

/**
 *
 * @author ang3lfco
 */
public class PruebaDTO {
    private int id;
    private String nombre;
    private int idCategoria;
    private boolean estaBorrado;

    public PruebaDTO(int id, String nombre, int idCategoria, boolean estaBorrado) {
        this.id = id;
        this.nombre = nombre;
        this.idCategoria = idCategoria;
        this.estaBorrado = estaBorrado;
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
        return "PruebaDTO{" + "id=" + id + ", nombre=" + nombre + ", idCategoria=" + idCategoria + ", estaBorrado=" + estaBorrado + '}';
    }
}
