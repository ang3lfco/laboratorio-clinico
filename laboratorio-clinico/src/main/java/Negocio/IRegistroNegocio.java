/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import Dtos.GuardarRegistroDTO;
import Dtos.RegistroDTO;

/**
 *
 * @author ang3lfco
 */
public interface IRegistroNegocio {
    RegistroDTO guardar(GuardarRegistroDTO registroDTO) throws NegocioException;
}
