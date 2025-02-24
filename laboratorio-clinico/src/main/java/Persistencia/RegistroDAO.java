/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dtos.GuardarRegistroDTO;
import Entidades.RegistroEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ang3lfco
 */
public class RegistroDAO implements IRegistroDAO{
    
    private IConexionBD conexionBD;
    
    public RegistroDAO(IConexionBD conexionBD){
        this.conexionBD = conexionBD;
    }
    
    @Override
    public RegistroEntidad guardar(GuardarRegistroDTO registroDTO) throws PersistenciaException {
        String query = "INSERT INTO registros (idAnalisis, idPrueba) VALUES (?, ?)";
        try{
            Connection mySqlConn = conexionBD.crearConexion();
            PreparedStatement pstm = mySqlConn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            mySqlConn.setAutoCommit(false);
            pstm.setInt(1, registroDTO.getIdAnalisis());
            pstm.setInt(2, registroDTO.getIdPrueba());
            if(pstm.executeUpdate() == 0){
                throw new PersistenciaException("No se pudo guardar Registro.");
            }
            int idRegistro;
            
            ResultSet rs = pstm.getGeneratedKeys();
            if(rs.next()){
                idRegistro = rs.getInt(1);
            }
            else{
                mySqlConn.rollback();
                throw new PersistenciaException("No se obtuvo ID de Registro.");
            }
            mySqlConn.commit();
            return buscarId(idRegistro);
        }
        catch(SQLException e){
            throw new PersistenciaException("Error. " + e.getMessage());
        }
    }
    
    public RegistroEntidad buscarId(int id) throws PersistenciaException{
        String query = "SELECT id, idAnalisis, idPrueba FROM registros WHERE id = ?";
        try{
            Connection mySqlConn = conexionBD.crearConexion();
            PreparedStatement pstm = mySqlConn.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            if (rs.next()) {
                int idRegistro = rs.getInt("id");
                int idAnalisis = rs.getInt("idAnalisis");
                int idPrueba = rs.getInt("idPrueba");

                return new RegistroEntidad(idRegistro, idAnalisis, idPrueba);
            }
            return null;
        }
        catch(SQLException e){
            throw new PersistenciaException(e.getMessage());
        }
    }
}
