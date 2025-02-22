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
import java.time.LocalDateTime;

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
        try {
            this.validarCamposEnEditar(analisis);
            this.existeElAnalisis(analisis);
            
            AnalisisEntidad analisisModificado = this.analisisDAO.editar(analisis);
            System.out.println(analisisModificado);
            return this.convertirAnalisisDTO(analisisModificado);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public AnalisisDTO eliminar(int id) throws NegocioException {
        try {
            if (id <= 0) {
                throw new NegocioException("El id recibido es incorrecto");
            }
            AnalisisEntidad analisisBD = this.analisisDAO.buscarPorId(id);
            if (analisisBD == null) {
                throw new NegocioException("No se pudo obtener el analisis con la clave ingresada");
            }
            AnalisisEntidad analisisEliminado = this.analisisDAO.eliminar(id);
            System.out.println(analisisEliminado);
            return this.convertirAnalisisDTO(analisisEliminado);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public AnalisisDTO buscarPorId(int id) throws NegocioException {
    try {
            if (id <= 0) {
                throw new NegocioException("El id recibido es incorrecto");
            }
            AnalisisEntidad analisisBD = this.analisisDAO.buscarPorId(id);
            AnalisisDTO analisisDTO = this.convertirAnalisisDTO(analisisBD);
            return analisisDTO;
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
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

    private void validarInformacionGuardarAnalisis(GuardarAnalisisDTO analisis) throws NegocioException {
        LocalDateTime fechaHora = analisis.getFechaHora();
        int idCliente = analisis.getIdCliente();
        
        if (fechaHora == null) {
            throw new NegocioException ("La fecha del analisis no debe de estar en blanco");
        }
        if (idCliente<1) {
            throw new NegocioException("El id del cliente es incorrecto");
        }
    }

    private void validarCamposEnEditar(EditarAnalisisDTO analisis) throws NegocioException {
        int id = analisis.getId();
        LocalDateTime fechaHora = analisis.getFechaHora();
        int idCliente = analisis.getIdCliente();
        
        if (id<1) {
            throw new NegocioException("El id del cliente es incorrecto");
        }
        if (fechaHora == null) {
            throw new NegocioException ("La fecha del analisis no debe de estar en blanco");
        }
        if (idCliente<1) {
            throw new NegocioException("El id del cliente es incorrecto");
        }
    }
    
    private void existeElAnalisis(EditarAnalisisDTO analisis) throws PersistenciaException, NegocioException {
        AnalisisEntidad analisisBD = this.analisisDAO.buscarPorId(analisis.getId());
        if (analisisBD == null) {
            throw new NegocioException("No se pudo obtener el analisis con los parametros ingresados");
        }
    }

    
    
    
}
