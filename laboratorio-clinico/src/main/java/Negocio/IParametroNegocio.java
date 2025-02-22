/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import Dtos.GuardarParametroDTO;
import Dtos.ParametroDTO;
import Dtos.ParametroTablaDTO;
import java.util.List;

/**
 *
 * @author ReneEzequiel23
 */
public interface IParametroNegocio {
    List<ParametroTablaDTO> buscarParametros() throws NegocioException;

    ParametroDTO guardar(GuardarParametroDTO parametro) throws NegocioException;

    ParametroDTO buscarPorId(int id) throws NegocioException;
}
