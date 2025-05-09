/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Dtos.GuardarPruebaDTO;
import Dtos.PruebaDTO;
import Entidades.PruebaEntidad;
import Negocio.NegocioException;
import java.util.List;

/**
 *
 * @author ang3lfco
 */
public interface IPruebaDAO {
    List<PruebaEntidad> buscarPruebas() throws PersistenciaException;
    PruebaEntidad guardar(GuardarPruebaDTO prueba) throws PersistenciaException;
    List<String> obtenerParametrosPorPrueba(int idPrueba) throws NegocioException;
    PruebaEntidad eliminar(int id) throws PersistenciaException;
    PruebaEntidad buscarId(int id) throws PersistenciaException;
    PruebaEntidad editar(PruebaDTO prueba) throws PersistenciaException;
}
