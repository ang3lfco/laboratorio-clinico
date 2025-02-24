/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dtos.CategoriaDTO;
import Entidades.CategoriaEntidad;
import Entidades.PruebaEntidad;
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
public class CategoriaDAO implements ICategoriaDAO{
    private IConexionBD conexionBD;
    
    public CategoriaDAO(IConexionBD conexionBD){
        this.conexionBD = conexionBD;
    }
    
    @Override
    public List<CategoriaEntidad> buscarCategorias() throws PersistenciaException{
        try{
            String query = "SELECT id, nombre FROM categorias";
            Connection mySqlConn = conexionBD.crearConexion();
            List<CategoriaEntidad> categorias = new ArrayList<>();
            
            PreparedStatement pstm = mySqlConn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                categorias.add(new CategoriaEntidad(rs.getInt("id"), rs.getString("nombre")));
            }
            rs.close();
            pstm.close();
            mySqlConn.close();
            return categorias;
        }
        catch(SQLException e){
            throw new PersistenciaException(e.getMessage());
        }
    }
    
    @Override
    public CategoriaEntidad guardar(CategoriaDTO categoria) throws PersistenciaException{
        String query = "INSERT INTO categorias (nombre) VALUES (?)";
        try{
            Connection mySqlConn = conexionBD.crearConexion();
            PreparedStatement pstm = mySqlConn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            mySqlConn.setAutoCommit(false);
            pstm.setString(1, categoria.getNombre());
            if(pstm.executeUpdate() == 0){
                throw new PersistenciaException("No se puedo guardar Categoria.");
            }
            int idCategoria;
            
            ResultSet rs = pstm.getGeneratedKeys();
            if(rs.next()){
                idCategoria = rs.getInt(1);
            }
            else{
                mySqlConn.rollback();
                throw new PersistenciaException("No se obtuvo ID de Categoria.");
            }
            mySqlConn.commit();
            return buscarId(idCategoria);
        }
        catch(SQLException e){
            throw new PersistenciaException("Error. " + e.getMessage());
        }
    }
    
    @Override
    public CategoriaEntidad buscarId(int id) throws PersistenciaException{
        String query = "SELECT id, nombre FROM categorias WHERE id = ?";
        try{
            Connection mySqlConn = conexionBD.crearConexion();
            PreparedStatement pstm = mySqlConn.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            if (rs.next()) {
                int idCategoria = rs.getInt("id");
                String nombre = rs.getString("nombre");

                return new CategoriaEntidad(idCategoria, nombre);
            }
            return null;
        }
        catch(SQLException e){
            throw new PersistenciaException(e.getMessage());
        }
    }
}
