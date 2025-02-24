/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dtos.GuardarPruebaDTO;
import Dtos.PruebaDTO;
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
    private Connection conexionGeneral;
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
    
    @Override
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
    
    @Override
    public PruebaEntidad eliminar(int id) throws PersistenciaException {
        try {
            this.conexionGeneral = this.conexionBD.crearConexion();
            this.conexionGeneral.setAutoCommit(false);
            this.eliminarPrueba(id);
            // Confirmar la transacción
            conexionGeneral.commit();
            return this.buscarId(id);
        } catch (SQLException | PersistenciaException ex) {
            try {
                // Deshacer cambios en caso de error
                if (this.conexionGeneral != null) {
                    this.conexionGeneral.rollback();
                }
            } catch (SQLException rollbackEx) {
                System.out.println("Error al hacer rollback: " + rollbackEx.getMessage());
            }
            System.out.println("Error al querer hacer la transaccion " + ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al editar el cliente, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        } finally {
            try {
                if (this.conexionGeneral != null) {
                    this.conexionGeneral.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexion de la base de datos");
            }
        }
        
    }
    
    private int eliminarPrueba(int id) throws SQLException {

        String update = """
                                UPDATE Pruebas 
                                SET estaBorrado = 1
                                WHERE id = ?
                                """;

        try (PreparedStatement preparedStatement = conexionGeneral.prepareStatement(update)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        }
        return id;

    }
    
    public PruebaEntidad editar(PruebaDTO prueba) throws PersistenciaException {
        try {
            this.conexionGeneral = this.conexionBD.crearConexion();
            this.conexionGeneral.setAutoCommit(false);
            int id = this.EditarPrueba(prueba);
            // Confirmar la transacción
            conexionGeneral.commit();
            return this.buscarId(id);
        } catch (SQLException | PersistenciaException ex) {
            try {
                // Deshacer cambios en caso de error
                if (this.conexionGeneral != null) {
                    this.conexionGeneral.rollback();
                }
            } catch (SQLException rollbackEx) {
                System.out.println("Error al hacer rollback: " + rollbackEx.getMessage());
            }
            System.out.println("Error al querer hacer la transaccion " + ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al editar el cliente, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        } finally {
            try {
                if (this.conexionGeneral != null) {
                    this.conexionGeneral.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexion de la base de datos");
            }
        }
    }

    private int EditarPrueba(PruebaDTO prueba) throws SQLException {
    String updateAlumno = """
                                UPDATE Pruebas 
                                SET nombre = ?
                                WHERE id = ?
                                """;

        try (PreparedStatement preparedStatement = conexionGeneral.prepareStatement(updateAlumno)) {
            preparedStatement.setString(1, prueba.getNombre());
            preparedStatement.setInt(2, prueba.getId());
            preparedStatement.executeUpdate();

        }
        return prueba.getId();
    }
}