/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import Dtos.GuardarMedicionDTO;
import Dtos.MedicionDTO;

/**
 *
 * @author ang3lfco
 */
public interface IMedicionNegocio {
    MedicionDTO guardar(GuardarMedicionDTO medicion) throws NegocioException;
}
