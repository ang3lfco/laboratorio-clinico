/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 * 
 * @author ReneEzequiel23
 */
public class ParametroEntidad {
    private int id;
    private String nombre;

    public ParametroEntidad() {
    }

    public ParametroEntidad(String nombre) {
        this.nombre = nombre;
    }

    public ParametroEntidad(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "ParametroEntidad{" + "id=" + id + ", nombre=" + nombre + '}';
    }
}
