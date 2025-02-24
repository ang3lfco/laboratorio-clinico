/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dtos;

/**
 *
 * @author ReneEzequiel23
 */
public class EmpleadoDTO {
    private int id;
    private String usuario;
    private String pass;
    private String tipo;

    public EmpleadoDTO(int id, String usuario, String pass, String tipo) {
        this.id = id;
        this.usuario = usuario;
        this.pass = pass;
        this.tipo = tipo;
    }

    public EmpleadoDTO(String usuario, String pass, String tipo) {
        this.usuario = usuario;
        this.pass = pass;
        this.tipo = tipo;
    }

    public EmpleadoDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "EmpleadoDTO{" + "id=" + id + ", usuario=" + usuario + ", pass=" + pass + ", tipo=" + tipo + '}';
    }
    
    
}
