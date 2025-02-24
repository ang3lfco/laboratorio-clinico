/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Entidades.ReporteEntidad;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author ReneEzequiel23
 */
public interface IReporteDAO {
    List<ReporteEntidad> crearReporte(Date inicio, Date fin) throws PersistenciaException;
}
