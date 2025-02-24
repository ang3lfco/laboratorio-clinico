/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dtos.EditarAnalisisDTO;
import Dtos.GuardarAnalisisDTO;
import Entidades.AnalisisEntidad;
import Entidades.ClienteEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ReneEzequiel23
 */
public class AnalisisDAO implements IAnalisisDAO{

    private IConexionBD conexionBD;
    private Connection conexionGeneral;
    
    /**
     * Constructor que recibe una instancia de IConexionBD para la conexión a la
     * base de datos.
     *
     * @param conexionBD Objeto que implementa la interfaz IConexionBD para la
     * conexión a la base de datos.
     */
    public AnalisisDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    /**
     * 
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public List<AnalisisEntidad> buscarAnalisis() throws PersistenciaException {
        try {
            String consultaSQL = """
                                 SELECT
                                 	id,
                                 	fechaHora,
                                 	estaBorrado,
                                 	idCliente         
                                 FROM Analisis
                                 WHERE estaBorrado = 0;                                 
                                 """;
            Connection conexion = this.conexionBD.crearConexion();
            PreparedStatement preparedStatement = conexion.prepareStatement(consultaSQL);
            ResultSet resultado = preparedStatement.executeQuery();

            List<AnalisisEntidad> analisisEntidadLista = null;
            while (resultado.next()) {
                if (analisisEntidadLista == null) {
                    analisisEntidadLista = new ArrayList<>();
                }

                analisisEntidadLista.add(this.convertirAnalisisAEntidad(resultado));
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return analisisEntidadLista;
        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage());
        }
    }

    /**
     * 
     * @param analisis
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public AnalisisEntidad guardar(GuardarAnalisisDTO analisis) throws PersistenciaException {
        try {
            this.conexionGeneral = this.conexionBD.crearConexion();
            this.conexionGeneral.setAutoCommit(false);
            int id = this.GuardarAnalisis(analisis);
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
            throw new PersistenciaException("Ocurrió un error al registrar el analisis, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
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
    
    /**
     * 
     * @param analisis
     * @return
     * @throws SQLException 
     */
    private int GuardarAnalisis(GuardarAnalisisDTO analisis) throws SQLException{
        int id = 0;
        String insert = """
                                    INSERT INTO `Analisis` (`fechaHora`,
                                                           `idCliente`)
                                                   VALUES (?, ? );
                                    """;
        try (PreparedStatement preparedStatement = conexionGeneral.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(2, analisis.getIdCliente());
            preparedStatement.setTimestamp(1, Timestamp.valueOf(analisis.getFechaHora()));
            preparedStatement.executeUpdate();

            try (ResultSet resultado = preparedStatement.getGeneratedKeys()) {
                if (resultado.next()) {
                    id = resultado.getInt(1);
                }
            }
        }
        return id;
    }

    /**
     * 
     * @param analisis
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public AnalisisEntidad editar(EditarAnalisisDTO analisis) throws PersistenciaException {
        try {
            this.conexionGeneral = this.conexionBD.crearConexion();
            this.conexionGeneral.setAutoCommit(false);
            int id = this.EditarAnalisis(analisis);
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
    
    /**
     * 
     * @param analisis
     * @return
     * @throws SQLException 
     */
    private int EditarAnalisis(EditarAnalisisDTO analisis) throws SQLException {
        String updateAlumno = """
                                UPDATE Analisis 
                                SET fechaHora = ?,
                                    estaBorrado = ?,
                                    idCliente = ?
                                WHERE id = ?
                                """;

        try (PreparedStatement preparedStatement = conexionGeneral.prepareStatement(updateAlumno)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(analisis.getFechaHora()));
            preparedStatement.setBoolean(2, analisis.isEstaBorrado());
            preparedStatement.setInt(3, analisis.getIdCliente());
            preparedStatement.setInt(4, analisis.getId());
            preparedStatement.executeUpdate();

        }
        return analisis.getId();
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public AnalisisEntidad eliminar(int id) throws PersistenciaException {
        try {
            this.conexionGeneral = this.conexionBD.crearConexion();
            this.conexionGeneral.setAutoCommit(false);
            this.eliminarAnalisis(id);
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
    
    /**
     * 
     * @param id
     * @return
     * @throws SQLException 
     */
    private int eliminarAnalisis(int id) throws SQLException {

        String update = """
                                UPDATE Analisis 
                                SET estaBorrado = 1
                                WHERE id = ?
                                """;

        try (PreparedStatement preparedStatement = conexionGeneral.prepareStatement(update)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        }
        return id;

    }
    
    /**
     * 
     * @param id
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public AnalisisEntidad buscarPorId(int id) throws PersistenciaException {
        try {
            AnalisisEntidad analisis = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               SELECT
                                    id,
                                    fechaHora,
                                    idCliente,
                                    estaBorrado
                               FROM Analisis
                               WHERE id = ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                analisis = this.convertirAnalisisAEntidad(resultado);
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return analisis;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }
    
    /**
     * 
     * @param resultado
     * @return
     * @throws SQLException 
     */
    private AnalisisEntidad convertirAnalisisAEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        int idCliente = resultado.getInt("idCliente");
        LocalDateTime fechaHora = resultado.getTimestamp("fechaHora").toLocalDateTime();
        boolean estaBorrado = resultado.getBoolean("estaborrado");
        return new AnalisisEntidad(id, fechaHora, idCliente , estaBorrado);
    }
    
    @Override
    public ClienteEntidad obtenerClientePorAnalisis(int idAnalisis) throws PersistenciaException {
        try {
            AnalisisEntidad analisis = buscarPorId(idAnalisis);

            String consultaSQL = """
                SELECT id, nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, estaBorrado 
                FROM Clientes 
                WHERE id = ?;
            """;

            Connection conexion = this.conexionBD.crearConexion();
            PreparedStatement preparedStatement = conexion.prepareStatement(consultaSQL);
            preparedStatement.setInt(1, analisis.getIdCliente());

            ResultSet resultado = preparedStatement.executeQuery();

            ClienteEntidad cliente = null;
            if (resultado.next()) {
                cliente = new ClienteEntidad(
                    resultado.getInt("id"),
                    resultado.getString("nombres"),
                    resultado.getString("apellidoPaterno"),
                    resultado.getString("apellidoMaterno"),
                    resultado.getDate("fechaNacimiento").toLocalDate(),
                    resultado.getBoolean("estaBorrado")
                );
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return cliente;
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al obtener el cliente asociado al análisis: " + ex.getMessage());
        }
    }

}
