/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Dtos.ResultadoDTO;
import Entidades.ResultadoEntidad;
import java.util.List;

/**
 *
 * @author ang3lfco
 */
public interface IResultadoDAO {
    List<ResultadoEntidad> buscarResultados() throws PersistenciaException;
    ResultadoEntidad guardar(ResultadoDTO resultado) throws PersistenciaException;
    ResultadoEntidad buscarId(int id) throws PersistenciaException;
}
