/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Dtos.EditarAnalisisDTO;
import Dtos.GuardarAnalisisDTO;
import Entidades.AnalisisEntidad;
import Entidades.ClienteEntidad;
import java.util.List;

/**
 *
 * @author ReneEzequiel23
 */
public interface IAnalisisDAO {
    List<AnalisisEntidad> buscarAnalisis() throws PersistenciaException;
    
    AnalisisEntidad guardar(GuardarAnalisisDTO analisis) throws PersistenciaException;

    AnalisisEntidad editar(EditarAnalisisDTO analisis) throws PersistenciaException;
    
    AnalisisEntidad eliminar(int id) throws PersistenciaException;
    
    AnalisisEntidad buscarPorId(int id) throws PersistenciaException;
    
    ClienteEntidad obtenerClientePorAnalisis(int idAnalisis) throws PersistenciaException;
}
