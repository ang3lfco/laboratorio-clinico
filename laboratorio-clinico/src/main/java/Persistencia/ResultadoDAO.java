/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dtos.ResultadoDTO;
import Entidades.ResultadoEntidad;
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
public class ResultadoDAO implements IResultadoDAO {
    private IConexionBD conexionBD;
    
    public ResultadoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    @Override
    public List<ResultadoEntidad> buscarResultados() throws PersistenciaException {
        try {
            String query = "SELECT id, idAnalisis, idParametro, valor FROM Resultados";
            Connection mySqlConn = conexionBD.crearConexion();
            List<ResultadoEntidad> resultados = new ArrayList<>();
            
            PreparedStatement pstm = mySqlConn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                resultados.add(new ResultadoEntidad(
                    rs.getInt("id"),
                    rs.getInt("idAnalisis"),
                    rs.getInt("idParametro"),
                    rs.getString("valor")
                ));
            }
            rs.close();
            pstm.close();
            mySqlConn.close();
            return resultados;
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }
    }
    
    @Override
    public ResultadoEntidad guardar(ResultadoDTO resultado) throws PersistenciaException {
        String query = "INSERT INTO Resultados (idAnalisis, idParametro, valor) VALUES (?, ?, ?)";
        try {
            Connection mySqlConn = conexionBD.crearConexion();
            PreparedStatement pstm = mySqlConn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            mySqlConn.setAutoCommit(false);
            pstm.setInt(1, resultado.getIdAnalisis());
            pstm.setInt(2, resultado.getIdParametro());
            pstm.setString(3, resultado.getValor());
            
            if (pstm.executeUpdate() == 0) {
                throw new PersistenciaException("No se pudo guardar el Resultado.");
            }
            
            int idResultado;
            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                idResultado = rs.getInt(1);
            } else {
                mySqlConn.rollback();
                throw new PersistenciaException("No se obtuvo ID de Resultado.");
            }
            mySqlConn.commit();
            return buscarId(idResultado);
        } catch (SQLException e) {
            throw new PersistenciaException("Error. " + e.getMessage());
        }
    }
    
    @Override
    public ResultadoEntidad buscarId(int id) throws PersistenciaException {
        String query = "SELECT id, idAnalisis, idParametro, valor FROM Resultados WHERE id = ?";
        try {
            Connection mySqlConn = conexionBD.crearConexion();
            PreparedStatement pstm = mySqlConn.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            if (rs.next()) {
                return new ResultadoEntidad(
                    rs.getInt("id"),
                    rs.getInt("idAnalisis"),
                    rs.getInt("idParametro"),
                    rs.getString("valor")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }
    }
}
