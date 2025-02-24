/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.AnalisisEntidad;
import Entidades.ReporteEntidad;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ReneEzequiel23
 */
public class ReporteDAO implements IReporteDAO{
    private IConexionBD conexionBD;
    
    public ReporteDAO(IConexionBD conexionBD){
        this.conexionBD = conexionBD;
    }
    
    @Override
    public List<ReporteEntidad> crearReporte(Date inicio, Date fin) throws PersistenciaException {
        try {
            ReporteEntidad reporte = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               CALL reporte(?,?)
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setDate(1, inicio);
            preparedStatement.setDate(2, fin);

            ResultSet resultado = preparedStatement.executeQuery();
            
            List<ReporteEntidad> reporteLista = null;
            while (resultado.next()) {
                if (reporteLista == null) {
                    reporteLista = new ArrayList<>();
                }

                reporteLista.add(this.convertirReporteAEntidad(resultado));
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return reporteLista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    private ReporteEntidad convertirReporteAEntidad(ResultSet resultado) throws SQLException {
    int id = resultado.getInt("id");
    String cliente = resultado.getString("cliente");
    String prueba = resultado.getString("nombre");
    int analisis = resultado.getInt("analisis");
    LocalDateTime fechaHora = resultado.getTimestamp("fechaHora").toLocalDateTime();
    return new ReporteEntidad(id, cliente, prueba, analisis, fechaHora);
    }
}
