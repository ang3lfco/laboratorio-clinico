/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.PruebaEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ang3lfco
 */
public class PruebaDAO implements IPruebaDAO{
    private IConexionBD conexionBD;

    public PruebaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    @Override
    public List<PruebaEntidad> buscarPruebas() throws PersistenciaException{
        try{
            String consulta = "SELECT id, nombre, idCategoria, estaBorrado FROM pruebas";
            Connection mySqlConn = conexionBD.crearConexion();
            List<PruebaEntidad> pruebas = new ArrayList<>();
            
            PreparedStatement pstm = mySqlConn.prepareStatement(consulta);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                pruebas.add(new PruebaEntidad(rs.getInt("id"), rs.getString("nombre"), rs.getInt("idCategoria"), rs.getBoolean("estaBorrado")));
            }
            rs.close();
            pstm.close();
            mySqlConn.close();
            return pruebas;
        }
        catch(SQLException e){
            throw new PersistenciaException(e.getMessage());
        }
    }
}