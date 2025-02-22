/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author ReneEzequiel23
 */
public class ConexionBD implements IConexionBD{
    //BASE DE DATOS CON EL QUE CONECTAMOS A MYSQL
    private final String USUARIO = "root";
    private final String CONTRASEÑA = "10279Fig";
    //NECESARIOS PARA LA CONEXION
    private final String SERVIDOR = "127.0.0.1";//localhost
    private final String BASE_DE_DATOS = "LaboratorioClinico";
    private final String URL = "jdbc:mysql://" + SERVIDOR + "/" + BASE_DE_DATOS;

    @Override
    public Connection crearConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
    }

}
