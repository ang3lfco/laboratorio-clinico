/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dtos.GuardarParametroDTO;
import Entidades.ParametroEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ReneEzequiel23
 */
public class ParametroDAO implements IParametroDAO{

    private IConexionBD conexionBD;
    private Connection conexionGeneral;
    
    /**
     * Constructor que recibe una instancia de IConexionBD para la conexión a la
     * base de datos.
     *
     * @param conexionBD Objeto que implementa la interfaz IConexionBD para la
     * conexión a la base de datos.
     */
    public ParametroDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    @Override
    public List<ParametroEntidad> buscarParametros() throws PersistenciaException {
        try {
            String consultaSQL = """
                                 SELECT
                                 	id,
                                 	nombre           
                                 FROM Parametros;                                 
                                 """;
            Connection conexion = this.conexionBD.crearConexion();
            PreparedStatement preparedStatement = conexion.prepareStatement(consultaSQL);
            ResultSet resultado = preparedStatement.executeQuery();

            List<ParametroEntidad> parametroEntidadLista = null;
            while (resultado.next()) {
                if (parametroEntidadLista == null) {
                    parametroEntidadLista = new ArrayList<>();
                }
                
                parametroEntidadLista.add(this.convertirParametroAEntidad(resultado));
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return parametroEntidadLista;
        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage());
        }
    }

    @Override
    public ParametroEntidad guardar(GuardarParametroDTO parametro) throws PersistenciaException {
        try {
            this.conexionGeneral = this.conexionBD.crearConexion();
            this.conexionGeneral.setAutoCommit(false);
            int id = this.GuardarParametro(parametro);
            // Confirmar la transacción
            conexionGeneral.commit();
            return this.buscarPorId(id);
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
            throw new PersistenciaException("Ocurrió un error al registrar el cliente, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
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

    private ParametroEntidad convertirParametroAEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String nombre = resultado.getString("nombre");
        return new ParametroEntidad(id,nombre);
    }

    private int GuardarParametro(GuardarParametroDTO parametro) throws SQLException {
        int id = 0;
        String insert = """
                                    INSERT INTO `Parametros` (`nombre`)
                                                   VALUES (?);
                                    """;
        try (PreparedStatement preparedStatement = conexionGeneral.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, parametro.getNombre());
            preparedStatement.executeUpdate();

            try (ResultSet resultado = preparedStatement.getGeneratedKeys()) {
                if (resultado.next()) {
                    id = resultado.getInt(1);
                }
            }
        }
        return id;
    }

    @Override
    public ParametroEntidad buscarPorId(int id) throws PersistenciaException {
        try {
            ParametroEntidad cliente = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               SELECT
                                    id,
                                    nombre
                               FROM Parametros
                               WHERE id = ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);
            
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                cliente = this.convertirParametroAEntidad(resultado);
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return cliente;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }
    
}
