/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dtos.CategoriaDTO;
import Dtos.GuardarMedicionDTO;
import Entidades.CategoriaEntidad;
import Entidades.MedicionEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ang3lfco
 */
public class MedicionDAO implements IMedicionDAO{
    private IConexionBD conexionBD;
    
    public MedicionDAO(IConexionBD conexionBD){
        this.conexionBD = conexionBD;
    }
    
    @Override
    public MedicionEntidad guardar(GuardarMedicionDTO medicion) throws PersistenciaException {
        String query = "INSERT INTO mediciones (idPrueba, idParametro) VALUES (?, ?)";
        try{
            Connection mySqlConn = conexionBD.crearConexion();
            PreparedStatement pstm = mySqlConn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            mySqlConn.setAutoCommit(false);
            pstm.setInt(1, medicion.getIdPrueba());
            pstm.setInt(2, medicion.getIdParametro());
            if(pstm.executeUpdate() == 0){
                throw new PersistenciaException("No se puedo guardar Medicion.");
            }
            int idMedicion;
            
            ResultSet rs = pstm.getGeneratedKeys();
            if(rs.next()){
                idMedicion = rs.getInt(1);
            }
            else{
                mySqlConn.rollback();
                throw new PersistenciaException("No se obtuvo ID de Medicion.");
            }
            mySqlConn.commit();
            return buscarId(idMedicion);
        }
        catch(SQLException e){
            throw new PersistenciaException("Error. " + e.getMessage());
        }
    }
    
    public MedicionEntidad buscarId(int id) throws PersistenciaException{
        String query = "SELECT id, idPrueba, idParametro FROM mediciones WHERE id = ?";
        try{
            Connection mySqlConn = conexionBD.crearConexion();
            PreparedStatement pstm = mySqlConn.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            if (rs.next()) {
                int idMedicion = rs.getInt("id");
                int idPrueba = rs.getInt("idPrueba");
                int idParametro = rs.getInt("idParametro");

                return new MedicionEntidad(idMedicion, idPrueba, idParametro);
            }
            return null;
        }
        catch(SQLException e){
            throw new PersistenciaException(e.getMessage());
        }
    }
}
