/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Entidades.EmpleadoEntidad;

/**
 *
 * @author ReneEzequiel23
 */
public interface IEmpleadoDAO {
    EmpleadoEntidad logIn(String usuario, String pass) throws PersistenciaException;
}
