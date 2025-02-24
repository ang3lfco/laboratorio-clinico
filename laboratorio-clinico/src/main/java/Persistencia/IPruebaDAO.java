/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Dtos.GuardarPruebaDTO;
import Entidades.PruebaEntidad;
import java.util.List;

/**
 *
 * @author ang3lfco
 */
public interface IPruebaDAO {
    List<PruebaEntidad> buscarPruebas() throws PersistenciaException;
    PruebaEntidad guardar(GuardarPruebaDTO prueba) throws PersistenciaException;
}
