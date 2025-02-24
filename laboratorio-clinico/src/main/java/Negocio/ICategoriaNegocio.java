/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import Dtos.CategoriaDTO;
import java.util.List;

/**
 *
 * @author ang3lfco
 */
public interface ICategoriaNegocio {
    public List<CategoriaDTO> buscarCategorias() throws NegocioException;
    public CategoriaDTO guardar(CategoriaDTO categoriaDTO) throws NegocioException;
    CategoriaDTO buscarId(int id) throws NegocioException;
}
