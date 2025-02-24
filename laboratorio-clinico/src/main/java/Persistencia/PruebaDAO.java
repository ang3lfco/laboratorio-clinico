/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dtos.GuardarPruebaDTO;
import Entidades.PruebaEntidad;
import Negocio.NegocioException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    @Override
    public PruebaEntidad guardar(GuardarPruebaDTO prueba) throws PersistenciaException{
        String query = "INSERT INTO pruebas (nombre, idCategoria) VALUES (?, ?)";
        try{
            Connection mySqlConn = conexionBD.crearConexion();
            PreparedStatement pstm = mySqlConn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            mySqlConn.setAutoCommit(false);
            pstm.setString(1, prueba.getNombre());
            pstm.setInt(2, prueba.getIdCategoria());
            if(pstm.executeUpdate() == 0){
                throw new PersistenciaException("No se puedo guardar Prueba.");
            }
            int idCategoria;
            
            ResultSet rs = pstm.getGeneratedKeys();
            if(rs.next()){
                idCategoria = rs.getInt(1);
            }
            else{
                mySqlConn.rollback();
                throw new PersistenciaException("No se obtuvo ID de Prueba.");
            }
            mySqlConn.commit();
            return buscarId(idCategoria);
        }
        catch(SQLException e){
            throw new PersistenciaException("Error. " + e.getMessage());
        }
    }
    
    public PruebaEntidad buscarId(int id) throws PersistenciaException{
        String query = "SELECT id, nombre, idCategoria, estaBorrado FROM pruebas WHERE id = ?";
        try{
            Connection mySqlConn = conexionBD.crearConexion();
            PreparedStatement pstm = mySqlConn.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            if (rs.next()) {
                int idPrueba = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int idCategoria = rs.getInt("idCategoria");
                boolean estaBorrado = rs.getBoolean("estaBorrado");
                

                return new PruebaEntidad(idPrueba, nombre, idCategoria, estaBorrado);
            }
            return null;
        }
        catch(SQLException e){
            throw new PersistenciaException(e.getMessage());
        }
    }
    
    @Override
    public List<String> obtenerParametrosPorPrueba(int idPrueba) throws NegocioException{
        List<String> parametros = new ArrayList<>();
        String query = "SELECT p.nombre FROM parametros p JOIN Mediciones m ON p.id = m.idParametro WHERE m.idPrueba = ?";
        
        try{
            Connection mySqlConn = conexionBD.crearConexion();
            PreparedStatement pstm = mySqlConn.prepareStatement(query);
            pstm.setInt(1, idPrueba);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                parametros.add(rs.getString("nombre"));
            }
        }
        catch(SQLException e){
            throw new NegocioException("Error al obtener parametros. " + e.getMessage());
        }
        return parametros;
    }
}