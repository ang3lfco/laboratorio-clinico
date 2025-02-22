/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dtos.AnalisisDTO;
import Dtos.AnalisisTablaDTO;
import Dtos.EditarAnalisisDTO;
import Dtos.GuardarAnalisisDTO;
import Entidades.AnalisisEntidad;
import Entidades.ClienteEntidad;
import java.util.ArrayList;
import java.util.List;
import Persistencia.IAnalisisDAO;
import Persistencia.PersistenciaException;

/**
 *
 * @author ReneEzequiel23
 */
public class AnalisisNegocio implements IAnalisisNegocio{
    private IAnalisisDAO analisisDAO;

    public AnalisisNegocio(IAnalisisDAO analisisDAO) {
        this.analisisDAO = analisisDAO;
    }

    @Override
    public List<AnalisisTablaDTO> buscarAnalisis() throws NegocioException {
    try {
            List<AnalisisEntidad> analisisEntidadLista = this.analisisDAO.buscarAnalisis(); //2

            return this.convertirAnalisisTablaDTO(analisisEntidadLista);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public AnalisisDTO guardar(GuardarAnalisisDTO analisis) throws NegocioException {
        try {
             this.validarInformacionGuardarAnalisis(analisis);

             AnalisisEntidad analisisGuardado = this.analisisDAO.guardar(analisis);
            return this.convertirAnalisisDTO(analisisGuardado);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public AnalisisDTO editar(EditarAnalisisDTO analisis) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AnalisisDTO eliminar(int id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AnalisisDTO buscarPorId(int id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private List<AnalisisTablaDTO> convertirAnalisisTablaDTO(List<AnalisisEntidad> analisisEntidadLista) {
        if (analisisEntidadLista == null) {
            return null;
        }
        
        List<AnalisisTablaDTO> analisisLista = new ArrayList<>();
        
        for (AnalisisEntidad entidad : analisisEntidadLista) {
            AnalisisTablaDTO dato = new AnalisisTablaDTO(
            entidad.getId(),
            entidad.getFechaHora(),
            entidad.getIdCliente(),
            entidad.isEstaBorrado());
            
            analisisLista.add(dato);
        }
        
        return analisisLista;
    }

    private AnalisisDTO convertirAnalisisDTO(AnalisisEntidad analisis) {
        if (analisis == null) {
            return null;
        }
        return new AnalisisDTO(
        analisis.getId(),
        analisis.getFechaHora(),
        analisis.getIdCliente(),
        analisis.isEstaBorrado());
    }

    private void validarInformacionGuardarAnalisis(GuardarAnalisisDTO analisis) {
        System.out.println("VAlidacionDeGuardado");
    }
    
    
}
