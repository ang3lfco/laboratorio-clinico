/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Dtos.GuardarRegistroDTO;
import Entidades.RegistroEntidad;

/**
 *
 * @author ang3lfco
 */
public interface IRegistroDAO {
    RegistroEntidad guardar(GuardarRegistroDTO registroDTO) throws PersistenciaException;
}
