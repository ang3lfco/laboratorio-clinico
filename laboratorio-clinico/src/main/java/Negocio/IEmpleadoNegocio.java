/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import Dtos.EmpleadoDTO;

/**
 *
 * @author ReneEzequiel23
 */
public interface IEmpleadoNegocio {
    EmpleadoDTO logIn(String usuario, String pass) throws NegocioException;
}
