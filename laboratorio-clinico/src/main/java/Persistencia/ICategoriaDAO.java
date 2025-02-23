/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Dtos.CategoriaDTO;
import Entidades.CategoriaEntidad;
import java.util.List;

/**
 *
 * @author ang3lfco
 */
public interface ICategoriaDAO {
    List<CategoriaEntidad> buscarCategorias() throws PersistenciaException;
    CategoriaEntidad guardar(CategoriaDTO categoria) throws PersistenciaException;
    CategoriaEntidad buscarId(int id) throws PersistenciaException;
}
