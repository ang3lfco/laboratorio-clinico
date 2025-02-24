/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import Dtos.ReporteDTO;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ReneEzequiel23
 */
public interface IReporteNegocio {
    List<ReporteDTO> reporteDatos(Date inicio, Date fin) throws NegocioException;
}
