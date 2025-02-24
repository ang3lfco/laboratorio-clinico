/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Dtos.GuardarMedicionDTO;
import Entidades.MedicionEntidad;

/**
 *
 * @author ang3lfco
 */
public interface IMedicionDAO {
    public MedicionEntidad guardar(GuardarMedicionDTO medicion) throws PersistenciaException;
}
