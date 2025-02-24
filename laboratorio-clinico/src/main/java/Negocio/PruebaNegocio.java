/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dtos.GuardarPruebaDTO;
import Dtos.PruebaDTO;
import Dtos.PruebaTablaDTO;
import Entidades.PruebaEntidad;
import Persistencia.IPruebaDAO;
import Persistencia.PersistenciaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ang3lfco
 */
public class PruebaNegocio implements IPruebaNegocio{
    private IPruebaDAO pruebaDAO;
    
    public PruebaNegocio(IPruebaDAO pruebaDAO){
        this.pruebaDAO = pruebaDAO;
    }
    
    @Override
    public List<PruebaTablaDTO> buscarPruebas() throws NegocioException {
        try{
            List<PruebaTablaDTO> pruebasDTO = new ArrayList<>();
            List<PruebaEntidad> pruebas = pruebaDAO.buscarPruebas();
            for(PruebaEntidad p : pruebas){
                pruebasDTO.add(new PruebaTablaDTO(p.getId(), p.getNombre(), p.getIdCategoria(), p.isEstaBorrado()));
            }
            return pruebasDTO;
        }
        catch(PersistenciaException e){
            throw new NegocioException("Error. " + e.getMessage());
        }
    }
    
    @Override 
    public PruebaDTO guardar(GuardarPruebaDTO prueba) throws NegocioException{
        try{
            PruebaEntidad pruebaEntidad = pruebaDAO.guardar(prueba);
            return new PruebaDTO(pruebaEntidad.getId(), pruebaEntidad.getNombre(), pruebaEntidad.getIdCategoria(), pruebaEntidad.isEstaBorrado());
        }
        catch(PersistenciaException e){
            throw new NegocioException(e.getMessage());
        }
    }
    
    @Override
    public List<String> obtenerParametrosPorPrueba(int idPrueba) throws NegocioException{
        return pruebaDAO.obtenerParametrosPorPrueba(idPrueba);
    }
    
    @Override
    public PruebaDTO eliminar(int id) throws NegocioException {
            try {
            if (id <= 0) {
                throw new NegocioException("El id recibido es incorrecto");
            }
            PruebaEntidad alumnoBD = this.pruebaDAO.buscarId(id);
            if (alumnoBD == null) {
                throw new NegocioException("No se pudo obtener la prueba con la clave ingresada");
            }
            PruebaEntidad eliminado = this.pruebaDAO.eliminar(id);
            System.out.println(eliminado);
            return new PruebaDTO(eliminado.getId(), eliminado.getNombre(), eliminado.getIdCategoria(), eliminado.isEstaBorrado());
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }
    @Override
    public PruebaDTO editar(PruebaDTO prueba) throws NegocioException {
        try {
            
            PruebaEntidad modificado = this.pruebaDAO.editar(prueba);
            System.out.println(modificado);
            return new PruebaDTO(modificado.getId(), modificado.getNombre(), modificado.getIdCategoria(), modificado.isEstaBorrado());
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }
    
}
