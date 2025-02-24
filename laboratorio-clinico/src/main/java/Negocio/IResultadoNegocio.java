/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import Dtos.ResultadoDTO;
import java.util.List;

/**
 *
 * @author ang3lfco
 */
public interface IResultadoNegocio {
    List<ResultadoDTO> buscarResultados() throws NegocioException;
    ResultadoDTO guardar(ResultadoDTO resultadoDTO) throws NegocioException;
    ResultadoDTO buscarId(int id) throws NegocioException;
}
