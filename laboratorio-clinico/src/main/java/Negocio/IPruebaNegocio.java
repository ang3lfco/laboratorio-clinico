/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import Dtos.GuardarPruebaDTO;
import Dtos.PruebaDTO;
import Dtos.PruebaTablaDTO;
import java.util.List;

/**
 *
 * @author ang3lfco
 */
public interface IPruebaNegocio {
    List<PruebaTablaDTO> buscarPruebas() throws NegocioException;
    PruebaDTO guardar(GuardarPruebaDTO prueba) throws NegocioException;
}
