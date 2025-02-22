/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dtos.GuardarParametroDTO;
import Entidades.ParametroEntidad;
import java.util.List;

/**
 *
 * @author ReneEzequiel23
 */
public interface IParametroDAO {
    
    List<ParametroEntidad> buscarParametros() throws PersistenciaException;

    ParametroEntidad guardar(GuardarParametroDTO parametro) throws PersistenciaException;
    
    ParametroEntidad buscarPorId(int id) throws PersistenciaException;
}
