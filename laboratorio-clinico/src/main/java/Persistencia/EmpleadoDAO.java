/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.ClienteEntidad;
import Entidades.EmpleadoEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ReneEzequiel23
 */
public class EmpleadoDAO implements IEmpleadoDAO{
    private IConexionBD conexionBD;
    private Connection conexionGeneral;
    
    /**
     * Constructor que recibe una instancia de IConexionBD para la conexión a la
     * base de datos.
     *
     * @param conexionBD Objeto que implementa la interfaz IConexionBD para la
     * conexión a la base de datos.
     */
    public EmpleadoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    @Override
    public EmpleadoEntidad logIn(String usuario, String pass) throws PersistenciaException {
        try {
            EmpleadoEntidad empleado = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               SELECT
                                    id,
                                    usuario,
                                    pass,
                                    tipo
                               FROM Empleados
                               WHERE usuario = ? AND pass = ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, pass);

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                empleado = this.convertirEmpleadoAEntidad(resultado);
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return empleado;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }
    
    private EmpleadoEntidad convertirEmpleadoAEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String usuario = resultado.getString("usuario");
        String pass = resultado.getString("pass");
        String tipo = resultado.getString("tipo");
        return new EmpleadoEntidad(id, usuario, pass, tipo);
    }
}
