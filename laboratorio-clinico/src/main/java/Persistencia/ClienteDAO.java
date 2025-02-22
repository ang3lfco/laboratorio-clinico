/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dtos.EditarClienteDTO;
import Dtos.GuardarClienteDTO;
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
 * Clase que implementa las operaciones de acceso a datos para la entidad Cliente.
 * Esta clase utiliza una conexión a la base de datos proporcionada por un
 * objeto IConexionBD.
 *
 * @author Usuario
 */
public class ClienteDAO implements IClienteDAO {

    private IConexionBD conexionBD;
    private Connection conexionGeneral;
    
    /**
     * Constructor que recibe una instancia de IConexionBD para la conexión a la
     * base de datos.
     *
     * @param conexionBD Objeto que implementa la interfaz IConexionBD para la
     * conexión a la base de datos.
     */
    public ClienteDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Obtiene una lista de todos los clientes en la base de datos
     * @return Una lista de objetos ClienteEntidad que sepresenta los clientes
     * en la base de datos
     * @throws PersistenciaException Si ocurre un error en la obtención de la
     * lista de clientes
     */
    @Override
    public List<ClienteEntidad> buscarClientes() throws PersistenciaException {
        try {
            String consultaSQL = """
                                 SELECT
                                 	id,
                                 	nombres,
                                 	apellidoPaterno,
                                 	apellidoMaterno, 
                                 	fechaNacimiento,
                                 	estaBorrado           
                                 FROM Clientes
                                 WHERE estaBorrado = 0;                                 
                                 """;
            Connection conexion = this.conexionBD.crearConexion();
            PreparedStatement preparedStatement = conexion.prepareStatement(consultaSQL);
            ResultSet resultado = preparedStatement.executeQuery();

            List<ClienteEntidad> clientesEntidadLista = null;
            while (resultado.next()) {
                if (clientesEntidadLista == null) {
                    clientesEntidadLista = new ArrayList<>();
                }

                clientesEntidadLista.add(this.convertirClienteAEntidad(resultado));
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return clientesEntidadLista;
        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage());
        }
    }

    /**
     * Crea un nuevo registro de cliente en la base de datos.
     * @param cliente Objeto guardarClienteDTO que representa el cliente a crear
     * @return Objeto ClienteEntidad que se creó
     * @throws PersistenciaException Si ocurre un error durante la operación de
     * persistencia
     */
    @Override
    public ClienteEntidad guardar(GuardarClienteDTO cliente) throws PersistenciaException {
        try {
            this.conexionGeneral = this.conexionBD.crearConexion();
            this.conexionGeneral.setAutoCommit(false);
            int id = this.GuardarCliente(cliente);
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

    /**
     * Crea un nuevo registro de cliente en la base de datos.
     * @param cliente Objeto guardarClienteDTO que representa el cliente a crear
     * @return id del cliente que se guardó en la base de datos
     * @throws SQLException Si ocurre un error al guardar el cliente
     */
    private int GuardarCliente(GuardarClienteDTO cliente) throws SQLException {
        int id = 0;
        String insert = """
                                    INSERT INTO `Clientes` (`nombres`,
                                                           `apellidopaterno`,
                                                           `apellidoMaterno`,
                                                           `fechaNacimiento`)
                                                   VALUES (?, ? , ? , ?);
                                    """;
        try (PreparedStatement preparedStatement = conexionGeneral.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, cliente.getNombres());
            preparedStatement.setString(2, cliente.getApellidoPaterno());
            preparedStatement.setString(3, cliente.getApellidoMaterno());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(cliente.getFechaNacimiento()));
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
     * Edita un cliente guardado en la base de datos
     * @param cliente Objeto EditarClienteDTO que representa el cliente a editar
     * @return Objeto cliente entidad que representa el cliente actualizado en
     * la base de datos
     * @throws PersistenciaException Si ocurre un error al editar el cliente
     * en la base de datos
     */
    @Override
    public ClienteEntidad editar(EditarClienteDTO cliente) throws PersistenciaException {
        try {
            this.conexionGeneral = this.conexionBD.crearConexion();
            this.conexionGeneral.setAutoCommit(false);
            int id = this.EditarCliente(cliente);
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
     * Edita un cliente guardado en la base de datos
     * @param cliente Objeto EditarClienteDTO que representa el cliente a editar
     * @return entero que representa el ID del cliente que se actualizó
     * @throws SQLException Si ocurre un error al editar el cliente
     */
    private int EditarCliente(EditarClienteDTO cliente) throws SQLException {

        String updateAlumno = """
                                UPDATE Clientes 
                                SET nombres = ?,
                                    apellidoPaterno = ?,
                                    apellidoMaterno = ?,
                                    fechaNacimiento = ?
                                WHERE id = ?
                                """;

        try (PreparedStatement preparedStatement = conexionGeneral.prepareStatement(updateAlumno)) {
            preparedStatement.setString(1, cliente.getNombres());
            preparedStatement.setString(2, cliente.getApellidoPaterno());
            preparedStatement.setString(3, cliente.getApellidoMaterno());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(cliente.getFechaNacimiento()));
            preparedStatement.setInt(5, cliente.getId());
            preparedStatement.executeUpdate();

        }
        return cliente.getId();

    }

    /**
     * Actualiza el estado estaBorrado del cliente a 1 representando que
     * está borrado
     * @param id entero que representa el ID del cliente a eliminar
     * @return Objeto clienteEntidad que representa el cliente borrado
     * @throws PersistenciaException Si ocurre un error al eliminar al cliente
     */
    @Override
    public ClienteEntidad eliminar(int id) throws PersistenciaException {
        try {
            this.conexionGeneral = this.conexionBD.crearConexion();
            this.conexionGeneral.setAutoCommit(false);
            this.eliminarCliente(id);
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
     * Actualiza el estado estaBorrado del cliente a 1 representando que
     * está borrado
     * @param id entero que representa el ID del cliente a eliminar
     * @return id entero que representa el ID del cliente eliminado
     * @throws SQLException Si ocurre un error al eliminar al cliente
     */
    private int eliminarCliente(int id) throws SQLException {

        String update = """
                                UPDATE Clientes 
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
     * Busca un cliente concreto en la base de datos
     * @param id entero que representa el ID del cliente en la base de datos
     * @return Objeto ClienteEntidad que representa el cliente encontrado en la
     * base de datos
     * @throws PersistenciaException Si ocurre un error al hacer la consulta
     */
    @Override
    public ClienteEntidad buscarPorId(int id) throws PersistenciaException {
        try {
            ClienteEntidad cliente = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               SELECT
                                    id,
                                    nombres,
                                    apellidopaterno,
                                    apellidoMaterno,
                                    fechaNacimiento,
                                    estaBorrado
                               FROM clientes
                               WHERE id = ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                cliente = this.convertirClienteAEntidad(resultado);
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

    /**
     * Convierte el objeto ResultSet a un objeto ClienteEntidad
     * @param resultado Objeto ResulSet con los datos del cliente
     * @return Objeto ClienteEntidad que representa al cliente recuperado de la
     * base de datos
     * @throws SQLException Si ocurre algún error al hacer la conversión
     */
    private ClienteEntidad convertirClienteAEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String nombres = resultado.getString("nombres");
        String apellidoPaterno = resultado.getString("apellidopaterno");
        String apellidoMaterno = resultado.getString("apellidomaterno");
        LocalDateTime fechaNacimiento = resultado.getTimestamp("fechaNacimiento").toLocalDateTime();
        boolean estaBorrado = resultado.getBoolean("estaborrado");
        return new ClienteEntidad(id, nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, estaBorrado);
    }
}
