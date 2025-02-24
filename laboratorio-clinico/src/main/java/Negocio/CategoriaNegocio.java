/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dtos.CategoriaDTO;
import Entidades.CategoriaEntidad;
import Persistencia.ICategoriaDAO;
import Persistencia.PersistenciaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ang3lfco
 */
public class CategoriaNegocio implements ICategoriaNegocio{
    private ICategoriaDAO categoriaDAO;
    
    public CategoriaNegocio(ICategoriaDAO categoriaDAO){
        this.categoriaDAO = categoriaDAO;
    }
    
    @Override
    public List<CategoriaDTO> buscarCategorias() throws NegocioException{
        try{
            List<CategoriaEntidad> categorias = categoriaDAO.buscarCategorias();
            return convertirDTO(categorias);
        }
        catch(PersistenciaException e){
            throw new NegocioException(e.getMessage());
        }
    }
    
    @Override
    public CategoriaDTO guardar(CategoriaDTO categoriaDTO) throws NegocioException{
        try{
            CategoriaEntidad categoria = categoriaDAO.guardar(categoriaDTO);
            return new CategoriaDTO(categoria.getId(), categoria.getNombre());
        }
        catch(PersistenciaException e){
            throw new NegocioException(e.getMessage());
        }
    }
    
    public List<CategoriaDTO> convertirDTO(List<CategoriaEntidad> categorias){
        List<CategoriaDTO> categoriasLista = new ArrayList<>();
        for(CategoriaEntidad c : categorias){
            CategoriaDTO cdto = new CategoriaDTO(c.getId(), c.getNombre());
            categoriasLista.add(cdto);
        }
        return categoriasLista;
    }
    
    @Override
    public CategoriaDTO buscarId(int id) throws NegocioException{
        try{
            CategoriaEntidad c = categoriaDAO.buscarId(id);
            return new CategoriaDTO(c.getId(), c.getNombre());
        }
        catch(PersistenciaException e){
            throw new NegocioException(e.getMessage());
        }
    }
}
